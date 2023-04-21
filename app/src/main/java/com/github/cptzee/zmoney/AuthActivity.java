package com.github.cptzee.zmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.cptzee.zmoney.Authentication.LoginFragment;
import com.github.cptzee.zmoney.Data.Helper.Database;
import com.github.cptzee.zmoney.Data.Manager.SessionManager;
import com.github.cptzee.zmoney.Slider.SplashFragment;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        new Database(this);

        if(SessionManager.getInstance().isFirstInstance()) {
            SessionManager.getInstance().setFirstInstance(false);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.auth_container, new SplashFragment())
                    .commit();
        }else
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.auth_container, new LoginFragment())
                    .commit();
    }
}