package com.clientesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clientesapi.model.ServicoPrestado;

@Repository
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long> {

	@Query("SELECT s FROM ServicoPrestado s JOIN s.cliente c "
			+ "WHERE upper(c.nome) LIKE upper(:nome) AND MONTH(s.data)=:mes")
	List<ServicoPrestado> findByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);

}
