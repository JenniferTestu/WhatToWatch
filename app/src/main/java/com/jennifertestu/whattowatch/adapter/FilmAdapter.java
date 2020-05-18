package com.jennifertestu.whattowatch.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.model.Offre;
import com.jennifertestu.whattowatch.model.Plateforme;
import com.jennifertestu.whattowatch.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class FilmAdapter extends ArrayAdapter<Film>  {

    private Context context;
    private boolean expand=false;
    Button btShowmore;
    TextView longue_description;

    public FilmAdapter(Context context, int resourceId, ArrayList<Film> films) {
        super(context, resourceId, films);
        this.context=context;
        Log.e("Taille à la crea", String.valueOf(films.size()));
    }

    //@RequiresApi(api = Build.VERSION_CODES.N)
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Film film_item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }

        // Element de la carte
        ImageView image = (ImageView) convertView.findViewById(R.id.affiche);
        ImageView bouton = (ImageView)convertView.findViewById(R.id.info);
        LinearLayout divers = (LinearLayout)convertView.findViewById(R.id.divers);

        // Info supplémentaires cachées
        final ScrollView plus_info = (ScrollView) convertView.findViewById(R.id.plus_info);
        ImageView miniature = (ImageView) convertView.findViewById(R.id.affiche_miniature);
        TextView name = (TextView) convertView.findViewById(R.id.info_titre);
        TextView date = (TextView) convertView.findViewById(R.id.info_date);
        TextView categories = (TextView) convertView.findViewById(R.id.info_categories);
        TextView real = (TextView) convertView.findViewById(R.id.info_real);
        longue_description = (TextView) convertView.findViewById(R.id.longue_description);

        // Remplissage des info
        //Picasso.with(convertView.getContext()).load("https://image.tmdb.org/t/p/" + "w500" +film_item.getUrlAffiche()).into(miniature);
        name.setText(film_item.getTitre());

        // Liste les videos
        for (final Video video: film_item.getListeVideos()) {
            TextView tv = new TextView(context);
            tv.setText(video.getType());
            tv.setPadding(30,0,0,0);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://www.youtube.com/watch?v="+video.getKey()));
                    i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
            //tv.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.id.ic),null,null,null);
            divers.addView(tv);
        }

        // Format de la date
        SimpleDateFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date tostring =null;
            try {
                tostring = formatter.parse(film_item.getDate());
            } catch (ParseException | java.text.ParseException e) {
                e.printStackTrace();
            }
            formatter = new SimpleDateFormat("dd MMMM yyyy",Locale.FRENCH);
            date.setText("Sortie le "+formatter.format(tostring));

        }



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

        makeTextViewResizable(longue_description,5,"Continuer",true);

        // Les offres
        OffreAdapter recyclerAdapterStreaming;
        OffreAdapter recyclerAdapterLocation;
        OffreAdapter recyclerAdapterAchat;

        RecyclerView recyclerViewStreaming = convertView.findViewById(R.id.streaming);
        RecyclerView recyclerViewLocation = convertView.findViewById(R.id.location);
        RecyclerView recyclerViewAchat = convertView.findViewById(R.id.achat);

        TextView texteStreaming = convertView.findViewById(R.id.text_streaming);
        TextView texteLocation = convertView.findViewById(R.id.text_location);
        TextView texteAchat = convertView.findViewById(R.id.text_achat);

        ArrayList<Offre> listeStreaming = new ArrayList<Offre>();
        ArrayList<Offre> listeLocation = new ArrayList<Offre>();
        ArrayList<Offre> listeAchat = new ArrayList<Offre>();

        Collections.sort(film_item.getListeOffres(), new Comparator<Offre>()
        {
            @Override
            public int compare(Offre o1, Offre o2) {

                return Double.valueOf(o1.getRetailPrice()).compareTo(o2.getRetailPrice());
            }
        });

        Collections.sort(film_item.getListeOffres(), new Comparator<Offre>()
        {
            @Override
            public int compare(Offre o1, Offre o2) {

                return Integer.valueOf(Plateforme.getById(o1.getProviderId()).getPlace()).compareTo(Plateforme.getById(o2.getProviderId()).getPlace());
            }
        });

        for (Offre o:film_item.getListeOffres()) {
            if(o.getMonetizationType().equalsIgnoreCase("flatrate")){
                if (texteStreaming.getVisibility()==View.GONE)
                    texteStreaming.setVisibility(View.VISIBLE);
                listeStreaming.add(o);
            }else if(o.getMonetizationType().equalsIgnoreCase("rent")){
                if (texteLocation.getVisibility()==View.GONE)
                    texteLocation.setVisibility(View.VISIBLE);
                listeLocation.add(o);
            }else if(o.getMonetizationType().equalsIgnoreCase("buy")){
                if (texteAchat.getVisibility()==View.GONE)
                    texteAchat.setVisibility(View.VISIBLE);
                listeAchat.add(o);
            }
        }

        recyclerAdapterStreaming = new OffreAdapter(getContext(), listeStreaming);
        recyclerAdapterLocation = new OffreAdapter(getContext(), listeLocation);
        recyclerAdapterAchat = new OffreAdapter(getContext(), listeAchat);

        recyclerViewStreaming.setAdapter(recyclerAdapterStreaming);
        recyclerViewLocation.setAdapter(recyclerAdapterLocation);
        recyclerViewAchat.setAdapter(recyclerAdapterAchat);


        // Récupération de l'affiche
        Picasso.with(convertView.getContext()).load("https://image.tmdb.org/t/p/" + "w780" +film_item.getUrlAffiche()).into(image);
        miniature.setImageDrawable(image.getDrawable());

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



        return convertView;
    }


    public void makeTextViewResizable(final TextView tv,
                                             final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    Log.e("Message","1");
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0,
                            lineEndIndex - expandText.length() + 1)
                            + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(tv.getText()
                                            .toString(), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    Log.e("Message","2");

                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0,
                            lineEndIndex - expandText.length() + 1)
                            + "... " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(tv.getText()
                                            .toString(), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    Log.e("Message","3");

                    int lineEndIndex = tv.getLayout().getLineEnd(
                            tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex)
                            + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(tv.getText()
                                            .toString(), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private SpannableStringBuilder addClickablePartTextViewResizable(
            final String strSpanned, final TextView tv, final int maxLine,
            final String spanableText, final boolean viewMore) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (strSpanned.contains(spanableText)) {
            Log.e("Message","4");

            ssb.setSpan(
                    new ClickableSpan() {

                        @Override
                        public void onClick(View widget) {

                            if (viewMore) {
                                Log.e("Message","5");

                                tv.setLayoutParams(tv.getLayoutParams());
                                tv.setText(tv.getTag().toString(),
                                        TextView.BufferType.SPANNABLE);
                                tv.invalidate();
                                makeTextViewResizable(tv, -5, "Voir moins",
                                        false);
                                tv.setTextColor(Color.BLACK);
                                notifyDataSetInvalidated();
                            } else {
                                Log.e("Message","6");

                                tv.setLayoutParams(tv.getLayoutParams());
                                tv.setText(tv.getTag().toString(),
                                        TextView.BufferType.SPANNABLE);
                                tv.invalidate();
                                makeTextViewResizable(tv, 5, "Continuer",
                                        true);
                                tv.setTextColor(Color.BLACK);
                                notifyDataSetInvalidated();
                            }

                        }
                    }, strSpanned.indexOf(spanableText),
                    strSpanned.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }
}
