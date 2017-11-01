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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByNameUser", query = "SELECT u FROM User u WHERE u.nameUser = :nameUser"),
    @NamedQuery(name = "User.findByLastNameUser", query = "SELECT u FROM User u WHERE u.lastNameUser = :lastNameUser"),
    @NamedQuery(name = "User.findByIdentificationUser", query = "SELECT u FROM User u WHERE u.identificationUser = :identificationUser"),
    @NamedQuery(name = "User.findByEmailUser", query = "SELECT u FROM User u WHERE u.emailUser = :emailUser"),
    @NamedQuery(name = "User.findByCelularUser", query = "SELECT u FROM User u WHERE u.celularUser = :celularUser"),
    @NamedQuery(name = "User.findByActiveUser", query = "SELECT u FROM User u WHERE u.activeUser = :activeUser"),
    @NamedQuery(name = "User.findByPasswordUser", query = "SELECT u FROM User u WHERE u.passwordUser = :passwordUser")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private Long idUser;
    @Size(max = 100)
    @Column(name = "NAME_USER")
    private String nameUser;
    @Size(max = 100)
    @Column(name = "LAST_NAME_USER")
    private String lastNameUser;
    @Size(max = 100)
    @Column(name = "IDENTIFICATION_USER")
    private String identificationUser;
    @Size(max = 100)
    @Column(name = "EMAIL_USER")
    private String emailUser;
    @Size(max = 50)
    @Column(name = "CELULAR_USER")
    private String celularUser;
    @Column(name = "ACTIVE_USER")
    private Boolean activeUser;
    @Size(max = 100)
    @Column(name = "PASSWORD_USER")
    private String passwordUser;
    @JoinColumn(name = "ID_ROL_USER", referencedColumnName = "ID_ROL_USER")
    @ManyToOne(fetch = FetchType.EAGER)
    private RolUser idRolUser;
    //@OneToMany(mappedBy = "idUser", fetch = FetchType.EAGER)
    //private List<User> userMasterActions;

    public User() {
    }

    public User(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getIdentificationUser() {
        return identificationUser;
    }

    public void setIdentificationUser(String identificationUser) {
        this.identificationUser = identificationUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getCelularUser() {
        return celularUser;
    }

    public void setCelularUser(String celularUser) {
        this.celularUser = celularUser;
    }

    public Boolean getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(Boolean activeUser) {
        this.activeUser = activeUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public RolUser getIdRolUser() {
        return idRolUser;
    }

    public void setIdRolUser(RolUser idRolUser) {
        this.idRolUser = idRolUser;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameUser + " " +lastNameUser;
    }
    
}
