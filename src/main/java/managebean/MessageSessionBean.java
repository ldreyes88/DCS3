/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author lreyes
 */

public class MessageSessionBean {
     
    public void sucefullMessage(String title, String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title, message) );
    }
    
    public void errorMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        String error = "Error";
        context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_ERROR, error, message));
    }
   
}
