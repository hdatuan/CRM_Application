package crm_app.service;

import java.util.List;

import crm_app.repository.RoleRepository;
import crm_app.repository.UserRepository;
import entity.Role;
import entity.User;

public class RoleService {
	
	private RoleRepository roleRepository = new RoleRepository();
	
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}
	
	public boolean insertRole(String name, String description ) {
		return roleRepository.insertRole(name, description) > 0;
	}
	
}
