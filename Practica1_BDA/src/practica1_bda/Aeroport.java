/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_bda;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;


/**
 * Classe de l'entitat aeroport
 * @author Karen i Judit
 */
@Entity
@Table(name="AEROPORT")
public class Aeroport implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="codi_int")
    private String codi_int;
    
    @Column(name="nom")
    private String nom;
     
    @Column(name="ciutat")
    private String ciutat;
    
    @Column(name="cost_handling")
    private double cost_handling;

    @OneToMany(mappedBy = "aeroport_origen", cascade=CascadeType.REMOVE)
    private Set<Ruta> rutes_origen;
    
    @OneToMany(mappedBy="aeroport_desti", cascade=CascadeType.REMOVE)
    private Set<Ruta> rutes_desti;

    @OneToMany(mappedBy = "aeroport", cascade=CascadeType.REMOVE)
    private Set<Pilot> pilots;

    
    /**
     * Constructor buit
     */
    public Aeroport() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor amb tots els parametres
     * @param id
     * @param codi_int
     * @param nom
     * @param ciutat
     * @param cost_handling 
     */
    public Aeroport(Integer id, String codi_int, String nom, String ciutat,
        double cost_handling) {
        super();
        this.id = id;
        this.codi_int = codi_int;
        this.nom = nom;
        this.ciutat = ciutat;
        this.cost_handling = cost_handling;
    }

    /**
     * Constructor sense id, es crea automaticament
     * @param codi_int
     * @param nom
     * @param ciutat
     * @param cost_handling 
     */
    public Aeroport(String codi_int, String nom, String ciutat,
        double cost_handling) {
        super(); 
        this.codi_int = codi_int;
        this.nom = nom;
        this.ciutat = ciutat;
        this.cost_handling = cost_handling;
    }

    /**
     * get id
     * @return id
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodi_int() {
        return codi_int;
    }

    public void setCodi_int(String codi_int) {
        this.codi_int = codi_int;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public double getCost_handling() {
        return cost_handling;
    }

    public void setCost_handling(double cost_handling) {
        this.cost_handling = cost_handling;
    }

    /**
     * To string
     * @return 
     */
    @Override
    public String toString() {
        //return "Aeroport{" + "id=" + id + ", codi_int=" + codi_int + ", nom=" + nom + ", ciutat=" + ciutat + ", cost_handling=" + cost_handling + '}';
        return codi_int + ", "+ nom +", "+ciutat+", "+cost_handling;
    }

}
