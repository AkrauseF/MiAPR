package com.example.miapr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void consulUltId() {
        btImportar.setEnabled(false);

        //Toast.makeText(this,"Importando datos desde el servidor",Toast.LENGTH_LONG).show();
        VerificaConexion verificaConexion = new VerificaConexion(etUrl.getText().toString());
        if (!verificaConexion.executeCommand()) {
            Toast.makeText(this, "Error de conexión con el Servidor", Toast.LENGTH_LONG).show();

        }else {
            String Url = "http://"+etUrl.getText().toString() + "/Apr/modelo/consultaID.php"; //obtiene el la cantidad de registros de medidores en appweb.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    conexionUnophp(response, "0");//obtiene los medidores de appweb y los inserta en la appmovil
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

    }
     public void conexionUnophp(final String id, String str) {

        final String Url = "http://"+etUrl.getText().toString()+"/Apr/modelo/descargarDatos.php";
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
                     VerificaConexion verificaConexion = new VerificaConexion(etUrl.getText().toString());
                     if(!verificaConexion.executeCommand()){
                         break;
                     }else{
                     progress.setProgress(num);
                     SystemClock.sleep(500);
                     final StringRequest stringRequest = new StringRequest(Request.Method.POST, Url+"?var='"+num+"'", new Response.Listener<String>() { //envia el id de la tabla de medidores.

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
                             //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                             Toast.makeText(getApplicationContext(), "Error en la tranferencia de medidores", Toast.LENGTH_LONG).show();

                         }
                     });
                     RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                     requestQueue.add(stringRequest);


                     //Toast.makeText(getApplicationContext(), stringRequest.toString(), Toast.LENGTH_LONG).show();
                     num = num + 1;
                 }}

                 progress.dismiss();
                 consultaIdOperadores();
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
         final String Url = "http://"+etUrl.getText().toString()+"/Apr/modelo/descargaLecturasAnteriores.php";

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

                 for (int cont = 1; cont < ListaidMed.length; cont++) {
                     VerificaConexion verificaConexion = new VerificaConexion(etUrl.getText().toString());
                     if (!verificaConexion.executeCommand()) {
                         break;
                     }else{
                     progress.setProgress(cont);
                     SystemClock.sleep(500);

                     //String num= Integer.toString(cont);
                     final String numeroMedidor = databaseAccess.getCodigoMedidor(ListaidMed[cont]);
                     StringRequest stringRequest = new StringRequest(Request.Method.POST, Url + "?var='" + numeroMedidor + "'", new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {
                             Log.i("KRA2::", response);
                             Log.i("KRA::", "prueba2");


                             if (response.equals("")) {
                                 String lectura = "0";
                                 String medidor = numeroMedidor;
                                 Log.i("KRA-??:", "si");
                                 DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                                 databaseAccess.open();
                                 databaseAccess.insertarRegistros(lectura, medidor);

                             } else {
                                 String[] respuesta = response.split(",");
                                 String lectura = respuesta[0];
                                 String medidor = respuesta[1];
                                 DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                                 databaseAccess.open();
                                 databaseAccess.insertarRegistros(lectura, medidor);

                             }

                         }
                     }, new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                             Toast.makeText(getApplicationContext(), "Error en la transferencia de lecturas", Toast.LENGTH_LONG).show();
                         }
                     });
                     RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                     requestQueue.add(stringRequest);
                 }}
                 progress.dismiss();
                 new Handler(Looper.getMainLooper()).post(new Runnable() {
                     @Override
                     public void run() {
                         Toast.makeText(getApplicationContext(), "Se importaron " + (ListaidMed.length - 1) + " clientes juntos con sus medidores asociados.", Toast.LENGTH_LONG).show();

                         VerificadorTransferencia verificadorTransferencia = new VerificadorTransferencia(etUrl.getText().toString(), getApplicationContext());

                         verificadorTransferencia.verificarMedidores();
                         verificadorTransferencia.verificarClientes();
                         verificadorTransferencia.verificarDatosCobros();
                         verificadorTransferencia.verificarLecturas();
                         verificadorTransferencia.verificarOperadores();
                     }
                 });
             }

         };
         t.start();

     }

     private void descargarDatosCobros(){
         VerificaConexion verificaConexion = new VerificaConexion(etUrl.getText().toString());
         if (!verificaConexion.executeCommand()) {
             Toast.makeText(this, "Error en la transferencia de Datos de cobros", Toast.LENGTH_LONG).show();

         }else {
             String Url = "http://"+etUrl.getText().toString() + "/Apr/modelo/descargarDatosCobros.php";
             StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {

                     //Toast.makeText(getApplicationContext(), "Respuesta datos cobros::-"+response, Toast.LENGTH_SHORT).show();

                     String[] respuesta = response.split(",");

                     String cargoFijo = respuesta[0];
                     String metrosSubsidio = respuesta[1];
                     String valorMetro = respuesta[2];

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
     }

     private void descargarCliente(final int ultimoId){

        final String Url = "http://"+etUrl.getText().toString()+"/Apr/modelo/descargaClientes.php";

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
                     VerificaConexion verificaConexion = new VerificaConexion(etUrl.getText().toString());
                     if (!verificaConexion.executeCommand()) {
                        break;
                     }else{
                     progress.setProgress(contador);
                     SystemClock.sleep(500);

                     String var= String.valueOf(contador);
                     StringRequest stringRequest = new StringRequest(Request.Method.POST, Url+"?var='"+var+"'", new Response.Listener<String>() {

                         @Override
                         public void onResponse(String response) {
                             // Toast.makeText(getApplicationContext(), "respuesta: "+response, Toast.LENGTH_SHORT ).show();
                             Log.i("kra-rut", "prueba");
                             String[] respuesta = response.split(",");
                             String rut =respuesta[0];
                             String nombre =respuesta[1];
                             String apellido =respuesta[2];
                             String direccion =respuesta[3];
                             String subsidio =respuesta[4];
                             Log.i("kra-rut", rut);
                             Log.i("kra-subsidio", subsidio);

                             String numSitio =respuesta[5];
                             String idMedidor =respuesta[6];

                             DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                             databaseAccess.open();
                             databaseAccess.insertarClientes(rut, nombre, apellido, direccion, subsidio, numSitio, idMedidor);

                             //Consulta en bbdd interna existencia de datos exportados.
                             //compara datos obtenidos de la consulta con los obtenido de la bbdd externa.
                             //si son iguales, conitunar con el bucle.
                             //si son distintos terminar con el bucle y borrar vaciar la tablas correspondientes.
                             //emitir error de inportación de clientes de datos,
                         }
                     }, new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                             Toast.makeText(getApplicationContext(), "Error en la transferencia de Clientes", Toast.LENGTH_LONG).show();

                         }
                     });
                     RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                     requestQueue.add(stringRequest);

                     contador++;
                 }}
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

    private void descargarOperadores(final int ultimoId){

        final String Url = "http://"+etUrl.getText().toString()+"/Apr/modelo/descargarOperadores.php";


                int contador = 1;
                while (contador <= ultimoId){
                    VerificaConexion verificaConexion = new VerificaConexion(etUrl.getText().toString());
                    if (!verificaConexion.executeCommand()) {
                        break;
                    }else{
                        progress.setProgress(contador);
                        SystemClock.sleep(500);

                        String var= String.valueOf(contador);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url+"?var='"+var+"'", new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                // Toast.makeText(getApplicationContext(), "respuesta: "+response, Toast.LENGTH_SHORT ).show();
                                Log.i("kra-rut", "prueba");
                                String[] respuesta = response.split(",");
                                String usuario =respuesta[0];
                                String contrasena =respuesta[1];

                                Log.i("kra-user", usuario);
                                Log.i("kra-user", contrasena);

                                Toast.makeText(getApplicationContext(), "Usuario: "+usuario, Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "Contrasena: "+contrasena, Toast.LENGTH_LONG).show();


                                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                                databaseAccess.open();
                                databaseAccess.insertarOperadores(usuario, contrasena);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "Error en la transferencia de Operadores", Toast.LENGTH_LONG).show();

                            }
                        });
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);

                        contador++;
                    }}

}

     private void consultaIdCliente(){
         VerificaConexion verificaConexion = new VerificaConexion(etUrl.getText().toString());
         if (!verificaConexion.executeCommand()) {
             Toast.makeText(this, "Error en la transferencia de Clientes*", Toast.LENGTH_LONG).show();

         }else {
             String Url = "http://"+etUrl.getText().toString() + "/Apr/modelo/consultaIdClientes.php"; //obtiene el la cantidad de registros de medidores en appweb.
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
     }

    private void consultaIdOperadores(){
        VerificaConexion verificaConexion = new VerificaConexion(etUrl.getText().toString());

        if (!verificaConexion.executeCommand()) {
            Toast.makeText(this, "Error en la transferencia de Operadores*", Toast.LENGTH_LONG).show();

        }else {
            String Url = "http://"+etUrl.getText().toString() + "/Apr/modelo/consultaIdOperadores.php"; //obtiene el la cantidad de registros de medidores en appweb.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    // Toast.makeText(getApplicationContext(), "respuesta cliente id+++++:::"+response, Toast.LENGTH_SHORT ).show();

                    int ultimoId = Integer.parseInt(response);
                    descargarOperadores(ultimoId);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error en traspaso de Operadores", Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }


    private void borrartabla(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        databaseAccess.VaciarMedidores();
        databaseAccess.VaciarLecturas();
        databaseAccess.VaciarDatosCobros();
        databaseAccess.VaciarClientes();
        databaseAccess.VaciarOperadores();

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