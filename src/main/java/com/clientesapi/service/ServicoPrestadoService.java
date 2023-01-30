package com.clientesapi.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.clientesapi.model.Cliente;
import com.clientesapi.model.ServicoPrestado;
import com.clientesapi.model.dto.ServicoPrestadoDTO;
import com.clientesapi.repository.ClienteRepository;
import com.clientesapi.repository.ServicoPrestadoRepository;
import com.clientesapi.util.BigDecimalConverter;

@Service
public class ServicoPrestadoService {

	@Autowired
	private ServicoPrestadoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BigDecimalConverter bigDecimalConverter;
	
	public List<ServicoPrestado> findByNomeClienteAndMes(String nome, Integer mes){
		return repository.findByNomeClienteAndMes("%" + nome + "%",mes);
	}
	
	public ServicoPrestado create(ServicoPrestadoDTO dto) {		
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Long id_cliente = dto.getId_cliente();
		
		Cliente cliente = clienteRepository.findById(id_cliente)
												.orElseThrow(() -> 
														new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente!"));
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return repository.save(servicoPrestado);
	}
}
