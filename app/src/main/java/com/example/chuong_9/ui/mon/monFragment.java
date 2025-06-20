    package com.example.chuong_9.ui.mon;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.ViewModelProvider;

    import com.example.chuong_9.databinding.FragmentMonBinding;

    public class monFragment extends Fragment {
        private FragmentMonBinding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            monViewModel monViewModel =
                    new ViewModelProvider(this).get(monViewModel.class);

            binding = FragmentMonBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            return root;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
    }
