package com.ub.edu.bda;

import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name="CATALOGO")
public class Catalogo {
    @Id @GeneratedValue
    @Column(name="id")
    private Integer id ;

    @Column(name="nombre")
    private String nombre ;

    @Column(name="descripcion")
    private String descripcion ;
    
    //afegim l'objecte Articulo amb cardinalitat N
    @Column(name="articulos")
    @OneToMany(mappedBy = "catalogo")
    private Set<Articulo> articulos;

    //afegim l'objecte Catalogo amb cardinalitat N
    @Column(name="tiendas")
    @ManyToMany(mappedBy = "catalogos")
    private Set<Tienda> tiendas;



public Catalogo() {
	super();

}

public Catalogo(String nombre, String descripcion) {
	super();
	this.nombre = nombre;
	this.descripcion = descripcion;
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

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

//NOUS GETTERS I SETTERS
public Set<Articulo> getArticulos() {
	return articulos;
}

public void setArticulos(Set<Articulo> articulos) {
	this.articulos = articulos;
}

//NOUS GETTERS I SETTERS TIENDAS
public Set<Tienda> getTiendas() {
	return tiendas;
}

public void setTiendas(Set<Tienda> tiendas) {
	this.tiendas = tiendas;
}

}
