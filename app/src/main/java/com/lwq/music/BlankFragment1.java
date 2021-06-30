package com.lwq.music;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
public class BlankFragment1 extends Fragment {
    RelativeLayout rl;
    View rootView;
    ArrayList<String> t_data = new ArrayList<>();
    ArrayList<Bitmap> b_data = new ArrayList<>();

    public BlankFragment1() {
    }

    // TODO: Rename and change types and number of parameters
    public static BlankFragment1 newInstance(ArrayList<String>text,ArrayList<Bitmap> images) {
        BlankFragment1 fragment = new BlankFragment1();
        fragment.t_data = text;
        fragment.b_data = images;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.home, container, false);
        }

        return rootView;
    }
}