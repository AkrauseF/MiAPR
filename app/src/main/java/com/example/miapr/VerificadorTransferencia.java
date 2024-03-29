package com.example.miapr;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VerificadorTransferencia extends AppCompatActivity {
    String ipServer;
    boolean flag = true;
    Context context;
    TextView tvRmedidores, tvRlecturas, tvRdatoscobros, tvRclientes, tvRoperadores;
    public VerificadorTransferencia( String ipServer, Context context, TextView tvRmedidores, TextView tvRlecturas, TextView tvRdatoscobros, TextView tvRclientes, TextView tvRoperadores ){
        this.ipServer = ipServer;
        this.context = context;
        this.tvRmedidores = tvRmedidores;
        this.tvRlecturas = tvRlecturas;
        this.tvRdatoscobros = tvRdatoscobros;
        this.tvRclientes = tvRclientes;
        this.tvRoperadores = tvRoperadores;

    }

    public boolean verificarMedidores(){

        Log.i("KraIN::","Ingreso");
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        final String[] cantRegistros = databaseAccess.UltimoIdMedidores();

        String Url = "http://"+ipServer+"/Apr/modelo/consultaID.php"; //obtiene el la cantidad de registros de medidores en appweb.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if(Integer.parseInt(cantRegistros[0])== Integer.parseInt(response)){
                    // Toast.makeText(context, "Transferencia de Medidores exitosa", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Transferencia de Medidores exitosa");
                    tvRmedidores.setVisibility(View.VISIBLE);
                    tvRmedidores.setText("SI");
                    tvRmedidores.setTypeface(tvRmedidores.getTypeface(), Typeface.BOLD);
                    tvRmedidores.setTextColor(Color.GREEN);
                }else{
                    // Toast.makeText(context, "Error en Transferencia de Medidores", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Error en Transferencia de Medidores");
                    tvRmedidores.setVisibility(View.VISIBLE);
                    tvRmedidores.setText("NO");
                    tvRmedidores.setTypeface(tvRmedidores.getTypeface(), Typeface.BOLD);
                    tvRmedidores.setTextColor(Color.RED);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(context, "Error en Transferencia de Medidores", Toast.LENGTH_SHORT).show();
                Log.i("Ver-transferencia", "Error en Transferencia de Medidores");

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Log.i("KraIN::","flag");
        return flag;
    }

    public void verificarLecturas(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        final String[] cantRegistros = databaseAccess.UltimoIdLectura();
        Log.i("Ver-transferencia-cant", cantRegistros[0]);

        String Url = "http://"+ipServer+"/Apr/modelo/consultaCantidadLecturas.php"; //obtiene el la cantidad de registros de LECTURAS del ultimomes
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Ver-transferencia-cant2", response);

                if(Integer.parseInt(cantRegistros[0])== Integer.parseInt(response)){

                    //  Toast.makeText(context, "Transferencia de Lecturas exitosa", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Transferencia de Lecturas exitosa");
                    tvRlecturas.setVisibility(View.VISIBLE);
                    tvRlecturas.setText("SI");
                    tvRlecturas.setTypeface(tvRlecturas.getTypeface(), Typeface.BOLD);
                    tvRlecturas.setTextColor(Color.GREEN);

                }else{
                    // Toast.makeText(context, "Error en Transferencia de Lecturas", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Error en Transferencia de Lecturas");
                    tvRlecturas.setVisibility(View.VISIBLE);
                    tvRlecturas.setText("NO");
                    tvRlecturas.setTypeface(tvRmedidores.getTypeface(), Typeface.BOLD);
                    tvRlecturas.setTextColor(Color.RED);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(context, "Error en Transferencia de Lecturas", Toast.LENGTH_SHORT).show();
                Log.i("Ver-transferencia", "Error en Transferencia de Lecturas");

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }

    public void verificarDatosCobros(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        final String[] cantRegistros = databaseAccess.UltimoIdDatosCobros();

        String Url = "http://"+ipServer+"/Apr/modelo/consultaCantidadDatosCobros.php"; //obtiene el la cantidad de registros de LECTURAS del ultimomes
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if(Integer.parseInt(cantRegistros[0])== Integer.parseInt(response)){

                    //  Toast.makeText(context, "Transferencia de Datos de Cobros exitosa", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Transferencia de Datos de Cobros exitosa");
                    tvRdatoscobros.setVisibility(View.VISIBLE);
                    tvRdatoscobros.setText("SI");
                    tvRdatoscobros.setTypeface(tvRdatoscobros.getTypeface(), Typeface.BOLD);
                    tvRdatoscobros.setTextColor(Color.GREEN);

                }else{
                    //  Toast.makeText(context, "Error en Transferencia de  Datos de Cobros", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Error en Transferencia de  Datos de Cobros");
                    tvRdatoscobros.setVisibility(View.VISIBLE);
                    tvRdatoscobros.setText("NO");
                    tvRdatoscobros.setTypeface(tvRmedidores.getTypeface(), Typeface.BOLD);
                    tvRdatoscobros.setTextColor(Color.RED);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(context, "Error en Transferencia de  Datos de Cobros", Toast.LENGTH_SHORT).show();
                Log.i("Ver-transferencia", "Error en Transferencia de  Datos de Cobros");

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    public void verificarClientes(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        final String[] cantRegistros = databaseAccess.UltimoIdCliente();


        String Url = "http://"+ipServer+"/Apr/modelo/consultaIdClientes.php"; //obtiene el la cantidad de registros de LECTURAS del ultimomes
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Cant-clientesIn", cantRegistros[0]);
                Log.i("Cant-clientesEx", response);


                if(Integer.parseInt(cantRegistros[0])== Integer.parseInt(response)){
                    //  Toast.makeText(context, "Transferencia de Clientes exitosa", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Transferencia de Clientes exitosa");
                    tvRclientes.setVisibility(View.VISIBLE);
                    tvRclientes.setText("SI");
                    tvRclientes.setTypeface(tvRclientes.getTypeface(), Typeface.BOLD);
                    tvRclientes.setTextColor(Color.GREEN);

                }else{
                    //   Toast.makeText(context, "Error en Transferencia de Clientes", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Error en Transferencia de Clientes");
                    tvRclientes.setVisibility(View.VISIBLE);
                    tvRclientes.setText("NO");
                    tvRclientes.setTypeface(tvRmedidores.getTypeface(), Typeface.BOLD);
                    tvRclientes.setTextColor(Color.RED);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(context, "Error en Transferencia de Clientes", Toast.LENGTH_SHORT).show();
                Log.i("Ver-transferencia", "Error en Transferencia de Clientes");

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    public void verificarOperadores(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        final String[] cantRegistros = databaseAccess.UltimoIdOperadores();

        String Url = "http://"+ipServer+"/Apr/modelo/consultaIdOperadores.php"; //obtiene el la cantidad de registros de LECTURAS del ultimomes
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Cant-operIn", cantRegistros[0]);
                Log.i("Cant-operEx", response);

                if(Integer.parseInt(cantRegistros[0])+1 == Integer.parseInt(response)){
                    //Toast.makeText(context, "Transferencia de Operadores exitosa", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Transferencia de Operadores exitosa");
                    tvRoperadores.setVisibility(View.VISIBLE);
                    tvRoperadores.setText("SI");
                    tvRoperadores.setTypeface(tvRoperadores.getTypeface(), Typeface.BOLD);
                    tvRoperadores.setTextColor(Color.GREEN);

                }else{
                   // Toast.makeText(context, "Error en Transferencia de Operadores", Toast.LENGTH_SHORT).show();
                    Log.i("Ver-transferencia", "Error en Transferencia de Operadores");
                    tvRoperadores.setVisibility(View.VISIBLE);
                    tvRoperadores.setText("NO");
                    tvRoperadores.setTypeface(tvRmedidores.getTypeface(), Typeface.BOLD);
                    tvRoperadores.setTextColor(Color.RED);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(context, "Error en Transferencia de Operadores", Toast.LENGTH_SHORT).show();

                Log.i("Ver-transferencia", "Error en Transferencia de Operadores**");

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}

