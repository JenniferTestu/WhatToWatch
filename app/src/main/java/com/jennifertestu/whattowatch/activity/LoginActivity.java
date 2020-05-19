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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jennifertestu.whattowatch.R;

public class LoginActivity extends AppCompatActivity {

    EditText tv_identif,tv_mdp;
    Button connexion;
    TextView inscription;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_identif = findViewById(R.id.identifiant);
        tv_mdp = findViewById(R.id.mdp);

        connexion = findViewById(R.id.connexion);
        inscription = findViewById(R.id.inscription);

        mFirebaseAuth = FirebaseAuth.getInstance();
/*
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Connecte toi", Toast.LENGTH_SHORT).show();
                }
            }
        };
*/
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String identif = tv_identif.getText().toString().trim();
                String mdp = tv_mdp.getText().toString().trim();

                if(identif.isEmpty()){
                    tv_identif.setError("Email requis");
                    tv_identif.requestFocus();
                }else if(mdp.isEmpty()){
                    tv_mdp.setError("Mot de passe requis");
                    tv_mdp.requestFocus();
                }else if(!(identif.isEmpty() && mdp.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(identif,mdp).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Echec de la connexion, réessaye plus tard", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this, "Une erreur s'est produite", Toast.LENGTH_SHORT).show();
                }
            }
        });

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignOnActivity.class);
                startActivity(i);
            }
        });
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
 */
}
