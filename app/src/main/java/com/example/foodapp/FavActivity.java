package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FavActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }


    DataBaseController dbController;
    ListView listView;
    ArrayList<FavouritesInfo> meals = new ArrayList<FavouritesInfo>();

    String name1 , price1, image1;
    boolean checkName , checkPrice, checkImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        listView = findViewById(R.id.fav_list);

        dbController = new DataBaseController(getApplicationContext());
        dbController.open();
        getStudents();
        FavouritesAdapter adapter = new FavouritesAdapter(getApplicationContext(), meals);
        listView.setAdapter(adapter);
        System.out.println(name1 + "\n" + price1 + "\n" + image1 + "\n");
    }

    private void getStudents(){
        meals.clear();
        for(Meal meal : dbController.selectMeals()){
            name1 = meal.getName();
            price1 = meal.getPrice();
            image1 = meal.getImageUrl();
            FavouritesInfo info = new FavouritesInfo(name1, image1, price1);
            meals.add(info);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbController.close();
    }


}