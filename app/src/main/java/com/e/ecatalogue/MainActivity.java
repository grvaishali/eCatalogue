package com.e.ecatalogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import listener.EditedListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10;
    private EditText editTextOTP1, editTextOTP2, editTextOTP3, editTextOTP4, editTextOTP5, editTextOTP6;
    private TextView textOTP;
    private Button buttonSendOTP;
    private Button verify;
    private TextView textPhoneNumber;
    String verficationCode;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        textPhoneNumber = findViewById( R.id.text_phone_number );
        auth = FirebaseAuth.getInstance();
        editText1 = findViewById( R.id.et1 );

        editText2 = findViewById( R.id.et2 );
        editText3 = findViewById( R.id.et3 );
        editText4 = findViewById( R.id.et4 );
        editText5 = findViewById( R.id.et5 );
        editText6 = findViewById( R.id.et6 );
        editText7 = findViewById( R.id.et7 );
        editText8 = findViewById( R.id.et8 );
        editText9 = findViewById( R.id.et9 );
        editText10 = findViewById( R.id.et10 );
        EditedListener.listen( editText1, editText2 );
        EditedListener.listen( editText2, editText3 );
        EditedListener.listen( editText3, editText4 );
        EditedListener.listen( editText4, editText5 );
        EditedListener.listen( editText5, editText6 );
        EditedListener.listen( editText6, editText7 );
        EditedListener.listen( editText7, editText8 );
        EditedListener.listen( editText8, editText9 );
        EditedListener.listen( editText9, editText10 );
        textOTP = findViewById( R.id.text_otp );
        editTextOTP1 = findViewById( R.id.otp_et1 );
        editTextOTP2 = findViewById( R.id.otp_et2 );
        editTextOTP3 = findViewById( R.id.otp_et3 );
        editTextOTP4 = findViewById( R.id.otp_et4 );
        editTextOTP5 = findViewById( R.id.otp_et5 );
        editTextOTP6 = findViewById( R.id.otp_et6 );
        EditedListener.listen( editTextOTP1, editTextOTP2 );
        EditedListener.listen( editTextOTP2, editTextOTP3 );
        EditedListener.listen( editTextOTP3, editTextOTP4 );
        EditedListener.listen( editTextOTP4, editTextOTP5 );
        EditedListener.listen( editTextOTP5, editTextOTP6 );
        buttonSendOTP = findViewById( R.id.sendButton );
        verify = findViewById( R.id.verifyButton );

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText( getApplicationContext(), "Verification failed", Toast.LENGTH_SHORT ).show();

                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                } else if (e instanceof FirebaseTooManyRequestsException) {

                }


            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent( verificationId, token );
                verficationCode = verificationId;
                Toast.makeText( getApplicationContext(), "code sent to the number", Toast.LENGTH_SHORT ).show();


            }
        };
    }

    public void sendSms(View view) {
        String number = "+91"+editText1.getText().toString() + editText2.getText().toString() + editText3.getText().toString() +
                editText4.getText().toString() + editText5.getText().toString() + editText6.getText().toString() +
                editText7.getText().toString() + editText8.getText().toString()
                + editText9.getText().toString() + editText10.getText().toString();
        Log.i("Phone number",number);
        //String number = "+919999254929";
        PhoneAuthProvider.getInstance().verifyPhoneNumber( number, 60, TimeUnit.SECONDS, this, mCallbacks );

    }

    public void signInWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential( credential ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText( getApplicationContext(), "User Verified", Toast.LENGTH_SHORT ).show();
                }
                else{
                    String message = "Somthing is wrong, we will fix it soon...";

                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        message = "Invalid code entered...";
                    }
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                    snackbar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    snackbar.show();
                }
            }
        } );

    }

    public void verifyOTP(View view) {
        String inputCode = editTextOTP1.getText().toString() + editTextOTP2.getText().toString() + editTextOTP3.getText().toString() +
                editTextOTP4.getText().toString() + editTextOTP5.getText().toString() + editTextOTP6.getText().toString();
       // String inputCode = "123456";
        verifyPhoneNumber( verficationCode, inputCode );

    }

    public void verifyPhoneNumber(String verifyCode, String inputCode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential( verifyCode, inputCode );
        signInWithPhone( credential );
    }
}
