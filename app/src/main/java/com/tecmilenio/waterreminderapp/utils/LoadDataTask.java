package com.tecmilenio.waterreminderapp.utils;

import android.os.AsyncTask;

public class LoadDataTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        return "Datos cargados correctamente";
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(s);
    }
}