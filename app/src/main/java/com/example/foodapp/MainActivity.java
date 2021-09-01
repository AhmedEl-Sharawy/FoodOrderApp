package com.example.foodapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView myrecyclerView;
    RecyclerViewAdapter myAdapter;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    List<Recipes> recipes1;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            Intent intent = new Intent(MainActivity.this, FavActivity.class);
            intent.putExtra("","");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        recipes1 = new ArrayList<>();
        recipes1.add(new Recipes("KFC Chicken"
                ,"Menu"
                , "\n",R.drawable.kfc1));

        recipes1.add(new Recipes("MacDonald's"
                ,"Menu"
                , "\n",R.drawable.mac));

        recipes1.add(new Recipes("Alex Burger"
                ,"Menu"
                , "\n",R.drawable.alexburger));

        recipes1.add(new Recipes("King Burger"
                ,"Menu"
                , "\n",R.drawable.king));

        myrecyclerView = (RecyclerView)findViewById(R.id.recyclerView_id);

        myAdapter = new RecyclerViewAdapter(this,recipes1);

        myrecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        myrecyclerView.setAdapter(myAdapter);



    }

}
