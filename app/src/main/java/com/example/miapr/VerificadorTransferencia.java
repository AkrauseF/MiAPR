package com.example.miapr;

import android.content.Context;
import android.util.Log;
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
    public VerificadorTransferencia( String ipServer, Context context){
        this.ipServer = ipServer;
        this.context = context;
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
                    Toast.makeText(context, "Transferencia de Medidores exitosa", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Error en Transferencia de Medidores", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
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

        String Url = "http://"+ipServer+"/Apr/modelo/consultaCantidadLecturas.php"; //obtiene el la cantidad de registros de LECTURAS del ultimomes
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if(Integer.parseInt(cantRegistros[0])== Integer.parseInt(response)){

                    Toast.makeText(context, "Transferencia de Lecturas exitosa", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Error en Transferencia de Lecturas", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
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

                    Toast.makeText(context, "Transferencia de Datos de Cobros exitosa", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Error en Transferencia de  Datos de Cobros", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
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

                if(Integer.parseInt(cantRegistros[0])== Integer.parseInt(response)){
                    Toast.makeText(context, "Transferencia de Clientes exitosa", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Error en Transferencia de Clientes", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
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

                if(Integer.parseInt(cantRegistros[0])== Integer.parseInt(response)){
                    Toast.makeText(context, "Transferencia de Operadores exitosa", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Error en Transferencia de Operadores", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}

