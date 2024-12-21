package domain.usecases;

import domain.repositories.AuthCallback;
import domain.repositories.AuthRepository;

public class RegistrationUseCase {
    AuthRepository authRepository;
    public RegistrationUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public void execute(String email, String password, AuthCallback authCallback ) {
        this.authRepository.signUp(email,password,authCallback);
    }
}
