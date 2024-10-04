package com.example.gestionresiduosapp.ui.Mapa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionresiduosapp.R;
import com.example.gestionresiduosapp.ui.PantallaPrincipal;

public class PantallaMapa extends AppCompatActivity {
    //Declaración de las vistas
    private Button btnVolver;
    private ImageView imageViewMapa;
    private Button btnVerLista; // Changed variable name

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_mapa);

        //Inicialización de las vistas
        imageViewMapa = findViewById(R.id.imageViewMapa);
        btnVolver = findViewById(R.id.boton_volver);
        btnVerLista = findViewById(R.id.btnVerLista); // Updated findViewById

        //Configuración del botón ver lista de reciclaje
        btnVerLista.setOnClickListener(this::openListaReciclaje); // Updated button reference

        //Configuración del botón volver
        btnVolver.setOnClickListener(v -> {
            startActivity(new Intent(PantallaMapa.this, PantallaPrincipal.class));
        });
    }

    //Método para abrir la actividad de lista de reciclaje
    private void openListaReciclaje(View v) {
        Intent intent = new Intent(PantallaMapa.this, ListaReciclajeActivity.class);
        startActivity(intent);
    }
}

