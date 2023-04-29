package es.mdef.gestionusuarios.repositorios;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionusuarios.entidades.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

	
}