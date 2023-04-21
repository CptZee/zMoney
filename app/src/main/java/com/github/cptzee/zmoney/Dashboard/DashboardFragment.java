package com.github.cptzee.zmoney.Dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.cptzee.zmoney.Data.Credential;
import com.github.cptzee.zmoney.Data.Helper.CredentialHelper;
import com.github.cptzee.zmoney.Data.Manager.CredentialManager;
import com.github.cptzee.zmoney.R;

public class DashboardFragment extends Fragment {
    public DashboardFragment() {
        super(R.layout.fragment_dashboard);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView username = view.findViewById(R.id.dashboard_username);
        TextView balance = view.findViewById(R.id.dashboard_balance);

        SharedPreferences preferences = getActivity().getSharedPreferences("zMoney", Context.MODE_PRIVATE);
        Credential credential = CredentialHelper.getInstance(getContext()).get(preferences.getInt("credentialID", 0));

        username.setText("Username: " + credential.getUsername());
        balance.setText("Password: ₱10,000.00");
    }
}
