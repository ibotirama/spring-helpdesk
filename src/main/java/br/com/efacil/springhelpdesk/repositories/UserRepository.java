package br.com.efacil.springhelpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.efacil.springhelpdesk.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
