package de.mb.tournamentCommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import de.mb.tournamentCommunity.repository.api.IUserDao;
import de.mb.tournamentCommunity.repository.model.User;
import de.mb.tournamentCommunity.service.api.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired BCryptPasswordEncoder passwordEncoder;

	@Autowired IUserDao userDao;

	@Override
	public void saveUser(final User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.saveUser(user);
	}
}
