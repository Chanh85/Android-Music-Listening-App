package tdtu.finalterm520h0335_519h0189.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tdtu.finalterm520h0335_519h0189.Model.Baihatyeuthich;
import tdtu.finalterm520h0335_519h0189.R;

public class DSplaysongadapter extends RecyclerView.Adapter<DSplaysongadapter.ViewHolder>{

    Context context;
    ArrayList<Baihatyeuthich> baihatyeuthichArrayList;

    public DSplaysongadapter(Context context, ArrayList<Baihatyeuthich> baihatyeuthichArrayList)
    {
        this.context =context;
        this.baihatyeuthichArrayList = baihatyeuthichArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.playdssong_sample,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihatyeuthich baihatyeuthich = baihatyeuthichArrayList.get(position);
        holder.txtcasi.setText(baihatyeuthich.getCasi());
        holder.txttenbaihat.setText(baihatyeuthich.getTenBaiHat());
        holder.txtindex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return baihatyeuthichArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtindex, txttenbaihat, txtcasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.txtviewplaysongindex);
            txttenbaihat = itemView.findViewById(R.id.txtviewplaysongtenbaihat);
            txtcasi = itemView.findViewById(R.id.txtviewtencasidsplaysong);
        }
    }
}
