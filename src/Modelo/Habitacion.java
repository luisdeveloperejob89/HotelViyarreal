/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Conexion.DMBaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WIN7UTL64
 */
public class Habitacion 
{
    private int Id;
    private String Habitacion;
    private String Descripcion;
    private double PrecioDia;
    private int Estado;
    private int Equipaje;
    private int ControlAire;
    private int Llave;
    private int ControlTV;
    private int CantToallas;
    //private int IdHospedaje;
    private DMBaseDatos DMObjeto;    

    public Habitacion()
    {
        this.Id = 0;
        this.Habitacion = null;
        this.Descripcion = null;
        this.PrecioDia = 0.0;        
        this.Estado = 0; //0: Habitación Disponible, 1: Habitación Ocupada, 2: Habitación Reservada, 3: Habitación en Servicio, 4: Habitación en mantenimiento, 5: Ocupada por Reservación.
        this.Equipaje = 0;
        //this.IdHospedaje = 0;
        this.ControlAire = 1;
        this.Llave = 1;
        this.ControlTV = 1;
        this.CantToallas = 0;
        this.DMObjeto = new DMBaseDatos();        
    }
    
    public boolean Insertar()
    {
        boolean bOk = true;
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "INSERT INTO HABITACIONES (HABITACION, DESCRIPCION, PRECIO_DIA, ESTADO, EQUIPAJE) ";
        strSentenciaSQL += "VALUES('" + this.Habitacion + "',";
        strSentenciaSQL += "'" + this.Descripcion + "',";
        strSentenciaSQL += "" + this.PrecioDia + ",";
        strSentenciaSQL += "" + this.Estado + ",";
        strSentenciaSQL += "" + this.Equipaje + ")";        
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }        
    
    public String DameSentenciaParaActualizarEdo2(boolean bOcupado, int iID)
    {
        int iEstadoHabitacion = 2;
        String strSentenciaSQL = null;
        
        if ( bOcupado )
        {
            iEstadoHabitacion = 1;
        }
        
        
        strSentenciaSQL = "UPDATE HABITACIONES SET ESTADO = 3 WHERE ID = " + iID;
        
        return strSentenciaSQL;
    }
    
    public String DameSentenciaParaActualizarEdo(boolean bOcupado, int iID)
    {
        int iEstadoHabitacion = 2;
        String strSentenciaSQL = null;
        
        if ( bOcupado )
        {
            iEstadoHabitacion = 1;
        }
        
        
        //strSentenciaSQL = "UPDATE HABITACIONES SET ESTADO = " + iEstadoHabitacion + ", ID_HOSPEDAJE = " + iIdHospedaje + " WHERE ID = " + iID;
        strSentenciaSQL = "UPDATE HABITACIONES SET ESTADO = " + iEstadoHabitacion + " WHERE ID = " + iID;
        
        return strSentenciaSQL;
    }
    
    public String DameSentenciaParaActEdoHabDeReservacion(int iID)
    {
        int iEstadoHabitacion = 5;
        String strSentenciaSQL = null;
                
        strSentenciaSQL = "UPDATE HABITACIONES SET ESTADO = " + iEstadoHabitacion + " WHERE ID = " + iID;
        
        return strSentenciaSQL;
    }
    
    public int DameRegistrosCnsHabitaciones(DefaultTableModel dmtModelo, String[] vColumnas)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Utilerias uUtilerias = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM HABITACIONES";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;
            strSentenciaSQL = "SELECT ID, HABITACION, DESCRIPCION, PRECIO_DIA, ESTADO FROM HABITACIONES";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][5];
            uUtilerias = new Utilerias();
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("HABITACION");
                objTuplas[iContadorAux][2] = rsEstado.getString("DESCRIPCION");
                objTuplas[iContadorAux][3] = rsEstado.getDouble("PRECIO_DIA");
                objTuplas[iContadorAux][4] = uUtilerias.DameEstadoFormateadoHabitacion(rsEstado.getInt("ESTADO"));
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
    
    public boolean EditaDatosBasicosHabitacion()
    {
        boolean bOk = false;        
        String strSentenciaSQL = null;
        
        strSentenciaSQL  = "UPDATE HABITACIONES SET ";
        strSentenciaSQL += " HABITACION = '" + this.Habitacion + "', ";
        strSentenciaSQL += " DESCRIPCION = '" + this.Descripcion + "', ";
        strSentenciaSQL += " PRECIO_DIA = " + this.PrecioDia + ", ";
        strSentenciaSQL += " CANT_TOALLAS = " + this.CantToallas + " ";
        strSentenciaSQL += " WHERE ID = " + this.Id;
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }
    
    public boolean LiberaHabitacion(int iIdHabitacionAux)
    {
        boolean bOk = false;        
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "UPDATE HABITACIONES SET ESTADO = 0 WHERE ID = " + iIdHabitacionAux;
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }
        
    public boolean CambiaEdoHabitacion(int iIdHabitacionAux, int iEdoAux)
    {
        boolean bOk = false;
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "UPDATE HABITACIONES SET ESTADO = " + iEdoAux + " WHERE ID = " + iIdHabitacionAux;
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }
    
    public void DameUnaHabitacion(int iID)
    {
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        
        try
        {
            strSentenciaSQL = "SELECT * FROM HABITACIONES WHERE ID = " + iID;        
            rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);
            this.Id = iID;
            rsResultado.next();
            this.Habitacion = rsResultado.getString("HABITACION");
            this.Descripcion = rsResultado.getString("DESCRIPCION");
            this.PrecioDia = rsResultado.getDouble("PRECIO_DIA");
            this.Estado = rsResultado.getInt("ESTADO");
            this.Equipaje = rsResultado.getInt("EQUIPAJE");
            //this.IdHospedaje = rsResultado.getInt("ID_HOSPEDAJE");
            this.CantToallas = rsResultado.getInt("CANT_TOALLAS");
        }
        catch ( Exception e )
        {            
        }
        finally
        {
            try
            {
                if ( rsResultado != null )
                {
                    rsResultado.close();
                }
                
                DMObjeto.CerrarConexion();
            }
            catch ( SQLException e )
            {
                
            }
        }
    }
    
    public Habitacion DameHabitacionInstanciada(int iID)
    {
        DameUnaHabitacion(iID);
        
        return this;
    }
    
    public void setId(int Id) 
    {
        this.Id = Id;
    }

    public void setHabitacion(String Habitacion) 
    {
        this.Habitacion = Habitacion;
    }

    public void setDescripcion(String Descripcion) 
    {
        this.Descripcion = Descripcion;
    }

    public void setPrecioDia(double PrecioDia) 
    {
        this.PrecioDia = PrecioDia;
    }

    public void setEstado(int Estado) 
    {
        this.Estado = Estado;
    }

    public void setEquipaje(int Equipaje) 
    {
        this.Equipaje = Equipaje;
    }

    public int getId() 
    {
        return Id;
    }

    public String getHabitacion() 
    {
        return Habitacion;
    }

    public String getDescripcion() 
    {
        return Descripcion;
    }

    public double getPrecioDia() 
    {
        return PrecioDia;
    }

    public int getEstado() 
    {
        return Estado;
    }

    public int getEquipaje() 
    {
        return Equipaje;
    }

    /*public int getIdHospedaje()
    {
        return IdHospedaje;
    }

    public void setIdHospedaje(int IdHospedaje)
    {
        this.IdHospedaje = IdHospedaje;
    } */              

    public void setCantToallas(int CantToallas)
    {
        this.CantToallas = CantToallas;
    }

    public int getCantToallas()
    {
        return CantToallas;
    }        
}
