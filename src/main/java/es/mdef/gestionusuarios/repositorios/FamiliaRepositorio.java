package es.mdef.gestionusuarios.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionusuarios.entidades.Familia;

public interface FamiliaRepositorio extends JpaRepository<Familia, Long> {
	
}