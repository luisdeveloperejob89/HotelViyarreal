/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Conexion.DMBaseDatos;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author LCC Luis Alberto Flores Castillo <luis.ejob@gmail.com>
 */
public class Turnos
{
    private int Id;
    private int IdUsuario;
    private int Estado;
    private Date HoraInicio;
    private Date FechaInicio;
    private Date HoraSalida;
    private Date FechaSalida;
    private static Turnos tTurnos;
    private Usuario uUsuario = Usuario.ObtenInstancia();
    private DMBaseDatos DMObjeto;    
    
    public static Turnos ObtenInstancia()
    {
        if ( tTurnos == null )
        {
            tTurnos = new Turnos();            
        }
        
        return tTurnos;
    }
    
    private Turnos()
    {
        InstanciaPropiedades();
        this.DMObjeto = new DMBaseDatos();
    }

    private void InstanciaPropiedades()
    {
        this.Id = 0;
        this.IdUsuario = 0;
        this.Estado = 0;
        this.HoraInicio = new Date();
        this.FechaInicio = new Date();
        this.HoraSalida = new Date();
        this.FechaSalida = new Date();
    }

    public void setId(int Id)
    {
        this.Id = Id;
    }
    
    public boolean CerrarTurno()
    {                        
        return DMObjeto.Insertar("UPDATE TURNOS SET ESTADO = 1, FECHA_SALIDA = NOW(), HORA_SALIDA = NOW() WHERE ESTADO = 0 AND ID_USUARIO = " + uUsuario.getID());
    }
    
    public boolean TurnoAbierto()
    {
        boolean bOk = false;
        
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM TURNOS WHERE ID_USUARIO = " + uUsuario.getID() + " AND ESTADO = 0";
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            
            if ( rsEstado != null )
            {
                rsEstado.next();
                bOk = ( rsEstado.getInt("TOTAL") > 0 );
            }         
        }
        catch ( Exception e )
        {
            bOk = false;
        }
        
        return bOk;
    }
    
    public int DameIdTurnoIniciado()
    {
        int iId = 0;
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT ID FROM TURNOS WHERE ESTADO = 0";
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            
            if ( rsEstado != null )
            {
                rsEstado.next();
                iId = rsEstado.getInt("ID");                                
            }         
        }
        catch ( Exception e )
        {
            iId = 0;
        }
        
        return iId;
    }
    
    public boolean Insertar()
    {
        boolean bOk = false;
        int iId = 0;
        String strSentenciaSQL = null;
        
        iId = DameIdTurnoIniciado();
        
        if ( iId == 0 ) //Insertar
        {
            strSentenciaSQL = "INSERT INTO TURNOS (ID_USUARIO, ESTADO, HORA_INICIO, FECHA_INICIO) ";
            strSentenciaSQL += " VALUES(";
            strSentenciaSQL += uUsuario.getID() + ", ";
            strSentenciaSQL += "0, ";
            strSentenciaSQL += "NOW(), ";
            strSentenciaSQL += "NOW()";
            strSentenciaSQL += ");";
            bOk = DMObjeto.Insertar(strSentenciaSQL);
        }
        else
        {
            bOk = true;
        }
        
        return bOk;
    }

    public void setIdUsuario(int IdUsuario)
    {
        this.IdUsuario = IdUsuario;
    }

    public void setEstado(int Estado)
    {
        this.Estado = Estado;
    }

    public void setHoraInicio(Date HoraInicio)
    {
        this.HoraInicio = HoraInicio;
    }

    public void setFechaInicio(Date FechaInicio)
    {
        this.FechaInicio = FechaInicio;
    }

    public void setHoraSalida(Date HoraSalida)
    {
        this.HoraSalida = HoraSalida;
    }

    public void setFechaSalida(Date FechaSalida)
    {
        this.FechaSalida = FechaSalida;
    }

    public int getId()
    {
        return Id;
    }

    public int getIdUsuario()
    {
        return IdUsuario;
    }

    public int getEstado()
    {
        return Estado;
    }

    public Date getHoraInicio()
    {
        return HoraInicio;
    }

    public Date getFechaInicio()
    {
        return FechaInicio;
    }

    public Date getHoraSalida()
    {
        return HoraSalida;
    }

    public Date getFechaSalida()
    {
        return FechaSalida;
    }
    
    
}
