package application.model;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Group Project : LockSmith Spring-2020
 * 
 * @authors Kyle Evers(RIY335) ; Sabita Paudyal Ghimire(agi486); Jonathan
 *          Villreal (zyj680); Pedro Jusino(ayt689) ; William G (Qoi678)
 *          
 *This class takes care of the password security. It does encryption and decryption
 */
public abstract class Security {

	/*
	 * uses MD5 algorythm to hash password without salt near carbon copy of
	 * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-
	 * sha-pbkdf2-bcrypt-examples/#md5
	 */
	public static String hash(String text) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(text.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return (generatedPassword);
	}

	/*
	 * takes in a sting and key and returns encypted text key should ideally be
	 * hashed master password based on a simple "blowfish" example described here
	 * https://stackoverflow.com/questions/5244950/encryption-with-blowfish-in-java
	 */
	public static String encryptS(String text, String key) throws Exception {
		String strData = "";

		try {
			SecretKeySpec skeyspec = new SecretKeySpec(key.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			strData = new String(encrypted);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}

	/*
	 * takes in a encypted sting and key and returns decrypted text key should
	 * ideally be hashed master password based on a simple "blowfish" example
	 * described here
	 * https://stackoverflow.com/questions/5244950/encryption-with-blowfish-in-java
	 */
	public static String decryptS(String text, String key) throws Exception {
		String strData = "";

		try {
			SecretKeySpec sKeySpecs = new SecretKeySpec(key.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, sKeySpecs);
			byte[] decrypted = cipher.doFinal(text.getBytes());
			strData = new String(decrypted);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}

	/*
	 * Takes in a path to a text file and a key to encrypt with encypts and saves
	 * the txt file
	 */
	static void encryptF(String filePath, String key) {
		try {

			String dataToWrite = "";
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String enc = encryptS(line, key);
				dataToWrite = dataToWrite + enc + "\n";

			}
			br.close();

			File file = new File(filePath);
			FileWriter printer = new FileWriter(file, false);
			printer.write(dataToWrite);
			printer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Takes in a path to a encypted text file and a key to decypt with decypts and
	 * saves the txt file
	 */
	static void decryptF(String filePath, String key) {
		try {

			String dataToWrite = "";
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String dec = decryptS(line, key);
				dataToWrite = dataToWrite + dec + "\n";

			}
			br.close();

			File file = new File(filePath);
			FileWriter printer = new FileWriter(file, false);
			printer.write(dataToWrite);
			printer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyToClipboard(String text) {

		StringSelection selection = new StringSelection(text);

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);

	}
	
	
	
	
	/*
	 * May not use this function
	 * Itended to create a uqnique key that is not the same as hashed password
	 /*
	public static String uniqueKey(String username, String key)
	{
		//just smacking username and key together to rehash
		//kind of just moving the goal post to make it slightly more difficult to get te passwords by 
		//direcly decypting useing password hash in masterUser txt file.
		//This step won't stop someone with acess to the source code to breaking encryption
		key = key + username;
		String uKey = hash(key);
		return(uKey);
	}
*/
}

