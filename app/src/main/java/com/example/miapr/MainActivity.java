package com.example.miapr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button registrar, importar, lista, lista2, exportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar = findViewById(R.id.btReg);
        importar = findViewById(R.id.btImp);
        lista = findViewById(R.id.btLista);
        lista2 = findViewById(R.id.btLista2);
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
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String valor = databaseAccess.verificarAlmacenamientoMedidores();
        if (valor.equals("0")){
            Toast.makeText(this, "No hay medidores para mostrar", Toast.LENGTH_SHORT).show();

        }else {
            Intent i = new Intent(this, ListaMedidores.class);
            startActivity(i);
        }

    }
    public void ListaRegistros(View view){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String valor = databaseAccess.verificarAlmacenamientoLecturas();
        if (valor.equals("0")){
            Toast.makeText(this, "No hay registros para mostrar", Toast.LENGTH_SHORT).show();

        }else {
            Intent i = new Intent(this, ListaRegistros.class);
            startActivity(i);
        }

    }

    public void Exportar(View view){
        Intent i = new Intent(this, Exportar.class);
        startActivity(i);
    }
}
