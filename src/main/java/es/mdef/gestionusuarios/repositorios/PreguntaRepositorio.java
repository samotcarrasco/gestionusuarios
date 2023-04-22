package es.mdef.gestionusuarios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.mdef.gestionusuarios.entidades.Pregunta;

public interface PreguntaRepositorio extends JpaRepository<Pregunta, Long> {
	List<Pregunta> findByUsuarioId(Long id);
    
	
	//este métodos son a parte de la práctica, los hago por estudio propio
	@Query(value="SELECT * FROM Preguntas WHERE familiaid > :minFamilia", nativeQuery = true)
    List<Pregunta> preguntasMinFamilias(Long minFamilia);
		
}