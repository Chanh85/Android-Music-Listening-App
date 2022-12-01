package tdtu.finalterm520h0335_519h0189.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalterm520h0335_519h0189.Adapter.DSPlaylistadapter;
import tdtu.finalterm520h0335_519h0189.Adapter.DStheloaitheochudeAdapter;
import tdtu.finalterm520h0335_519h0189.Model.ChuDe;
import tdtu.finalterm520h0335_519h0189.Model.Playlist;
import tdtu.finalterm520h0335_519h0189.Model.TheLoai;
import tdtu.finalterm520h0335_519h0189.R;
import tdtu.finalterm520h0335_519h0189.Service.APIService;
import tdtu.finalterm520h0335_519h0189.Service.DataService;

public class DStheloaitheochudeActivity extends AppCompatActivity {

    ChuDe chuDe;
    Toolbar toolbar;
    RecyclerView recyclerView;
    DStheloaitheochudeAdapter dStheloaitheochudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dstheloaitheochude);
        GetIntent();
        Mapping();
        init();
        GetData();


    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.GetDstheloaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai = (ArrayList<TheLoai>) response.body();
                dStheloaitheochudeAdapter = new DStheloaitheochudeAdapter(DStheloaitheochudeActivity.this, mangtheloai);
                recyclerView.setLayoutManager(new GridLayoutManager(DStheloaitheochudeActivity.this,2));
                recyclerView.setAdapter(dStheloaitheochudeAdapter);

            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }


    private void Mapping() {
        toolbar = findViewById(R.id.toolbardstheloai);
        recyclerView = findViewById(R.id.recyclerviewdstheloai);
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude"))
        {
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
        }
    }
    private void init()
    {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}