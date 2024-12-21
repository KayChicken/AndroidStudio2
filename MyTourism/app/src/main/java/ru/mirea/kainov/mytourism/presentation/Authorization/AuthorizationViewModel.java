package ru.mirea.kainov.mytourism.presentation.Authorization;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import domain.repositories.AuthCallback;
import domain.sharedpref.UserPreferences;
import domain.usecases.LoginUseCase;
import ru.mirea.kainov.data.repository.AuthRepositoryImpl;
import ru.mirea.kainov.data.sharedprefs.UserPreferencesImpl;

public class AuthorizationViewModel extends ViewModel {

    LoginUseCase loginUseCase;
    UserPreferences userPreferences;

    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> loginError = new MutableLiveData<>();
    private final MutableLiveData<String> returningUserEmail = new MutableLiveData<>();



    public AuthorizationViewModel(Context context) {
        userPreferences = new UserPreferencesImpl(context);
        loginUseCase = new LoginUseCase(new AuthRepositoryImpl(userPreferences));
        checkReturningUser();
    }

    public void checkReturningUser() {
        String email = userPreferences.getEmail();
        if (!email.isEmpty()) {
            returningUserEmail.postValue(email);
        }
    }

    public LiveData<String> getReturningUserEmail() {
        return returningUserEmail;
    }

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public LiveData<String> getLoginError() {
        return loginError;
    }

    public void login(String email, String password) {
        loginUseCase.execute(email, password, new AuthCallback() {
            @Override
            public void onSuccess() {
                loginSuccess.postValue(true);
            }

            @Override
            public void onError(String message) {
                loginError.postValue(message);
            }
        });
    }
}
