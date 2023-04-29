package es.mdef.gestionusuarios.REST;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionusuarios.GestionUsuariosApplication;
import es.mdef.gestionusuarios.entidades.Departamento;
import es.mdef.gestionusuarios.repositorios.DepartamentoRepositorio;
import es.mdef.gestionusuarios.validation.RegisterNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
	private final DepartamentoRepositorio repositorio;
	private final DepartamentoAssembler assembler;
	private final DepartamentoListaAssembler listaAssembler;
	private final MaterialListaAssembler matListaAssembler;

	private final Logger log;
		
	DepartamentoController(DepartamentoRepositorio repositorio, DepartamentoAssembler assembler, 
			DepartamentoListaAssembler listaAssembler, MaterialListaAssembler matListaAssembler) {
			this.repositorio = repositorio;
			this.assembler = assembler;
			this.listaAssembler = listaAssembler;
			this.matListaAssembler = matListaAssembler;
			log = GestionUsuariosApplication.log;
		}
		
	
		@GetMapping("{id}")
			public DepartamentoModel one(@PathVariable Long id) {
			Departamento departamento = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "departamento"));
			log.info("Recuperada " + departamento);
			return assembler.toModel(departamento);
		}
		
		@GetMapping("{id}/materialesOfertados")
		public CollectionModel<MaterialListaModel> materialesOfertados(@PathVariable Long id) {
			Departamento departamento = repositorio.findById(id)
					.orElseThrow(() -> new RegisterNotFoundException(id, "departamento"));
		    return matListaAssembler.toCollection(departamento.getMaterialesOfertados());
		}
		
		@GetMapping("{id}/materialesAdquiridos")
		public CollectionModel<MaterialListaModel> materialesAdquiridos(@PathVariable Long id) {
			Departamento departamento = repositorio.findById(id)
					.orElseThrow(() -> new RegisterNotFoundException(id, "departamento"));
		    return matListaAssembler.toCollection(departamento.getMaterialesAdquiridos());
		}
		
		

		@GetMapping
		public CollectionModel<DepartamentoListaModel> all() {
			return listaAssembler.toCollection(repositorio.findAll());
		}

		@PostMapping
		public DepartamentoModel add(@Valid @RequestBody DepartamentoPostModel model) {
			Departamento departamento = repositorio.save(assembler.toEntity(model));
			log.info("Añadido " + departamento);
			return assembler.toModel(departamento);
		}
		
		
		@PutMapping("{id}")
		public DepartamentoModel edit(@Valid @PathVariable Long id, @RequestBody DepartamentoPostModel model) {
			  
			Departamento departamento = repositorio.findById(id).map(dep -> {
				
				dep.setNombre(model.getNombre());
				dep.setAbreviatura(model.getAbreviatura());
				dep.setAcuartelamiento(model.getAcuartelamiento());
				dep.setEmail(model.getEmail());
				dep.setResponsableEmpleo(model.getResponsableEmpleo());
				dep.setResponsableNombre(model.getResponsableNombre());
				dep.setTelefono(model.getTelefono());
				
			return repositorio.save(dep);
			})
			.orElseThrow(() -> new RegisterNotFoundException(id, "Departamento"));
			log.info("Actualizado " + departamento);
			return assembler.toModel(departamento);
	}
		
		//PATCH para el credito
		@PatchMapping("{id}")
		public DepartamentoModel patch(@Valid @PathVariable Long id, @RequestBody DepartamentoPatchModel model) {
		    Departamento departamento = repositorio.findById(id)
		        .orElseThrow(() -> new RegisterNotFoundException(id, "departamento"));

		         departamento.setCredito(model.getCredito());
		    
		    Departamento dep = repositorio.save(departamento);
		    log.info("Actualizado " + dep);
		    return assembler.toModel(dep);
		}
		

		
		@DeleteMapping("{id}")
		public void delete(@PathVariable Long id) {
		    log.info("Borrado Departamento " + id);
		    Departamento departamento = repositorio.findById(id).map(fam -> {
					repositorio.deleteById(id);	
					return fam;
				})
				.orElseThrow(() -> new RegisterNotFoundException(id, "departamento"));
		}
		
		
		@PutMapping("{id}/aumentarCredito")
		public DepartamentoModel aumentarCredito(@PathVariable Long id, @RequestParam int cantidad) {
		    Departamento departamento = repositorio.findById(id)
		        .orElseThrow(() -> new RegisterNotFoundException(id, "Departamento"));

		    departamento.aumentarCredito(cantidad);
		    repositorio.save(departamento);
		    log.info("Credito aumentado en " + cantidad + " para " + departamento);
		    return assembler.toModel(departamento);
		}
		
	
}
