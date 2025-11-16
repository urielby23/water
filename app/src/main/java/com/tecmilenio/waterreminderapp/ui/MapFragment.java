package com.tecmilenio.waterreminderapp.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tecmilenio.waterreminderapp.R;

public class MapFragment extends Fragment implements SensorEventListener {

    private GoogleMap gMap;
    private FusedLocationProviderClient locationClient;
    private SensorManager sensorManager;

    private Sensor accelerometer;
    private Sensor lightSensor;
    private Sensor proximitySensor;
    private Sensor rotationSensor;
    private Sensor gyroSensor;

    private Marker userMarker;
    private TextView sensorInfo; // Texto para mostrar valores de sensores

    private float lastAngle = 0;

    // Control de actualización para evitar parpadeos
    private long lastUpdate = 0;

    // Último ángulo registrado para evitar movimientos bruscos del mapa

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    private final OnMapReadyCallback mapReadyCallback = googleMap -> {
        gMap = googleMap;

        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        enableUserLocation();
    };

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorInfo = view.findViewById(R.id.sensorInfo);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.google_map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(mapReadyCallback);
        }

        locationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    // ----------------------------
    //  UBICACIÓN Y PERMISOS
    // ----------------------------
    private void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }

        gMap.setMyLocationEnabled(true);

        locationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                moveCamera(location);
                showUserMarker(location);
            }
        });
    }

    private void moveCamera(Location location) {
        LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(pos)
                .zoom(17)
                .build();

        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void showUserMarker(Location location) {
        LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());

        if (userMarker != null) userMarker.remove();

        userMarker = gMap.addMarker(new MarkerOptions()
                .position(pos)
                .title("Estás aquí"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enableUserLocation();
        } else {
            Toast.makeText(getContext(), "Permiso de ubicación rechazado", Toast.LENGTH_SHORT).show();
        }
    }

    // ----------------------------
    //  SENSORES
    // ----------------------------
    @Override
    public void onResume() {
        super.onResume();

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        long now = System.currentTimeMillis();
        if (now - lastUpdate < 200) return; // Actualizar cada 200 ms
        lastUpdate = now;

        StringBuilder info = new StringBuilder();

        // 1️⃣ Acelerómetro (suavizado + redondeo)
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = Math.round(event.values[0] * 100f) / 100f;
            float y = Math.round(event.values[1] * 100f) / 100f;
            float z = Math.round(event.values[2] * 100f) / 100f;

            info.append("Acelerómetro:\nX=").append(x)
                    .append("  Y=").append(y)
                    .append("  Z=").append(z).append("\n");

            // Sacudida
            if (Math.abs(x) > 15 || Math.abs(y) > 15 || Math.abs(z) > 15) {
                Toast.makeText(getContext(), "Detecté sacudida 💦", Toast.LENGTH_SHORT).show();
            }
        }

        // 2️⃣ Luz
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float luz = Math.round(event.values[0] * 100f) / 100f;
            info.append("Luz: ").append(luz).append(" lx\n");

            if (luz < 5) gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            else gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        // 3️⃣ Proximidad
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float prox = Math.round(event.values[0] * 100f) / 100f;
            info.append("Proximidad: ").append(prox).append("\n");

            if (prox < proximitySensor.getMaximumRange()) {
                Toast.makeText(getContext(), "Proximidad activada 👋", Toast.LENGTH_SHORT).show();
            }
        }

        // 4️⃣ Orientación (suavizada)
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {

            float angle = Math.round(event.values[0]);

            info.append("Orientación: ").append(angle).append("°\n");

            // Solo girar si cambia mucho
            if (Math.abs(angle - lastAngle) > 10) {
                lastAngle = angle;

                if (gMap != null) {
                    gMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .bearing(angle)
                                    .zoom(17)
                                    .target(gMap.getCameraPosition().target)
                                    .build()
                    ));
                }
            }
        }

        // 5️⃣ Giroscopio
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float z = Math.round(event.values[2] * 100f) / 100f;
            info.append("Giroscopio Z: ").append(z).append("\n");

            if (z > 1.5f) {
                Toast.makeText(getContext(), "Giro rápido detectado 🔄", Toast.LENGTH_SHORT).show();
            }
        }

        // Mostrar texto
        sensorInfo.setText(info.toString());
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
