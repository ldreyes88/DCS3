/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entitys.MasterActions;
import java.util.List;

/**
 *
 * @author lreyes
 */
public interface MasterActionEJBLocal {

    /**
     * Interface encargada de consultar todas las Master Actions
     *
     * @return List "MasterActions"
     */
    public List<MasterActions> consultarMasterActions();

    /**
     * Medoto que consulta un proceso por ID
     *
     * @param id
     * @return MasterActions
     */
    public MasterActions consultarMasterActById(Long id);

    /**
     * Metodo para crear un proceso
     *
     * @param masterActions
     */
    public void CreateProcess(MasterActions masterActions);

    /**
     * Metodo para editar un Proceso
     *
     * @param masterActions
     */
    public void UpdateProcess(MasterActions masterActions);

    /**
     * Metodo para eliminar Proceso
     *
     * @param masterActions
     */
    public void DeleteProcess(MasterActions masterActions);

}
