package ru.mirea.kainov.mytourism.presentation.Main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Collections;
import java.util.List;

import domain.models.Post;
import domain.usecases.GetPostsUseCase;
import ru.mirea.kainov.data.repository.PostRepositoryImpl;
import ru.mirea.kainov.mytourism.R;
import ru.mirea.kainov.mytourism.presentation.FullPost.FullPost;
import ru.mirea.kainov.mytourism.presentation.Profile.Profile;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public MainViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}