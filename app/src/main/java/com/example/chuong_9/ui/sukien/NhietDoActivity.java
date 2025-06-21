package com.example.chuong_9.ui.sukien;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.chuong_9.R;

public class NhietDoActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private View borderView;
    private TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nhiet_do);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo view
        borderView = findViewById(R.id.border_layout);
        temperatureTextView = findViewById(R.id.temperature_text);

        // Khởi tạo cảm biến
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        // Kiểm tra cảm biến
        if (temperatureSensor == null) {
            Toast.makeText(this, "Thiết bị không hỗ trợ cảm biến nhiệt độ!", Toast.LENGTH_LONG).show();
            temperatureTextView.setText("Không có cảm biến nhiệt độ");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (temperatureSensor != null) {
            sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float temperature = event.values[0]; // Nhiệt độ (độ Celsius)

            // Cập nhật TextView
            temperatureTextView.setText(String.format("Nhiệt độ: %.1f°C", temperature));

            // Thay đổi màu viền
            int color = calculateBorderColor(temperature);
            borderView.setBackgroundColor(color);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Không cần xử lý
    }

    private int calculateBorderColor(float temperature) {
        // Thay đổi màu dựa trên nhiệt độ
        if (temperature < 10) {
            return Color.BLUE; // Lạnh
        } else if (temperature < 25) {
            return Color.GREEN; // Mát
        } else if (temperature < 35) {
            return Color.YELLOW; // Ấm
        } else {
            return Color.RED; // Nóng
        }
    }
}