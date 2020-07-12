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


import com.example.readybakingapp.Fragment.RecipeDetailFragment;
import com.example.readybakingapp.R;
import com.example.readybakingapp.RecipeDetailActivity;
import com.example.readybakingapp.RecipeStepsActivity;
import com.example.readybakingapp.model.Recipe;
import com.example.readybakingapp.model.Steps;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeViewHolder>{

    private  final RecipeStepsActivity recipeStepsActivity;
    private final ArrayList<Steps> mStepList;
    private final boolean mTwoPane;

    public RecipeStepAdapter(RecipeStepsActivity recipeStepsActivity, ArrayList<Steps> mStepList, boolean mTwoPane) {
        this.recipeStepsActivity =recipeStepsActivity;
        this.mStepList = mStepList;
        this.mTwoPane = mTwoPane;
    }

    @NonNull
    @Override
    public RecipeStepAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_steps_fragment, parent, false);

        return new RecipeViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull RecipeStepAdapter.RecipeViewHolder holder, final int position) {

        holder.shortSteps.setText(mStepList.get(position).getShortDescription());
        holder.longSteps.setText(mStepList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("RecipeStepsAdapter:", "get count="+String.valueOf(mStepList.size()));

                if (mTwoPane) {

                    Bundle arguments = new Bundle();
                    arguments.putParcelableArrayList("stepList", mStepList);
                    arguments.putInt("position",position);
                    RecipeDetailFragment fragment = new RecipeDetailFragment();
                    fragment.setArguments(arguments);
                    recipeStepsActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.recipe_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();

                    Intent intent = new Intent(context, RecipeDetailActivity.class);
                    intent.putParcelableArrayListExtra("stepList", mStepList);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStepList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder{
        TextView shortSteps;
        TextView longSteps;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            shortSteps = itemView.findViewById(R.id.shortDescTextView);
            longSteps = itemView.findViewById(R.id.longDescTextView);
        }
    }
}
