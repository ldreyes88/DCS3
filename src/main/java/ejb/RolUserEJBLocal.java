/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import entitys.RolUser;

/**
 *
 * @author Luis Daniel
 */
public interface RolUserEJBLocal {

    /**
     * Metodo encargado de consultar todos los roles
     *
     * @return List "Strategy"
     */
    public List<RolUser> consultarTodosRoles();

    /**
     * Metodo encargado de consultar un ROL por ID
     *
     * @param id
     * @return RolUser
     */
    public RolUser consultarRolById(Long id);

    /**
     * Metodo para crear un Rol
     *
     * @param rol
     */
    public void CreateRol(RolUser rol);

    /**
     * Metodo encargado de actualizar un rol
     *
     * @param rol
     */
    public void UpdateRol(RolUser rol);

    /**
     * Metodo para eliminar un rol
     *
     * @param rol
     */
    public void DeleteRol(RolUser rol);
}
