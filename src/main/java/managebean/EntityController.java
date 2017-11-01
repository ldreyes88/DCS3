package managebean;

import ejb.EntityEJBLocal;
import entitys.Entitys;
import managebean.util.JsfUtil;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "entityController")
@SessionScoped
public class EntityController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Entitys current;
    @EJB
    private EntityEJBLocal ejbFacadeLocal;
    private final MessageSessionBean msj;
    private List<Entitys> listEntity;

    public EntityController() {
        msj = new MessageSessionBean();
        listEntity = null;
    }

    public Entitys getSelected() {
        if (getCurrent() == null) {
            setCurrent(new Entitys());
        }
        return getCurrent();
    }

    public String prepareList() {
        prepareListEntity();
        return "ListEntity";
    }

    public void prepareListEntity() {
        try {
            listEntity = ejbFacadeLocal.consultarEntitys();
        } catch (Exception ex) {
        }
    }

    /**
     * Metodo que consulta una entidad por Id
     *
     * @param id
     * @return Entitys
     */
    public Entitys prepareEntityById(Long id) {
    	Entitys resultado = null;
        try {
            resultado = ejbFacadeLocal.consultarEntityById(id);
        } catch (Exception ex) {
        }
        return resultado;
    }

    public String prepareView() {
        return "ViewEntity";
    }

    public String prepareCreate() {
        setCurrent(new Entitys());
        return "CreateEntity";
    }

    public String create() {
        try {
            ejbFacadeLocal.CreateEntity(getCurrent());
            msj.sucefullMessage(ResourceBundle.getBundle("objects/Bundle").getString("EntityCreated"), ResourceBundle.getBundle("objects/Bundle").getString("CreateEntityLabel_nameEntity") + getCurrent().getNameEntity());
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        return "EditEntity";
    }

    public String update() {
        try {
            ejbFacadeLocal.UpdateEntity(getCurrent());
            msj.sucefullMessage(ResourceBundle.getBundle("objects/Bundle").getString("EntityUpdated"), getCurrent().getNameEntity());
            return "ViewEntity";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        prepareListEntity();
        return "ListEntity";
    }

    private void performDestroy() {
        try {
            ejbFacadeLocal.DeleteEntity(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("EntityDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /**
     * @return the listEntity
     */
    public List<Entitys> getListEntity() {
        return listEntity;
    }

    /**
     * @param listEntity the listEntity to set
     */
    public void setListEntity(List<Entitys> listEntity) {
        this.listEntity = listEntity;
    }

    /**
     * @return the current
     */
    public Entitys getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Entitys current) {
        this.current = current;
    }

    @FacesConverter(forClass = Entitys.class)
    public static class EntityControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EntityController controller = (EntityController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "entityController");
            return controller.ejbFacadeLocal.consultarEntityById(getKey(value));
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
            if (object instanceof Entitys) {
            	Entitys o = (Entitys) object;
                return getStringKey(o.getIdEntity());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Entitys.class.getName());
            }
        }

    }

}
