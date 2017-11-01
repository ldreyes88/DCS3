/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import entitys.Actions;

/**
 *
 * @author lreyes
 */
public interface ActionEJBLocal {

    /**
     * Metodo que consulta todas las Actiones
     *
     * @return List "Actions"
     */
    public List<Actions> consultarActions();

    /**
     * Metodo encargado de consultar una ACCION por ID
     *
     * @param id
     * @return Actions
     */
    public Actions consultarActionsById(Long id);

    /**
     * Metodo para crear una accion
     *
     * @param action
     */
    public void CreateAction(Actions action);

    /**
     * Metodo encargado de actualizar una accion
     *
     * @param action
     */
    public void UpdateAction(Actions action);

    /**
     * Metodo para eliminar una accion
     *
     * @param action
     */
    public void DeleteAction(Actions action);
}
