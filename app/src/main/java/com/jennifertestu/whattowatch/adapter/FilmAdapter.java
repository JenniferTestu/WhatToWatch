package com.jennifertestu.whattowatch.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.model.Genre;
import com.jennifertestu.whattowatch.model.Offre;
import com.jennifertestu.whattowatch.model.Plateforme;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Film>  {

    private Context context;

    public FilmAdapter(Context context, int resourceId, ArrayList<Film> films) {
        super(context, resourceId, films);
        Log.e("Taille à la crea", String.valueOf(films.size()));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Film film_item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        // Element de la carte
        ImageView image = (ImageView) convertView.findViewById(R.id.affiche);
        LinearLayout offres = (LinearLayout) convertView.findViewById(R.id.offres);
        ImageView bouton = (ImageView)convertView.findViewById(R.id.info);

        // Info supplémentaires cachées
        final ScrollView plus_info = (ScrollView) convertView.findViewById(R.id.plus_info);
        TextView name = (TextView) convertView.findViewById(R.id.info_titre);
        TextView date = (TextView) convertView.findViewById(R.id.info_date);
        TextView categories = (TextView) convertView.findViewById(R.id.info_categories);
        TextView real = (TextView) convertView.findViewById(R.id.info_real);
        TextView longue_description = (TextView) convertView.findViewById(R.id.longue_description);

        // Remplissage des info
        name.setText(film_item.getTitre());
        date.setText("Sortie le "+film_item.getDate());

        categories.setText("");
        if(film_item.getGenres()!=null) {
            for (String g : film_item.getGenres()) {
                categories.append(g + " ");
            }
        }


        if(film_item.getCredits()!=null) {
            real.setText("Réalisé par " + film_item.getRealisateurs());
        }


        longue_description.setText(film_item.getLongueDesc());

        // Récupération de l'affiche
        Picasso.with(convertView.getContext()).load("https://image.tmdb.org/t/p/" + "w780" +film_item.getUrlAffiche()).into(image);
        //Picasso.with(convertView.getContext()).load(film_item.getUrlAffiche()).into(image);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(plus_info.getVisibility()==View.INVISIBLE){
                    plus_info.setVisibility(View.VISIBLE);
                }else {
                    plus_info.setVisibility(View.INVISIBLE);
                }
            }
        });

        // Liste des offres
        Log.e(film_item.getTitre(), "Adapter Liste Null ? "+ String.valueOf(film_item.getListeOffres()==null));
        Log.e(film_item.getTitre(), "Adapter Liste Empty ? "+ String.valueOf(film_item.getListeOffres().isEmpty()));

        if(film_item.getListeOffres()!=null && film_item.getListeOffres().isEmpty()==false){
            for (Offre o:film_item.getListeOffres()) {
                Log.e(film_item.getTitre(), String.valueOf(o.getProviderId()));
                Log.e(film_item.getTitre(), Plateforme.getById(o.getProviderId()).getNom());
                //offres.append(Plateforme.getById(o.getProviderId()).getNom()+", ");
                ImageView icon= new ImageView(getContext());
                icon.setImageResource(Plateforme.getById(o.getProviderId()).getImage());
                offres.addView(icon);
            }
        }
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
