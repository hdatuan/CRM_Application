package crm_app.service;

import java.util.List;

import crm_app.repository.UserRepository;
import entity.User;

public class UserService {
	
	private UserRepository userRepository = new UserRepository();
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public boolean insertUser(String fullName, String email, String password, int roleId) {
		return userRepository.insertUser(fullName, email, password, roleId) > 0;
	}
	
	public void deleteUser(int id) {
		userRepository.deleteUser(id);
	}
}
