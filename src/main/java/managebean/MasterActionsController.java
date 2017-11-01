package managebean;

import ejb.StrategyEJBLocal;
import ejb.MasterActionEJBLocal;
import ejb.ActionEJBLocal;
import ejb.UserEJBLocal;
import entitys.Actions;
import entitys.MasterActions;
import entitys.Strategy;
import entitys.User;
import managebean.util.JsfUtil;
import managebean.util.ModeloUtilidades;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "masterActionsController")
@SessionScoped
public class MasterActionsController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MasterActions current;
    @EJB
    private StrategyEJBLocal strategyEjbLocal;
    @EJB
    private MasterActionEJBLocal mastersActionsEjbLocal;
    @EJB
    private ActionEJBLocal actionsEjbLocal;
    @EJB
    private UserEJBLocal usersEjbLocal;
    private List<Strategy> listStrategy;
    private List<MasterActions> listMasterActions;
    private List<Actions> listActions;
    private List<User> listUsers;

    @ManagedProperty(value = "#{loginManageBean}")
    private LoginManageBean loginManageBean;

    public MasterActionsController() {
    }

    public MasterActions getSelected() {
        if (getCurrent() == null) {
            setCurrent(new MasterActions());
        }
        return getCurrent();
    }

    public String prepareList() {
        consultarMasterActions();
        return "ListMasterActions";
    }

    public void consultarMasterActions() {
        try {
            listMasterActions = mastersActionsEjbLocal.consultarMasterActions();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void consultarActions() {
        try {
            listActions = actionsEjbLocal.consultarActions();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void consultarUser() {
        try {
            setListUsers(usersEjbLocal.consultarUsuarios());
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public String prepareView() {
        return "View";
    }

    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }

    public String prepareCreate() {
        setCurrent(new MasterActions());
        current.setUserActions(loginManageBean.getUserLogin());
        getCurrent().setTimeActions(ModeloUtilidades.getInstance().getFechaActual());
        consultarActions();
        
        return "CreateMasterActions";
    }

    public List<Strategy> prepareListStrategyAutocomplete(String name) {
        List<Strategy> result = null;
        try {
            result = strategyEjbLocal.consultarStrategyAutocomplete(name);
        } catch (Exception ex) {
        }
        return result;
    }

    public String create() {
        try {
            mastersActionsEjbLocal.CreateProcess(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("MasterActionsCreated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        consultarUser();
        return "EditMasterActions";
    }

    public String update() {
        try {
            mastersActionsEjbLocal.UpdateProcess(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("MasterActionsUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        consultarMasterActions();
        return "ListMasterActions";
    }

    private void performDestroy() {
        try {
            mastersActionsEjbLocal.DeleteProcess(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("MasterActionsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /**
     * @return the loginManageBean
     */
    public LoginManageBean getLoginManageBean() {
        return loginManageBean;
    }

    /**
     * @param loginManageBean the loginManageBean to set
     */
    public void setLoginManageBean(LoginManageBean loginManageBean) {
        this.loginManageBean = loginManageBean;
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
     * @return the listMasterActions
     */
    public List<MasterActions> getListMasterActions() {
        return listMasterActions;
    }

    /**
     * @param listMasterActions the listMasterActions to set
     */
    public void setListMasterActions(List<MasterActions> listMasterActions) {
        this.listMasterActions = listMasterActions;
    }

    /**
     * @return the current
     */
    public MasterActions getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(MasterActions current) {
        this.current = current;
    }

    /**
     * @return the listActions
     */
    public List<Actions> getListActions() {
        return listActions;
    }

    /**
     * @param listActions the listActions to set
     */
    public void setListActions(List<Actions> listActions) {
        this.listActions = listActions;
    }

    /**
     * @return the listUsers
     */
    public List<User> getListUsers() {
        return listUsers;
    }

    /**
     * @param listUsers the listUsers to set
     */
    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }
    
    
    @FacesConverter(forClass = MasterActions.class)
    public static class MasterActionsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MasterActionsController controller = (MasterActionsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "masterActionsController");
            return controller.mastersActionsEjbLocal.consultarMasterActById(getKey(value));
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
            if (object instanceof MasterActions) {
                MasterActions o = (MasterActions) object;
                return getStringKey(o.getIdMasterActions());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MasterActions.class.getName());
            }
        }

    }

}
