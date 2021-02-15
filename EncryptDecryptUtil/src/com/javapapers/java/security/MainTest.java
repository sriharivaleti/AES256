package com.javapapers.java.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainTest {
	
	public static void main(String[] args) {
		try {
		EncryptionDecryptionAES aes = EncryptionDecryptionAES.getInstance();
		String plainText = "pass.1word";
		System.out.println("Plain Text Before Encryption: " + plainText);

		String encryptedText = aes.encrypt(plainText);
		System.out.println("Encrypted Text After Encryption: " + encryptedText);

		String decryptedText = aes.decrypt(encryptedText);
		System.out.println("Decrypted Text After Decryption: " + decryptedText);
		
		String plainText2 = "titan#12";
		System.out.println("Plain Text Before Encryption: " + plainText2);

		String encryptedText2 = aes.encrypt(plainText2);
		System.out.println("Encrypted Text After Encryption: " + encryptedText2);

		String decryptedText2 = aes.decrypt(encryptedText2);
		System.out.println("Decrypted Text After Decryption: " + decryptedText2);

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
