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

import ejb.EntityEJBLocal;
import entitys.Entitys;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luis D. Reyes
 * @version 1.0
 *
 */
@Stateless
public class EntitysFacade extends AbstractFacade<Entitys> implements EntityEJBLocal {

    /**
     * Atributo entityManager : EntityManager
     */
    @PersistenceContext(unitName = "dcs3")
    private EntityManager entityManager;

    /**
     * Atributo log : Logger
     */
    private static final Logger log = Logger.getLogger(Entitys.class.getName());

    /**
     * Metodo constructor de la clase UserFacade
     */
    public EntitysFacade() {
        super(Entitys.class);
    }

    /**
     * Postconstructor de la clase UserFacade
     */
    @PostConstruct
    public void init() {
    }

    /**
     * @return 
     * @see AbstractFacade#getEntityManager()
     */
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metodo que permite consultar todas las entidades
     * @return
     */
    @Override
    public List<Entitys> consultarEntitys() {
        List<Entitys> result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT us FROM Entitys us");

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

    @Override
    public Entitys consultarEntityById(Long id) {
        Entitys result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT en FROM Entitys en WHERE en.idEntity =:id");
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
                result = (Entitys) query.getSingleResult();
            }
            return result;
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public List<Entitys> consultarEntitysAutocomplete(String name) {
        List<Entitys> result = null;
        try {
            Query query = getEntityManager().createQuery("SELECT en FROM Entitys en WHERE en.nameEntity like :name");
            query.setParameter("name", "%" + name + "%");
            if (!query.getResultList().isEmpty()) {
                result = (List<Entitys>) query.getResultList();
            }
            return result;
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Metodo para crear una Entidad
     *
     * @param Entidad
     */
    @Override
    public void CreateEntity(Entitys Entidad) {
        try {
            create(Entidad);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al crear Entidad");
        }
    }

    /**
     * Metodo para editar una entidad
     *
     * @param Entidad
     */
    @Override
    public void UpdateEntity(Entitys Entidad) {
        try {
            edit(Entidad);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al editar Entidad");
        }
    }

    /**
     * Metodo para eliminar una entidad
     *
     * @param Entidad
     */
    @Override
    public void DeleteEntity(Entitys Entidad) {
        try {
            remove(Entidad);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
            System.out.println("Error al eliminar la Entidad");
        }
    }
}
