package ru.mirea.kainov.mytourism.presentation.FullPost;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.kainov.mytourism.R;

public class FullPost extends AppCompatActivity {
    private FullPostViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView titleTextView = findViewById(R.id.post_title);
        TextView bodyTextView = findViewById(R.id.post_body);

        vm = new ViewModelProvider(this, new FullPostViewModelFactory(this)).get(FullPostViewModel.class);


        vm.getPostStateLiveData().observe(this, postState -> {
            if (postState != null) {
                if (postState.post != null) {
                    titleTextView.setText(postState.post.getTitle());
                    bodyTextView.setText(postState.post.getBody());
                } else if (postState.error != null) {
                    // Показать сообщение об ошибке
                    titleTextView.setText("Ошибка загрузки поста");
                    bodyTextView.setText("");
                    Toast.makeText(this, postState.error, Toast.LENGTH_SHORT).show();
                }
            }
        });

        String postId = getIntent().getStringExtra("postId");
        vm.loadPost(postId);

    }

}