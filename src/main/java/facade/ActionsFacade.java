/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.ActionEJBLocal;
import entitys.Actions;
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
 * @author lreyes
 */
@Stateless
public class ActionsFacade extends AbstractFacade<Actions> implements ActionEJBLocal {

    /**
     * Atributo entityManager : EntityManager
     */
    @PersistenceContext(unitName = "dcs3")
    private EntityManager entityManager;

    /**
     * Atributo log : Logger
     */
    private static final Logger log = Logger.getLogger(Actions.class.getName());

    /**
     * Metodo constructor de la clase UserFacade
     */
    public ActionsFacade() {
        super(Actions.class);
    }

    /**
     * Postconstructor de la clase UserFacade
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
     * Metodo que consulta todas la acciones
     *
     * @return List <Actions>
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Actions> consultarActions() {
        List<Actions> result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT ac FROM Actions ac");
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
     * Metodo encargado de consultar una ACCION por ID
     *
     * @param id
     * @return Actions
     */
    @Override
    public Actions consultarActionsById(Long id) {
        Actions result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT ac FROM Actions ac WHERE ac.idActions =:id");
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
                result = (Actions) query.getSingleResult();
            }
            return result;
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Metodo para crear una accion
     *
     * @param action
     */
    @Override
    public void CreateAction(Actions action) {
        try {
            create(action);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al crear accion");
        }
    }

    /**
     * Metodo encargado de actualizar una accion
     *
     * @param action
     */
    @Override
    public void UpdateAction(Actions action) {
        try {
            edit(action);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Metodo para eliminar una accion
     *
     * @param action
     */
    @Override
    public void DeleteAction(Actions action) {
        try {
            remove(action);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al eliminar una accion");
        }
    }

}
