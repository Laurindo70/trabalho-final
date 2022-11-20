package com.unigran.prova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.unigran.prova.model.Cliente;

import java.util.LinkedList;
import java.util.List;

public class tela_clientes extends AppCompatActivity {

    DatabaseReference databaseReference;

    List listaClientes = new LinkedList();

    private EditText nomeCliente;
    private EditText enderecoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clientes);

        nomeCliente = findViewById(R.id.nomeCliente);
        enderecoCliente = findViewById(R.id.enderecoCliente);

        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void salvar(View view){
        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente.getText().toString());
        cliente.setEndereco(enderecoCliente.getText().toString());
        databaseReference.child("clientes").child(cliente.getNome()).setValue(cliente);

        nomeCliente.setText("");
        enderecoCliente.setText("");
    }
}