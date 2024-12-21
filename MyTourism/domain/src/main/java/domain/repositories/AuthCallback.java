package domain.repositories;

public interface AuthCallback {
    void onSuccess();
    void onError(String errorMessage);
}