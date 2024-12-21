package ru.mirea.kainov.fragmentmanagerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class CountryDetailFragment extends Fragment {
    private ShareViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_detail, container, false);
        TextView countryNameTextView = view.findViewById(R.id.countryDetailText);

        viewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        viewModel.getSelectedCountry().observe(getViewLifecycleOwner(), country -> {
            countryNameTextView.setText(country);
        });

        return view;
    }
}