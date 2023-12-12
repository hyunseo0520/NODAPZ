package com.example.trip_project.hotel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trip_project.R;

import java.util.List;

public class topplacesAdapter extends RecyclerView.Adapter<topplacesAdapter.topplacesViewHolder> {

    Context context;
    List<topplacesData> topplacesDataList;

    public topplacesAdapter(Context context, List<topplacesData> topplacesDataList) {
        this.context = context;
        this.topplacesDataList = topplacesDataList;
    }

    @NonNull
    @Override
    public topplacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.topplaces_item, parent, false);

        // here we create a recyclerview row item layout file
        return new topplacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull topplacesViewHolder holder, int position) {

        holder.countryName.setText(topplacesDataList.get(position).getCountryName());
        holder.placeName.setText(topplacesDataList.get(position).getPlaceName());
        holder.price.setText(topplacesDataList.get(position).getPrice());
        holder.placeImage.setImageResource(topplacesDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return topplacesDataList.size();
    }

    public static final class topplacesViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, countryName, price;

        public topplacesViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);

        }
    }
}
