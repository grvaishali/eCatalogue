package com.e.spectra.domain.model.services.impl;

import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;

import com.e.spectra.util.factory.NotificationFactory;

public class NotificationJobService extends JobService {
    public NotificationJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        NotificationFactory.getInstance(this).createChannel("222", "Notification from spectra", NotificationManager.IMPORTANCE_HIGH);
        NotificationFactory.getInstance(this).publishNotification(121, params.getExtras().getString("title"), params.getExtras().getString("body"), "222");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

}

