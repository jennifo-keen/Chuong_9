package com.example.chuong_9.ui.sukien;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.chuong_9.R;

public class NghiengActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private View borderView;
    private static final float TILT_THRESHOLD = 2.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nghieng);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo cảm biến và view
        borderView = findViewById(R.id.border_layout);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];

            // Tính góc nghiêng
            float tiltX = x / SensorManager.GRAVITY_EARTH;
            float tiltY = y / SensorManager.GRAVITY_EARTH;

            // Thay đổi màu viền
            int color = calculateBorderColor(tiltX, tiltY);
            borderView.setBackgroundColor(color);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Không cần xử lý
    }

    private int calculateBorderColor(float tiltX, float tiltY) {
        // Chuẩn hóa giá trị nghiêng
        float normalizedX = Math.min(Math.max(tiltX, -TILT_THRESHOLD), TILT_THRESHOLD) / TILT_THRESHOLD;
        float normalizedY = Math.min(Math.max(tiltY, -TILT_THRESHOLD), TILT_THRESHOLD) / TILT_THRESHOLD;

        // Tính giá trị RGB
        int red = (int) ((normalizedX + 1) * 127.5);   // -1 to 1 maps to 0 to 255
        int green = (int) ((normalizedY + 1) * 127.5); // -1 to 1 maps to 0 to 255
        int blue = (int) ((Math.abs(normalizedX * normalizedY)) * 255);

        return Color.rgb(red, green, blue);
    }
}