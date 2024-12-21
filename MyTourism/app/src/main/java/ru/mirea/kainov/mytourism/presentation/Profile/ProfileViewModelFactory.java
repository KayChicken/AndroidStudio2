package ru.mirea.kainov.mytourism.presentation.Profile;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import domain.repositories.PostRepository;
import domain.sharedpref.UserPreferences;
import ru.mirea.kainov.data.repository.PostRepositoryImpl;
import ru.mirea.kainov.data.sharedprefs.UserPreferencesImpl;
import ru.mirea.kainov.mytourism.R;
import ru.mirea.kainov.mytourism.presentation.FullPost.FullPostViewModel;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {

    public Context context;

    public ProfileViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            return (T) new ProfileViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
