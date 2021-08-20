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

public class VerificadorActivTransf extends AppCompatActivity {
    String ipServer;
    boolean flag = true;
    Context context;
    public String var="";
    public VerificadorActivTransf(String ipServer, Context context){
        this.ipServer = ipServer;
        this.context = context;

    }

    public void  verificarPermisoTransferencia() {

        String Url = "http://" + ipServer + "/Apr/modelo/verificarTranActivo.php?var=xo";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Log.i("Ver-respuesta", response);

                String resp = response;
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
                databaseAccess.open();

                databaseAccess.actulizarPermisoTransferencia(resp);
                databaseAccess.close();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error en Transferencia de Medidores", Toast.LENGTH_SHORT).show();
                Log.i("Ver-transferencia", "Error en Transferencia de Medidores");

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

}
