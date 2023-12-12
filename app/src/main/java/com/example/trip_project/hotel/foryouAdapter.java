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

public class foryouAdapter extends RecyclerView.Adapter<foryouAdapter.foryouViewHolder> {

    Context context;
    List<foryouData> foryouDataList;

    public foryouAdapter(Context context, List<foryouData> foryouDataList) {
        this.context = context;
        this.foryouDataList = foryouDataList;
    }

    @NonNull
    @Override
    public foryouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.foryou_item, parent, false);

        // here we create a recyclerview row item layout file
        return new foryouViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull foryouViewHolder holder, int position) {

        holder.countryName.setText(foryouDataList.get(position).getCountryName());
        holder.placeName.setText(foryouDataList.get(position).getPlaceName());
        holder.price.setText(foryouDataList.get(position).getPrice());
        holder.placeImage.setImageResource(foryouDataList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 클릭한 아이템의 데이터를 가져오기
                foryouData clickedItem = foryouDataList.get(holder.getAdapterPosition());

                // Intent에 데이터를 추가하여 DetailsActivity로 전환
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("hotelName", clickedItem.getPlaceName());
                intent.putExtra("countryName", clickedItem.getCountryName());
                intent.putExtra("price", clickedItem.getPrice());
                intent.putExtra("imageResource", clickedItem.getImageUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foryouDataList.size();
    }

    public static final class foryouViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, countryName, price;

        public foryouViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);

        }
    }
}
