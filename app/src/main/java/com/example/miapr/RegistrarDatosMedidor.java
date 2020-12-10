package com.example.miapr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class RegistrarDatosMedidor extends AppCompatActivity {

    Button btCapturaQr;
    EditText etNumMedidor;
    TextView tvQR;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_datos_medidor);

        btCapturaQr= findViewById(R.id.btCapturaQR);
        tvQR = findViewById(R.id.tvQR);

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
                tvQR.setText(result.getContents());//imprime código por pantalla

                 super.onActivityResult(requestCode, resultCode, data);

            }
        }

    }
}
