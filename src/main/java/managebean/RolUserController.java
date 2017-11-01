package managebean;

import entitys.RolUser;
import ejb.RolUserEJBLocal;
import managebean.util.JsfUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "rolUserController")
@SessionScoped
public class RolUserController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RolUser current;

    @EJB
    private RolUserEJBLocal ejbLocalRol;
    private List<RolUser> listRoles;

    public RolUserController() {
    }

    public RolUser getSelected() {
        if (getCurrent() == null) {
            setCurrent(new RolUser());
        }
        return getCurrent();
    }

    public String prepareList() {
        setListRoles(new ArrayList<>());
        try {
            setListRoles(ejbLocalRol.consultarTodosRoles());
        } catch (Exception ex) {
        }
        return "ListRoles";
    }

    public String prepareView() {
        return "ViewRol";
    }

    public String prepareCreate() {
        setCurrent(new RolUser());
        return "CreateRol";
    }

    public String create() {
        try {
            ejbLocalRol.CreateRol(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("RolUserCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        return "EditRol";
    }

    public String update() {
        try {
            ejbLocalRol.UpdateRol(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("RolUserUpdated"));
            return "ViewRol";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public void destroy() {
        performDestroy();
        prepareList();
    }

    private void performDestroy() {
        try {
            ejbLocalRol.DeleteRol(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("RolUserDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /**
     * @return the current
     */
    public RolUser getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(RolUser current) {
        this.current = current;
    }

    /**
     * @return the listRoles
     */
    public List<RolUser> getListRoles() {
        return listRoles;
    }

    /**
     * @param listRoles the listRoles to set
     */
    public void setListRoles(List<RolUser> listRoles) {
        this.listRoles = listRoles;
    }

    @FacesConverter(forClass = RolUser.class)
    public static class RolUserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolUserController controller = (RolUserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolUserController");
            return controller.ejbLocalRol.consultarRolById(getKey(value));
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
            if (object instanceof RolUser) {
                RolUser o = (RolUser) object;
                return getStringKey(o.getIdRolUser());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + RolUser.class.getName());
            }
        }

    }

}
