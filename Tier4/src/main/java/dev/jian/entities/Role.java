package dev.jian.entities;

public class Role {

	private String roleName;

	public Role() {
		super();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + "]";
	}
	
	
	
}
