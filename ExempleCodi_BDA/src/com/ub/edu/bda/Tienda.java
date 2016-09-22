package com.ub.edu.bda;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="TIENDA")
public class Tienda {

        @Id @GeneratedValue
        @Column(name="id")
	private Integer id;
	
        @Column(name="nombre")
        private String nombre;
        
        @Column(name="direccion")
	private String direccion;
        
        @Column(name="telefono")
	private String telefono;
	
	//afegim l'objecte catalogo amb cardinalitat N
        @Column(name="catalogos")
        @ManyToMany
	private Set<Catalogo> catalogos;
	
	public Tienda(String nombre, String direccion, String telefono) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Tienda() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
//GETTERS I SETTERS DE LA RELACIO N-N
	public Set<Catalogo> getCatalogos() {
		return catalogos;
	}

	public void setCatalogos(Set<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}
	

}
