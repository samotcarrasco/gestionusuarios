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
public class MaterialPostModel extends RepresentationModel<MaterialPostModel>{
	
	
	private Long id;
	private String nombre;

	private Departamento dptoOferta;
	private Departamento dptoAdquisicion;
	private Categoria categoria;
	
	private String descripcion;
	private LocalDate fechaAdquisicion;
	private LocalDate fechaOferta;
	private String imagen;
	private EstadoMaterial estado;
	private int milis;
	private int cantidad;
	private String dimensiones;
	private String peso;

	private TipoMaterial tipoMaterial;
	private String noc;
	private String numeroSerie;
	private int bonificacion;
     	 
    
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Departamento getDptoOferta() {
		return dptoOferta;
	}

	public Departamento getDptoAdquisicion() {
		return dptoAdquisicion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public LocalDate getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public LocalDate getFechaOferta() {
		return fechaOferta;
	}

	public String getImagen() {
		return imagen;
	}

	public EstadoMaterial getEstado() {
		return estado;
	}

	public int getMilis() {
		return milis;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public String getPeso() {
		return peso;
	}

	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}
	
	public String getNoc() {
		return noc;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public int getBonificacion() {
		return bonificacion;
	}

	@Override
	public String toString() {
		return "MATERIAL [Nombre=" + getNombre() +"]";
	}
	
	
}
