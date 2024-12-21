package ru.mirea.kainov.mytourism.presentation.Main;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import domain.models.Post;
import ru.mirea.kainov.mytourism.R;
import ru.mirea.kainov.mytourism.presentation.FullPost.FullPost;
import ru.mirea.kainov.mytourism.presentation.Main.RecycleView.PostAdapter;
import ru.mirea.kainov.mytourism.presentation.Profile.Profile;

public class Main extends Fragment {
    private MainViewModel vm;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    final String[] filterWords = {"Все", "Грибы", "Природа", "Маршруты"};
    final String[] filterKeys = {"all", "mushrooms", "nature", "trails"};
    private TableRow buttonContainer;

    private ImageView profileIcon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_news, container, false);

        vm = new ViewModelProvider(this, new MainViewModelFactory(requireActivity())).get(MainViewModel.class);


        recyclerView = view.findViewById(R.id.postsContainer);
        profileIcon = view.findViewById(R.id.profileImage);
        buttonContainer = view.findViewById(R.id.buttonsContainer);

        for (int i = 0; i < filterWords.length; i++) {
            int index = i;
            Button button = new Button(getContext());
            button.setText(filterWords[i]);
            button.setTextColor(getResources().getColor(R.color.white));
            button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            button.setOnClickListener(v -> filterPosts(filterKeys[index]));
            buttonContainer.addView(button);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter = new PostAdapter(getContext(), new ArrayList<>(), this::openPostDetail);
        recyclerView.setAdapter(postAdapter);

        vm.getPosts().observe(getViewLifecycleOwner(), this::displayPosts);
        vm.getError().observe(getViewLifecycleOwner(), this::showError);

        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Profile.class);
            startActivity(intent);
        });

        return view;
    }

    private void openPostDetail(String postId) {
        Intent intent = new Intent(getContext(), FullPost.class);
        intent.putExtra("postId", postId);
        startActivity(intent);
    }

    private void displayPosts(List<Post> posts) {
        postAdapter.updatePosts(posts);
    }

    private void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void filterPosts(String categoryKey) {
        vm.setCategoryFilter(categoryKey);
    }
}