package com.example.miapr;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ListaRegistros extends AppCompatActivity {

    ListView lista;



    /*String[][] datos = {
            {"Interestelar", "Christopher Nolan", "2:49", "9", "Interstellar (Interestelar en Hispanoamérica) es una película épica de ciencia ficción estadounidense de 2014, dirigida por Christopher Nolan y protagonizada por Matthew McConaughey, Anne Hathaway, Jessica Chastain, Michael Caine y Matt Damon. La película presenta a un equipo de astronautas que viaja a través de un agujero de gusano en busca de un nuevo hogar para la humanidad. Los hermanos Christopher y Jonathan Nolan escribieron el guion, que tuvo su origen en un borrador que Jonathan desarrolló en 2007. Christopher Nolan produjo la película junto a su esposa Emma Thomas mediante su compañía productora Syncopy, y con Lynda Obst a través de Lynda Obst Productions. El físico teórico Kip Thorne, cuyo trabajo inspiró la película, fue productor ejecutivo y participó como consultor científico. Warner Bros., Paramount Pictures y Legendary Pictures cofinanciaron la película."},
            {"Logan", "James Mangold", "2:17", "7", "Logan(Logan: Wolverine en Hispanoamérica) es una película estadounidense de 2017 y la última de la trilogía de Wolverine, basada en el personaje de Wolverine, de Marvel Comics, y producida por la 20th Century Fox. Se estrenó el 3 de marzo de 2017, protagonizada por Hugh Jackman y Patrick Stewart siendo esta sus últimas apariciones como Wolverine y el Profesor X en la franquicia de X-Men."},
            {"Everest", "Baltasar Kormákur", "2:01", "8", "Everest es una película estadounidense estrenada el 18 de septiembre de 2015, dirigida por Baltasar Kormákur y escrita por Justin Isbell y William Nicholson. La cinta, que tiene como protagonistas a Jason Clarke, Josh Brolin, John Hawkes, Robin Wright, Michael Kelly, Keira Knightley, Emily Watson, Sam Worthington y Jake Gyllenhaal, narra la tragedia ocurrida en el monte Everest el 10 de mayo de 1996, en la que ocho alpinistas fallecieron debido a una tormenta."},
            {"Titanes del Pacífico", "Guillermo del Toro", "2:12", "7", "Pacific Rim (Titanes del Pacífico en Hispanoamérica) es una película estadounidense de ciencia ficción del 2013 dirigida por Guillermo del Toro, escrita por Del Toro y Travis Beacham, y protagonizada por Charlie Hunnam, Idris Elba, Rinko Kikuchi, Charlie Day, Robert Kazinsky, Max Martini, y Ron Perlman. La película está ambientada en la década de 2020, cuando la Tierra es atacada por kaijus, monstruos colosales que han surgido a partir de un portal interdimensional en el fondo del Océano Pacífico, llamado \"El Abismo\". Para luchar contra los monstruos, la humanidad se une para crear a los Jaegers: gigantescas máquinas humanoides, cada una controlada por dos pilotos cuyas mentes están unidas por un puente neural (similares a los personajes llamados Headmasters de Transformers o a las unidades EVA (mecha) de Neon Genesis Evangelion). Centrándose en los días posteriores de la guerra, la historia sigue a Raleigh Becket, un piloto jaeger llamado de su retiro, que se asociará con la piloto novata Mako Mori en un último esfuerzo para derrotar a los kaijus."},
            {"Ex Machina", "Alex Garland", "1:48", "9", "Ex Machina es una película de ciencia ficción británica de 2015, escrita y dirigida por Alex Garland, siendo su primera película como director. Está protagonizada por Domhnall Gleeson, Alicia Vikander, Oscar Isaac y Sonoya Mizuno. Ex Machina cuenta la historia de Caleb, un programador de la empresa Bluebook, quien es invitado por Nathan, el Presidente de la compañía para la cual él trabaja, con el fin de realizar la prueba de Turing a un androide con inteligencia artificial. La película ha recibido principalmente críticas positivas de los expertos. La cinta ganó el Óscar a los mejores efectos visuales."},
            {"Arrival (La llegada)", "Denis Villeneuve", "1:56", "8", "Arrival (titulada en español como La llegada) es una película estadounidense de drama y ciencia ficción, dirigida por Denis Villeneuve y escrita por Eric Heisserer. Con Amy Adams y Jeremy Renner en los papeles principales, está basada en el premiado relato La historia de tu vida (Story of Your Life) de Ted Chiang. Fue estrenada mundialmente el 1 de septiembre de 2016 en el Festival Internacional de Cine de Venecia."}
    };*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_registros);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lista = findViewById(R.id.lvListaR);

        crearListaRegistros();
        lista.setAdapter(new AdaptadorRegistros(this, crearListaRegistros()));

    }

    private String selectMedidor(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String[] ultimoId = databaseAccess.UltimoIdMedidores();
        return ultimoId[0];
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

    public String[][] crearListaRegistros(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String sub = databaseAccess.getIdMedidores();
        String[] respuesta = sub.split(",");
        String [][] datos = new String[respuesta.length-1][7];

        for(int i=1; i < respuesta.length; i++){
            String[] codigo = databaseAccess.getRegistros(i);
          /*  Log.i("Visual Q Medidor: ", codigo[0]);
            Log.i("Visual Q Lectura: ", codigo[1]);
            Log.i("Visual Q Fecha: ", codigo[2]);
            Log.i("Visual Q LecAnt: ", codigo[5]);*/
            String subsidio = databaseAccess.getSubsidioLecturas(codigo[0]);
           // Log.i("Visual->", codigo[3]);
            //Log.i("Visual->", subsidio);
            int posicion = i;
            int pos = posicion-1;
            if(codigo[0]== null){
                datos[pos][0]="-";
            }else {
                datos[pos][0]=codigo[0];
            }

            if(codigo[1]== null){
                datos[pos][1]="-";
            }else {
                datos[pos][1]=codigo[1];
            }
            if(codigo[2]== null){
                datos[pos][2]="-";
            }else {
                datos[pos][2]=codigo[2];
            }
            if(codigo[3]== null){
                datos[pos][3]="-";
            }else {
                datos[pos][3]=codigo[3];
            }
            if(codigo[4]== null){
                datos[pos][4]="-";
            }else {
                datos[pos][4]=codigo[4];
            }
            if(codigo[5]== null){
                datos[pos][5]="-";
            }else {
                datos[pos][5]=codigo[5];
            }
            if(subsidio== null){
                datos[pos][6]="-";
            }else {
                datos[pos][6]=subsidio;
            }
           /* datos[pos][0]=codigo[0];
            datos[pos][1]=codigo[1];
            datos[pos][2]=codigo[2];

            datos[pos][4]=codigo[4];
            datos[pos][5]=codigo[5];
            datos[pos][6]=subsidio;*/
        }
        return datos;
    }

  /*  public void verificarLecturasDeMedidoresImportadas(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String sub = databaseAccess.getIdMedidores();
        String[] respuesta = sub.split(",");
        String [][] datos = new String[respuesta.length-1][5];

        for(int i=1; i < respuesta.length; i++){
            String[] codigo = databaseAccess.getRegistros(i);
            Log.i("DB-> Nº Medidor: ", codigo[0]);
            Log.i("DB-> Lectura anterior: ", codigo[5]);
            Log.i("DB->", "**************************");

        }

    }*/
}