package com.example.miapr;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button registrar, importar, lista, lista2, exportar, imprimir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        registrar = findViewById(R.id.btReg);
        importar = findViewById(R.id.btImp);
        lista = findViewById(R.id.btLista);
        lista2 = findViewById(R.id.btLista2);
        exportar = findViewById(R.id.btExp);
        //imprimir = findViewById(R.id.btnImprime);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnHome:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.idSalir:
                Intent intent2 = new Intent(this, Login.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
                startActivity(intent2);
                return true;

        }

        return super.onOptionsItemSelected(item);
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

    public void Imprimir(View view){
        Intent i = new Intent(this, Imprimir.class);
        startActivity(i);
    }
}
