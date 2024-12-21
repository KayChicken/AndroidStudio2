package ru.mirea.kainov.mytourism.presentation.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.kainov.mytourism.R;
import ru.mirea.kainov.mytourism.presentation.Authorization.Authorization;

public class Profile extends Fragment {
    private ProfileViewModel vm;
    private Button logoutBtn;

    private TextView userProfileEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = new ViewModelProvider(this, new ProfileViewModelFactory(this.getContext())).get(ProfileViewModel.class);
        vm.userProfileLiveData.observe(this, email -> userProfileEmail.setText(email));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        userProfileEmail = view.findViewById(R.id.userProfileEmail);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.logOut();
                Toast.makeText(getActivity(), "Выход из аккаунта", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Authorization.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
