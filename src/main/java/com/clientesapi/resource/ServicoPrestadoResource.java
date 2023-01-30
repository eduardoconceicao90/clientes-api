package com.clientesapi.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clientesapi.model.ServicoPrestado;
import com.clientesapi.model.dto.ServicoPrestadoDTO;
import com.clientesapi.service.ServicoPrestadoService;

@RestController
@RequestMapping(value = "/servicos-prestados")
public class ServicoPrestadoResource {
	
	@Autowired
	private ServicoPrestadoService service;
	
	@GetMapping
	public ResponseEntity<List<ServicoPrestado>> findByNomeClienteAndMes(
							@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
							@RequestParam(value = "mes", required = false) Integer mes){
		
		List<ServicoPrestado> find = service.findByNomeClienteAndMes(nome, mes);
		return ResponseEntity.ok().body(find);
	}
	
	@PostMapping
	public ResponseEntity<ServicoPrestadoDTO> create(@RequestBody ServicoPrestadoDTO dto){
		ServicoPrestado newObj = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
