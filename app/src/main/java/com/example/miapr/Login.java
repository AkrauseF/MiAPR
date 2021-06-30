package com.example.miapr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText usuario, contrasena;
    Button btEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.etUser);
        contrasena = findViewById(R.id.etPass);
        btEnviar = findViewById(R.id.btLogin);

        //testLogin();
        //VerificaConexion verificaConexion = new VerificaConexion("192.168.0.5");
        //verificaConexion.testExecuteCommand();

    }

    public void login(View view) {

        String user = usuario.getText().toString();
        String pass = contrasena.getText().toString();

        Hashing hashing = new Hashing(this, contrasena.getText().toString().getBytes());
        String hashPass = hashing.hashear();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String[] credenciales = databaseAccess.getCredenciales(user, hashPass);

        if (user.equals(credenciales[0]) && hashPass.equals(credenciales[1])) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            usuario.setText("");
            contrasena.setText("");

        } else {
            Toast.makeText(this, "Credenciales Erroneas", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Intentelo de Nuevo", Toast.LENGTH_SHORT).show();


        }
    }

        /*public void testLogin(){

            String[] usuarios={"oper1", "oper2", "oper3", "oper4", "oper5"};
            String[] passw={"123", "1992xc", "passxx", "123plo9", "password"};

            for(int i=0; i<usuarios.length; i++){

                Log.i("Cred-User: ", usuarios[i]);
                Log.i("Cred-Password: ", passw[i]);

                Hashing hashing2 = new Hashing(this, passw[i].getBytes());
                String hashPass2 = hashing2.hashear();

                Log.i("Cred-HPassword: ", hashPass2);

                DatabaseAccess databaseAccess2 = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess2.open();

                String[] credenciales2 = databaseAccess2.getCredenciales(usuarios[i], hashPass2);

                if(usuarios[i].equals(credenciales2[0]) && hashPass2.equals(credenciales2[1])){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    Log.i("Cred-Validacion:","Credenciales VALIDAS");

                }else {
                    Log.i("Cred-Validacion:","Credenciales INVALIDAS");
                }
            }
        }*/
}