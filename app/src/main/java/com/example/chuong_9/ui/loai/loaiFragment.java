    package com.example.chuong_9.ui.loai;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.ViewModelProvider;

    import com.example.chuong_9.databinding.FragmentLoaiBinding;

    public class loaiFragment extends Fragment {
        private FragmentLoaiBinding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            loaiViewModel loaiViewModel =
                    new ViewModelProvider(this).get(loaiViewModel.class);

            binding = FragmentLoaiBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            return root;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
    }
