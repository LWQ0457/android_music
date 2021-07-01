package com.lwq.music;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    ArrayList<Integer> id_data;
    ArrayList<Bitmap> b_data;
    ArrayList<String> t_data;

    public BannerAdapter(ArrayList<Bitmap> b_data, ArrayList<String> t_data,ArrayList<Integer> ids ) {
        this.b_data = b_data;
        this.t_data = t_data;
        id_data=ids;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewHolder holder, int position) {
        position=position%Math.min(t_data.size(),b_data.size());
        holder.textView.setText(t_data.get(position));
        holder.imageView.setImageBitmap(b_data.get(position));
        holder.id=id_data.get(position);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Integer id;
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.banner_image);
            textView=itemView.findViewById(R.id.banner_tv);
            imageView.setOnClickListener(v -> Log.e("asdfnawe",""+this.id));
        }
    }

}
