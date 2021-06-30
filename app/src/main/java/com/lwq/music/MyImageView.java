package com.lwq.music;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {
    private static float x1, x2, y1, y2;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyImageView(Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                x1=event.getX();
//                y1=event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                return super.onTouchEvent(event);
//
//            case MotionEvent.ACTION_UP:
//                x2=event.getX();
//                y2=event.getY();
//                long downTime = SystemClock.uptimeMillis();
//                long eventTime = SystemClock.uptimeMillis() + 100;
//                float x = 0.0f;
//                float y = 0.0f;
//                if(Math.abs(x2-x1)<5||Math.abs(y1-y2)<5){
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                    return super.onTouchEvent(event);
//                }
//                return super.onTouchEvent(MotionEvent.obtain(downTime,eventTime,MotionEvent.ACTION_MOVE,x1,x2,10,10,0,x2,y2,0,0));
//
//            default:
//                break;
//        }
//
//        return super.onTouchEvent(event);
//    }

}
