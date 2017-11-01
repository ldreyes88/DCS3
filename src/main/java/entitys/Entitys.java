/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lreyes
 */
@javax.persistence.Entity
@Table(name = "entity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entity.findAll", query = "SELECT e FROM Entitys e"),
    @NamedQuery(name = "Entity.findByIdEntity", query = "SELECT e FROM Entitys e WHERE e.idEntity = :idEntity"),
    @NamedQuery(name = "Entity.findByNameEntity", query = "SELECT e FROM Entitys e WHERE e.nameEntity = :nameEntity"),
    @NamedQuery(name = "Entity.findByNitEntity", query = "SELECT e FROM Entitys e WHERE e.nitEntity = :nitEntity"),
    @NamedQuery(name = "Entity.findByNumberSuscriptorEntity", query = "SELECT e FROM Entitys e WHERE e.numberSuscriptorEntity = :numberSuscriptorEntity"),
    @NamedQuery(name = "Entity.findByDescripcionEntity", query = "SELECT e FROM Entitys e WHERE e.descripcionEntity = :descripcionEntity"),
    @NamedQuery(name = "Entity.findByIdUser", query = "SELECT e FROM Entitys e WHERE e.idUser = :idUser")})
public class Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ENTITY")
    private Long idEntity;
    @Size(max = 50)
    @Column(name = "NAME_ENTITY")
    private String nameEntity;
    @Size(max = 50)
    @Column(name = "NIT_ENTITY")
    private String nitEntity;
    @Column(name = "NUMBER_SUSCRIPTOR_ENTITY")
    private BigInteger numberSuscriptorEntity;
    @Size(max = 100)
    @Column(name = "DESCRIPCION_ENTITY")
    private String descripcionEntity;
    @Column(name = "ID_USER")
    private BigInteger idUser;
    @OneToMany(mappedBy = "idEntity", fetch = FetchType.EAGER)
    private List<Strategy> strategyList;

    public Entitys() {
    }

    public Entitys(Long idEntity) {
        this.idEntity = idEntity;
    }

    public Long getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(Long idEntity) {
        this.idEntity = idEntity;
    }

    public String getNameEntity() {
        return nameEntity;
    }

    public void setNameEntity(String nameEntity) {
        this.nameEntity = nameEntity;
    }

    public String getNitEntity() {
        return nitEntity;
    }

    public void setNitEntity(String nitEntity) {
        this.nitEntity = nitEntity;
    }

    public BigInteger getNumberSuscriptorEntity() {
        return numberSuscriptorEntity;
    }

    public void setNumberSuscriptorEntity(BigInteger numberSuscriptorEntity) {
        this.numberSuscriptorEntity = numberSuscriptorEntity;
    }

    public String getDescripcionEntity() {
        return descripcionEntity;
    }

    public void setDescripcionEntity(String descripcionEntity) {
        this.descripcionEntity = descripcionEntity;
    }

    public BigInteger getIdUser() {
        return idUser;
    }

    public void setIdUser(BigInteger idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public List<Strategy> getStrategyList() {
        return strategyList;
    }

    public void setStrategyList(List<Strategy> strategyList) {
        this.strategyList = strategyList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntity != null ? idEntity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entitys)) {
            return false;
        }
        Entitys other = (Entitys) object;
        if ((this.idEntity == null && other.idEntity != null) || (this.idEntity != null && !this.idEntity.equals(other.idEntity))) {
            return false;
        }
        return true;
    }

   @Override
    public String toString() {
        return nameEntity;
    }
    
}
