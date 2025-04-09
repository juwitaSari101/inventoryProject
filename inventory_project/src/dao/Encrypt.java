/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author juwita
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    
    // Metode untuk menghasilkan hash MD5
    public static String getmd5java(String input) {
        try {
            // Inisialisasi instance MessageDigest dengan algoritma MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Proses input dan menghasilkan byte array hash
            byte[] messageDigest = md.digest(input.getBytes());

            // Mengubah byte array menjadi format heksadesimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Tambahkan 0 jika panjang hex kurang dari 2
                }
                hexString.append(hex);
            }

            return hexString.toString(); // Hasil hash dalam format string heksadesimal
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: Algoritma MD5 tidak ditemukan.", e);
        }
    }
}

