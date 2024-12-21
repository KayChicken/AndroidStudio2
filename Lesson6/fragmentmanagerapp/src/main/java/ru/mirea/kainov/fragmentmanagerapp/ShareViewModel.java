package ru.mirea.kainov.fragmentmanagerapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {
    private final MutableLiveData<String> selectedCountry = new MutableLiveData<>();

    public void selectCountry(String country) {
        selectedCountry.setValue(country);
    }

    public LiveData<String> getSelectedCountry() {
        return selectedCountry;
    }
}

