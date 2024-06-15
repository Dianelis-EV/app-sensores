package com.example.caminata.service;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encriptacion {
    private static final long serialVersionUID = 548686554397673076L;
    private static final String key ="08wR?!S6_wo&-v$f#0RUdrEfRoclTh";
    private static final String salt ="huwlzO@a*&t8tr83e$l6hiy#k+vl0cr";

    private SecretKey clave_secreta_temporal;


    public static String getencriptacion(String contrasenna) {
        byte [] vector_inicializacion = new byte[16];
        String encrip = null;
        try {
            SecretKeySpec clave_especifica = generateKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clave_especifica);
            encrip = Base64.encodeToString(cipher.doFinal(contrasenna.getBytes()), Base64.DEFAULT);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return encrip;
    }

    public static String getdesencriptar(String encrip) {
        String desencrip = null;
        byte [] vector_inicializacion = new byte[16];
        try {

            SecretKeySpec clave_especifica = generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, clave_especifica);
            desencrip= new String (cipher.doFinal(Base64.decode(encrip,Base64.DEFAULT)));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return desencrip;
    }

    private static SecretKeySpec generateKey() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] pass = key.getBytes("UTF-8");
        pass = messageDigest.digest(pass);
        SecretKeySpec secretKey= new SecretKeySpec(pass,"AES");
        return secretKey;

    }

}
