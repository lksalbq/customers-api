package br.com.mrnt.challenge.customers.rest.service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mrnt.challenge.customers.rest.service.bean.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{
	Page<Phone> findByCustomerId(Long customerId, Pageable pageable);

	Optional<Phone> findByIdAndCustomerId(Long id, Long customerId);
}
