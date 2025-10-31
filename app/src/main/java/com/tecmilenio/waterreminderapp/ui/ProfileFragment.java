package com.tecmilenio.waterreminderapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.data.entities.User;
import com.tecmilenio.waterreminderapp.viewmodel.UserViewModel;

public class ProfileFragment extends Fragment {

    private EditText etNombre, etEdad, etPeso, etMeta;
    private RadioButton rbHombre, rbMujer;
    private Button btnGuardar;
    private TextView tvTituloRegistro, tvDatosUsuario;
    private LinearLayout layoutRegistro;

    private UserViewModel viewModel;
    private User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etNombre = view.findViewById(R.id.et_nombre);
        etEdad = view.findViewById(R.id.et_edad);
        etPeso = view.findViewById(R.id.et_peso);
        etMeta = view.findViewById(R.id.et_meta);
        rbHombre = view.findViewById(R.id.rb_hombre);
        rbMujer = view.findViewById(R.id.rb_mujer);
        btnGuardar = view.findViewById(R.id.btn_guardar_perfil);

        tvTituloRegistro = view.findViewById(R.id.tv_titulo_registro);
        layoutRegistro = view.findViewById(R.id.layout_registro);
        tvDatosUsuario = view.findViewById(R.id.tv_datos_usuario);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // 🔹 Cargar datos guardados automáticamente
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                currentUser = user;

                // Mostrar en formulario
                etNombre.setText(user.nombre);
                etEdad.setText(String.valueOf(user.edad));
                etPeso.setText(String.valueOf(user.peso));
                etMeta.setText(String.valueOf(user.metaDiariaMl));
                if ("Hombre".equals(user.genero)) rbHombre.setChecked(true);
                else rbMujer.setChecked(true);

                // Mostrar en la lista inferior
                tvTituloRegistro.setVisibility(View.VISIBLE);
                layoutRegistro.setVisibility(View.VISIBLE);
                tvDatosUsuario.setText(
                        "👤 " + user.nombre + "\n" +
                                "Edad: " + user.edad + " años\n" +
                                "Peso: " + user.peso + " kg\n" +
                                "Género: " + user.genero + "\n" +
                                "Meta diaria: " + user.metaDiariaMl + " ml"
                );

            } else {
                currentUser = null;
                layoutRegistro.setVisibility(View.GONE);
                tvTituloRegistro.setVisibility(View.GONE);
            }
        });

        // 🔹 Guardar o actualizar usuario
        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String edadStr = etEdad.getText().toString().trim();
            String pesoStr = etPeso.getText().toString().trim();
            String metaStr = etMeta.getText().toString().trim();

            if (nombre.isEmpty() || edadStr.isEmpty() || pesoStr.isEmpty() || metaStr.isEmpty()) {
                Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int edad = Integer.parseInt(edadStr);
            double peso = Double.parseDouble(pesoStr);
            int meta = Integer.parseInt(metaStr);
            String genero = rbHombre.isChecked() ? "Hombre" : "Mujer";

            if (currentUser == null) {
                // Insertar nuevo usuario
                viewModel.insertUser(new User(nombre, edad, peso, genero, meta));
                Toast.makeText(getContext(), "Perfil guardado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                // Actualizar el existente
                currentUser.nombre = nombre;
                currentUser.edad = edad;
                currentUser.peso = peso;
                currentUser.metaDiariaMl = meta;
                currentUser.genero = genero;
                viewModel.updateUser(currentUser);
                Toast.makeText(getContext(), "Perfil actualizado", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
