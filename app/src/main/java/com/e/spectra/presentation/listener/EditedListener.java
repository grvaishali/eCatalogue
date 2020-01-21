package com.e.spectra.presentation.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class EditedListener {


    EditedListener() {
        EditedListener object = new EditedListener();

    }

    public static void listen(EditText et1, final EditText et2) {
        et1.addTextChangedListener( new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (s.length() == 1) {
                    et2.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        } );


    }

    public static void autoTextListen(AutoCompleteTextView view, String text) {

        view.addTextChangedListener( new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (s.length() == 2) {

                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        } );


    }

}
