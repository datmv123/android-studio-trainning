package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            setBackGroundTextView();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar seekBarRed;
    private SeekBar seekBarGreen;
    private SeekBar seekBarBlue;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarRed = findViewById(R.id.seekBarColorRed);
        seekBarGreen = findViewById(R.id.seekBarColorGreen);
        seekBarBlue = findViewById(R.id.seekBarColorBlue);
        textView = findViewById(R.id.textViewShow);

        seekBarRed.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarGreen.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarBlue.setOnSeekBarChangeListener(seekBarChangeListener);

        // init default color value
        textView.setBackgroundColor(0);
    }

    private void setBackGroundTextView() {
        textView.setBackgroundColor(getComposeColorValue());
    }

    private int getComposeColorValue() {
        int red = seekBarRed.getProgress();
        int blue = seekBarBlue.getProgress();
        int green = seekBarGreen.getProgress();
        return Color.rgb(red, green, blue);
    }
}
