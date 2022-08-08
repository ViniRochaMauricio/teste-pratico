package com.example.api.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}


	@GetMapping("/busca")
		public Page<Customer> findAll(
			@RequestParam(
				value = "page",
				required = false,
				defaultValue = "0") int page,
			@RequestParam(
				value = "size",
				required = false,
				defaultValue = "10") int size){
					return service.busca(page, size);

				}
			
			
	
	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastroCliente (@RequestBody Customer customer,
											  @RequestHeader HttpHeaders httpHeaders,
											  HttpServletRequest request){
	 try{
		service.cadastraCliente(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	 }catch(Exception e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no cadastro do cliente");
	 }
	}

	@PatchMapping("/edita")
	public ResponseEntity<?> editaCliente (@RequestBody Customer customer,
										   @RequestHeader HttpHeaders httpHeaders,
										   HttpServletRequest request){
       try{
		service.editaCliente(customer);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	   }catch(Exception e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no update do cliente");
	   }
	}
	

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

}
