package com.example.project.ui.profile;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.DataBaseHelper;
import com.example.project.R;
import com.example.project.SharedPrefManager;
import com.example.project.SignIn;
import com.example.project.SignUp;

import java.util.ArrayList;

public class profile extends Fragment {

    private ProfileViewModel mViewModel;

    // Fields to be updated.
    private EditText firstNameEditText;
    private EditText secondNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Spinner spinner;
    private Button saveButton;

    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String favDestination;

    private ArrayAdapter<String> continentsSpinner;
    static SharedPrefManager sharedPrefManager;
    DataBaseHelper dataBaseHelper;

    public static profile newInstance() {
        return new profile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        dataBaseHelper = new DataBaseHelper(this.getContext(),"Hotels",null,1);
        firstNameEditText = (EditText) view.findViewById(R.id.firstNameEditText);
        secondNameEditText = (EditText) view.findViewById(R.id.secondNameEditText);
        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        passwordEditText = (EditText) view.findViewById(R.id.newPasswordEditText);
        saveButton = (Button) view.findViewById(R.id.saveButton);


        UpdateData();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);


        // Save Changes.
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data from Fields.
                firstName = firstNameEditText.getText().toString();
                secondName = secondNameEditText.getText().toString();
                if(!passwordEditText.getText().toString().equals(""))
                    password = passwordEditText.getText().toString();


                // Validate Data.
                if(validateInput()) {

                    // Update on database.
                    dataBaseHelper.updateUserInfo(email, firstName, secondName, password);

                    // Toast message.
                    Toast toast = Toast.makeText(view.getContext(), "Updated :)", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    protected void UpdateData(){
        // Get the email of the registered user from shared pref.
        email = getUserData(this.getContext());
        if(!email.equals("none")){
            Cursor userData = dataBaseHelper.getUserData(email);
            while (userData.moveToNext()){
                firstName = userData.getString(1);
                secondName = userData.getString(2);
                password = userData.getString(3);
            }
        }
        // Fill them into the UI
        emailEditText.setText(email);
        firstNameEditText.setText(firstName);
        secondNameEditText.setText(secondName);
        passwordEditText.setText("");

    }

    protected boolean validateInput(){

        if(!validateLength(firstName, 3, 20)) {
            Toast toast = Toast.makeText(this.getContext(), "First name should be 3-20 length.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if(!validateLength(secondName, 3, 20)) {
            Toast toast = Toast.makeText(this.getContext(), "Last name should be 3-20 length.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        if(!validatePassword(password)){
            Toast toast = Toast.makeText(this.getContext(), "Choose a stronger password.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }

    protected boolean validateLength(String str, int min, int max){
        if(str.length() < min)
            return false;
        if(str.length() > max)
            return false;
        return true;
    }

    protected static boolean validatePassword(String str){
        if(!str.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,15}$"))
            return false;
        return true;
    }

    public static String getUserData(Context context){
        sharedPrefManager =SharedPrefManager.getInstance(context);
        return sharedPrefManager.readString("registeredAccount", "none");
    }
}