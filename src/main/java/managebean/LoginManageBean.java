/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebean;

import ejb.RolUserEJBLocal;
import ejb.UserEJBLocal;
import entitys.RolUser;
import entitys.User;
import managebean.util.JsfUtil;
import managebean.util.SesionUtilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

/**
 *
 * @author lreyes
 */
@ManagedBean(name = "loginManageBean")
@SessionScoped
public class LoginManageBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final String BEAN_NAME = "LoginManageBean";

    @EJB
    private UserEJBLocal userEJBLocal;
    @EJB
    private RolUserEJBLocal ejbLocalRol;
    private List<RolUser> listRoles;
    private User userLogin;
    private User localUser;
    private boolean session;
    private String identificacion;
    private String passWords;
    private final MessageSessionBean msj;
    private boolean logeado = false;

    /**
     * Creates a new instance of LoginManageBean
     */
    public LoginManageBean() {
        userLogin = null;
        session = false;
        msj = new MessageSessionBean();
    }

    /**
     * metodo que consulta la session
     * @return ruta
     */
    public String consultarSesion() {
        String operacion = "";
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            setUserLogin(getUserEJBLocal().consultarLogin(identificacion, passWords));

            if (getUserLogin() == null) {
                msj.errorMessage("Verifique sus datos!!");
            } else {
                operacion = "loggedIn";
                logeado = true;
                setSession(true);
                msj.sucefullMessage("Operacion en JBOSS", "Bienvenido: " + getUserLogin().getNameUser());
                context.addCallbackParam("loggedIn", true);
            }

        } catch (Exception ex) {
            msj.errorMessage("Error al iniciar");
        }
        return operacion;
    }

    /**
     * Metodo que cierra la sesiÃ³n de un funcionario en el sistema
     *
     * @return
     */
    public String logout() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpSession session = request.getSession();
            SesionUtilidades.getInstance().limpiarSesionMenu(session);
            session.invalidate();
        } catch (Exception exception) {
            SesionUtilidades.getInstance().addException(exception);
        } finally {
            setLogeado(false);
        }
        return "loggedOut";
    }

    /**
     * Metodo que cierra la sesiÃ³n de un funcionario en el sistema
     *
     * @return
     */
    public String closeSesion() {

        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpSession session = request.getSession();
            SesionUtilidades.getInstance().limpiarSesionMenu(session);
            session.invalidate();
        } catch (Exception exception) {
            SesionUtilidades.getInstance().addException(exception);
        } finally {
            setLogeado(false);
        }
        
        return "loggedOut";
        
    }


    public String prepareUpdatePerfil(){
        localUser = new User();
        prepareListRoles();
        localUser.setActiveUser(userLogin.getActiveUser());
        localUser.setCelularUser(userLogin.getCelularUser());
        localUser.setEmailUser(userLogin.getEmailUser());
        //localUser.setIdRolUser(userLogin.getIdRolUser());
        localUser.setIdUser(userLogin.getIdUser());
        localUser.setIdentificationUser(userLogin.getIdentificationUser());
        localUser.setLastNameUser(userLogin.getLastNameUser());
        localUser.setNameUser(userLogin.getNameUser());
        localUser.setPasswordUser(userLogin.getPasswordUser());     
        
        return "UpdateProfile";
    }
    
    public String update() {
        try {            
            getUserEJBLocal().updateUser(localUser);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("objects/Bundle").getString("UserUpdated"));
            return "";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("objects/Bundle").getString("PersistenceErrorOccured"));
            return "";
        }
    }
    
	public String prepareListRoles(){
        setListRoles(new ArrayList<>());
        try {
            setListRoles(ejbLocalRol.consultarTodosRoles());
        } catch (Exception ex) {
        }
        return "ListRoles";
    }

    /**
     * @return the userLogin
     */
    public User getUserLogin() {
        return userLogin;
    }

    /**
     * @param userLogin the userLogin to set
     */
    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }
    

    /**
     * @return the session
     */
    public boolean isSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(boolean session) {
        this.session = session;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the passWords
     */
    public String getPassWords() {
        return passWords;
    }

    /**
     * @param passWords the passWords to set
     */
    public void setPassWords(String passWords) {
        this.passWords = passWords;
    }

    /**
     * @return the BEAN_NAME
     */
    public static String getBEAN_NAME() {
        return BEAN_NAME;
    }

    /**
     * @return the logeado
     */
    public boolean isLogeado() {
        return logeado;
    }

    /**
     * @param logeado the logeado to set
     */
    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    /**
     * @return the userEJBLocal
     */
    public UserEJBLocal getUserEJBLocal() {
        return userEJBLocal;
    }

    /**
     * @return the localUser
     */
    public User getLocalUser() {
        return localUser;
    }

    /**
     * @param localUser the localUser to set
     */
    public void setLocalUser(User localUser) {
        this.localUser = localUser;
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

    
}
