package com.example.project.ui;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.R;

public class ReservationActivity extends AppCompatActivity {

    private Spinner durationSpinner;
    private Button servicesButton;
    private EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        durationSpinner = findViewById(R.id.durationSpinner);
        servicesButton = findViewById(R.id.servicesButton);
        dateEditText = findViewById(R.id.dateEditText);

        servicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click to go to services page
                // Replace with your own logic
                Toast.makeText(ReservationActivity.this, "Go to services page", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
