package com.example.gestionresiduosapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionresiduosapp.R;

public class PantallaPrincipal extends AppCompatActivity {

    private Button btnCalendario;
    private Button btnMapa;
    private Button btnEstadisticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        btnCalendario = findViewById(R.id.btn_calendario);
        btnMapa = findViewById(R.id.btn_mapa);
        btnEstadisticas = findViewById(R.id.btn_estadisticas);

        btnCalendario.setOnClickListener(v -> {
            startActivity(new Intent(PantallaPrincipal.this, PantallaCalendario.class));
        });

        btnMapa.setOnClickListener(v -> {
            startActivity(new Intent(PantallaPrincipal.this, PantallaMapa.class));
        });

        btnEstadisticas.setOnClickListener(v -> {
            startActivity(new Intent(PantallaPrincipal.this, PantallaEstadisicas.class));
        });
    }
}