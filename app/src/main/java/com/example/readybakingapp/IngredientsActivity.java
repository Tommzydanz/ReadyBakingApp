package com.example.readybakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.readybakingapp.BakingAdapters.IngredientsAdapter;
import com.example.readybakingapp.Widget.BakingIngredientWidget;
import com.example.readybakingapp.model.Ingredients;
import com.example.readybakingapp.model.Recipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {


    public static String widgetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);


        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("RecipePojo");
        assert args != null;
        Recipe recipe = args.getParcelable("RecipePojo");

        ArrayList<Ingredients> ingredientsArray = recipe.getIngredients();
        ListView listView=findViewById(R.id.ingredients_listView);
        IngredientsAdapter ingredientsAdapter=new IngredientsAdapter(this, ingredientsArray);
        listView.setAdapter(ingredientsAdapter);

        FloatingActionButton setWidget = findViewById(R.id.addWidget);
        setWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                widgetText= "";
                for(int i=0;i<ingredientsArray.size();i++){
                    widgetText = widgetText + (i+1)+(".")+ingredientsArray.get(i).getIngredient()
                            +" --"+ingredientsArray.get(i).getQuantity()+ingredientsArray.get(i).getMeasure();
                    widgetText = widgetText + "\t\n ";
                }
                Intent intent = new Intent(IngredientsActivity.this, BakingIngredientWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(),BakingIngredientWidget.class));
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                Toast.makeText(IngredientsActivity.this,"Added Ingredients to Widget", Toast.LENGTH_SHORT).show();
                sendBroadcast(intent);
            }
        });
    }
}