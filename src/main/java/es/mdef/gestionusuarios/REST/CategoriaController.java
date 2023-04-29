package es.mdef.gestionusuarios.REST;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionusuarios.GestionUsuariosApplication;
import es.mdef.gestionusuarios.entidades.Categoria;
import es.mdef.gestionusuarios.repositorios.CategoriaRepositorio;
import es.mdef.gestionusuarios.validation.RegisterNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	private final CategoriaRepositorio repositorio;
	private final CategoriaAssembler assembler;
	private final CategoriaListaAssembler listaAssembler;
	private final MaterialListaAssembler matListaAssembler;

	private final Logger log;
		
	CategoriaController(CategoriaRepositorio repositorio, CategoriaAssembler assembler, 
			CategoriaListaAssembler listaAssembler, MaterialListaAssembler matListaAssembler) {
			this.repositorio = repositorio;
			this.assembler = assembler;
			this.listaAssembler = listaAssembler;
			this.matListaAssembler = matListaAssembler;
			log = GestionUsuariosApplication.log;
		}
		
	
		@GetMapping("{id}")
			public CategoriaModel one(@PathVariable Long id) {
			Categoria categoria = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "categoria"));
			log.info("Recuperada " + categoria);
			return assembler.toModel(categoria);
		}
		
		@GetMapping("{id}/materiales")
		public CollectionModel<MaterialListaModel> materialesDeCategoria(@PathVariable Long id) {
			Categoria categoria = repositorio.findById(id)
					.orElseThrow(() -> new RegisterNotFoundException(id, "categoria"));
		    return matListaAssembler.toCollection(categoria.getMateriales());
		}
		

		@GetMapping
		public CollectionModel<CategoriaListaModel> all() {
			return listaAssembler.toCollection(repositorio.findAll());
		}

		@PostMapping
		public CategoriaModel add(@Valid @RequestBody CategoriaPostModel model) {
			Categoria categoria = repositorio.save(assembler.toEntity(model));
			log.info("Añadido " + categoria);
			return assembler.toModel(categoria);
		}
		
		
		@PutMapping("{id}")
		public CategoriaModel edit(@Valid @PathVariable Long id, @RequestBody CategoriaPostModel model) {
			  
			Categoria categoria = repositorio.findById(id).map(cat -> {
				cat.setCategoria(model.getCategoria());
				cat.setDescripcion(model.getDescripcion());
				cat.setGrupo(model.getGrupo());
				cat.setMinMilis(model.getMinMilis());
				cat.setMaxMilis(model.getMaxMilis());
				
			return repositorio.save(cat);
			})
			.orElseThrow(() -> new RegisterNotFoundException(id, "Categoria"));
			log.info("Actualizado " + categoria);
			return assembler.toModel(categoria);
	}
		
		

		
		@DeleteMapping("{id}")
		public void delete(@PathVariable Long id) {
		    log.info("Borrado Categoria " + id);
		    Categoria categoria = repositorio.findById(id).map(fam -> {
					repositorio.deleteById(id);	
					return fam;
				})
				.orElseThrow(() -> new RegisterNotFoundException(id, "categoria"));
		}
	
}
