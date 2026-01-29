package hdatuan.entity;

public class User {
	private int id;
	private String email;
	private String password;
	private String fullname;
	private int roleID;
	private String roleDescription;
	
	public User() {}
	
	public User(int id, String email, String password, String fullname, int roleID, String roleDescription) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.roleID = roleID;
		this.roleDescription = roleDescription;
	}
	
	
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
}

