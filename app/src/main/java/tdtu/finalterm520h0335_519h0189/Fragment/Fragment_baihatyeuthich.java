package tdtu.finalterm520h0335_519h0189.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import tdtu.finalterm520h0335_519h0189.Adapter.BaihatFavAdapter;
import tdtu.finalterm520h0335_519h0189.Model.Baihatyeuthich;
import tdtu.finalterm520h0335_519h0189.R;
import tdtu.finalterm520h0335_519h0189.Service.APIService;
import tdtu.finalterm520h0335_519h0189.Service.DataService;

public class Fragment_baihatyeuthich extends Fragment {

    View view;
    RecyclerView recyclerViewbaihatyeuthich;
    BaihatFavAdapter baihatFavAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_baihetyeuthich,container,false);
         Mapping();
         GetData();
         return view;
    }

    private void Mapping() {

        recyclerViewbaihatyeuthich = view.findViewById(R.id.recyclerviewbaihathot);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Baihatyeuthich>> callback = dataService.getFavSongs();
        callback.enqueue(new Callback<List<Baihatyeuthich>>() {
            @Override
            public void onResponse(Call<List<Baihatyeuthich>> call, Response<List<Baihatyeuthich>> response) {
                ArrayList<Baihatyeuthich> baihatyeuthichArrayList = (ArrayList<Baihatyeuthich>) response.body();
                baihatFavAdapter = new BaihatFavAdapter(getActivity(),baihatyeuthichArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihatyeuthich.setLayoutManager(linearLayoutManager);
                recyclerViewbaihatyeuthich.setAdapter(baihatFavAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihatyeuthich>> call, Throwable t) {

            }
        });

    }
}
