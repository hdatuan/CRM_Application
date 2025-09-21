package crm_app.service;

import crm_app.repository.LoginRepository;

public class LoginService {
	
	private LoginRepository loginRepository = new LoginRepository();
	
	public boolean findUser(String email, String password) {
		return loginRepository.findUser(email, password);
	}
}
