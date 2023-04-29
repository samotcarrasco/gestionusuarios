package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Categoria;


@Component
public class CategoriaListaAssembler  implements RepresentationModelAssembler<Categoria, CategoriaListaModel>{

	@Override
	public CategoriaListaModel toModel(Categoria entity) {
		CategoriaListaModel model = new CategoriaListaModel();
		model.setCategoria(entity.getCategoria());
		model.setDescripcion(entity.getDescripcion());
		model.setGrupo(entity.getGrupo());
		model.setId(entity.getId());
		model.setMinMilis(entity.getMinMilis());
		model.setMaxMilis(entity.getMaxMilis());
		
		//devolvemos el numero de materiales que tiene la categoria
		int numMateriales = entity.getMateriales() != null ? entity.getMateriales().size() : 0;
		model.setNumMateriales(numMateriales);
				
		model.add(
				linkTo(methodOn(CategoriaController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public CollectionModel<CategoriaListaModel> toCollection(List<Categoria> lista) {
		CollectionModel<CategoriaListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
//		collection.add(
//				linkTo(methodOn(CategoriaController.class).all()).withRel("categorias")
//				);		
		return collection;
	}
}
