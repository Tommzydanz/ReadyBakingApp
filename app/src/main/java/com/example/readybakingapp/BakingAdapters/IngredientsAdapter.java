package com.example.readybakingapp.BakingAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.readybakingapp.R;
import com.example.readybakingapp.model.Ingredients;

import java.util.ArrayList;


public class IngredientsAdapter extends ArrayAdapter<Ingredients> {

        private ArrayList<Ingredients> mIngredients_List;


    public IngredientsAdapter(Context context, ArrayList<Ingredients> ingredientsArrayList) {
        super(context,0, ingredientsArrayList);
        mIngredients_List = ingredientsArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredients_list_item, parent, false);
        }
        TextView ingredientName = convertView.findViewById(R.id.ingredient_name_tv);
        TextView ingredientMeasure=convertView.findViewById(R.id.ingredients_measure_tv);
        TextView ingredientQuantity=convertView.findViewById(R.id.ingredient_quantity_tv);

        ingredientQuantity.setText(mIngredients_List.get(position).getQuantity());
        ingredientName.setText(mIngredients_List.get(position).getIngredient());
        ingredientMeasure.setText(mIngredients_List.get(position).getMeasure());

        return convertView;

    }

}
