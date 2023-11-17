package com.example.howlerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    Button callLogin, login_btn;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;
    TextInputLayout name, username, email, password;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        callLogin = findViewById(R.id.login_Screen);
        image = findViewById(R.id.logo_Image);
        logo = findViewById(R.id.logo_name);
        slogan = findViewById(R.id.slogan_name);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");
                registerUser(view);
                Intent intent = new Intent(SignUp.this, Login.class);

            }
        });

        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logo, "logo_text");
                pairs[2] = new Pair<View, String>(slogan, "slogan_text");
                pairs[3] = new Pair<View, String>(username, "username_tran");
                pairs[4] = new Pair<View, String>(password, "password_tran");
                pairs[5] = new Pair<View, String>(login_btn, "button_tran");
                pairs[6] = new Pair<View, String>(callLogin, "login_signup_tran");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }

    private Boolean ValidateName() {
        String val = name.getEditText().getText().toString();

        if (val.isEmpty()) {
            name.setError("Field Cannot be empty");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean ValidateUserName() {
        String val = username.getEditText().getText().toString();

        if (val.isEmpty()) {
            username.setError("Field Cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            username.setError("Username too long");
            return false;
        }else {
            username.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean ValidateEmail() {
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field Cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;

        } else {
            email.setError(null);
            return true;
        }
    }

    private Boolean ValidatePassword() {
        String val = password.getEditText().getText().toString();


        if (val.isEmpty()) {
            password.setError("Field Cannot be empty");
            return false;
        } else {
            password.setError(null);
            name.setErrorEnabled(false);
            return true;
        }

    }

    public void registerUser(View view) {
        if (!ValidateName() | !ValidateUserName() | !ValidateEmail() | !ValidatePassword()) {
            return;
        }
        String names = name.getEditText().getText().toString();
        String usernames = username.getEditText().getText().toString();
        String emails = email.getEditText().getText().toString();
        String passwords = password.getEditText().getText().toString();


        UserHelperClass helperClass = new UserHelperClass(names, usernames, emails, passwords);
        reference.child(usernames).setValue(helperClass);

    }
}