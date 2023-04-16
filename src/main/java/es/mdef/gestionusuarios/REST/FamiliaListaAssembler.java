package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.FamiliaImpl;
import es.mdef.gestionusuarios.entidades.Usuario;

@Component
public class FamiliaListaAssembler implements RepresentationModelAssembler<FamiliaImpl, FamiliaListaModel>{

	@Override
	public FamiliaListaModel toModel(FamiliaImpl entity) {
		FamiliaListaModel model = new FamiliaListaModel();
		model.setEnunciado(entity.getEnunciado());
		model.setTamanio(entity.getTamanio());
		model.add(
				linkTo(methodOn(FamiliaController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public CollectionModel<FamiliaListaModel> toCollection(List<FamiliaImpl> lista) {
		CollectionModel<FamiliaListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
				linkTo(methodOn(UsuarioController.class).all()).withRel("familias")
				);
		return collection;
	}

}
