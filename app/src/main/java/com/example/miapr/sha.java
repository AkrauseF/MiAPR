package com.example.miapr;

import java.security.MessageDigest;

public class sha {

    public static byte[] encryptSha(byte[] data, String shaN) throws Exception{

        MessageDigest sha= MessageDigest.getInstance(shaN);
        sha.update(data);
        return sha.digest();
    }
}
