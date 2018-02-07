package br.com.efacil.springhelpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.efacil.springhelpdesk.models.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>{
	Role findByName(String name);

}
