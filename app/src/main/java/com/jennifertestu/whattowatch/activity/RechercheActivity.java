package com.jennifertestu.whattowatch.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.adapter.AutoCompleteAdapter;
import com.jennifertestu.whattowatch.adapter.PlateformeAdapter;
import com.jennifertestu.whattowatch.communication.ConnexionAPI;
import com.jennifertestu.whattowatch.model.Personne;
import com.jennifertestu.whattowatch.model.PersonnesResultats;
import com.jennifertestu.whattowatch.model.Plateforme;
import com.jennifertestu.whattowatch.model.Recherche;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechercheActivity extends AppCompatActivity {

    private Recherche recherche;
    private PlateformeAdapter mAdapter;
    private AutoCompleteAdapter adapter;
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    private Handler handler;
    private Personne personne = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        // Recherche
        final SharedPreferences mPrefs = getSharedPreferences("Recherche", 0);
        // Récupérer la recherche et la convertir en objet Recherche
        Gson gson = new Gson();
        String json = mPrefs.getString("Champs", "");
        recherche = gson.fromJson(json, Recherche.class);
        String nom_personne = mPrefs.getString("Personne", "");


        final CheckBox films = findViewById(R.id.check_films);
        final CheckBox series = findViewById(R.id.check_series);

        final CheckBox act = findViewById(R.id.check_act);
        final CheckBox ani = findViewById(R.id.check_ani);
        final CheckBox cmy = findViewById(R.id.check_cmy);
        final CheckBox crm = findViewById(R.id.check_crm);
        final CheckBox doc = findViewById(R.id.check_doc);
        final CheckBox eur = findViewById(R.id.check_eur);
        final CheckBox fml = findViewById(R.id.check_fml);
        final CheckBox fnt = findViewById(R.id.check_fnt);
        final CheckBox hrr = findViewById(R.id.check_hrr);
        final CheckBox hst = findViewById(R.id.check_hst);
        final CheckBox msc = findViewById(R.id.check_msc);
        final CheckBox rly = findViewById(R.id.check_rly);
        final CheckBox rma = findViewById(R.id.check_rma);
        final CheckBox scf = findViewById(R.id.check_scf);
        final CheckBox spt = findViewById(R.id.check_spt);
        final CheckBox trl = findViewById(R.id.check_trl);
        final CheckBox war = findViewById(R.id.check_war);
        final CheckBox wsn = findViewById(R.id.check_wsn);
        final CheckBox drm = findViewById(R.id.check_drm);

        final RangeSeekBar bar = findViewById(R.id.seekBar);

        final EditText min_duree = findViewById(R.id.min_duree);
        final EditText max_duree = findViewById(R.id.max_duree);

        final CheckBox u = findViewById(R.id.check_u);
        final CheckBox moins_10 = findViewById(R.id.check_10);
        final CheckBox moins_12 = findViewById(R.id.check_12);
        final CheckBox moins_16 = findViewById(R.id.check_16);
        final CheckBox moins_18 = findViewById(R.id.check_18);

        final AutoCompleteTextView tv_acteur = findViewById (R.id.acteur);
        int layout = android.R.layout.simple_dropdown_item_1line;
        adapter = new AutoCompleteAdapter(this, layout);
        tv_acteur.setAdapter (adapter);
        tv_acteur.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        personne = adapter.getObject(position);
                    }
                });
        tv_acteur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int
                    count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,
                        AUTO_COMPLETE_DELAY);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == TRIGGER_AUTO_COMPLETE) {
                    if (!TextUtils.isEmpty(tv_acteur.getText())) {
                        appelApi(tv_acteur.getText().toString());
                    }
                }
                return false;
            }
        });

        Button annuler = findViewById(R.id.annuler);
        final Button rechercher = findViewById(R.id.rechercher);
        final RecyclerView plateformes = findViewById(R.id.plateformes);

        // Plateformes
        if(!json.equals("")) mAdapter = new PlateformeAdapter(Plateforme.values(),recherche.getProviders());
        else mAdapter = new PlateformeAdapter(Plateforme.values(),recherche.getProviders());
        GridLayoutManager manager = new GridLayoutManager(this,5);
        //manager.setOrientation(RecyclerView.HORIZONTAL);
        plateformes.setHasFixedSize(true);
        plateformes.setLayoutManager(manager);
        plateformes.setAdapter(mAdapter);

        // Définition de la fourchette de valeurs
        bar.setRangeValues(1900,Calendar.getInstance().get(Calendar.YEAR));

        // Chargement de la recherche précédente
        if(!json.equals("")){
            if(recherche.getContent_types().contains("movie"))films.setChecked(true);
            if(recherche.getContent_types().contains("show"))series.setChecked(true);

            // Genre
            if(recherche.getGenres().contains("act"))act.setChecked(true);
            if(recherche.getGenres().contains("ani"))ani.setChecked(true);
            if(recherche.getGenres().contains("cmy"))cmy.setChecked(true);
            if(recherche.getGenres().contains("crm"))crm.setChecked(true);
            if(recherche.getGenres().contains("doc"))doc.setChecked(true);
            if(recherche.getGenres().contains("eur"))eur.setChecked(true);
            if(recherche.getGenres().contains("fml"))fml.setChecked(true);
            if(recherche.getGenres().contains("fnt"))fnt.setChecked(true);
            if(recherche.getGenres().contains("hrr"))hrr.setChecked(true);
            if(recherche.getGenres().contains("hst"))hst.setChecked(true);
            if(recherche.getGenres().contains("msc"))msc.setChecked(true);
            if(recherche.getGenres().contains("rly"))rly.setChecked(true);
            if(recherche.getGenres().contains("rma"))rma.setChecked(true);
            if(recherche.getGenres().contains("scf"))scf.setChecked(true);
            if(recherche.getGenres().contains("spt"))spt.setChecked(true);
            if(recherche.getGenres().contains("trl"))trl.setChecked(true);
            if(recherche.getGenres().contains("war"))war.setChecked(true);
            if(recherche.getGenres().contains("wsn"))wsn.setChecked(true);
            if(recherche.getGenres().contains("drm"))drm.setChecked(true);

            // Année
            bar.setSelectedMinValue(recherche.getRelease_year_from());
            bar.setSelectedMaxValue(recherche.getRelease_year_until());

            // Durée
            if(recherche.getMin_runtime()!=0)min_duree.setText(String.valueOf(recherche.getMin_runtime()));
            if(recherche.getMax_runtime()!=0)max_duree.setText(String.valueOf(recherche.getMax_runtime()));

            // Personne
            if(!nom_personne.matches("")){
                personne = new Personne();
                personne.setFullName(nom_personne);
                personne.setId(recherche.getPerson_id());
                tv_acteur.setText(nom_personne);
            }else{
                personne = new Personne();
            }

            // Age
            if(recherche.getAge_certifications().contains("U"))u.setChecked(true);
            if(recherche.getAge_certifications().contains("10"))moins_10.setChecked(true);
            if(recherche.getAge_certifications().contains("12"))moins_12.setChecked(true);
            if(recherche.getAge_certifications().contains("16"))moins_16.setChecked(true);
            if(recherche.getAge_certifications().contains("18"))moins_18.setChecked(true);

            // Plateformes
            for (int i = 0; i < plateformes.getChildCount(); i++) {
                View view = plateformes.getChildAt(i);
                if (recherche.getProviders().contains(view.getTag().toString())) {
                    view.setAlpha(1f);
                }
            }
        }

        // Bouton annuler
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RechercheActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        // Bouton rechercher
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Recherche new_recherche = new Recherche();

                // Tableau pour les listes
                ArrayList<String> age_certifications = new ArrayList<String>();
                ArrayList<String> content_types = new ArrayList<String>();
                ArrayList<String> genres = new ArrayList<String>();
                ArrayList<String> monetization_types = new ArrayList<String>();
                ArrayList<String> providers = new ArrayList<String>();

                // Type
                if(films.isChecked())content_types.add("movie");
                if(series.isChecked())content_types.add("show");
                new_recherche.setContent_types(content_types);

                // Genre
                if(act.isChecked())genres.add("act");
                if(ani.isChecked())genres.add("ani");
                if(cmy.isChecked())genres.add("cmy");
                if(crm.isChecked())genres.add("crm");
                if(doc.isChecked())genres.add("doc");
                if(eur.isChecked())genres.add("eur");
                if(fml.isChecked())genres.add("fml");
                if(fnt.isChecked())genres.add("fnt");
                if(hrr.isChecked())genres.add("hrr");
                if(hst.isChecked())genres.add("hst");
                if(msc.isChecked())genres.add("msc");
                if(rly.isChecked())genres.add("rly");
                if(rma.isChecked())genres.add("rma");
                if(scf.isChecked())genres.add("scf");
                if(spt.isChecked())genres.add("spt");
                if(trl.isChecked())genres.add("trl");
                if(war.isChecked())genres.add("war");
                if(wsn.isChecked())genres.add("wsn");
                if(drm.isChecked())genres.add("drm");
                new_recherche.setGenres(genres);

                // Année
                new_recherche.setRelease_year_from((Integer) bar.getSelectedMinValue());
                new_recherche.setRelease_year_until((Integer) bar.getSelectedMaxValue());

                // Durée
                if (min_duree.getText().toString().trim().length() > 0)new_recherche.setMin_runtime(Integer.valueOf(min_duree.getText().toString()));
                if (max_duree.getText().toString().trim().length() > 0)new_recherche.setMax_runtime(Integer.valueOf(max_duree.getText().toString()));

                // Personne
                if(tv_acteur.getText().toString().trim().matches("") || personne==null){

                }else {
                    new_recherche.setPerson_id(personne.getId());
                }

                // Age
                if(u.isChecked())age_certifications.add("U");
                if(moins_10.isChecked())age_certifications.add("10");
                if(moins_12.isChecked())age_certifications.add("12");
                if(moins_16.isChecked())age_certifications.add("16");
                if(moins_18.isChecked())age_certifications.add("18");
                new_recherche.setAge_certifications(age_certifications);

                // Monetisation
                new_recherche.setMonetization_types(monetization_types);

                // Plateformes
                //providers.add("nfx");

                for (int i = 0; i < plateformes.getChildCount(); i++) {
                    View view = plateformes.getChildAt(i);
                    Log.e("Tag",view.getTag().toString());

                    if (view.getAlpha()==1f) {
                        providers.add(view.getTag().toString());
                    }
                }
                new_recherche.setProviders(providers);

                // Stocker la recherche
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(new_recherche);
                prefsEditor.putString("Champs", json);
                if(tv_acteur.getText().toString().trim().matches("") || personne==null) {
                    prefsEditor.putString("Personne", "");

                }else{
                    prefsEditor.putString("Personne", personne.getFullName());
                }
                prefsEditor.commit();

                Intent i = new Intent(RechercheActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void appelApi(String text) {
        ConnexionAPI.getInstance()
                .getJWApi()
                .getPersonnes("{\"content_types\":[\"person\"],\"query\":\""+text+"\",\"page\":1,\"page_size\":5}")
                .enqueue(new Callback<PersonnesResultats>() {
                    @Override
                    public void onResponse(Call<PersonnesResultats> call, Response<PersonnesResultats> response) {
                        ArrayList<Personne> suggestions = new ArrayList<>();

                        for (Personne p:response.body().getItems()) {
                            suggestions.add(p);
                        }

                        adapter.setData(suggestions);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<PersonnesResultats> call, Throwable t) {
                        Log.e("Erreur", "Autocomplete de l'acteur");
                        t.printStackTrace();
                    }
                });
    }

}
