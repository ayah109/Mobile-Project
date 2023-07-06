package com.example.project.ui.room_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.FoodDetailsActivity;
import com.example.project.R;
import com.example.project.databinding.FragmentRoomServiceBinding;
import com.example.project.models.Food;
import com.example.project.models.FoodAdapter;

import java.util.ArrayList;

public class RoomServiceFragment extends Fragment {
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room_service, container, false);
        listView = rootView.findViewById(R.id.eatListView);

        int[] foodID = {R.drawable.steak,R.drawable.caesar_salad,R.drawable.sushi,R.drawable.pizza1,R.drawable.paella,R.drawable.falafel,R.drawable.chicken_tikka,R.drawable.pad_thai};
        String[] names = {"Steak", "Caesar salad", "Sushi", "Pizza", "Paella","Falafel", "Chicken tikka", "Pad Thai"};
        String[] price = {"$50","$15","$25","$15","$35","$5","$30","$10"};
        String[] ingredients = {getString(R.string.steak),getString(R.string.caesar_salad),getString(R.string.sushi),getString(R.string.pizza),getString(R.string.paella),getString(R.string.falafel),getString(R.string.chicken_tikka_masala),getString(R.string.pad_thai)};

        ArrayList<Food> foodArrayList = new ArrayList<>();

        for (int i = 0; i<foodID.length;i++){
            Food food = new Food(names[i],price[i],ingredients[i],foodID[i]);
            foodArrayList.add(food);
        }

        FoodAdapter foodAdapter = new FoodAdapter(getContext(),foodArrayList);
        listView.setAdapter(foodAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FoodDetailsActivity.class);
                intent.putExtra("foodID", foodID[position]);
                intent.putExtra("name", names[position]);
                intent.putExtra("price", price[position]);
                intent.putExtra("ingredients", ingredients[position]);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
