/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Conexion.DMBaseDatos;
import Interfaces.FrmListaSalaConferencias;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WIN7UTL64
 */
public class SalaConferencias 
{
    private int ID;
    private Date FechaRegistro;
    private Date HoraRegistro;
    private String Nombre;
    private int Paquete;
    private Date FechaReservada;
    private String HoraReservada;
    private int TiempoReservado;
    private int Anticipo;
    private double TotalAnticipo;
    private double Total;
    private int CantPersonas;
    private int Empresa;
    private double TotDescuento;
    private double Saldo;
    private DMBaseDatos DMObjeto;
    
    public SalaConferencias()
    {
        this.ID = 0;
        this.FechaRegistro = null;
        this.HoraRegistro = null;
        this.Nombre = null;
        this.Paquete = 0;
        this.FechaReservada = null;
        this.HoraReservada = null;
        this.TiempoReservado = 0;
        this.Anticipo = 0;
        this.TotalAnticipo = 0.0;
        this.Total = 0.0;
        this.CantPersonas = 0;
        this.Empresa = 0;
        this.TotDescuento = 0.0;
        this.Saldo = 0.0;
        DMObjeto = new DMBaseDatos();
    }
    
    public String DameSentenciaSQLInsert()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "INSERT INTO SALACONFERENCIAS (FECHA_REGISTRO, HORA_REGISTRO, NOMBRE, PAQUETE, FECHA_RESERVADA, HORA_RESERVADA, TIEMPO_RESERVADO, ANTICIPO, TOT_ANTICIPO, TOTAL, CANT_PERSONAS, EMPRESA, TOT_DESCUENTO, SALDO) ";
        strSentenciaSQL += " VALUES (";
        strSentenciaSQL += "NOW(), "; //Fecha del Registro
        strSentenciaSQL += "NOW(), "; //Hora del Registro
        strSentenciaSQL += "'" + this.Nombre + "', ";
        strSentenciaSQL += "" + this.Paquete + ", ";        
        strSentenciaSQL += "?, "; //Fecha de Reservación
        strSentenciaSQL += "?, "; //Hora de la Reservación
        strSentenciaSQL += "" + this.TiempoReservado + ", ";
        strSentenciaSQL += "" + this.Anticipo + ", "; 
        strSentenciaSQL += "" + this.TotalAnticipo + ", ";
        strSentenciaSQL += "" + this.Total + ", ";
        strSentenciaSQL += "" + this.CantPersonas + ", ";
        strSentenciaSQL += "" + this.Empresa + ", ";
        strSentenciaSQL += "" + this.TotDescuento + ", ";
        strSentenciaSQL += "" + this.Saldo;
        strSentenciaSQL += ")";
        
        return strSentenciaSQL;
    }
        
    public void RevisionEstados()            
    {
        int iEstadoSala = DameEstadoSalaDeConferencias();
        
        if ( iEstadoSala == 0 ) //Disponible para ser ocupado
        {
            //Revisar l horario de Inicio de cualquiera de los registros para determinar el cambio de estado
            RevisaEstadoCero();
        }
        else if ( iEstadoSala == 1 ) //Ocupado en este momento
        {
            RevisaEstadoUno();
        }
        else if ( iEstadoSala == 3 ) //En Servicio de Limpieza.
        {
            RevisaEstadoTres();
        }
    }
            
    public int DameIDRegistroDeConferencias()
    {
        int iID = 0;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        
        try
        {
            strSentenciaSQL = "SELECT ID_REGISTRO FROM CONFERENCIAS WHERE ID = 1";
            rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);

            if ( rsResultado != null )
            {
                rsResultado.next();
                iID = rsResultado.getInt("ID_REGISTRO");
            }
        }
        catch ( Exception e )
        {
            iID = 0;
        }
        
        return iID;
    }
    
    public void RevisaEstadoTres()
    {        
        int iHorasBD = 0;        
        String strFechaAux = null;
        Date dtFecha = new Date();
        Date dtFechaBD = new Date();
        Format fFormato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cCalendario = Calendar.getInstance();
        ResultSet rsResultado = null;
        
        rsResultado = DameRegistrosDeSC(DameIDRegistroDeConferencias());
        
        if ( rsResultado != null )
        {
            try
            {
                while ( rsResultado.next() )
                {                                        
                    strFechaAux = fFormato.format(dtFechaBD);  
                    strFechaAux += " " + rsResultado.getString("HORA_RESERVADA");
                    dtFechaBD = sdfFormato.parse(strFechaAux);
                    cCalendario.setTime(dtFechaBD);
                    iHorasBD = rsResultado.getInt("TIEMPO_RESERVADO");
                    cCalendario.add(Calendar.HOUR, iHorasBD + 1);
                    dtFechaBD = cCalendario.getTime();
                                        
                    if ( dtFecha.getTime() >= dtFechaBD.getTime() )
                    {
                        CambiaEstadoSalaConferencia(0);                                                
                        break;
                    }
                    else
                    {
                        System.out.println("No coinciden");
                    }
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
    }
    
    public boolean EnviarMantenimientoSalaConferencias()
    {
        boolean bOk = false;
        int iIdRegistro = 0;
        String strSentenciaSQL1 = null;
        String strSentenciaSQL2 = null;
        
        iIdRegistro = DameIDRegistroDeConferencias();
        strSentenciaSQL1 = "UPDATE SALACONFERENCIAS SET ESTADO = 0 WHERE ID = " + iIdRegistro;        
        strSentenciaSQL2 = "UPDATE CONFERENCIAS SET ESTADO = 4, ID_REGISTRO = " + iIdRegistro + " WHERE ID = 1";
            
        if ( iIdRegistro > 0 )
        {           
            bOk = DMObjeto.ActualizaEstadoTresSC(strSentenciaSQL1, strSentenciaSQL2);  
        }
        else
        {
            bOk = DMObjeto.Insertar(strSentenciaSQL2);
        }
        
        return bOk;
    }
    
    public boolean LiberaSalaDeConferencias()
    {
        boolean bOk = false;
        int iIdRegistro = 0;
        String strSentenciaSQL1 = null;
        String strSentenciaSQL2 = null;
        
        iIdRegistro = DameIDRegistroDeConferencias();
        strSentenciaSQL1 = "UPDATE SALACONFERENCIAS SET ESTADO = 0 WHERE ID = " + iIdRegistro;        
        strSentenciaSQL2 = "UPDATE CONFERENCIAS SET ESTADO = 0, ID_REGISTRO = " + iIdRegistro + " WHERE ID = 1";
            
        if ( iIdRegistro > 0 )
        {            
            bOk = DMObjeto.ActualizaEstadoTresSC(strSentenciaSQL1, strSentenciaSQL2);  
        }
        else
        {
            bOk = DMObjeto.Insertar(strSentenciaSQL2);
        } 
        
        return bOk;
    }
    
    public boolean EnviarLimpiezaSalaConferencias()
    {
        boolean bOk = false;
        int iIdRegistro = 0;
        String strSentenciaSQL1 = null;
        String strSentenciaSQL2 = null;
        
        iIdRegistro = DameIDRegistroDeConferencias();
        strSentenciaSQL1 = "UPDATE SALACONFERENCIAS SET ESTADO = 0 WHERE ID = " + iIdRegistro;        
        strSentenciaSQL2 = "UPDATE CONFERENCIAS SET ESTADO = 3, ID_REGISTRO = " + iIdRegistro + " WHERE ID = 1";
            
        if ( iIdRegistro > 0 )
        {            
            bOk = DMObjeto.ActualizaEstadoTresSC(strSentenciaSQL1, strSentenciaSQL2);  
        }
        else
        {
            bOk = DMObjeto.Insertar(strSentenciaSQL2);
        } 
        
        return bOk;
    }
    
    public boolean EntregarSalaConferencias()
    {
        boolean bOk = false;
        int iIdRegistro = 0;
        String strSentenciaSQL1 = null;
        String strSentenciaSQL2 = null;
        
        strSentenciaSQL1 = "UPDATE SALACONFERENCIAS SET ESTADO = 0 WHERE ID = " + iIdRegistro;        
        strSentenciaSQL2 = "UPDATE CONFERENCIAS SET ESTADO = 0, ID_REGISTRO = " + iIdRegistro + " WHERE ID = 1";
            
        if ( iIdRegistro > 0 )
        {            
            bOk = DMObjeto.ActualizaEstadoTresSC(strSentenciaSQL1, strSentenciaSQL2);  
        }
        else
        {
            bOk = DMObjeto.Insertar(strSentenciaSQL2);
        }
        
        return bOk;
    }
    
    public void RevisaEstadoUno()
    {
        int iID = 0;
        int iHorasBD = 0;
        String strSentenciaSQL = null;
        String strSentenciaSQLC = null;
        String strFechaAux = null;
        Date dtFecha = new Date();
        Date dtFechaBD = new Date();
        Date dtFechaBD2 = new Date();
        Format fFormato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");        
        Calendar cCalendario = Calendar.getInstance();
        ResultSet rsResultado = null;
        
        rsResultado = DameRegistrosDisponibles();
        
        if ( rsResultado != null )
        {
            try
            {
                while ( rsResultado.next() )
                {                                     
                    strFechaAux = fFormato.format(dtFechaBD);  
                    dtFechaBD2 = rsResultado.getDate("FECHA_RESERVADA");//sdfFormato.parse(strFechaAux);
                    strFechaAux += " " + rsResultado.getString("HORA_RESERVADA");
                    dtFechaBD = sdfFormato.parse(strFechaAux);
                    cCalendario.setTime(dtFechaBD);
                    iHorasBD = rsResultado.getInt("TIEMPO_RESERVADO");
                    cCalendario.add(Calendar.HOUR, iHorasBD);
                    dtFechaBD = cCalendario.getTime();
                    
                    //Si el día de hoy es mayor o igual a la fecha de finalización Ó La hora actual es mayor a la hora de finalización
                    if ( dtFecha.getTime() >= dtFechaBD2.getTime() || dtFecha.getTime() >= dtFechaBD.getTime() )
                    {                        
                        iID = rsResultado.getInt("ID");                        
                        strSentenciaSQL = "UPDATE SALACONFERENCIAS SET ESTADO = 0 WHERE ID = " + iID;                        
                        strSentenciaSQLC = "UPDATE CONFERENCIAS SET ESTADO = 3, ID_REGISTRO = " + iID + " WHERE ID = 1";                        
                        DMObjeto.ActualizaEstadoTresSC(strSentenciaSQL, strSentenciaSQLC);                       
                        break;
                    }
                    else
                    {
                        System.out.println("No coinciden");
                    }
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }            
    }
    
    public void RevisaEstadoCero()
    {
        String strFechaAux = null;
        Date dtFecha = new Date();
        Date dtFechaBD = new Date();
        Format fFormato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");        
        ResultSet rsResultado = null;
        
        rsResultado = DameRegistrosDisponibles();
        
        if ( rsResultado != null )
        {
            try
            {
                while ( rsResultado.next() )
                {                                        
                    strFechaAux = fFormato.format(dtFechaBD);  
                    strFechaAux += " " + rsResultado.getString("HORA_RESERVADA");
                    dtFechaBD = sdfFormato.parse(strFechaAux);
                    
                    if ( dtFecha.getTime() >= dtFechaBD.getTime() )
                    {
                        CambiaEstadoSalaConferencia(1);
                        FrmListaSalaConferencias frm = new FrmListaSalaConferencias();
                        frm.LlenaSalaDeConferencias();
                        break;
                    }
                    else
                    {
                        System.out.println("No coinciden");
                    }
                }
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
    }
    
    public void CambiaEstadoSalaConferencia(int iEstado)
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "UPDATE CONFERENCIAS SET ESTADO = " + iEstado + " WHERE ID = 1";
        DMObjeto.Insertar(strSentenciaSQL);
    }
    
    public ResultSet DameRegistrosDeSC(int iID)
    {
        ResultSet rsResultado = null;       
        String strSentenciaSQL = "SELECT * FROM SALACONFERENCIAS WHERE ID = " + iID;
        
        //rsResultado = DMObjeto.DameRegistrosPorFechaSC(strSentenciaSQL, dtFecha);
        rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);
        
        return rsResultado;
    }
    
    public ResultSet DameRegistrosDisponibles()
    {
        Date dtFecha = new Date();
        ResultSet rsResultado = null;
        //String strSentenciaSQL = "SELECT * FROM SALACONFERENCIAS WHERE FECHA_RESERVADA = ? AND ESTADO = 1";
        String strSentenciaSQL = "SELECT * FROM SALACONFERENCIAS WHERE ESTADO = 1";
        
        //rsResultado = DMObjeto.DameRegistrosPorFechaSC(strSentenciaSQL, dtFecha);
        rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);
        
        return rsResultado;
    }
    
    public int DameEstadoSalaDeConferencias()
    {
        int iEstado = 0;
        String strSentenciaSQL = "SELECT ESTADO FROM CONFERENCIAS WHERE ID = 1";
        ResultSet rsResultado = null;
        
        try
        {
            rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                iEstado = rsResultado.getInt("ESTADO");
            }
        }
        catch ( Exception e )
        {
            iEstado = 0;
            e.printStackTrace();
        }
        
        return iEstado;
    }
    
    public boolean FechaReservada(Date dtFecha)
    {
        boolean bOk = true;
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM SALACONFERENCIAS WHERE FECHA_RESERVADA = ?";
        bOk = DMObjeto.ExisteFechaReservada(strSentenciaSQL, dtFecha);
        
        return bOk;
    }
    
    public boolean Insertar()
    {
        boolean bOk = true;
        
        bOk = DMObjeto.InsertaSalaConferencias(DameSentenciaSQLInsert(), this.FechaReservada, this.HoraReservada);
        
        return bOk;
    }

    public void setID(int ID) 
    {
        this.ID = ID;
    }

    public void Prueba()
    {
        DMObjeto.Prueba();
    }
    
    public void setFechaRegistro(Date FechaRegistro) 
    {
        this.FechaRegistro = FechaRegistro;
    }

    public void setHoraRegistro(Date HoraRegistro) 
    {
        this.HoraRegistro = HoraRegistro;
    }

    public void setNombre(String Nombre) 
    {
        this.Nombre = Nombre;
    }

    public void setPaquete(int Paquete) 
    {
        this.Paquete = Paquete;
    }

    public void setFechaReservada(Date FechaReservada) 
    {
        this.FechaReservada = FechaReservada;
    }

    public void setHoraReservada(String HoraReservada) 
    {
        this.HoraReservada = HoraReservada;
    }

    public void setTiempoReservado(int TiempoReservado) 
    {
        this.TiempoReservado = TiempoReservado;
    }

    public void setAnticipo(int Anticipo) 
    {
        this.Anticipo = Anticipo;
    }

    public void setTotalAnticipo(double TotalAnticipo) 
    {
        this.TotalAnticipo = TotalAnticipo;
    }

    public void setTotal(double Total) 
    {
        this.Total = Total;
    }

    public void setCantPersonas(int CantPersonas) 
    {
        this.CantPersonas = CantPersonas;
    }

    public void setEmpresa(int Empresa) 
    {
        this.Empresa = Empresa;
    }

    public void setTotDescuento(double TotDescuento) 
    {
        this.TotDescuento = TotDescuento;
    }

    public void setSaldo(double Saldo) 
    {
        this.Saldo = Saldo;
    }

    public int getID() 
    {
        return ID;
    }

    public Date getFechaRegistro() 
    {
        return FechaRegistro;
    }

    public Date getHoraRegistro() 
    {
        return HoraRegistro;
    }

    public String getNombre() 
    {
        return Nombre;
    }

    public int getPaquete() 
    {
        return Paquete;
    }

    public Date getFechaReservada() 
    {
        return FechaReservada;
    }

    public String getHoraReservada() 
    {
        return HoraReservada;
    }

    public int getTiempoReservado() 
    {
        return TiempoReservado;
    }

    public int getAnticipo()
    {
        return Anticipo;
    }

    public double getTotalAnticipo() 
    {
        
        return TotalAnticipo;
    }

    public double getTotal() 
    {
        return Total;
    }

    public int getCantPersonas() 
    {
        return CantPersonas;
    }

    public int getEmpresa() 
    {
        return Empresa;
    }

    public double getTotDescuento() 
    {
        return TotDescuento;
    }

    public double getSaldo() 
    {
        return Saldo;
    }        

    public boolean ChoqueDeHoraReservada(Date dtFechaReservada, String strHoraReservada, int iHorasReservadas)
    {
        boolean bOk = true;
        
        bOk = DMObjeto.ChocaConHoraReservada(dtFechaReservada, strHoraReservada, iHorasReservadas);
        
        return bOk;
    }
    
    public int DameRegistrosReservados(DefaultTableModel dmtModelo, String[] vColumnas)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;
        
        try
        {            
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM SALACONFERENCIAS WHERE ESTADO = 1";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;            
            strSentenciaSQL = "SELECT ID, NOMBRE, FECHA_RESERVADA, HORA_RESERVADA, TIEMPO_RESERVADO  FROM SALACONFERENCIAS WHERE ESTADO = 1";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][5];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("NOMBRE");
                objTuplas[iContadorAux][2] = rsEstado.getString("FECHA_RESERVADA");
                objTuplas[iContadorAux][3] = rsEstado.getString("HORA_RESERVADA");
                objTuplas[iContadorAux][4] = rsEstado.getString("TIEMPO_RESERVADO");                
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
