package com.example.clubapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout containerRegistrations;
    private TextView tvEmptyState;
    private int registrationCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerRegistrations = findViewById(R.id.container_registrations);
        tvEmptyState = findViewById(R.id.tv_empty_state);

        Button eventButton1 = findViewById(R.id.btn_join_event_1);
        eventButton1.setOnClickListener(v ->
                onJoinEventClick(eventButton1, getString(R.string.event_name_1), getString(R.string.event_date_1))
        );

        Button eventButton2 = findViewById(R.id.btn_join_event_2);
        eventButton2.setOnClickListener(v ->
                onJoinEventClick(eventButton2, getString(R.string.event_name_2), getString(R.string.event_date_2))
        );

        findViewById(R.id.nav_activities).setOnClickListener(v ->
                Toast.makeText(this, R.string.nav_coming_soon, Toast.LENGTH_SHORT).show()
        );
        findViewById(R.id.nav_events).setOnClickListener(v ->
                Toast.makeText(this, R.string.nav_coming_soon, Toast.LENGTH_SHORT).show()
        );
        findViewById(R.id.nav_profile).setOnClickListener(v ->
                Toast.makeText(this, R.string.nav_coming_soon, Toast.LENGTH_SHORT).show()
        );
    }

    private void onJoinEventClick(Button button, String eventName, String eventDate) {
        addRegistrationDynamically(eventName, eventDate, button);

        button.setEnabled(false);
        button.setText(R.string.btn_joined);
        button.setTextColor(getResources().getColor(R.color.white));
        button.setBackgroundColor(getResources().getColor(R.color.accent_darker));
    }

    private void addRegistrationDynamically(String eventName, String eventDate, Button eventButton) {
        registrationCounter++;

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(32, 24, 32, 24);
        card.setBackgroundColor(getResources().getColor(R.color.white));

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 16);
        card.setLayoutParams(cardParams);

        TextView nameTextView = new TextView(this);
        nameTextView.setText(eventName);
        nameTextView.setTextColor(getResources().getColor(R.color.text_primary));
        nameTextView.setTextSize(16);
        nameTextView.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView dateTextView = new TextView(this);
        dateTextView.setText(eventDate);
        dateTextView.setTextColor(getResources().getColor(R.color.text_secondary));
        dateTextView.setTextSize(14);

        TextView statusTextView = new TextView(this);
        statusTextView.setText("Ticket ID: #PAS-" + (4800 + registrationCounter));
        statusTextView.setTextColor(getResources().getColor(R.color.accent_darker));
        statusTextView.setTextSize(12);

        Button cancelButton = new Button(this);
        cancelButton.setText(R.string.btn_cancel);
        final LinearLayout cardRef = card;
        cancelButton.setOnClickListener(v -> {
            containerRegistrations.removeView(cardRef);
            updateEmptyState();
            reactivateEventButton(eventButton);
        });

        card.addView(nameTextView);
        card.addView(dateTextView);
        card.addView(statusTextView);
        card.addView(cancelButton);

        containerRegistrations.addView(card);
        updateEmptyState();
    }

    private void reactivateEventButton(Button button) {
        button.setEnabled(true);
        button.setText(R.string.btn_join_event);
        button.setTextColor(getResources().getColor(R.color.white));
        button.setBackgroundColor(getResources().getColor(R.color.primary));
    }

    private void updateEmptyState() {
        if (containerRegistrations.getChildCount() == 0) {
            tvEmptyState.setVisibility(View.VISIBLE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
        }
    }
}