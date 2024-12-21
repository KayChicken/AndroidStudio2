package ru.mirea.kainov.data.mappers;

import java.util.ArrayList;
import java.util.List;

import domain.models.Post;
import ru.mirea.kainov.data.models.PostEntity;
public class PostMapper {

    public static Post mapEntityToDomain(PostEntity postEntity) {
        Post post = new Post();
        post.setId(postEntity.id);
        post.setUserId(postEntity.userId);
        post.setTitle(postEntity.title);
        post.setCategory(postEntity.category);
        post.setBody(postEntity.body);
        return post;
    }

    public static List<Post> mapEntitiesToDomain(List<PostEntity> postEntities) {
        List<Post> posts = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            posts.add(mapEntityToDomain(postEntity));
        }
        return posts;
    }

    public static Post mapOneEntityToDomain(PostEntity postEntity) {
        Post post = mapEntityToDomain(postEntity);
        return post;
    }


    public static PostEntity mapDomainToEntity(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.id = post.getId();
        postEntity.userId = post.getUserId();
        postEntity.title = post.getTitle();
        postEntity.body = post.getBody();
        return postEntity;
    }

    public static List<PostEntity> mapDomainToEntities(List<Post> posts) {
        List<PostEntity> postEntities = new ArrayList<>();
        for (Post post : posts) {
            postEntities.add(mapDomainToEntity(post));
        }
        return postEntities;
    }
}