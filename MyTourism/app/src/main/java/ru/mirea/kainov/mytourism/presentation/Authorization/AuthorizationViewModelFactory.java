package ru.mirea.kainov.mytourism.presentation.Authorization;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import domain.repositories.AuthCallback;
import domain.sharedpref.UserPreferences;
import domain.usecases.LoginUseCase;
import ru.mirea.kainov.data.repository.AuthRepositoryImpl;
import ru.mirea.kainov.data.sharedprefs.UserPreferencesImpl;

public class AuthorizationViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;

    public AuthorizationViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AuthorizationViewModel.class)) {
            return (T) new AuthorizationViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
