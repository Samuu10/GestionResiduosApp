package com.example.gestionresiduosapp.ui.Estadisticas;

import android.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    //Variables
    private MutableLiveData<ArrayList<Pair<String, Integer>>> _recyclingData = new MutableLiveData<>();
    private LiveData<ArrayList<Pair<String, Integer>>> recyclingData = _recyclingData;

    //Constructor que inicializa los datos de reciclaje
    public MyViewModel() {
        ArrayList<Pair<String, Integer>> data = new ArrayList<>();
        data.add(new Pair<>("Orgánico", 52));
        data.add(new Pair<>("Papel", 60));
        data.add(new Pair<>("Vidrio", 26));
        data.add(new Pair<>("Plástico", 47));
        data.add(new Pair<>("Otros", 18));
        _recyclingData.setValue(data);
    }

    //Método para obtener los datos de reciclaje
    public LiveData<ArrayList<Pair<String, Integer>>> getRecyclingData() {
        return recyclingData;
    }
}