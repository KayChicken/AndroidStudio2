package ru.mirea.kainov.data.repository;// Data/AuthRepositoryImpl.java
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import domain.repositories.AuthCallback;
import domain.repositories.AuthRepository;
import domain.sharedpref.UserPreferences;
import ru.mirea.kainov.data.sharedprefs.UserPreferencesImpl;
public class AuthRepositoryImpl implements AuthRepository {
    private final FirebaseAuth firebaseAuth;
    private final UserPreferences userPreferences;

    public AuthRepositoryImpl(UserPreferences userPreferences) {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userPreferences = userPreferences;

    }

    @Override
    public void signUp(String email, String password, AuthCallback callback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess();
                        userPreferences.saveEmail(email);
                    } else {
                        callback.onError(task.getException() != null ? task.getException().getMessage() : "Unknown error");
                    }
                });
    }

    @Override
    public void signIn(String email, String password, AuthCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess();
                        userPreferences.saveEmail(email);

                    } else {
                        callback.onError(task.getException() != null ? task.getException().getMessage() : "Unknown error");
                    }
                });
    }
}
