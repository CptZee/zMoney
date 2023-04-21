package com.github.cptzee.zmoney.Slider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.github.cptzee.zmoney.Authentication.LoginFragment;
import com.github.cptzee.zmoney.R;

public class SliderFragment extends Fragment {
    public SliderFragment() {
        super(R.layout.fragment_slider);
    }

    private ImageView second, third;
    private int index;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button back = view.findViewById(R.id.slider_back);
        Button next = view.findViewById(R.id.slider_next);
        ImageView image = view.findViewById(R.id.slider_image);
        second = view.findViewById(R.id.slider_visual_2);
        third = view.findViewById(R.id.slider_visual_3);
        index = 1;
        SharedPreferences preference = getActivity().getSharedPreferences("zMoney", Context.MODE_PRIVATE);

        next.setOnClickListener(v -> {
            switch (index) {
                case 1:
                    back.setVisibility(View.VISIBLE);
                    second.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.circle_filled, getContext().getTheme()));
                    image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.slider_2, getContext().getTheme()));
                    index = 2;
                    break;
                case 2:
                    Glide.with(this)
                            .load(R.raw.slider_3)
                            .into(image);
                    third.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.circle_filled, getContext().getTheme()));
                    index = 3;
                    next.setText("START");
                    break;
                case 3:
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.auth_container, new LoginFragment())
                            .commit();
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putBoolean("firstLaunch", false);
                    editor.commit();
                    break;
            }
        });

        back.setOnClickListener(v -> {
            switch (index) {
                case 2:
                    back.setVisibility(View.INVISIBLE);
                    second.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.circle_empty, getContext().getTheme()));
                    image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.slider_1, getContext().getTheme()));
                    index = 1;
                    break;
                case 3:
                    third.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.circle_empty, getContext().getTheme()));
                    image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.slider_2, getContext().getTheme()));
                    index = 2;
                    next.setText("NEXT");
                    break;
            }
        });

    }
}
