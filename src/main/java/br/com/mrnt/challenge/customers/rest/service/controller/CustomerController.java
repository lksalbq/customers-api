package br.com.mrnt.challenge.customers.rest.service.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mrnt.challenge.customers.rest.service.bean.Customer;
import br.com.mrnt.challenge.customers.rest.service.repository.CustomerRepository;

@RestController
@Validated
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/api/customers")
	public Page<Customer> retrieveAllCustomers(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
	
	@GetMapping("/api/customers/{id}")
	public Customer retrieveCustomer(@PathVariable long id) {
		Optional<Customer> customer = customerRepository.findById(id);

		if (!customer.isPresent())
			throw new ResourceNotFoundException("id-" + id);

		return customer.get();
	}
	
	@DeleteMapping("/api/customers/{id}")
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<Object> deleteCustomer(@PathVariable long id) {
		return customerRepository.findById(id).map(customer -> {
			customerRepository.delete(customer);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found"));
	}
	
	@PostMapping("/api/customers")
	@RolesAllowed("ROLE_ADMIN")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		customer.getAddress().setCustomer(customer);
		customer.getEmails().forEach((email) -> email.setCustomer(customer));
		customer.getPhones().forEach((phone) -> phone.setCustomer(customer));
		return customerRepository.save(customer);

	}
	
	@RolesAllowed("ROLE_ADMIN")
	@PutMapping("/api/customers/{id}")
	public Customer updateCustomer(@Valid @RequestBody Customer customerRequest, @PathVariable Long id) {
		return customerRepository.findById(id).map(customer -> {
			customer.setName(customerRequest.getName());
			customer.setCpf(customerRequest.getCpf());
			customer.setAddress(customerRequest.getAddress());
			customer.setEmails(customerRequest.getEmails());
			customer.setPhones(customerRequest.getPhones());
			return customerRepository.save(customer);
		}).orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found"));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
