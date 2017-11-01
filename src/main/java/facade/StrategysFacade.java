/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import ejb.StrategyEJBLocal;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entitys.Strategy;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luis D. Reyes
 * @version 1.0
 *
 */
@Stateless
public class StrategysFacade extends AbstractFacade<Strategy> implements StrategyEJBLocal {

    /**
     * Atributo entityManager : EntityManager
     */
    @PersistenceContext(unitName = "dcs3")
    private EntityManager entityManager;

    /**
     * Atributo log : Logger
     */
    private static final Logger log = Logger.getLogger(Strategy.class.getName());

    /**
     * Metodo constructor de la clase UserFacade
     */
    public StrategysFacade() {
        super(Strategy.class);
    }

    /**
     * Postconstructor de la clase UserFacade
     */
    @PostConstruct
    public void init() {
    }

    /**
     * Entity Manager
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metodo encargado de consultar las estrategias por codigoStrategy
     *
     * @return "List Strategy"
     */
    @Override
    public List<Strategy> consultarStrategys() {
        List<Strategy> result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT st FROM Strategy st ORDER BY st.codigoStrategy DESC");

            if (query.getResultList().isEmpty()) {
                return null;
            } else {
                result = (List<Strategy>) query.getResultList();
            }

            return result;
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public List<Strategy> consultarStrategyAutocomplete(String name) {
        List<Strategy> result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT st FROM Strategy st WHERE st.nameStrategy like :name ");
            query.setParameter("name", "%" + name + "%");

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
     * Consultar el valor Maximo del CODIGO_STRATEGY
     *
     * @return BigInteger
     */
    @Override
    public BigInteger consultarMaxCodeStrategy() {
        BigInteger result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT MAX(cs.codigoStrategy) FROM Strategy cs");

            if (query.getResultList().isEmpty()) {
                return null;
            } else {
                result = (BigInteger) query.getSingleResult();
            }

            return result;
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public Strategy consultarStrategyById(Long id) {
        Strategy result = new Strategy();
        try {
            Query query = getEntityManager().createQuery("SELECT es FROM Strategy es WHERE es.idStrategy =:id");
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
                result = (Strategy) query.getSingleResult();
            }
            return result;
        } catch (Exception e) {
        }
        return result;
    }
    
    /**
     * Metodo encargado de consultar las estrategias por nameStrategy
     * @param name
     * @return 
     */
    @Override
    public boolean FindNameDB(String name) {
        boolean exist = false;
        try {
            Query query = getEntityManager().createQuery("SELECT st FROM Strategy st WHERE st.nameStrategy =:name");
            query.setParameter("name", name);            
            if (!query.getResultList().isEmpty()) {
                exist = true;
            } 
        } catch (Exception e) {
            System.out.print("Tenemos problemas al ejecutar FindNameDB(String name)");
        }       
        return exist;
    }

    
    /**
     * Metodo para crear una Estrategia
     * @param strategy
     */
    @Override
    public void CreateStrategy(Strategy strategy) {
        try {
            create(strategy);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al crear Entidad");
        }
    }

    /**
     * Metodo para editar una estrategia
     * @param strategy
     */
    @Override
    public void UpdateStrategy(Strategy strategy) {
        try {
            edit(strategy);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al editar Estrategia");
        }
    }

    /**
     * Metodo para eliminar una estrategia
     * @param strategy
     */
    @Override
    public void DeleteStrategy(Strategy strategy){
        try {
            remove(strategy);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al eliminar la estrategia");
        }
    }
}
