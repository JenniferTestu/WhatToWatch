package com.jennifertestu.whattowatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.utils.ActionsMenu;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ActionsMenu.menuPrincipal(this,getClass().getSimpleName());

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        TextView question = findViewById(R.id.question);

        TextView deco = findViewById(R.id.deco);
        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        TextView supp_compte = findViewById(R.id.supp_compte);
        supp_compte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(ProfilActivity.this,R.style.dialogTheme);
                builder1.setMessage("Veux-tu supprimer ton compte ? Toutes tes données seront supprimées.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Oui",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                user.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(ProfilActivity.this, "Compte supprimé", Toast.LENGTH_LONG).show();
                                                    FirebaseAuth.getInstance().signOut();
                                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                                    startActivity(i);
                                                }else {
                                                    Toast.makeText(ProfilActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Non",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

        TextView supp_histo = findViewById(R.id.supp_histo);
        supp_histo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ProfilActivity.this,R.style.AppTheme);
                builder1.setMessage("Veux-tu supprimer ton historique ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Oui",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Non",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
}
