package com.br.apipadrao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.br.apipadrao.domain.User;
import com.br.apipadrao.dto.UserDTO;
import com.br.apipadrao.enums.Profile;
import com.br.apipadrao.repositories.UserRepository;
import com.br.apipadrao.security.UserSS;
import com.br.apipadrao.services.exceptions.AuthorizationException;
import com.br.apipadrao.services.exceptions.ObjectNotFoundException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("null")
	public User findByEmail(String email) {
		UserSS user = this.authenticated();
		if (user == null && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		User obj = userRepository.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + User.class.getName());
		}
		return obj;
	}

	public List<User> list() {
		return userRepository.findAll();
	}

	public User find(Long id) {
		UserSS userSS = this.authenticated();
		if ((userSS == null || !userSS.hasRole(Profile.ADMIN)) && !id.equals(userSS.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<User> user = userRepository.findById(id);

		return user.orElseThrow(() -> new ObjectNotFoundException(
				"O Objeto não foi contrado, ID: " + id + ", Usuário: " + User.class.getName()));
	}

	public User create(UserDTO userDTO) {
		userDTO.getUser().setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userDTO.getUser().addProfile(Profile.USUARIO);
		return userRepository.save(userDTO.getUser());
	}

	public User update(UserDTO userDTO) throws Exception {
		return userRepository.save(updatePassword(userDTO));
	}

	private User updatePassword(UserDTO userDTO) throws Exception {
		User newUser = userDTO.getUser();
		User oldUser = userRepository.getOne(newUser.getId());

		newUser.setPassword(oldUser.getPassword());
		if (!StringUtils.isEmpty(userDTO.getPassword())) {
			String hash = passwordEncoder.encode(userDTO.getPassword());
			newUser.setPassword(hash);
		}
		return newUser;
	}

	public User remove(Long id) throws Exception {
		User user = userRepository.getOne(id);
		user.setActive(false);
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
	}
}
