package com.jennifertestu.whattowatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.jennifertestu.whattowatch.R;

public class ResetActivity extends AppCompatActivity {

    private TextView mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        getSupportActionBar().hide();

        Button annuler = findViewById(R.id.annuler);
        Button reset = findViewById(R.id.reset);
        mail = findViewById(R.id.identifiant);

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResetActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(mail.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Un mail vous a été envoyé", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(ResetActivity.this, LoginActivity.class);
                                    finish();
                                    startActivity(i);
                                }else {
                                    Toast.makeText(ResetActivity.this, "Un problème est survenu", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
