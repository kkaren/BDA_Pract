/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_bda;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Classe de l'entitat pilot
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
    
    @OneToMany(mappedBy = "pilot", cascade=CascadeType.REMOVE)
    private Set<Ruta> rutes;
    
    /**
     * Constructor buit
     */
    public Pilot() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor amb tots els parametres
     * @param id
     * @param nom
     * @param cognom
     * @param hores_vol
     * @param id_aeroport 
     */
    public Pilot(Integer id, String nom, String cognom, Integer hores_vol, Aeroport id_aeroport) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.hores_vol = hores_vol;
        this.aeroport = id_aeroport;
        this.models = new HashSet<ModelAvio>();
    }
    
    /**
     * Constructor sense id, es genera automaticament.
     * @param nom
     * @param cognom
     * @param hores_vol
     * @param id_aeroport 
     */
    public Pilot(String nom, String cognom, Integer hores_vol, Aeroport id_aeroport) {
        this.nom = nom;
        this.cognom = cognom;
        this.hores_vol = hores_vol;
        this.aeroport = id_aeroport;
        this.models = new HashSet<ModelAvio>();
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public String getCognom() {
        return cognom;
    }

    /**
     *
     * @param cognom
     */
    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    /**
     *
     * @return
     */
    public Integer getHores_vol() {
        return hores_vol;
    }

    /**
     *
     * @param hores_vol
     */
    public void setHores_vol(Integer hores_vol) {
        this.hores_vol = hores_vol;
    }

    /**
     *
     * @return
     */
    public Aeroport getAeroport() {
        return aeroport;
    }

    /**
     *
     * @param aeroport
     */
    public void setAeroport(Aeroport aeroport) {
        this.aeroport = aeroport;
    }

    /**
     *
     * @return
     */
    public Set<ModelAvio> getModels() {
        return models;
    }

    /**
     *
     * @param models
     */
    public void setModels(Set<ModelAvio> models) {
        this.models = models;
    }
    
    /**
     *
     * @param a
     */
    public void addModel(ModelAvio a){
        this.models.add(a);
    }
    
    /**
     *
     * @param a
     */
    public void deleteModel(ModelAvio a){
        this.models.remove(a);
    }    
    
    /**
     *
     * @return
     */
    public Set<String> getModelsNames() {
        Set<String> n = new HashSet<String>();
        for (ModelAvio model: this.models){
            n.add(model.getNom());
        }
        return n;
    }
    
    @Override
    public String toString() {
        //return "Pilot{" + "id=" + id + ", nom=" + nom + ", cognom=" + cognom + ", hores_vol=" + hores_vol + ", aeroport=" + aeroport + '}';
        // MODELS AVIO ??
        Set<String> noms = new HashSet<String>();
        for (ModelAvio model: this.models){
            noms.add(model.getNom());
        }
        return nom + cognom + ", "+ hores_vol +", "+aeroport.getCodi_int()+", "+noms;
    }

}
