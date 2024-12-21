package ru.mirea.kainov.mytourism.presentation.Main.RecycleView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import ru.mirea.kainov.mytourism.R;

public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView titleTextView, bodyTextView;
    ShapeableImageView postImage;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.postTitle);
        bodyTextView = itemView.findViewById(R.id.postDescription);
        postImage = itemView.findViewById(R.id.post_image);
    }
}