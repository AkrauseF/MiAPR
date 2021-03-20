package com.example.miapr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Importar extends AppCompatActivity {

    Button btImportar;
    EditText etUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar);

        btImportar = findViewById(R.id.btImportar);
        etUrl = (EditText) findViewById(R.id.etUri);

    }



    public void consulUltId(View view) {

        String Url = "http://"+etUrl.getText().toString()+"/Apr/modelo/consultaID.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                conexionUnophp(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
     public void conexionUnophp(String id) {
        String Url = "http://"+etUrl.getText().toString()+"/Apr/modelo/descargarDatos.php";
        Toast.makeText(this, "Se importarán "+id+" registros de medidores", Toast.LENGTH_LONG).show();

        borrartabla();

         int ide = Integer.parseInt(id);
         Integer num = 1;

         //While repite el ciclo hasta el ultimo id de la tabla.
         while (num <= ide ){

             StringRequest stringRequest = new StringRequest(Request.Method.POST, Url+"?var='"+num+"'", new Response.Listener<String>() {

                 @Override
                 public void onResponse(String response) {

                     //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                     String[] respuesta = response.split(",");

                     String id =respuesta[0];
                     String numero =respuesta[1];
                     String marca =respuesta[2];


                     //Se envian los datos consultados a la funcion crear tabla en SQLite
                    crearTabla(id, numero,marca);

                 }
             }, new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                     Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                 }
             });
             RequestQueue requestQueue = Volley.newRequestQueue(this);
             requestQueue.add(stringRequest);

             num = num + 1;
         }

         Toast.makeText(getApplicationContext(), "Datos importados correctamente", Toast.LENGTH_LONG).show();

     }

    private void borrartabla(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        //vaciar tabla de registros
        databaseAccess.getVaciarTabla();

        //Toast.makeText(getApplicationContext(), "tabla borrada", Toast.LENGTH_LONG).show();

        databaseAccess.close();
    }
    private void crearTabla(String id, String numero, String marca){

        //Toast.makeText(this, numero, Toast.LENGTH_SHORT).show();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        databaseAccess.getInsetarTabla(id,numero,marca);


    }
}