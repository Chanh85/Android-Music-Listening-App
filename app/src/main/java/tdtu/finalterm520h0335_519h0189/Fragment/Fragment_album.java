package tdtu.finalterm520h0335_519h0189.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalterm520h0335_519h0189.Activity.DStatcaalbumActivity;
import tdtu.finalterm520h0335_519h0189.Adapter.AlbumAdapter;
import tdtu.finalterm520h0335_519h0189.Model.Album;
import tdtu.finalterm520h0335_519h0189.R;
import tdtu.finalterm520h0335_519h0189.Service.APIService;
import tdtu.finalterm520h0335_519h0189.Service.DataService;

public class Fragment_album extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView txtxemthemalbum;
    AlbumAdapter albumAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container,false);
        Mapping();
        GetData();
        return view;
    }

    private void Mapping() {
        recyclerView = view.findViewById(R.id.recyclerviewalbum);
        txtxemthemalbum = view.findViewById(R.id.txtxemthemalbum);
        txtxemthemalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DStatcaalbumActivity.class);
                startActivity(intent);
            }
        });

    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.getAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum = (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(),mangalbum);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(albumAdapter);


            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
