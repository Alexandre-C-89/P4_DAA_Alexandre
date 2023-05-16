package com.example.p4_daa_alexandre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.p4_daa_alexandre.databinding.FragmentCreateMeetingBinding;

public class CreateMeetingFragment extends Fragment {

    private FragmentCreateMeetingBinding binding;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateMeetingBinding.inflate(inflater, container, false);

        // Ajouter le bouton de retour en arrière dans la barre d'outils
        //Toolbar toolbar = binding.toolbar;
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Simuler un appui sur le bouton de retour système
                getActivity().onBackPressed();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}