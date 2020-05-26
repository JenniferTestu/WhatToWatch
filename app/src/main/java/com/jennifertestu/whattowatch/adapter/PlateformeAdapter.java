package com.jennifertestu.whattowatch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.model.Plateforme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlateformeAdapter extends RecyclerView.Adapter<PlateformeAdapter.MyViewHolder> {

    private List<Plateforme> mModelList;
    private ArrayList<String> selection;

    public PlateformeAdapter(Plateforme[] modelList, ArrayList<String> selection) {
        mModelList = Arrays.asList(modelList);
        this.selection = selection;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plateforme_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Plateforme model = mModelList.get(position);
        holder.imageView.setImageResource(model.getImage());
        holder.imageView.setTag(model.getCode());
        //if (selection.contains(holder.imageView.getTag()))
        holder.imageView.setAlpha(selection.contains(holder.imageView.getTag()) ? (1f) : (0.2f));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imageView.setAlpha(holder.imageView.getAlpha()==1f ? (0.2f) : (1f));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModelList == null ? 0 : mModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView imageView;

        private MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.icon);
        }
    }

}