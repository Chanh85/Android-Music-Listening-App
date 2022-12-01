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
import tdtu.finalterm520h0335_519h0189.Model.Album;
import tdtu.finalterm520h0335_519h0189.R;

public class DSAlbumAdapter extends RecyclerView.Adapter<DSAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public DSAlbumAdapter(Context context, ArrayList<Album> albumArrayList)
    {
        this.albumArrayList = albumArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dsalbum_sample, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        Picasso.get().load(album.getHinhAlbum()).into(holder.imgviewAlbum);
        holder.txtTenAlbum.setText(album.getTenAlbum());

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgviewAlbum;
        TextView txtTenAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgviewAlbum = itemView.findViewById(R.id.imgviewdsalbum);
            txtTenAlbum = itemView.findViewById(R.id.txtviewtendsalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ActivityHienBaiHat.class);
                    intent.putExtra("album", albumArrayList.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
