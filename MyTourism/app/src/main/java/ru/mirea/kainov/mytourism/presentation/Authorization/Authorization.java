package ru.mirea.kainov.mytourism.presentation.Authorization;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import domain.sharedpref.UserPreferences;
import ru.mirea.kainov.mytourism.R;
import ru.mirea.kainov.mytourism.presentation.Main.Main;
import ru.mirea.kainov.mytourism.presentation.MainActivity;
import ru.mirea.kainov.mytourism.presentation.Registration.Registration;

public class Authorization extends AppCompatActivity {

    private AuthorizationViewModel vm;
    private EditText loginEditText;
    private EditText passwordEditText;

    private TextView authRegistrationBtn;

    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);


        vm = new ViewModelProvider(this, new AuthorizationViewModelFactory(this)).get(AuthorizationViewModel.class);

        loginEditText = findViewById(R.id.loginTextField);
        passwordEditText = findViewById(R.id.passwordTextField);
        authRegistrationBtn = findViewById(R.id.authRegistrationBtn);
        Button loginButton = findViewById(R.id.loginButton);


        vm.getReturningUserEmail().observe(this, email -> {
            if (email != null && !email.isEmpty()) {
                Toast.makeText(Authorization.this, "С возвращением " + email, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Authorization.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        vm.getLoginSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(Authorization.this, "Авторизация успешна", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Authorization.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vm.getLoginError().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(Authorization.this, "Ошибка: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Authorization.this, "Введите логин и пароль", Toast.LENGTH_SHORT).show();
                    return;
                }

                vm.login(email,password);
            }
        });

        authRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Authorization.this, Registration.class);
                startActivity(intent);
            }
        });
    }
}