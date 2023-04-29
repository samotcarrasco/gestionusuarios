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
	
}	
