package ru.mirea.kainov.fragmentmanagerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                    .add(R.id.countryListFragment, new CountryListFragment(), "list")
                    .add(R.id.countryDetailFragment, new CountryDetailFragment(), "details")
                    .commit();
        }
    }

}