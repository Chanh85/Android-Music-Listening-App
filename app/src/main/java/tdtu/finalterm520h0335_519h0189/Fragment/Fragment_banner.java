package tdtu.finalterm520h0335_519h0189.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import java.util.ArrayList;
import java.util.List;


import me.relex.circleindicator.CircleIndicator;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalterm520h0335_519h0189.Adapter.BannerAdapter;
import tdtu.finalterm520h0335_519h0189.Model.Quangcao;
import tdtu.finalterm520h0335_519h0189.R;
import tdtu.finalterm520h0335_519h0189.Service.APIService;
import tdtu.finalterm520h0335_519h0189.Service.DataService;

public class Fragment_banner extends Fragment {

    View view;
    ViewPager2 viewPager;
    CircleIndicator3 circleIndicator;
    BannerAdapter bannerAdapter;
    Handler handler;
    Runnable runnable;
    int currItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container,false);
        mapping();
        getData();
        return view;
    }

    private void mapping() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }



    private void getData()
    {
        DataService dataService = APIService.getService();
        Call<List<Quangcao>> callback = dataService.getDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);

                handler = new Handler();
                runnable = () -> {
                    currItem = viewPager.getCurrentItem();
                    currItem++;
                    if(currItem >= viewPager.getAdapter().getItemCount())
                    {
                        currItem = 0;
                    }
                    viewPager.setCurrentItem(currItem, true);
                    handler.postDelayed(runnable, 4500);
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {
            }
        });
    }
}
