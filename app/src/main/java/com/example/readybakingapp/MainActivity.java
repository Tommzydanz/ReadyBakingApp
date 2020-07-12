package com.example.readybakingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;


import com.example.readybakingapp.BakingAdapters.MainViewAdapter;
import com.example.readybakingapp.IdlingResource.MyIdlingResource;
import com.example.readybakingapp.model.Recipe;
import com.example.readybakingapp.utils.JsonUtils;
import com.example.readybakingapp.utils.NetworkUtils;

import java.util.ArrayList;

import id.ionbit.ionalert.IonAlert;

public class MainActivity extends AppCompatActivity {


    ArrayList<Recipe> recipeArrayList;

    ProgressBar mProgress;
    RecyclerView mRecyclerView;
    MainViewAdapter mainViewAdapter;
    BakingAsyncTask mainViewAsyncTask;

    @Nullable
    private static MyIdlingResource mIdlingResource=new MyIdlingResource();

    @VisibleForTesting
    @NonNull
    public static IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new MyIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.recipe_progress);


        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainViewAdapter = new MainViewAdapter(this, recipeArrayList);
        mRecyclerView.setAdapter(mainViewAdapter);

        mainViewAsyncTask = new BakingAsyncTask();

        String NETWORK_STATUS = getNetworkStatus();
        if (NETWORK_STATUS.equals("CONNECTED")) {
            mainViewAsyncTask.execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        }
        else{
            mProgress.setVisibility(View.INVISIBLE);
            IonAlert ionAlert = new IonAlert(this, IonAlert.ERROR_TYPE);
            ionAlert.setTitleText("Oops...");
            ionAlert.setContentText("Check your network and Try again!");
            ionAlert.show();
        }

    }

    private String getNetworkStatus() {


        ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NI = CM.getActiveNetworkInfo();
        if (NI != null && NI.isConnected()) {

            return "CONNECTED";

        } else {
            return "NOT_CONNECTED";
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class BakingAsyncTask extends AsyncTask<String, Void, String> {
        @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgress = (ProgressBar) findViewById(R.id.recipe_progress);

                mProgress.setVisibility(View.VISIBLE);
                mIdlingResource.setIdleState(false);
            }

            //Background Task
            @Override
            protected String doInBackground(String... urls) {

                String json = NetworkUtils.fetchJsonString(urls[0]);
                return json;
            }


            //Execute
            @Override
            protected void onPostExecute(String json) {
                ArrayList<Recipe> recipeArrayList = JsonUtils.fetchRecipeFeaturesFromJson(json);
                for (int k = 0; k < recipeArrayList.size(); k++) {
                    for (int m = 0; m < recipeArrayList.get(k).getIngredients().size(); m++) {

                    }
                }
                mainViewAdapter = new MainViewAdapter(MainActivity.this, recipeArrayList);
                mRecyclerView.setAdapter(mainViewAdapter);
                mProgress = (ProgressBar) findViewById(R.id.recipe_progress);

                mProgress.setVisibility(View.GONE);

                super.onPostExecute(json);
                mIdlingResource.setIdleState(true);

            }
        }
}