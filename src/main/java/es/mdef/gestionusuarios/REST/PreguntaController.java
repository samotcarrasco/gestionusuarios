package es.mdef.gestionusuarios.REST;

import java.util.List;
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
import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.repositorios.PreguntaRepositorio;
import es.mdef.gestionusuarios.repositorios.UsuarioRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
	private final PreguntaRepositorio repositorio;
	private final PreguntaAssembler prAssembler;
	private final PreguntaListaAssembler listaAssembler;
	private final Logger log;
	
	PreguntaController(PreguntaRepositorio repositorio, PreguntaAssembler prAssembler, 
			PreguntaListaAssembler listaAssembler) {
		this.repositorio = repositorio;
		this.prAssembler = prAssembler;
		this.listaAssembler = listaAssembler;
		log = GestionUsuariosApplication.log;
	}

	@GetMapping("{id}")
	public PreguntaModel one(@PathVariable Long id) {
		Pregunta pregunta = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "pregunta"));
		return prAssembler.toModel(pregunta);
	}
	
	 
	@GetMapping("/preguntasminfamilia")
	    public CollectionModel<PreguntaListaModel> getPreguntasPorMinFamilia(
	            @RequestParam(value = "minFamilia", required = false) Long minFamilia) {
	        List<Pregunta> preguntas;
	        if (minFamilia != null) {
	            preguntas = repositorio.preguntasMinFamilias(minFamilia);
	        } else {
	            preguntas = repositorio.findAll();
	        }
	        return listaAssembler.toCollection(preguntas);
	    }
	  
	  @GetMapping("/preguntasminfamilia2")
	    public CollectionModel<PreguntaListaModel> getPreguntasMinFamilia2(
	            @RequestParam(value = "minFamilia", required = false) Long minFamilia) {  
	  	     	  List<Pregunta> preguntas = repositorio.findAll();
		        if (minFamilia != null) {
	            preguntas = preguntas.stream()
	                    .filter(p -> p.getFamilia() != null && p.getFamilia().getId() > minFamilia)
	                    .collect(Collectors.toList());
	        }

	        return listaAssembler.toCollection(preguntas);
	  }
	  
	@GetMapping
	public CollectionModel<PreguntaListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	

	@PostMapping
	public PreguntaModel add(@Valid @RequestBody PreguntaPostModel model) {
		Pregunta pregunta = repositorio.save(prAssembler.toEntity(model));
		log.info("Añadido " + pregunta);
		return prAssembler.toModel(pregunta);
	}

	
	@PutMapping("{id}")
	public PreguntaModel edit(@Valid @PathVariable Long id, @RequestBody PreguntaPostModel model) {
		Pregunta pregunta = repositorio.findById(id).map(preg -> {
			preg.setEnunciado(model.getEnunciado());
		    preg.setUsuario(model.getUsuario());
			preg.setFamilia(model.getFamilia());
			return repositorio.save(preg);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "pregunta"));
		log.info("Actualizado " + pregunta);
		return prAssembler.toModel(pregunta);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		
		Pregunta pregunta = repositorio.findById(id).map(preg -> {
			repositorio.deleteById(id);	
			return preg;
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "pregunta"));
		log.info("Actualizado " + pregunta);
		log.info("Borrado pregunta " + id);
		
	}	
	
}
