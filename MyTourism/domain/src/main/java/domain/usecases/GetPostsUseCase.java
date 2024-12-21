package domain.usecases;

import java.util.List;

import domain.models.Post;
import domain.repositories.AuthCallback;
import domain.repositories.AuthRepository;
import domain.repositories.PostRepository;

public class GetPostsUseCase {
    PostRepository postRepository;
    public GetPostsUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public List<Post> execute() {
        return this.postRepository.getPosts();
    }
}
