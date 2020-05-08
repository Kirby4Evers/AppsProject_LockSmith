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

public class Model {
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private ArrayList<LogIn> log = new ArrayList<LogIn>();
	public String csvFolderPath = "src/application/model/csvFiles/";
	public String username = "";

	/**
	 * this method varifies the username and password
	 * if varified return true, else return false.
	 *First, all the username and password from file are saved in arrayList named log
	 *it uses that arraylist to varify if the username and password matches.
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public boolean login(String u, String p) throws IOException {
		boolean result=true;
		try {

			BufferedReader br = new BufferedReader(new FileReader(csvFolderPath + "MasterUsers.csv"));
			String line = "";
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String[] fields = line.split(",");
				String username = fields[0];
				String password = fields[1];

				LogIn z = new LogIn(username, password);
				log.add(z);
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	
		String hashedP = Security.hash(p);
		for (int j = 0; j < log.size(); j++) {

			LogIn data2 = log.get(j);
			if ((data2.getUsername().equals(u)) && (data2.getPassword().equals(hashedP)) ){
				result= true;
				this.username = u;
				break;}
			else
				result= false;

		}
		return result;
	}

	/**
	 * This method is called by model to sign up new user
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
		// After Master userName and Password is saved, it creates file with that username
		Model m= new Model();
		m.createCSV(username);
	}
	
	public void createCSV(String s) {
		
		try {
			//if the file with same username exists, it will not create a new file
			String path = csvFolderPath + s + ".csv";

			if(Files.exists(Paths.get(path))) { 
				   System.out.println("file already exists");
				   return;
				}
			
			   PrintWriter pw= new PrintWriter(new File(path));

			   
			   pw.close();
			   
			   System.out.println("finished");
			   } catch (Exception e) {
			      // TODO: handle exception
			   }
	}


	public void writeFile() {
		System.out.println("Write is being called");
		
		String path = csvFolderPath + this.username + ".csv";
		try {
			
			File file = new File(path);
			FileWriter printer = new FileWriter(file, false);
			
			String data = "";
			for (Entry e : entries)
			{
				data += e.getWebsite() + ",";
				data += e.getUsername() + ",";
				data += e.getPassword() + ",";
				data += e.getEmail() + ",";
				data += "\n";
				
			}
			
			
			printer.write(data);
			printer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void readFile()
	{
		System.out.println("Read is being called");
		
		String path = csvFolderPath + this.username + ".csv";
		try {

			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = "";
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String[] fields = line.split(",");
				
				String website = fields[0];
				String usr = fields[1];
				String pass = fields[2];
				String email = fields[3];
				Entry e = new Entry(website,usr,pass,email);
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
