package com.example.project;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.models.Service;
import com.example.project.ui.Cart.cart;
import com.example.project.ui.room_service.RoomServiceFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        // Get a reference for each element in xml code
        CircleImageView foodImage = findViewById(R.id.food_IMAGE);
        TextView foodName = findViewById(R.id.food_NAME);
        TextView foodIngredients = findViewById(R.id.food_INGREDIENTS);
        TextView foodPrice = findViewById(R.id.food_PRICE);
        Button buy = findViewById(R.id.button_buy);

        // Get the values passed from the RoomServiceFragment via the Intent
        Intent intent = getIntent();
        int foodID = intent.getIntExtra("foodID", 0);
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String ingredients = intent.getStringExtra("ingredients");

        // Set the values to the corresponding views
        foodImage.setImageResource(foodID);
        foodName.setText(name);
        foodIngredients.setText(ingredients);
        foodPrice.setText(price);

        // Set the title in the action bar
        getSupportActionBar().setTitle(name);

        // Set a listener to add a cart button
        buy.setOnClickListener(view -> {
            // Get the service details from the current activity
            String serviceName = name;
            // Remove the dollar sign from the price string
            String priceString = price.replaceAll("\\$", "");
            double serviceCost = Double.parseDouble(priceString);
            Service service = new Service(serviceName,serviceCost);
            cart.createNewService(service);

            // Create a toast message with the service details
            String message = "Service Name: " + serviceName + "\n" +
                    "Service Cost: $" + serviceCost;
            Toast.makeText(FoodDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back to the previous screen
        return true;
    }
}