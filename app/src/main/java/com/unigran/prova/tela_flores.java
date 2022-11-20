package com.unigran.prova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.unigran.prova.model.Flor;

public class tela_flores extends AppCompatActivity {

    private EditText nomeFlor;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_flores);

        nomeFlor = findViewById(R.id.nomeCliente);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void salvarFlor(View view){
        Flor flor = new Flor();
        flor.setNomeFlor(nomeFlor.getText().toString());
        databaseReference.child("flores").child(flor.getNomeFlor()).setValue(flor);

        nomeFlor.setText("");
    }
}