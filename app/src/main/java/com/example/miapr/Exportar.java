package com.example.miapr;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Exportar extends AppCompatActivity {

    Button btnExportar;
    EditText etUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expotar);

        btnExportar= findViewById(R.id.btExportar);
        etUrl = findViewById(R.id.etUri2);




    }

    public void IniciarExportacion(View view){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String ultimaLectura = databaseAccess.UltimoIdLectura();
        Toast.makeText(this, "Se exportarán "+ultimaLectura+" Registros de lecturas", Toast.LENGTH_SHORT).show();

        int ulIdLectura = Integer.parseInt(ultimaLectura);

        String[] fecha = databaseAccess.getLecturas(ulIdLectura);
        verificarFecha(fecha[2], ulIdLectura );



    }
    //Obtine los registros de lecturas en base de datos app móvil
    public void obtenerLecturas(int ulIdLectura){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        int num = 1;

         while (num <= ulIdLectura) {

             String[] registroLecturas = databaseAccess.getLecturas(num);
             ExportarDatos(registroLecturas[0], registroLecturas[1], registroLecturas[2], registroLecturas[3], registroLecturas[4]);
             //Toast.makeText(this, registroLecturas[0]+"-"+registroLecturas[1]+"-"+registroLecturas[2]+"-"+registroLecturas[3]+"-"+registroLecturas[4], Toast.LENGTH_SHORT).show();
             num++;

         }
    }
    //verifca que la fecha del ultimo registo en el móvil no se encuntre en exportado. para no volve a exportar o duplicar datos en app web.
    public void verificarFecha(final String date, final int ulIdLectura){
        String Url = "http://"+etUrl.getText().toString()+"/Apr/modelo/verificarFecha.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
               // Toast.makeText(getApplicationContext(), "recibido: "+response+" Enviado: "+date, Toast.LENGTH_LONG).show();

                if(response.equals("1")){
                    Toast.makeText(getApplicationContext(), "Los datos ya se encuentran exportados", Toast.LENGTH_LONG).show();

                }else {
                    obtenerLecturas(ulIdLectura);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String, String>();

                parametros.put("fecha", date);

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void ExportarDatos(final String codigo, final String lectura, final String fecha, final String monto, final String metrosc) {

        String Url = "http://" + etUrl.getText().toString() + "/Apr/modelo/cargarDatos.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("lectura", lectura);
                parametros.put("monto", monto);
                parametros.put("fecha", fecha);
                parametros.put("metros_cubicos", metrosc);
                parametros.put("num_medidor", codigo);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
