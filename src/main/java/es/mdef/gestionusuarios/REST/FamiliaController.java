package es.mdef.gestionusuarios.REST;

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
import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Familia;
import es.mdef.gestionusuarios.entidades.FamiliaImpl;
import es.mdef.gestionusuarios.repositorios.FamiliaRepositorio;
import es.mdef.gestionusuarios.repositorios.PreguntaRepositorio;


@RestController
@RequestMapping("/familias")
public class FamiliaController {
	private final FamiliaRepositorio repositorio;
	private final FamiliaAssembler assembler;
	private final FamiliaListaAssembler famListaAssembler;
	private final PreguntaListaAssembler prListaAssembler;
	private final Logger log;
		
	FamiliaController(FamiliaRepositorio repositorio, FamiliaAssembler assembler, 
			PreguntaListaAssembler prListaAssembler, FamiliaListaAssembler famListaAssembler) {
			this.repositorio = repositorio;
			this.assembler = assembler;
			this.prListaAssembler = prListaAssembler;
			this.famListaAssembler = famListaAssembler;
			log = GestionUsuariosApplication.log;
		}
		
		@GetMapping("{id}")
		public EntityModel<FamiliaImpl> one(@PathVariable Long id) {
			FamiliaImpl familia = repositorio.findById(id)
					.orElseThrow(() -> new RegisterNotFoundException(id, "familia"));
			log.info("Recuperado " + familia);
			return assembler.toModel(familia);
		}
		
		@GetMapping("{id}/preguntas")
		public CollectionModel<PreguntaListaModel> preguntasDeFamilia(@PathVariable Long id) {
			FamiliaImpl familia = repositorio.findById(id)
					.orElseThrow(() -> new RegisterNotFoundException(id, "familia"));
		    return prListaAssembler.toCollection(familia.getPreguntas());
			
		}
		
//		@GetMapping("{id}/usuarios")
//		public CollectionModel<UsuarioListaModel> usuariosDeFamilia(@PathVariable Long id) {
//			FamiliaImpl familia = repositorio.findById(id)
//					.orElseThrow(() -> new RegisterNotFoundException(id, "familia"));
//		    return famListaAssembler.toCollection(familia.getUsuarios());
//		    return null;
//		}
		
		@PutMapping("{id}")
		public EntityModel<FamiliaImpl> edit(@PathVariable Long id, @RequestBody FamiliaModel model) {
			
			FamiliaImpl familia = repositorio.findById(id).map(fam -> {
				fam.setEnunciado(model.getEnunciado());
				fam.setTamanio(model.getTamanio());
			return repositorio.save(fam);
			})
			.orElseThrow(() -> new RegisterNotFoundException(id, "Familia"));
			log.info("Actualizado " + familia);
			return assembler.toModel(familia);
		}
		
		@DeleteMapping("{id}")
		public void delete(@PathVariable Long id) {
		    log.info("Borrado Familia " + id);
//		    if (repositorio.existsById(id)) {
		        repositorio.deleteById(id);
//		    } else {
//		        throw new RegisterNotFoundException(id, "familia");
//		    }
		}
		
		
		@GetMapping
		public CollectionModel<FamiliaListaModel> all() {
			return famListaAssembler.toCollection(repositorio.findAll());
		}
		
		@PostMapping
		public EntityModel<FamiliaImpl> add(@RequestBody FamiliaModel model) {
//			FamiliaImpl familia = repositorio.save(assembler.toEntity(model));
//			log.info("Añadido " + familia);
//			return assembler.toModel(familia);
			return null;
		}
}