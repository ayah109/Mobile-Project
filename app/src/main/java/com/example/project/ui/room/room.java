package com.example.project.ui.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.DoubleActivity;
import com.example.project.R;
import com.example.project.SingleActivity;
import com.example.project.SuiteActivity;

public class room extends Fragment {

    private Button btnSingle,btnDouble,btnSuite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room, container, false);

        Spinner spinnerDesiredPrice = rootView.findViewById(R.id.spinnerDesiredPrice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.desired_prices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesiredPrice.setAdapter(adapter);

        Spinner spinnerGuestCount = rootView.findViewById(R.id.spinnerGuestCount);
        ArrayAdapter<CharSequence> guestCountAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.guest_counts, android.R.layout.simple_spinner_item);
        guestCountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGuestCount.setAdapter(guestCountAdapter);


        btnSingle = rootView.findViewById(R.id.btnSingle);
        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the SingleActivity when btnSingle is clicked
                Intent intent = new Intent(getActivity(), SingleActivity.class);
                startActivity(intent);
            }
        });


    btnDouble = rootView.findViewById(R.id.btnDouble);
        btnDouble.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Launch the DoubleActivity when btnDouble is clicked
            Intent intent = new Intent(getActivity(), DoubleActivity.class);
            startActivity(intent);
        }
    });

    btnSuite = rootView.findViewById(R.id.btnSuite);
        btnSuite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Launch the SuiteActivity when btnSuite is clicked
            Intent intent = new Intent(getActivity(), SuiteActivity.class);
            startActivity(intent);
        }
    });

        return rootView;
}
}
