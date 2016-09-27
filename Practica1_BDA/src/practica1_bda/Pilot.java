/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_bda;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Karen i Judit
 */
@Entity
@Table(name="PILOT")
public class Pilot implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nom")
    private String nom;
    
    @Column(name="cognom")
    private String cognom;
     
    @Column(name="hores_vol")
    private Integer hores_vol;
    
    @ManyToOne
    @JoinColumn(name="aeroport")
    private Aeroport aeroport; // Relacio many-to-one

    @ManyToMany
    @JoinColumn(name="models")
    private Set<ModelAvio> models; // Relacio n-n
    
    public Pilot() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Pilot(Integer id, String nom, String cognom, Integer hores_vol, Aeroport id_aeroport) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.hores_vol = hores_vol;
        this.aeroport = id_aeroport;
        this.models = new HashSet<ModelAvio>();
    }
    
    public Pilot(String nom, String cognom, Integer hores_vol, Aeroport id_aeroport) {
        this.nom = nom;
        this.cognom = cognom;
        this.hores_vol = hores_vol;
        this.aeroport = id_aeroport;
        this.models = new HashSet<ModelAvio>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public Integer getHores_vol() {
        return hores_vol;
    }

    public void setHores_vol(Integer hores_vol) {
        this.hores_vol = hores_vol;
    }

    public Aeroport getAeroport() {
        return aeroport;
    }

    public void setAeroport(Aeroport aeroport) {
        this.aeroport = aeroport;
    }

    public Set<ModelAvio> getModels() {
        return models;
    }

    public void setModels(Set<ModelAvio> models) {
        this.models = models;
    }
    
    public void addModel(ModelAvio a){
        System.out.println("WTF");
        this.models.add(a);
    }
    
    public Set<String> getModelsNames() {
        Set<String> noms = new HashSet<String>();
        for (ModelAvio model: this.models){
            noms.add(model.getNom());
        }
        return noms;
    }

    @Override
    public String toString() {
        return "Pilot{" + "id=" + id + ", nom=" + nom + ", cognom=" + cognom + ", hores_vol=" + hores_vol + ", aeroport=" + aeroport + '}';
        // MODELS AVIO ??
    }

}
