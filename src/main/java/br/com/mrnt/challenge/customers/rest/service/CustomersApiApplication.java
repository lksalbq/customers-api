package br.com.mrnt.challenge.customers.rest.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.mrnt.challenge.customers.rest.service.bean.Address;
import br.com.mrnt.challenge.customers.rest.service.bean.Customer;
import br.com.mrnt.challenge.customers.rest.service.bean.Email;
import br.com.mrnt.challenge.customers.rest.service.bean.Phone;
import br.com.mrnt.challenge.customers.rest.service.bean.PhoneType;
import br.com.mrnt.challenge.customers.rest.service.bean.Role;
import br.com.mrnt.challenge.customers.rest.service.bean.RoleName;
import br.com.mrnt.challenge.customers.rest.service.bean.User;
import br.com.mrnt.challenge.customers.rest.service.repository.AddressRepository;
import br.com.mrnt.challenge.customers.rest.service.repository.CustomerRepository;
import br.com.mrnt.challenge.customers.rest.service.repository.EmailRepository;
import br.com.mrnt.challenge.customers.rest.service.repository.PhoneRepository;
import br.com.mrnt.challenge.customers.rest.service.repository.RoleRepository;
import br.com.mrnt.challenge.customers.rest.service.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class CustomersApiApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;

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
		userRepository.deleteAllInBatch();
		roleRepository.deleteAllInBatch();

		
		for(int i=0; i < 10; i++) {			
			// Create default address
			Address address = new Address("71050181", "Guara", "QE 15", "Brasilia", "DF", "complemento");

			// Create default phones numbers
			Phone p1 = new Phone("6130281866", PhoneType.RESIDENTIAL);
			Phone p2 = new Phone("61981006447", PhoneType.CELLPHONE);
			Set<Phone> phones = new HashSet<Phone>();
			phones.add(p1);
			phones.add(p2);

			// Create default email customer
			Email e1 = new Email("lks.albq@gmail.com");
			Email e2 = new Email("lks.albq@gmail.com");
			Set<Email> emails = new HashSet<Email>();
			emails.add(e1);
			emails.add(e2);

			// Create default customer
			Customer customer = new Customer("Lucas", String.format("%s%s%s%s%s%s%s%s%s%s%s",i,i,i,i,i,i,i,i,i,i,i).toString(), address, phones, emails);
			customer.getAddress().setCustomer(customer);
			customer.getPhones().forEach((phone) -> phone.setCustomer(customer));
			customer.getEmails().forEach((email) -> email.setCustomer(customer));
			customerRepository.save(customer);			
		}
		
		
		// create default roles and users

		Role roleUser = new Role(RoleName.ROLE_USER);
		Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
		
		roleRepository.save(roleUser);
		roleRepository.save(roleAdmin);

		User admin = new User("admin", "12345");
		admin.setRoles(Collections.singleton(roleAdmin));
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		userRepository.save(admin);

		User comum = new User("comum", "12345");
		comum.setRoles(Collections.singleton(roleUser));
		comum.setPassword(passwordEncoder.encode(comum.getPassword()));
		userRepository.save(comum);


	}
}
