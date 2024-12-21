package domain.repositories;

import domain.models.User;

public interface UsersRepository {
    public User createUser();
    public User getProfile();
}

