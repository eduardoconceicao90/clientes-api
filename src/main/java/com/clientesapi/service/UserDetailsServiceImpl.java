package com.clientesapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clientesapi.model.Usuario;
import com.clientesapi.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository
								.findByUsername(username)
								.orElseThrow(() -> new UsernameNotFoundException("Login não encontrado!"));
		return User.builder()
						.username(usuario.getUsername())
					    .password(usuario.getPassword())
					    .roles("USER")
					    .build();
	}

}
