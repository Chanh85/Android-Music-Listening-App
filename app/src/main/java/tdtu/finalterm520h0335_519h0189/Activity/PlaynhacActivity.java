package tdtu.finalterm520h0335_519h0189.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import tdtu.finalterm520h0335_519h0189.Adapter.ViewPagerPlayDSnhac;
import tdtu.finalterm520h0335_519h0189.Custom.BlurBuilder;
import tdtu.finalterm520h0335_519h0189.Fragment.Fragment_dia_nhac;
import tdtu.finalterm520h0335_519h0189.Fragment.Fragment_playdsbaihat;
import tdtu.finalterm520h0335_519h0189.Model.Baihatyeuthich;
import tdtu.finalterm520h0335_519h0189.Model.ChuDe;
import tdtu.finalterm520h0335_519h0189.R;

public class PlaynhacActivity extends AppCompatActivity {
    Baihatyeuthich baihatyeuthich;
    ArrayList<Baihatyeuthich> mangbaihatyeuthich;
    LinearLayout linearLayoutplaysong;
    Toolbar toolbarplaysong;
    ViewPager2 viewPagerplaysong;
    TextView txtviewtimesong, txtviewtotaltimesong;
    SeekBar seekBarsong;
    ImageButton imgbuttonshufflesong, imgbuttonpreviewsong, imgbuttonplaysong,imgbuttonnextsong,imgbuttonrepeatsong;
    public static  ArrayList<Baihatyeuthich> dsbaihat = new ArrayList<>();
    public static ViewPagerPlayDSnhac adapternhac;
    Fragment_dia_nhac fragment_dia_nhac;
    Fragment_playdsbaihat playdsbaihat;
    MediaPlayer mediaPlayer;
    int positionforchangesong = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playnhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetIntent();
        Mapping();
        init();

        eventClick();

    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.createFragment(0) != null)
                {
                    if(dsbaihat.size()>0)
                    {
                        fragment_dia_nhac.Loadnhac(dsbaihat.get(0).getHinhBaiHat());
                        fragment_dia_nhac.StartAnimation();
                        handler.removeCallbacks(this);
                    }
                    else
                    {
                        handler.postDelayed(this,300);
                    }
                }
            }
        }, 500);
        imgbuttonplaysong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    imgbuttonplaysong.setImageResource(R.drawable.iconplay);
                    fragment_dia_nhac.StopAnimation();
                }
                else
                {
                    mediaPlayer.start();
                    imgbuttonplaysong.setImageResource(R.drawable.iconpause);
                    fragment_dia_nhac.StartAnimation();
                }
            }
        });
        imgbuttonrepeatsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!repeat)
                {
                    if(checkrandom)
                    {
                        checkrandom = false;
                        imgbuttonrepeatsong.setImageResource(R.drawable.iconsyned);
                        imgbuttonshufflesong.setImageResource(R.drawable.iconsuffle);
                    }
                    imgbuttonrepeatsong.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }
                else
                {
                    imgbuttonrepeatsong.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgbuttonshufflesong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkrandom)
                {
                    if(repeat)
                    {
                        repeat = false;
                        imgbuttonshufflesong.setImageResource(R.drawable.iconshuffled);
                        imgbuttonrepeatsong.setImageResource(R.drawable.iconrepeat);
                    }
                    imgbuttonshufflesong.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }
                else
                {
                    imgbuttonshufflesong.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        seekBarsong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgbuttonnextsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dsbaihat.size() != 0)
                {
                    if(mediaPlayer.isPlaying() || mediaPlayer != null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(positionforchangesong < dsbaihat.size())
                    {
                        imgbuttonplaysong.setImageResource(R.drawable.iconpause);
                        positionforchangesong++;
                        if(repeat)
                        {
                            if(positionforchangesong == 0)
                            {
                                positionforchangesong = dsbaihat.size();
                            }
                            positionforchangesong -=1;
                        }
                        if(checkrandom)
                        {
                            Random random = new Random();
                            int index = random.nextInt(dsbaihat.size());
                            if(index == positionforchangesong)
                            {
                                positionforchangesong = index-1;
                            }
                            positionforchangesong = index;
                        }
                        if(positionforchangesong > dsbaihat.size()-1)
                        {
                            positionforchangesong = 0;
                        }
                        new PlayMusic().execute(dsbaihat.get(positionforchangesong).getLinkBaiHat());
                        fragment_dia_nhac.Loadnhac(dsbaihat.get(positionforchangesong).getHinhBaiHat());
                        setValueInView(dsbaihat.get(positionforchangesong).getTenBaiHat(), dsbaihat.get(positionforchangesong).getHinhBaiHat());
                        UpdateTimeSong();
                    }
                }
                imgbuttonpreviewsong.setClickable(false);
                imgbuttonnextsong.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgbuttonpreviewsong.setClickable(true);
                        imgbuttonnextsong.setClickable(true);
                    }
                },5000);
            }
        });
        imgbuttonpreviewsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dsbaihat.size() != 0)
                {
                    if(mediaPlayer.isPlaying() || mediaPlayer != null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(positionforchangesong < dsbaihat.size())
                    {
                        imgbuttonplaysong.setImageResource(R.drawable.iconpause);
                        positionforchangesong--;
                        if(positionforchangesong < 0)
                        {
                            positionforchangesong = dsbaihat.size()-1;
                        }
                        if(repeat)
                        {
                            positionforchangesong +=1;
                        }
                        if(checkrandom)
                        {
                            Random random = new Random();
                            int index = random.nextInt(dsbaihat.size());
                            if(index == positionforchangesong)
                            {
                                positionforchangesong = index-1;
                            }
                            positionforchangesong = index;
                        }
                        new PlayMusic().execute(dsbaihat.get(positionforchangesong).getLinkBaiHat());
                        fragment_dia_nhac.Loadnhac(dsbaihat.get(positionforchangesong).getHinhBaiHat());
                        setValueInView(dsbaihat.get(positionforchangesong).getTenBaiHat(), dsbaihat.get(positionforchangesong).getHinhBaiHat());
                        UpdateTimeSong();
                    }
                }
                imgbuttonpreviewsong.setClickable(false);
                imgbuttonnextsong.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgbuttonpreviewsong.setClickable(true);
                        imgbuttonnextsong.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void setValueInView(String tenBaiHat, String hinhBaiHat) {
        getSupportActionBar().setTitle(tenBaiHat);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL(hinhBaiHat);
                    Bitmap originalBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    Bitmap blurredBitmap = BlurBuilder.blur( PlaynhacActivity.this, originalBitmap );

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            linearLayoutplaysong.setBackground(new BitmapDrawable(getResources(),blurredBitmap));
                        }
                    });

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    private void init()
    {
        setSupportActionBar(toolbarplaysong);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarplaysong.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                dsbaihat.clear();
            }
        });
        toolbarplaysong.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_dia_nhac();
        playdsbaihat = new Fragment_playdsbaihat();
        adapternhac = new ViewPagerPlayDSnhac(getSupportFragmentManager(), getLifecycle());
        adapternhac.AddFragment(fragment_dia_nhac);
        adapternhac.AddFragment(playdsbaihat);
        viewPagerplaysong.setAdapter(adapternhac);
        fragment_dia_nhac = (Fragment_dia_nhac) adapternhac.createFragment(0);
        if(dsbaihat.size() >0)
        {
            setValueInView(dsbaihat.get(0).getTenBaiHat(), dsbaihat.get(0).getHinhBaiHat());
            new PlayMusic().execute(dsbaihat.get(0).getLinkBaiHat());
            imgbuttonplaysong.setImageResource(R.drawable.iconpause);
        }
    }

    private void Mapping() {
        linearLayoutplaysong = findViewById(R.id.linearlayoutplaysong);
        toolbarplaysong = findViewById(R.id.toolbarplaynhac);
        viewPagerplaysong = findViewById(R.id.viewpagernghenhac);
        txtviewtimesong = findViewById(R.id.txtviewtimesong);
        txtviewtotaltimesong = findViewById(R.id.txtviewtotaltimesong);
        seekBarsong = findViewById(R.id.seekbarsong);
        imgbuttonshufflesong = findViewById(R.id.imgbuttonshufflesong);
        imgbuttonpreviewsong = findViewById(R.id.imgbuttonpreviewsong);
        imgbuttonplaysong = findViewById(R.id.imgbuttonplaysong);
        imgbuttonnextsong = findViewById(R.id.imgbuttonnextsong);
        imgbuttonrepeatsong = findViewById(R.id.imgbuttonrepeatsong);
    }

    private void GetIntent() {
        Intent intent = getIntent();
        dsbaihat.clear();
        if(intent != null)
        {
            if(intent.hasExtra("cakhuc"))
            {
                baihatyeuthich = intent.getParcelableExtra("cakhuc");
                dsbaihat.add(baihatyeuthich);
            }
            if(intent.hasExtra("mangbaihat"))
            {
                mangbaihatyeuthich = intent.getParcelableArrayListExtra("mangbaihat");
                dsbaihat = mangbaihatyeuthich;
            }

        }

    }
    class PlayMusic extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtviewtotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarsong.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTimeSong()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null)
                {
                    seekBarsong.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtviewtimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },3000);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next)
                {
                    if(positionforchangesong < dsbaihat.size())
                    {
                        imgbuttonplaysong.setImageResource(R.drawable.iconpause);
                        positionforchangesong++;
                        if(repeat)
                        {
                            if(positionforchangesong == 0)
                            {
                                positionforchangesong = dsbaihat.size();
                            }
                            positionforchangesong -=1;
                        }
                        if(checkrandom)
                        {
                            Random random = new Random();
                            int index = random.nextInt(dsbaihat.size());
                            if(index == positionforchangesong)
                            {
                                positionforchangesong = index-1;
                            }
                            positionforchangesong = index;
                        }
                        if(positionforchangesong > dsbaihat.size()-1)
                        {
                            positionforchangesong = 0;
                        }
                        new PlayMusic().execute(dsbaihat.get(positionforchangesong).getLinkBaiHat());
                        fragment_dia_nhac.Loadnhac(dsbaihat.get(positionforchangesong).getHinhBaiHat());
                        setValueInView(dsbaihat.get(positionforchangesong).getTenBaiHat(), dsbaihat.get(positionforchangesong).getHinhBaiHat());
                    }

                    imgbuttonpreviewsong.setClickable(false);
                    imgbuttonnextsong.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgbuttonpreviewsong.setClickable(true);
                            imgbuttonnextsong.setClickable(true);
                        }
                    },5000);
                    next = false;
                    handler1.removeCallbacks(this);
                }
                else
                {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}