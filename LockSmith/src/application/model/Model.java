package application.model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Model{
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    
    
    
    public void login(String username, String password) {
    	//TODO: add login function
    }
    
    
    /** This method is called by model to sign up new user
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

