package es.mdef.gestionusuarios.repositorios;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionusuarios.entidades.Categoria;
import es.mdef.gestionusuarios.entidades.Departamento;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByAbreviatura(String abreviatura);

}