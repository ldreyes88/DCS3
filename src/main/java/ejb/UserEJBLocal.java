/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entitys.User;
import java.util.List;

/**
 *
 * @author lreyes
 */
public interface UserEJBLocal {

    /**
     * Metodo encargado de consultar usuario valido
     *
     * @param identificacion
     * @param password
     * @return User User
     */
    public User consultarLogin(String identificacion, String password);

    /**
     * Metodo encargado de consultar un usuario por ID
     *
     * @param id
     * @return User
     */
    public User consultarUserById(Long id);

    /**
     * Metodo que consulta todos los usuarios del sistema
     *
     * @return List "User"
     */
    public List<User> consultarUsuarios();

    /**
     * Metodo para crear un Usuario
     *
     * @param user
     */
    public void CreateUser(User user);

    /**
     * Metodo encargado de Actualizar la información del usuario
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * Metodo para eliminar una User
     * @param user
     */
    public void DeleteUser(User user);

}
