package es.mdef.gestionusuarios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionusuarios.entidades.Pregunta;

public interface PreguntaRepositorio extends JpaRepository<Pregunta, Long> {
	List<Pregunta> findByUsuarioId(Long id);
}