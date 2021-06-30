package com.example.miapr;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;

public class Hashing extends AppCompatActivity {

    byte[] passClaro;
    Context context;

    public Hashing(Context context, byte[] passClaro){
        this.passClaro = passClaro;
        this.context = context;

    }

    public String hashear() {
        String SHA256 = "SHA-256";
        byte[] outputData = new byte[0];
        try {
            outputData = sha.encryptSha(passClaro, SHA256);
        }catch (Exception e){
            e.printStackTrace();
        }

        BigInteger shaData = new BigInteger(1, outputData);

        return shaData.toString(16);


    }
}
