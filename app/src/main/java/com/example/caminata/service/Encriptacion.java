package com.example.caminata.service;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encriptacion {
    private static final String key ="08wR?!S6_wo&-v$f#0RUdrEfRoclTh";

    public static String getencriptacion(String password) {
        String encrip = null;
        try {
            SecretKey key = getSecretKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,getSecretKey());
            byte[] datosEncriptados= cipher.doFinal(password.getBytes());
            encrip = Base64.encodeToString(datosEncriptados,Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return encrip;
    }

    public static String getdesencriptacion(String password) {
        String desencrip = null;
        try {
            SecretKey key = getSecretKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,getSecretKey());
            byte[] datosDesencriptados= Base64.decode(password,Base64.DEFAULT);
            byte[] datosDesencriptadosByte= cipher.doFinal(datosDesencriptados);
            desencrip = new String(datosDesencriptadosByte);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return desencrip;
    }

    private static SecretKey getSecretKey() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyByte = key.getBytes("UTF-8");
        keyByte = sha.digest(keyByte);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyByte,"AES");
        return secretKeySpec;
    }
}
