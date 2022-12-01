package tdtu.finalterm520h0335_519h0189.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.finalterm520h0335_519h0189.Activity.PlaynhacActivity;
import tdtu.finalterm520h0335_519h0189.Adapter.DSplaysongadapter;
import tdtu.finalterm520h0335_519h0189.R;

public class Fragment_playdsbaihat extends Fragment {

    View view;
    RecyclerView recyclerView;
    DSplaysongadapter dSplaysongadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playdsbaihat,container,false);
        recyclerView = view.findViewById(R.id.recyclerviewdsplaybaihat);
        if (PlaynhacActivity.dsbaihat.size() > 0)
        {
            dSplaysongadapter = new DSplaysongadapter(getActivity(), PlaynhacActivity.dsbaihat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(dSplaysongadapter);
        }
        return view;
    }
}
