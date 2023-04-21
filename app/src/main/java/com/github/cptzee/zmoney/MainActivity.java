package com.github.cptzee.zmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.github.cptzee.zmoney.Dashboard.DashboardFragment;
import com.github.cptzee.zmoney.Dashboard.HistoryFragment;
import com.github.cptzee.zmoney.Dashboard.InboxFragment;
import com.github.cptzee.zmoney.Dashboard.Profile.ProfileFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new DashboardFragment())
                .commit();

        BubbleNavigationLinearView bubbleNavigation = findViewById(R.id.bottom_nav);
        bubbleNavigation.setNavigationChangeListener((view, position) -> {
            switch (view.getId()){
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, new DashboardFragment())
                            .addToBackStack("Dashboard")
                            .commit();
                    break;
                case R.id.nav_inbox:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, new InboxFragment())
                            .addToBackStack("Inbox")
                            .commit();
                    break;
                case R.id.nav_transaction:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, new HistoryFragment())
                            .addToBackStack("Transaction")
                            .commit();
                    break;
                case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, new ProfileFragment())
                            .addToBackStack("Profile")
                            .commit();
                    break;
            }
        });
    }
}