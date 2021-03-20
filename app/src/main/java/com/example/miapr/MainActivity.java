package com.example.miapr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button registrar, importar, lista, exportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar = findViewById(R.id.btReg);
        importar = findViewById(R.id.btImp);
        lista = findViewById(R.id.btLista);
        exportar = findViewById(R.id.btExp);
    }

    public void Registrar(View view){
        Intent i = new Intent(this, RegistrarDatosMedidor.class);
        startActivity(i);
    }
    public void Importar(View view){
        Intent i = new Intent(this, Importar.class);
        startActivity(i);

    }
    public void ListaMedidores(View view){
        Intent i = new Intent(this, ListaMedidores.class);
        startActivity(i);
    }

    public void Exportar(View view){
        Intent i = new Intent(this, Exportar.class);
        startActivity(i);
    }
}
