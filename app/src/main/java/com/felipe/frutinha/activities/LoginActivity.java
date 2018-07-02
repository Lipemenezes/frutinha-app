package com.felipe.frutinha.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.felipe.frutinha.R;
import com.felipe.frutinha.api.APIUser;

public class LoginActivity extends AppCompatActivity {

    EditText user;
    EditText password;
    private ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViewComponents();
    }

    private void initViewComponents() {
        user = findViewById(R.id.editUser);
        password = findViewById(R.id.editPassword);
    }

    public void btnLogin (View v) {
        dialog = ProgressDialog.show(this, "", "Validando seu acesso...", true);
        APIUser.authenticate(user.getText().toString(), password.getText().toString(), this, dialog);
    }

    public void btnNewUser(View v) {
        Intent i = new Intent(this, NewUserActivity.class);
        startActivity(i);
    }

}
