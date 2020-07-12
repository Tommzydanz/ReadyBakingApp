package com.example.readybakingapp.BakingAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.readybakingapp.R;
import com.example.readybakingapp.RecipeStepsActivity;
import com.example.readybakingapp.model.Recipe;


import java.util.ArrayList;


public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.MainViewHolder> {

    ArrayList<Recipe> recipeArray;
    Context context;

    public MainViewAdapter(Context context, ArrayList<Recipe> recipeArray) {
        this.recipeArray = recipeArray;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.main_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, final int position) {

        String name = recipeArray.get(position).getName();
        holder.item.setText(name);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, RecipeStepsActivity.class);
                Bundle args = new Bundle();
                args.putParcelable("RecipePojo", recipeArray.get(position));
                intent.putExtra("RecipePojo", args);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeArray.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{
        public TextView item;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            item= itemView.findViewById(R.id.recipeName);
        }
    }

}