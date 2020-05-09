package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import application.model.LogIn;

/**
 * Group Project : LockSmith- Spring-2020
 * 
 * @authors Kyle Evers(RIY335) ; Sabita Paudyal Ghimire(agi486); Jonathan
 *          Villreal (zyj680); Pedro Jusino(ayt689) ; William G (Qoi678)
 * 
 *          This class handles reading from file, writing to file as per
 *          controller's request. It also uses Methods of security class to make
 *          sure files are encrypted and decrypted as needed
 *
 */
public class Model {
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private ArrayList<LogIn> log = new ArrayList<LogIn>();
	public String csvFolderPath = "src/application/model/csvFiles/";
	public String username = "";

	/**

	 * this method varifies the username and password if varified return true, else
	 * return false. First, all the username and password from file are saved in
	 * arrayList named log it uses that arraylist to varify if the username and
	 * password matches.
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public boolean login(String u, String p) throws IOException {
		boolean result = true;
		try {
			// Reading from file
			BufferedReader br = new BufferedReader(new FileReader(csvFolderPath + "MasterUsers.csv"));
			String line = "";
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String[] fields = line.split(",");
				String username = fields[0];
				String password = fields[1];
				// object of Login class
				LogIn z = new LogIn(username, password);
				// adding the contents of file into arrayList
				log.add(z);
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String hashedP = Security.hash(p);
		for (int j = 0; j < log.size(); j++) {

			LogIn data2 = log.get(j);
			if ((data2.getUsername().equals(u)) && (data2.getPassword().equals(hashedP))) {
				result = true;
				this.username = u;
				break;
			} else
				result = false;

		}
		return result;
	}

	/**
	 * This method is called by model to sign up new user
	 * 
	 * @param username
	 * @param masterPassword
	 */
	public void signUp(String username, String masterPassword) {

		String hashedP = Security.hash(masterPassword);
		File file = new File(csvFolderPath + "MasterUsers.csv");

		try {
			FileWriter printer = new FileWriter(file, true);
			printer.append(username + ",");

			printer.append(hashedP + ",");
			printer.append("\n");
			printer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// After Master userName and Password is saved, it creates file with that
		// username
		Model m = new Model();
		m.createCSV(username);
	}

	public void createCSV(String s) {

		try {
			// if the file with same username exists, it will not create a new file
			String path = csvFolderPath + s + ".csv";

			if (Files.exists(Paths.get(path))) {
				System.out.println("file already exists");
				return;
			}

			PrintWriter pw = new PrintWriter(new File(path));

			pw.close();

			System.out.println("finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * stores values in model.entries into user file
	 */
	public void writeFile(String password) {
		// System.out.println("Write is being called");
		String hashedP = Security.hash(password);

		String path = csvFolderPath + this.username + ".csv";
		try {

			File file = new File(path);
			FileWriter printer = new FileWriter(file, false);

			String data = "";
			String line = "";
			for (Entry e : entries) {
				line += e.getWebsite() + ",";
				line += e.getUsername() + ",";
				line += e.getPassword() + ",";
				line += e.getEmail();

				data += line + "\n";
				line = "";

			}

			printer.write(data);
			printer.close();

			Security.encryptF(path, hashedP);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * reads user files and loads values into mode.entries
	 */
	public void readFile(String password) {
		String hashedP = Security.hash(password);

		String path = csvFolderPath + this.username + ".csv";
		try {
			Security.decryptF(path, hashedP);

			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = "";
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				System.out.println(line);
				String[] fields = line.split(",");

				String website = fields[0];
				String usr = fields[1];
				String pass = fields[2];
				String email = fields[3];
				Entry e = new Entry(website, usr, pass, email);
				entries.add(e);

			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Entry> getEntries() {
		return entries;
	}

}