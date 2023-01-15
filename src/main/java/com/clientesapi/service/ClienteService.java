package com.clientesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.clientesapi.model.Cliente;
import com.clientesapi.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(Cliente obj) {
		obj.setId(null);
		Cliente newObj = repository.save(obj);
		return newObj;
	}

	public void delete(Long id) {
		findById(id);		
		if(id != null) {
			repository.deleteById(id);					
		} else {
			new ResponseStatusException(HttpStatus.NOT_FOUND);			
		}
	}

}
