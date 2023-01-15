package com.clientesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clientesapi.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
