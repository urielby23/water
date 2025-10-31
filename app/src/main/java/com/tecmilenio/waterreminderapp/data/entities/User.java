package com.tecmilenio.waterreminderapp.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuario")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nombre;
    public int edad;
    public double peso;
    public String genero;
    public int metaDiariaMl;

    public User(String nombre, int edad, double peso, String genero, int metaDiariaMl) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.genero = genero;
        this.metaDiariaMl = metaDiariaMl;
    }
}
