/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Conexion.DMBaseDatos;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LCC Luis Alberto Flores Castillo <luis.ejob@gmail.com>
 */
public class Reservacion
{
    private int Id;
    private int IdHabitacion;
    private int IdHuesped;
    private int Estado; //[0]: Reservación "PROGRAMADA", [1]: Reservación "DISFRUTADA"
    private Date FReservada;
    private Date FRegistro;
    private DMBaseDatos DMObjeto;
    
    public Reservacion()
    {   
        InicializaAtributos();
        this.DMObjeto = new DMBaseDatos();
    }
    
    public void InicializaAtributos()
    {
        this.Id = 0;
        this.IdHabitacion = 0;
        this.IdHuesped = 0;
        this.Estado = 0; 
        this.FReservada = null;
        this.FRegistro = null;   
    }
    
    public boolean Insertar(int iIdHabitacion, int iIdHuesped, Date dtFecha)
    {
        boolean bOk = false;
        String strSentenciaSQL = null;
        
        strSentenciaSQL  = "INSERT INTO RESERVACIONES (ID_HABITACION, ID_HUESPED, ESTADO, F_RESERVADA, F_REGISTRO)";
        strSentenciaSQL += " VALUES(";
        strSentenciaSQL += iIdHabitacion + ", ";
        strSentenciaSQL += iIdHuesped + ", ";
        strSentenciaSQL += "0, ";
        strSentenciaSQL += "?, ";
        strSentenciaSQL += "NOW()";
        strSentenciaSQL += ")";
        
        bOk = DMObjeto.InsertaConUnaFecha(strSentenciaSQL, dtFecha);
        
        return bOk;
    }
    
    public boolean InstanciaReservacion(int iIdHabitacion, Date dtFechaRegistro)
    {
        boolean bOk = false;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        
        try
        {            
            strSentenciaSQL = "SELECT * FROM RESERVACIONES WHERE ID_HABITACION = " + iIdHabitacion + " AND F_RESERVADA = ? AND ESTADO = 0";
            rsResultado = DMObjeto.DameRegistrosPorFechaSC(strSentenciaSQL, dtFechaRegistro);
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                this.Id = rsResultado.getInt("ID");
                this.IdHabitacion = rsResultado.getInt("ID_HABITACION");
                this.IdHuesped = rsResultado.getInt("ID_HABITACION");
                this.Estado = rsResultado.getInt("ESTADO");
                this.FReservada = rsResultado.getDate("F_RESERVADA");
                this.FRegistro = rsResultado.getDate("F_REGISTRO");
                bOk = true;
            }
        }
        catch ( Exception e )
        {
            bOk = false;
            System.err.println("Error en InstanciaReservacion(): " + e.getMessage());
        }
        
        return bOk;
    }
    
    public boolean HabitacionReservada(int iIdHabitacion, Date dtFechaRegistro)
    {
        boolean bOk = true;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM RESERVACIONES WHERE ID_HABITACION = " + iIdHabitacion + " AND F_RESERVADA = ? AND ESTADO = 0";
            rsResultado = DMObjeto.DameRegistrosPorFechaSC(strSentenciaSQL, dtFechaRegistro);
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                
                if ( rsResultado.getInt("TOTAL") == 0 )
                {
                    bOk = false;
                }
            }
        }
        catch ( Exception e )
        {
            bOk = true;
            System.err.println("Error en HabitacionReservada(): " + e.getMessage());
        }
        
        return bOk;
    }
    
    public int DameRegistrosReservadosPorHabitacion(DefaultTableModel dmtModelo, String[] vColumnas, int iIdHabitacion)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;
        
        try
        {            
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM RESERVACIONES WHERE ESTADO = 0 AND ID_HABITACION = " + iIdHabitacion;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;            
            //strSentenciaSQL = "SELECT R.ID, CONCAT(H.NOMBRE, ' ', H.AP_PATERNO, ' ', H.AP_MATERNO) AS NOMBRE, R.F_RESERVADA, R.F_REGISTRO FROM RESERVACIONES R LEFT JOIN HUESPEDES H ON R.ID_HUESPED = H.ID WHERE R.ESTADO = 0 AND R.ID_HABITACION = " + iIdHabitacion;
            strSentenciaSQL = "SELECT R.ID, CONCAT(H.NOMBRE, ' ', H.AP_PATERNO, ' ', H.AP_MATERNO) AS NOMBRE, R.F_RESERVADA FROM RESERVACIONES R LEFT JOIN HUESPEDES H ON R.ID_HUESPED = H.ID WHERE R.ESTADO = 0 AND R.ID_HABITACION = " + iIdHabitacion;
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            //objTuplas = new Object[iTotalRegistros][4];
            objTuplas = new Object[iTotalRegistros][3];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("NOMBRE");
                objTuplas[iContadorAux][2] = rsEstado.getString("F_RESERVADA");
                //objTuplas[iContadorAux][3] = rsEstado.getString("F_REGISTRO");                
                iContadorAux++;
            }
            
            dmtModelo.setDataVector(objTuplas, vColumnas);                        
        }   
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        return iTotalRegistros;
    }

    public int getId()
    {
        return Id;
    }

    public void setId(int Id)
    {
        this.Id = Id;
    }

    public int getIdHabitacion()
    {
        return IdHabitacion;
    }

    public void setIdHabitacion(int IdHabitacion)
    {
        this.IdHabitacion = IdHabitacion;
    }

    public int getIdHuesped()
    {
        return IdHuesped;
    }

    public void setIdHuesped(int IdHuesped)
    {
        this.IdHuesped = IdHuesped;
    }

    public int getEstado()
    {
        return Estado;
    }

    public void setEstado(int Estado)
    {
        this.Estado = Estado;
    }

    public Date getFReservada()
    {
        return FReservada;
    }

    public void setFReservada(Date FReservada)
    {
        this.FReservada = FReservada;
    }

    public Date getFRegistro()
    {
        return FRegistro;
    }

    public void setFRegistro(Date FRegistro)
    {
        this.FRegistro = FRegistro;
    }
    
    
}
