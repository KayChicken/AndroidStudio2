package ru.mirea.kainov.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.kainov.data.dao.PostDao;
import ru.mirea.kainov.data.models.PostEntity;

@Database(entities = {PostEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PostDao postDao();
}