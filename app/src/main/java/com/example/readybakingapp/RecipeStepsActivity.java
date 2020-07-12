package com.example.readybakingapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readybakingapp.BakingAdapters.RecipeStepAdapter;
import com.example.readybakingapp.Fragment.RecipeDetailFragment;
import com.example.readybakingapp.model.Ingredients;

import com.example.readybakingapp.model.Recipe;
import com.example.readybakingapp.model.Steps;

import java.util.ArrayList;

/**
 * An activity representing a list of Recipes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeStepsActivity extends AppCompatActivity {

    public static ArrayList<Ingredients> mIngredientsList;
    public static ArrayList<Steps> mStepList;


    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);


        if (findViewById(R.id.recipe_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        Bundle args = intent.getBundleExtra("RecipePojo");
        assert args != null;
        final Recipe recipe = args.getParcelable("RecipePojo");
        mStepList = recipe.getRecipeSteps();
        mIngredientsList = recipe.getIngredients();



        View recyclerView = findViewById(R.id.recipe_list_view);

        setupRecyclerView((RecyclerView) recyclerView);

        // ingredient onClickListener
        TextView RecipeIngredients = findViewById(R.id.ingredients);
        RecipeIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeStepsActivity.this, IngredientsActivity.class);
                Bundle args = new Bundle();
                args.putParcelable("RecipePojo", recipe);
                intent.putExtra("RecipePojo", args);


                startActivity(intent);
            }
        });

        //Set the first Instruction
        if (mTwoPane) {
            setFirstStep();

        }
    }

    private void closeOnError() {
        finish();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        (recyclerView).setAdapter(new RecipeStepAdapter(this, mStepList, mTwoPane));
    }

    private void setFirstStep() {
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList("stepList",mStepList);
        arguments.putInt("position", 0);
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(arguments);
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipe_detail_container, fragment)
                .commit();
    }

}