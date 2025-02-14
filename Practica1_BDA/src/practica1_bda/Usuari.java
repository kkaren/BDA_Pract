/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_bda;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe usuari
 * @author Karen i Judit
 */
@Entity
@Table(name="USUARI")
public class Usuari implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id; 

    @Column(name="usr")
    private String nom_usuari;
    
    @Column(name="pwd")
    private String password;
    
    /**
     * Constructor buit
     */
    public Usuari() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor amb tots els paramaetres
     * @param id
     * @param nom_usuari
     * @param password 
     */
    public Usuari(Integer id, String nom_usuari, String password) {
        super();
        this.id = id;
        this.nom_usuari = nom_usuari;
        this.password = password;
    }
    
    /**
     * Constructor sense id, es genera automaticament
     * @param nom_usuari
     * @param password 
     */
    public Usuari(String nom_usuari, String password) {
        super();
        this.nom_usuari = nom_usuari;
        this.password = password;
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
    public String getNom_usuari() {
        return nom_usuari;
    }

    /**
     *
     * @param nom_usuari
     */
    public void setNom_usuari(String nom_usuari) {
        this.nom_usuari = nom_usuari;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
