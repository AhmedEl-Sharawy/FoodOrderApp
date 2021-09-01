package com.example.foodapp;

import static com.example.foodapp.AddToChart.Price;
import static com.example.foodapp.AddToChart.TotalPrice;
import static com.example.foodapp.AddToChart.chartList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderSucceessActivity extends AppCompatActivity {

    TextView Order;
    Button doneBtn;
    @Override

    protected void onDestroy() {
        super.onDestroy();

    }
    public void onBackPressed() {
        //super.onBackPressed();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_succeess);
        Order = findViewById(R.id.Order);
        doneBtn = findViewById(R.id.buttonDone);

        if (getIntent() != null) {
            Order.setText("Hello "+ getIntent().getStringExtra("Name"));
        }

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSucceessActivity.this, MainActivity.class);
                intent.putExtra("pass","");
                startActivity(intent);
                chartList.clear();
                Price = 0.0;
                TotalPrice = 0.0;
            }
        });
    }
}