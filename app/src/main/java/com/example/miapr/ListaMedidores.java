package com.example.miapr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListaMedidores extends AppCompatActivity {

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
        setContentView(R.layout.activity_lista_medidores);

        lista = findViewById(R.id.lvLista);

        crearListaMedidores();
        lista.setAdapter(new Adaptador(this, crearListaMedidores()));



    }

    private String selectMedidor(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String ultimoId = databaseAccess.UltimoIdMedidores();

        return ultimoId;


    }

    private String[][] crearListaMedidores(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String ultimoId = databaseAccess.UltimoIdMedidores();
        int ulId = Integer.parseInt(ultimoId);

        String [][] datos = new String[ulId][2];


        int contador= 1;
        while (contador <= ulId){
            String num = String.valueOf(contador);
            String codigo = databaseAccess.getCodigoMedidor(num);
            String marca = databaseAccess.getMarcaMedidor(num);

            //Toast.makeText(this, codigo, Toast.LENGTH_LONG).show();

           int posicion = contador;
            int pos = posicion - 1;
            datos[pos][0]=codigo;
            datos[pos][1]=marca;

            // Toast.makeText(this,datos[pos][0],Toast.LENGTH_LONG ).show();
            //Toast.makeText(this,datos[pos][1],Toast.LENGTH_LONG ).show();
            contador++;

        }
        return datos;


    }
}