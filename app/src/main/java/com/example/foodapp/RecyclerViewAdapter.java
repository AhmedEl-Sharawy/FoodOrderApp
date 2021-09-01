package com.example.foodapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private Context mContext;
    private List<Recipes> mData;




    public RecyclerViewAdapter(Context mContext, List<Recipes> mData){
        this.mContext = mContext;
        this.mData = mData;
    }







    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_recipe,viewGroup,false);
        return  new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, @SuppressLint("RecyclerView") final int i) {

        myHolder.recipeTitle.setText(mData.get(i).getRecipeName());
        myHolder.img_recipe_thumbnail.setImageResource(mData.get(i).getThumbnail());
        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,RecipeActivity.class);

                String [] Urls = new String[4];
                Urls[0] = "https://jsonware.com/json/eb0c34a77a42de7e4f475184e6008051.json";
                Urls[1] = "https://jsonware.com/json/2d8313c559fb3400c435cfef4cb8dbce.json";
                Urls[2] = "https://jsonware.com/json/3daddd2315f1e52d8710299b30f0c4cc.json";
                Urls[3] = "https://jsonware.com/json/472f521b9681783f4a6e684bec62c5ad.json";

                intent.putExtra("RecipeName",mData.get(i).getRecipeName());
                intent.putExtra("RecipeMethodTitle",mData.get(i).getRecipeMethodTitle());
                intent.putExtra("Thumbnail",mData.get(i).getThumbnail());
                intent.putExtra("Url",Urls[i]);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView recipeTitle;
        CardView cardView;
        ImageView img_recipe_thumbnail;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            recipeTitle = (TextView)itemView.findViewById(R.id.recipe_text);
            img_recipe_thumbnail = (ImageView)itemView.findViewById(R.id.recipe_img_id);
            cardView = (CardView)itemView.findViewById(R.id.cardview_id);


        }
    }
}

