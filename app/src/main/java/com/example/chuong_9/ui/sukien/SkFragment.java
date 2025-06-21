package com.example.chuong_9.ui.sukien;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chuong_9.databinding.FragmentSkBinding;
import android.content.Intent;

public class SkFragment extends Fragment {

    private FragmentSkBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SkViewModel skViewModel =
                new ViewModelProvider(this).get(SkViewModel.class);

        binding = FragmentSkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnNghienDT.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), NghiengActivity.class));
        });

        binding.btnLacDT.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LacActivity.class));
        });

        binding.btnNhietDo.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), NhietDoActivity.class));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}