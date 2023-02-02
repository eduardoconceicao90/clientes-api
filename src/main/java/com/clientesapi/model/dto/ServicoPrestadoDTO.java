package com.clientesapi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoPrestadoDTO {

	private String descricao;
	private String preco;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String data;
	private Long id_cliente;

}
