package tdtu.finalterm520h0335_519h0189.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tdtu.finalterm520h0335_519h0189.Activity.ActivityHienBaiHat;
import tdtu.finalterm520h0335_519h0189.Model.Playlist;
import tdtu.finalterm520h0335_519h0189.R;

public class DSPlaylistadapter extends RecyclerView.Adapter<DSPlaylistadapter.ViewHolder> {

    Context context;
    ArrayList<Playlist> playlists;

    public DSPlaylistadapter(Context context, ArrayList<Playlist> playlists)
    {
        this.context = context;
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dsplaylist_sample, parent, false);
        return new DSPlaylistadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Playlist playlist = playlists.get(position);
        Picasso.get().load(playlist.getHinhIcon()).into(holder.imgviewdsplaylist);
        holder.txtviewtendsplaylist.setText(playlist.getTenPlaylist());

    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgviewdsplaylist;
        TextView txtviewtendsplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgviewdsplaylist = itemView.findViewById(R.id.imgviewdsplaylist);
            txtviewtendsplaylist = itemView.findViewById(R.id.txtviewtendsplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ActivityHienBaiHat.class);
                    intent.putExtra("itemplaylist", playlists.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
