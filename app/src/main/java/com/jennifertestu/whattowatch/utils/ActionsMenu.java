package com.jennifertestu.whattowatch.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.activity.LoginActivity;
import com.jennifertestu.whattowatch.activity.MainActivity;
import com.jennifertestu.whattowatch.activity.ProfilActivity;
import com.jennifertestu.whattowatch.activity.RechercheActivity;
import com.jennifertestu.whattowatch.activity.ResetActivity;
import com.jennifertestu.whattowatch.activity.WishlistActivity;
import com.jennifertestu.whattowatch.model.Recherche;

public class ActionsMenu{

    public static void menuPrincipal(@NonNull final AppCompatActivity app, String acti){

        app.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        app.getSupportActionBar().setCustomView(R.menu.menu);


        // Profil
        ImageButton profil = app.findViewById(R.id.profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app, ProfilActivity.class);
                app.startActivity(i);
            }
        });

        // Ecran de recherche
        ImageButton recherche = app.findViewById(R.id.recherche);
        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app, MainActivity.class);
                app.startActivity(i);
            }
        });

        ImageButton loupe = app.findViewById(R.id.loupe);
        loupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app, RechercheActivity.class);
                app.startActivity(i);
            }
        });

        ImageButton bouton_wishlist = app.findViewById(R.id.bouton_wishlist);
        bouton_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(app, WishlistActivity.class);
                app.startActivity(intent);
            }
        });


        if(acti.matches(MainActivity.class.getSimpleName())){
            recherche.setAlpha(1f);
        }else if(acti.matches(WishlistActivity.class.getSimpleName())){
            bouton_wishlist.setAlpha(1f);
        }else if(acti.matches(RechercheActivity.class.getSimpleName())){
            loupe.setAlpha(1f);
        }else if(acti.matches(ProfilActivity.class.getSimpleName())){
            profil.setAlpha(1f);
        }/*
        deco.setAlpha(.5f);
        recherche.setAlpha(.5f);
        bouton_wishlist.setAlpha(.5f);

 */
    }

}
