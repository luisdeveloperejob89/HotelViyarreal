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
public class Gastos
{
    private int ID;
    private int IdUsuario;
    private String Concepto;
    private double Total;
    private Date Fecha;
    private Date Hora;
    private DMBaseDatos DMObjeto;
    private Usuario uUsuario = Usuario.ObtenInstancia();
    
    public Gastos()
    {
        DMObjeto = new DMBaseDatos();
    }
    
    public void InstanciaObjetos()
    {
        this.ID = 0;
        this.IdUsuario = 0;
        this.Concepto = null;
        this.Total = 0.0;
        this.Fecha = new Date();
        this.Hora = new Date();
    }        
    
    public double DameTotalActualRegistro()
    {
        double dTotal = 0.0;
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT TOTAL FROM GASTOS WHERE ID = " + this.ID;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            
            if ( rsEstado != null )
            {
                rsEstado.next();
                dTotal = rsEstado.getDouble("TOTAL");
            }
        }
        catch ( Exception e )
        {
            dTotal = 0.0;
        }
        
        return dTotal;
    }
    
    public boolean Editar()
    {
        boolean bOk = false;
        double dTotal = 0.0;
        double dTotalGastosEnCaja = 0.0;
        String strSentenciaSQL = null;
        String strSentenciaSQLCaja = null;
        
        strSentenciaSQL = "UPDATE GASTOS SET CONCEPTO = '" + this.Concepto + "', TOTAL = " + this.Total + " WHERE ID = " + this.ID;
        dTotalGastosEnCaja = ( DMObjeto.DameEfectivoActualDeGastosEnCaja() - DameTotalActualRegistro() );
        dTotalGastosEnCaja += this.Total;
        dTotal = ( DMObjeto.DameEfectivoIngresadoInicTurnoEnCaja() + DMObjeto.DameEfectivoActualDeHabEnCaja() ) - dTotalGastosEnCaja;
        strSentenciaSQLCaja = "UPDATE CAJA SET EFECTIVO_GASTADO = " + dTotalGastosEnCaja + ", EFECTIVO_TOTAL = " + dTotal + " WHERE CORTE_CAJA_HECHO = 0;";
        bOk = DMObjeto.InsertaGastosYActualizaCaja(strSentenciaSQL, strSentenciaSQLCaja);
        
        return bOk;
    }
    
    public boolean Insertar()
    {
        boolean bOk = false;
        double dTotal = 0.0;
        double dTotalGastosEnCaja = 0.0;
        String strSentenciaSQL = null;
        String strSentenciaSQLCaja = null;
                
        strSentenciaSQL = "INSERT INTO GASTOS (ID_USUARIO, CONCEPTO, TOTAL, FECHA, HORA) ";
        strSentenciaSQL += " VALUES (" + uUsuario.getID() + ", ";
        strSentenciaSQL += "'" + this.Concepto + "', ";
        strSentenciaSQL += this.Total + ", ";
        strSentenciaSQL += "NOW(), ";
        strSentenciaSQL += "NOW());";
        dTotalGastosEnCaja = DMObjeto.DameEfectivoActualDeGastosEnCaja();        
        dTotalGastosEnCaja += this.Total;
        dTotal = ( DMObjeto.DameEfectivoIngresadoInicTurnoEnCaja() + DMObjeto.DameEfectivoActualDeHabEnCaja() ) - dTotalGastosEnCaja;
        strSentenciaSQLCaja = "UPDATE CAJA SET EFECTIVO_GASTADO = " + dTotalGastosEnCaja + ", EFECTIVO_TOTAL = " + dTotal + " WHERE CORTE_CAJA_HECHO = 0;";
        bOk = DMObjeto.InsertaGastosYActualizaCaja(strSentenciaSQL, strSentenciaSQLCaja);
        
        return bOk;
    }
    
    public void InstanciaClase()
    {
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT * FROM GASTOS WHERE ID = " + this.ID;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);

            if ( rsEstado != null )
            {
                rsEstado.next();
                this.ID = rsEstado.getInt("ID");
                this.Concepto = rsEstado.getString("CONCEPTO");
                this.Total = rsEstado.getDouble("TOTAL");
                this.Fecha = rsEstado.getDate("FECHA");
                this.Hora = rsEstado.getDate("HORA");
            }
        }
        catch ( Exception e )
        {        
            System.err.println("Error en InstanciaClase(): " + e.getMessage());
        }
    }
    
    public int DameRegistrosGastos(DefaultTableModel dmtModelo, String[] vColumnas)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM GASTOS";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;            
            strSentenciaSQL = "SELECT G.ID, G.CONCEPTO, CONCAT(U.NOMBRE, ' ', U.AP_PATERNO, ' ', U.AP_MATERNO) AS NOMBRE, G.TOTAL, G.FECHA, G.HORA FROM GASTOS G LEFT JOIN USUARIOS U ON G.ID_USUARIO = U.ID";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][6];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("G.ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("G.CONCEPTO");
                objTuplas[iContadorAux][2] = rsEstado.getString("NOMBRE");
                objTuplas[iContadorAux][3] = rsEstado.getDouble("G.TOTAL");
                objTuplas[iContadorAux][4] = rsEstado.getString("G.FECHA");
                objTuplas[iContadorAux][5] = rsEstado.getString("G.HORA");                
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

    public void setIdUsuario(int IdUsuario)
    {
        this.IdUsuario = IdUsuario;
    }

    public void setConcepto(String Concepto)
    {
        this.Concepto = Concepto;
    }

    public void setTotal(double Total)
    {
        this.Total = Total;
    }

    public void setFecha(Date Fecha)
    {
        this.Fecha = Fecha;
    }

    public void setHora(Date Hora)
    {
        this.Hora = Hora;
    }

    public int getID()
    {
        return ID;
    }

    public int getIdUsuario()
    {
        return IdUsuario;
    }

    public String getConcepto()
    {
        return Concepto;
    }

    public double getTotal()
    {
        return Total;
    }

    public Date getFecha()
    {
        return Fecha;
    }

    public Date getHora()
    {
        return Hora;
    }
    
    
}
