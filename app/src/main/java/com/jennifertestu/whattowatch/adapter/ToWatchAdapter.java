package com.jennifertestu.whattowatch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.model.Film;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ToWatchAdapter extends RecyclerView.Adapter<ToWatchAdapter.FilmViewHolder> {

    private Context context;
    private ArrayList<Film> listeFilms;

    public ToWatchAdapter(Context context, ArrayList<Film> listeFilms) {
        this.context = context;
        this.listeFilms = listeFilms;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.towatchitem, parent, false);

        return new FilmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        final Film film = listeFilms.get(position);
        holder.titre.setText(film.getTitre());
        Picasso.with(context).load("https://image.tmdb.org/t/p/" + "w780" +film.getUrlArrierePlan()).into(holder.thumbnail);
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

    public void restoreItem(Film item, int position) {
        listeFilms.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        public TextView titre, textVu, textSupp;
        public ImageView thumbnail,vuIcon,suppIcon;
        public RelativeLayout viewBackground, viewForeground;

        public FilmViewHolder(View view) {
            super(view);
            titre = view.findViewById(R.id.titre);
            thumbnail = view.findViewById(R.id.thumbnail);
            textVu = view.findViewById(R.id.text_vu);
            textSupp = view.findViewById(R.id.text_supp);
            vuIcon = view.findViewById(R.id.vu_icon);
            suppIcon = view.findViewById(R.id.delete_icon);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }
}
