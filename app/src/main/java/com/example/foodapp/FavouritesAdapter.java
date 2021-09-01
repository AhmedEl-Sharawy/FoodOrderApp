package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouritesAdapter extends ArrayAdapter<FavouritesInfo> {

    public FavouritesAdapter( Context context, List<FavouritesInfo> meals) {
        super(context, 0, meals);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {


        View currentListView = convertView;

        if(currentListView == null){
            currentListView = LayoutInflater.from(getContext()).inflate(R.layout.favourites, parent, false);
        }

        FavouritesInfo info = getItem(position);

        ImageView imageView = currentListView.findViewById(R.id.fav_image);
        TextView tvName =  currentListView.findViewById(R.id.fav_name);
        TextView tvPrice = currentListView.findViewById(R.id.fav_price);


        Picasso.get().load(info.getImgURL()).into(imageView);
        tvName.setText(info.getName());
        tvPrice.setText("Price: "+ info.getPrice() + "$");

        return currentListView;
    }
}
