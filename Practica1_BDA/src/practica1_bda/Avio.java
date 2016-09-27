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
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="matricula")
    private String matricula;
    
    @ManyToOne
    @JoinColumn(name="id_model")
    private ModelAvio modelAvio; // Relacio many-to-1
    
    public Avio() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Avio(Integer id, String matricula, ModelAvio modelAvio) {
        this.id = id;
        this.matricula = matricula;
        this.modelAvio = modelAvio;
    }

    public Avio(String matricula, ModelAvio modelAvio) {
        this.matricula = matricula;
        this.modelAvio = modelAvio;
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

    public ModelAvio getModelAvio() {
        return modelAvio;
    }

    public void setModelAvio(ModelAvio modelAvio) {
        this.modelAvio = modelAvio;
    }

    @Override
    public String toString() {
        return "Avio{" + "id=" + id + ", matricula=" + matricula + ", modelAvio=" + modelAvio + '}';
    }

}