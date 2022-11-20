package com.unigran.prova;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unigran.prova.model.Cliente;
import com.unigran.prova.model.Encomenda;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class tela_encomenda extends AppCompatActivity {

    DatabaseReference databaseReference;

    List listaEncomendas = new LinkedList();

    private EditText nomeCliente;
    private  EditText nomeFlor;

    private String comparacaoCliente;
    private String comparacaoFlor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_encomenda);

        nomeCliente = findViewById(R.id.nomeClienteEnco);
        nomeFlor = findViewById(R.id.nomeFlorEnco);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        lerEncomendas();
    }

    public void cadastrarEncomenda(View view){

        Random gerador = new Random();
        Encomenda encomenda = new Encomenda();
        encomenda.setNomeCliente(nomeCliente.getText().toString());
        encomenda.setNomeFlor(nomeFlor.getText().toString());

        if(lerCliente() && lerFlores()){
            databaseReference.child("encomendas").child("" + encomenda.getNomeCliente() + encomenda.getNomeFlor() + gerador.nextInt()).setValue(encomenda);
        } else {
            Toast.makeText(this,"Cliente ou flor não cadastrado", Toast.LENGTH_SHORT).show();
        }

    }

    public void lerEncomendas(){
        DatabaseReference encomendas = databaseReference.child("clientes");

        listaEncomendas.clear();

        encomendas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dados:snapshot.getChildren()
                ) {
                    Encomenda encomenda = dados.getValue(Encomenda.class);
                    listaEncomendas.add(encomenda);
                    Log.i("Encomendas", dados.child("nomeCliente").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Firabase", error.toString());
            }
        });
    }

    public boolean lerCliente(){
        DatabaseReference clientes = databaseReference.child("clientes");

        final boolean[] clienteBoo = {false};

        clientes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dados:snapshot.getChildren()
                ) {
                    if(dados.child("nome").getValue().toString() == nomeCliente.getText().toString())
                        clienteBoo[0] = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Firabase", error.toString());
            }
        });

        return clienteBoo[0];
    }

    public boolean lerFlores(){
        DatabaseReference flores = databaseReference.child("flores");

        final boolean[] florBoo = {false};

        flores.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dados:snapshot.getChildren()
                ) {
                    if(dados.child("nomeFlor").getValue().toString() == nomeFlor.getText().toString())
                        florBoo[0] = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Firabase", error.toString());
            }
        });

        return florBoo[0];
    }


}