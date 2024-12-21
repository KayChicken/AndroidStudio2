package ru.mirea.kainov.data.repository;

import android.content.Context;

import androidx.room.Room;

import java.io.IOException;
import java.util.List;

import domain.models.Post;
import domain.repositories.PostRepository;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mirea.kainov.data.PostApi;
import ru.mirea.kainov.data.dao.PostDao;
import ru.mirea.kainov.data.mappers.PostMapper;
import ru.mirea.kainov.data.models.PostEntity;
import ru.mirea.kainov.data.room.AppDatabase;

public class PostRepositoryImpl implements PostRepository {
    private final PostDao postDao;
    private final PostApi postApi;

    public PostRepositoryImpl(Context context) {
        AppDatabase appDatabase = Room.databaseBuilder(
                context, AppDatabase.class, "app_database"
        ).build();
        this.postDao = appDatabase.postDao();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://67418e65e4647499008e0ae6.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.postApi = retrofit.create(PostApi.class);
    }

    @Override
    public Post savePost() {
        return null;
    }

    @Override
    public List<Post> getPosts() {
        try {
            List<PostEntity> postsFromNetwork = postApi.fetchPosts().execute().body();
            if (postsFromNetwork != null) {
                postDao.insertPosts(postsFromNetwork);
                return PostMapper.mapEntitiesToDomain(postsFromNetwork);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<PostEntity> cachedPosts = postDao.getAllPosts();
        if (!cachedPosts.isEmpty()) {
            return PostMapper.mapEntitiesToDomain(cachedPosts);
        }

        return null;
    }

    @Override
    public int deletePostById() {
        return 0;
    }

    @Override
    public Post getById(String id) {
        try {
            PostEntity postFromNetwork = postApi.fetchPostById(id).execute().body();
            if (postFromNetwork != null) {
                postDao.insertPost(postFromNetwork);
                return PostMapper.mapEntityToDomain(postFromNetwork);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PostEntity cachedPost = postDao.getPostById(id);
        if (cachedPost != null) {
            return PostMapper.mapOneEntityToDomain(cachedPost);
        }

        return null;
    }

    @Override
    public Post editPost() {
        return null;
    }
}