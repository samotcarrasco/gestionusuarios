package es.mdef.gestionusuarios.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionusuarios.entidades.Categoria;
import es.mdef.gestionusuarios.entidades.Departamento;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {
	
}