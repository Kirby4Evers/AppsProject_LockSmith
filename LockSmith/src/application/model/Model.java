package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Model {
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private ArrayList<LogIn> log = new ArrayList<LogIn>();

	/**
	 * this method varifies the username and password
	 * if varified return true, else return false.
	 * 
	 *First, all the username and password from file are saved in arrayList named log
	 *it uses that arraylist to varify if the username and password matches.
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public boolean login(String username, String password) throws IOException {
		boolean result = true;
		try {

			BufferedReader br = new BufferedReader(new FileReader("Master.csv"));
			String line = "";
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String[] fields = line.split(",");
				String u = fields[0];
				String p = fields[1];

				LogIn z = new LogIn(u, p);
				log.add(z);
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int j = 0; j < log.size(); j++) {

			LogIn data2 = log.get(j);
			if (data2.getUsername().equals(username) && data2.getPassword().equals(password)) {
				result = true;
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

		File file = new File("Master.csv");

		try {
			FileWriter printer = new FileWriter(file, true);
			printer.append(username + ",");

			printer.append(masterPassword + ",");
			printer.append("\n");
			printer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
