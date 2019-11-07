package com.example.app3_intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textViewShow;
    @BindView(R.id.buttonBackToMain)
    Button buttonBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String value = bundle.get("key").toString();
        textViewShow.setText("recieve: " + value);

        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);

        buttonBackToMain.setOnClickListener(t -> startActivity(mainActivityIntent));
    }
}
