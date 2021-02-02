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


    public String getMedidor() {
        c = db.rawQuery("select numero from medidores", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String numMedidor = c.getString(0);

            buffer.append(numMedidor);

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


    public void getVaciarTabla() {

        db.execSQL("delete from medidores");
        db.execSQL("delete from lecturas");

    }

    public void getInsetarTabla(String id, String numero, String marca) {
        db.execSQL("insert into medidores (id_medidor, numero, marca) " + "values ('"+id+"','"+numero+"','"+marca+"')");
        db.execSQL("insert into lecturas ( id_medidor, lectura, fecha, monto) " + "values ('"+id+"','"+null+"','"+null+"','"+null+"')");


    }

}

