package com.github.cptzee.zmoney.Dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.cptzee.zmoney.R;

public class HistoryFragment extends Fragment {
    public HistoryFragment() {
        super(R.layout.fragment_transactions);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView monthly = view.findViewById(R.id.history_monthly);
        TextView quarterly = view.findViewById(R.id.history_quarterly);
        TextView biannually = view.findViewById(R.id.history_biannually);
        TextView annually = view.findViewById(R.id.history_annually);

        monthly.setOnClickListener(v->{
            monthly.setTextColor(Color.parseColor("#CC4E5C"));
            quarterly.setTextColor(Color.parseColor("#424242"));
            biannually.setTextColor(Color.parseColor("#424242"));
            annually.setTextColor(Color.parseColor("#424242"));
        });

        quarterly.setOnClickListener(v->{
            quarterly.setTextColor(Color.parseColor("#CC4E5C"));
            monthly.setTextColor(Color.parseColor("#424242"));
            biannually.setTextColor(Color.parseColor("#424242"));
            annually.setTextColor(Color.parseColor("#424242"));
        });

        biannually.setOnClickListener(v->{
            biannually.setTextColor(Color.parseColor("#CC4E5C"));
            quarterly.setTextColor(Color.parseColor("#424242"));
            monthly.setTextColor(Color.parseColor("#424242"));
            annually.setTextColor(Color.parseColor("#424242"));
        });

        annually.setOnClickListener(v->{
            annually.setTextColor(Color.parseColor("#CC4E5C"));
            quarterly.setTextColor(Color.parseColor("#424242"));
            biannually.setTextColor(Color.parseColor("#424242"));
            monthly.setTextColor(Color.parseColor("#424242"));
        });
    }
}
