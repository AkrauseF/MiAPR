package com.example.miapr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
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

    Button btImportar, btImportar2;
    EditText etUrl;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar);
        btImportar2 = findViewById(R.id.btImportar2);
        btImportar = findViewById(R.id.btImportar);
        etUrl = (EditText) findViewById(R.id.etUri);
        btImportar.setEnabled(true);
        btImportar2.setVisibility(View.INVISIBLE);
        btImportar2.setEnabled(false);
        btImportar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                consulUltId();

            }
        });
        /*btImportar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                descargaLecturasAnteriores();

            }
        });*/
    }

    public void consulUltId() {
        btImportar.setEnabled(false);

        //Toast.makeText(this,"Importando datos desde el servidor",Toast.LENGTH_LONG).show();


        String Url = etUrl.getText().toString()+"/Apr/modelo/consultaID.php"; //obtiene el la cantidad de registros de medidores en appweb.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                conexionUnophp(response);//obtiene los medidores de appweb y los inserta en la appmovil
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
     public void conexionUnophp(final String id) {
        final String Url = etUrl.getText().toString()+"/Apr/modelo/descargarDatos.php";
        //Toast.makeText(this, "Se importarán "+id+" registros de medidores", Toast.LENGTH_LONG).show();

        borrartabla();//borra la tabla de medidores de app movil antes de insertar los nuevos medidores exportados

         final int ide = Integer.parseInt(id);
         progress=new ProgressDialog(this);
         progress.setMessage("Importando "+id+" Medidores...");
         progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
         //progress.setIndeterminate(true);
         progress.setProgress(0);
         progress.show();
         progress.setMax(ide);
         final Thread t = new Thread() {
             @Override
             public void run() {


                 Integer num = 1;
                 while (num <= ide ){
                     progress.setProgress(num);
                     SystemClock.sleep(500);
                     StringRequest stringRequest = new StringRequest(Request.Method.POST, Url+"?var='"+num+"'", new Response.Listener<String>() { //envia el id de la tabla de medidores.

                         @Override
                         public void onResponse(String response) {
                             String[] respuesta = response.split(",");

                             String numero =respuesta[0];
                             String marca =respuesta[1];
                             String id =respuesta[2];
                             crearTabla(numero,marca, id);

                         }
                     }, new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                         }
                     });
                     RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                     requestQueue.add(stringRequest);

                     num = num + 1;
                 }


                 progress.dismiss();
                 descargarDatosCobros();
                 consultaIdCliente();

             }
         };
         t.start();

     }


     public void descargaLecturasAnteriores(){
         btImportar2.setEnabled(false);
         //Toast.makeText(this, "Importanto lecturas del mes anterior", Toast.LENGTH_LONG).show();
         final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
         databaseAccess.open();

         String idMedidores = databaseAccess.getIdMedidores();
         final String[] ListaidMed = idMedidores.split(",");
         Log.i("KRA2::","prueba1");
         final String Url = etUrl.getText().toString()+"/Apr/modelo/descargaLecturasAnteriores.php";

         progress=new ProgressDialog(this);
         progress.setMessage("Importando lecturas del mes anterior...");
         progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
         //progress.setIndeterminate(true);
         progress.setProgress(0);
         progress.show();

         progress.setMax(ListaidMed.length -1);
         final Thread t = new Thread() {
             @Override
             public void run() {

                 for(int cont=1; cont < ListaidMed.length; cont++){

                     progress.setProgress(cont);
                     SystemClock.sleep(500);

                     //String num= Integer.toString(cont);
                     final String numeroMedidor= databaseAccess.getCodigoMedidor(ListaidMed[cont]);
                     StringRequest stringRequest = new StringRequest(Request.Method.POST, Url+"?var='"+numeroMedidor+"'", new Response.Listener<String>(){
                         @Override
                         public void onResponse(String response) {
                             Log.i("KRA2::",response);
                             Log.i("KRA::","prueba2");


                             if(response.equals("")){
                                 String lectura ="0";
                                 String medidor =numeroMedidor;
                                 Log.i("KRA-??:", "si");
                                 DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                                 databaseAccess.open();
                                 databaseAccess.insertarRegistros(lectura, medidor);
                             }else{
                                 String[] respuesta = response.split(",");
                                 String lectura =respuesta[0];
                                 String medidor =respuesta[1];
                                 DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                                 databaseAccess.open();
                                 databaseAccess.insertarRegistros(lectura, medidor);
                             }

                         }
                     }, new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                         }
                     });
                     RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                     requestQueue.add(stringRequest);
                 }
                 progress.dismiss();
                 new Handler(Looper.getMainLooper()).post(new Runnable() {
                     @Override
                     public void run() {
                         Toast.makeText(getApplicationContext(), "Se importaron "+(ListaidMed.length-1)+" clientes juntos con sus medidores asociados.", Toast.LENGTH_LONG).show();

                     }
                 });
             }
         };
         t.start();

     }

     private void descargarDatosCobros(){
         String Url = etUrl.getText().toString()+"/Apr/modelo/descargarDatosCobros.php";
         StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>(){
             @Override
             public void onResponse(String response) {

                 //Toast.makeText(getApplicationContext(), "Respuesta datos cobros::-"+response, Toast.LENGTH_SHORT).show();

                 String[] respuesta = response.split(",");

                 String cargoFijo =respuesta[0];
                 String metrosSubsidio =respuesta[1];
                 String valorMetro =respuesta[2];

                 DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                 databaseAccess.open();

                 databaseAccess.insertarDatosCobros(cargoFijo, metrosSubsidio, valorMetro);

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
     private void descargarCliente(final int ultimoId){

        final String Url = etUrl.getText().toString()+"/Apr/modelo/descargaClientes.php";

         progress=new ProgressDialog(this);
         progress.setMessage("Importando Clientes...");
         progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
         //progress.setIndeterminate(true);
         progress.setProgress(0);
         progress.show();

         progress.setMax(ultimoId);
         final Thread t = new Thread() {
             @Override
             public void run() {

                 int contador = 1;
                 while (contador <= ultimoId){
                     progress.setProgress(contador);
                     SystemClock.sleep(500);

                     String var= String.valueOf(contador);
                     StringRequest stringRequest = new StringRequest(Request.Method.POST, Url+"?var='"+var+"'", new Response.Listener<String>() {

                         @Override
                         public void onResponse(String response) {
                             // Toast.makeText(getApplicationContext(), "respuesta: "+response, Toast.LENGTH_SHORT ).show();

                             String[] respuesta = response.split(",");

                             String rut =respuesta[0];
                             String nombre =respuesta[1];
                             String apellido =respuesta[2];
                             String direccion =respuesta[3];
                             String subsidio =respuesta[4];
                             String numSitio =respuesta[5];
                             String idMedidor =respuesta[6];

                             DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                             databaseAccess.open();
                             databaseAccess.insertarClientes(rut, nombre, apellido, direccion, subsidio, numSitio, idMedidor);

                         }
                     }, new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                         }
                     });
                     RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                     requestQueue.add(stringRequest);

                     contador++;
                 }
                 progress.dismiss();

                 new Handler(Looper.getMainLooper()).post(new Runnable() {
                     @Override
                     public void run() {
                         //btImportar2.setEnabled(true);
                        // btImportar2.setVisibility(View.VISIBLE);
                         descargaLecturasAnteriores();

                     }
                 });


             }

         };

         t.start();
     }

     private void consultaIdCliente(){
         String Url = etUrl.getText().toString()+"/Apr/modelo/consultaIdClientes.php"; //obtiene el la cantidad de registros de medidores en appweb.
         StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

             @Override
             public void onResponse(String response) {
                // Toast.makeText(getApplicationContext(), "respuesta cliente id+++++:::"+response, Toast.LENGTH_SHORT ).show();

                 int ultimoId = Integer.parseInt(response);
                 descargarCliente(ultimoId);

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


    private void borrartabla(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        databaseAccess.VaciarMedidores();

        databaseAccess.VaciarLecturas();
        databaseAccess.VaciarDatosCobros();
        databaseAccess.VaciarClientes();

    }
    private void crearTabla(String numero, String marca, String id){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        databaseAccess.insertarMedidoresM(numero,marca, id);
        databaseAccess.insertarMedidorL(numero);

    }

    public void prueba(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        int num=1;
        while (num <= 7) {
            String[] registroLecturas = databaseAccess.pruebas(num);
            Toast.makeText(this, registroLecturas[0]+"-"+registroLecturas[1]+"-"+registroLecturas[2]+"-"+registroLecturas[3]+"-"+registroLecturas[4]+"-"+registroLecturas[5]+"-"+registroLecturas[6], Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "medidor: "+registroLecturas[0]+"lectura ant:"+registroLecturas[1], Toast.LENGTH_SHORT).show();
            num++;
        }
        databaseAccess.close();
    }


}