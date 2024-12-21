package ru.mirea.kainov.resultapifragmentapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ResultApiFragmentApp extends AppCompatActivity implements FragmentListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_api_fragment_app);
        getSupportFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, bundle) -> {
            String result = bundle.getString("key");
            Log.d(BottomSheetFragment.class.getSimpleName(), "I'm	MainActivity	" + result);
        });
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
//                .add(R.id.fragmentContainerBottomSheet, new BottomSheetFragment(), "bottomFragment")
                .add(R.id.fragmentContainerData, new DataFragment(), "dataFragment")
                .add(R.id.fragmentContainerImage, new BlankFragment(), "imageFragment")
                .commit();


    }

    @Override
    public void sendResult(String message) {
        Log.d(ResultApiFragmentApp.class.getSimpleName(), "message:	" + message);
    }


}