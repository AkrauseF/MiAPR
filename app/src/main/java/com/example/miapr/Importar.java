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
    TextView tvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar);

        btImportar = findViewById(R.id.btImportar);
        tvDatos = findViewById(R.id.tvDatos);
    }

    public void consulUltId(View view) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.0.4/Apr/modelo/consultaID.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //Se envia a la funcion el numero del utlimo registro.
                //conexionUnophp(response);
                //tvDatos.setText(response);
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
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();

        borrartabla();

         int ide = Integer.parseInt(id);
         Integer num = 1;

         //While permite parar el ciclo hasta el ultimo id de la tabla.
         while (num <= ide ){

             StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.0.4/Apr/modelo/descargarDatos.php?var='"+num+"'", new Response.Listener<String>() {

                 @Override
                 public void onResponse(String response) {

                     //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                     String[] respuesta = response.split(",");

                     String id =respuesta[0];
                     String numero =respuesta[1];
                     String marca =respuesta[2];

                     //tvDatos.setText(id+"-"+numero+"-"+marca);

                     //Se envian los datos consultados a la funcion crear tabla en SQLite
                    crearTabla(id, numero,marca);

                   /* Toast.makeText(getApplicationContext(), respuesta[0], Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), respuesta[1], Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), respuesta[2], Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), respuesta[3], Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), respuesta[4], Toast.LENGTH_LONG).show();*/

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

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        databaseAccess.getInsetarTabla(id,numero,marca);

        String lecturas = databaseAccess.getLecturas();

        tvDatos.setText(lecturas);

    }
}