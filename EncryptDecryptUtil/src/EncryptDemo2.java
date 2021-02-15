import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
public class EncryptDemo2 {

	private static final String ALGORITHM = "AES";
	private static byte[] key = "53756d546f74616c".getBytes(StandardCharsets.UTF_8);

	public EncryptDemo2() {

	}

	/**
	 * Encrypts the given plain text
	 */
	public static String encrypt(String plainText)  {
		
		String finalString = null;
		try {
		
		byte[] plainTextBytes  = plainText.getBytes(StandardCharsets.UTF_8); //Converting String to Bytes
		
		SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM); // Constructs a secret key from the given byte
																		// array.
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Constant used to initialize cipher to encryption mode.
		byte[] encryptedPlainTextByte = cipher.doFinal(plainTextBytes); // Encrypts or decrypts data in a single-part operation, or finishes a multiple-part operation.
		finalString = new String(encryptedPlainTextByte);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return finalString;
	}

	/**
	 * Decrypts the given byte array
	 */
	public static String decrypt(String cipherText)  {
		
		String finalString = null;
		try {
		byte[] cipherTextBytes  = cipherText.getBytes(StandardCharsets.UTF_8);
		
		SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM); // Constructs a secret key from the given byte
																		// array.
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey); // Constant used to initialize cipher to decryption mode.
		byte[] decryptedCipherTextBytes =  cipher.doFinal(cipherTextBytes); // Encrypts or decrypts data in a single-part operation, or finishes a multiple-part operation.
		finalString = new String(decryptedCipherTextBytes);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return finalString;
	}

	public static void main(String[] args) {
		String imgpassword ="pass1.word";
		String ftppassword ="titan#12";
		System.out.println("imgpassword :" +imgpassword +"\nimgpassword Encrypted :" + encrypt(imgpassword) +"\nimgpassword Decrypted:"+ decrypt(imgpassword));
		System.out.println("ftppassword :" +ftppassword +"\nftppassword Encrypted :" + encrypt(ftppassword) +"\nftppassword Decrypted:"+ decrypt(ftppassword));
	}


	
}
