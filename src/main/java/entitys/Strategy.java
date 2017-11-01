/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author lreyes
 */
@Entity
@Table(name = "strategy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Strategy.findAll", query = "SELECT s FROM Strategy s"),
    @NamedQuery(name = "Strategy.findByIdStrategy", query = "SELECT s FROM Strategy s WHERE s.idStrategy = :idStrategy"),
    @NamedQuery(name = "Strategy.findByNameStrategy", query = "SELECT s FROM Strategy s WHERE s.nameStrategy = :nameStrategy"),
    @NamedQuery(name = "Strategy.findByCodigoStrategy", query = "SELECT s FROM Strategy s WHERE s.codigoStrategy = :codigoStrategy"),
    @NamedQuery(name = "Strategy.findByDescripcionStrategy", query = "SELECT s FROM Strategy s WHERE s.descripcionStrategy = :descripcionStrategy"),
    @NamedQuery(name = "Strategy.findByActiveStrategy", query = "SELECT s FROM Strategy s WHERE s.activeStrategy = :activeStrategy")})
public class Strategy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_STRATEGY")
    private Long idStrategy;
    @Size(max = 50)
    @Column(name = "NAME_STRATEGY")
    private String nameStrategy;
    @Column(name = "CODIGO_STRATEGY")
    private BigInteger codigoStrategy;
    @Size(max = 100)
    @Column(name = "DESCRIPCION_STRATEGY")
    private String descripcionStrategy;
    @Column(name = "ACTIVE_STRATEGY")
    private Boolean activeStrategy;
    
    //@JoinColumn(name = "ID_ENTITY", referencedColumnName = "ID_ENTITY")
    //@ManyToOne(fetch = FetchType.EAGER)
    //private Entitys.Entity idEntity;
    //@Column(name = "ID_ENTITY")
    //private Long idEntity;
    @JoinColumn(name = "ID_ENTITY", referencedColumnName = "ID_ENTITY")
    @ManyToOne(fetch = FetchType.EAGER)
    private Entitys idEntity;

    public Strategy() {
    }

    public Strategy(Long idStrategy) {
        this.idStrategy = idStrategy;
    }

    public Long getIdStrategy() {
        return idStrategy;
    }

    public void setIdStrategy(Long idStrategy) {
        this.idStrategy = idStrategy;
    }

    public String getNameStrategy() {
        return nameStrategy;
    }

    public void setNameStrategy(String nameStrategy) {
        this.nameStrategy = nameStrategy;
    }

    public BigInteger getCodigoStrategy() {
        return codigoStrategy;
    }

    public void setCodigoStrategy(BigInteger codigoStrategy) {
        this.codigoStrategy = codigoStrategy;
    }

    public String getDescripcionStrategy() {
        return descripcionStrategy;
    }

    public void setDescripcionStrategy(String descripcionStrategy) {
        this.descripcionStrategy = descripcionStrategy;
    }

    public Boolean getActiveStrategy() {
        return activeStrategy;
    }

    public void setActiveStrategy(Boolean activeStrategy) {
        this.activeStrategy = activeStrategy;
    }

     public Entitys getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(Entitys idEntity) {
        this.idEntity = idEntity;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStrategy != null ? idStrategy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Strategy)) {
            return false;
        }
        Strategy other = (Strategy) object;
        if ((this.idStrategy == null && other.idStrategy != null) || (this.idStrategy != null && !this.idStrategy.equals(other.idStrategy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Strategy[ idStrategy=" + idStrategy + " ]";
    }
    
}
