package com.github.cptzee.zmoney.Authentication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.cptzee.zmoney.Data.Credential;
import com.github.cptzee.zmoney.Data.Helper.CredentialHelper;
import com.github.cptzee.zmoney.Data.Manager.CredentialManager;
import com.github.cptzee.zmoney.R;
import com.github.cptzee.zmoney.Util.PinUtil;

public class PinSetupFragment extends Fragment {
    public PinSetupFragment() {
        super(R.layout.fragment_pin);
    }

    private String pin;
    private ImageView indicatorOne, indicatorTwo, indicatorThree, indicatorFour;
    private Button backButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        indicatorOne = view.findViewById(R.id.pin_visual_1);
        indicatorTwo = view.findViewById(R.id.pin_visual_2);
        indicatorThree = view.findViewById(R.id.pin_visual_3);
        indicatorFour = view.findViewById(R.id.pin_visual_4);
        TextView indicator = view.findViewById(R.id.pin_text);
        backButton = view.findViewById(R.id.pin_back);
        pin = "";

        PinUtil.setFinished(false);

        indicator.setText("Set your new pin:");

        view.findViewById(R.id.pin_1).setOnClickListener(v -> {
            pin = pin + "1";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });
        view.findViewById(R.id.pin_2).setOnClickListener(v -> {
            pin = pin + "2";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });
        view.findViewById(R.id.pin_3).setOnClickListener(v -> {
            pin = pin + "3";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });
        view.findViewById(R.id.pin_4).setOnClickListener(v -> {
            pin = pin + "4";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });
        view.findViewById(R.id.pin_5).setOnClickListener(v -> {
            pin = pin + "5";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });
        view.findViewById(R.id.pin_6).setOnClickListener(v -> {
            pin = pin + "6";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });
        view.findViewById(R.id.pin_7).setOnClickListener(v -> {
            pin = pin + "7";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });
        view.findViewById(R.id.pin_8).setOnClickListener(v -> {
            pin = pin + "8";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });
        view.findViewById(R.id.pin_9).setOnClickListener(v -> {
            pin = pin + "9";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });
        view.findViewById(R.id.pin_0).setOnClickListener(v -> {
            pin = pin + "0";
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
            if(PinUtil.finished)
                setUpPin();
        });

        backButton.setOnClickListener(v -> {
            pin = pin.substring(0, pin.length()-1);
            PinUtil.updateUI(getContext(), pin, indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
        });
    }

    private void setUpPin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You entered the pin \"" + pin + "\" Do you want to proceed with this pin?")
                .setPositiveButton("Yes", (dialog, id) -> {
                    SharedPreferences preferences = getActivity().getSharedPreferences("zMoney", Context.MODE_PRIVATE);
                    Credential credential = CredentialHelper.getInstance(getContext()).get(preferences.getInt("credentialID", 0));

                    credential.setPin(pin);

                    CredentialHelper.getInstance(getContext()).update(credential);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.auth_container, new PinFragment())
                            .commit();
                })
                .setNegativeButton("No", (dialog, id) -> {
                    pin = "";
                    PinUtil.resetUI(getContext(), indicatorOne, indicatorTwo, indicatorThree, indicatorFour, backButton);
                })
                .create()
                .show();
    }
}