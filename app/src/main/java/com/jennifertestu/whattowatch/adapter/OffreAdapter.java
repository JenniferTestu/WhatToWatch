package com.jennifertestu.whattowatch.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.model.Offre;
import com.jennifertestu.whattowatch.model.Plateforme;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OffreAdapter extends RecyclerView.Adapter<OffreAdapter.OffreViewHolder>{


    private Context context;
    private List<Offre> listeFilms;

    public OffreAdapter(Context context, List<Offre> listeFilms) {
        this.context = context;
        this.listeFilms = listeFilms;
    }

    @NonNull
    @Override
    public OffreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offre, parent, false);

        return new OffreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OffreViewHolder holder, int position) {
        final Offre o = listeFilms.get(position);
        holder.prix.setText(String.valueOf(o.getRetailPrice())+"\u20ac");
        holder.format.setText(o.getPresentationType());
        Picasso.with(context).load(Plateforme.getById(o.getProviderId()).getImage()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return listeFilms.size();
    }

    public void removeItem(int position) {
        listeFilms.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Offre item, int position) {
        listeFilms.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    public class OffreViewHolder extends RecyclerView.ViewHolder {
        public TextView prix, format;
        public ImageView icon;

        public OffreViewHolder(View view) {
            super(view);
            prix = view.findViewById(R.id.prix);
            format = view.findViewById(R.id.format);
            icon = view.findViewById(R.id.icon);

        }
    }
}
