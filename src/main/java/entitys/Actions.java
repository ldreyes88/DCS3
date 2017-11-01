/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "actions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actions.findAll", query = "SELECT a FROM Actions a"),
    @NamedQuery(name = "Actions.findByIdActions", query = "SELECT a FROM Actions a WHERE a.idActions = :idActions"),
    @NamedQuery(name = "Actions.findByNameActions", query = "SELECT a FROM Actions a WHERE a.nameActions = :nameActions"),
    @NamedQuery(name = "Actions.findByDescripcionActions", query = "SELECT a FROM Actions a WHERE a.descripcionActions = :descripcionActions"),
    @NamedQuery(name = "Actions.findByActiveActions", query = "SELECT a FROM Actions a WHERE a.activeActions = :activeActions")})

public class Actions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACTIONS")
    private Long idActions;
    @Size(max = 50)
    @Column(name = "NAME_ACTIONS")
    private String nameActions;
    @Size(max = 100)
    @Column(name = "DESCRIPCION_ACTIONS")
    private String descripcionActions;
    @Column(name = "ACTIVE_ACTIONS")
    private Boolean activeActions;

    public Actions() {
    }

    public Actions(Long idActions) {
        this.idActions = idActions;
    }

    public Long getIdActions() {
        return idActions;
    }

    public void setIdActions(Long idActions) {
        this.idActions = idActions;
    }

    public String getNameActions() {
        return nameActions;
    }

    public void setNameActions(String nameActions) {
        this.nameActions = nameActions;
    }

    public String getDescripcionActions() {
        return descripcionActions;
    }

    public void setDescripcionActions(String descripcionActions) {
        this.descripcionActions = descripcionActions;
    }

    public Boolean getActiveActions() {
        return activeActions;
    }

    public void setActiveActions(Boolean activeActions) {
        this.activeActions = activeActions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActions != null ? idActions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Actions)) {
            return false;
        }
        Actions other = (Actions) object;
        if ((this.idActions == null && other.idActions != null) || (this.idActions != null && !this.idActions.equals(other.idActions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameActions;
    }
    
}
