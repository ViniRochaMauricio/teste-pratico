package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	private CustomerJPARepository customerJPARepository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public void cadastraCliente(Customer customer) throws Exception{
		if(validaCampo(customer)){
			repository.save(customer);
		}else{
			throw new Exception();
		}
	}

	public void editaCliente(Customer customer){
		if(validaCampo(customer)){
			repository.update(customer);
		}else{
			throw new Exception();
		}

	public Boolean validaCampo (Customer customer){
		Boolean valida = false;
		if(customer.getName().isEmpty() || customer.getName().isBlank() || customer.getEmail().isEmpty() || customer.getEmail().isBlank()){
			valida = false
		}else {
			valida = true;
		}
	}
	}

}
