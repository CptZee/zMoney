package com.github.cptzee.zmoney.Slider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.github.cptzee.zmoney.Authentication.LoginFragment;
import com.github.cptzee.zmoney.Authentication.PinFragment;
import com.github.cptzee.zmoney.R;

public class SplashFragment extends Fragment {
    public SplashFragment() {
        super(R.layout.fragment_splash);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preference = getActivity().getSharedPreferences("zMoney", Context.MODE_PRIVATE);
        ImageView gifImageView = view.findViewById(R.id.loading);

        Glide.with(this)
                .load(R.raw.loading)
                .into(gifImageView);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (preference.getBoolean("firstLaunch", true)) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.auth_container, new SliderFragment())
                        .commit();
            } else {
                if (preference.getInt("credentialID", 0) == 0)
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.auth_container, new LoginFragment())
                            .commit();
                else
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.auth_container, new PinFragment())
                            .commit();
            }
        }, 5000);
    }
}
