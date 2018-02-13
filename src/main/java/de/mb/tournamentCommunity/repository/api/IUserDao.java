package de.mb.tournamentCommunity.repository.api;

import de.mb.tournamentCommunity.repository.model.User;

/**
 * @author Martin Bauer (02.02.2018)
 *
 */
public interface IUserDao {
	User findUserByUsername(String username);

	User saveUser(User user);
}
