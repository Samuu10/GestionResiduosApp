package com.example.gestionresiduosapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestionresiduosapp.R; // Make sure this is the correct import for your R file
import java.util.List;

public class RecyclingPointAdapter extends RecyclerView.Adapter<RecyclingPointAdapter.ViewHolder> {

    private List<RecyclingPoint> recyclingPoints;

    public RecyclingPointAdapter(List<RecyclingPoint> recyclingPoints) {
        this.recyclingPoints = recyclingPoints;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycling_point, parent, false); // Create item_recycling_point.xml
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclingPoint point = recyclingPoints.get(position);
        holder.nameTextView.setText(point.name);
        holder.addressTextView.setText(point.address);
    }

    @Override
    public int getItemCount() {
        return recyclingPoints.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView addressTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView); // Add these TextViews to item_recycling_point.xml
            addressTextView = itemView.findViewById(R.id.addressTextView);
        }
    }
}
