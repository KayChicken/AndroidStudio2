package ru.mirea.kainov.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import ru.mirea.kainov.data.models.PostEntity;

@Dao
public interface PostDao {
    @Query("SELECT * FROM posts WHERE id = :postId LIMIT 1")
    PostEntity getPostById(String postId);

    @Query("SELECT * FROM posts")
    List<PostEntity> getAllPosts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPosts(List<PostEntity> posts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(PostEntity post);
}