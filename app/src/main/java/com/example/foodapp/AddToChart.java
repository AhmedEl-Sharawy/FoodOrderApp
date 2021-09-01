package com.example.foodapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AddToChart extends AppCompatActivity {


    ImageView image;
    TextView name, price;
    EditText num_of_meal;
    Button chart_btn, chart_btn2;
    static Double TotalPrice = 0.0;
    static Double Price = 0.0;
    DataBaseController dbController;
    FloatingActionButton fab;
    String priceDataBase;
    String urlDataBase;
    String nameDataBase;
    String fabImage = "";
    boolean check = true;


    static ArrayList <String> chartList = new ArrayList<>();


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbController.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_chart);

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        num_of_meal = findViewById(R.id.num_of_meal);
        chart_btn = findViewById(R.id.chart_btn);
        fab = findViewById(R.id.fab);

        if (getIntent() != null){
            name.setText("Meal Name: " + getIntent().getStringExtra("name"));
            price.setText("Price: " + getIntent().getStringExtra("price")+ " $");
            Picasso.get().load(getIntent().getStringExtra("urls")).into(image);
            Price = Double.parseDouble(getIntent().getStringExtra("price"));
            nameDataBase = getIntent().getStringExtra("name").trim();
            priceDataBase = getIntent().getStringExtra("price").trim();
            urlDataBase = getIntent().getStringExtra("urls").trim();
            fabImage = getIntent().getStringExtra("fabImage");
        }

            if (fabImage.equals("hi")){
                fab.setImageResource(R.drawable.ic_baseline_favorite_24);
            }else if (fabImage.equals("bi")){
                fab.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            }

        dbController = new DataBaseController(getApplicationContext());
        dbController.open();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check){
                    fab.setImageResource(R.drawable.ic_baseline_favorite_24);
                    check = false;
                    dbController.insertMeal(nameDataBase.trim(), priceDataBase, urlDataBase);
                    Toast.makeText(getApplicationContext(), "Meal Added To Fav List", Toast.LENGTH_LONG).show();
                }
                else{
                    fab.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    check = true;

                    dbController.deleteMeal(nameDataBase.trim());
                    Toast.makeText(getApplicationContext(), "Meal Removed From Fav List", Toast.LENGTH_LONG).show();
                }
            }
        });

        chart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(num_of_meal.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_LONG).show();
                    return;
                }
                    Price *= Double.parseDouble(num_of_meal.getText().toString());
                    TotalPrice += Price;
                    chartList.add(name.getText()+ "\n"  + price.getText() +"\n" + "Qty: " + num_of_meal.getText().toString());
                    Intent intent = new Intent(AddToChart.this, RecipeActivity.class);
                    intent.putExtra("RecipeName","");
                    Toast.makeText(getApplicationContext(), "Meal Added Successfully", Toast.LENGTH_LONG).show();
                    startActivity(intent);
            }
        });

    }
}