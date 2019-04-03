package br.com.mrnt.challenge.customers.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import br.com.mrnt.challenge.customers.rest.service.repository.AddressRepository;
import br.com.mrnt.challenge.customers.rest.service.repository.CustomerRepository;
import br.com.mrnt.challenge.customers.rest.service.repository.EmailRepository;
import br.com.mrnt.challenge.customers.rest.service.repository.PhoneRepository;

@SpringBootApplication
@EnableJpaAuditing
public class CustomersApiApplication implements CommandLineRunner{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private EmailRepository emailRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CustomersApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Clean up database tables
		addressRepository.deleteAllInBatch();
		phoneRepository.deleteAll();
		emailRepository.deleteAll();
		customerRepository.deleteAllInBatch();
	}
}
