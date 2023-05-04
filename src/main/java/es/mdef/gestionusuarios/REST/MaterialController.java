package es.mdef.gestionusuarios.REST;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import es.mdef.gestionusuarios.entidades.Inventariable;
import es.mdef.gestionusuarios.entidades.Material;
import es.mdef.gestionusuarios.entidades.Material.TipoMaterial;
import es.mdef.gestionusuarios.entidades.NoInventariable;
import es.mdef.gestionusuarios.repositorios.MaterialRepositorio;
import es.mdef.gestionusuarios.validation.RegisterNotFoundException;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
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
		
		
		@PutMapping("{id}")
		public MaterialModel edit(@Valid @PathVariable Long id, @RequestBody MaterialPostModel model) {
		Material material = repositorio.findById(id).map(mat -> {
			
				  //solamente actualizamos los datos necesarios de cada rol cuando corresponda
//		        if (model.getTipoMaterial() == TipoMaterial.Inventariable) {
//		          	Inventariable inv = new Inventariable();
//		        	repositorio.actualizarInventariable(model.getNumeroSerie(), model.getNoc(), id);
//			        mat = inv;
//		        }else if (model.getTipoMaterial() == TipoMaterial.noInventariable) {
//		           	NoInventariable noInv = new NoInventariable();
//		        	repositorio.actualizarNoInventariable(model.getBonificacion(), id);
//			        mat = noInv;
//		        }
		        
		    	mat.setNombre(model.getNombre());
				mat.setDescripcion(model.getDescripcion());
				mat.setCantidad(model.getCantidad());
				mat.setDimensiones(model.getDimensiones());
				mat.setFechaAdquisicion(model.getFechaAdquisicion());
				mat.setFechaOferta(model.getFechaOferta());
				mat.setCantidad(model.getCantidad());
				mat.setMilis(model.getMilis());
				mat.setEstado(model.getEstado());
				
				//las entidades con las que esta relacionada
				mat.setDeptoOferta(model.getDptoOferta());
				mat.setCategoria(model.getCategoria());
				mat.setDptoAdquisicion(model.getDptoAdquisicion());
				
			
			return repositorio.save(mat);
			})
			.orElseThrow(() -> new RegisterNotFoundException(id, "Material"));
			log.info("Actualizado " + material);
			return assembler.toModel(material);
	}
		
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
