package es.mdef.gestionusuarios.REST;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionusuarios.entidades.Familia;
import es.mdef.gestionusuarios.entidades.Usuario;

@Relation(itemRelation="material")
public class MaterialModel extends RepresentationModel<MaterialModel>{

	 private String nombre;
	    private String descripcion;
	    private String dimensiones;
	    private String imagen;
	    private String numeroSerie;
	    private String invetariable;
	    private double peso;
	    private boolean bonificacion;
	    private int noc;
	    private LocalDate fechaAdquisicion;
	    private LocalDate fechaOferta;

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
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

	    public String getImagen() {
	        return imagen;
	    }

	    public void setImagen(String imagen) {
	        this.imagen = imagen;
	    }

	    public String getNumeroSerie() {
	        return numeroSerie;
	    }

	    public void setNumeroSerie(String numeroSerie) {
	        this.numeroSerie = numeroSerie;
	    }

	    public String getInvetariable() {
	        return invetariable;
	    }

	    public void setInvetariable(String invetariable) {
	        this.invetariable = invetariable;
	    }

	    public double getPeso() {
	        return peso;
	    }

	    public void setPeso(double peso) {
	        this.peso = peso;
	    }

	    public boolean isBonificacion() {
	        return bonificacion;
	    }

	    public void setBonificacion(boolean bonificacion) {
	        this.bonificacion = bonificacion;
	    }

	    public int getNoc() {
	        return noc;
	    }

	    public void setNoc(int noc) {
	        this.noc = noc;
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


}
