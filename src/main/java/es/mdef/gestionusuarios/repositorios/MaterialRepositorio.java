package es.mdef.gestionusuarios.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.mdef.gestionusuarios.entidades.Material;
import jakarta.transaction.Transactional;

public interface MaterialRepositorio extends JpaRepository<Material, Long> {
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.materiales SET "
					+ "inventariable = 'I',"
					+ "numero_serie = :numero_serie, " 
					+ "noc = :noc, "
					+ "bonificacion = null "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarInventariable(
	    @Param("numero_serie") String numSerie,
	    @Param("noc") String noc,
	    @Param("id") Long id
	);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.materiales SET "
					+ "inventariable = 'I',"
					+ "numero_serie = null, " 
					+ "noc = null, "
					+ "bonificacion = :bonificacion "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarNoInventariable(
	    @Param("bonificacion") int bonificacion,
	    @Param("id") Long id
	);
	

	
}	
