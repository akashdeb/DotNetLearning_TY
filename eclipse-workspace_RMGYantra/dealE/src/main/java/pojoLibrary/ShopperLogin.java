package pojoLibrary;

public class ShopperLogin {
	
	private String email; 
	private String password;  
	private String role;
	public ShopperLogin(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
	
	

}
