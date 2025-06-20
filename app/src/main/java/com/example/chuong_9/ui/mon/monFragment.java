package com.example.chuong_9.ui.mon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chuong_9.databinding.FragmentMonBinding;

public class monFragment extends Fragment {
    private FragmentMonBinding binding;
    private TextView batteryStatus;

    // BroadcastReceiver để lắng nghe thay đổi về pin
    private final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent batteryStatusIntent) {
            // Lấy trạng thái sạc hiện tại (Charging, Full, Discharging, v.v.)
            int status = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
                    || status == BatteryManager.BATTERY_STATUS_FULL;

            // Kiểm tra thiết bị đang sạc bằng USB hay AC
            int chargePlug = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            // Lấy mức pin hiện tại và quy mô tối đa
            int level = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            // Tính phần trăm pin
            int batteryPct = (int) ((level / (float) scale) * 100);

            // Tạo chuỗi thông tin để hiển thị trên giao diện
            String info = "Battery: " + batteryPct + "%\nCharging: " + isCharging +
                    "\nVia: " + (usbCharge ? "USB" : acCharge ? "AC" : "Not Charging");

            // Hiển thị thông tin lên TextView
            batteryStatus.setText(info);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        monViewModel monViewModel =
                new ViewModelProvider(this).get(monViewModel.class);

        binding = FragmentMonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Gán TextView từ binding (giả định txtBatteryStatus tồn tại trong FragmentMonBinding)
        batteryStatus = binding.txtBatteryStatus;

        // Đăng ký BroadcastReceiver để lắng nghe sự kiện pin
        requireActivity().registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
