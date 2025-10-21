package crm_app.service;

import java.util.List;

import crm_app.repository.RoleRepository;
import crm_app.repository.UserRepository;
import entity.Role;
import entity.User;

public class RoleService {
	
	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean updateRole(int roleId, String roleName, String description) {
		return roleRepository.updateRole(roleId, roleName, description) > 0;
	}
	
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}
	
	public boolean insertRole(String name, String description ) {
		return roleRepository.insertRole(name, description) > 0;
	}
	
	public boolean deleteRole(int roleId) {
		return roleRepository.deleteRole(roleId) > 0;
	}
	
	public Role findById(int id) {
	    return roleRepository.findById(id);
	}

}
