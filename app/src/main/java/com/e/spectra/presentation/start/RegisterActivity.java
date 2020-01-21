package com.e.spectra.presentation.start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.e.spectra.R;
import com.e.spectra.util.ViewUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.text.TextUtils.isEmpty;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    @BindView(R.id.input_email)
    EditText mEmail;
    @BindView(R.id.input_password)
    EditText mPassword;
    @BindView(R.id.input_confirm_password)
     EditText mConfirmPassword;
    @BindView(R.id.btn_register)
     Button mRegister;
    @BindView(R.id.progressBar)
     ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        hideSoftKeyboard();

    }


    /**
     * Register a new email and password to Firebase Authentication
     *
     * @param email
     * @param password
     */
    public void registerNewEmail(final String email, String password) {

        ViewUtil.showProgressBar(mProgressBar);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: AuthState: " + FirebaseAuth.getInstance().getCurrentUser().getUid());
                            FirebaseAuth.getInstance().signOut();
                            //redirect the user to the login screen
                            redirectLoginScreen();
                        } else {
                            ViewUtil.toast(RegisterActivity.this, "Unable to Register");
                        }
                        ViewUtil.hideProgressBar(mProgressBar);

                    }
                });
    }

    /**
     * Returns True if the user's email contains '@tabian.ca'
     *
     * @param email
     * @return
     */
//    private boolean isValidDomain(String email) {
//        Log.d(TAG, "isValidDomain: verifying email has correct domain: " + email);
//        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
//        Log.d(TAG, "isValidDomain: users domain: " + domain);
//        return domain.equals(DOMAIN_NAME);
//    }

    /**
     * Redirects the user to the login screen
     */
    private void redirectLoginScreen() {
        Log.d(TAG, "redirectLoginScreen: redirecting to login screen.");
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Return true if @param 's1' matches @param 's2'
     *
     * @param s1
     * @param s2
     * @return
     */
    private boolean doStringsMatch(String s1, String s2) {
        return s1.equals(s2);
    }

    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @OnClick(R.id.btn_register)
    public void registerValidation() {
        Log.d(TAG, "onClick: attempting to register.");

        //check for null valued EditText fields
        if (!isEmpty(mEmail.getText().toString())
                && !isEmpty(mPassword.getText().toString())
                && !isEmpty(mConfirmPassword.getText().toString())) {

            //check if user has a company email address


            //check if passwords match
            if (doStringsMatch(mPassword.getText().toString(), mConfirmPassword.getText().toString())) {

                // InsertOnCloud.create(mPassword.getText().toString(), mEmail.getText().toString());
                //Initiate registration task
                registerNewEmail(mEmail.getText().toString(), mPassword.getText().toString());
            } else {
                Toast.makeText(RegisterActivity.this, "Passwords do not Match", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegisterActivity.this, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
        }

    }
}
