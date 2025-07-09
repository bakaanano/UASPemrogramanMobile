package id.edu.unmer.fti.uaspemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class BrightnessControlActivity extends AppCompatActivity {

    private SeekBar brightnessControl;
    private TextView brightnessValue;
    private Window window;
    private float currentBrightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness_control);

        brightnessControl = findViewById(R.id.brightnessControl);
        brightnessValue = findViewById(R.id.brightnessValue);
        Button btnFlashlight = findViewById(R.id.btnFlashlight);
        Button btnClock = findViewById(R.id.btnClock);
        window = getWindow();

        currentBrightness = window.getAttributes().screenBrightness;
        if (currentBrightness == -1) {
            currentBrightness = 0.5f;
        }

        brightnessControl.setProgress((int)(currentBrightness * 100));
        brightnessValue.setText(String.valueOf(currentBrightness));

        brightnessControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float brightness = (float) progress / 100;
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.screenBrightness = brightness;
                window.setAttributes(layoutParams);
                brightnessValue.setText(String.format("%.2f", brightness));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnFlashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrightnessControlActivity.this, FlashLightActivity.class);
                startActivity(intent);
            }
        });

        btnClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrightnessControlActivity.this, ClockActivity.class);
                startActivity(intent);
            }
        });
    }
}