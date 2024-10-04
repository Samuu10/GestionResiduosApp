package com.example.gestionresiduosapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestionresiduosapp.R;
import com.example.gestionresiduosapp.ui.Calendario.PantallaCalendario;
import com.example.gestionresiduosapp.ui.Estadisticas.PantallaEstadisicas;
import com.example.gestionresiduosapp.ui.Mapa.PantallaMapa;

public class PantallaPrincipal extends AppCompatActivity {

    //Declaración de las vistas
    private Button btnCalendario;
    private Button btnMapa;
    private Button btnEstadisticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        //Inicialización de las vistas
        btnCalendario = findViewById(R.id.btn_calendario);
        btnMapa = findViewById(R.id.btn_mapa);
        btnEstadisticas = findViewById(R.id.btn_estadisticas);


        //Configuración del botón calendario
        btnCalendario.setOnClickListener(v -> {
            startActivity(new Intent(PantallaPrincipal.this, PantallaCalendario.class));
        });

        //Configuración del botón mapa
        btnMapa.setOnClickListener(v -> {
            startActivity(new Intent(PantallaPrincipal.this, PantallaMapa.class));
        });

        //Configuración del botón estadísticas
        btnEstadisticas.setOnClickListener(v -> {
            startActivity(new Intent(PantallaPrincipal.this, PantallaEstadisicas.class));
        });
    }
}