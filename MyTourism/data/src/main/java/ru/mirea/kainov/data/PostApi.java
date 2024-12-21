package ru.mirea.kainov.data;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.mirea.kainov.data.models.PostEntity;

public interface PostApi {
    @GET("/posts")
    Call<List<PostEntity>> fetchPosts();

    @GET("/posts/{id}")
    Call<PostEntity> fetchPostById(@Path("id") String id);


}
