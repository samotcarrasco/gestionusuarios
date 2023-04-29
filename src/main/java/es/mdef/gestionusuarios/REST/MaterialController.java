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
import es.mdef.gestionusuarios.entidades.Material;
import es.mdef.gestionusuarios.repositorios.MaterialRepositorio;
import es.mdef.gestionusuarios.validation.RegisterNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/materiales")
public class MaterialController {
	private final MaterialRepositorio repositorio;
	private final MaterialAssembler assembler;
	private final MaterialListaAssembler listaAssembler;
	
	private final Logger log;
		
	MaterialController(MaterialRepositorio repositorio, MaterialAssembler assembler, 
			MaterialListaAssembler listaAssembler) {
			this.repositorio = repositorio;
			this.assembler = assembler;
			this.listaAssembler = listaAssembler;
			log = GestionUsuariosApplication.log;
		}
		
	
		@GetMapping("{id}")
			public MaterialModel one(@PathVariable Long id) {
			Material material = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "material"));
			log.info("Recuperada " + material);
			return assembler.toModel(material);
		}
		
		
		@GetMapping
		public CollectionModel<MaterialListaModel> all() {
			return listaAssembler.toCollection(repositorio.findAll());
		}

		@PostMapping
		public MaterialModel add(@Valid @RequestBody MaterialPostModel model) {
			Material material = repositorio.save(assembler.toEntity(model));
			log.info("Añadido " + material);
			return assembler.toModel(material);
		}
		
//		
//		@PutMapping("{id}")
//		public MaterialModel edit(@Valid @PathVariable Long id, @RequestBody MaterialPostModel model) {
//			  
//			Material material = repositorio.findById(id).map(mat -> {
//				mat.setMaterial(model.getMaterial());
//				mat.setDescripcion(model.getDescripcion());
//				mat.setGrupo(model.getGrupo());
//				mat.setMinMilis(model.getMinMilis());
//				mat.setMaxMilis(model.getMaxMilis());
//				
//			return repositorio.save(mat);
//			})
//			.orElseThrow(() -> new RegisterNotFoundException(id, "Material"));
//			log.info("Actualizado " + material);
//			return assembler.toModel(material);
//	}
//		
//		
//
//		
		@DeleteMapping("{id}")
		public void delete(@PathVariable Long id) {
		    log.info("Borrado Material " + id);
		    Material material = repositorio.findById(id).map(fam -> {
					repositorio.deleteById(id);	
					return fam;
				})
				.orElseThrow(() -> new RegisterNotFoundException(id, "material"));
		}
	
}
