/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entitys.User;
import ejb.UserEJBLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luis D. Reyes
 * @version 1.0
 *
 */
@Stateless
public class UsersFacade extends AbstractFacade<User> implements UserEJBLocal {

    /**
     * Atributo entityManager : EntityManager
     */
    @PersistenceContext(unitName = "dcs3")
    private EntityManager entityManager;

    /**
     * Atributo log : Logger
     */
    private static final Logger log = Logger.getLogger(User.class.getName());

    /**
     * Metodo constructor de la clase UserFacade
     */
    public UsersFacade() {
        super(User.class);
    }

    /**
     * Postconstructor de la clase UsersFacade
     */
    @PostConstruct
    public void init() {
    }

    /**
     * @return @see AbstractFacade#getEntityManager()
     */
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metodo encargado de consultar usuario recibido como parametro
     *
     * @param pw
     * @return Object OrdenPago
     */
    @Override
    public User consultarLogin(String identificacion, String pw) {
        User result = null;
        try {
            boolean active = true;
            Query query = getEntityManager().createQuery("SELECT us FROM User us WHERE us.identificationUser = :identification AND us.passwordUser = :password AND us.activeUser =:active");
            query.setParameter("identification", identificacion).setParameter("password", pw).setParameter("active", active);

            if (query.getResultList().isEmpty()) {
                return null;
            } else {
                result = (User) query.getSingleResult();
            }

            return result;
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public List<User> consultarUsuarios() {
        List<User> result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT us FROM User us ");
            if (query.getResultList().isEmpty()) {
                return null;
            } else {
                result = query.getResultList();
            }
            return result;
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return result;
    }

    /**
     * Metodo encargado de consultar un usuario por ID
     *
     * @param id
     * @return User
     */
    @Override
    public User consultarUserById(Long id) {
        User result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT us FROM User us WHERE us.idUser =:id");
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
                result = (User) query.getSingleResult();
            }
            return result;
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Metodo para crear un Usuario
     *
     * @param user
     */
    @Override
    public void CreateUser(User user) {
        try {
            create(user);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al crear Entidad");
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            edit(user);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Metodo para eliminar una User
     * @param user
     */
    @Override
    public void DeleteUser(User user){
        try {
            remove(user);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al eliminar la user");
        }
    }

}
