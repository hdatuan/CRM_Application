package crm_app.service;

import java.util.List;

import crm_app.repository.UserRepository;
import entity.User;

public class UserService {
	
	private UserRepository userRepository = new UserRepository();
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public void deleteUser(int id) {
		userRepository.deleteUser(id);
	}
}
