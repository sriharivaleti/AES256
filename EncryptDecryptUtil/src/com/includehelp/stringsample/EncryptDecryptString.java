package com.includehelp.stringsample;

import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Program to Encrypt/Decrypt String Using AES 128 bit Encryption Algorithm
 */
public class EncryptDecryptString {
    
    private static final String encryptionKey           = "SUMTOTALEXPENSE!";
    private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    
    
    /**
     * Method for Encrypt Plain String Data
     * @param plainText
     * @return encryptedText
     */
    public static String encrypt(String plainText) {
        String encryptedText = "";
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            byte[] key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
             System.err.println("Encrypt Exception : "+E.getMessage());
        }
        return encryptedText;
    }

    /**
     * Method For Get encryptedText and Decrypted provided String
     * @param encryptedText
     * @return decryptedText
     */
    public static String decrypt(String encryptedText) {
        String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            System.err.println("decrypt Exception : "+E.getMessage());
        }
        return decryptedText;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String : ");
        String plainString = "pass.1word";//sc.nextLine();
        
        String encyptStr   = encrypt(plainString);
        String decryptStr  = decrypt(encyptStr);
        
        System.out.println("Plain   String  : "+plainString);
        System.out.println("Encrypt String  : "+encyptStr);
        System.out.println("Decrypt String  : "+decryptStr);
        
        String plainString2 = "titan#12";//sc.nextLine();
        
        String encyptStr2   = encrypt(plainString2);
        String decryptStr2  = decrypt(encyptStr2);
        
        System.out.println("Plain   String  2: "+plainString2);
        System.out.println("Encrypt String  2: "+encyptStr2);
        System.out.println("Decrypt String  2: "+decryptStr2);
        
        String plainString3 = "redab977";//sc.nextLine();
        
        String encyptStr3   = encrypt(plainString3);
        String decryptStr3  = decrypt(encyptStr3);
        
        System.out.println("Plain   String  3: "+plainString3);
        System.out.println("Encrypt String  3: "+encyptStr3);
        System.out.println("Decrypt String  3: "+decryptStr3);
        
        String plainString4 = "sreehari";//sc.nextLine();
        
        String encyptStr4   = encrypt(plainString4);
        String decryptStr4  = decrypt(encyptStr4);
        
        System.out.println("Plain   String  3: "+plainString4);
        System.out.println("Encrypt String  3: "+encyptStr4);
        System.out.println("Decrypt String  3: "+decryptStr4);
        
        String encryptedText = "EHMWYRpxFboP8cBgmg9f0w==";
        String decrypted = decrypt(encryptedText);
        System.out.println("Encrypted Text :"+encryptedText);
        System.out.println("Decrypted value:"+decrypted);
        
        
    }   
}
