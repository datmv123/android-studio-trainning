package com.example.app7_touch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        gestureDetector = new GestureDetectorCompat(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //vuot tu trai qua phai, tu duoi len tren
                if(e2.getX() - e1.getX() > 0
                && e2.getY() - e1.getY()< 0){
                    imageView.setScaleX(2f);
                    imageView.setScaleY(2f);
                }
                //vuot tu phai qua trai, tu tren xuong duoi
                if(e2.getX() - e1.getX() < 0
                        && e2.getY() - e1.getY()> 0){
                    imageView.setScaleX(0.5f);
                    imageView.setScaleY(0.5f);
                }

                return true;
            }
        });

        imageView.setOnTouchListener((view, event)->{
            return gestureDetector.onTouchEvent(event);
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Toast.makeText(this, "touch down screen", Toast.LENGTH_SHORT).show();
//                break;
//            case MotionEvent.ACTION_UP:
//                Toast.makeText(this, "touch up screen", Toast.LENGTH_SHORT).show();
//                break;
//        }
        return false;
    }
}
