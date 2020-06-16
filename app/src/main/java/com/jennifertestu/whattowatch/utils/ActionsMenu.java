package com.jennifertestu.whattowatch.utils;

import android.content.Intent;
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
import com.jennifertestu.whattowatch.activity.RechercheActivity;
import com.jennifertestu.whattowatch.activity.WishlistActivity;

public class ActionsMenu{

    public static void menuPrincipal(@NonNull final AppCompatActivity app){

        app.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        app.getSupportActionBar().setCustomView(R.menu.menu);


        // Deconnexion
        ImageButton deco = app.findViewById(R.id.deco);
        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(app, LoginActivity.class);
                app.startActivity(i);
            }
        });

        // Ecran de recherche
        ImageButton recherche = app.findViewById(R.id.recherche);
        recherche.setOnClickListener(new View.OnClickListener() {
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

    }

}
