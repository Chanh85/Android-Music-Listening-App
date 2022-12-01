package tdtu.finalterm520h0335_519h0189.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tdtu.finalterm520h0335_519h0189.Activity.DStheloaitheochudeActivity;
import tdtu.finalterm520h0335_519h0189.Model.ChuDe;
import tdtu.finalterm520h0335_519h0189.Model.Playlist;
import tdtu.finalterm520h0335_519h0189.R;

public class DSChudeAdapter extends RecyclerView.Adapter<DSChudeAdapter.ViewHolder> {

    Context context;
    ArrayList<ChuDe> mangchude;

    public DSChudeAdapter(Context context, ArrayList<ChuDe> mangchude)
    {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dschude_sample, parent, false);
        return new DSChudeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = mangchude.get(position);
        Picasso.get().load(chuDe.getHinhChuDe()).into(holder.imgchude);


    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imgviewchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DStheloaitheochudeActivity.class);
                    intent.putExtra("chude", mangchude.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
