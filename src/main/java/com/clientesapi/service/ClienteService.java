package com.clientesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientesapi.model.Cliente;
import com.clientesapi.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(Cliente obj) {
		obj.setId(null);
		Cliente newObj = repository.save(obj);
		return newObj;
	}

}
