package de.mb.tournamentCommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import de.mb.tournamentCommunity.repository.api.IUserDao;
import de.mb.tournamentCommunity.repository.model.User;

/**
 * @author Martin Bauer (02.02.2018)
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired private IUserDao userDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		final User user = userDao.findUserByUsername(username);
		UserBuilder builder = null;
		if (user != null) {

			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());
			final String[] authorities = user.getAuthorities().stream().map(a -> a.getAuthority())
					.toArray(String[]::new);

			builder.authorities(authorities);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		return builder.build();
	}

}
