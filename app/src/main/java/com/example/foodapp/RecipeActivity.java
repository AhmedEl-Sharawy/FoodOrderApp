package com.example.foodapp;

import static com.example.foodapp.AddToChart.chartList;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class RecipeActivity extends AppCompatActivity {




    private TextView mRecipeName;
    private TextView mRecipeMethodTitle;
    Button check_out;

    private final int jsoncode = 1;
    private ListView listView;
    ArrayList<MealModel> mealModelArrayList;
    private MealAdapter mealAdapter;
    static String url = "";
    DataBaseController dbController;
    private static ProgressDialog mProgressDialog;
    Meal meal;
    boolean ch;



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        listView = findViewById(R.id.meal_list);
        check_out = findViewById(R.id.check_out);

        TextView tvRecipe = findViewById(R.id.text_recipe);


        mRecipeName = (TextView)findViewById(R.id.text_recipe);
        mRecipeMethodTitle = (TextView)findViewById(R.id.method);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("RecipeName");
        String MethodTitle = intent.getExtras().getString("RecipeMethodTitle");
        //url = intent.getExtras().getString("Url");
        mRecipeName.setText(Title);
        mRecipeMethodTitle.setText(MethodTitle);

        if (tvRecipe.getText().equals("KFC Chicken")){
            System.out.println("Hi");
            url = "https://jsonware.com/json/eb0c34a77a42de7e4f475184e6008051.json";
        }
        else if (tvRecipe.getText().equals("MacDonald's")){
            System.out.println("bi");
            url = "https://jsonware.com/json/2d8313c559fb3400c435cfef4cb8dbce.json";
        }
        else if (tvRecipe.getText().equals("Alex Burger")){
            System.out.println("zo");
            url = "https://jsonware.com/json/3daddd2315f1e52d8710299b30f0c4cc.json";
        }
        else if (tvRecipe.getText().equals("King Burger")){
            System.out.println("xx");
            url = "https://jsonware.com/json/472f521b9681783f4a6e684bec62c5ad.json";
        }

        fetchJSON();


        dbController = new DataBaseController(getApplicationContext());
        dbController.open();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RecipeActivity.this, AddToChart.class);
                intent.putExtra("name", mealModelArrayList.get(i).getName());
                intent.putExtra("price", mealModelArrayList.get(i).getPrice());
                intent.putExtra("urls", mealModelArrayList.get(i).getImgURL());


                   meal = dbController.selectMeal(mealModelArrayList.get(i).getName());
                   System.out.println(meal);
                   if (meal != null) {
                       if (meal.getName().trim().equals(mealModelArrayList.get(i).getName().trim())) {
                           intent.putExtra("fabImage", "hi");
                       } else {
                           intent.putExtra("fabImage", "bi");
                       }
                   }
                   else{
                       intent.putExtra("fabImage", "null");
                   }
                startActivity(intent);
            }
        });


    }

    @SuppressLint("StaticFieldLeak")
    private void fetchJSON(){

        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);
        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                HashMap<String, String> map = new HashMap<>();
                try {
                    HttpRequest req = new HttpRequest(url);
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response =e .getMessage();
                }
                return response;
            }
            protected void onPostExecute(String result) {
                //do something with response
                Log.d("newwwss",result);
                onTaskCompleted(result,jsoncode);
            }
        }.execute();
    }

    public void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
        switch (serviceCode) {
            case jsoncode:
                if (isSuccess(response)) {
                    removeSimpleProgressDialog();  //will remove progress dialog
                    mealModelArrayList = getInfo(response);
                    mealAdapter  = new MealAdapter(this,mealModelArrayList);
                    listView.setAdapter(mealAdapter);
                }else {
                    Toast.makeText(RecipeActivity.this, getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }

    public ArrayList<MealModel> getInfo(String response) {
        ArrayList<MealModel> mealModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {

                JSONArray dataArray = jsonObject.getJSONArray("menus");

                for (int i = 0; i < dataArray.length(); i++) {
                    MealModel mealsModel = new MealModel();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    mealsModel.setName(dataobj.getString("name"));
                    mealsModel.setPrice(dataobj.getString("price"));
                    mealsModel.setImgURL(dataobj.getString("url"));
                    mealModelArrayList.add(mealsModel);
                }
            }



            check_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (chartList.size() == 0){
                        Toast.makeText(getApplicationContext(), "Select Meal To Buy it", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Intent intent = new Intent(RecipeActivity.this, PlaceYourOrderActivity.class);
                    intent.putExtra("chartList", chartList);
                    Toast.makeText(getApplicationContext(), "To The Bill", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mealModelArrayList;
    }

    public boolean isSuccess(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("status").equals("true")) {
                return true;
            } else {
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("message");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
