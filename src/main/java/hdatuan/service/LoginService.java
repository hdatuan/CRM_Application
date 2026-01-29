package hdatuan.service;

import hdatuan.repository.LoginRepository;
import hdatuan.entity.User;

public class LoginService {
	
	private LoginRepository loginRepository = new LoginRepository();
	
	public User findUser(String email, String password) {
		return loginRepository.findUser(email, password);
	}
}

