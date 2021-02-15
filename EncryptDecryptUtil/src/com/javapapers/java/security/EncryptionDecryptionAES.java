package com.javapapers.java.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptionDecryptionAES {

	static Cipher cipher;
	private static SecretKey secretKey = null;
	private static EncryptionDecryptionAES aesobj = null;
	
	public static SecretKey getSecretKey() {
		return secretKey;
	}

	public static void setSecretKey(SecretKey secretKey) {
		EncryptionDecryptionAES.secretKey = secretKey;
	}
	
	private EncryptionDecryptionAES() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			secretKey = keyGenerator.generateKey();
			cipher = Cipher.getInstance("AES");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(
					new java.util.Date() + ":- Issue While Initiating EncryptionDecryptionUtil " + e.getMessage());
			return;
		}
	}
	
	public static EncryptionDecryptionAES getInstance() {
		
		if(aesobj == null) {
			aesobj = new EncryptionDecryptionAES();
		}
		return aesobj;
	}

	public static String encrypt(String plainText) throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}

	public static String decrypt(String encryptedText) throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}

}
