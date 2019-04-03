package br.com.mrnt.challenge.customers.rest.service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mrnt.challenge.customers.rest.service.bean.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{
	Page<Email> findByCustomerId(Long customerId, Pageable pageable);

	Optional<Email> findByIdAndCustomerId(Long id, Long customerId);
}
