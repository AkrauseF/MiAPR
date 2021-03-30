package com.example.miapr;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class AdaptadorRegistros extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    String[][] datos;

    public AdaptadorRegistros(Context conexto, String[][] datos)
    {
        this.contexto = conexto;
        this.datos = datos;

        inflater = (LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista_registros, null);

        TextView medidor = (TextView) vista.findViewById(R.id.tvMedidor);
       TextView subsidio = (TextView) vista.findViewById(R.id.tvSubsidio);
        TextView lectura = (TextView) vista.findViewById(R.id.tvlectura);
        TextView fecha = (TextView) vista.findViewById(R.id.tvfecha);
       // TextView monto = (TextView) vista.findViewById(R.id.tvmonto);
       // TextView metroc = (TextView) vista.findViewById(R.id.tvcubos);
        TextView lecturaAnt = (TextView) vista.findViewById(R.id.tvLecturaAnt);

        medidor.setText("Medidor: "+datos[i][0]);
        if(datos[i][1] ==null){
            lectura.setText("Lectura: "+datos[i][1]);
            lectura.setTypeface(lectura.getTypeface(), Typeface.BOLD);
            lectura.setTextColor(Color.RED);
        }else{
            lectura.setText("Lectura: "+datos[i][1]);
        }
        //lectura.setText("Lectura: "+datos[i][1]);
        fecha.setText("Fecha: "+datos[i][2]);
       // monto.setText("Monto: "+datos[i][3]);
       // metroc.setText("Metros C: "+datos[i][4]);
        lecturaAnt.setText("Lectura Ant: "+datos[i][3]);
       subsidio.setText("Subsidio: "+datos[i][4]);

        //codigo.setText("Hola");
        //marca.setText("Mundo");
        Log.i("Visual Medidor : ", medidor.getText().toString());
        Log.i("Visual Lectura : ", lectura.getText().toString());
        Log.i("Visual fecha : ", fecha.getText().toString());
        Log.i("Visual Lectura Ant : ", lecturaAnt.getText().toString());


        return vista;
    }
    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}

