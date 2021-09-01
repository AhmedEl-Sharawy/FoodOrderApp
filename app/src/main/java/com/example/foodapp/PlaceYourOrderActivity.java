package com.example.foodapp;

import static com.example.foodapp.AddToChart.Price;
import static com.example.foodapp.AddToChart.TotalPrice;
import static com.example.foodapp.AddToChart.chartList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class PlaceYourOrderActivity extends AppCompatActivity {

    ListView listView;
    TextView tvSubtotalAmount, tvTotalAmount;
    ArrayList<String> cart = new ArrayList<>();
    Button buttonPlaceYourOrder;
    private EditText inputName, inputCardNumber, inputCardExpiry, inputCardPin;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Price = 0.0;
        TotalPrice = 0.0;
        chartList.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_your_order);
        listView = findViewById(R.id.cartItemsListView);
        tvSubtotalAmount = findViewById(R.id.tvSubtotalAmount);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        buttonPlaceYourOrder = findViewById(R.id.buttonPlaceYourOrder);
        inputCardNumber = findViewById(R.id.inputCardNumber);
        inputCardExpiry = findViewById(R.id.inputCardExpiry);
        inputCardPin = findViewById(R.id.inputCardPin);
        inputName = findViewById(R.id.inputName);

        ArrayAdapter mealsAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.cart_item, chartList);
        listView.setAdapter(mealsAdapter);

        tvSubtotalAmount.setText(String.valueOf(Math.round(TotalPrice)) + "$");
        tvTotalAmount.setText(String.valueOf(Math.round(TotalPrice + 5)) + "$");

        buttonPlaceYourOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(inputName.getText().toString())) {
                    inputName.setError("Please enter name ");
                    return;
                } else if (TextUtils.isEmpty(inputCardNumber.getText().toString())) {
                    inputCardNumber.setError("Please enter card number ");
                    return;
                } else if (TextUtils.isEmpty(inputCardExpiry.getText().toString())) {
                    inputCardExpiry.setError("Please enter card expiry ");
                    return;
                } else if (TextUtils.isEmpty(inputCardPin.getText().toString())) {
                    inputCardPin.setError("Please enter card pin/cvv ");
                    return;
                } else {
                    Intent intent = new Intent(PlaceYourOrderActivity.this, OrderSucceessActivity.class);
                    intent.putExtra("Name", inputName.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }


}