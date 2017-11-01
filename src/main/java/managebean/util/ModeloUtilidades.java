/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managebean.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lreyes
 */
public class ModeloUtilidades {
    
    private static ModeloUtilidades modeloUtilidades;

    private ModeloUtilidades() {   
    }

    public static ModeloUtilidades getInstance() {
        if (modeloUtilidades == null) {
            modeloUtilidades = new ModeloUtilidades();
        }
        return modeloUtilidades;
    }
    
    public Integer getVigenciaActual() {
        Integer anio = 0;
        try {
            anio = GregorianCalendar.getInstance().get(Calendar.YEAR);
        } catch (Exception exception) {
        }
        return anio;
    }

    /**
     * Metodo encargado de retornar el anio de una fecha dada.
     *
     * @param fechaEntrada
     * @return
     */
    public Integer getAnioDate(Date fechaEntrada) {
        Integer anio = 0;
        try {
            Calendar fecha = GregorianCalendar.getInstance();
            fecha.setTimeInMillis(fechaEntrada.getTime());
            anio = fecha.get(Calendar.YEAR);
        } catch (Exception exception) {
        }
        return anio;
    }

    /**
     * Metodo encargado de retornar el Mes de una fecha dada.
     *
     * @param fechaEntrada
     * @return
     */
    public Integer getMesDate(Date fechaEntrada) {
        Integer mes = 0;
        try {
            Calendar fecha = GregorianCalendar.getInstance();
            fecha.setTimeInMillis(fechaEntrada.getTime());
        	mes = fecha.get(Calendar.MONTH);
        	
        } catch (Exception exception) {
        }
        
        return mes;
    }

    /**
     * Metodo encargado de retornar el anio de una fecha dada.
     *
     * @param fechaEntrada
     * @return 
     */
    public Integer getAnio(Timestamp fechaEntrada) {
        Integer anio = 0;
        try {
            Calendar fecha = GregorianCalendar.getInstance();
            fecha.setTimeInMillis(fechaEntrada.getTime());
            anio = fecha.get(Calendar.YEAR);
        } catch (Exception exception) {
        }
        return anio;
    }
    
    /**
     * Metodo encargado de retornar la Fecha del Sistema
     * @return fechaActual
     */
    public Timestamp getFechaActual() {
        Timestamp fechaActual = null;
        try {
            fechaActual = formatearFecha(new Timestamp(System.currentTimeMillis()));
        } catch (Exception exception) {
        }
        return fechaActual;
    }
    
    public Timestamp formatearFecha(Timestamp fechaEntrada) {
        Timestamp fechaSalida = null;
        try {
            // Tomar la fecha actual
            Calendar fecha = GregorianCalendar.getInstance();
            // Cambiar por la fecha de entrada
            fecha.setTimeInMillis(fechaEntrada.getTime());
            // Quitar las horas, minutos, segundos y milisegundos 
            fecha.set(Calendar.AM_PM,Calendar.AM);
            fecha.set(Calendar.HOUR_OF_DAY,0);
            fecha.set(Calendar.HOUR,0);
            fecha.set(Calendar.MINUTE,0);
            fecha.set(Calendar.SECOND,0);
            fecha.set(Calendar.MILLISECOND,0);
            // Generar la fecha de salida formateada.
            fechaSalida = new Timestamp(fecha.getTime().getTime());
        } catch (Exception exception) {
        }
        return (fechaSalida != null ? fechaSalida : fechaEntrada);
    }
    
     protected void setIntoSession(final String key, final Object value) {
        try {
            if (getFromSession(key) != null) {
                throw new RuntimeException("Identificador duplicado en sesion: " + key);
            }
            getSession().setAttribute(key, value);
        } catch (Exception exception) {
        }
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
        }
        return null;
    } 
    
    public HttpSession getSession() {
        try {
            return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        } catch (Exception exception) {
        }
        return null;
    }

}
