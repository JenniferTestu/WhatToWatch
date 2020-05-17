package com.jennifertestu.whattowatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jennifertestu.whattowatch.R;

import java.util.HashMap;
import java.util.Map;

public class SignOnActivity extends AppCompatActivity {

    EditText tv_prenom,tv_identif,tv_mdp,tv_mdp_conf;
    Button inscription;
    TextView connexion;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String utilisateurId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_on);

        tv_prenom = findViewById(R.id.prenom);
        tv_identif = findViewById(R.id.identifiant);
        tv_mdp = findViewById(R.id.mdp);
        tv_mdp_conf = findViewById(R.id.mdp_conf);

        connexion = findViewById(R.id.connexion);
        inscription = findViewById(R.id.inscription);

        mFirebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String identif = tv_identif.getText().toString().trim();
                String mdp = tv_mdp.getText().toString().trim();
                String mdp_conf = tv_mdp_conf.getText().toString().trim();
                final String prenom = tv_prenom.getText().toString().trim();

                if(prenom.isEmpty()){
                    tv_prenom.setError("Prénom requis");
                    tv_prenom.requestFocus();
                }else if(identif.isEmpty()){
                    tv_identif.setError("Email requis");
                    tv_identif.requestFocus();
                }else if(mdp.isEmpty()){
                    tv_mdp.setError("Mot de passe requis");
                    tv_mdp.requestFocus();
                }else if(mdp.length()<6){
                    tv_mdp.setError("Le mot de passe doit contenir plus de 6 caractères");
                    tv_mdp.requestFocus();
                }else if(!mdp.equals(mdp_conf)){
                    tv_mdp_conf.setError("Le mot de passe ne correspond pas au champs précédent");
                    tv_mdp_conf.requestFocus();
                }else if(!(identif.isEmpty() && mdp.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(identif,mdp).addOnCompleteListener(SignOnActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignOnActivity.this, "Echec de l'inscription, réessaye plus tard", Toast.LENGTH_SHORT).show();
                            }else{
                                utilisateurId = mFirebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("Utilisateurs").document(utilisateurId);

                                Map<String,Object> utilisateur = new HashMap<>();
                                utilisateur.put("prenom",prenom);
                                documentReference.set(utilisateur).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(SignOnActivity.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(SignOnActivity.this, LoginActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                            }
                        }
                    });
                }else {
                    Toast.makeText(SignOnActivity.this, "Une erreur s'est produite", Toast.LENGTH_SHORT).show();
                }
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignOnActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
