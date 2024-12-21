package domain.usecases;

import domain.repositories.AuthCallback;
import domain.repositories.AuthRepository;

public class LoginUseCase {
    AuthRepository authRepository;
    public LoginUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public void execute(String email, String password, AuthCallback authCallback ) {
        this.authRepository.signIn(email,password,authCallback);
    }
}
