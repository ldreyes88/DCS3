package managebean;

import entitys.Actions;
import managebean.util.JsfUtil;
import ejb.ActionEJBLocal;

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

@ManagedBean(name = "actionsController")
@SessionScoped
public class ActionsController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Actions current;
    @EJB
    private ActionEJBLocal ejbLocalActions;
    private List<Actions> listActions;

    public ActionsController() {
    }

    public Actions getSelected() {
        if (getCurrent() == null) {
            setCurrent(new Actions());
        }
        return getCurrent();
    }

    public void consultarListaAction() {
        try {
            setListActions(ejbLocalActions.consultarActions());
        } catch (Exception ex) {
            System.out.println("Error al consultar Lista de Acciones");
        }
    }

    public String prepareList() {
        consultarListaAction();
        return "ListAcciones";
    }

    public String prepareView() {
        return "ViewAccion";
    }

    public String prepareCreate() {
        setCurrent(new Actions());
        return "CreateAccion";
    }

    public String create() {
        try {
            ejbLocalActions.CreateAction(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("ActionsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        return "EditAccion";
    }

    public String update() {
        try {
            ejbLocalActions.UpdateAction(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("ActionsUpdated"));
            return "ViewAccion";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        consultarListaAction();
        return "ListAcciones";
    }

    private void performDestroy() {
        try {
            ejbLocalActions.DeleteAction(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("ActionsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    /**
     * @return the current
     */
    public Actions getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Actions current) {
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

    @FacesConverter(forClass = Actions.class)
    public static class ActionsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ActionsController controller = (ActionsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "actionsController");
            return controller.ejbLocalActions.consultarActionsById(getKey(value));
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
            if (object instanceof Actions) {
                Actions o = (Actions) object;
                return getStringKey(o.getIdActions());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Actions.class.getName());
            }
        }

    }

}
