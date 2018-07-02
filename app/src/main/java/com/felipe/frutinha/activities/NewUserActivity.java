package com.felipe.frutinha.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.felipe.frutinha.R;
import com.felipe.frutinha.api.APIUser;

public class NewUserActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    EditText email;
    EditText firstName;
    EditText lastName;
    private ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        initViewComponents();
    }

    private void initViewComponents() {
        login = findViewById(R.id.editLogin);
        password = findViewById(R.id.editPassword);
        email = findViewById(R.id.editEmail);
        firstName = findViewById(R.id.editFirstName);
        lastName = findViewById(R.id.editLastName);
    }

    public void btnRegister(View v) {
        dialog = ProgressDialog.show(this, "", "Cadastrando usuário...", true);
        APIUser.register(login.getText().toString(), password.getText().toString(),
                email.getText().toString(), firstName.getText().toString(),
                lastName.getText().toString(), this, dialog);
    }

}
