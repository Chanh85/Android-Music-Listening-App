package tdtu.finalterm520h0335_519h0189.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalterm520h0335_519h0189.Adapter.BaihatFavAdapter;
import tdtu.finalterm520h0335_519h0189.Adapter.SearchBaiHarAdapter;
import tdtu.finalterm520h0335_519h0189.Model.Baihatyeuthich;
import tdtu.finalterm520h0335_519h0189.R;
import tdtu.finalterm520h0335_519h0189.Service.APIService;
import tdtu.finalterm520h0335_519h0189.Service.DataService;

public class Fragment_TimKiem extends Fragment {

    View view;
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView textView;
    SearchBaiHarAdapter searchBaiHarAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        Mapping();
        return view;
    }

    private void Mapping() {
        toolbar = view.findViewById(R.id.toolbarsearchbaihat);
        recyclerView = view.findViewById(R.id.recyclerviewsearchbaihat);
        textView = view.findViewById(R.id.txtnoresult);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_baihat,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchTukhoaBaihat(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void searchTukhoaBaihat(String query)
    {
        DataService dataService = APIService.getService();
        Call<List<Baihatyeuthich>> callback = dataService.dataDSBaihat(query);
        callback.enqueue(new Callback<List<Baihatyeuthich>>() {
            @Override
            public void onResponse(Call<List<Baihatyeuthich>> call, Response<List<Baihatyeuthich>> response) {
                ArrayList<Baihatyeuthich> baihatyeuthichArrayList = (ArrayList<Baihatyeuthich>) response.body();
                if(baihatyeuthichArrayList.size() > 0)
                {
                    searchBaiHarAdapter = new SearchBaiHarAdapter(getActivity(), baihatyeuthichArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(searchBaiHarAdapter);
                    textView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                else
                {
                    textView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Baihatyeuthich>> call, Throwable t) {

            }
        });
    }
}
