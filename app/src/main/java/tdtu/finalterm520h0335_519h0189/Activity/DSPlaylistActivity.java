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
import tdtu.finalterm520h0335_519h0189.Adapter.DSPlaylistadapter;
import tdtu.finalterm520h0335_519h0189.Model.Playlist;
import tdtu.finalterm520h0335_519h0189.R;
import tdtu.finalterm520h0335_519h0189.Service.APIService;
import tdtu.finalterm520h0335_519h0189.Service.DataService;

public class DSPlaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    DSPlaylistadapter dsPlaylistadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsplaylist);
        Mapping();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.getAllPlaylists();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                dsPlaylistadapter = new DSPlaylistadapter(DSPlaylistActivity.this, mangplaylist);
                recyclerView.setLayoutManager(new GridLayoutManager(DSPlaylistActivity.this,2));
                recyclerView.setAdapter(dsPlaylistadapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void Mapping() {
        toolbar = findViewById(R.id.toolbardsplaylist);
        recyclerView = findViewById(R.id.recyclerviewdsplaylist);
    }

    private void init()
    {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}