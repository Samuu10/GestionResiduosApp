package com.example.gestionresiduosapp.ui.Mapa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestionresiduosapp.R; // Make sure this is the correct import for your R file
import java.util.List;

//Clase para representar un adaptador de puntos de reciclaje
public class RecyclingPointAdapter extends RecyclerView.Adapter<RecyclingPointAdapter.ViewHolder> {

    //Variables
    private List<RecyclingPoint> recyclingPoints;

    //Constructor
    public RecyclingPointAdapter(List<RecyclingPoint> recyclingPoints) {
        this.recyclingPoints = recyclingPoints;
    }

    //Método para crear un ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycling_point, parent, false); // Create item_recycling_point.xml
        return new ViewHolder(view);
    }

    //Método para vincular los datos a un ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclingPoint point = recyclingPoints.get(position);
        holder.nameTextView.setText(point.name);
        holder.addressTextView.setText(point.address);
    }

    //Método para obtener el número de elementos
    @Override
    public int getItemCount() {
        return recyclingPoints.size();
    }

    //Clase estática para representar un ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Variables
        public TextView nameTextView;
        public TextView addressTextView;

        //Constructor
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView); // Add these TextViews to item_recycling_point.xml
            addressTextView = itemView.findViewById(R.id.addressTextView);
        }
    }
}
