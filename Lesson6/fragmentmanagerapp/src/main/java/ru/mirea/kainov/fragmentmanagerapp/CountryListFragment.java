package ru.mirea.kainov.fragmentmanagerapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CountryListFragment extends Fragment {

    private String[] countries = {"Россия", "США", "Франция", "Германия", "Япония"};

    private ShareViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        ListView listView = view.findViewById(R.id.countryListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedCountry = countries[position];
            viewModel.selectCountry(selectedCountry);
        });

        return view;
    }
}