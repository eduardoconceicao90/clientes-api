package com.clientesapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.clientesapi.model.Usuario;
import com.clientesapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public Usuario create(Usuario obj) {
		obj.setId(null);
		boolean exists = repository.existsByUsername(obj.getUsername());
		
		if(exists) {
			throw new DataIntegrityViolationException("Usuário já cadastrado!");
		}
		
		return repository.save(obj);		
	}
	
	
}
