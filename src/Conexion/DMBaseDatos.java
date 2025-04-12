/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexion;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import sun.misc.JavaxSecurityAuthKerberosAccess;

/**
 *
 * @author WIN7UTL64
 */
public class DMBaseDatos 
{
    private Conexion cConexion;
    private Connection cCon;    
    private Statement stEstado = null;   
    
    public DMBaseDatos()
    {        
        IniciaObjetosConexion();      
    }
    
    public boolean RealizaCheckOutParaHabitacion(String strSentenciaSQLHospedaje, String strSentenciaSQLHabitacion)
    {
        boolean bOk = false;                  
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);            
            stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);            
            stEstado.executeUpdate(strSentenciaSQLHospedaje);            
            stEstado.executeUpdate(strSentenciaSQLHabitacion);            
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }                              
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;
    }                
    
    public void Prueba()
    {        
        PreparedStatement pst = null;
        ResultSet rsResultado = null;
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);                        
            pst = cCon.prepareStatement("SELECT * FROM HOSPEDAJES WHERE HORA = ?");            
            pst.setString(1, "13:14:23");
            //pst.executeUpdate();                        
            rsResultado = pst.executeQuery();
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                System.out.println("ID: " + rsResultado.getInt("ID"));
            }
        }
        catch ( Exception e )
        {
            
        }
    }        
    
    public boolean InsertaConUnaFecha(String strSentenciaSQL, Date dtFecha)
    {
        boolean bOk = false;          
        PreparedStatement pst = null;        
        java.sql.Date sqldtFechaInicio = null;
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);                     
            pst = cCon.prepareStatement(strSentenciaSQL);            
            sqldtFechaInicio = new java.sql.Date(dtFecha.getTime());                        
            pst.setDate(1, sqldtFechaInicio);            
            pst.executeUpdate();                        
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }
                
                if ( pst != null )
                {
                    pst.close();
                }
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;
    }
    
    public boolean InsertaSalaConferencias(String strSentenciaSQL, Date dtFechaReservacion, String strHoraReservacion)
    {
        boolean bOk = false;          
        PreparedStatement pst = null;
        String strFechaAux = null;
        String strSentenciaSQLAux = null;
        Format fFormato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date sqldtFechaInicio = null;        
        
        try
        {
            IniciaConexionSiEstaCerrada();
            //strSentenciaSQLAux = "UPDATE CONFERENCIAS SET ESTADO = 1 WHERE ID = 1";
            cCon.setAutoCommit(false);         
            //stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //stEstado.executeUpdate(strSentenciaSQLAux);
            pst = cCon.prepareStatement(strSentenciaSQL);
            strFechaAux = fFormato.format(dtFechaReservacion);  
            strFechaAux += " " + strHoraReservacion;
            dtFechaReservacion = sdfFormato.parse(strFechaAux);
            sqldtFechaInicio = new java.sql.Date(dtFechaReservacion.getTime());                        
            pst.setDate(1, sqldtFechaInicio);
            pst.setString(2, strHoraReservacion);
            pst.executeUpdate();                        
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }
                
                if ( pst != null )
                {
                    pst.close();
                }
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;
    }
    
    public boolean EnviaHabitacionMantenimiento(int iIdHospedaje, int iIdHabitacion, String strObservaciones)
    {
        boolean bOk = false;
        String strSentenciaSQLHospedaje = null;
        String strSentenciaSQLHabitacion = null;
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);            
            stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //0: Habitación Disponible, 1: Habitación Ocupada, 2: Habitación Reservada, 3: Habitación en Servicio
            strSentenciaSQLHabitacion = "UPDATE HABITACIONES SET ESTADO = 3 WHERE ID = " + iIdHabitacion;
            //0: Habitación libre en ese momento, 1: Habitación ocupada
            strSentenciaSQLHospedaje = "UPDATE HOSPEDAJES SET ESTADO = 0, OBSERVACIONES = '" + strObservaciones + "' WHERE ID = " + iIdHospedaje;
            stEstado.executeUpdate(strSentenciaSQLHospedaje);            
            stEstado.executeUpdate(strSentenciaSQLHabitacion);            
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            bOk = false;
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            bOk = false;
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }                                
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;
    }
    
    public boolean ActualizaEstadoTresSC(String strSentenciaSQLCon, String strSentenciaSQLSC)
    {
        boolean bOk = false;                  
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);            
            stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);            
            stEstado.executeUpdate(strSentenciaSQLCon);            
            stEstado.executeUpdate(strSentenciaSQLSC);                               
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }                                
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;    
    }
    
    public double DameEfectivoActualDeHabEnCaja()
    {        
        double dTotal = 0.0;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        Statement stEstadoAux = null;
        
        try
        {
            strSentenciaSQL = "SELECT EFECTIVO_HAB FROM CAJA WHERE CORTE_CAJA_HECHO = 0";
            stEstadoAux = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rsResultado = stEstadoAux.executeQuery(strSentenciaSQL);
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                dTotal = rsResultado.getDouble("EFECTIVO_HAB");
            }
        }
        catch ( Exception e )
        {
            dTotal = 0.0;
        }
        
        return dTotal;
    }

    public double DameEfectivoActualDeGastosEnCaja()
    {        
        double dTotal = 0.0;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        Statement stEstadoAux = null;
        
        try
        {
            strSentenciaSQL = "SELECT EFECTIVO_GASTADO FROM CAJA WHERE CORTE_CAJA_HECHO = 0";
            stEstadoAux = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rsResultado = stEstadoAux.executeQuery(strSentenciaSQL);
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                dTotal = rsResultado.getDouble("EFECTIVO_GASTADO");
            }
        }
        catch ( Exception e )
        {
            dTotal = 0.0;
        }
        
        return dTotal;
    }
    
    public double DameEfectivoIngresadoInicTurnoEnCaja()
    {        
        double dTotal = 0.0;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        Statement stEstadoAux = null;
        
        try
        {
            strSentenciaSQL = "SELECT EFECTIVO_INGRESADO FROM CAJA WHERE CORTE_CAJA_HECHO = 0";
            stEstadoAux = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rsResultado = stEstadoAux.executeQuery(strSentenciaSQL);
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                dTotal = rsResultado.getDouble("EFECTIVO_INGRESADO");
            }
        }
        catch ( Exception e )
        {
            dTotal = 0.0;
        }
        
        return dTotal;
    }
    
    public double DameEfectivoActualTotalEnCaja()
    {        
        double dTotal = 0.0;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        Statement stEstadoAux = null;
        
        try
        {
            strSentenciaSQL = "SELECT EFECTIVO_TOTAL FROM CAJA WHERE CORTE_CAJA_HECHO = 0";
            stEstadoAux = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rsResultado = stEstadoAux.executeQuery(strSentenciaSQL);
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                dTotal = rsResultado.getDouble("EFECTIVO_TOTAL");
            }
        }
        catch ( Exception e )
        {
            dTotal = 0.0;
        }
        
        return dTotal;
    }
    
    public String DameSentenciaActualizacionCajaEnTurno(double dEfectivoIngresadoRentaHabitacion)
    {
        String strSentenciaSQL = null;
        double dTotal = 0.0;
        double dTotalHabitacion = 0.0;
        
        dTotalHabitacion = dEfectivoIngresadoRentaHabitacion + DameEfectivoActualDeHabEnCaja();
        dTotal = ( DameEfectivoIngresadoInicTurnoEnCaja() + dTotalHabitacion ) - DameEfectivoActualDeGastosEnCaja();
        strSentenciaSQL = "UPDATE CAJA SET EFECTIVO_HAB = " + dTotalHabitacion + ", EFECTIVO_TOTAL = " + dTotal + " WHERE CORTE_CAJA_HECHO = 0";
        
        return strSentenciaSQL;
    }
    
    public boolean InsertaGastosYActualizaCaja(String strSentenciaSQL, String strSentenciaSQLCaja)
    {
        boolean bOk = false;          
        PreparedStatement pst = null;        
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);            
            stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);            
            stEstado.executeUpdate(strSentenciaSQL);
            stEstado.executeUpdate(strSentenciaSQLCaja);            
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }
                
                if ( pst != null )
                {
                    pst.close();
                }
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;  
    }
    
    public boolean RestauraBDAFabrica()
    {
        boolean bOk = false;          
        PreparedStatement pst = null;        
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);            
            stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);            
            stEstado.executeUpdate("UPDATE HABITACIONES SET ESTADO = 0;");            
            stEstado.executeUpdate("DELETE FROM BITACORA;");
            stEstado.executeUpdate("DELETE FROM CONFIGURACION;");            
            stEstado.executeUpdate("DELETE FROM DESCRIPCIONESPAQUETES;");
            stEstado.executeUpdate("DELETE FROM DESCUENTOS;");
            stEstado.executeUpdate("DELETE FROM HUESPEDES;");
            stEstado.executeUpdate("DELETE FROM PAQUETES;");                        
            stEstado.executeUpdate("DELETE FROM TURNOS;");
            stEstado.executeUpdate("DELETE FROM SALACONFERENCIAS;");
            stEstado.executeUpdate("DELETE FROM HOSPEDAJES;");
            stEstado.executeUpdate("DELETE FROM CAJA;");
            stEstado.executeUpdate("DELETE FROM GASTOS;");
            stEstado.executeUpdate("DELETE FROM USUARIOS;");
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }
                
                if ( pst != null )
                {
                    pst.close();
                }
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;  
    }
    
    public boolean ActualizaDosTablas(String strSentenciaSQLHospedaje, String strSentenciaSQLHabitacion, Timestamp dtFechaInicio, Date dtFechaFin, double dEfectivoIngresadoRentaHabitacion)
    {
        boolean bOk = false;          
        PreparedStatement pst = null;
        java.sql.Date sqldtFechaInicio;
        java.sql.Date sqldtFechaFin;
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);            
            stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);                        
            stEstado.executeUpdate(strSentenciaSQLHabitacion);
            stEstado.executeUpdate(DameSentenciaActualizacionCajaEnTurno(dEfectivoIngresadoRentaHabitacion));
            pst = cCon.prepareStatement(strSentenciaSQLHospedaje);
            sqldtFechaInicio = new java.sql.Date(dtFechaInicio.getTime());
            sqldtFechaFin = new java.sql.Date(dtFechaFin.getTime());
            pst.setDate(1, sqldtFechaInicio);
            pst.setDate(2, sqldtFechaFin);
            pst.executeUpdate();                        
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }
                
                if ( pst != null )
                {
                    pst.close();
                }
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;
    }
    
    public boolean ActualizaTresTablas(String strSentenciaSQLHuespedes, String strSentenciaSQLHospedaje, String strSentenciaSQLHabitacion, Timestamp dtFechaInicio, Date dtFechaFin, double dEfectivoIngresadoRentaHabitacion)
    {
        boolean bOk = false;          
        PreparedStatement pst = null;
        java.sql.Date sqldtFechaInicio;
        java.sql.Date sqldtFechaFin;
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);            
            stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);            
            stEstado.executeUpdate(strSentenciaSQLHuespedes);            
            stEstado.executeUpdate(strSentenciaSQLHabitacion);
            stEstado.executeUpdate(DameSentenciaActualizacionCajaEnTurno(dEfectivoIngresadoRentaHabitacion));
            pst = cCon.prepareStatement(strSentenciaSQLHospedaje);
            sqldtFechaInicio = new java.sql.Date(dtFechaInicio.getTime());
            sqldtFechaFin = new java.sql.Date(dtFechaFin.getTime());
            pst.setDate(1, sqldtFechaInicio);
            pst.setDate(2, sqldtFechaFin);
            pst.executeUpdate();                        
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }
                
                if ( pst != null )
                {
                    pst.close();
                }
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;
    }
    
    public void IniciaConexionSiEstaCerrada() throws SQLException
    {
        if ( cCon.isClosed() )
        {
            IniciaObjetosConexion();
        }
    }
    
    public void IniciaObjetosConexion()
    {
         this.cConexion = new Conexion();
         cCon = cConexion.DameConexion();   
    }
    
    
    
    public boolean Insertar(String strSentenciaSQL)
    {
        boolean bOk = false;                                
        
        try
        {    
            IniciaConexionSiEstaCerrada();
            cCon.setAutoCommit(false);            
            stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stEstado.executeUpdate(strSentenciaSQL);
            cCon.commit();          
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;
    }
    
    public void CerrarConexion()
    {      
        try
        {
            if ( stEstado != null )
            {
                stEstado.close();
            }
            
            if ( cCon != null )
            {
                cCon.close();
            }
            
            if ( cConexion != null )
            {
                cConexion.CerrarConexion();
            }
            
            
        }
        catch ( Exception e )
        {
            
        }
    }                
    
    public ResultSet DameUnDato(String strSentenciaSQL)
    {        
        ResultSet rsResultado = null;
        
        try
        {
            IniciaConexionSiEstaCerrada();
            stEstado = cCon.createStatement();
            rsResultado = stEstado.executeQuery(strSentenciaSQL);
        }
        catch ( SQLException e )
        {            
        }
        
        return rsResultado;
    }           
    
    public ResultSet DameRegistrosPorFechaSC(String strSentenciaSQL, Date dtFecha)
    {        
        PreparedStatement pst = null;
        ResultSet rsResultado = null;
        java.sql.Date sqldtFechaInicio;
        
        try
        {
            IniciaConexionSiEstaCerrada();
            pst = cCon.prepareStatement(strSentenciaSQL);
            sqldtFechaInicio = new java.sql.Date(dtFecha.getTime());
            pst.setDate(1, sqldtFechaInicio);
            rsResultado = pst.executeQuery();                        
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        return rsResultado;
    }
    
    public boolean ExisteFechaReservada(String strSentenciaSQL, Date dtFecha)
    {
        boolean bOk = true;
        PreparedStatement pst = null;
        ResultSet rsResultado = null;
        java.sql.Date sqldtFechaInicio;
        
        try
        {
            IniciaConexionSiEstaCerrada();
            pst = cCon.prepareStatement(strSentenciaSQL);
            sqldtFechaInicio = new java.sql.Date(dtFecha.getTime());
            pst.setDate(1, sqldtFechaInicio);
            rsResultado = pst.executeQuery();
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                bOk = ( rsResultado.getInt("TOTAL") > 0 );
            }
        }
        catch ( Exception e )
        {
            
        }
        
        return bOk;
    }
    public boolean ChocaConHoraReservada(Date dtFechaFinEventoAReservar, String strHoraReservada, int iHorasReservadas)
    {
        boolean bOk = true;
        int iHorasBD = 0;
        String strSentenciaSQL = null;
        String strFechaBD = null;
        String strFechaIngresada = null;
        Date dtFechaAux = null;
        Date dtFechaOriginalAReservar = null;
        Date dtFechaInicioEventoBD = null;
        Date dtFechaFinEventoBD = null;        
        Calendar cCalendario = Calendar.getInstance();
        Format fFormato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        PreparedStatement pst = null;
        ResultSet rsResultado = null;            
        java.sql.Date sqldtFechaInicio;                
        
        try
        {                       
            //strSentenciaSQL = "SELECT HORA_RESERVADA FROM SALACONFERENCIAS WHERE FECHA_RESERVADA = ? AND HORA_RESERVADA = ?";
            strSentenciaSQL = "SELECT HORA_RESERVADA, TIEMPO_RESERVADO FROM SALACONFERENCIAS WHERE FECHA_RESERVADA = ? ORDER BY HORA_RESERVADA DESC";
            IniciaConexionSiEstaCerrada();
            pst = cCon.prepareStatement(strSentenciaSQL);         
            sqldtFechaInicio = new java.sql.Date(dtFechaFinEventoAReservar.getTime());
            pst.setDate(1, sqldtFechaInicio);            
            rsResultado = pst.executeQuery();
            
            if ( rsResultado != null )
            {
                while ( rsResultado.next() && bOk )
                {
                    //bOk = true;
                    iHorasBD = rsResultado.getInt("TIEMPO_RESERVADO");
                    dtFechaInicioEventoBD = dtFechaFinEventoAReservar;
                    strFechaIngresada = fFormato.format(dtFechaFinEventoAReservar);  
                    strFechaIngresada += " " + strHoraReservada;
                    dtFechaFinEventoAReservar = sdfFormato.parse(strFechaIngresada);  //Fecha y Hora a reservar para comparar.
                    dtFechaOriginalAReservar = dtFechaFinEventoAReservar;
                    cCalendario.setTime(dtFechaFinEventoAReservar);
                    cCalendario.add(Calendar.HOUR, iHorasReservadas + 1);
                    dtFechaFinEventoAReservar = cCalendario.getTime();
                    strFechaBD = fFormato.format(dtFechaInicioEventoBD);
                    strFechaBD += " " + rsResultado.getString("HORA_RESERVADA");
                    dtFechaInicioEventoBD = sdfFormato.parse(strFechaBD);  //Fecha y Hora a reservar para comparar.
                    dtFechaFinEventoBD = dtFechaInicioEventoBD;
                    cCalendario.setTime(dtFechaFinEventoBD);
                    cCalendario.add(Calendar.HOUR, iHorasBD + 1);
                    dtFechaFinEventoBD = cCalendario.getTime();
                    
                    //Falta agregar comparación con la Hora de apertura y cierre de la Sala de conferencias.
                    /*if ( !( dtFechaOriginalAReservar.getTime() == dtFechaInicioEventoBD.getTime() )    && 
                         ( ( dtFechaFinEventoAReservar.getTime() < dtFechaInicioEventoBD.getTime() )         ||                          
                           ( dtFechaOriginalAReservar.getTime() > dtFechaFinEventoBD.getTime() ) ) )                       
                    {
                        bOk = false;                        
                    }*/               
                    
                    /*
                    Hay que validar contra la Fecha más alta de la base de datos (Inicio y Fin) y Contra el Inicio de la Fecha más baja.
                    */
                    if ( !( dtFechaOriginalAReservar.getTime() == dtFechaInicioEventoBD.getTime() ) )
                    {                                                
                        if ( rsResultado.isLast() )
                        {
                            if ( dtFechaFinEventoAReservar.getTime() < dtFechaInicioEventoBD.getTime() )
                            {
                                bOk = false;
                            }
                        }
                        else if ( rsResultado.isFirst() )
                        {
                            if ( dtFechaOriginalAReservar.getTime() > dtFechaFinEventoBD.getTime() )
                            {
                                bOk = false;
                            }
                            else if ( dtFechaOriginalAReservar.getTime() > dtFechaInicioEventoBD.getTime() ) 
                            {
                                break;
                            }
                            else
                            {
                                dtFechaAux = dtFechaInicioEventoBD;
                            }
                        }
                        else 
                        {
                            if ( ( dtFechaOriginalAReservar.getTime() > dtFechaFinEventoBD.getTime() ) && 
                                   ( dtFechaFinEventoAReservar.getTime() < dtFechaAux.getTime() ) )
                            {
                                bOk = false;
                            }
                            else
                            {
                                dtFechaAux = dtFechaInicioEventoBD;
                            }
                        }
                    }                    
                }
            }
            else
            {
                bOk = false;
            }
        }
        catch ( Exception e )
        {
            
        }
        
        return bOk;
        
    }

    public boolean GeneraCorteAlDia()
    {
        boolean bOk = false;                  
        
        try
        {
            IniciaConexionSiEstaCerrada();            
            cCon.setAutoCommit(false);            
            stEstado = cCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);            
            stEstado.executeUpdate("UPDATE CAJA SET CORTE_CAJA_HECHO = 1, FECHA_CORTE = NOW(), HORA_CORTE = NOW() WHERE CORTE_CAJA_HECHO = 0");            
            stEstado.executeUpdate("UPDATE HOSPEDAJES SET REVISADO = 1 WHERE REVISADO = 0");            
            cCon.commit();      
            bOk = true;
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
            
            try
            {
                if( cCon != null )
                {
                    cCon.rollback();
                }
            }
            catch ( SQLException se2 )
            {
                se2.printStackTrace();
            }
        }
        catch ( Exception e3 )
        {
            e3.printStackTrace();
        }
        finally
        {
            try
            {
                if( stEstado != null )
                {
                    stEstado.close();
                }                                
            }
            catch(SQLException se2)
            {
            }
            
            try
            {
                if ( cCon != null )
                {
                    cCon.close();
                }
            }
            catch ( SQLException se )
            {
                se.printStackTrace();
            }
        }
        
        return bOk;
    }
}
