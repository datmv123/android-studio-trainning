package com.example.app3_intent;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicScreen extends AppCompatActivity {

    @BindView(R.id.buttonLoadMusic)
    Button buttonLoadMusic;
    @BindView(R.id.scrollViewMusicList)
    ScrollView scrollViewMusicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_screen);
        ButterKnife.bind(this);
        scrollViewMusicList.removeAllViews();
        refreshListMusic();
        buttonLoadMusic.setOnClickListener(t -> {
            scrollViewMusicList.removeAllViews();
            refreshListMusic();
        });
    }

    private void refreshListMusic() {
        File SDCard = getSdCard();
        List<HashMap<String, String>> songList = getPlayList(SDCard);
        if (songList == null || songList.isEmpty()) {
            TextView textView = new TextView(this.getApplicationContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setText("SD card does not have any song !!!");
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            textView.setGravity(Gravity.CENTER);
            scrollViewMusicList.addView(textView);
        } else {
            for (int i = 0; i < songList.size(); i++) {
                String fileName = songList.get(i).get("file_name");
                String filePath = songList.get(i).get("file_path");
                //here you will get list of file name and file path that present in your device
                Button song = new Button(this.getApplicationContext());
                song.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                song.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                song.setText(fileName);
                song.setOnClickListener(t -> playSong(filePath));
                scrollViewMusicList.addView(song);
            }
        }
    }

    private void playSong(String path) {
        Intent playMusic = new Intent();
        playMusic.setAction(android.content.Intent.ACTION_VIEW);
        playMusic.setDataAndType(Uri.fromFile(new File(path)), "audio/*");
        startActivity(playMusic);
    }

    private File getSdCard() {
        return new File("/storage/self/primary/Download");
    }

    private List<HashMap<String, String>> getPlayList(File rootFolder) {
        List<HashMap<String, String>> fileList = new ArrayList<>();
        File[] files = rootFolder.listFiles();
        for (File file : files) {
            if (file.isDirectory() && !file.isHidden()) {
                fileList.addAll(getPlayList(file));
            } else if (file.getName().endsWith(".mp3")) {
                HashMap<String, String> song = new HashMap<>();
                song.put("file_path", file.getAbsolutePath());
                song.put("file_name", file.getName());
                fileList.add(song);
            } else {
                //do nothing
            }
        }
        return fileList;
    }
}
