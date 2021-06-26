package com.example.miapr;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.UUID;


public class Imprimir extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;

    OutputStream outputStream;
    InputStream inputStream;
    Thread thread;

    TextView tvName;
    TextView edText;
    TextView tvNamePrinter;

    Button btnConnect;

    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprimir);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnConnect = (Button) findViewById(R.id.btnCon);
        Button btnDisConnect = (Button) findViewById(R.id.btnDesc);
        Button btnPrint = (Button) findViewById(R.id.btnImp);


        edText = (TextView) findViewById(R.id.edText);
        tvNamePrinter = (TextView) findViewById(R.id.tvImp);
        tvName =(TextView) findViewById(R.id.tvName);

        try{
            FindBluetoothDevice();
            openBluetoothPrinter();

        }catch (Exception ex){
            ex.printStackTrace();

        }

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String recepMedidor = getIntent().getStringExtra("medidor");
        String recpLectura = getIntent().getStringExtra("lectura");
        String recpMectrosC = getIntent().getStringExtra("metrosC");
        String recpCobro = getIntent().getStringExtra("cobro");
        String recpSubsidio = getIntent().getStringExtra("subsidio");
        String recpFecha = getIntent().getStringExtra("fecha");
        String lecturaAnt = databaseAccess.getLecturaAnterior(recepMedidor);

        String[] datosClientes = databaseAccess.getDatosClientes(recepMedidor);
        String nombre = datosClientes[0];
        String apellido = datosClientes[1];
        String direccion = datosClientes[2];
        String numSitio = datosClientes[3];

        String [] datosCobros = databaseAccess.getDatosCobros();
        String cargoFijo= datosCobros[0];
        String metrosSubsidio= datosCobros[1];
        String valorMetro= datosCobros[2];

        Date c = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(c);
        calendar.add(Calendar.MONTH,1);


        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(calendar.getTime());

        /*edText.setText("" +
                "************ VILLA SAN PEDRO, PUCON ************\n" +
                "************ COMITE DE AGUA POTABLE ************\n\n" +
                "*************** AVISO DE COBRANZA **************\n\n");*/

       edText.setText("" +
                "************ VILLA SAN PEDRO, PUCON ************\n" +
                "************ COMITE DE AGUA POTABLE ************\n\n" +
                "*************** AVISO DE COBRANZA **************\n\n" +
               "NOMBRE = "+nombre+" "+apellido+"\n\n" +
                "DIRECCION = "+direccion+"\n\n" +
                "NUMERO DE SITIO = "+numSitio+"\n\n" +
                "NUMERO MEDIDOR = "+recepMedidor+"\n\n" +
                "FECHA = "+recpFecha+"\n\n" +
                "LECTURA ANTERIOR = "+lecturaAnt+"\n\n" +
                "LECTURA ACTUAL = "+recpLectura+"\n\n" +
                "METROS CONSUMIDOS = "+recpMectrosC+"\n\n" +
                "VALOR CARGO FIJO = "+cargoFijo+"\n\n" +
                "VALOR CONSUMO DEL MES = "+recpCobro+"\n\n" +
                "SUBSIDIO = "+recpSubsidio+"\n\n" +
                //"SALDO ANTERIOR = 0\n\n" +
               // "MULTAS E INTERESES = 0\n\n" +
                "TOTAL A PAGAR = "+recpCobro+"\n\n" +
                "FECHA VENCIMIENTO = "+formattedDate+"\n\n"+
                "***************** OBSERVACIONES ****************\n\n" +
                ">ESTO NO ES VALIDO COMO\n" +
                "COMPROBANTE DE CANCELACION\n\n" +
                ">NO SE ACEPTAN CHEQUES\n\n\n\n");

        edText.setMovementMethod(new ScrollingMovementMethod());

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FindBluetoothDevice();
                    openBluetoothPrinter();

                }catch (Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(getApplicationContext(),"No se encunetra impresora...", Toast.LENGTH_LONG).show();



                }

            }
        });

        btnDisConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    disconnectBT();

                }catch (Exception ex){
                    ex.printStackTrace();


                }
            }
        });

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    printData();

                }catch (Exception ex){
                    ex.printStackTrace();

                }
            }
        });

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

    void FindBluetoothDevice(){
        try{

            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if(bluetoothAdapter==null){
                tvNamePrinter.setText("No se encontró adaptador de bluetooth");
                tvNamePrinter.setTypeface(tvNamePrinter.getTypeface(), Typeface.BOLD);
                tvNamePrinter.setTextColor(Color.RED);
            }
            if(bluetoothAdapter.isEnabled()){
                Intent enableBT= new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBT,0);
            }else{
                tvNamePrinter.setVisibility(View.INVISIBLE);
                tvName.setText("Impresora Desconectada");
                tvName.setTypeface(tvName.getTypeface(), Typeface.BOLD);
                tvName.setTextColor(Color.RED);
        }

            Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();

            if(pairedDevice.size()>0){
                for(BluetoothDevice pairedDev:pairedDevice){
                    if(pairedDev.getName().equals("MTP-3")){
                        bluetoothDevice=pairedDev;
                        tvName.setText("Impresora Conectada");
                        tvName.setTypeface(tvName.getTypeface(), Typeface.BOLD);
                        tvName.setTextColor(Color.BLUE);


                        tvNamePrinter.setVisibility(View.VISIBLE);
                        tvNamePrinter.setText("Nombre:"+pairedDev.getName());
                        tvNamePrinter.setTypeface(tvNamePrinter.getTypeface(), Typeface.BOLD);
                        tvNamePrinter.setTextColor(Color.BLUE);

                        btnConnect.setVisibility(View.INVISIBLE);

                        break;
                    }else{
                        tvName.setText("Impresora Desconectada");
                        tvName.setTypeface(tvName.getTypeface(), Typeface.BOLD);
                        tvName.setTextColor(Color.RED);

                    }
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    //abrir impresora bluetooth
    void openBluetoothPrinter() throws IOException{
        try{
            UUID uuidSting = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuidSting);
            bluetoothSocket.connect();
            outputStream=bluetoothSocket.getOutputStream();
            inputStream=bluetoothSocket.getInputStream();
            tvName.setText("Impresora Conectada");
            tvName.setTypeface(tvName.getTypeface(), Typeface.BOLD);
            tvName.setTextColor(Color.BLUE);
            Log.i("Imp-Estado: ", "Conectada");
            Log.i("Imp-: ", "**********************");

            beginListenData();
        }catch (Exception ex){
            tvName.setText("Impresora Apagada");
            tvName.setTypeface(tvName.getTypeface(), Typeface.BOLD);
            tvName.setTextColor(Color.RED);
            tvNamePrinter.setVisibility(View.INVISIBLE);
            btnConnect.setVisibility(View.VISIBLE);
            Log.i("Imp-Estado: ", "Desconectada");
            Log.i("Imp-: ", "**********************");

        }

    }
    void beginListenData(){
        try{
            final Handler handler = new Handler();
            final byte delimiter=10;
            stopWorker=false;
            readBufferPosition=0;
            readBuffer = new byte[1024];
            thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted() && !stopWorker){
                        try{
                            int byteAvailable = inputStream.available();
                            if(byteAvailable>0){
                                byte[] packetByte = new byte[byteAvailable];
                                inputStream.read(packetByte);

                                for(int i=0; i < byteAvailable; i++){
                                    byte b = packetByte[i];
                                    if(b==delimiter){
                                        byte[] encodeByte = new byte[readBufferPosition];
                                        System.arraycopy(readBuffer, 0, encodeByte, 0, encodeByte.length);

                                        final String data = new String(encodeByte,"US-ASCII");
                                        readBufferPosition=0;
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                tvNamePrinter.setText(data);
                                            }
                                        });
                                    }else {
                                        readBuffer[readBufferPosition++]=b;
                                    }
                                }
                            }
                        }catch (Exception ex){
                            stopWorker=true;

                        }
                    }

                }
            });
            thread.start();
        }catch (Exception ex){
            ex.printStackTrace();

        }
    }

    //Iprimir texto en Impresora bluetooth
    void printData() throws IOException{
        /*String [] textos ={"Hola a todos", "Proyecto de titulo", "Sprint 05", "A.P.R Villa San Pedro", "Profesor guia", "La casa de pedro", "Todo bien", "Asi no mas", "El finde llueve", "Falta poco"};
        for(int i=0; i<textos.length; i++) {
            edText.setText(textos[i]);*/
            try {
                String msg = edText.getText().toString();//
                msg += "\n\n";
                outputStream.write(msg.getBytes());
                tvNamePrinter.setText("Impresora texto...");
                Toast.makeText(this, "Imprimiendo...", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Impresión finalizada", Toast.LENGTH_LONG).show();
                disconnectBT();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

    }

    //Desconectar imprsora
    void disconnectBT() throws IOException{

        try{
            stopWorker=true;
            outputStream.close();
            inputStream.close();
            bluetoothSocket.close();
            tvName.setText("Impresora desconectada");
            tvName.setTypeface(tvName.getTypeface(), Typeface.BOLD);
            tvName.setTextColor(Color.RED);

            tvNamePrinter.setVisibility(View.INVISIBLE);
            btnConnect.setVisibility(View.VISIBLE);

        }catch (Exception ex){
            ex.printStackTrace();

        }
    }
}