package com.github.cptzee.zmoney.Authentication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.cptzee.zmoney.Data.Credential;
import com.github.cptzee.zmoney.Data.Helper.CredentialHelper;
import com.github.cptzee.zmoney.Data.Manager.CredentialManager;
import com.github.cptzee.zmoney.R;
import com.github.cptzee.zmoney.Util.DataEncrypter;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment {
    public RegisterFragment() {
        super(R.layout.fragment_register);
    }

    private EditText usernameText, passwordText, confirmPasswordText;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameText = view.findViewById(R.id.register_username);
        passwordText = view.findViewById(R.id.register_password);
        confirmPasswordText = view.findViewById(R.id.register_confirm_password);

        view.findViewById(R.id.register_link).setOnClickListener(v ->
                getActivity().onBackPressed()
        );

        view.findViewById(R.id.register_back_img).setOnClickListener(v ->
                getActivity().onBackPressed()
        );

        view.findViewById(R.id.register_button).setOnClickListener(v -> {
            new checkFieldsTask().execute(usernameText.getText().toString());

        });
    }

    private void performRegistration(){
        Credential credential = new Credential();
        credential.setUsername(usernameText.getText().toString());
        credential.setEncryptedPassword(DataEncrypter.encryptPassword(passwordText.getText().toString()));

        CredentialHelper.getInstance(getContext()).insert(credential);

        //This is for static database
        //CredentialManager.getInstance().add(credential);

        Toast.makeText(getContext(), "Account successfully created!", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }

    class checkFieldsTask extends AsyncTask<String, Void, Boolean>{
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            boolean validFields = aBoolean;

            if(usernameText.getText().toString().isEmpty()){
                usernameText.setError("Username cannot be empty!");
                validFields = false;
            }

            if(passwordText.getText().toString().isEmpty()){
                passwordText.setError("Password cannot be empty!");
                validFields = false;
            }

            if(!passwordText.getText().toString().equals(confirmPasswordText.getText().toString())){
                confirmPasswordText.setError("Passwords does not match!");
                validFields = false;
            }

            if(validFields)
                performRegistration();

        }

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean validFields = true;
            for(Credential credential : CredentialHelper.getInstance(getContext()).get()){
                if(credential.getUsername().equalsIgnoreCase(strings[0]))
                    validFields = false;
            }
            return validFields;
        }
    }
}
