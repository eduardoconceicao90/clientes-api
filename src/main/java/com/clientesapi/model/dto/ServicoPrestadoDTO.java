package com.clientesapi.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoPrestadoDTO {

	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@NotEmpty(message = "{campo.preco.obrigatorio}")
	private String preco;
	
	@NotEmpty(message = "{campo.data.obrigatorio}")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String data;
	
	@NotNull(message = "{campo.cliente.obrigatorio}")
	private Long id_cliente;

}
