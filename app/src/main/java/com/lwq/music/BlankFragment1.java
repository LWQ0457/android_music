package com.lwq.music;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
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
    ViewPager2 vp_banner;
    private LinearLayout indicatorContainer;
    private int lastPosition=500;
    private Handler mHandler=new Handler();

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
        indicatorContainer=rootView.findViewById(R.id.container_indicator);
        vp_banner=rootView.findViewById(R.id.banner_viewpage2);
        BannerAdapter adapter=new BannerAdapter(b_data,t_data);
        NestedScrollableHost nsh=rootView.findViewById(R.id.banner_container);
        nsh.setOnClickListener(v -> {
            Log.e("asdfjnaowejnf","aosdfoinaweon");
            Toast.makeText(getContext(),"asdifbwiae",Toast.LENGTH_LONG);
        });
        vp_banner.setAdapter(adapter);
        init_vp();
        return rootView;
    }

    private void init_vp() {

        for(int i = 0; i < t_data.size(); i++){
            ImageView imageView = new ImageView(getContext());
            if (i == 0) imageView.setBackgroundResource(R.drawable.banner_point2);
            else imageView.setBackgroundResource(R.drawable.banner_point);
            //为指示点添加间距
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginEnd(5);
            imageView.setLayoutParams(layoutParams);
            //将指示点添加进容器
            indicatorContainer.addView(imageView);
        }
        vp_banner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //轮播时，改变指示点
                int current = position % t_data.size();
                int last = lastPosition % t_data.size();
                indicatorContainer.getChildAt(current).setBackgroundResource(R.drawable.banner_point2);
                indicatorContainer.getChildAt(last).setBackgroundResource(R.drawable.banner_point);
                lastPosition = position;
            }
        });
        vp_banner.setCurrentItem(500);
    }
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //获得轮播图当前的位置
            int currentPosition = vp_banner.getCurrentItem();
            currentPosition++;
            vp_banner.setCurrentItem(currentPosition,true);
            mHandler.postDelayed(runnable,5000);
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(runnable,5000);
    }

    /* 当应用被暂停时，让轮播图停止轮播 */
    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(runnable);
    }

}