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
import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.repositorios.PreguntaRepositorio;
import es.mdef.gestionusuarios.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
	private final PreguntaRepositorio repositorio;
	private final UsuarioRepositorio uRepositorio;
	private final PreguntaAssembler prAssembler;
	private final UsuarioAssembler usuarioAssembler;
	private final PreguntaListaAssembler listaAssembler;
	private final Logger log;
	
	PreguntaController(PreguntaRepositorio repositorio, PreguntaAssembler prAssembler, 
			PreguntaListaAssembler listaAssembler, UsuarioAssembler usuarioAssembler, UsuarioRepositorio uRepositorio) {
		this.repositorio = repositorio;
		this.prAssembler = prAssembler;
		this.usuarioAssembler = usuarioAssembler;
		this.listaAssembler = listaAssembler;
		this.uRepositorio = uRepositorio;
		log = GestionUsuariosApplication.log;
	}

	@GetMapping("{id}")
	public PreguntaModel one(@PathVariable Long id) {
		Pregunta pregunta = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		return prAssembler.toModel(pregunta);
	}
	
	
	@GetMapping
	public CollectionModel<PreguntaListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	

	@PostMapping
	public PreguntaModel add(@RequestBody PreguntaPostModel model) {
		Pregunta pregunta = repositorio.save(prAssembler.toEntity(model));
		log.info("Añadido " + pregunta);
		return prAssembler.toModel(pregunta);
	}

	
//	@PutMapping("{id}")
//	public EntityModel<Pregunta> edit(@PathVariable Long id, @RequestBody PreguntaModel model) {
//		Pregunta pregunta = repositorio.findById(id).map(preg -> {
//			preg.setEnunciado(model.getEnunciado());
//			preg.setUsuario(model.getUsuario());
//			return repositorio.save(preg);
//		})
//		.orElseThrow(() -> new RegisterNotFoundException(id, "pregunta"));
//		log.info("Actualizado " + pregunta);
//		return prAssembler.toModel(pregunta);
//	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado pregunta " + id);
		repositorio.deleteById(id);
	}
	
//	@GetMapping("porEstado")
//	public CollectionModel<PreguntaListaModel> pedidosPorEstado(@RequestParam PedidoEstado estado) {
//		return listaAssembler.toCollectionModel(
//				repositorio.findPedidoByEstado(estado)
//				);
//	}
}
