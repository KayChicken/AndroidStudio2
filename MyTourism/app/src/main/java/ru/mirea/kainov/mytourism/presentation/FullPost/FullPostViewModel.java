package ru.mirea.kainov.mytourism.presentation.FullPost;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import domain.models.Post;
import domain.repositories.PostRepository;
import domain.usecases.GetPostByIdUseCase;

public class FullPostViewModel extends ViewModel {

    private final MutableLiveData<Post> postLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private final MediatorLiveData<PostState> postStateLiveData = new MediatorLiveData<>();
    private final GetPostByIdUseCase getPostByIdUseCase;
    private final ExecutorService executorService;

    public FullPostViewModel(PostRepository postRepository) {
        this.getPostByIdUseCase = new GetPostByIdUseCase(postRepository);
        this.executorService = Executors.newSingleThreadExecutor();

        postStateLiveData.addSource(postLiveData, post -> {
            postStateLiveData.setValue(new PostState(post, null));
        });

        postStateLiveData.addSource(errorLiveData, error -> {
            postStateLiveData.setValue(new PostState(null, error));
        });
    }

    public LiveData<PostState> getPostStateLiveData() {
        return postStateLiveData;
    }

    public void loadPost(String postId) {
        executorService.execute(() -> {
            try {
                Post post = getPostByIdUseCase.execute(postId);
                postLiveData.postValue(post);
            } catch (Exception e) {
                e.printStackTrace();
                errorLiveData.postValue("Ошибка загрузки поста");
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown();
    }

    public static class PostState {
        public final Post post;
        public final String error;

        public PostState(Post post, String error) {
            this.post = post;
            this.error = error;
        }
    }
}