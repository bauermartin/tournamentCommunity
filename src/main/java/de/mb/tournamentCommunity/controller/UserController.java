package de.mb.tournamentCommunity.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import de.mb.tournamentCommunity.repository.model.Authorities;
import de.mb.tournamentCommunity.repository.model.User;
import de.mb.tournamentCommunity.service.api.IUserService;

/**
 * @author Martin Bauer (02.02.2018)
 *
 */
@Controller
public class UserController {
	@Autowired IUserService service;

	@GetMapping("/")
	public String index(final Model model, final Principal principal) {
		System.out.println(principal.getName());
		System.out.println("index");
		return "index.html";
	}

}
