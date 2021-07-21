package com.lwq.music;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.lwq.music.utils.OkHttpUtil;

import java.util.ArrayList;

public class BlankFragment1 extends Fragment {
    private View rootView;
    private ArrayList<String> t_data = new ArrayList<>();
    private ArrayList<Bitmap> b_data = new ArrayList<>();
    private ViewPager2 vp_banner;
    private LinearLayout indicatorContainer;
    private int lastPosition = 500;
    private Handler viewPageHandler = new Handler();
    private ArrayList<Integer> idList;
    private NestedScrollableHost nsh;
    private Button button;
    private TextView textView;

    public BlankFragment1() {
    }

    // TODO: Rename and change types and number of parameters
    public static BlankFragment1 newInstance(ArrayList<String> text, ArrayList<Bitmap> images, ArrayList<Integer> idList) {
        BlankFragment1 fragment = new BlankFragment1();
        fragment.t_data = text;
        fragment.b_data = images;
        fragment.idList = idList;
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPageHandler.postDelayed(runnable, 5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        viewPageHandler.removeCallbacks(runnable);
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
        init_view();
        return rootView;
    }

    private void init_view() {
        indicatorContainer = rootView.findViewById(R.id.container_indicator);
        vp_banner = rootView.findViewById(R.id.banner_viewpage2);
        BannerAdapter adapter = new BannerAdapter(b_data, t_data, idList);
        nsh = rootView.findViewById(R.id.banner_container);
        vp_banner.setAdapter(adapter);
        //设置轮播图指示点
        for (int i = 0; i < t_data.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            if (i == 0) imageView.setBackgroundResource(R.drawable.banner_point2);
            else imageView.setBackgroundResource(R.drawable.banner_point);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginEnd(5);
            imageView.setLayoutParams(layoutParams);
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
        textView = rootView.findViewById(R.id.httpText);
        button = rootView.findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "发送了请求", Toast.LENGTH_SHORT);
                OkHttpUtil okHttpUtil = new OkHttpUtil();
                okHttpUtil.get("https://netease-cloud-music-api-eta-two.vercel.app/banner?type=1",contentHandler);
            }
        });
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //获得轮播图当前的位置
            int currentPosition = vp_banner.getCurrentItem();
            currentPosition++;
            vp_banner.setCurrentItem(currentPosition, true);
            viewPageHandler.postDelayed(runnable, 5000);
        }
    };
    private Handler contentHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            textView.setText(msg.getData().getString("data"));
            return true;
        }
    });

}