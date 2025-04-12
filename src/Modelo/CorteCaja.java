/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import Conexion.DMBaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author LCC Luis Alberto Flores Castillo <luis.ejob@gmail.com>
 */
public class CorteCaja
{
    private int ID;
    private double EfectivoIngresado;
    private int CorteCajaHecho; //0: Si no se ha realizado el Corte de caja. 1: Se ha realizado el Corte de Caja.
    private Date FechaCorte;
    private Date HoraCorte;
    private double EfectivoGastado; //Efectivo utilizado para compras (Proveedores o algún servicio).
    private double EfectivoHabitaciones; //Efectivo recibido por la renta de las Habitaciones.
    private double EfectivoTotal; //Efectivo total restante (Efectivo ingresado + Efectivo Habitaciones - Efectivo gastado).
    private Date FechaIngEfectivo; //Fecha de Ingreso del Efectivo.
    private Date HoraIngEfectivo; //Hora del Ingreso del Efectivo.
    private static CorteCaja cCorteCaja;
    private DMBaseDatos DMObjeto;
    
    public static CorteCaja ObtenInstancia()
    {
        if ( cCorteCaja == null )
        {
            cCorteCaja = new CorteCaja();            
        }
        
        return cCorteCaja;        
    }
    
    private CorteCaja()
    {
        InstanciaPropiedades();
        this.DMObjeto = new DMBaseDatos();
    }
    
    public void InstanciaPropiedades()
    {
        this.ID = 0;
        this.EfectivoIngresado = 0.0;
        this.CorteCajaHecho = 0;
        this.FechaCorte = new Date();
        this.HoraCorte = new Date();
        this.EfectivoGastado = 0.0;
        this.EfectivoHabitaciones = 0.0;
        this.EfectivoTotal = 0.0;
        this.FechaIngEfectivo = new Date();
        this.HoraIngEfectivo = new Date();
    }        
    
    public int DameTotalCortesCajaPorHacer()
    {
        int iTotalRegistros = 0;         
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM CAJA WHERE CORTE_CAJA_HECHO = 0";
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            
            if ( rsEstado != null )
            {
                rsEstado.next();
                iTotalRegistros = rsEstado.getInt("TOTAL");                                
            }            
        }
        catch ( Exception e )
        {
            iTotalRegistros = 0;
        }
                
        return iTotalRegistros;
    }
    
    public void InstanciaClaseConRS(ResultSet rsEstado) throws SQLException
    {
        this.ID = rsEstado.getInt("ID");
        this.EfectivoIngresado = rsEstado.getDouble("EFECTIVO_INGRESADO");
        this.CorteCajaHecho = rsEstado.getInt("CORTE_CAJA_HECHO");
        this.FechaCorte = rsEstado.getDate("FECHA_CORTE");
        this.HoraCorte = rsEstado.getDate("HORA_CORTE");
        this.EfectivoGastado = rsEstado.getDouble("EFECTIVO_GASTADO");
        this.EfectivoHabitaciones = rsEstado.getDouble("EFECTIVO_HAB");
        this.EfectivoTotal = rsEstado.getDouble("EFECTIVO_TOTAL");
        this.FechaIngEfectivo = rsEstado.getDate("FECHA_ING_EFECTIVO");
        this.HoraIngEfectivo = rsEstado.getDate("HORA_ING_EFECTIVO");
    }
    /**
     * Esta función determina si existe algún corte de caja por hacer. En caso
     * que no existan registros o que se tenga un registro al cual no se le ha
     * realizado el corte, instanciará el objeto para que pueda ser ingresado el efectivo
     * a utilizar al Inicio del día / turno.
     */
    public boolean ExisteCorteCajaActivo()
    {
        boolean bOk = false;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        try
        {            
            iTotalRegistros = DameTotalCortesCajaPorHacer();
                
            if ( iTotalRegistros > 0 )
            {            
                strSentenciaSQL = "SELECT * AS TOTAL FROM CAJA WHERE CORTE_CAJA_HECHO = 0";                
                rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
                    
                if ( rsEstado != null )
                {                                        
                    rsEstado.next(); //Debería haber un solo registro activo siempre.
                    InstanciaClaseConRS(rsEstado);
                    bOk = true;
                }                
            }
            else
            {
                bOk = true;
            }
        }
        catch ( Exception e )
        {
            bOk = false;
        }
        
        return bOk;
    }
    
    public boolean GeneraCorteAlDia()
    {
        boolean bOk = false;
        
        bOk = DMObjeto.GeneraCorteAlDia();
        
        return bOk;
    }
    
    public String DameSentenciaInsercionEfectivo()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "INSERT INTO CAJA ";
        strSentenciaSQL += " (EFECTIVO_INGRESADO, CORTE_CAJA_HECHO, EFECTIVO_GASTADO, EFECTIVO_HAB, EFECTIVO_TOTAL, FECHA_ING_EFECTIVO, HORA_ING_EFECTIVO) ";
        strSentenciaSQL += " VALUES(";
        strSentenciaSQL += this.EfectivoIngresado + ", ";
        strSentenciaSQL += "0, ";
        strSentenciaSQL += "0.0, ";
        strSentenciaSQL += "0.0, ";
        strSentenciaSQL += this.EfectivoIngresado + ", ";
        strSentenciaSQL += "NOW(), ";
        strSentenciaSQL += "NOW()";
        strSentenciaSQL += ")";
                
        return strSentenciaSQL;
    }
    
    public boolean IngresarEfectivo(double dEfectivo)
    {
        boolean bOk = false;
        String strSentenciaSQL = null;        
        
        if ( DameTotalCortesCajaPorHacer() > 0 ) //Actualizar
        {
            strSentenciaSQL = "UPDATE CAJA SET EFECTIVO_INGRESADO = " + dEfectivo +  ", CORTE_CAJA_HECHO = 0  WHERE ID = " + this.ID;
        }
        else //Insertar
        {
            this.EfectivoIngresado = dEfectivo;
            strSentenciaSQL = DameSentenciaInsercionEfectivo();
        }
        
        try
        {
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

    public void setEfectivoIngresado(double EfectivoIngresado)
    {
        this.EfectivoIngresado = EfectivoIngresado;
    }

    public void setCorteCajaHecho(int CorteCajaHecho)
    {
        this.CorteCajaHecho = CorteCajaHecho;
    }

    public void setFechaCorte(Date FechaCorte)
    {
        this.FechaCorte = FechaCorte;
    }

    public void setHoraCorte(Date HoraCorte)
    {
        this.HoraCorte = HoraCorte;
    }

    public void setEfectivoGastado(double EfectivoGastado)
    {
        this.EfectivoGastado = EfectivoGastado;
    }

    public void setEfectivoHabitaciones(double EfectivoHabitaciones)
    {
        this.EfectivoHabitaciones = EfectivoHabitaciones;
    }

    public void setEfectivoTotal(double EfectivoTotal)
    {
        this.EfectivoTotal = EfectivoTotal;
    }

    public void setFechaIngEfectivo(Date FechaIngEfectivo)
    {
        this.FechaIngEfectivo = FechaIngEfectivo;
    }

    public void setHoraIngEfectivo(Date HoraIngEfectivo)
    {
        this.HoraIngEfectivo = HoraIngEfectivo;
    }

    public void setDMObjeto(DMBaseDatos DMObjeto)
    {
        this.DMObjeto = DMObjeto;
    }

    public int getID()
    {
        return ID;
    }

    public double getEfectivoIngresado()
    {
        return EfectivoIngresado;
    }

    public int getCorteCajaHecho()
    {
        return CorteCajaHecho;
    }

    public Date getFechaCorte()
    {
        return FechaCorte;
    }

    public Date getHoraCorte()
    {
        return HoraCorte;
    }

    public double getEfectivoGastado()
    {
        return EfectivoGastado;
    }

    public double getEfectivoHabitaciones()
    {
        return EfectivoHabitaciones;
    }

    public double getEfectivoTotal()
    {
        return EfectivoTotal;
    }

    public Date getFechaIngEfectivo()
    {
        return FechaIngEfectivo;
    }

    public Date getHoraIngEfectivo()
    {
        return HoraIngEfectivo;
    }

    public DMBaseDatos getDMObjeto()
    {
        return DMObjeto;
    }
    
    
}
