package com.br.apipadrao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import com.br.apipadrao.dto.UserDTO;
import com.br.apipadrao.entity.User;
import com.br.apipadrao.repository.UserRepository;

public class UserService implements UserDetailsService {
	
	@Autowired
    UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User create(UserDTO userDTO) {
		userDTO.getUser().setPassword(passwordEncoder.encode(userDTO.getPassword()));
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
	
	public User remove(Long id) throws Exception{
		User user = userRepository.getOne(id);
		user.setActive(false);
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
