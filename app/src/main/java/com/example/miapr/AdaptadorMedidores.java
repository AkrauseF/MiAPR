package com.example.miapr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class AdaptadorMedidores extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    String[][] datos;

    public AdaptadorMedidores(Context conexto, String[][] datos)
    {
        this.contexto = conexto;
        this.datos = datos;

        inflater = (LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista_medidores, null);

        TextView codigo = (TextView) vista.findViewById(R.id.tvCodigo);
        TextView marca = (TextView) vista.findViewById(R.id.tvMarca);
        TextView subsidio = (TextView) vista.findViewById(R.id.tvSubsidio);

        codigo.setText("Código: "+datos[i][0]);
        marca.setText("Marca: "+datos[i][1]);
        subsidio.setText("Subsidio: "+datos[i][2]);
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
