package ru.mirea.kainov.mytourism.presentation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.mirea.kainov.mytourism.R;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    private NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        navController = navHostFragment.getNavController();

        bottomNavigation = findViewById(R.id.bottomNavigationView);

        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.news:
                    navController.navigate(R.id.main2);
                    return true;
                case R.id.analize:
                    navController.navigate(R.id.uploadImage);
                    return true;
                case R.id.profile:
                    navController.navigate(R.id.profile2);
                    return true;
                default:
                    return false;
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
