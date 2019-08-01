package com.salbin.gatewayserver.service.serviceimpl;

import com.salbin.gatewayserver.auth.JwtToken;
import com.salbin.gatewayserver.auth.User;
import com.salbin.gatewayserver.exception.CustomException;
import com.salbin.gatewayserver.repository.JwtTokenRepository;
import com.salbin.gatewayserver.repository.UserRepository;
import com.salbin.gatewayserver.security.JwtTokenProvider;
import com.salbin.gatewayserver.service.ILoginService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtTokenRepository jwtTokenRepository;

	@Override
	public String login(String username, String password) {
		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			User user = userRepository.findByEmail(username);
//			if (user == null || user.getRole() == null || user.getRole().isEmpty()) {
				if (user == null) {
				throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
			}
			// NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically
			// for us.
			// Since we are using custom token using JWT we should add ROLE_ prefix
			List<String> list = new ArrayList<String>();
//			Role role =new Role(1,"admin");
//			Role role1 =new Role(1,"user");
			list.add("admin");
				String token = jwtTokenProvider.createToken(username,list);
			return token;

		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
		}
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public boolean logout(String token) {
		jwtTokenRepository.delete(new JwtToken(token));
		return true;
	}

	@Override
	public Boolean isValidToken(String token) {
		return jwtTokenProvider.validateToken(token);
	}

	@Override
	public String createNewToken(String token) {
		String username = jwtTokenProvider.getUsername(token);
		List<String> roleList = jwtTokenProvider.getRoleList(token);
		String newToken = jwtTokenProvider.createToken(username, roleList);
		return newToken;
	}
}
