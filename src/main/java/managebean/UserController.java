package managebean;

import entitys.User;
import entitys.RolUser;
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

@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User current;
    @EJB
    private ejb.UserEJBLocal EjbLocalUser;
    @EJB
    private ejb.RolUserEJBLocal EjbLocalRolUser;
    private List<User> listUser;
    private List<RolUser> listRoles;

    public UserController() {
    }

    public User getSelected() {
        if (getCurrent() == null) {
            setCurrent(new User());
        }
        return getCurrent();
    }


    public void consultarListUser() {
        setListUser(new ArrayList<>());
        try {
            setListUser(EjbLocalUser.consultarUsuarios());
        } catch (Exception ex) {
        }

    }

    public String prepareList() {
        consultarListUser();
        return "ListUser";
    }

    /**
     * Metodo que consulta todos los roles
     */
    public void prepareListRoles() {
        setListRoles(new ArrayList<>());
        try {
            setListRoles(EjbLocalRolUser.consultarTodosRoles());
        } catch (Exception ex) {
        }
    }

    public String prepareView() {
        return "ViewUser";
    }

    public String prepareCreate() {
        setCurrent(new User());
        prepareListRoles();
        return "CreateUser";
    }

    public String create() {
        try {
            EjbLocalUser.CreateUser(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("UserCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        prepareListRoles();
        return "EditUser";
    }

    public String update() {
        try {
            EjbLocalUser.updateUser(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("UserUpdated"));
            return "ViewUser";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        consultarListUser();
        return "ListUser";
    }

    private void performDestroy() {
        try {
            EjbLocalUser.DeleteUser(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("UserDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /**
     * @return the current
     */
    public User getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(User current) {
        this.current = current;
    }

    /**
     * @return the listUser
     */
    public List<User> getListUser() {
        return listUser;
    }

    /**
     * @param listUser the listUser to set
     */
    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
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

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.EjbLocalUser.consultarUserById(getKey(value));
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
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getIdUser());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }

}
