package com.example.app3_intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.Math.floor;
import static java.lang.Math.random;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.buttonNextScreen)
    Button buttonNextScreen;
    @BindView(R.id.buttonToPlayMusic)
    Button buttonToPlayMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView.setText("" + floor(random() * 10));
        Intent demoScreen = new Intent(getApplicationContext(), OtherActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("key", textView.getText().toString());
        demoScreen.putExtras(bundle);
        buttonNextScreen.setOnClickListener(t -> startActivity(demoScreen));

        Intent musicScreen = new Intent(getApplicationContext(),MusicScreen.class);
        buttonToPlayMusic.setOnClickListener(t-> startActivity(musicScreen));
    }
}
