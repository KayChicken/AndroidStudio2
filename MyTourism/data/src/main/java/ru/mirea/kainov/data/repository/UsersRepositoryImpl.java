package ru.mirea.kainov.data.repository;


import domain.repositories.UsersRepository;

public class UsersRepositoryImpl implements UsersRepository {
    @Override
    public domain.models.User createUser() {
        return new domain.models.User();
    }

    @Override
    public domain.models.User getProfile() {
        return new domain.models.User();
    }
}
