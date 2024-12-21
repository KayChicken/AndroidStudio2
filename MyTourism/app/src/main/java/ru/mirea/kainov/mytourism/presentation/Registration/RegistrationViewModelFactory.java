package ru.mirea.kainov.mytourism.presentation.Registration;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.kainov.mytourism.presentation.Authorization.AuthorizationViewModel;

public class RegistrationViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;

    public RegistrationViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegistrationViewModel.class)) {
            return (T) new RegistrationViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
