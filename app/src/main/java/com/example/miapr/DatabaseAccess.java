package com.example.miapr;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);

    }


    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);

        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();

    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }


    public String getCodigoMedidor(String num) {
        c = db.rawQuery("select numero from medidores where id_medidor = '"+num+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
       while (c.moveToNext()) {
            String codigo = c.getString(0);

            buffer.append(codigo);

        }
        return buffer.toString();
    }

    public String getMarcaMedidor(String num) {
        c = db.rawQuery("select marca from medidores where id_medidor = '"+num+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String marca = c.getString(0);

            buffer.append(marca);

        }
        return buffer.toString();
    }

    public String getLecturas() {
        c = db.rawQuery("select id_medidor from lecturas", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String lecturas = c.getString(0);

            buffer.append(lecturas);

        }
        return buffer.toString();
    }

    public String UltimoId() {
        c = db.rawQuery("SELECT * FROM medidores ORDER BY id_medidor DESC LIMIT 1; ", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String ultimoId = c.getString(0);

            buffer.append(ultimoId);

        }
        return buffer.toString();
    }


    public void getVaciarTabla() {

        db.execSQL("delete from medidores");
        db.execSQL("delete from lecturas");

    }

    public void getInsetarTabla(String id, String numero, String marca) {
        db.execSQL("insert into medidores (id_medidor, numero, marca) " + "values ('"+id+"','"+numero+"','"+marca+"')");
        db.execSQL("insert into lecturas ( id_medidor, lectura, fecha, monto) " + "values ('"+id+"','"+null+"','"+null+"','"+null+"')");


    }

}

