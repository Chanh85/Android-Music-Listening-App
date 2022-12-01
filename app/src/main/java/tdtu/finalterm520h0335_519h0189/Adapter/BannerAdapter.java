package tdtu.finalterm520h0335_519h0189.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tdtu.finalterm520h0335_519h0189.Activity.ActivityHienBaiHat;
import tdtu.finalterm520h0335_519h0189.Model.Quangcao;
import tdtu.finalterm520h0335_519h0189.R;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyViewHolder> {

    Context context;
    ArrayList<Quangcao> quangcaoArrayList;


    public BannerAdapter(Context context, ArrayList<Quangcao> quangcaoArrayList)
    {
        this.context = context;
        this.quangcaoArrayList = quangcaoArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.banner_sample,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(quangcaoArrayList.get(position).getHinhAnh()).into(holder.imgbackgroundbanner);
        Picasso.get().load(quangcaoArrayList.get(position).getHinhBaiHat()).into(holder.imgsongbanner);
        holder.titlesongbanner.setText(quangcaoArrayList.get(position).getTenBaiHat());
        holder.noidungbanner.setText(quangcaoArrayList.get(position).getNoiDung());

        holder.imgbackgroundbanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityHienBaiHat.class);
                intent.putExtra("Banner",quangcaoArrayList.get(position));
                context.startActivity(intent);
            }
        });

        holder.imgsongbanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityHienBaiHat.class);
                intent.putExtra("Banner",quangcaoArrayList.get(position));
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return quangcaoArrayList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgbackgroundbanner;
        private ImageView imgsongbanner;
        private TextView titlesongbanner;
        private TextView noidungbanner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbackgroundbanner = itemView.findViewById(R.id.imageviewbackgroundbanner);
            imgsongbanner = itemView.findViewById(R.id.banner_image);
            titlesongbanner = itemView.findViewById(R.id.textviewtitlebannerbaihat);
            noidungbanner = itemView.findViewById(R.id.noidungbanner);
        }
    }
}
