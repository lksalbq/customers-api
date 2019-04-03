package br.com.mrnt.challenge.customers.rest.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mrnt.challenge.customers.rest.service.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}