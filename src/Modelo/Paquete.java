/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Conexion.DMBaseDatos;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WIN7UTL64
 */
public class Paquete 
{
    private int ID;
    private int Horas;
    private String Descripcion;
    private double PrecioSala;
    private double PrecioCoffee;
    private double PrecioAlmuerzoPP;
    private DMBaseDatos DMObjeto;
    
    public Paquete()
    {
        this.ID = 0;
        this.Horas = 0;
        this.Descripcion = null;
        this.PrecioSala = 0.0;
        this.PrecioCoffee = 0.0;
        this.PrecioAlmuerzoPP = 0.0;
        this.DMObjeto = new DMBaseDatos();
    }

    public String DameSentenciaParaUpdate()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "UPDATE PAQUETES  SET ";
        strSentenciaSQL += "PAQUETE = '" + this.Descripcion + "', ";
        strSentenciaSQL += "HORAS = " + this.Horas + ", ";
        strSentenciaSQL += "PRECIO_SALA = " + this.PrecioSala + ", ";
        strSentenciaSQL += "PRECIO_COFFEE = " + this.PrecioCoffee + ", ";
        strSentenciaSQL += "PRECIO_ALMUERZOPP = " + this.PrecioAlmuerzoPP + " ";
        strSentenciaSQL += " WHERE ID = " + this.ID;
        
        return strSentenciaSQL;
    }
    
    public void InstanciaPaquete(int iID)
    {
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        strSentenciaSQL = "SELECT * FROM PAQUETES WHERE ID = " + iID;
        rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
        
        try
        {
            if ( rsEstado != null )
            {
                rsEstado.next();
                this.ID = rsEstado.getInt("ID");
                this.Descripcion = rsEstado.getString("PAQUETE");
                this.Horas = rsEstado.getInt("HORAS");
                this.PrecioSala = rsEstado.getDouble("PRECIO_SALA");
                this.PrecioCoffee = rsEstado.getDouble("PRECIO_COFFEE");
                this.PrecioAlmuerzoPP = rsEstado.getDouble("PRECIO_ALMUERZOPP");
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public boolean Actualizar()
    {
        boolean bOk = true;
        String strSentenciaSQL = null;
        
        strSentenciaSQL = DameSentenciaParaUpdate();
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }
    
    public String DameSentenciaSQLInsercion()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "INSERT INTO PAQUETES (PAQUETE, HORAS, PRECIO_SALA, PRECIO_COFFEE, PRECIO_ALMUERZOPP) ";
        strSentenciaSQL += "VALUES (";        
        strSentenciaSQL += "'" + this.Descripcion + "' ,";
        strSentenciaSQL += "" + this.Horas + " ,";
        strSentenciaSQL += "" + this.PrecioSala + " ,";
        strSentenciaSQL += "" + this.PrecioCoffee + " ,";
        strSentenciaSQL += "" + this.PrecioAlmuerzoPP + "";
        strSentenciaSQL += ")";
                
        return strSentenciaSQL;
    }
    
    public boolean Insertar()
    {
        boolean bOk = true;
        String strSentenciaSQL = null;
        
        strSentenciaSQL = DameSentenciaSQLInsercion();
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }

    public void setHoras(int Horas) 
    {
        this.Horas = Horas;
    }

    public int getHoras() 
    {
        return Horas;
    }        
    
    public void setID(int ID) 
    {
        this.ID = ID;
    }

    public void setDescripcion(String Descripcion) 
    {
        this.Descripcion = Descripcion;
    }

    public void setPrecioSala(double PrecioSala) 
    {
        this.PrecioSala = PrecioSala;
    }

    public void setPrecioCoffee(double PrecioCoffee) 
    {
        this.PrecioCoffee = PrecioCoffee;
    }

    public void setPrecioAlmuerzoPP(double PrecioAlmuerzoPP) 
    {
        this.PrecioAlmuerzoPP = PrecioAlmuerzoPP;
    }

    public int getID() 
    {
        return ID;
    }

    public String getDescripcion() 
    {
        return Descripcion;
    }

    public double getPrecioSala() 
    {
        return PrecioSala;
    }

    public double getPrecioCoffee() 
    {
        return PrecioCoffee;
    }

    public double getPrecioAlmuerzoPP() 
    {
        return PrecioAlmuerzoPP;
    }

    public int DameRegistroCnsPaquetes(DefaultTableModel dmtModelo, String[] vColumnas) 
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;       
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM PAQUETES";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;
            strSentenciaSQL = "SELECT ID, PAQUETE, PRECIO_SALA, PRECIO_COFFEE, PRECIO_ALMUERZOPP FROM PAQUETES";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][5];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("PAQUETE");
                objTuplas[iContadorAux][2] = rsEstado.getDouble("PRECIO_SALA");
                objTuplas[iContadorAux][3] = rsEstado.getDouble("PRECIO_COFFEE");
                objTuplas[iContadorAux][4] = rsEstado.getDouble("PRECIO_ALMUERZOPP");
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
    
    
}
