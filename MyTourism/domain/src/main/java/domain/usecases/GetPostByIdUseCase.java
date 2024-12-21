package domain.usecases;

import java.util.List;

import domain.models.Post;
import domain.repositories.PostRepository;

public class GetPostByIdUseCase {
    PostRepository postRepository;
    public GetPostByIdUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Post execute(String id) {
        return this.postRepository.getById(id);
    }
}
