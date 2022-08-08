package com.example.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.api.domain.Customer;

public interface CustomerRepository extends JpaRepositories<Customer, Long> {

	Page<Customer> findAllByOrderByNameAsc(
		Pageable pageable
	);

}
