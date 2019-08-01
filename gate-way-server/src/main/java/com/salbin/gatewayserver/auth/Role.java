package com.salbin.gatewayserver.auth;



public class Role {
    private Integer id;
    private String role;

    public Integer getId() {
        return id;
    }

    public Role(Integer id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public void setId(Integer id) {
        this.id = id;
    }
  
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}