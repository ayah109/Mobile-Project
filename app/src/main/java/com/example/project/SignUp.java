package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.project.models.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    // Information
    String firstNameText;
    String lastNameText;
    String emailAddressText;
    String passwordText;
    String confirmPasswordText;

    // Wrappers
    TextInputLayout firstNameLayout;
    TextInputLayout lastNameLayout;
    TextInputLayout emailAddressLayout;
    TextInputLayout passwordLayout;
    TextInputLayout confirmPasswordLayout;

    Button signUpBtn;
    Button signInBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        firstNameLayout = (TextInputLayout) findViewById(R.id.firstNameTxtLayout_SU);
        lastNameLayout = (TextInputLayout) findViewById(R.id.lastNameTxtLayout_SU);
        emailAddressLayout = (TextInputLayout) findViewById(R.id.emailTxtLayout_SU);
        passwordLayout = (TextInputLayout) findViewById(R.id.passwordTxtLayout_SU);
        passwordLayout.setPasswordVisibilityToggleEnabled(false);
        confirmPasswordLayout = (TextInputLayout) findViewById(R.id.confirmPasswordTxtLayout_SU);
        confirmPasswordLayout.setPasswordVisibilityToggleEnabled(false);
        signUpBtn = (Button) findViewById(R.id.signupBtn_SU);
        signInBtn = (Button) findViewById(R.id.signInBtn_SU) ;



        // Validate user, then add to DB.
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(view.getContext(),"TravelGuide",null,1);


                if (validateInput() == false){
                    Toast toast = Toast.makeText(view.getContext(), "Invalid information.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }


                // Check if the user exists.
                if(dataBaseHelper.checkIFUserExists(emailAddressText)){
                    Toast toast = Toast.makeText(view.getContext(), "Account already exists.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                User user = new User();
                user.setEmail(emailAddressText);
                user.setFirstName(firstNameText);
                user.setLastName(lastNameText);
                user.setPassword(passwordText);


                // Save the user on database
                dataBaseHelper.insertUser(user);

                // Inform that he account has been created.
                Toast toast = Toast.makeText(view.getContext(), "Account has been created successfully.", Toast.LENGTH_SHORT);
                toast.show();

                // Go to Sign In page
                Intent intent = new
                        Intent(SignUp.this, SignIn.class);
                SignUp.this.startActivity(intent);
            }
        });

        // Go to Sign In page.
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SignUp.this, SignIn.class);
                SignUp.this.startActivity(intent);
            }
        });
    }

    protected boolean validateInput(){

        firstNameText = firstNameLayout.getEditText().getText().toString();
        lastNameText = lastNameLayout.getEditText().getText().toString();
        emailAddressText = emailAddressLayout.getEditText().getText().toString();
        passwordText = passwordLayout.getEditText().getText().toString();
        confirmPasswordText = confirmPasswordLayout.getEditText().getText().toString();

        if(!validateLength(firstNameText, 3, 20)) {
            firstNameLayout.setErrorEnabled(true);
            firstNameLayout.setError("First name should be 3-20 length.");
            return false;
        }else {
            firstNameLayout.setError(null);
            firstNameLayout.setErrorEnabled(false);
        }

        if(!validateLength(lastNameText, 3, 20)) {
            lastNameLayout.setErrorEnabled(true);
            lastNameLayout.setError("Last name should be 3-20 length.");
            return false;
        }else {
            lastNameLayout.setError(null);
            lastNameLayout.setErrorEnabled(false);
        }

        if(!validateEmail(emailAddressText)) {
            emailAddressLayout.setErrorEnabled(true);
            emailAddressLayout.setError("Invalid Email Address");
            return false;
        }else {
            emailAddressLayout.setErrorEnabled(false);
            emailAddressLayout.setError(null);
        }

        if(!validatePassword(passwordText)){
            passwordLayout.setErrorEnabled(true);
            passwordLayout.setError("Password should be 8-15 length and must contain at\n" +
                    "least one number, one lowercase letter, and one uppercase letter.");
            return false;
        }else{
            passwordLayout.setErrorEnabled(false);
            passwordLayout.setError(null);
        }

        if(!passwordText.equals(confirmPasswordText)){
            confirmPasswordLayout.setErrorEnabled(true);
            confirmPasswordLayout.setError("Not Matching!");
            return false;
        }else{
            confirmPasswordLayout.setErrorEnabled(false);
            confirmPasswordLayout.setError(null);
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

    protected static boolean validateEmail(String str){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return str.matches(regexPattern);
    }

    protected static boolean validatePassword(String str){
        if(!str.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,15}$"))
            return false;

        return true;
    }


}