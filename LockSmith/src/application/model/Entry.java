package application.model;
public class Entry{
  private String website;
  private String username;
  private String password;
  private String email;
  private String uniqueKey;
  
  public Entry(String key){
    website = "";
    username = "";
    password = "";
    email = "";
	uniqueKey = Security.uniqueKey(key);
  }
  
  public Entry(String website, String username, String password, String email, String key){
	uniqueKey = Security.uniqueKey(key);
	this.website = website;
    this.username = username;
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
  
  public String getPasswordDecrypted() throws Exception{
    return Security.decryptS(this.password, this.uniqueKey);
  }
  
  public String getEmail(){
	    return this.email;
  }
 
  
  public void setWebsite(String website){
    this.website = website;
  }
  
  public void setUsername(String username){
    this.username = username;
  }
  
  public void setPassword(String password) {
	try {
		this.password = Security.encryptS(password, uniqueKey);
	} catch (Exception e) {
		System.out.println("Exception in setPassword(), EXCEPTION: " + e);
		e.printStackTrace();
	}
  }
  
  public void setEmail(String email){
	    this.email = email;
  }
}
