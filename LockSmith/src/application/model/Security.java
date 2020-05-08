package application.model;
 
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 


public abstract class Security {
	
	/*
	 * uses MD5 algorythm to hash password without salt
	 * near carbon copy of https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#md5 
	 */
	public static String hash(String text)
	{
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(text.getBytes("UTF-8") );
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return(generatedPassword);
    }
	
	
	/*
	 * takes in a sting and key and returns encypted text
	 * key should ideally be hashed master password
	 * based on a simple "blowfish" example described here
	 * https://stackoverflow.com/questions/5244950/encryption-with-blowfish-in-java
	 */
	public static String encryptS(String text, String hashedP) throws Exception
	{
		String strData="";
		//making the key 128 bytes
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(hashedP.getBytes());
        byte[] mdbytes = md.digest();
        byte[] key = new byte[mdbytes.length / 2];
        
        for(int I = 0; I < key.length; I++){
            // Choice 1 for using only 128 bits of the 256 generated
        	key[I] = mdbytes[I];
        }
		
		try {
				
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            strData = new String(encrypted);
            //System.err.println(new String(encrypted));
            // decrypt the text
           
			/*
			SecretKeySpec skeyspec = new SecretKeySpec(key.getBytes(),"Blowfish");
			
			Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
			strData=new String(encrypted);
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	
	/*
	 * takes in a encypted sting and key and returns decrypted text
	 * key should ideally be hashed master password
	 * based on a simple "blowfish" example described here
	 * https://stackoverflow.com/questions/5244950/encryption-with-blowfish-in-java
	 */
	public static String decryptS(String text, String hashedP) throws Exception
	{
		
		String strData="";
		//making the key 128 bytes
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(hashedP.getBytes());
        byte[] mdbytes = md.digest();
        byte[] key = new byte[mdbytes.length / 2];
        
        for(int I = 0; I < key.length; I++){
            // Choice 1 for using only 128 bits of the 256 generated
        	key[I] = mdbytes[I];
        }
		
		try {
			
			Key aesKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
			
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted = new String( cipher.doFinal(text.getBytes()) ) ;
			strData = new String(decrypted);
	        //System.err.println(decrypted);
				
			/*
			SecretKeySpec sKeySpecs=new SecretKeySpec(key.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, sKeySpecs);
			byte[] decrypted=cipher.doFinal(text.getBytes());
			strData=new String(decrypted);
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	
	/*
	 * Takes in a path to a text file and a key to encrypt with
	 * encypts and saves the txt file
	 */
	static void encryptF(String filePath, String key )
	{
		try
		{
			
		String dataToWrite = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String enc = encryptS(line,key);
			dataToWrite = dataToWrite + enc + "\n";
			
		}
		br.close();
		
		File file = new File(filePath);
		FileWriter printer = new FileWriter(file, false);
		printer.write(dataToWrite);
		printer.close();
		
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
	}
	
	/*
	 * Takes in a path to a encypted text file and a key to decypt with
	 * decypts and saves the txt file
	 */
	static void decryptF( String filePath, String key)
	{
		try
		{
			
		String dataToWrite = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String dec = decryptS(line,key);
			dataToWrite = dataToWrite + dec + "\n";
			
		}
		br.close();
		
		File file = new File(filePath);
		FileWriter printer = new FileWriter(file, false);
		printer.write(dataToWrite);
		printer.close();
		
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
	}
	
	public static void copyToClipboard(String text) {
		
		StringSelection selection = new StringSelection( text );
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		
		
	}
	
	
	
	
	
	
	
	public static String uniqueKey(String key)
	{
		
		String uKey = hash(key);
		return(uKey);
	}

}

