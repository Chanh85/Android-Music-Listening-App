package tdtu.finalterm520h0335_519h0189.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import tdtu.finalterm520h0335_519h0189.Adapter.MainViewPagerAdapter;
import tdtu.finalterm520h0335_519h0189.Fragment.Fragment_TimKiem;
import tdtu.finalterm520h0335_519h0189.Fragment.Fragment_TrangChu;
import tdtu.finalterm520h0335_519h0189.R;

public class MainActivity extends AppCompatActivity {
    
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();



    }

    private void init()
    {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu()) ;
        mainViewPagerAdapter.addFragment(new Fragment_TimKiem());
        viewPager.setAdapter(mainViewPagerAdapter);

        String[] tabTitles = {"Trang chu", "Tim kiem"};
        new TabLayoutMediator(tabLayout, viewPager,(tabLayout,position) -> tabLayout.setText(tabTitles[position])).attach();


    }

    private void mapping()
    {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

}