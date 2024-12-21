package ru.mirea.kainov.data.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import domain.sharedpref.UserPreferences;


public class UserPreferencesImpl implements UserPreferences {
    private final SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AuthPrefs";
    private static final String KEY_EMAIL = "user_email";

    public UserPreferencesImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    @Override
    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    @Override
    public void logOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_EMAIL);
        editor.apply();
    }
}