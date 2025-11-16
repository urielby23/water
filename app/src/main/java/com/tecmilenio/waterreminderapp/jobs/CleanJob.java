package com.tecmilenio.waterreminderapp.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class CleanJob extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        new Thread(() -> {
            System.out.println("Limpiando datos viejos...");
            jobFinished(params, false);
        }).start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) { return false; }
}