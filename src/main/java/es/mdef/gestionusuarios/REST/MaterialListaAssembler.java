package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Categoria;
import es.mdef.gestionusuarios.entidades.Material;


@Component
public class MaterialListaAssembler  implements RepresentationModelAssembler<Material, MaterialListaModel>{

	@Override
	public MaterialListaModel toModel(Material entity) {
		MaterialListaModel model = new MaterialListaModel();
		
		model.setId(entity.getId());
		model.setEstado(entity.getEstado());
		model.setNombre(entity.getNombre());
		model.setDescripcion(entity.getDescripcion());
		model.setCantidad(entity.getCantidad());
		
		//por compodidad para el front, estos tres los devolvemoscomo String
		model.setCategoriaN(entity.getCategoria().getCategoria());
		model.setDptoOfertaN(entity.getDeptoOferta().getNombre());
		
		String nombreUnidadOferta = entity.getDptoAdquisicion() != null ? entity.getDptoAdquisicion().getNombre() : "-";
		model.setDptoAdquisicionN(nombreUnidadOferta);
	
		
//		model.add(
//				linkTo(methodOn(MaterialControler.class).one(entity.getId())).withSelfRel()
//				);
		return model;
	}
	
	public CollectionModel<MaterialListaModel> toCollection(List<Material> lista) {
		CollectionModel<MaterialListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
//		collection.add(
//				linkTo(methodOn(MaterialControler.class).all()).withRel("materiales")
//				);		
		return collection;
	}
}
