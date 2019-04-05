package br.com.mrnt.challenge.customers.rest.service.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.mrnt.challenge.customers.rest.service.support.AuditModel;

@Entity
@Table(name = "customer")
public class Customer extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 100, min = 3)
	@Pattern(regexp = "^[a-zA-Z0-9-\\s+]+$")
	private String name;

	@NotNull
	@Size(max = 11)
	@Pattern(regexp = "^\\d{11}$")
	@Column(unique = true)
	private String cpf;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Address address = new Address();

	@NotNull
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Phone> phones = new HashSet<>();

	@NotNull
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	@Size(min = 1)
	private Set<Email> emails = new HashSet<>();

	public Customer() {
	}

	public Customer(String name, String cpf, Address address, Set<Phone> phones, Set<Email> emails) {
		this.name = name;
		this.cpf = cpf;
		this.address = address;
		this.phones = phones;
		this.emails = emails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id2) {
		this.id = id2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj instanceof Customer) {
			Customer c = (Customer) obj;
			result = c.getId() == this.getId();
		}

		return result;
	}


}