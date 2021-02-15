


import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecryptUtil {



	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";
	 
	public static String encrypt(String value) {
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	 
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	 
	        byte[] encrypted = cipher.doFinal(value.getBytes());
	        return encrypted.toString();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

	/**
	 * Decrypts the given byte array
	 */
	public static String decrypt(String encrypted) {
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	 
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	        byte[] original = cipher.doFinal(encrypted.getBytes());
	        return new String(original);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	 
	    return null;
	}

	public static void main(String[] args) {
		String imgpassword ="pass1.word";
		String ftppassword ="titan#12";
		System.out.println("imgpassword :" +imgpassword +"\nimgpassword Encrypted :" + encrypt(imgpassword) +"\nimgpassword Decrypted:"+ decrypt(imgpassword));
		System.out.println("ftppassword :" +ftppassword +"\nftppassword Encrypted :" + encrypt(ftppassword) +"\nftppassword Decrypted:"+ decrypt(ftppassword));
	}
}
