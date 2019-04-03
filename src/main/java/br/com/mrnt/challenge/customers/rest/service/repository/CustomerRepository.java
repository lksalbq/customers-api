package br.com.mrnt.challenge.customers.rest.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mrnt.challenge.customers.rest.service.bean.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}