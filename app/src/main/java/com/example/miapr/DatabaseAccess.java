package com.example.miapr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    public String[] UltimoIdDatosCobros() {
        String[] registros= new String[5];
        String ultimo=null;
        c = db.rawQuery("SELECT * FROM datosCobros ORDER BY id_datos DESC LIMIT 1; ", new String[]{});
        while (c.moveToNext()) {
            ultimo = c.getString(0);

        }
        registros[0]=ultimo;
        return registros;
    }

    public String[] UltimoIdOperadores() {
        String[] registros= new String[5];
        String ultimo=null;
        c = db.rawQuery("SELECT * FROM usuarios ORDER BY id_usuario DESC LIMIT 1; ", new String[]{});
        while (c.moveToNext()) {
            ultimo = c.getString(0);

        }
        registros[0]=ultimo;
        return registros;
    }

    public String[] UltimoIdCliente() {
        String[] registros= new String[5];
        String ultimo=null;
        c = db.rawQuery("SELECT * FROM clientes ORDER BY id_medidor DESC LIMIT 1; ", new String[]{});
        while (c.moveToNext()) {
            ultimo = c.getString(0);

        }
        registros[0]=ultimo;
        return registros;
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
    public String getNumMedidor(String num) {// para validar existencia de medidor al momento de registrar su lectura.
        c = db.rawQuery("select numero from medidores where numero = '"+num+"'", new String[]{});
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

    public String getSubsidioMedidor(String num) {
        c = db.rawQuery("SELECT subsidio FROM clientes WHERE id_medidor="+num, new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String codigo = c.getString(0);

            buffer.append(codigo);
        }
        return buffer.toString();
    }

    public String getSubsidioLecturas(String num) {
        c = db.rawQuery("SELECT subsidio FROM clientes INNER JOIN medidores ON medidores.id_medidor=clientes.id_medidor WHERE medidores.numero="+num, new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String codigo = c.getString(0);

            buffer.append(codigo);
        }
        return buffer.toString();
    }

    public String[] getDatosClientes(String num) {

        String[] registros= new String[4];
        String Nombre=null;
        String Apellido=null;
        String Direccion=null ;
        String NumSitio=null;


        c = db.rawQuery("SELECT clientes.nombre, clientes.apellido, clientes.direccion, clientes.num_sitio  FROM clientes INNER JOIN medidores ON medidores.id_medidor=clientes.id_medidor WHERE medidores.numero="+num, new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            Nombre = c.getString(0);
            Apellido = c.getString(1);
            Direccion = c.getString(2);
            NumSitio = c.getString(3);

        }
        registros[0]=Nombre;
        registros[1]=Apellido;
        registros[2]=Direccion;
        registros[3]=NumSitio;

        return registros;
    }

    public String getLecturaAnterior(String medidor) {
        c = db.rawQuery("select lectura_ant from lecturas where id_medidor = '" + medidor + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String lecturaAnterior = c.getString(0);
            buffer.append(lecturaAnterior);

        }
        Log.i("anterior", buffer.toString());
        Log.i("anterior", "buffer.toString()");
        String respuesta = buffer.toString();
         if(respuesta.equals("null")){
                return "0";

         }else{
             return buffer.toString();
        }

    }

    public String getIdMedidores() {
        c = db.rawQuery("select id_medidor from medidores", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String lecturaAnterior = c.getString(0);
            buffer.append(",");
            buffer.append(lecturaAnterior);

        }
        return buffer.toString();
    }


    public String[] getLecturas(int num) {

        String[] registros= new String[5];
        String id_medidor=null;
        String lectura=null ;
        String fecha=null;
        String monto=null ;
        String metrosc=null;

        c = db.rawQuery("select id_medidor, lectura, fecha, monto, metros_cubicos  from lecturas where id_lectura = '"+num+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (c.moveToNext()) {
             id_medidor = c.getString(0);
             lectura = c.getString(1);
             fecha = c.getString(2);
             monto = c.getString(3);
             metrosc = c.getString(4);
        }

        registros[0]=id_medidor;
        registros[1]=lectura;
        registros[2]=fecha;
        registros[3]=monto;
        registros[4]=metrosc;

        return registros;
    }
    public String[] getRegistros(int num) {

        String[] registros= new String[6];
        String medidor=null;
        String lectura=null ;
        String fecha=null;
        String monto=null ;
        String metrosc=null;
        String lectAnt=null;

        c = db.rawQuery("select id_medidor, lectura, fecha, monto, metros_cubicos, lectura_ant  from lecturas where id_lectura = '"+num+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (c.moveToNext()) {
            medidor = c.getString(0);
            lectura = c.getString(1);
            fecha = c.getString(2);
            monto = c.getString(3);
            metrosc = c.getString(4);
            lectAnt = c.getString(5);

        }

        registros[0]=medidor;
        registros[1]=lectura;
        registros[2]=fecha;
        registros[3]=monto;
        registros[4]=metrosc;
        registros[5]=lectAnt;



        return registros;
    }

    public String[]  getDatosCobros(){
        String[] registros= new String[3];
        String cargoFijo=null;
        String metrosSub=null ;
        String valorMetro= null;

        c = db.rawQuery("select cargo_fijo, metros_subsidio, valor_metro from datosCobros where id_datos = '1'", new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (c.moveToNext()) {
            cargoFijo = c.getString(0);
            metrosSub = c.getString(1);
            valorMetro = c.getString(2);

        }

        registros[0]=cargoFijo;
        registros[1]=metrosSub;
        registros[2]=valorMetro;




        return registros;
    }

    public String[]  getDatosMedidores(String id){
        String[] registros= new String[3];
        String idMedidor=null;
        String numero=null ;
        String marca= null;

        c = db.rawQuery("select * from medidores where id_medidor = '"+id+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (c.moveToNext()) {
            idMedidor = c.getString(0);
            numero = c.getString(1);
            marca = c.getString(2);

        }

        registros[0]=idMedidor;
        registros[1]=numero;
        registros[2]=marca;




        return registros;
    }

    public String[] UltimoIdMedidores() {
        String[] registros= new String[5];
        String ultimo=null;
        c = db.rawQuery("SELECT * FROM medidores ORDER BY id_medidor DESC LIMIT 1; ", new String[]{});
        while (c.moveToNext()) {
            ultimo = c.getString(0);

        }
        registros[0]=ultimo;
        return registros;
    }

    public String[] UltimoIdLectura() {
        String[] registros= new String[5];
        String ultimo=null;
        c = db.rawQuery("SELECT * FROM lecturas ORDER BY id_lectura DESC LIMIT 1; ", new String[]{});
        while (c.moveToNext()) {
            ultimo = c.getString(0);

        }
        registros[0]=ultimo;
        return registros;
    }

    public String UltimoIdLecturallllll() {
        c = db.rawQuery("SELECT * FROM lecturas ORDER BY id_lectura DESC LIMIT 1; ", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String ultimoIdLect = c.getString(0);

            buffer.append(ultimoIdLect);

        }
        return buffer.toString();
    }


    public void VaciarMedidores() {

        db.execSQL("delete from medidores");
        db.execSQL("update sqlite_sequence SET seq = 0 WHERE name ='medidores'");//reset autoincrement

    }
    public void VaciarLecturas() {
        Log.i("numeros: ", "prueba1");
        db.execSQL("delete from lecturas");
        db.execSQL("update sqlite_sequence SET seq = 0 WHERE name ='lecturas'");//reset autoincrement

    }

    public void VaciarDatosCobros(){
        db.execSQL("delete from datosCobros");
        db.execSQL("update sqlite_sequence SET seq = 0 WHERE name ='datosCobros'");
    }
    public void VaciarClientes(){
        db.execSQL("delete from clientes");
        db.execSQL("update sqlite_sequence SET seq = 0 WHERE name ='clientes'");
    }

    public void  VaciarOperadores(){
        db.execSQL("delete from usuarios");
        db.execSQL("update sqlite_sequence SET seq = 0 WHERE name ='usuarios'");
    }


    public void insertarMedidoresM(String numero, String marca, String id) {
       // Log.i("numeros: ", "prueba1");

        db.execSQL("insert into medidores (id_medidor, numero, marca) " + "values ('"+id+"','"+numero+"','"+marca+"')");
    }

    public void insertarMedidorL(String numero) {


        //db.execSQL("insert into lecturas (id_medidor, lectura, fecha, monto, metros_cubicos, lectura_ant) " + "values ('"+numero+"','"+null+"','"+null+"','"+null+"','"+null+"','"+null+"')");
        //db.execSQL("insert into lecturas (id_medidor) " + "values ('"+numero+"')");
        //db.execSQL("update lecturas set id_medidor="+numero+" where id_lectura ="+num);

        //Creamos el registro a insertar como objeto ContentValues
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id_medidor", numero);
        /*nuevoRegistro.put("lectura", "11");
        nuevoRegistro.put("fecha", "2021-10-09");
        nuevoRegistro.put("monto", "1234");
        nuevoRegistro.put("metros_cubicos", "2");
        nuevoRegistro.put("lectura_ant", "9877");*/

//Insertamos el registro en la base de datos
        db.insert("lecturas", null, nuevoRegistro);
    }
    public void insertarClientes(String rut, String nombre, String apellido, String direccion, String subsidio, String numSitio, String idMedidor){
        /*Log.i("insertando1::", rut);
        Log.i("insertando2: ", nombre);
        Log.i("insertando3: ", apellido);
        Log.i("insertando4: ", direccion);
        Log.i("insertando5: ", subsidio);
        Log.i("insertando6: ", numSitio);*/


        db.execSQL("insert into clientes (rut, nombre, apellido, direccion, subsidio, num_sitio, id_medidor) " + "values ('"+rut+"','"+nombre+"','"+apellido+"','"+direccion+"','"+subsidio+"','"+numSitio+"','"+idMedidor+"')");

    }

    public void insertarRegistros(String lecturaAnt, String numMedidor) {
        db.execSQL("update lecturas set lectura_ant="+lecturaAnt+" where id_medidor ="+numMedidor);
    }

    public void insertarDatosCobros(String cargoFijo, String metrosSub, String valorMetro){
        db.execSQL("insert into datosCobros (cargo_fijo, metros_subsidio, valor_metro) " + "values ('"+cargoFijo+"','"+metrosSub+"','"+valorMetro+"')");

    }
    public void insertarOperadores(String usuario, String contrasena){
        db.execSQL("insert into usuarios (usuario, contrasena) " + "values ('"+usuario+"','"+contrasena+"')");

    }

    public void insertarLectura(String lectura, String numMedidor) { // en realidad estos campos estan nulo, hay que actualizar el dato.

        db.execSQL("update lecturas set lectura="+lectura+" where id_medidor ="+numMedidor);


    }
    public void insertarFecha(String fecha, String numMedidor) { // en realidad estos campos estan nulo, hay que actualizar el dato.
        db.execSQL("update lecturas set fecha='"+fecha+"' where id_medidor ="+numMedidor);


    }

    public void insertarCobro(int cobro, String numMedidor){

        db.execSQL("update lecturas set monto="+cobro+" where id_medidor ="+numMedidor);

    }
    public void insertarMetrosC(int metrosC, String numMedidor){

        db.execSQL("update lecturas set metros_cubicos="+metrosC+" where id_medidor ="+numMedidor);

    }
    public String prueba(int num) {

        c = db.rawQuery("select lectura_ant from lecturas where id_lectura = '" + num + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (c.moveToNext()) {
            String lect = c.getString(0);
            buffer.append(lect);
        }
        return buffer.toString();
    }

    public String[] pruebas(int num) {

        String[] registros= new String[7];
        String rut=null;
        String nombre=null ;
        String apellido=null;
        String direccion=null ;
        String subsidio=null;
        String numSitio=null;
        String idMedidor=null;


        c = db.rawQuery("select rut, nombre, apellido, direccion, subsidio, num_sitio, id_medidor  from clientes where id_cliente = '"+num+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (c.moveToNext()) {
            rut = c.getString(0);
            nombre = c.getString(1);
            apellido = c.getString(2);
            direccion = c.getString(3);
            subsidio = c.getString(4);
            numSitio = c.getString(5);
            idMedidor = c.getString(6);

        }

        registros[0]=rut;
        registros[1]=nombre;
        registros[2]=apellido;
        registros[3]=direccion;
        registros[4]=subsidio;
        registros[5]=numSitio;
        registros[6]=idMedidor;


        return registros;
    }
    public String prueba2(String num) {
        c = db.rawQuery("SELECT * FROM clientes", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String codigo = c.getString(0);

            buffer.append(codigo);

        }
        return buffer.toString();
    }

    public String verificarAlmacenamientoMedidores() {
        c = db.rawQuery("SELECT count(*) FROM medidores", null);
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String codigo = c.getString(0);

            buffer.append(codigo);

        }
        return buffer.toString();
    }
    public String verificarAlmacenamientoLecturas() {
        c = db.rawQuery("SELECT count(*) FROM lecturas", null);
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String codigo = c.getString(0);

            buffer.append(codigo);

        }
        return buffer.toString();
    }


    public boolean verificaSiEsNull(){
        Boolean flag= false;
        c = db.rawQuery("SELECT lectura FROM lecturas", null);
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String dato = c.getString(0);
            String lectura = buffer.append(dato).toString();
            Log.i("Kreturn", lectura);
            if(lectura.equals("null")){
                Log.i("Kreturn", "pasó");
                flag=true;
                break;

            }else {
                buffer.setLength(0);
                continue;
            }

        }
        Log.i("Kreturn", String.valueOf(flag));
        return flag;

    }

    public String[] getCredenciales(String usuario, String contrasena) {
        String[] registros= new String[3];
        String user=null;
        String pass=null;
        c = db.rawQuery("SELECT * FROM usuarios where usuario='"+usuario+"' and contrasena='"+contrasena+"'", new String[]{});
        while (c.moveToNext()) {
            user = c.getString(1);
            pass = c.getString(2);

        }
        registros[0]=user;
        registros[1]=pass;
        return registros;
    }




}
