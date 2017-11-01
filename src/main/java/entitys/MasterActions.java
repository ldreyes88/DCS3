/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lreyes
 */
@Entity
@Table(name = "master_actions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterActions.findAll", query = "SELECT m FROM MasterActions m"),
    @NamedQuery(name = "MasterActions.findByIdMasterActions", query = "SELECT m FROM MasterActions m WHERE m.idMasterActions = :idMasterActions"),
    @NamedQuery(name = "MasterActions.findByDescripcionMasterActions", query = "SELECT m FROM MasterActions m WHERE m.descripcionMasterActions = :descripcionMasterActions"),
    @NamedQuery(name = "MasterActions.findByUserActions", query = "SELECT m FROM MasterActions m WHERE m.idUser = :userActions"),
    @NamedQuery(name = "MasterActions.findByTimeActions", query = "SELECT m FROM MasterActions m WHERE m.timeActions = :timeActions"),
    @NamedQuery(name = "MasterActions.findByStateActions", query = "SELECT m FROM MasterActions m WHERE m.stateActions = :stateActions")})
public class MasterActions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MASTER_ACTIONS")
    private Long idMasterActions;
    @Size(max = 200)
    @Column(name = "DESCRIPCION_MASTER_ACTIONS")
    private String descripcionMasterActions;
    @Column(name = "TIME_ACTIONS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeActions;
    @Column(name = "STATE_ACTIONS")
    private Integer stateActions;
    @Column(name = "FINISH_ACTIONS")
    private Boolean finishActions;
    @JoinColumn(name = "ID_STRATEGY", referencedColumnName = "ID_STRATEGY")
    @ManyToOne(fetch = FetchType.EAGER)
    private Strategy idStrategy;
    @JoinColumn(name = "ID_ACTIONS", referencedColumnName = "ID_ACTIONS")
    @ManyToOne(fetch = FetchType.EAGER)
    private Actions idActions;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(fetch = FetchType.EAGER)
    private User idUser;

    public MasterActions() {
    }

    public MasterActions(Long idMasterActions) {
        this.idMasterActions = idMasterActions;
    }

    public Long getIdMasterActions() {
        return idMasterActions;
    }

    public void setIdMasterActions(Long idMasterActions) {
        this.idMasterActions = idMasterActions;
    }

    public String getDescripcionMasterActions() {
        return descripcionMasterActions;
    }

    public void setDescripcionMasterActions(String descripcionMasterActions) {
        this.descripcionMasterActions = descripcionMasterActions;
    }

    public User getUserActions() {
        return idUser;
    }

    public void setUserActions(User userActions) {
        this.idUser = userActions;
    }

    public Date getTimeActions() {
        return timeActions;
    }

    public void setTimeActions(Date timeActions) {
        this.timeActions = timeActions;
    }

    public Integer getStateActions() {
        return stateActions;
    }

    public void setStateActions(Integer stateActions) {
        this.stateActions = stateActions;
    }
    
    public Boolean getFinishActions() {
        return finishActions;
    }

    public void setFinishActions(Boolean finishActions) {
        this.finishActions = finishActions;
    }

    public Strategy getIdStrategy() {
        return idStrategy;
    }

    public void setIdStrategy(Strategy idStrategy) {
        this.idStrategy = idStrategy;
    }

    public Actions getIdActions() {
        return idActions;
    }

    public void setIdActions(Actions idActions) {
        this.idActions = idActions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMasterActions != null ? idMasterActions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterActions)) {
            return false;
        }
        MasterActions other = (MasterActions) object;
        if ((this.idMasterActions == null && other.idMasterActions != null) || (this.idMasterActions != null && !this.idMasterActions.equals(other.idMasterActions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.MasterActions[ idMasterActions=" + idMasterActions + " ]";
    }
    
}
