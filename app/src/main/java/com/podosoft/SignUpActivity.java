package com.podosoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.podosoft.Request.RegisterResponse;
import com.podosoft.Request.RegisterResponseListener;
import com.podosoft.Request.RequestManager;
import com.podosoft.Request.User;

public class SignUpActivity extends AppCompatActivity {

    TextView textView_go_to_sign_in;
    EditText editText_first_name, editText_last_name, editText_email, editText_password, editText_confirm;
    Button btn_sign_up;

    RequestManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // findView By Ids
        textView_go_to_sign_in = findViewById(R.id.textView_go_to_sign_in);
        editText_first_name = findViewById(R.id.editText_first_name);
        editText_last_name = findViewById(R.id.editText_last_name);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        editText_confirm = findViewById(R.id.editText_confirm);

        btn_sign_up = findViewById(R.id.btn_sign_up);


        /// OnClick Views
        textView_go_to_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignIn();
            }
        });

        // OnClick Button
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(processFormFields()){
                    User user = new User(editText_first_name.getText().toString(),
                            editText_last_name.getText().toString(),
                            editText_email.getText().toString(),
                            editText_password.getText().toString()
                    );
                    manager = new RequestManager(SignUpActivity.this);
                    manager.register(registerResponseListener, user);
                }

            }
        });
    }


    //Go To Sign In
    public void goToSignIn(){
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
    
    // process fields
    public boolean processFormFields(){
        if(!validateFirstName() || !validateEmail() || !validatePassword() || !validateConfirm()){
            return false;
        }
        else{
            return true;
        }
    }

    // Validations
    public boolean validateFirstName(){
        String firstName = editText_first_name.getText().toString();

        if(firstName.isEmpty()){
            editText_first_name.setError("First name cannot be empty");
            return false;
        }
        return true;
    }

    public boolean validateEmail(){
        String email = editText_email.getText().toString();
        if(email.isEmpty()){
            editText_email.setError("Email cannot be empty");
            return false;
        }
        return true;
    }

    public boolean validatePassword(){
        String password = editText_password.getText().toString();

        if(password.isEmpty()){
            editText_password.setError("Password cannot be empty");
            return false;
        }
        return true;
    }

    public boolean validateConfirm(){
        String confirm = editText_confirm.getText().toString();
        String password = editText_password.getText().toString();
        if(confirm.isEmpty()){
            editText_confirm.setError("Password Confirm cannot be empty");
            return false;
        }else if(!confirm.equals(password)){
            editText_confirm.setError("Confirm should match password");
            return false;
        }
        return true;
    }

    // Listener
    private final RegisterResponseListener registerResponseListener = new RegisterResponseListener() {
        @Override
        public void didRegister(RegisterResponse response, String message) {
            Toast.makeText(SignUpActivity.this, response.getStatus() + " :: " +response.getBody(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void didError(String message) {
            Toast.makeText(SignUpActivity.this, "Error : " + message, Toast.LENGTH_LONG).show();
        }
    };
}