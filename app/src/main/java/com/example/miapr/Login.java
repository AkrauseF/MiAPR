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

    }

    public void login(View view){

        String user= usuario.getText().toString();
        String pass = contrasena.getText().toString();


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String[] credenciales = databaseAccess.getCredenciales(user, pass);

        //usuario.setText(sha256);

        if(user.equals(credenciales[0]) && pass.equals(credenciales[1])){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            usuario.setText("");
            contrasena.setText("");

        }else {
            Toast.makeText(this, "Credenciales Erroneas", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Intentelo de Nuevo", Toast.LENGTH_SHORT).show();


        }
    }
}