package com.example.gestionresiduosapp.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestionresiduosapp.R;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

public class ListaReciclajeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reciclaje); // Create this layout file

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d("ListaReciclajeActivity", "onCreate called");

        List<RecyclingPoint> recyclingPoints = new ArrayList<>();
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Fijo Hortaleza", "Calle Tomas Redondo,8"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Móvil", "Calle Sofía,207, San Blas-Canillejas"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Fijo del Ayuntamiento", "Calle de Luis I,40, Vallecas"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Fijo del Distrito de Usera", "Calle Cristo de la Victoria,245, Usera"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Fijo Distrito Latina", "Calle del Concejal Francisco José Jimenénez Martín, 5, Latina"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Fijo Distrito Moratalaz" , "Calle del Arroyo de la Media Legua, 72, Moratañaz"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Movil del Ayuntamiento de Madrid", "Avenida de Filipinas, 32, Chamberi"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Movil Chamberi" , "Pl. de San Juan de la Cruz, 3, Chamberi"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio de Proximidad", "Calle de Bravo Murillo, 378, Tetuán"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Fijo Distrito Chamartin", "Avenida de Alfonso XIII, 128, Chamartin"));
        recyclingPoints.add(new RecyclingPoint("Punto de recogida y reciclaje" , "Plaza de Alonso Martínez, Centro"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio Movil", "Calle de Ibiza, 60, Retiro"));
        recyclingPoints.add(new RecyclingPoint("Punto Limpio", "Calle de Gabriel García Márquez, 24, Alcobendas"));
        recyclingPoints.add(new RecyclingPoint("Punto recogida reciclaje aceite y ropa" , "Calle de San Román del Valle, 250, San Blas-Canillejas"));
        // ... add the rest of your recycling points

        RecyclerView recyclerView = findViewById(R.id.recyclerView); // Add RecyclerView to activity_lista_reciclaje.xml
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclingPointAdapter adapter = new RecyclingPointAdapter(recyclingPoints);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
