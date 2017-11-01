package managebean;

import entitys.Strategy;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import ejb.EntityEJBLocal;
import ejb.StrategyEJBLocal;
import entitys.Entitys;
import java.math.BigInteger;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "strategyController")
@SessionScoped
public class StrategyController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Strategy current;

    @EJB
    private EntityEJBLocal entitysEjbLocal;
    @EJB
    private StrategyEJBLocal strategyEjbLocal;
    private List<Entitys> listEntitys;
    private List<Strategy> listStrategy;
    private final MessageSessionBean msj;
    private BigInteger consecutivoCodigoStrategy;
    private boolean visibleButtonSaveConsecutivo;
    private Long idEntity;
    private String msjNameStrategy;
    private String msjTipo;

    @ManagedProperty(value = "#{entityController}")
    private EntityController entityManageBean;

    public StrategyController() {
        msj = new MessageSessionBean();
    }

    public Strategy getSelected() {
        if (getCurrent() == null) {
            setCurrent(new Strategy());
        }
        return getCurrent();
    }

    /**
     * Metodo que carga prepareListStrategy() y retorna ruta
     *
     * @return String
     */
    public String prepareList() {
        prepareListStrategy();
        return "ListStrategy";
    }

    /**
     * Metodo encargado de preparar la Vista y retornar la ruta
     *
     * @return String
     */
    public String prepareView() {
        return "ViewStrategy";
    }

    /**
     * Metodo encargado de preparar la creación de una Estrategia
     *
     * @return String
     */
    public String prepareCreate() {
        setCurrent(new Strategy());
        visibleButtonSaveConsecutivo = false;
        msjTipo = "white";
        preparateListEntitys();
        return "CreateStrategy";
    }

    /**
     * Metodo encargado de preparar la creación de una Estrategia
     *
     * @return String
     */
    public String prepareCreateWithConsecutive() {
        setCurrent(new Strategy());
        consecutivoCodigoStrategy = strategyEjbLocal.consultarMaxCodeStrategy().add(BigInteger.ONE);
        current.setCodigoStrategy(consecutivoCodigoStrategy);
        visibleButtonSaveConsecutivo = true;
        preparateListEntitys();
        return "CreateStrategy";
    }

    /**
     * Metodo encargado de preparar la edición de una estrategia
     *
     * @return String
     */
    public String prepareEdit() {
        preparateListEntitys();
        return "EditStrategy";
    }

    /**
     * Metodo para autocompletar Entidades en el formulario de Creación
     *
     * @param name
     * @return List "Entity"
     */
    public List<Entitys> prepareListEntitys(String name) {
        List<Entitys> result = null;
        try {
            result = entitysEjbLocal.consultarEntitysAutocomplete(name);
        } catch (Exception ex) {
            msj.errorMessage(ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
        return result;
    }

    /**
     * Metodo para traer todas Entidades en el formulario de Creación
     *
     */
    public void preparateListEntitys() {
        try {
            listEntitys = entitysEjbLocal.consultarEntitys();
        } catch (Exception ex) {
            msj.errorMessage(ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /**
     * Metodo que carga la lista de todas las strategias
     */
    public void prepareListStrategy() {
        try {
            listStrategy = strategyEjbLocal.consultarStrategys();
        } catch (Exception ex) {
            msj.errorMessage(ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /**
     * Metodo que realiza la creación de las estrategias del sistema. Realiza
     * una busqueda de la entidad
     *
     * @return String
     */
    public String create() {
        try {
            strategyEjbLocal.CreateStrategy(getCurrent());
            //getFacade().create(getCurrent());
            msj.sucefullMessage(ResourceBundle.getBundle("objects/Bundle").getString("StrategyCreated"), ResourceBundle.getBundle("objects/Bundle").getString("CreateStrategyLabel_Strategy") + getCurrent().getNameStrategy());
            return "ViewStrategy";
        } catch (Exception e) {
            msj.errorMessage(ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     * Metodo que realiza la creación de las estrategias del sistema.
     *
     * @return String
     */
    public String createWithConsecutive() {
        try {
            /**
             * Realiza la operación para asegurarse de que sea el codigo
             */
            consecutivoCodigoStrategy = strategyEjbLocal.consultarMaxCodeStrategy().add(BigInteger.ONE);
            current.setCodigoStrategy(consecutivoCodigoStrategy);
            strategyEjbLocal.CreateStrategy(getCurrent());
            msj.sucefullMessage(ResourceBundle.getBundle("objects/Bundle").getString("StrategyCreated"), ResourceBundle.getBundle("objects/Bundle").getString("CreateStrategyLabel_Strategy") + getCurrent().getNameStrategy());
            return "ViewStrategy";
        } catch (Exception e) {
            msj.errorMessage(ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     * Metodo que actualiza la información de una estrategia en el current
     *
     * @return String
     */
    public String update() {
        try {
            strategyEjbLocal.UpdateStrategy(getCurrent());
            msj.sucefullMessage("Operación", ResourceBundle.getBundle("objects/Bundle").getString("StrategyUpdated"));
            return "ViewStrategy";
        } catch (Exception e) {
            msj.errorMessage(ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     * Metodo encargado de invocar performDestroy para eliminar una estrategia
     *
     * @return
     */
    public String destroy() {
        performDestroy();
        prepareListStrategy();
        return "ListStrategy";
    }

    /**
     * Metodo encargado de realizar la eliminación de una estrategia
     */
    private void performDestroy() {
        try {
            strategyEjbLocal.DeleteStrategy(getCurrent());
            msj.sucefullMessage("Operación", ResourceBundle.getBundle("objects/Bundle").getString("StrategyDeleted"));
        } catch (Exception e) {
            msj.errorMessage(ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /**
     * Metodo encargado de consultar una Estrategia en la base de datos
     *
     * @param name
     */
    public void findNameDB(String name) {
        boolean exist = strategyEjbLocal.FindNameDB(name.trim());
        if (!name.isEmpty()) {
            if (exist) {
                msjNameStrategy = "El nombre ya existe!!";
                msjTipo = "red";
            } else {
                msjNameStrategy = "Correcto!!";
                msjTipo = "green";
            }
        } else {
            msjNameStrategy = "Escriba un nombre valido!!";
            msjTipo = "red";
        }
    }

    /**
     * @return the entityManageBean
     */
    public EntityController getEntityManageBean() {
        return entityManageBean;
    }

    /**
     * @param entityManageBean the entityManageBean to set
     */
    public void setEntityManageBean(EntityController entityManageBean) {
        this.entityManageBean = entityManageBean;
    }

    /**
     * @return the listEntitys
     */
    public List<Entitys> getListEntitys() {
        return listEntitys;
    }

    /**
     * @param listEntitys the listEntitys to set
     */
    public void setListEntitys(List<Entitys> listEntitys) {
        this.listEntitys = listEntitys;
    }

    /**
     * @return the current
     */
    public Strategy getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Strategy current) {
        this.current = current;
    }

    /**
     * @return the listStrategy
     */
    public List<Strategy> getListStrategy() {
        return listStrategy;
    }

    /**
     * @param listStrategy the listStrategy to set
     */
    public void setListStrategy(List<Strategy> listStrategy) {
        this.listStrategy = listStrategy;
    }

    /**
     * @return the consecutivoCodigoStrategy
     */
    public BigInteger getConsecutivoCodigoStrategy() {
        return consecutivoCodigoStrategy;
    }

    /**
     * @param consecutivoCodigoStrategy the consecutivoCodigoStrategy to set
     */
    public void setConsecutivoCodigoStrategy(BigInteger consecutivoCodigoStrategy) {
        this.consecutivoCodigoStrategy = consecutivoCodigoStrategy;
    }

    /**
     * @return the visibleButtonSaveConsecutivo
     */
    public boolean isVisibleButtonSaveConsecutivo() {
        return visibleButtonSaveConsecutivo;
    }

    /**
     * @param visibleButtonSaveConsecutivo the visibleButtonSaveConsecutivo to
     * set
     */
    public void setVisibleButtonSaveConsecutivo(boolean visibleButtonSaveConsecutivo) {
        this.visibleButtonSaveConsecutivo = visibleButtonSaveConsecutivo;
    }

    /**
     * @return the idEntity
     */
    public Long getIdEntity() {
        return idEntity;
    }

    /**
     * @param idEntity the idEntity to set
     */
    public void setIdEntity(Long idEntity) {
        this.idEntity = idEntity;
    }

    /**
     * @return the msjNameStrategy
     */
    public String getMsjNameStrategy() {
        return msjNameStrategy;
    }

    /**
     * @param msjNameStrategy the msjNameStrategy to set
     */
    public void setMsjNameStrategy(String msjNameStrategy) {
        this.msjNameStrategy = msjNameStrategy;
    }

    /**
     * @return the msjTipo
     */
    public String getMsjTipo() {
        return msjTipo;
    }

    /**
     * @param msjTipo the msjTipo to set
     */
    public void setMsjTipo(String msjTipo) {
        this.msjTipo = msjTipo;
    }

    @FacesConverter(forClass = Strategy.class)
    public static class EntityControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            StrategyController controller = (StrategyController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "strategyController");
            return controller.strategyEjbLocal.consultarStrategyById(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Strategy) {
                Strategy o = (Strategy) object;
                return getStringKey(o.getIdStrategy());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Strategy.class.getName());
            }
        }

    }

}
