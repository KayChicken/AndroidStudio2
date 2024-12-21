package ru.mirea.kainov.mytourism.presentation.Registration;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import domain.models.Post;
import domain.repositories.AuthCallback;
import domain.usecases.RegistrationUseCase;
import ru.mirea.kainov.data.repository.AuthRepositoryImpl;
import ru.mirea.kainov.data.sharedprefs.UserPreferencesImpl;
import ru.mirea.kainov.mytourism.presentation.Authorization.Authorization;

public class RegistrationViewModel extends ViewModel {

    private final MutableLiveData<Boolean> authLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    AuthRepositoryImpl authRepository;
    RegistrationUseCase registrationUseCase;
    public RegistrationViewModel(Context context) {
        this.authRepository = new AuthRepositoryImpl(new UserPreferencesImpl(context));
        this.registrationUseCase = new RegistrationUseCase(authRepository);

    }

    public LiveData<Boolean> getAuthLiveData() {
        return  this.authLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return this.errorLiveData;
    }

    public void registration(String email, String password, String repeatPassword) {
        if (email.isEmpty() || password.isEmpty()) {
            errorLiveData.postValue("Некорректные логин или пароль");
            return;
        }
        if (!repeatPassword.equals(password)) {
            errorLiveData.postValue("Пароли не совпадают");
            return;
        }

        registrationUseCase.execute(email, password, new AuthCallback() {
            @Override
            public void onSuccess() {
                authLiveData.postValue(true);
            }

            @Override
            public void onError(String message) {
                errorLiveData.postValue(message);
            }
        });
    }


}
