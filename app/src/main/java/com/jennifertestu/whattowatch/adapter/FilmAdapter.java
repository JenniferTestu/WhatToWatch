package com.jennifertestu.whattowatch.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.model.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends ArrayAdapter<Film> {

    Context context;

    public FilmAdapter(Context context, int resourceId, ArrayList<Film> films) {
        super(context, resourceId, films);
        Log.e("Taille", String.valueOf(films.size()));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Film film_item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.titre);
        final TextView longue_description = (TextView) convertView.findViewById(R.id.longue_description);
        ImageView bouton = (ImageView)convertView.findViewById(R.id.info);
        ImageView image = (ImageView) convertView.findViewById(R.id.affiche);

        name.setText(film_item.getTitre());
        longue_description.setText(film_item.getLongueDesc());
        Picasso.with(convertView.getContext()).load("https://image.tmdb.org/t/p/" + "w780" +film_item.getUrlAffiche()).into(image);
        //Picasso.with(convertView.getContext()).load(film_item.getUrlAffiche()).into(image);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(longue_description.getVisibility()==View.INVISIBLE){
                    longue_description.setVisibility(View.VISIBLE);
                }else {
                    longue_description.setVisibility(View.INVISIBLE);
                }
            }
        });

/*
        Log.e("Id",film_item.getIdImdb());
        Log.e("Titre",film_item.getTitre());
        Log.e("Url",film_item.getUrlAffiche());
*/
        /*
        switch (film_item.getUrlAffiche()) {
            case "default":
                Picasso.with(context).load(R.mipmap.ic_launcher).into(image);
                //Glide.with(convertView.getContext()).load(R.mipmap.ic_launcher).into(image);
                break;
            default:
                //Glide.clear(image);
                Picasso.with(context).load("https://cdn.journaldev.com/wp-content/uploads/2016/11/android-image-picker-project-structure.png").into(image);
                //Glide.with(convertView.getContext()).load(film_item.getUrlAffiche()).into(image);
                break;
        }
*/
        return convertView;
    }
}
