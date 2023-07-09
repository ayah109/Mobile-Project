package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.project.models.User;
import com.google.android.material.textfield.TextInputLayout;

public class SignIn extends AppCompatActivity {


    SharedPrefManager sharedPrefManager;


    // Information
    String emailAddressText;
    String passwordText;

    // Wrappers
    TextInputLayout emailAddressLayout;
    TextInputLayout passwordLayout;

    Button signUpBtn;
    Button signInBtn;

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sharedPrefManager =SharedPrefManager.getInstance(SignIn.this);

        emailAddressLayout = (TextInputLayout) findViewById(R.id.emailTxtLayout_SI);
        passwordLayout = (TextInputLayout) findViewById(R.id.passwordTxtLayout_SI);
        passwordLayout.setPasswordVisibilityToggleEnabled(false);
        signUpBtn = (Button) findViewById(R.id.signupBtn_SI);
        signInBtn = (Button) findViewById(R.id.signInBtn_SI) ;
        checkBox = (CheckBox) findViewById(R.id.rememberMeCHB);

        // Get data from shared preference
        String emailInSP = sharedPrefManager.readString("email","");
        String passInSP = sharedPrefManager.readString("password","");
        String checked = sharedPrefManager.readString("checkBox","false");

        // Check if the check box was check or not
        if (checked.equals("true")) {
            checkBox.setChecked(true);
            emailAddressLayout.getEditText().setText(emailInSP);
            passwordLayout.getEditText().setText(passInSP);
        }else
            checkBox.setChecked(false);

        // Validate user
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateInput() == false)
                    return;

                // Check if user exist
                DataBaseHelper dataBaseHelper = new DataBaseHelper(view.getContext(),"TravelGuide",null,1);
                boolean userExists = dataBaseHelper.checkusernamepassword(emailAddressText,passwordText);
                if(!userExists){
                    Toast toast = Toast.makeText(view.getContext(), "Check your email and password again.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if(checkBox.isChecked()){
                    sharedPrefManager.writeString("email",emailAddressText);
                    sharedPrefManager.writeString("password",passwordText);
                    sharedPrefManager.writeString("checkBox","true");
                } else {
                    sharedPrefManager.writeString("checkBox","false");
                }


                // Go to Home Page
                Intent intent = new
                        Intent(SignIn.this, Navigator.class);
                SignIn.this.startActivity(intent);
            }


        });

        // Go to Sign Up page.
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SignIn.this, SignUp.class);
                SignIn.this.startActivity(intent);
            }
        });
    }

    protected boolean validateInput(){

        emailAddressText = emailAddressLayout.getEditText().getText().toString();
        passwordText = passwordLayout.getEditText().getText().toString();

        if(!SignUp.validateEmail(emailAddressText)) {
            emailAddressLayout.setErrorEnabled(true);
            emailAddressLayout.setError("Invalid Email Address");
            return false;
        }else {
            emailAddressLayout.setErrorEnabled(false);
            emailAddressLayout.setError(null);
        }

        if(!SignUp.validatePassword(passwordText)){
            passwordLayout.setErrorEnabled(true);
            passwordLayout.setError("Password should be 8-15 length and must contain at\n" +
                    "least one number, one lowercase letter, and one uppercase letter.");
            return false;
        }else{
            passwordLayout.setErrorEnabled(false);
            passwordLayout.setError(null);
        }

        return true;
    }

}