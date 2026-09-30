package com.example.clubapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout containerInscripciones;
    private int contadorInscripciones = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerInscripciones = findViewById(R.id.container_inscripciones);
    }

    public void onAnotarmeClick(View view) {
        agregarInscripcionDinamica("Torneo de Pádel", "Sábado 10 Octubre · 15:00");
    }

    private void agregarInscripcionDinamica(String nombreEvento, String fechaEvento) {
        contadorInscripciones++;

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

        TextView tvNombre = new TextView(this);
        tvNombre.setText(nombreEvento);
        tvNombre.setTextColor(getResources().getColor(R.color.text_primary));
        tvNombre.setTextSize(16);
        tvNombre.setTypeface(null, android.graphics.Typeface.BOLD);

        TextView tvFecha = new TextView(this);
        tvFecha.setText(fechaEvento);
        tvFecha.setTextColor(getResources().getColor(R.color.text_secondary));
        tvFecha.setTextSize(14);

        TextView tvEstado = new TextView(this);
        tvEstado.setText("Ticket ID: #PAS-" + (4800 + contadorInscripciones));
        tvEstado.setTextColor(getResources().getColor(R.color.accent));
        tvEstado.setTextSize(12);

        Button btnBaja = new Button(this);
        btnBaja.setText(R.string.btn_cancelar);
        final LinearLayout cardRef = card;
        btnBaja.setOnClickListener(v -> containerInscripciones.removeView(cardRef));

        card.addView(tvNombre);
        card.addView(tvFecha);
        card.addView(tvEstado);
        card.addView(btnBaja);

        containerInscripciones.addView(card);
    }
}