package ru.mirea.kainov.data.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class PostEntity {
    @NonNull
    @PrimaryKey
    public String id;
    public String userId;
    public String category;
    public String title;
    public String body;
}