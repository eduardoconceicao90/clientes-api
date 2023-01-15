package com.clientesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.clientesapi.model.Cliente;
import com.clientesapi.repository.ClienteRepository;

@SpringBootApplication
public class ClientesApiApplication {

	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {
			Cliente cliente = Cliente.builder().cpf("60521090091").nome("Eduardo Conceição").build();
			repository.save(cliente);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientesApiApplication.class, args);
	}

}
