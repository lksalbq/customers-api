package br.com.mrnt.challenge.customers.rest.service.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.mrnt.challenge.customers.rest.service.support.AuditModel;

@Entity
@Table(name = "customers")
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
	
	public Customer() {
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