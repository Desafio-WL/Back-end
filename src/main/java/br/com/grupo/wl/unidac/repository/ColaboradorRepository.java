package br.com.grupo.wl.unidac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.grupo.wl.unidac.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{
	
	
boolean existsByCpf(String cpf);

boolean existsByAlimento(String alimento);
}
