
/**
 * Group Project : LockSmith   Spring-2020
 * 
 * @authors Kyle Evers(RIY335) ; Sabita Paudyal Ghimire(agi486); 
 *          Jonathan Villreal (zyj680); Pedro Jusino(ayt689) ;
 *          William G (Qoi678)
 * This class is a model for username and master password 

 */
package application.model;

public class LogIn {
	private String username;
	private String password;

	/**
	 * @param username
	 * @param password
	 */
	public LogIn(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
