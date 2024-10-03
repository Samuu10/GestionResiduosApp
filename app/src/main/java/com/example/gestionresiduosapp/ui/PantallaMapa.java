package com.example.gestionresiduosapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionresiduosapp.R;

public class PantallaMapa extends AppCompatActivity {

    private Button btnVolver;
    private ImageView imageViewMapa;
    private Button btnVerLista; // Changed variable name

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_mapa);

        imageViewMapa = findViewById(R.id.imageViewMapa);
        btnVolver = findViewById(R.id.boton_volver);
        btnVerLista = findViewById(R.id.btnVerLista); // Updated findViewById

        btnVerLista.setOnClickListener(this::openListaReciclaje); // Updated button reference

        btnVolver.setOnClickListener(v -> {
            startActivity(new Intent(PantallaMapa.this, PantallaPrincipal.class));
        });
    }

    private void openListaReciclaje(View v) {
        Intent intent = new Intent(PantallaMapa.this, ListaReciclajeActivity.class);
        startActivity(intent);
    }
}

