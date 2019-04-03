package br.com.mrnt.challenge.customers.rest.service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mrnt.challenge.customers.rest.service.bean.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
   Page<Address> findByCustomerId(Long customerId, Pageable pageable);
   Optional<Address> findByIdAndCustomerId(Long id, Long customerId);
}
