package com.example.readybakingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readybakingapp.Fragment.RecipeDetailFragment;

/**
 * An activity representing a single Recipe detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RecipeStepsActivity}.
 */
public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);


        // http://developer.android.com/guide/components/fragments.html
        //
     //   if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
         Bundle arguments = new Bundle();
         arguments.putParcelableArrayList("stepList", getIntent()
                  .getParcelableArrayListExtra("stepList"));
         arguments.putInt("position",
         getIntent().getIntExtra("position",0));
         RecipeDetailFragment fragment = new RecipeDetailFragment();
         fragment.setArguments(arguments);
         getSupportFragmentManager().beginTransaction()
                  .add(R.id.recipe_detail_container, fragment)
                  .commit();

         }

}