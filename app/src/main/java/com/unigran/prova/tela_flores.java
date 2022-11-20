package com.unigran.prova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unigran.prova.model.Flor;

import java.util.LinkedList;
import java.util.List;

public class tela_flores extends AppCompatActivity {

    List listaFlores = new LinkedList();

    private EditText nomeFlor;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_flores);

        nomeFlor = findViewById(R.id.nomeFlor);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        lerFlores();
    }

    public void salvarFlor(View view){
        Flor flor = new Flor();
        flor.setNomeFlor(nomeFlor.getText().toString());
        databaseReference.child("flores").child(flor.getNomeFlor()).setValue(flor);

        nomeFlor.setText("");
    }

    public void lerFlores(){
        DatabaseReference flores = databaseReference.child("flores");

        listaFlores.clear();
        flores.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dados:snapshot.getChildren()
                     ) {
                    Flor flor = dados.getValue(Flor.class);
                    listaFlores.add(flor);
                    Log.i("Firabase", dados.child("nomeFlor").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Firabase", error.toString());
            }
        });
    }
}