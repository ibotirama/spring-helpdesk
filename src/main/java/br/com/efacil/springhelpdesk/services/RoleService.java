package br.com.efacil.springhelpdesk.services;

import java.util.List;

import br.com.efacil.springhelpdesk.models.Role;

public interface RoleService {
	public List<Role> findAll();
	public Role findById(Long id);
	public Role create(Role role);
	public Boolean delete(Long id);
}
