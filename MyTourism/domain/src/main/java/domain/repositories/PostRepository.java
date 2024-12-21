package domain.repositories;

import java.io.IOException;
import java.util.List;

import domain.models.Post;

public interface PostRepository {

    public Post savePost();


    public List<Post> getPosts();

    public int deletePostById();

    public Post getById(String id);


    public Post editPost();
}
