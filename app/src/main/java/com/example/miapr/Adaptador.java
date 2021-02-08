package com.example.miapr;

import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    String[][] datos;

    public Adaptador(Context conexto, String[][] datos)
    {
        this.contexto = conexto;
        this.datos = datos;

        inflater = (LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        TextView codigo = (TextView) vista.findViewById(R.id.tvCodigo);
        TextView marca = (TextView) vista.findViewById(R.id.tvMarca);

        codigo.setText("Código"+datos[i][0]);
        marca.setText("Marca"+datos[i][1]);
        //codigo.setText("Hola");
        //marca.setText("Mundo");

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
