/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.MasterActionEJBLocal;
import entitys.MasterActions;
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
public class MastersActionsFacade extends AbstractFacade<MasterActions> implements MasterActionEJBLocal {

    /**
     * Atributo entityManager : EntityManager
     */
    @PersistenceContext(unitName = "dcs3")
    private EntityManager entityManager;

    /**
     * Atributo log : Logger
     */
    private static final Logger log = Logger.getLogger(MasterActions.class.getName());

    /**
     * Metodo constructor de la clase UserFacade
     */
    public MastersActionsFacade() {
        super(MasterActions.class);
    }

    /**
     * Postconstructor de la clase UserFacade
     */
    @PostConstruct
    public void init() {
    }

    /**
     * Get Entity Manager
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metodo encargado de consultar todas las Master Actions
     *
     * @return List "MasterActions"
     */
    @Override
    public List<MasterActions> consultarMasterActions() {
        List<MasterActions> result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT ma FROM MasterActions ma ORDER BY ma.idMasterActions DESC");
            if (query.getResultList().isEmpty()) {
                return null;
            } else {
                result = query.getResultList();
            }
        } catch (Exception ex) {
            System.out.print("Error consultando todas las masterAcciones" + ex);
        }
        return result;
    }
    
    /**
     * Medoto que consulta un proceso por ID
     * @param id
     * @return MasterActions
     */
    @Override
    public MasterActions consultarMasterActById(Long id) {
    	MasterActions result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT ma FROM MasterActions ma WHERE ma.idMasterActions =:id");
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
                result = (MasterActions) query.getSingleResult();
            }
            return result;
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Metodo para crear un proceso
     * @param masterActions
     */
    @Override
    public void CreateProcess(MasterActions masterActions) {
        try {
            create(masterActions);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al crear Proceso");
        }
    }

    /**
     * Metodo para editar un Proceso
     *
     * @param masterActions
     */
    @Override
    public void UpdateProcess(MasterActions masterActions) {
        try {
            edit(masterActions);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al editar el proceso");
        }
    }

    /**
     * Metodo para eliminar Proceso
     *
     * @param masterActions
     */
    @Override
    public void DeleteProcess(MasterActions masterActions) {
        try {
            remove(masterActions);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al eliminar el proceso");
        }
    }
}
