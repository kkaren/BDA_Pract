/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_bda;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Classe de l'entitat model avio
 * @author Karen i Judit
 */
@Entity
@Table(name="MODEL_AVIO")
public class ModelAvio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nom")
    private String nom;
    
    @Column(name="descripcio")
    private String descripcio;
    
    @Column(name="places")
    private Integer places;
    
    @Column(name="pes")
    private double pes;
    
    @ManyToMany
    @JoinColumn(name="pilots")
    private Set<Pilot> pilots;
    
    @OneToMany(mappedBy="modelAvio", cascade=CascadeType.REMOVE)
    private Set<Avio> avions;
    
    @OneToMany(mappedBy="model_avio", cascade=CascadeType.REMOVE)
    private Set<Ruta> rutes;
    
    /**
     * Constructor buit
     */
    public ModelAvio() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor amb tots els parametres
     * @param id
     * @param nom
     * @param descripcio
     * @param places
     * @param pes 
     */
    public ModelAvio(Integer id, String nom, String descripcio, Integer places, 
            double pes) { //, Set<Pilot> pilots) {
        super();
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.places = places;
        this.pes = pes;
        //this.pilots = pilots;
    }
    
    /**
     * Constructor sense id, es genera automaticament.
     * @param nom
     * @param descripcio
     * @param places
     * @param pes 
     */
    public ModelAvio(String nom, String descripcio, Integer places, 
            double pes) { //, Set<Pilot> pilots) {
        super();
        this.nom = nom;
        this.descripcio = descripcio;
        this.places = places;
        this.pes = pes;
        //this.pilots = pilots;
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

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public double getPes() {
        return pes;
    }

    public void setPes(double pes) {
        this.pes = pes;
    }

    public Set<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(Set<Pilot> pilots) {
        this.pilots = pilots;
    }

    
    @Override
    public String toString() {
        //return "ModelAvio{" + "id=" + id + ", nom=" + nom + ", descripcio=" + descripcio + ", places=" + places + ", pes=" + pes + '}';
        // PILOTS ??
        return nom + ", "+ descripcio +", "+places+", "+pes;
    }
}
