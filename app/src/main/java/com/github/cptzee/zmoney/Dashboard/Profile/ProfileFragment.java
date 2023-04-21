package com.github.cptzee.zmoney.Dashboard.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.cptzee.zmoney.AuthActivity;
import com.github.cptzee.zmoney.Data.Credential;
import com.github.cptzee.zmoney.Data.Helper.CredentialHelper;
import com.github.cptzee.zmoney.R;

public class ProfileFragment extends Fragment {
    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView username = view.findViewById(R.id.profile_username);

        SharedPreferences preferences = getActivity().getSharedPreferences("zMoney", Context.MODE_PRIVATE);
        Credential credential = CredentialHelper.getInstance(getContext()).get(preferences.getInt("credentialID", 0));

        username.setText(credential.getUsername());

        view.findViewById(R.id.profile_logout).setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Are you sure you want to log out?")
                    .setPositiveButton("Yes", (dialog, id) -> {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("credentialID", 0);
                        editor.commit();
                        getActivity().finish();
                        startActivity(new Intent(getContext(), AuthActivity.class));
                        Toast.makeText(getContext(), "Logged out of the application, please log in again!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog, id) -> {

                    })
                    .create()
                    .show();
        });
    }
}