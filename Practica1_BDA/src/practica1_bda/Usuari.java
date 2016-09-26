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
@Table(name="USUARI")
public class Usuari implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nom_usuari")
    private String nom_usuari;
    
    @Column(name="password")
    private String password;
    
    public Usuari() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Usuari(Integer id, String nom_usuari, String password) {
        super();
        this.id = id;
        this.nom_usuari = nom_usuari;
        this.password = password;
    }
    
    public Usuari(String nom_usuari, String password) {
        super();
        this.nom_usuari = nom_usuari;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom_usuari() {
        return nom_usuari;
    }

    public void setNom_usuari(String nom_usuari) {
        this.nom_usuari = nom_usuari;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
