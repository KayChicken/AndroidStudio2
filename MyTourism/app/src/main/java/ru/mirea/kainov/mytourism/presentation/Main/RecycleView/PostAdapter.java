package ru.mirea.kainov.mytourism.presentation.Main.RecycleView;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import domain.models.Post;
import ru.mirea.kainov.mytourism.R;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private final Context context;
    private final List<Post> posts;
    private final OnPostClickListener listener;

    public interface OnPostClickListener {
        void onPostClick(String postId);
    }

    public PostAdapter(Context context, List<Post> posts, OnPostClickListener listener) {
        this.context = context;
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.titleTextView.setText(post.getTitle());

        CharSequence shortDescription = TextUtils.ellipsize(post.getBody(),
                holder.bodyTextView.getPaint(), 500, TextUtils.TruncateAt.END);
        holder.bodyTextView.setText(shortDescription);

        Picasso.get()
                .load("https://random-image-pepebigotes.vercel.app/api/random-image")
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.lake)
                .into(holder.postImage);

        holder.itemView.setOnClickListener(v -> listener.onPostClick(post.getId()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void updatePosts(List<Post> newPosts) {
        posts.clear();
        posts.addAll(newPosts);
        notifyDataSetChanged();
    }

}
