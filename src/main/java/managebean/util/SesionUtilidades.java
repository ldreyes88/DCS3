
package managebean.util;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DB-SYSTEM LTDA
 */
public class SesionUtilidades {

    private static SesionUtilidades sesionUtilidades = null;

    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Metodo constructor de la clase.
     *
     */
    private SesionUtilidades() {
        logger = Logger.getLogger(SesionUtilidades.class.getName());
    }

    public static SesionUtilidades getInstance() {
        if (sesionUtilidades == null) {
            sesionUtilidades = new SesionUtilidades();
        }
        return sesionUtilidades;
    }

    /**
     * Metodo encargado de limpiar la sesion de usuario.
     *
     * @param sesion
     * @throws java.lang.Exception
     */
    public void limpiarSesionMenu(HttpSession sesion) throws Exception {
        try {
            if (sesion != null) {
                // Obtener los atributos de la sesion
                Enumeration<String> atributos = sesion.getAttributeNames();
                while (atributos.hasMoreElements()) {
                    String atributo = atributos.nextElement();
                    if (!atributo.equals("isLogin") && !atributo.equals("usuarioSesion")) {
                        sesion.removeAttribute(atributo);
                    }
                }
            }
        } catch (Exception exception) {
            addException(exception);
            throw new Exception(exception.getMessage(),exception);
        } catch (Throwable throwable) {
            addException(throwable);
        }
    }

    /**
     * Metodo encargado de cerrar la sesion
     * @throws java.lang.Exception
     */
    public void cerrarSesion() throws Exception {
        try {
            limpiarSesionMenu(getSession());
        } catch (IllegalStateException iexception) {
            addException(iexception);
        } catch (Exception exception) {
            addException(exception);
        } catch (Throwable throwable) {
            addException(throwable);
        }
        finally {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            request.getSession().invalidate();
        }
    }
    
    /**
     * Metodo encargado de retornar la sesion
     * @return HttpSession
     */
    public HttpSession getSession() {
        try {
            return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        } catch (Exception exception) {
            addException(exception);
        }
        return null;
    }
    
    /**
     * Metodo encargado de retornar un atributo de sesion
     * @param key : String - Nombre del atributo de sesion
     * @return Object
     */
    public Object getFromSession(String key) {
        try {
            HttpSession sesion = getSession();
            return sesion.getAttribute(key);
        } catch (Exception exception) {
            addException(exception);
        }
        return null;
    } 
    
    protected void setIntoSession(final String key, final Object value) {
        try {
            if (getFromSession(key) != null) {
                throw new RuntimeException("Identificador duplicado en sesion: " + key);
            }
            getSession().setAttribute(key, value);
        } catch (Exception exception) {
            addException(exception);
        }
    }
    
    public void removeFromSession(final String key) {
        try {
            getSession().removeAttribute(key);
        } catch (Exception exception) {
            addException(exception);
        }
    }
    
    public void setIntoSessionReplace(final String key, final Object value) {
        try {
            if (getFromSession(key) != null) {
                removeFromSession(key);
            }
            getSession().setAttribute(key, value);
        } catch (Exception exception) {
            addException(exception);
        }
    }
    
    /**
     * Metodo encargado de retornar un bean de sesion
     * @param nameBean : String
     * @return Object
     */
    public Object getSessionManagedBean(String nameBean) {
        try {
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext,null,nameBean);
        } catch (Exception exception) {
            addException(exception);
        }
        return null;
    }

    /**
     * @author : DB-SYSTEM LTDA
     * @param s
     * @return Formato titulo de una cadena
     */
    public String toDisplayCase(String s) {
        final String ACTIONABLE_DELIMITERS = " '-/";
        StringBuilder sb = new StringBuilder();
        boolean capNext = true;
        for (char c : s.toCharArray()) {
            c = (capNext) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            sb.append(c);
            capNext = (ACTIONABLE_DELIMITERS.indexOf(c) >= 0);
        }
        return sb.toString();
    }

    /**
     * ******************************************************************************
     * MANEJO DE LOS MENSAJES
     * ******************************************************************************
     */
    /**
     * Metodo que muestra un mensaje de tipo Info
     *
     * @param msj
     */
    public void addInfo(String msj) {
        try {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msj,msj));
        } catch (Exception exception) {
            addException(exception);
        }
    }

    /**
     * Metodo que muestra un mensaje de tipo Warning
     *
     * @param msj
     */
    public void addWarn(String msj) {
        try {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,msj,msj));
        } catch (Exception exception) {
            addException(exception);
        }
    }

    /**
     * Metodo que muestra un mensaje de tipo Error
     *
     * @param msj
     */
    public void addError(String msj) {
        try {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,msj,msj));
        } catch (Exception exception) {
            addException(exception);
        }
    }

    /**
     * Metodo que muestra un mensaje de tipo Fatal
     *
     * @param msj
     */
    public void addFatal(String msj) {
        try {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,msj,msj));
        } catch (Exception exception) {
            addException(exception);
        }
    }

    /**
     * Metodo que muestra una excepcion
     *
     * @param excepcion
     */
    public void addException(Throwable excepcion) {
        try {
            String mensaje = excepcion.getMessage();
            
            // Validar si la excepcion es de base de datos
            if (excepcion instanceof SQLException) {
                mensaje = "No se pudo realizar la operación, por favor intente nuevamente. Si el problema persiste por favor comuníquese con el administrador del sistema.";
            }
            
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,mensaje,mensaje));
            logger.log(Level.SEVERE,".:: SIPSE ::. Excepción - " + mensaje,excepcion);
        } catch (Exception exception) {
            logger.log(Level.SEVERE,excepcion.getMessage(),exception);
        }
    }

    public SelectItem[] getSelectItems(List<?> entities,boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("","---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x,x.toString());
        }
        return items;
    }
}
