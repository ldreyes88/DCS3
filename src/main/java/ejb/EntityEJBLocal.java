/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entitys.Entitys;
import java.util.List;

/**
 *
 * @author lreyes
 */
public interface EntityEJBLocal {

    /**
     * Metodo encargado de consultar todas las entidades
     *
     * @param Long identificacion
     * @param String password
     * @return User User
     */
    public List<Entitys> consultarEntitys();

    /**
     * Metodo encargado de Consultar Entidades en Autocompletado
     *
     * @param name
     * @return List<Entities>
     */
    public List<Entitys> consultarEntitysAutocomplete(String name);
    
    /**
     * Metodo que consulta una entidad por su Id
     * @param id
     * @return Entitiess
     */
    public Entitys consultarEntityById(Long id);
    
    /**
     * Metodo que permite crear una Entidad
     * @param Entidad 
     */
    public void CreateEntity(Entitys Entidad);
    
    /**
     * Metodo que permite editar una Entidad
     * @param Entidad 
     */
    public void UpdateEntity(Entitys Entidad);
    
    /**
     * Metodo que permite eliminar una entidad
     * @param Entidad 
     */
    public void DeleteEntity(Entitys Entidad);
}
