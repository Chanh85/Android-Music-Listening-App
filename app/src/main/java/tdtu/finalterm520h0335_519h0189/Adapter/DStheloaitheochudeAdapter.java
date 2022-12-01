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
import tdtu.finalterm520h0335_519h0189.Model.TheLoai;
import tdtu.finalterm520h0335_519h0189.R;

public class DStheloaitheochudeAdapter extends RecyclerView.Adapter<DStheloaitheochudeAdapter.ViewHolder>  {

    Context context;
    ArrayList<TheLoai> arrayListtheloai;

    public DStheloaitheochudeAdapter(Context context, ArrayList<TheLoai>arrayList)
    {
        this.context = context;
        this.arrayListtheloai = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.theloaitheochude_sample, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = arrayListtheloai.get(position);
        Picasso.get().load(theLoai.getHinhTheLoai()).into(holder.imagetheloai);
        holder.tentheoloai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return arrayListtheloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imagetheloai;
        TextView tentheoloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagetheloai = itemView.findViewById(R.id.imgviewtheloaitheochude);
            tentheoloai = itemView.findViewById(R.id.txtviewtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ActivityHienBaiHat.class);
                    intent.putExtra("idtheloai", arrayListtheloai.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
