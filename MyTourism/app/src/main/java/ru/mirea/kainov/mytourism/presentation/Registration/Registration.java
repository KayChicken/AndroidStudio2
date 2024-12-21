package ru.mirea.kainov.mytourism.presentation.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import domain.repositories.AuthCallback;
import domain.usecases.RegistrationUseCase;
import ru.mirea.kainov.data.repository.AuthRepositoryImpl;
import ru.mirea.kainov.data.sharedprefs.UserPreferencesImpl;
import ru.mirea.kainov.mytourism.R;
import ru.mirea.kainov.mytourism.presentation.Authorization.Authorization;
import ru.mirea.kainov.mytourism.presentation.Authorization.AuthorizationViewModel;
import ru.mirea.kainov.mytourism.presentation.Authorization.AuthorizationViewModelFactory;

public class Registration extends AppCompatActivity {
    private RegistrationViewModel vm;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private TextView returnLoginBtn;
    private Button registrationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        vm = new ViewModelProvider(this, new RegistrationViewModelFactory(this)).get(RegistrationViewModel.class);

        emailEditText = findViewById(R.id.registrationEmailField);
        passwordEditText = findViewById(R.id.registrationPasswordField);
        repeatPasswordEditText = findViewById(R.id.registrationRepeatPasswordField);
        registrationButton = findViewById(R.id.registrationButton);
        returnLoginBtn = findViewById(R.id.registrationToAuthBtn);

        vm.getAuthLiveData().observe(this, auth -> {
            if (auth) {
                Toast.makeText(Registration.this, "Поздравляем с успешной, регистрацией! Добро пожаловать!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registration.this, Authorization.class);
                startActivity(intent);
            }
        });


        vm.getErrorLiveData().observe(this, error -> {
            if (!error.isEmpty()) {
                Toast.makeText(Registration.this, "Ошибка авторизации: " + error, Toast.LENGTH_SHORT).show();
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String repeatPassword = repeatPasswordEditText.getText().toString().trim();

                vm.registration(email,password,repeatPassword);
            }
        });

        returnLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Authorization.class);
                startActivity(intent);
            }
        });
    }
}