/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_bda;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Karen i Judit
 */
@Entity
@Table(name="AVIO")
public class Avio implements Serializable {
    @Id @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="matricula")
    private String matricula;
    
    @Column(name="id_model")
    private Integer id_model; // Relacio many-to-1
    
    public Avio() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Avio(Integer id, String matricula, Integer id_model) {
        super();
        this.id = id;
        this.matricula = matricula;
        this.id_model = id_model;
    }
    
    public Avio(String matricula, Integer id_model) {
        super();
        this.matricula = matricula;
        this.id_model = id_model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getId_model() {
        return id_model;
    }

    public void setId_model(Integer id_model) {
        this.id_model = id_model;
    }
    
}