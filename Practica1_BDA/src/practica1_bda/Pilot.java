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
@Table(name="PILOT")
public class Pilot implements Serializable {
    @Id @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="nom")
    private String nom;
    
    @Column(name="cognom")
    private String cognom;
     
    @Column(name="hores_vol")
    private Integer hores_vol;
    
    @ManyToOne
    private Aeroport aeroport; // Relacio many-to-one

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


}
