package tdtu.finalterm520h0335_519h0189.Adapter;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalterm520h0335_519h0189.Activity.PlaynhacActivity;
import tdtu.finalterm520h0335_519h0189.Model.Baihatyeuthich;
import tdtu.finalterm520h0335_519h0189.R;
import tdtu.finalterm520h0335_519h0189.Service.APIService;
import tdtu.finalterm520h0335_519h0189.Service.DataService;

public class BaihatFavAdapter extends RecyclerView.Adapter<BaihatFavAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihatyeuthich> baihatFavAdapterArrayList;


    public BaihatFavAdapter(Context context, ArrayList<Baihatyeuthich> baihatFavAdapterArrayList)
    {
        this.context = context;
        this.baihatFavAdapterArrayList = baihatFavAdapterArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.baihatfav_sample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihatyeuthich baihatyeuthich = baihatFavAdapterArrayList.get(position);
        holder.txtten.setText(baihatyeuthich.getTenBaiHat());
        holder.txtcasi.setText(baihatyeuthich.getCasi());
        Picasso.get().load(baihatyeuthich.getHinhBaiHat()).into(holder.imghinh);

    }

    @Override
    public int getItemCount() {
        return baihatFavAdapterArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten, txtcasi;
        ImageView imghinh, imgluotthich;
        public ViewHolder(View itemView)
        {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtenbaihatFav);
            txtcasi = itemView.findViewById(R.id.txttencasibaihatFav);
            imghinh = itemView.findViewById(R.id.imageviewbaihatFav);
            imgluotthich = itemView.findViewById(R.id.luotthich);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.updateLuotThich("1", baihatFavAdapterArrayList.get(getBindingAdapterPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String res= response.body();
                            if(res.equals("Success"))
                            {
                                Toast.makeText(context, "Đã yêu thích "+ baihatFavAdapterArrayList.get(getBindingAdapterPosition()).getTenBaiHat(), Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(context, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlaynhacActivity.class);
                    intent.putExtra("cakhuc", baihatFavAdapterArrayList.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
