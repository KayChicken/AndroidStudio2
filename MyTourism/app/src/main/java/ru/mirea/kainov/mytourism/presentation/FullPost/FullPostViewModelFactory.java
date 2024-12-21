package ru.mirea.kainov.mytourism.presentation.FullPost;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import domain.repositories.PostRepository;
import ru.mirea.kainov.data.repository.PostRepositoryImpl;

public class FullPostViewModelFactory implements ViewModelProvider.Factory {
    public Context context;
    public FullPostViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FullPostViewModel.class)) {
            PostRepository movieRepository = new PostRepositoryImpl(context);
            return (T) new FullPostViewModel(movieRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}