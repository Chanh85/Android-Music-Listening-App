package tdtu.finalterm520h0335_519h0189.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import tdtu.finalterm520h0335_519h0189.R;

public class Fragment_dia_nhac extends Fragment {
    View view;

    CircleImageView circleImageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac,container,false);
        circleImageView = view.findViewById(R.id.circleviewdianhac);


        return view;
    }

    public void Loadnhac(String hinhanh) {
        Picasso.get().load(hinhanh).into(circleImageView);
    }

    public void StartAnimation()
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                circleImageView.animate().rotationBy(360).withEndAction(this).setDuration(50000).setInterpolator(new LinearInterpolator()).start();
            }
        };
        circleImageView.animate().rotationBy(360).withEndAction(runnable).setDuration(50000).setInterpolator(new LinearInterpolator()).start();
    }

    public void StopAnimation()
    {
        circleImageView.animate().cancel();
    }
}
