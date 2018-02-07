package br.com.efacil.springhelpdesk.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.efacil.springhelpdesk.models.Role;
import br.com.efacil.springhelpdesk.models.User;
import br.com.efacil.springhelpdesk.repositories.RoleRepository;
import br.com.efacil.springhelpdesk.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return this.userRepository.findOne(id);
	}

	@Override
	public User create(User user) {
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Role userRole = this.roleRepository.findByName("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return this.userRepository.save(user);
	}

	@Override
	public Boolean delete(Long id) {
		User user = this.userRepository.findOne(id);
		if (user != null) {
			this.userRepository.delete(user);
			return true;
		}
		
		return false;
	}

	@Override
	public User update(Long id, User user) {
		User userExistent = this.userRepository.findOne(id);
		if (userExistent != null) {
			user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			BeanUtils.copyProperties(user, userExistent, "id");
			this.userRepository.save(userExistent);
		}
		return userExistent;
	}
	
	

}
