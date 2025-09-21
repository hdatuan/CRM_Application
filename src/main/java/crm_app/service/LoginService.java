package crm_app.service;

import crm_app.repository.LoginRepository;
import entity.User;

public class LoginService {
	
	private LoginRepository loginRepository = new LoginRepository();
	
	public User findUser(String email, String password) {
		return loginRepository.findUser(email, password);
	}
}
