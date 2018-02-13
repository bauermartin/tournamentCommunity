package de.mb.tournamentCommunity.repository.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Martin Bauer (02.02.2018)
 *
 */
@Entity
@Table(name = "users")
public class User {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user") private Set<Authorities> authorities = new HashSet<>();

	@Column(name = "enabled", nullable = false) private boolean enabled;

	@Column(name = "password", nullable = false) private String password;

	@Id @Column(name = "username") private String username;

	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setAuthorities(final Set<Authorities> authorities) {
		this.authorities = authorities;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

}
