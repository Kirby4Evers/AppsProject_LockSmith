
package application.model;

/**
 * Group Project : LockSmith   Spring-2020
 * 
 * @authors Kyle Evers(RIY335) ; Sabita Paudyal Ghimire(agi486); 
 *          Jonathan Villreal (zyj680); Pedro Jusino(ayt689) ;
 *          William G (Qoi678)
 *
 *This class is a model for user entries
 */
public class Entry{
//instance variables 	
  private String website;
  private String username;
  private String password;
  private String email;
//Empty constructor  
  public Entry(){
    website = "";
    username = "";
    password = "";
    email = "";
    
  }
 //constructor with parameters, to initializes the instance variables   
  public Entry(String website, String username, String password, String email){
    this.website = website;
    this.username = username;
    this.password = password;
    this.email = email;
  }
  
  //List of accessors for instance variables
  public String getWebsite(){
    return this.website;
  }
  
  public String getUsername(){
    return this.username;
  }
  
  public String getPassword(){
    return this.password;
  }
  
  public String getEmail(){
	    return this.email;
  }
 
  //List of mutators for the instance variables
  public void setWebsite(String website){
    this.website = website;
  }
  
  public void setUsername(String username){
    this.username = username;
  }
  
  public void setPassword(String password){
    this.password = password;
  }
  
  public void setEmail(String email){
	    this.email = email;
  }

  
}
