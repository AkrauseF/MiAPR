package com.example.miapr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class RegistrarDatosMedidor extends AppCompatActivity {

    Button btCapturaQr, btregistrar;
    EditText etNumMedidor, etNumLectura;
    TextView tvValidar, tvLectAnt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_datos_medidor);

        btCapturaQr= findViewById(R.id.btImportar);
        btCapturaQr= findViewById(R.id.btRegistrar);
        etNumMedidor= findViewById(R.id.etNumMedidor);
        etNumLectura= findViewById(R.id.etNumLectura);
        tvValidar= findViewById(R.id.tvValidacion);
        tvLectAnt=findViewById(R.id.tvLectAnt);

        etNumLectura.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    if(!validadorLargoString(etNumMedidor.getText().toString())){
                        etNumMedidor.setText("Error");
                        tvValidar.setText("El largo del número exede el máximo permitido");
                        tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvValidar.setTextColor(Color.RED);
                    }else if(!validadorCaractersString(etNumMedidor.getText().toString())) {
                        etNumMedidor.setText("Error");
                        tvValidar.setText("El código capturado no corresponde a un número de medior");
                        tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvValidar.setTextColor(Color.RED);
                    }else if(!validadorExistenciaMedidor(etNumMedidor.getText().toString())) {
                        etNumMedidor.setText("Error");
                        tvValidar.setText("No existe en base de datos");
                        tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvValidar.setTextColor(Color.RED);
                    }else if (etNumMedidor.getText().toString().equals("")){
                        etNumMedidor.setText("Error");
                        tvValidar.setText("Ingrese o capture número de medidor");
                        tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvValidar.setTextColor(Color.RED);
                    }else {
                        etNumMedidor.setText(etNumMedidor.getText().toString());
                        tvValidar.setText("Código correcto");
                        tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvValidar.setTextColor(Color.BLUE);
                    }
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    String medidor = etNumMedidor.getText().toString();
                    String lecturaAnterior = databaseAccess.getLecturaAnterior(medidor);
                    if(lecturaAnterior.equals("0")){
                        tvLectAnt.setText("ATENCIÓN!!, Este medidor no tiene lectura anterior.");
                        tvLectAnt.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvLectAnt.setTextColor(Color.RED);
                    }else{
                        tvLectAnt.setText("Lectura anterior: "+lecturaAnterior);
                        //tvLectAnt.setTextColor(Color.);
                        tvLectAnt.setTextColor(Color.parseColor("#f1b434"));
                    }

                }
                else{
                    // Do something when Focus is not on the EditText
                }
            }
        });

        etNumMedidor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    etNumMedidor.setText("");

                }
                else{
                    // Do something when Focus is not on the EditText
                }
            }
        });

        //test();
        //testInsertarCobro();

    }

    public void escanearNumeroMedidor(View view){

        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("Escanear Codigo");
        intent.setCameraId(0);
        intent.setOrientationLocked(true);
        intent.setBeepEnabled(false);
        intent.setCaptureActivity(Capturar.class);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents()== null){
                Toast.makeText(this, "Cancelaste escaneo", Toast.LENGTH_SHORT).show();
            }else {

                if(!validadorLargoString(result.getContents())){
                    etNumMedidor.setText("Error");
                    tvValidar.setText("El largo del número exede el máximo permitido");
                    tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                    tvValidar.setTextColor(Color.RED);
                }else if(!validadorCaractersString(result.getContents())) {
                    etNumMedidor.setText("Error");
                    tvValidar.setText("El código capturado no corresponde a un número de medior");
                    tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                    tvValidar.setTextColor(Color.RED);
                }else if(!validadorExistenciaMedidor(result.getContents())){
                    etNumMedidor.setText("Error");
                    tvValidar.setText("No existe en base de datos");
                    tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                    tvValidar.setTextColor(Color.RED);
                }else {
                    etNumMedidor.setText(result.getContents());
                    tvValidar.setText("Código correcto");
                    tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                    tvValidar.setTextColor(Color.BLUE);
                }

                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private Boolean validadorEdicionMedidor(final String result){


                    if(!validadorLargoString(result)){
                        etNumMedidor.setText("Error");
                        tvValidar.setText("El largo del número exede el máximo permitido");
                        tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvValidar.setTextColor(Color.RED);
                        return false;
                    }else if(!validadorCaractersString(result)) {
                        etNumMedidor.setText("Error");
                        tvValidar.setText("El código capturado no corresponde a un número de medior");
                        tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvValidar.setTextColor(Color.RED);
                        return false;

                    }else if(!validadorExistenciaMedidor(result)){
                        etNumMedidor.setText("Error");
                        tvValidar.setText("No existe en base de datos");
                        tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvValidar.setTextColor(Color.RED);
                        return false;

                    }else {
                        etNumMedidor.setText(result);
                        tvValidar.setText("Código correcto");
                        tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                        tvValidar.setTextColor(Color.BLUE);
                        return true;

                    }

    }
    private boolean validadorLargoString(String str){ //comprueba que el largo del string sea no mas de 15 caractéres, para evitar denegación de servicio.
        if(str.length() > 15){
            return false;
        }else {
            return true;
        }
    }

    private boolean validadorCaractersString(String str){
        Boolean flag=true;
        String num="1234567890";
        for(int i=0; i< str.length(); i++){
            if(num.indexOf(str.charAt(i)) < 0){ //recorre la cadena capturada y verifica que cada caracter se encuentre en la cadena de números
                flag=false;
                break;
            }
        }
        return flag;
    }
    private boolean validadorExistenciaMedidor(String medidor){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String response= databaseAccess.getNumMedidor(medidor);
        String lecturaAnterior = databaseAccess.getLecturaAnterior(medidor);

        tvLectAnt.setText("Lectura anterior:"+lecturaAnterior);

        if(response.equals(medidor)){
            return true;
        }else{
            return false;
        }
    }

    public void registrarLectura(View view){

        String NumMedidor = etNumMedidor.getText().toString();
        String NumLectura = etNumLectura.getText().toString();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        if(TextUtils.isEmpty(NumLectura) || TextUtils.isEmpty(NumMedidor)){
            tvValidar.setText("Debes completar los campos");
            tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
            tvValidar.setTextColor(Color.RED);
        }else{

            String lecturaAnterior = databaseAccess.getLecturaAnterior(NumMedidor);

            //Toast.makeText(this, "Lectura anterior:::"+lecturaAnterior, Toast.LENGTH_SHORT).show();
            int lecAnt=Integer.parseInt(lecturaAnterior);

            String [] datosCobros = databaseAccess.getDatosCobros();

            String cargoFijo= datosCobros[0];
            String metrosSubsidio= datosCobros[1];
            String valorMetro= datosCobros[2];

            int cargo_fijo = Integer.parseInt(cargoFijo);
            int metros_sub = Integer.parseInt(metrosSubsidio);
            int valor_metro = Integer.parseInt(valorMetro);


            int subsidioMayor = cargo_fijo +  (metros_sub * 400); //8500
            int subsidioMenor = subsidioMayor / 2;//4250


            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String formattedDate = df.format(c);

            // Toast.makeText(this,formattedDate , Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Lectura anterior:::"+lecturaAnterior, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "cargoFijo:::"+datosCobros[0]+" metrosSubsidio:::"+datosCobros[1], Toast.LENGTH_SHORT).show();



            if(validadorEdicionMedidor(NumMedidor)){ // validacion de registro final
                databaseAccess.insertarLectura(NumLectura, NumMedidor);
                databaseAccess.insertarFecha(formattedDate, NumMedidor);
                Log.i("subs33", NumMedidor);

                String subsidio = databaseAccess.getSubsidioLecturas(NumMedidor);
                Log.i("subs332", subsidio);
                int subsidioD = Integer.parseInt(subsidio);
                int lecturaActual = Integer.parseInt(NumLectura);
                int metrosCubicos = lecturaActual - lecAnt;
                String metrosC = String.valueOf(metrosCubicos);
                databaseAccess.insertarMetrosC(metrosCubicos, NumMedidor);

                Intent i = new Intent(this, Imprimir.class);

                if(validadorLectura(lecturaActual, lecAnt )){
                    tvValidar.setText("Lectura anteriror es mayor que la actual");
                    tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                    tvValidar.setTextColor(Color.RED);

                } else if(subsidioD == 10){
                    if(metrosCubicos<=metros_sub){
                        int cobro = (metrosCubicos * valor_metro + cargo_fijo)/2;
                        if(cobro < 0){
                            cobro =0;
                            i.putExtra("cobro", cobro);
                            databaseAccess.insertarCobro(cobro, NumMedidor);
                        }else{
                            databaseAccess.insertarCobro(cobro, NumMedidor);
                            i.putExtra("cobro", String.valueOf(cobro));
                        }
                    }else {
                        int cobro = (metrosCubicos * valor_metro + cargo_fijo)-subsidioMenor;
                        if(cobro < 0){
                            cobro =0;
                            databaseAccess.insertarCobro(cobro, NumMedidor);
                            i.putExtra("cobro", String.valueOf(cobro));
                        }else{
                            databaseAccess.insertarCobro(cobro, NumMedidor);
                            i.putExtra("cobro", String.valueOf(cobro));
                        }

                    }

                    // etNumLectura.setText("");
                    //etNumMedidor.setText("");
                    //tvValidar.setText("");
                    //tvLectAnt.setText("");
                    Toast.makeText(this, "Registro de lectura exitoso 1", Toast.LENGTH_LONG).show();


                    i.putExtra("medidor", NumMedidor);
                    i.putExtra("lectura", NumLectura);
                    i.putExtra("metrosC", metrosC);
                    i.putExtra("subsidio", subsidio);
                    i.putExtra("fecha", formattedDate);
                    startActivity(i);

                }else if(subsidioD == 20){

                    int cobro = (metrosCubicos * valor_metro + cargo_fijo) - subsidioMayor;

                    if(cobro < 0){
                        cobro =0;
                        databaseAccess.insertarCobro(cobro, NumMedidor);
                        i.putExtra("cobro", String.valueOf(cobro));
                    }else {
                        databaseAccess.insertarCobro(cobro, NumMedidor);
                        i.putExtra("cobro", String.valueOf(cobro));

                    }
                    // etNumLectura.setText("");
                    //etNumMedidor.setText("");
                    //tvValidar.setText("");
                    //tvLectAnt.setText("");
                    Toast.makeText(this, "Registro de lectura exitoso 2", Toast.LENGTH_LONG).show();

                    i.putExtra("medidor", NumMedidor);
                    i.putExtra("lectura", NumLectura);
                    i.putExtra("metrosC", metrosC);
                    i.putExtra("subsidio", subsidio);
                    i.putExtra("fecha", formattedDate);
                    startActivity(i);
                }else{
                    int cobro = metrosCubicos * valor_metro + cargo_fijo;

                    databaseAccess.insertarCobro(cobro, NumMedidor);
                   // etNumLectura.setText("");
                    //etNumMedidor.setText("");
                    //tvValidar.setText("");
                    //tvLectAnt.setText("");
                    Toast.makeText(this, "Registro de lectura exitoso 3", Toast.LENGTH_LONG).show();

                    i.putExtra("medidor", NumMedidor);
                    i.putExtra("lectura", NumLectura);
                    i.putExtra("metrosC", metrosC);
                    i.putExtra("cobro", String.valueOf(cobro));
                    i.putExtra("subsidio", subsidio);
                    i.putExtra("fecha", formattedDate);

                    startActivity(i);
                }

            }else {
                Toast.makeText(this, "Datos incorrectos para realizar el registro", Toast.LENGTH_SHORT).show();
            }



        }


    }

    public boolean validadorLectura(int lecturaActual, int lecAnt ) {
        if (lecturaActual < lecAnt) {
            return true;
        } else {
            return false;
        }
    }

    public void prueba(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        int num=1;
        while (num <= 4) {
            String[] registroLecturas = databaseAccess.pruebas(num);
            Toast.makeText(this, registroLecturas[0]+"-"+registroLecturas[1]+"-"+registroLecturas[2]+"-"+registroLecturas[3]+"-"+registroLecturas[4]+"-"+registroLecturas[5], Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "medidor: "+registroLecturas[0]+"lectura :"+registroLecturas[1], Toast.LENGTH_SHORT).show();
            num++;
        }
        databaseAccess.close();
    }

    /*public void test(){

        int [] subsidios = {10, 20, 0};
        int [] M3 = {15, 19};
        for(int i=0; i < subsidios.length; i++){
            for(int e=0; e < M3.length; e++){
                testCalculoCobro(M3[e], subsidios[i], 400, 2500);
            }
        }
    }

   public void testCalculoCobro(int metrosCubicos, int subsidioD, int valor_metro, int cargo_fijo ){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Log.i("cal-M3: ",String.valueOf(metrosCubicos));
        Log.i("cal-Subsidio: ",String.valueOf(subsidioD));

        int subsidioMayor = cargo_fijo +  (15 * 400); //8500
        int subsidioMenor = subsidioMayor / 2;//4250

        if(subsidioD == 10) {
            if (metrosCubicos <= 15) {
                int cobro = (metrosCubicos * valor_metro + cargo_fijo) / 2;
                Log.i("cal-Resultado Calculo: ", String.valueOf(cobro));
                Log.i("cal- ", "**********************");
                if (cobro < 0) {
                    cobro = 0;
                    Log.i("cal-Resultado Calculo: ", String.valueOf(cobro));
                    Log.i("cal- ", "**********************");
                }
            } else {
                int cobro = (metrosCubicos * valor_metro + cargo_fijo) - subsidioMenor;
                if (cobro < 0) {
                    cobro = 0;
                    Log.i("cal-Resultado Calculo: ", String.valueOf(cobro));
                    Log.i("cal- ", "**********************");

                } else {
                    Log.i("cal-Resultado Calculo: ", String.valueOf(cobro));
                    Log.i("cal- ", "**********************");
                }
            }
        }else if(subsidioD == 20){
            int cobro = (metrosCubicos * valor_metro + cargo_fijo) - subsidioMayor;
            if(cobro < 0){
                cobro =0;
                Log.i("cal-Resultado Calculo: ", String.valueOf(cobro));
                Log.i("cal- ", "**********************");
            }else {
                Log.i("cal-Resultado Calculo: ", String.valueOf(cobro));
                Log.i("cal- ", "**********************");
            }
        }else{
            int cobro = metrosCubicos * valor_metro + cargo_fijo;
            Log.i("cal-Resultado Calculo: ", String.valueOf(cobro));
            Log.i("cal- ", "**********************");
        }
    }*/

    public void testInsertarCobro(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        int[] cobros= {4000,4250,5000,0,3000,1000,2370,4500,8500,7000};
        String[] mdidores= {"1111", "2222","3333","4444","5555","6666","7777","8888","9999","1000"};

        int cont=1;
        for(int i=0; i < cobros.length; i++ ){
            Log.i("Ins- Cobro: ", String.valueOf(cobros[i]));
            Log.i("Ins- Medidor: ", mdidores[i]);
            //Inserción de cobro en base de datos
            databaseAccess.insertarCobro(cobros[i], mdidores[i]);

            //Verificación de cobro insertado
            String [] datos = databaseAccess.getRegistros(cont);
            Log.i("Ins- Cobro en BBDD: ", datos[3]);
            Log.i("Ins-", "*********************");

            cont++;
        }
    }



}
