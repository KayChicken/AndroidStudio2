package ru.mirea.kainov.mytourism.presentation.Main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.models.Post;
import domain.usecases.GetPostsUseCase;
import ru.mirea.kainov.data.repository.PostRepositoryImpl;
import ru.mirea.kainov.mytourism.R;
import ru.mirea.kainov.mytourism.presentation.FullPost.FullPost;
import ru.mirea.kainov.mytourism.presentation.Profile.Profile;

public class MainViewModel extends ViewModel {

    private String currentCategory = "all";
    private final MutableLiveData<List<Post>> postsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final GetPostsUseCase getPostsUseCase;

    public MainViewModel(Context context) {
        this.getPostsUseCase = new GetPostsUseCase(new PostRepositoryImpl(context));
        loadPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return postsLiveData;
    }

    public LiveData<String> getError() {
        return errorLiveData;
    }

    private void loadPosts() {
        new Thread(() -> {
            try {
                List<Post> posts = getPostsUseCase.execute();
                List<Post> filteredPosts;
                if ("all".equals(currentCategory)) {
                    filteredPosts = posts;
                } else {
                    filteredPosts = posts.stream()
                            .filter(post -> post.getCategory() != null && post.getCategory().equals(currentCategory))
                            .collect(Collectors.toList());
                }
                postsLiveData.postValue(filteredPosts);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("ERROR_TAG",e.toString());
                errorLiveData.postValue("Ошибка загрузки постов");
                postsLiveData.postValue(Collections.emptyList());
            }
        }).start();
    }

    public void setCategoryFilter(String category) {
        currentCategory = category;
        loadPosts();
    }
}