package ru.mirea.kainov.mytourism.presentation.Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import domain.sharedpref.UserPreferences;
import ru.mirea.kainov.data.sharedprefs.UserPreferencesImpl;
import ru.mirea.kainov.mytourism.R;

public class ProfileViewModel extends ViewModel {
    UserPreferences userPreferences;

    MutableLiveData<Boolean> logoutLiveData = new MutableLiveData<>();

    MutableLiveData<String> userProfileLiveData = new MutableLiveData<>();

    public ProfileViewModel(Context context){
        this.userPreferences = new UserPreferencesImpl(context);
        getEmailProfile();

    }

    public LiveData<Boolean> isLogout() {
        return logoutLiveData;
    }

    public void logOut() {
        userPreferences.logOut();
    }

    public void getEmailProfile() {
       userProfileLiveData.postValue(userPreferences.getEmail());
    }


}