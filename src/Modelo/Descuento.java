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
public class Descuento 
{
    private int ID;
    private String Empresa;
    private double Descuento;
    private DMBaseDatos DMObjeto;
    
    public Descuento()
    {
        this.ID = 0;
        this.Empresa = null;
        this.Descuento = 0.0;        
        this.DMObjeto = new DMBaseDatos();    
    }

    public String DameSentenciaParaInsercion()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "INSERT INTO DESCUENTOS(EMPRESA, DESCUENTO) ";
        strSentenciaSQL += " VALUES(";
        strSentenciaSQL += "'" + this.Empresa + "',";
        strSentenciaSQL += "" + this.Descuento + "";
        strSentenciaSQL += ")";
        
        return strSentenciaSQL;
    }       
    
    public void InstanciaEmpresaDescuentos(int iID)
    {
        ResultSet rsEstado = null; 
        String strSentenciaSQL = null;
        
        try
        {
            strSentenciaSQL = "SELECT * FROM DESCUENTOS WHERE ID = " + iID;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            
            if ( rsEstado != null)
            {
                rsEstado.next();
                this.ID = rsEstado.getInt("ID");
                this.Empresa = rsEstado.getString("EMPRESA");
                this.Descuento = rsEstado.getDouble("DESCUENTO");
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public String DameSentenciaParaEdicion()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "UPDATE DESCUENTOS SET ";
        strSentenciaSQL += " EMPRESA = '" + this.Empresa + "', ";
        strSentenciaSQL += " DESCUENTO = " + this.Descuento + " ";
        strSentenciaSQL += "WHERE ID = " + this.ID;
        
        return strSentenciaSQL;
    }
    
    public boolean Actualizar()
    {
        boolean bOk = true;              
        String strSentenciaSQL = null;
        
        strSentenciaSQL = DameSentenciaParaEdicion();
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }
    
    public boolean Insertar()
    {
        boolean bOk = true;              
        String strSentenciaSQL = null;
        
        strSentenciaSQL = DameSentenciaParaInsercion();
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }        
    
    public int DameRegistrosEmpresasDescuentos(DefaultTableModel dmtModelo, String[] vColumnas)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;       
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM DESCUENTOS";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;
            strSentenciaSQL = "SELECT * FROM DESCUENTOS";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][3];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("EMPRESA");
                objTuplas[iContadorAux][2] = rsEstado.getDouble("DESCUENTO");                
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
    
    public void setID(int ID) 
    {
        this.ID = ID;
    }

    public void setEmpresa(String Empresa) 
    {
        this.Empresa = Empresa;
    }

    public void setDescuento(double Descuento) 
    {
        this.Descuento = Descuento;
    }

    public int getID() 
    {
        return ID;
    }

    public String getEmpresa() 
    {
        return Empresa;
    }

    public double getDescuento() 
    {
        return Descuento;
    }

    public String DameSentenciaParaEliminar()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "DELETE FROM DESCUENTOS WHERE ID = " + this.ID;
        
        return strSentenciaSQL;
    }
    
    public boolean Eliminar()
    {
        boolean bOk = true;
        String strSentenciaSQL = null;
        
        strSentenciaSQL = DameSentenciaParaEliminar();
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }    
}
