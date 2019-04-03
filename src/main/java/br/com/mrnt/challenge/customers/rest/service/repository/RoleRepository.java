package br.com.mrnt.challenge.customers.rest.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mrnt.challenge.customers.rest.service.bean.Role;
import br.com.mrnt.challenge.customers.rest.service.bean.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}