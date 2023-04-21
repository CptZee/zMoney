package com.github.cptzee.zmoney.Util;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import com.github.cptzee.zmoney.R;

public class PinUtil {

    public static boolean finished = false;
    public static void updateUI(Context context, String pin, ImageView one, ImageView two, ImageView three, ImageView four, Button backButton){
        switch (pin.length()){
            case 0:
                one.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_empty, context.getTheme()));
                backButton.setVisibility(View.INVISIBLE);
                break;
            case 1:
                one.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_filled, context.getTheme()));
                two.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_empty, context.getTheme()));
                backButton.setVisibility(View.VISIBLE);
                break;
            case 2:
                two.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_filled, context.getTheme()));
                three.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_empty, context.getTheme()));
                break;
            case 3:
                three.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_filled, context.getTheme()));
                break;
            case 4:
                four.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_filled, context.getTheme()));
                finished = true;
                break;
        }
    }

    public static void resetUI(Context context, ImageView one, ImageView two, ImageView three, ImageView four, Button backButton){
        one.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_empty, context.getTheme()));
        two.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_empty, context.getTheme()));
        three.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_empty, context.getTheme()));
        four.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.circle_empty, context.getTheme()));
        backButton.setVisibility(View.INVISIBLE);
        finished = false;
    }

    public static void setFinished(boolean finished) {
        PinUtil.finished = finished;
    }
}
