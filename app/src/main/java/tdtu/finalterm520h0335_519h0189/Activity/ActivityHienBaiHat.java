package tdtu.finalterm520h0335_519h0189.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalterm520h0335_519h0189.Adapter.Danhsachbaihatadapter;
import tdtu.finalterm520h0335_519h0189.Model.Album;
import tdtu.finalterm520h0335_519h0189.Model.Baihatyeuthich;
import tdtu.finalterm520h0335_519h0189.Model.Playlist;
import tdtu.finalterm520h0335_519h0189.Model.Quangcao;
import tdtu.finalterm520h0335_519h0189.Model.TheLoai;
import tdtu.finalterm520h0335_519h0189.R;
import tdtu.finalterm520h0335_519h0189.Service.APIService;
import tdtu.finalterm520h0335_519h0189.Service.DataService;

public class ActivityHienBaiHat extends AppCompatActivity {

    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ImageView imgviewdsbaihat;
    ArrayList<Baihatyeuthich> baihatyeuthichArrayList;
    Danhsachbaihatadapter danhsachbaihatadapter;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Mapping();
        DataIntent();
        init();
        if(quangcao != null && !quangcao.getTenBaiHat().equals(""))
        {
            setValueinView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangCao(quangcao.getIdQuangCao());
        }
        if(playlist != null && !playlist.getTenPlaylist().equals(""))
        {
            setValueinView(playlist.getTenPlaylist(), playlist.getHinhIcon());
            GetDataPlaylist(playlist.getIdPlaylist());
        }
        if(theLoai != null && !theLoai.getTenTheLoai().equals(""))
        {
            setValueinView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheloai());
        }
        if(album != null && !album.getTenAlbum().equals(""))
        {
            setValueinView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<Baihatyeuthich>> callback = dataService.GetDsbaihattheoalbum(idAlbum);
        callback.enqueue(new Callback<List<Baihatyeuthich>>() {
            @Override
            public void onResponse(Call<List<Baihatyeuthich>> call, Response<List<Baihatyeuthich>> response) {
                baihatyeuthichArrayList = (ArrayList<Baihatyeuthich>) response.body();
                danhsachbaihatadapter = new Danhsachbaihatadapter(ActivityHienBaiHat.this, baihatyeuthichArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ActivityHienBaiHat.this));
                recyclerView.setAdapter(danhsachbaihatadapter);
                fltBtnEventClick();
            }

            @Override
            public void onFailure(Call<List<Baihatyeuthich>> call, Throwable t) {

            }
        });

    }

    private void GetDataTheLoai(String idTheloai) {
        DataService dataService = APIService.getService();
        Call<List<Baihatyeuthich>> callback = dataService.GetDsBaiHatTheoTheLoai(idTheloai);
        callback.enqueue(new Callback<List<Baihatyeuthich>>() {
            @Override
            public void onResponse(Call<List<Baihatyeuthich>> call, Response<List<Baihatyeuthich>> response) {
                baihatyeuthichArrayList = (ArrayList<Baihatyeuthich>) response.body();
                danhsachbaihatadapter = new Danhsachbaihatadapter(ActivityHienBaiHat.this, baihatyeuthichArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ActivityHienBaiHat.this));
                recyclerView.setAdapter(danhsachbaihatadapter);
                fltBtnEventClick();
            }

            @Override
            public void onFailure(Call<List<Baihatyeuthich>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
        DataService dataService = APIService.getService();
        Call<List<Baihatyeuthich>> callback = dataService.GetDsBaiHatTheoPlaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihatyeuthich>>() {
            @Override
            public void onResponse(Call<List<Baihatyeuthich>> call, Response<List<Baihatyeuthich>> response) {
                baihatyeuthichArrayList = (ArrayList<Baihatyeuthich>) response.body();
                danhsachbaihatadapter = new Danhsachbaihatadapter(ActivityHienBaiHat.this, baihatyeuthichArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ActivityHienBaiHat.this));
                recyclerView.setAdapter(danhsachbaihatadapter);
                fltBtnEventClick();
            }

            @Override
            public void onFailure(Call<List<Baihatyeuthich>> call, Throwable t) {

            }
        });

    }

    private void GetDataQuangCao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<Baihatyeuthich>> callback = dataService.GetDsBaiHatTheoBanner(idquangcao);
        callback.enqueue(new Callback<List<Baihatyeuthich>>() {
           @Override
           public void onResponse(Call<List<Baihatyeuthich>> call, Response<List<Baihatyeuthich>> response) {
               baihatyeuthichArrayList = (ArrayList<Baihatyeuthich>) response.body();
               danhsachbaihatadapter = new Danhsachbaihatadapter(ActivityHienBaiHat.this,baihatyeuthichArrayList);
               recyclerView.setLayoutManager(new LinearLayoutManager(ActivityHienBaiHat.this));
               recyclerView.setAdapter(danhsachbaihatadapter);
               fltBtnEventClick();

           }

           @Override
           public void onFailure(Call<List<Baihatyeuthich>> call, Throwable t) {

           }
       });

    }

    private void setValueinView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL(hinh);
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            collapsingToolbarLayout.setBackground(bitmapDrawable);
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
        Picasso.get().load(hinh).into(imgviewdsbaihat);
    }

    private void init()
    {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);

    }

    private void Mapping() {
        collapsingToolbarLayout = findViewById(R.id.collapsetoolbar);
        coordinatorLayout = findViewById(R.id.coordinatinglayout);
        collapsingToolbarLayout = findViewById(R.id.collapsetoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        floatingActionButton = findViewById(R.id.floatingplaybtn);
        recyclerView = findViewById(R.id.recyclerviewdsbaihat);
        imgviewdsbaihat = findViewById(R.id.imgviewdsbaihat);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null)
        {
            if(intent.hasExtra("Banner"))
            {
                quangcao =(Quangcao) intent.getSerializableExtra("Banner");

            }
            if(intent.hasExtra("itemplaylist"))
            {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("idtheloai"))
            {
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album"))
            {
                album = (Album) intent.getSerializableExtra("album");
            }
        }

    }

    private void fltBtnEventClick()
    {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHienBaiHat.this, PlaynhacActivity.class);
                intent.putExtra("mangbaihat", baihatyeuthichArrayList);
                startActivity(intent);
            }
        });
    }
}