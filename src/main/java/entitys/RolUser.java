/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
@Table(name = "rol_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolUser.findAll", query = "SELECT r FROM RolUser r"),
    @NamedQuery(name = "RolUser.findByIdRolUser", query = "SELECT r FROM RolUser r WHERE r.idRolUser = :idRolUser"),
    @NamedQuery(name = "RolUser.findByNameRolUser", query = "SELECT r FROM RolUser r WHERE r.nameRolUser = :nameRolUser"),
    @NamedQuery(name = "RolUser.findByDescriptionUser", query = "SELECT r FROM RolUser r WHERE r.descriptionUser = :descriptionUser")})
public class RolUser implements Serializable {
    //@OneToMany(mappedBy = "idRolUser")
   // private List<ConfigRol> configRolList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ROL_USER")
    private Long idRolUser;
    @Size(max = 50)
    @Column(name = "NAME_ROL_USER")
    private String nameRolUser;
    @Size(max = 100)
    @Column(name = "DESCRIPTION_USER")
    private String descriptionUser;
    @OneToMany(mappedBy = "idRolUser", fetch = FetchType.EAGER)
    private List<User> userList;

    public RolUser() {
    }

    public RolUser(Long idRolUser) {
        this.idRolUser = idRolUser;
    }

    public Long getIdRolUser() {
        return idRolUser;
    }

    public void setIdRolUser(Long idRolUser) {
        this.idRolUser = idRolUser;
    }

    public String getNameRolUser() {
        return nameRolUser;
    }

    public void setNameRolUser(String nameRolUser) {
        this.nameRolUser = nameRolUser;
    }

    public String getDescriptionUser() {
        return descriptionUser;
    }

    public void setDescriptionUser(String descriptionUser) {
        this.descriptionUser = descriptionUser;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolUser != null ? idRolUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUser)) {
            return false;
        }
        RolUser other = (RolUser) object;
        if ((this.idRolUser == null && other.idRolUser != null) || (this.idRolUser != null && !this.idRolUser.equals(other.idRolUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameRolUser;
    }

//    @XmlTransient
//    public List<ConfigRol> getConfigRolList() {
//        return configRolList;
//    }
//
//    public void setConfigRolList(List<ConfigRol> configRolList) {
//        this.configRolList = configRolList;
//    }
    
}
