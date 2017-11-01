/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.RolUserEJBLocal;
import entitys.RolUser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luis Daniel
 */
@Stateless
public class RolUsersFacade extends AbstractFacade<RolUser> implements RolUserEJBLocal {

    /**
     * Atributo entityManager : EntityManager
     */
    @PersistenceContext(unitName = "dcs3")
    private EntityManager entityManager;

    /**
     * Atributo log : Logger
     */
    private static final Logger log = Logger.getLogger(RolUser.class.getName());

    /**
     * Metodo constructor de la clase UserFacade
     */
    public RolUsersFacade() {
        super(RolUser.class);
    }

    /**
     * Postconstructor de la clase UserFacade
     */
    @PostConstruct
    public void init() {
    }

    /**
     * @see AbstractFacade#getEntityManager()
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metodo encargado de consultar los roles existentes
     *
     * @return
     */
    @Override
    public List<RolUser> consultarTodosRoles() {
        List<RolUser> result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT ru FROM RolUser ru ORDER BY ru.nameRolUser DESC");

            if (query.getResultList().isEmpty()) {
                return null;
            } else {
                result = query.getResultList();
            }

            return result;
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * Metodo encargado de consultar un ROL por ID
     * @param id
     * @return RolUser
     */
    @Override
    public RolUser consultarRolById(Long id) {
        RolUser result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT ro FROM RolUser ro WHERE ro.idRolUser =:id");
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
                result = (RolUser) query.getSingleResult();
            }
            return result;
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Metodo para crear un Rol
     * @param rol
     */
    @Override
    public void CreateRol(RolUser rol) {
        try {
            create(rol);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al crear Rol");
        }
    }

    /**
     * Metodo encargado de actualizar un rol
     * @param rol
     */
    @Override
    public void UpdateRol(RolUser rol) {
        try {
            edit(rol);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Metodo para eliminar un rol
     * @param rol
     */
    @Override
    public void DeleteRol(RolUser rol) {
        try {
            remove(rol);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al eliminar un rol");
        }
    }

}
