package com.e.spectra.ui.model;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.e.spectra.R;
import com.e.spectra.databinding.ActivityLoginBinding;
import com.e.spectra.services.impl.NotificationJobService;

import com.e.spectra.factory.NotificationFactory;
import com.e.spectra.model.AuthViewModel;
import com.e.spectra.ui.home.BrandsActivity;
import com.e.spectra.ui.navigation.NavigationActivity;
import com.e.spectra.ui.view.AuthListener;
import com.e.spectra.util.ViewUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class LoginActivity extends AppCompatActivity implements ComponentCallbacks2, AuthListener, HasActivityInjector {
    @BindView(R.id.loginProgressBar)
    ProgressBar mProgressBar;
    Intent intent;
    private JobScheduler mScheduler;
    AuthViewModel viewModel;
    private static final int JOB_ID = 0;
    @BindView(R.id.forgot_password)
    TextView resetPassword;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bind();
        ButterKnife.bind(this);
      //  crash();
        hideSoftKeyboard();

        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    }

//    private void crash() {
//        Button crashButton = new Button(this);
//        crashButton.setText("Crash!");
//        crashButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Crashlytics.getInstance().crash(); // Force a crash
//            }
//        });

//        addContentView(crashButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//    }

    private void bind() {
      ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        loginBinding.setViewModel(viewModel);
        viewModel.authListener = this;
        viewModel.setupFirebaseAuth();
    }


    @Override
    public void onStart() {
        super.onStart();
        viewModel.onStart();

        scheduleJob();

    }


    @Override
    public void onStop() {
        super.onStop();
        viewModel.onStop();

    }


    public void showNotification() {
        NotificationChannel androidChannel = NotificationFactory.getInstance(LoginActivity.this).createChannel(
                "123", "Notification", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationFactory.getInstance(LoginActivity.this).publishNotification(101, "Spectra", "Welcome", "123");
    }

    public void scheduleJob() {
        PersistableBundle jobParams = new PersistableBundle();
        jobParams.putString("title", "Hello");
        jobParams.putString("body", "Buffalo");
        ComponentName serviceName = new ComponentName(getPackageName(),
                NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(false)
                .setExtras(jobParams);
        JobInfo myJobInfo = builder.build();
        mScheduler.schedule(myJobInfo);
        Toast.makeText(this, R.string.job_scheduled, Toast.LENGTH_SHORT).show();

    }


    public void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onStarted() {
        ViewUtil.showProgressBar(mProgressBar);
    }

    @Override
    public void onSuccess(LiveData response) {

        response.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                ViewUtil.hideProgressBar(mProgressBar);
                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                startActivity(intent);
                ViewUtil.toast(LoginActivity.this, o.toString());
            }
        });

    }


    @Override
    public void onFailed(String message) {
        ViewUtil.hideProgressBar(mProgressBar);
        ViewUtil.toast(LoginActivity.this, message);
    }

    @Override
    public void onReset(LiveData response) {

        response.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {

                ViewUtil.toast(LoginActivity.this, o.toString());
            }
        });

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
