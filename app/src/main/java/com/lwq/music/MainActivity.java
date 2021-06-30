package com.lwq.music;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ConstraintLayout home, seek, mine;
    ImageView ihome, iseek, imine, icurrent;
    ViewPager2 vp_home;
    TextView thome, tseek, tmine, tcurrent;
    Window window;
    boolean dark=true;
    ArrayList<Fragment> frameLayoutList = new ArrayList<>();
    ArrayList<String> tv_data=new ArrayList<>();
    ArrayList<Bitmap> iv_data=new ArrayList<>();
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatueBarTextColor();
        init_Bean();
        init_fragment();
        init_toolbar();
    }

    private void init_toolbar() {
    }

    private void init_fragment() {
        tv_data.add("新歌首发");
        tv_data.add("独家");
        tv_data.add("全新专辑");
        iv_data.add(BitmapFactory.decodeFile("src/main/res/mipmap-hdpi/banner.jpg"));
        iv_data.add(BitmapFactory.decodeFile("src/main/res/mipmap-hdpi/banner.jpg"));
        iv_data.add(BitmapFactory.decodeFile("src/main/res/mipmap-hdpi/banner.jpg"));
        frameLayoutList.add(BlankFragment1.newInstance(tv_data,iv_data));
        frameLayoutList.add(BlankFragment1.newInstance(tv_data,iv_data));
        frameLayoutList.add(BlankFragment1.newInstance(tv_data,iv_data));
        vp_home = findViewById(R.id.vp_home);
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), getLifecycle(), frameLayoutList);
        vp_home.setAdapter(myFragmentAdapter);
        vp_home.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                dark=!dark;
                super.onPageSelected(position);
                change(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void change(int position) {
        icurrent.setSelected(false);
        tcurrent.setTextColor(Color.BLACK);
        switch (position) {
            case R.id.bottom_home:
                vp_home.setCurrentItem(0);
            case 0:
                ihome.setSelected(true);
                thome.setTextColor(Color.RED);
                icurrent = ihome;
                tcurrent = thome;
                break;
            case R.id.bottom_seek:
                vp_home.setCurrentItem(1);
            case 1:
                iseek.setSelected(true);
                tseek.setTextColor(Color.RED);
                icurrent = iseek;
                tcurrent = tseek;
                break;
            case R.id.bottom_mine:
                vp_home.setCurrentItem(2);
            case 2:
                imine.setSelected(true);
                tmine.setTextColor(Color.RED);
                icurrent = imine;
                tcurrent = tmine;
                break;
        }
    }

    private void init_Bean() {
        home = findViewById(R.id.bottom_home);
        mine = findViewById(R.id.bottom_mine);
        seek = findViewById(R.id.bottom_seek);
        iseek = findViewById(R.id.bottom_iseek);
        ihome = findViewById(R.id.bottom_ihome);
        imine = findViewById(R.id.bottom_imine);
        thome = findViewById(R.id.bottom_tv1);
        tseek = findViewById(R.id.bottom_tv2);
        tmine = findViewById(R.id.bottom_tv3);
        home.setOnClickListener(this);
        seek.setOnClickListener(this);
        mine.setOnClickListener(this);
        icurrent = ihome;
        tcurrent = thome;
        ihome.setSelected(true);
        thome.setTextColor(Color.RED);
    }

    @Override
    public void onClick(View v) {
        change(v.getId());

    }
    private void setStatueBarTextColor() {
        if (dark) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        else{
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    public void test(View view) {
        Log.e("afdsdf","asdjafwne");
    }
}