package com.tecmilenio.waterreminderapp.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "consumo_agua")
public class WaterIntake {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String fecha;
    public String hora;
    public int cantidadMl;
    public String tipoRecipiente;

    public WaterIntake(String fecha, String hora, int cantidadMl, String tipoRecipiente) {
        this.fecha = fecha;
        this.hora = hora;
        this.cantidadMl = cantidadMl;
        this.tipoRecipiente = tipoRecipiente;
    }
}