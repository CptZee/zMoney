package com.github.cptzee.zmoney.Util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataEncrypter {
    public static String encryptPassword(String password){
        String encryptedPassword = "";
        try {
            encryptedPassword = hashPassword(password);
            Log.i("DataEncrypter Util", "Password encrypted successfully.");
        }catch (NoSuchAlgorithmException e){
            Log.e("DataEncrypter Util", "", e.getCause());
        }

        return encryptedPassword;
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return bytesToHex(digest);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
