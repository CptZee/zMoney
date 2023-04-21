package com.github.cptzee.zmoney.Authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.cptzee.zmoney.Data.Credential;
import com.github.cptzee.zmoney.Data.Helper.CredentialHelper;
import com.github.cptzee.zmoney.Data.Manager.CredentialManager;
import com.github.cptzee.zmoney.MainActivity;
import com.github.cptzee.zmoney.R;
import com.github.cptzee.zmoney.Util.DataEncrypter;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    private EditText usernameText, passwordText;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameText = view.findViewById(R.id.login_username);
        passwordText = view.findViewById(R.id.login_password);

        view.findViewById(R.id.login_link).setOnClickListener(v ->
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.auth_container, new RegisterFragment())
                        .addToBackStack("login")
                        .commit()
        );


        view.findViewById(R.id.login_button).setOnClickListener(v -> {
            if (fieldsAreValid())
                performLogin();
        });
    }


    private void performLogin() {
        for (Credential credential : CredentialHelper.getInstance(getContext()).get()) {
            if (isValidCredential(credential)) {
                SharedPreferences preferences = getActivity().getSharedPreferences("zMoney", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("credentialID", credential.getID());
                editor.commit();
                if (credential.getPin() == null)
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.auth_container, new PinSetupFragment())
                            .commit();
                else
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.auth_container, new PinFragment())
                            .commit();
                return;
            }
        }

        /**
        //TODO: Remove debug code
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
         */

        Toast.makeText(getContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
        usernameText.setError("Invalid username");
        passwordText.setError("Invalid password");
    }

    private boolean isValidCredential(Credential credential) {
        Log.i("CredentialParser", credential.getUsername() + " vs " + usernameText.getText().toString());
        Log.i("CredentialParser", credential.getEncryptedPassword() + " vs " + passwordText.getText().toString());
        return usernameText.getText().toString().equals(credential.getUsername())
                && DataEncrypter.encryptPassword(passwordText.getText().toString()).equals(credential.getEncryptedPassword());
    }

    private boolean fieldsAreValid() {
        boolean validFields = true;

        if (usernameText.getText().toString().isEmpty()) {
            usernameText.setError("Username cannot be empty!");
            validFields = false;
        }

        if (passwordText.getText().toString().isEmpty()) {
            passwordText.setError("Password cannot be empty!");
            validFields = false;
        }

        return validFields;
    }
}
