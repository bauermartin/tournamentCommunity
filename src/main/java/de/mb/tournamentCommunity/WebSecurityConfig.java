package de.mb.tournamentCommunity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author Martin Bauer (02.02.2018)
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired private UserDetailsService userDetailsService;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http
				// we don't need CSRF because our token is invulnerable
				.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(new Http403ForbiddenEntryPoint()).and().authorizeRequests()
				.antMatchers("/js/**", "/css/**", "/assets/**", "/index.html", "/register").permitAll()
				.and().authorizeRequests().antMatchers("/rest/**").hasAnyRole("ADMIN", "USER").and()
				.formLogin().loginProcessingUrl("/loginAction")
				.successHandler(new AuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(final HttpServletRequest request,
							final HttpServletResponse response, final Authentication authentication)
							throws IOException, ServletException {
						response.setStatus(HttpStatus.ACCEPTED.value());
					}
				}).failureHandler(new AuthenticationFailureHandler() {

					@Override
					public void onAuthenticationFailure(final HttpServletRequest request,
							final HttpServletResponse response, final AuthenticationException exception)
							throws IOException, ServletException {
						response.sendError(HttpStatus.UNAUTHORIZED.value(),
								"Invalid Credentials, login failed.");
					}
				}).permitAll().and().logout().logoutSuccessHandler(new LogoutSuccessHandler() {

					@Override
					public void onLogoutSuccess(final HttpServletRequest request,
							final HttpServletResponse response, final Authentication authentication)
							throws IOException, ServletException {}
				}).permitAll();

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
