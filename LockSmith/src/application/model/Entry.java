package application.model;
public class Entry{
  private String website;
  private String username;
  private String password;
  private String email;
  
  public Entry(){
    website = null;
    username = null;
    password = null;
    email = null;
    
  }
  
  public Entry(String website, String username, String password, String email){
    this.website = website;
    this.username = username;
    //TODO: run entered password string through encryption method
    this.password = password;
    this.email = email;
  }
  
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
  
  /*
  public String getPasswordDecrypted(){
    //TODO: add method of decryption
    return this.password;
  }*/
  
  public void setWebsite(String website){
    this.website = website;
  }
  
  public void setUsername(String username){
    this.username = username;
  }
  
  public void setPassword(String password){
    //TODO: run through encryption method
    this.password = password;
  }
  
  public void setEmail(String email){
	    //TODO: run through encryption method
	    this.email = email;
  }
  
  /*
  //TODO: complete Encryption and Decryption methods
  private void Encrypt(){
  }
  private void Decrypt(){
  }
  */
  
}
