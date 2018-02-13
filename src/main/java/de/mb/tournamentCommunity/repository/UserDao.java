package de.mb.tournamentCommunity.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import de.mb.tournamentCommunity.repository.api.IUserDao;
import de.mb.tournamentCommunity.repository.model.User;

@Repository
@Transactional
public class UserDao implements IUserDao {
	@Autowired private SessionFactory sessionFactory;

	@Override
	public User findUserByUsername(final String username) {
		return sessionFactory.getCurrentSession().get(User.class, username);
	}

	@Override
	public User saveUser(final User user) {
		System.out.println("Save user: " + user);
		sessionFactory.getCurrentSession().persist(user);
		System.out.println("User saved");
		return sessionFactory.getCurrentSession().get(User.class, user.getUsername());
	}

}
