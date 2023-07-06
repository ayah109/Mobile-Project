package com.example.project.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.R;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {

public FoodAdapter(Context context, ArrayList<Food> foodArrayList){
    super(context, R.layout.eat_list_item,foodArrayList);
}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.eat_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.foodImage = view.findViewById(R.id.food_picture);
            viewHolder.foodName = view.findViewById(R.id.food_name);
            viewHolder.foodPrice = view.findViewById(R.id.food_price);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Food food = getItem(position);
        if (food != null) {
            viewHolder.foodImage.setImageResource(food.photo);
            viewHolder.foodName.setText(food.name);
            viewHolder.foodPrice.setText(food.price);
        }

        return view;
    }

    private static class ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
    }
}
