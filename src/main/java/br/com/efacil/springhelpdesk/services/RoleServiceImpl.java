package br.com.efacil.springhelpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.efacil.springhelpdesk.models.Role;
import br.com.efacil.springhelpdesk.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role create(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findById(Long id) {
		return this.roleRepository.findOne(id);
	}

	@Override
	public Boolean delete(Long id) {
		Role role = this.findById(id);
		if (role != null) {
			this.roleRepository.delete(id);
			return true;
		}
		
		return false;
	}
	
	

}
