package com.example.readybakingapp.utils;

import android.util.Log;


import com.example.readybakingapp.model.Ingredients;
import com.example.readybakingapp.model.Recipe;
import com.example.readybakingapp.model.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private JsonUtils(){

    }
    private static final String LOG_ERROR_TAG = "JSONparsing:";

    public static ArrayList<Recipe> fetchRecipeFeaturesFromJson(String json){

            ArrayList<Recipe> recipePOJOArrayList=new ArrayList<>();
            if(json!=null) {
                try {
                    JSONArray baseArray = new JSONArray(json);

                    for (int i = 0; i < baseArray.length(); i++) {
                        JSONObject baseJsonObject = baseArray.getJSONObject(i);
                        JSONArray ingredientArray = baseJsonObject.getJSONArray("ingredients");
                        JSONArray stepsArray=baseJsonObject.getJSONArray("steps");

                        ArrayList<Ingredients> ingredients = getIngredientsFeatures(ingredientArray);
                        ArrayList<Steps> steps = fetchStepsFeatures(stepsArray);
                        Recipe recipe = getRecipeFeatures(baseJsonObject, ingredients, steps);
                        recipePOJOArrayList.add(recipe);

                   }

                } catch (JSONException e) {
                    Log.e(LOG_ERROR_TAG,"Error fetchFeaturesFromJson -"+e);
                }
            }
            else
            {
               Log.e(LOG_ERROR_TAG,"Json value passed is null");
            }

            return recipePOJOArrayList;
        }


    private static Recipe getRecipeFeatures(JSONObject jsonObject, ArrayList<Ingredients> ingredients, ArrayList<Steps> steps) {

        try {
            String id=jsonObject.getString("id");
            String name=jsonObject.getString("name");
            String serving=jsonObject.getString("servings");

            return new Recipe(Integer.parseInt(id),name,serving,steps,ingredients);

        } catch (JSONException e) {
            Log.e(LOG_ERROR_TAG,"Error fetchRecipeFeatures -"+e);
        }

        return null;
    }



    private static ArrayList<Ingredients> getIngredientsFeatures(JSONArray ingredientArray) {

            ArrayList<Ingredients> ingredientsArrayList=new ArrayList<>();
        ingredientsArrayList.clear();
       try{

                for(int b=0;b<ingredientArray.length();b++){
                    JSONObject ingredientObject=ingredientArray.getJSONObject(b);
                    String quantity=ingredientObject.getString("quantity");
                    String measure=ingredientObject.getString("measure");
                    String ingredient=ingredientObject.getString("ingredient");

                    Ingredients ingredientsPOJO=new Ingredients(quantity,measure,ingredient);
                    ingredientsArrayList.add(ingredientsPOJO);

                }


        } catch (JSONException e) {
            Log.e(LOG_ERROR_TAG,"Error GettingIngredientsFeatures -"+e);
        }

        return ingredientsArrayList;
    }

    private static ArrayList<Steps> fetchStepsFeatures(JSONArray stepsArray) {

        ArrayList<Steps> stepsArrayList=new ArrayList<>();

        try{

            for(int j=0;j<stepsArray.length();j++){
                JSONObject stepsObject=stepsArray.getJSONObject(j);
                String id=stepsObject.getString("id");
                String shortDescription=stepsObject.getString("shortDescription");
                String description=stepsObject.getString("description");
                String videoURL=stepsObject.getString("videoURL");

                Steps steps = new Steps (id,shortDescription,description,videoURL);
                stepsArrayList.add(steps);

            }
            return stepsArrayList;

        } catch (JSONException e) {
            Log.e(LOG_ERROR_TAG,"Error GettingRecipeFeatures -"+e);
        }
        return null;

    }




}
