package br.com.mrnt.challenge.customers.rest.service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.mrnt.challenge.customers.rest.service.bean.Email;

@Repository
public interface EmailRepository {
	Page<Email> findByCustomerId(Long postId, Pageable pageable);

	Optional<Email> findByIdAndCustomerId(Long id, Long customerId);
}
