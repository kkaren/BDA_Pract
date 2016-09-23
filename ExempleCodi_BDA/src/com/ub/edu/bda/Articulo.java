package com.ub.edu.bda;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="ARTICULO")
public class Articulo implements Serializable {
    @Id @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="description")
    private String descripcion;
    
    @Column(name="precio")
    private double precio;

// afegim l'objecte cataleg en cardinalitat 1
    @Column(name="catalogo")
    private Catalogo catalogo;


    public Articulo() {
            super();
            // TODO Auto-generated constructor stub
    }

    public Articulo(Integer id, String descripcion,
                    double precio) {
            super();
            this.id = id;
            this.descripcion = descripcion;
            this.precio = precio;
    }


    public Articulo(String descripcion, double precio) {
            super();
            this.descripcion = descripcion;
            this.precio = precio;
    }

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public String getDescripcion() {
            return descripcion;
    }

    public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
    }

    public double getPrecio() {
            return precio;
    }

    public void setPrecio(double precio) {
            this.precio = precio;
    }
    //NOUS GETTERS I SETTERS
    public Catalogo getCatalogo() {
            return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
            this.catalogo = catalogo;
    }




    }
