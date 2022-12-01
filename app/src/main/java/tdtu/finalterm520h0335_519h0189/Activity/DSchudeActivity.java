package tdtu.finalterm520h0335_519h0189.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalterm520h0335_519h0189.Adapter.DSChudeAdapter;
import tdtu.finalterm520h0335_519h0189.Adapter.DSPlaylistadapter;
import tdtu.finalterm520h0335_519h0189.Model.ChuDe;
import tdtu.finalterm520h0335_519h0189.Model.Playlist;
import tdtu.finalterm520h0335_519h0189.R;
import tdtu.finalterm520h0335_519h0189.Service.APIService;
import tdtu.finalterm520h0335_519h0189.Service.DataService;

public class DSchudeActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    DSChudeAdapter dsChudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dschude);
        Mapping();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.getAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangpchude = (ArrayList<ChuDe>) response.body();
                dsChudeAdapter = new DSChudeAdapter(DSchudeActivity.this,mangpchude);
                recyclerView.setLayoutManager(new GridLayoutManager(DSchudeActivity.this,1));
                recyclerView.setAdapter(dsChudeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void Mapping() {
        toolbar = findViewById(R.id.toolbardschude);
        recyclerView = findViewById(R.id.recyclerviewdschude);
    }

    private void init()
    {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}