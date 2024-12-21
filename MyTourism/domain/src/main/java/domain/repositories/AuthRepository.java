package domain.repositories;

public interface AuthRepository {
    void signIn(String email, String password, AuthCallback callback);
    void signUp(String email, String password, AuthCallback callback);
}
