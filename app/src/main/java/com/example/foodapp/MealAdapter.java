package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MealAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MealModel> mealModelArrayList;

    public MealAdapter(Context context, ArrayList<MealModel> mealModelArrayList) {

        this.context = context;
        this.mealModelArrayList = mealModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return mealModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mealModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.meals, null, true);

            holder.iv = (ImageView) convertView.findViewById(R.id.meal_image);
            holder.tvname = (TextView) convertView.findViewById(R.id.meal_name);
            holder.tvprice = (TextView) convertView.findViewById(R.id.meal_price);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        Picasso.get().load(mealModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.tvname.setText(mealModelArrayList.get(position).getName());
        holder.tvprice.setText("Price: "+mealModelArrayList.get(position).getPrice() + "$");
        return convertView;

    }

    private class ViewHolder {
        protected TextView tvname, tvprice;
        protected ImageView iv;
    }

}
