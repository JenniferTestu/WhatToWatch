package com.jennifertestu.whattowatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jennifertestu.whattowatch.R;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

public class ChargementActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseUser mFirebaseUser;
    private final int TIMEOUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargement);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mFirebaseUser = mFirebaseAuth.getCurrentUser();
            }
        };

        // Récupération de la connexion de l'utilisateur
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mFirebaseUser != null) {
                    Intent i = new Intent(ChargementActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(ChargementActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, TIMEOUT);

        TextView tv = findViewById(R.id.textApp);

        ObjectAnimator textColorAnim = ObjectAnimator.ofInt(tv, "textColor", Color.parseColor("#2A387F"), Color.WHITE);
        textColorAnim.setDuration(1000);
        textColorAnim.setEvaluator(new ArgbEvaluator());
        textColorAnim.start();

        /*
        View yourView = findViewById(R.id.mascotte);

        new SimpleTooltip.Builder(this)
                .anchorView(yourView)
                .text("Coucou, content de te revoir !")
                .maxWidth(400f)
                .textColor(Color.WHITE)
                .gravity(Gravity.END)
                .build()
                .show();

         */
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}