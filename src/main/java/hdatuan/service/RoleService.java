package hdatuan.service;

import java.util.List;

import hdatuan.repository.RoleRepository;
import hdatuan.repository.UserRepository;
import hdatuan.entity.Role;
import hdatuan.entity.User;

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

