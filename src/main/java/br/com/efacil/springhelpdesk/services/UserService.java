package br.com.efacil.springhelpdesk.services;

import java.util.List;

import br.com.efacil.springhelpdesk.models.User;

public interface UserService {
	public List<User> findAll();
	public User findById(Long id);
	public User create(User user);
	public User update(Long id, User user);
	public Boolean delete(Long id);
}
