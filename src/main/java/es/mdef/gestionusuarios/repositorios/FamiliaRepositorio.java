package es.mdef.gestionusuarios.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionusuarios.entidades.FamiliaImpl;

public interface FamiliaRepositorio extends JpaRepository<FamiliaImpl, Long> {
	
}