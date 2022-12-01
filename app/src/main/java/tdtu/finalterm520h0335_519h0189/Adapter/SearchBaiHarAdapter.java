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

public class SearchBaiHarAdapter extends RecyclerView.Adapter<SearchBaiHarAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihatyeuthich> baihatyeuthichArrayList;

    public SearchBaiHarAdapter(Context context, ArrayList<Baihatyeuthich> baihatyeuthichArrayList) {
        this.context = context;
        this.baihatyeuthichArrayList = baihatyeuthichArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.searchbaihat_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihatyeuthich baihatyeuthich = baihatyeuthichArrayList.get(position);
        holder.txtTenbaihat.setText(baihatyeuthich.getTenBaiHat());
        holder.txtCasi.setText(baihatyeuthich.getCasi());
        Picasso.get().load(baihatyeuthich.getHinhBaiHat()).into(holder.imgbaihat);

    }

    @Override
    public int getItemCount() {
        return baihatyeuthichArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenbaihat, txtCasi;
        ImageView imgbaihat, imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.txtviewsearchtenbaihat);
            txtCasi = itemView.findViewById(R.id.txtviewsearchtencasi);
            imgbaihat = itemView.findViewById(R.id.imgviewsearchbaihat);
            imgluotthich = itemView.findViewById(R.id.imgviewsearchluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlaynhacActivity.class);
                    intent.putExtra("cakhuc", baihatyeuthichArrayList.get(getBindingAdapterPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.updateLuotThich("1", baihatyeuthichArrayList.get(getBindingAdapterPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success"))
                            {
                                Toast.makeText(context, "Đã yêu thích "+ baihatyeuthichArrayList.get(getBindingAdapterPosition()).getTenBaiHat(), Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}
