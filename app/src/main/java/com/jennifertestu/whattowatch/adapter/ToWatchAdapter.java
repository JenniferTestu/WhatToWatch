package com.jennifertestu.whattowatch.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.activity.WishlistActivity;
import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.model.Genre;
import com.jennifertestu.whattowatch.model.Offre;
import com.jennifertestu.whattowatch.model.Plateforme;
import com.jennifertestu.whattowatch.model.Video;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ToWatchAdapter extends RecyclerView.Adapter<ToWatchAdapter.FilmViewHolder> implements ActionMode.Callback {

    private Context context;
    private ArrayList<Film> listeFilms;
    private AppCompatActivity activity;

    private boolean multiSelect = false;
    private ArrayList<Film> selectedItems = new ArrayList<Film>();

    public ToWatchAdapter(AppCompatActivity activity, Context context, ArrayList<Film> listeFilms) {
        this.context = context;
        this.listeFilms = listeFilms;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.towatchitem, parent, false);

        return new FilmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilmViewHolder holder, int position) {

        final Film film = listeFilms.get(position);
        if(film.getType().matches("show")) {
            holder.titre.setText(film.getNomSerie());
        }else {
            holder.titre.setText(film.getTitre());
        }
        Picasso.get().load("https://image.tmdb.org/t/p/" + "w780" +film.getUrlArrierePlan()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!multiSelect) {
                    if (holder.plus_info.getVisibility() == View.GONE) {
                        //holder.plus_info.setVisibility(View.VISIBLE);
                        TranslateAnimation animate = new TranslateAnimation(0, 0, -holder.plus_info.getHeight(), 0);
                        animate.setDuration(500);
                        animate.setFillAfter(true);
                        holder.plus_info.startAnimation(animate);
                        holder.plus_info.setVisibility(View.VISIBLE);
                    } else if (holder.plus_info.getVisibility() == View.VISIBLE) {
                        //holder.plus_info.setVisibility(View.GONE);
                        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, holder.plus_info.getHeight());
                        animate.setDuration(500);
                        animate.setFillAfter(true);
                        holder.plus_info.startAnimation(animate);
                        holder.plus_info.setVisibility(View.GONE);
                    }
                }else if(multiSelect){
                    selectItem(holder, film);
                }
            }
        });

        if (selectedItems.contains(film)) {
            // if the item is selected, let the user know by adding a dark layer above it
            holder.thumbnail.setAlpha(0.3f);
        } else {
            // else, keep it as it is
            holder.thumbnail.setAlpha(1.0f);
        }

        holder.thumbnail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // if multiSelect is false, set it to true and select the item
                if (!multiSelect) {
                    // We have started multi selection, so set the flag to true
                    multiSelect = true;
                    // Add it to the list containing all the selected images
                    activity.startSupportActionMode(ToWatchAdapter.this);
                    selectItem(holder, film);

                    return true;

                }else {
                    return false;
                }

            }
        });

        // Remplissage des info
        //Picasso.with(convertView.getContext()).load("https://image.tmdb.org/t/p/" + "w500" +film_item.getUrlAffiche()).into(miniature);
        Picasso.get()
                .load("https://image.tmdb.org/t/p/" + "w780" +film.getUrlAffiche())
                .into(holder.miniature,
                        new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                Log.e("Image","Echec");

                            }

                        });

        // Liste les videos
        /*
        for (final Video video: film.getListeVideos().getResults()) {
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
            holder.divers.addView(tv);
        }
*/
        // Liste les 4 premieres videos qui sont classées de Trailer, Extrait, Clip
        Collections.sort(film.getListeVideos().getResults(), new Comparator<Video>()
        {
            @Override
            public int compare(Video v1, Video v2) {
                return (v2.getType()).compareTo(v1.getType());
            }
        });
        int i = 0;
        for (final Video video: film.getListeVideos().getResults()) {
            if (i<4) {
                TextView tv = new TextView(context);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.width = 0;
                layout.weight = 1;
                tv.setLayoutParams(layout);
                tv.setText(video.getType());
                tv.setPadding(30, 0, 0, 0);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://www.youtube.com/watch?v=" + video.getKey()));
                        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                });
                //tv.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.id.ic),null,null,null);
                holder.divers.addView(tv);
                i++;
            }
        }

        // Format de la date
        SimpleDateFormat formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date tostring =null;
            try {
                if(film.getType().matches("show")) {
                    tostring = formatter.parse(film.getDate_premier_ep());
                }else{
                    tostring = formatter.parse(film.getDate());
                }
            } catch (ParseException | java.text.ParseException e) {
                e.printStackTrace();
            }
            formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
            holder.date.setText("Sortie le "+formatter.format(tostring));

        }



        holder.categories.setText("");
        if(film.getGenres()!=null) {
            for (Genre g : film.getGenres()) {
                holder.categories.append(g.getNom() + " ");
            }
        }

        if(film.getType().matches("movie")) {
            if (film.getCredits() != null) {
                holder.real.setText("Réalisé par " + film.getRealisateurs());
            }
        }else {
            if (film.getCreated_by() != null) {
                holder.real.setText("Réalisé par " + film.getCreated_by());
            }
        }

        if(!film.getCredits().getCast().isEmpty()) {
            holder.credits.setText("Avec : " + film.getCredits().getCast().get(0).getName());
            for (int compt = 1; compt < 5 && compt < film.getCredits().getCast().size(); compt++) {
                holder.credits.append(", " + film.getCredits().getCast().get(compt).getName());
            }
        }
        holder.longue_description.setText(film.getLongueDesc());

        //makeTextViewResizable(holder.longue_description,5,"Continuer",true);

        // Les offres
        OffreAdapter recyclerAdapterStreaming;
        OffreAdapter recyclerAdapterLocation;
        OffreAdapter recyclerAdapterAchat;

        ArrayList<Offre> listeStreaming = new ArrayList<Offre>();
        ArrayList<Offre> listeLocation = new ArrayList<Offre>();
        ArrayList<Offre> listeAchat = new ArrayList<Offre>();

        Collections.sort(film.getListeOffres(), new Comparator<Offre>()
        {
            @Override
            public int compare(Offre o1, Offre o2) {

                return Double.valueOf(o1.getRetailPrice()).compareTo(o2.getRetailPrice());
            }
        });

        Collections.sort(film.getListeOffres(), new Comparator<Offre>()
        {
            @Override
            public int compare(Offre o1, Offre o2) {

                return Integer.valueOf(Plateforme.getById(o1.getProviderId()).getPlace()).compareTo(Plateforme.getById(o2.getProviderId()).getPlace());
            }
        });

        for (Offre o:film.getListeOffres()) {
            if(o.getMonetizationType().equalsIgnoreCase("flatrate")){
                if (holder.texteStreaming.getVisibility()==View.GONE)
                    holder.texteStreaming.setVisibility(View.VISIBLE);
                listeStreaming.add(o);
            }else if(o.getMonetizationType().equalsIgnoreCase("rent")){
                if (holder.texteLocation.getVisibility()==View.GONE)
                    holder.texteLocation.setVisibility(View.VISIBLE);
                listeLocation.add(o);
            }else if(o.getMonetizationType().equalsIgnoreCase("buy")){
                if (holder.texteAchat.getVisibility()==View.GONE)
                    holder.texteAchat.setVisibility(View.VISIBLE);
                listeAchat.add(o);
            }
        }

        recyclerAdapterStreaming = new OffreAdapter(context, listeStreaming);
        recyclerAdapterLocation = new OffreAdapter(context, listeLocation);
        recyclerAdapterAchat = new OffreAdapter(context, listeAchat);

        holder.recyclerViewStreaming.setAdapter(recyclerAdapterStreaming);
        holder.recyclerViewLocation.setAdapter(recyclerAdapterLocation);
        holder.recyclerViewAchat.setAdapter(recyclerAdapterAchat);

        // Type
        if(film.getType().matches("show")) {
            holder.type.setText("Série de "+film.getSeason_number()+" saisons.\nUn épisode dure "+film.getEpisode_run_time_average()+"min");
        }else {
            holder.type.setText("Film de "+film.getRuntime()/60+"h"+film.getRuntime()%60);
            if (film.getAge()!=null && film.getAge().matches("U")) {
                holder.type.append("\n" + "Tous public");
            }else if(film.getAge()!=null){
                holder.type.append("\n-" + film.getAge());
            }
        }
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

    private void selectItem(FilmViewHolder holder, Film f) {
        // If the "selectedItems" list contains the item, remove it and set it's state to normal
        if (selectedItems.contains(f)) {
            selectedItems.remove(f);
            holder.thumbnail.setAlpha(1.0f);
        } else {
            // Else, add it to the list and add a darker shade over the image, letting the user know that it was selected
            selectedItems.add(f);
            holder.thumbnail.setAlpha(0.3f);
        }
    }

    // Méthodes de l'interface ActionMode

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // Inflate the menu resource providing context menu items
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_multi, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            // Delete button is clicked, handle the deletion and finish the multi select process

            // Enlever de la ToWatchList
            FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
            FirebaseFirestore fStore = FirebaseFirestore.getInstance();

            String utilisateurId = mFirebaseAuth.getCurrentUser().getUid();
            final CollectionReference collectionReference = fStore.collection("Utilisateurs").document(utilisateurId).collection("ToWatch");

            for (final Film f :selectedItems) {

                collectionReference.document(f.getIdFirebase()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                    listeFilms.remove(f);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Eléments supprimés", Toast.LENGTH_LONG).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Probleme", "Error getting documents: ", e);
                                Toast.makeText(context, "Echec de la suppression", Toast.LENGTH_LONG).show();
                            }

                        });

            }
            selectedItems.clear();
            mode.finish();
        }
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // finished multi selection
        multiSelect = false;
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        public TextView titre, textAime, textPasAime,type,name,date,categories,real,longue_description,credits;
        public ImageView thumbnail, miniature;
        public RelativeLayout viewBackground, viewForeground;
        public LinearLayout divers;
        public ScrollView plus_info;

        public RecyclerView recyclerViewStreaming, recyclerViewLocation, recyclerViewAchat;
        public TextView texteStreaming, texteLocation, texteAchat;

        public FilmViewHolder(View view) {
            super(view);
            titre = view.findViewById(R.id.titre);
            thumbnail = view.findViewById(R.id.thumbnail);
            textAime = view.findViewById(R.id.text_vu);
            textPasAime = view.findViewById(R.id.text_supp);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
            divers = view.findViewById(R.id.divers);

            plus_info = view.findViewById(R.id.plus_info);
            type = view.findViewById(R.id.type);

            // Info supplémentaires cachées
            miniature = view.findViewById(R.id.affiche_miniature);
            name = view.findViewById(R.id.info_titre);
            date = view.findViewById(R.id.info_date);
            categories = view.findViewById(R.id.info_categories);
            real = view.findViewById(R.id.info_real);
            longue_description = view.findViewById(R.id.longue_description);
            credits = view.findViewById(R.id.credits);

            // Plateformes
            recyclerViewStreaming = view.findViewById(R.id.streaming);
            recyclerViewLocation = view.findViewById(R.id.location);
            recyclerViewAchat = view.findViewById(R.id.achat);

            texteStreaming = view.findViewById(R.id.text_streaming);
            texteLocation = view.findViewById(R.id.text_location);
            texteAchat = view.findViewById(R.id.text_achat);
        }
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

            ssb.setSpan(
                    new ClickableSpan() {

                        @Override
                        public void onClick(View widget) {

                            if (viewMore) {

                                tv.setLayoutParams(tv.getLayoutParams());
                                tv.setText(tv.getTag().toString(),
                                        TextView.BufferType.SPANNABLE);
                                tv.invalidate();
                                makeTextViewResizable(tv, -5, "Voir moins",
                                        false);
                                tv.setTextColor(Color.BLACK);
                                //notifyDataSetInvalidated();
                            } else {

                                tv.setLayoutParams(tv.getLayoutParams());
                                tv.setText(tv.getTag().toString(),
                                        TextView.BufferType.SPANNABLE);
                                tv.invalidate();
                                makeTextViewResizable(tv, 5, "Continuer",
                                        true);
                                tv.setTextColor(Color.BLACK);
                                //notifyDataSetInvalidated();
                            }

                        }
                    }, strSpanned.indexOf(spanableText),
                    strSpanned.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }

    public void clear(){
        multiSelect = false;
        selectedItems.clear();
        notifyDataSetChanged();
    }
}
