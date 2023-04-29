package es.mdef.gestionusuarios.REST;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Categoria;
import es.mdef.gestionusuarios.entidades.Departamento;
import es.mdef.gestionusuarios.entidades.Material;
import es.mdef.gestionusuarios.entidades.Categoria.TipoGrupo;
import es.mdef.gestionusuarios.entidades.Material.EstadoMaterial;
import es.mdef.gestionusuarios.entidades.Material.TipoMaterial;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Relation(collectionRelation="categorias")
public class MaterialListaModel extends RepresentationModel<MaterialListaModel>{
	
	
	private Long id;
	private String nombre;
	//estos 3 se rellenan en el Assembler
	//en el modeloLista, no devolemoos las entidades, sino los nombres
	private String dptoOfertaN;
	private String dptoAdquisicionN;
	private String categoriaN;
	
	private String descripcion;
	private EstadoMaterial estado;
	private int milis;
	private int cantidad;
     	 
    	 

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDptoOfertaN() {
		return dptoOfertaN;
	}
	public void setDptoOfertaN(String dptoOferta) {
		this.dptoOfertaN = dptoOferta;
	}

	public String getDptoAdquisicionN() {
		return dptoAdquisicionN;
	}
	public void setDptoAdquisicionN(String dptoAdquisicion) {
		this.dptoAdquisicionN = dptoAdquisicion;
	}

	public String getCategoriaN() {
		return categoriaN;
	}
	public void setCategoriaN(String categoria) {
		this.categoriaN = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoMaterial getEstado() {
		return estado;
	}
	public void setEstado(EstadoMaterial estado) {
		this.estado = estado;
	}


	public int getMilis() {
		return milis;
	}
	public void setMilis(int milis) {
		this.milis = milis;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public String toString() {
		return "MATERIAL [Nombre=" + getNombre() +"]";
	}
	
	
}
