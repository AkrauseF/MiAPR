package com.example.miapr;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DescargarLecturanAnt extends AppCompatActivity {
    String url;
    Context context;
    public DescargarLecturanAnt(String url, Context context){
        this.url = url;
        this.context=context;
    }

    public void descargaLecturasAnteriores(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        String idMedidores = databaseAccess.getIdMedidores();
        String[] ListaidMed = idMedidores.split(",");
        //
        //Log.i("KRALista:", idMedidores);


        //Log.i("Cantidad::",ultimoId[0]);
        String Url = url+"/Apr/modelo/descargaLecturasAnteriores.php";

        //Integer ulId = Integer.parseInt(ultimoId[0]); //cantidad de medidores en movil
        //Toast.makeText(this, ultimoId[0], Toast.LENGTH_SHORT).show();

        for(int cont=1; cont < ListaidMed.length; cont++){
            //int cont=1;
            //while (cont <= ulId){ //trae las lecturas anteriores de los medidores en el movil
            String num= Integer.toString(cont);
            // Log.i("KRA-num:", num);

            final String numeroMedidor= databaseAccess.getCodigoMedidor(ListaidMed[cont]);
            //databaseAccess.insertarLecturaAnt(numeroMedidor, num);
            // Log.i("KRA-numeroMedidor:", numeroMedidor);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url+"?var='"+numeroMedidor+"'", new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    //  Log.i("KRA-response:", response);
                    //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    if(response.equals("")){
                        //Toast.makeText(getApplicationContext(), "false",Toast.LENGTH_SHORT).show();
                        String lectura ="0";
                        String medidor =numeroMedidor;

                        // Toast.makeText(getApplicationContext(), "lectura: "+lectura+"-Medidor: "+medidor, Toast.LENGTH_LONG).show();
                        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                        databaseAccess.open();

                        databaseAccess.insertarRegistros(lectura, medidor);
                    }else{
                        //Toast.makeText(getApplicationContext(), "true",Toast.LENGTH_SHORT).show();
                        String[] respuesta = response.split(",");
                        //LOS FECHAS DE LOS REGISTROS DEBEN SER DE UN DIA ANTERIOR PARA QUE FUNCIONE EL SIGUINTE BLOQUE.
                        String lectura =respuesta[0];
                        String medidor =respuesta[1];

                        // Toast.makeText(getApplicationContext(), "lectura: "+lectura+"-Medidor: "+medidor, Toast.LENGTH_LONG).show();
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
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            //cont++;
        }
        Toast.makeText(context, "Lecturas anteriores importadas correctamente", Toast.LENGTH_SHORT).show();
        // prueba();// valida que la lectura anterior junto con su medidor se encuentren en la tabla de registros de lecturas.

    }
}
