package com.br.apipadrao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.br.apipadrao.dto.UserDTO;
import com.br.apipadrao.entity.User;
import com.br.apipadrao.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public List<User> list() {
		return userRepository.findAll();
	}

	public User findById(long id) {
		return userRepository.getOne(id);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

//	public User create(UserDTO userDTO) {
//		userDTO.getUser().setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        return userRepository.save(userDTO.getUser());
//    }

	public User create(UserDTO userDTO) {
		userDTO.getUser().setPassword(userDTO.getPassword());
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		return user;
	}
}
