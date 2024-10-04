package com.example.gestionresiduosapp.ui.Estadisticas;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestionresiduosapp.R;
import com.example.gestionresiduosapp.ui.PantallaPrincipal;

public class PantallaEstadisicas extends AppCompatActivity {

    //Declaración de las vistas
    private MyViewModel viewModel;
    private Grafica chartView;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_estadisticas);

        //Inicialización de la gráfica
        chartView = new Grafica(this);
        ViewGroup layout = findViewById(R.id.grafica_vista); // Cambiar a ViewGroup o View
        layout.addView(chartView);

        //Inicialización del ViewModel
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //Observar los cambios en los datos de reciclaje
        viewModel.getRecyclingData().observe(this, data -> {
            if (data != null) {
                chartView.setRecyclingData(data);
            }
        });

        //Configuración del botón volver
        btnVolver = findViewById(R.id.boton_volver);
        btnVolver.setOnClickListener(v -> {
            startActivity(new Intent(PantallaEstadisicas.this, PantallaPrincipal.class));
        });
    }
}