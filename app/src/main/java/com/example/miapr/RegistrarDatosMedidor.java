package com.example.miapr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class RegistrarDatosMedidor extends AppCompatActivity {

    Button btCapturaQr;
    EditText etNumMedidor;
    TextView tvValidar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_datos_medidor);

        btCapturaQr= findViewById(R.id.btImportar);
        etNumMedidor= findViewById(R.id.etNumMedidor);
        tvValidar= findViewById(R.id.tvValidacion);
        //tvQR = findViewById(R.id.tvQR);

        //selectMedidor();

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
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents()== null){
                Toast.makeText(this, "Cancelaste escaneo", Toast.LENGTH_SHORT).show();
            }else {
                if(!validadorLargoString(result.getContents())){
                    etNumMedidor.setText("Error");
                    tvValidar.setText("El largo del número exede el máximo permitido");
                    tvValidar.setTypeface(tvValidar.getTypeface(), Typeface.BOLD);
                    tvValidar.setTextColor(Color.RED);
                }else if(!validadorCaractersString(result.getContents())){
                    etNumMedidor.setText("Error");
                    tvValidar.setText("El código capturado no corresponde a un número de medior");
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

    /*private void selectMedidor(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String numMedidor = databaseAccess.getMedidor();

        etNumMedidor.setText(numMedidor);


        // Toast.makeText(this, numMedidor, Toast.LENGTH_LONG ).show();

    }*/
}
