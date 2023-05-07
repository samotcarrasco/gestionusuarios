package es.mdef.gestionusuarios.entidades;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="MATERIALES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name="inventariable", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("null")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Material{
	private static final long serrialVersionUID = 1L;
	public static enum TipoMaterial {
		Inventariable,
	    noInventariable	
	}
	public static enum EstadoMaterial {
		disponible,
	    adquirido,
	    entregado
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="el nombre es obligatorio")
	private String nombre;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dptoO_id")
	@NotNull(message="El departamento es obligatorio")
	private Departamento dptoOferta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dptoA_id", nullable = true)
	private Departamento dptoAdquisicion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categoria_id")
	@NotNull(message="La categoria es obligatoria")
	private Categoria categoria;
	
	private String descripcion;
	private String dimensiones;
	private String peso;
	private EstadoMaterial estado;
	private LocalDate fechaAdquisicion;
	private LocalDate fechaOferta;
	private String imagen;
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

	public Departamento getDeptoOferta() {
		return dptoOferta;
	}
	public void setDeptoOferta(Departamento deptoOferta) {
		this.dptoOferta = deptoOferta;
	}

	public Departamento getDptoAdquisicion() {
		return dptoAdquisicion;
	}
	public void setDptoAdquisicion(Departamento dptoAdquisicion) {
		this.dptoAdquisicion = dptoAdquisicion;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDimensiones() {
		return dimensiones;
	}
	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}

	public EstadoMaterial getEstado() {
		return estado;
	}
	public void setEstado(EstadoMaterial estado) {
		this.estado = estado;
	}
	
	public TipoMaterial getTipoMaterial() {
		return null;
	}

	public LocalDate getFechaAdquisicion() {
		return fechaAdquisicion;
	}
	public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public LocalDate getFechaOferta() {
		return fechaOferta;
	}
	public void setFechaOferta(LocalDate fechaOferta) {
		this.fechaOferta = fechaOferta;
	}

	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
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