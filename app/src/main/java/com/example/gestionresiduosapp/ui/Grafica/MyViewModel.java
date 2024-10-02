package com.example.gestionresiduosapp.ui.Grafica;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Pair<String, Integer>>> _recyclingData = new MutableLiveData<>();
    private LiveData<ArrayList<Pair<String, Integer>>> recyclingData = _recyclingData;

    public MyViewModel() {
        ArrayList<Pair<String, Integer>> data = new ArrayList<>();
        data.add(new Pair<>("Semana 1", 56));
        data.add(new Pair<>("Semana 2", 70));
        data.add(new Pair<>("Semana 3", 62));
        data.add(new Pair<>("Semana 4", 34));
        data.add(new Pair<>("Semana 5", 78));
        data.add(new Pair<>("Semana 6", 55));
        _recyclingData.setValue(data);
    }

    public LiveData<ArrayList<Pair<String, Integer>>> getRecyclingData() {
        return recyclingData;
    }
}