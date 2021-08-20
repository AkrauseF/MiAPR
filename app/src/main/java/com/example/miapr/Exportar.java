package com.example.miapr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    TextView tvRlecturas;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expotar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnExportar= findViewById(R.id.btExportar);
        etUrl = findViewById(R.id.etUri2);
        tvRlecturas= findViewById(R.id.tvRlecturas);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        if(databaseAccess.verificaSiEsNull()){
            Toast.makeText(this, "No se han registado todas las lecturas", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Complete la totalidad de registros de lecturas", Toast.LENGTH_LONG).show();


            Intent i = new Intent(this, ListaRegistros.class);
            startActivity(i);
        }

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

    public void IniciarExportacion(View view){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        VerificadorActivTransf ver = new VerificadorActivTransf(etUrl.getText().toString(), getApplication());
        ver.verificarPermisoTransferencia();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();

                String respuesta = databaseAccess.getPermisoTransferencia();


                if(databaseAccess.verificaSiEsNull()){
                    Toast.makeText(getApplicationContext(), "No se han registado todas las lecturas", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Complete la totalidad de registros de lecturas", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), ListaRegistros.class);
                    startActivity(i);
                } else if(respuesta.equals("0")){
                    Toast.makeText(getApplicationContext(), "No tiene permisos para transferir datos !!!", Toast.LENGTH_LONG).show();

                }else {

                    String[] ultimaLectura = databaseAccess.UltimoIdLectura();
                    //Toast.makeText(this, "Se exportarán "+ultimaLectura[0]+" Registros de lecturas", Toast.LENGTH_LONG).show();

                    int ulIdLectura = Integer.parseInt(ultimaLectura[0]);

                    String[] fecha = databaseAccess.getLecturas(ulIdLectura);
                    verificarFecha(fecha[2], ulIdLectura );
                }
            }
        }, 2000);



    }
    //Obtine los registros de lecturas en base de datos app móvil
    public void obtenerLecturas(final int ulIdLectura){
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String[] ultimaLectura = databaseAccess.UltimoIdLectura();

        progress=new ProgressDialog(this);
        progress.setMessage("Exportando "+ultimaLectura[0]+" registros de lecturas...");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        progress.setMax(ulIdLectura);
        final Thread t = new Thread() {
            @Override
            public void run() {

                int num = 1;
                while (num <= ulIdLectura) {

                    progress.setProgress(num);
                    SystemClock.sleep(500);
                    String[] registroLecturas = databaseAccess.getLecturas(num);
                    ExportarDatos(registroLecturas[0], registroLecturas[1], registroLecturas[2], registroLecturas[3], registroLecturas[4]);
                    //Toast.makeText(this, registroLecturas[0]+"-"+registroLecturas[1]+"-"+registroLecturas[2]+"-"+registroLecturas[3]+"-"+registroLecturas[4], Toast.LENGTH_SHORT).show();
                    num++;

                }
                progress.dismiss();

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Datos exportados exitosamente", Toast.LENGTH_LONG).show();


                    }
                });


            }

        };

        t.start();


    }
    //verifca que la fecha del ultimo registo en el móvil no se encuntre en exportado. para no volve a exportar o duplicar datos en app web.
    public void verificarFecha(final String date, final int ulIdLectura){
        String Url = "http://"+etUrl.getText().toString()+"/Apr/modelo/verificarFecha.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

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
       /* Log.i("Exportación- Nº Medidor", codigo);
        Log.i("Exportación- Lectura", lectura);
        Log.i("Exportación- Fecha", fecha);
        Log.i("Exportación- Monto", monto);
        Log.i("Exportación- MetrosC", metrosc);
        Log.i("Exportación-", "**************************");*/

        String Url = "http://"+etUrl.getText().toString() + "/Apr/modelo/cargarDatos.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
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

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        VerificadorTransferencia verificadorTransferencia = new VerificadorTransferencia(etUrl.getText().toString(), getApplicationContext(), tvRlecturas, tvRlecturas,tvRlecturas,tvRlecturas,tvRlecturas );
                        verificadorTransferencia.verificarLecturas();
                    }
                }, 2000);

            }
        });

    }

}
