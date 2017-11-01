/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entitys.Strategy;
import java.math.BigInteger;
import java.util.List;

/**
 * Interface EJB para manejar las estrategias
 * @author lreyes
 */
public interface StrategyEJBLocal {

    /**
     * Metodo encargado de consultar las estrategias
     * @return List "Strategy"
     */
    public List<Strategy> consultarStrategys();

    /**
     * Metodo Encargado de consultar para Autocompletar
     * @param name
     * @return List "Strategy"
     */
    public List<Strategy> consultarStrategyAutocomplete(String name);

    /**
     * Metodo Encargado de consultar el consetivo del Codigo de la Strategia
     * @return BigInteger
     */
    public BigInteger consultarMaxCodeStrategy();
    
    /**
     * Metodo encargado de consultar la estrategia por ID
     * @param id
     * @return Strategy
     */
    public Strategy consultarStrategyById(Long id);

    /**
     * Metodo para crear una Estrategia
     * @param strategy
     */
    public void CreateStrategy(Strategy strategy);

    /**
     * Metodo para editar una estrategia
     * @param strategy
     */
    public void UpdateStrategy(Strategy strategy);

    /**
     * Metodo para eliminar una estrategia
     * @param strategy
     */
    public void DeleteStrategy(Strategy strategy);
    
     /**
    
    /**
     * Metodo encargado de consultar las estrategias por nameStrategy
     * @param name
     * @return 
     */
    public boolean FindNameDB(String name);

}
