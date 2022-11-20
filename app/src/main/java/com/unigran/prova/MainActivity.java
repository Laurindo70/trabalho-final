package com.unigran.prova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    public void telaClientes(View view){
        Intent clientes = new Intent(getApplicationContext(), tela_clientes.class);
        startActivity(clientes);
    }

    public void telaFlores(View view){
        Intent flores = new Intent(getApplicationContext(), tela_flores.class);
        startActivity(flores);
    }

    public void telaEncomendas(View view){
        Intent encomendas = new Intent(getApplicationContext(), tela_encomenda.class);
        startActivity(encomendas);
    }
}