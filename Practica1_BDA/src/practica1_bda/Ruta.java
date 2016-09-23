/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_bda;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.*;

/**
 *
 * @author Karen i Judit
 */
@Entity
@Table(name="RUTA")
public class Ruta implements Serializable {
    @Id @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="dia")
    private String dia;
    
    @Column(name="hora")
    private Time hora;
    
    @Column(name="id_aero_origen")
    private Integer id_aero_origen; // Relacio n-1
    
    @Column(name="id_aero_desti")
    private Integer id_aero_desti; // Relacio n-1
    
    @Column(name="id_model")
    private Integer id_model; // Relacio n-1
    
    @Column(name="data")
    private Date data;
     
    @Column(name="id_avio")
    private Integer id_avio; // Relacio n-1
     
    @Column(name="id_pilot")
    private Integer id_pilot; // Relacio n-1
     
    @Column(name="incidencies")
    private String incidencies;
     
    public Ruta() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Ruta(Integer id, String dia, Time hora, Integer id_aero_origen,
            Integer id_aero_desti, Integer id_model, Date data, Integer id_avio,
            Integer id_pilot, String incidencies) {
        super();
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.id_aero_origen = id_aero_origen;
        this.id_aero_desti = id_aero_desti;
        this.id_model = id_model;
        this.data = data;
        this.id_avio = id_avio;
        this.id_pilot = id_pilot;
        this.incidencies = incidencies;
    }
    
    public Ruta(String dia, Time hora, Integer id_aero_origen,
            Integer id_aero_desti, Integer id_model, Date data, Integer id_avio,
            Integer id_pilot, String incidencies) {
        super();
        this.dia = dia;
        this.hora = hora;
        this.id_aero_origen = id_aero_origen;
        this.id_aero_desti = id_aero_desti;
        this.id_model = id_model;
        this.data = data;
        this.id_avio = id_avio;
        this.id_pilot = id_pilot;
        this.incidencies = incidencies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Integer getId_aero_origen() {
        return id_aero_origen;
    }

    public void setId_aero_origen(Integer id_aero_origen) {
        this.id_aero_origen = id_aero_origen;
    }

    public Integer getId_aero_desti() {
        return id_aero_desti;
    }

    public void setId_aero_desti(Integer id_aero_desti) {
        this.id_aero_desti = id_aero_desti;
    }

    public Integer getId_model() {
        return id_model;
    }

    public void setId_model(Integer id_model) {
        this.id_model = id_model;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getId_avio() {
        return id_avio;
    }

    public void setId_avio(Integer id_avio) {
        this.id_avio = id_avio;
    }

    public Integer getId_pilot() {
        return id_pilot;
    }

    public void setId_pilot(Integer id_pilot) {
        this.id_pilot = id_pilot;
    }

    public String getIncidencies() {
        return incidencies;
    }

    public void setIncidencies(String incidencies) {
        this.incidencies = incidencies;
    }

}
