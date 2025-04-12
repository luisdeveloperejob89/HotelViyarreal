/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Conexion.DMBaseDatos;
import java.util.Date;

/**
 *
 * @author WIN7UTL64
 */
public class Bitacora 
{
    public static final int INICIAR_SESION = 0;
    public static final int AGREGAR_HOSPEDAJE = 1;
    public static final int AGREGAR_RESERVACION_SALA = 2;
    public static final int AGREGAR_CLIENTE = 3;
    public static final int MANDAR_LIMPIEZA_HABITACION = 4;
    public static final int LIBERAR_HABITACION = 5;
    public static final int MANDAR_LIMPIEZA_SALA = 6;
    public static final int LIBERAR_SALA = 7;
    public static final int GENERAR_CORTE_DIA = 8;
    public static final int CERRAR_SISTEMA = 9;    
    public static final int ELIMINAR_CLIENTE = 10;
    public static final int EDITAR_CLIENTE = 11;
    public static final int AGREGAR_EMPRESADESCTO = 12;
    public static final int EDITAR_EMPRESADESCTO = 13;
    public static final int ELIMINAR_EMPRESADESCTO = 14;
    private int ID;
    private int IdUsuario;
    private int TipoAccion;
    private Date Hora;
    private Date Fecha;
    private String Accion;
    private String Modulo;
    private static Bitacora uBitacora;
    private DMBaseDatos DMObjeto;
    
    public static Bitacora ObtenInstancia()
    {
        if ( uBitacora == null )
        {
            uBitacora = new Bitacora();                    
        }
        
        return uBitacora;
    }
    
    private Bitacora()
    {
        this.ID = 0;
        this.IdUsuario = 0;
        this.TipoAccion = 0;
        this.Hora = null;
        this.Fecha = null;
        this.Accion = null;
        this.Modulo = null;
        DMObjeto = new DMBaseDatos();
    }

    public void InsertarAccion(int iUsuario, String strAccion, String strModulo, int iTipo)
    {
        this.IdUsuario = iUsuario;
        this.TipoAccion = iTipo;
        this.Hora = new Date();
        this.Fecha = new Date();
        this.Accion = strAccion;
        this.Modulo = strModulo;
        Insertar();
    }
    
    public boolean Insertar()
    {
        boolean bOk = false;
        String strSentenciaSQL = null;
        
        try
        {
            strSentenciaSQL = "INSERT INTO BITACORA (ID_USUARIO, HORA ,FECHA, ACCION, MODULO, TIPOACCION) ";
            strSentenciaSQL += " VALUES(";
            strSentenciaSQL += this.IdUsuario + ", ";
            strSentenciaSQL += "NOW(), ";
            strSentenciaSQL += "NOW(), ";
            strSentenciaSQL += "'" + this.Accion + "', ";
            strSentenciaSQL += "'" + this.Modulo + "', ";
            strSentenciaSQL += this.TipoAccion + " ";
            strSentenciaSQL += ")";
            bOk = DMObjeto.Insertar(strSentenciaSQL);
        }
        catch ( Exception e )
        {
            bOk = false;
        }
        
        return bOk;
    }
    
    public void setID(int ID) 
    {
        this.ID = ID;
    }

    public void setIdUsuario(int IdUsuario) 
    {
        this.IdUsuario = IdUsuario;
    }

    public void setTipoAccion(int TipoAccion) 
    {
        this.TipoAccion = TipoAccion;
    }

    public void setHora(Date Hora) 
    {
        this.Hora = Hora;
    }

    public void setFecha(Date Fecha) 
    {
        this.Fecha = Fecha;
    }

    public void setAccion(String Accion) 
    {
        this.Accion = Accion;
    }

    public void setModulo(String Modulo) 
    {
        this.Modulo = Modulo;
    }

    public int getID() 
    {
        return ID;
    }

    public int getIdUsuario() 
    {
        return IdUsuario;
    }

    public int getTipoAccion() 
    {
        return TipoAccion;
    }

    public Date getHora() 
    {
        return Hora;
    }

    public Date getFecha() 
    {
        return Fecha;
    }

    public String getAccion() 
    {
        return Accion;
    }

    public String getModulo() 
    {
        return Modulo;
    }
    
    
}
