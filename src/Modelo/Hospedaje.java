/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Conexion.DMBaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WIN7UTL64
 */
public class Hospedaje 
{
    private int Id;
    private int IdHuesped;
    private int IdHabitacion;
    private int Dias;
    private int FormaPago;
    private int TotalAdultos; //Número de Adultos que entrarán en la habitación.
    private int TotalNinios; //Número de Niños que entrarán en la habitación.
    private int TotalOcupantes; //Total de personas que ocuparán la habitación (Adultos y Niños).
    private int RealizoAbono; //Si realizó algún abono para la habitación.
    //private Date FechaEntrada;
    private Timestamp FechaEntrada;
    private Date FechaSalida;
    private int Mes;
    private int Anio;
    private double Total;
    private String Observaciones;
    private double TotalAbono; //Total del Abono realizado.
    private int Estado; //0: Habitación libre en ese momento, 1: Habitación ocupada, 2: Habitacion reservada
    private int CtrlAire;
    private int CtrlTv;
    private int Llave;
    private int Revisado;
    private int IdUsuario;
    private Huesped Cliente;
    private Habitacion Cuarto;
    private DMBaseDatos DMObjeto;
    
    public Hospedaje()
    {
        this.DMObjeto = new DMBaseDatos();
        this.Cliente = new Huesped();
        this.Cuarto = new Habitacion();
    }
    
    public int DameIDNuevo()
    {
        int iIdNuevo = 0;
        
        iIdNuevo = DameMaximoID();
        iIdNuevo++;
        
        return iIdNuevo;
    }
    
    public int DameMaximoID()
    {
        int iMaximoId = 1;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        
        try
        {
            strSentenciaSQL = "SELECT MAX(ID) AS MAXIMO FROM HOSPEDAJES";
            rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);        
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                iMaximoId = rsResultado.getInt("MAXIMO");                
            }                
        }
        catch ( Exception e )
        {
            iMaximoId = 1;
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
            
        return iMaximoId;
    }
    
    public boolean Insertar(boolean bOcupado)
    {
        boolean bOk = true;
        
        String strSentenciaSQLHuespedes = null;        
        String strSentenciaSQLHospedaje = null;
        String strSentenciaSQLHabitaciones = null;
        
        strSentenciaSQLHuespedes = this.Cliente.DameSentenciaParaInsercion();
        this.IdHuesped = this.Cliente.getId();
        strSentenciaSQLHospedaje = DameSentenciaParaInsercion();
        //strSentenciaSQLHabitaciones = this.Cuarto.DameSentenciaParaActualizarEdo(bOcupado, this.Cuarto.getId(), this.Id);
        strSentenciaSQLHabitaciones = this.Cuarto.DameSentenciaParaActualizarEdo(bOcupado, this.Cuarto.getId());
        bOk = DMObjeto.ActualizaTresTablas(strSentenciaSQLHuespedes, strSentenciaSQLHospedaje, strSentenciaSQLHabitaciones, this.FechaEntrada, this.FechaSalida, this.Total);
        
        return bOk;
    }
    
    public boolean InsertarDeHabitacionReservada()
    {
        boolean bOk = true;
                
        String strSentenciaSQLHospedaje = null;
        String strSentenciaSQLHabitaciones = null;
                
        this.IdHuesped = this.Cliente.getId();
        strSentenciaSQLHospedaje = DameSentenciaParaInsercion();        
        strSentenciaSQLHabitaciones = this.Cuarto.DameSentenciaParaActEdoHabDeReservacion(this.Cuarto.getId());
        bOk = DMObjeto.ActualizaDosTablas(strSentenciaSQLHospedaje, strSentenciaSQLHabitaciones, this.FechaEntrada, this.FechaSalida, this.Total);
        
        return bOk;   
    }
    
    public boolean LiberaHospedaje(int iID, int iHabitacion)
    {
        boolean bOk = true;
        String strSentenciaSQLHospedaje = null;
        String strSentenciaSQLHabitaciones = null;        
        
        strSentenciaSQLHospedaje = "UPDATE HOSPEDAJES SET ESTADO = 0 WHERE ID = " + iID;        
        strSentenciaSQLHabitaciones = this.Cuarto.DameSentenciaParaActualizarEdo2(false, iHabitacion);
        bOk = DMObjeto.RealizaCheckOutParaHabitacion(strSentenciaSQLHospedaje, strSentenciaSQLHabitaciones);
        
        return bOk;
    }
    
    public int DameRegistrosCnsHospedajesHoy(DefaultTableModel dmtModelo, String[] vColumnas)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;       
        
        try
        {
            //strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM HOSPEDAJES WHERE F_ENTRADA = DATE(NOW())";
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM HOSPEDAJES WHERE FECHA_REGISTRO = DATE(NOW()) AND REVISADO = 0";
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;
            strSentenciaSQL = "SELECT H.ID, CONCAT(C.NOMBRE, ' ', C.AP_PATERNO, ' ', C.AP_MATERNO) AS NOMBRE, H.HABITACION, H.DIAS, H.F_ENTRADA, H.F_SALIDA, H.OBSERVACIONES, H.ESTADO, H.TOTAL, H.ABONO, H.TOTAL_ABONO FROM HOSPEDAJES H LEFT JOIN HUESPEDES C ON H.HUESPED = C.ID  WHERE FECHA_REGISTRO = DATE(NOW()) AND REVISADO = 0";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][11];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("H.ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("NOMBRE");
                objTuplas[iContadorAux][2] = rsEstado.getInt("H.HABITACION");
                objTuplas[iContadorAux][3] = FormateaEstado(rsEstado.getInt("H.ESTADO"));
                objTuplas[iContadorAux][4] = rsEstado.getInt("H.DIAS");
                objTuplas[iContadorAux][5] = rsEstado.getString("H.F_ENTRADA");
                objTuplas[iContadorAux][6] = rsEstado.getString("H.F_SALIDA");                
                objTuplas[iContadorAux][7] = rsEstado.getDouble("H.TOTAL");
                
                if ( rsEstado.getInt("H.ABONO") == 0 )
                {
                    objTuplas[iContadorAux][8] = "SI";
                }
                else
                {
                    objTuplas[iContadorAux][8] = "NO";
                }
                
                objTuplas[iContadorAux][9] = rsEstado.getDouble("H.TOTAL_ABONO");
                objTuplas[iContadorAux][10] = rsEstado.getString("H.OBSERVACIONES");                
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
    
    public int DameRegistrosCnsHospedajes(DefaultTableModel dmtModelo, String[] vColumnas)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;       
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM HOSPEDAJES WHERE REVISADO = 0";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;
            strSentenciaSQL = "SELECT H.ID, CONCAT(C.NOMBRE, ' ', C.AP_PATERNO, ' ', C.AP_MATERNO) AS NOMBRE, H.HABITACION, H.DIAS, H.F_ENTRADA, H.F_SALIDA, H.OBSERVACIONES, H.ESTADO, H.TOTAL, H.ABONO, H.TOTAL_ABONO FROM HOSPEDAJES H LEFT JOIN HUESPEDES C ON H.HUESPED = C.ID WHERE REVISADO = 0";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][11];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("H.ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("NOMBRE");
                objTuplas[iContadorAux][2] = rsEstado.getInt("H.HABITACION");
                objTuplas[iContadorAux][3] = FormateaEstado(rsEstado.getInt("H.ESTADO"));
                objTuplas[iContadorAux][4] = rsEstado.getInt("H.DIAS");
                objTuplas[iContadorAux][5] = rsEstado.getString("H.F_ENTRADA");
                objTuplas[iContadorAux][6] = rsEstado.getString("H.F_SALIDA");                
                objTuplas[iContadorAux][7] = rsEstado.getDouble("H.TOTAL");
                
                if ( rsEstado.getInt("H.ABONO") == 0 )
                {
                    objTuplas[iContadorAux][8] = "SI";
                }
                else
                {
                    objTuplas[iContadorAux][8] = "NO";
                }
                
                objTuplas[iContadorAux][9] = rsEstado.getDouble("H.TOTAL_ABONO");
                objTuplas[iContadorAux][10] = rsEstado.getString("H.OBSERVACIONES");                                
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
    
    public String FormateaEstado(int iEstado)
    {
        String strEstado = null;
        
        if ( iEstado == 0 )
        {
            strEstado = "DESOCUPADO";
        }
        else
        {
            strEstado = "OCUPADO";
        }
        
        return strEstado;
    }
    
    public int DameUltimoIDHospedado()
    {
        int iMaximoId = 1;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        
        try
        {
            strSentenciaSQL = "SELECT MAX(ID) AS MAXIMO FROM HOSPEDAJES";
            rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);        
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                iMaximoId = rsResultado.getInt("MAXIMO");                
            }                
        }
        catch ( Exception e )
        {
            iMaximoId = 1;
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
            
        return iMaximoId;
    }
    
    public boolean FechaSalidaEsLaActualParaHabitacion(int iIdHabitacion, Date dtFecha)
    {
        boolean bOk = false;
        Date dtFechaSalida = null;
        
        if ( ExisteHospedajeConEsaHabitacion(iIdHabitacion) )
        {
            dtFechaSalida = DameFechaSalidaHabitacion(iIdHabitacion);
            
            if ( dtFechaSalida != null )
            {
                bOk = ( dtFecha.compareTo(dtFechaSalida) == 0 );
            }
        }
        
        return bOk;
    }
    
    public boolean InstanciaObjetoPorHabitacion(int iIdHabitacion)
    {
        boolean bOk = false;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        
        strSentenciaSQL = "SELECT * FROM HOSPEDAJES WHERE HABITACION = " + iIdHabitacion + " AND ESTADO = 1";
        rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);
        
        try
        {
            if ( rsResultado != null )
            {
                rsResultado.next();
                this.Id = rsResultado.getInt("ID");
                this.IdHuesped = rsResultado.getInt("HUESPED");
                this.IdHabitacion = rsResultado.getInt("HABITACION");
                this.Dias = rsResultado.getInt("DIAS");
                this.FormaPago = rsResultado.getInt("FORMA_PAGO");
                this.TotalAdultos = rsResultado.getInt("TOT_ADULTOS");
                this.TotalNinios = rsResultado.getInt("TOT_NINIOS");
                this.TotalOcupantes = rsResultado.getInt("TOT_OCUPANTES");
                this.RealizoAbono = rsResultado.getInt("ABONO");
                this.FechaEntrada = rsResultado.getTimestamp("F_ENTRADA");
                this.FechaSalida = rsResultado.getDate("F_SALIDA");
                this.Mes = rsResultado.getInt("MES");
                this.Anio = rsResultado.getInt("ANIO");
                this.Total = rsResultado.getDouble("TOTAL");
                this.Observaciones = rsResultado.getString("OBSERVACIONES");
                this.TotalAbono = rsResultado.getDouble("TOTAL_ABONO");
                this.Estado = rsResultado.getInt("ESTADO");
                this.CtrlAire = rsResultado.getInt("CTRL_AIRE");
                this.CtrlTv = rsResultado.getInt("CTRL_TV");
                this.Llave = rsResultado.getInt("LLAVE");
                this.Revisado = rsResultado.getInt("REVISADO");
                this.IdUsuario = rsResultado.getInt("ID_USUARIO");
                this.Cliente.InstanciaHuesped(this.IdHuesped);
                this.Cuarto = this.Cuarto.DameHabitacionInstanciada(this.IdHabitacion);
                bOk = true;
            }
        }
        catch ( Exception e )
        {
            bOk = false;
        }
        
        return bOk;
    }
    
    public Date DameFechaSalidaHabitacion(int iIdHabitacion)
    {
        Date dtFecha = null;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        
        strSentenciaSQL = "SELECT F_SALIDA FROM HOSPEDAJES WHERE HABITACION = " + iIdHabitacion + " AND ESTADO = 1";        
        rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);
        
        try
        {
            if ( rsResultado != null )
            {
                rsResultado.next();
                dtFecha = rsResultado.getDate("F_SALIDA");
            }
        }
        catch ( Exception e )
        {
            dtFecha = null;
        }
        
        return dtFecha;
    }
    
    public boolean ExisteHospedajeConEsaHabitacion(int iIdHabitacionAux)
    {
        boolean bOk = false;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;       
        
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM HOSPEDAJES WHERE HABITACION = " + iIdHabitacionAux + " AND ESTADO = 1";
            rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);
            
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
    
    public boolean EntregaHabitacionDeHospedaje(String strObservaciones)
    {
        boolean bOk = false;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;       
        
        
        try
        {
            strSentenciaSQL = "SELECT ID FROM HOSPEDAJES WHERE HABITACION = " + this.IdHabitacion + " AND ESTADO = 1";
            rsResultado = DMObjeto.DameUnDato(strSentenciaSQL);
            
            if ( rsResultado != null )
            {
                rsResultado.next();
                this.Id = rsResultado.getInt("ID");
                this.Observaciones = strObservaciones;
                bOk = DMObjeto.EnviaHabitacionMantenimiento(this.Id, this.IdHabitacion, this.Observaciones);
            }
            
        }
        catch ( Exception e )
        {            
        }
        
        return bOk;
    }
    
    public String DameSentenciaParaInsercion()
    {
        String strSentenciaSQL = null;
                
        //this.Id = DameIDNuevo();
        strSentenciaSQL = "INSERT INTO HOSPEDAJES (HUESPED, HABITACION, DIAS, FORMA_PAGO, TOT_ADULTOS, TOT_NINIOS, TOT_OCUPANTES, ABONO, F_ENTRADA, F_SALIDA, MES, ANIO, TOTAL, OBSERVACIONES, TOTAL_ABONO, ESTADO, HORA, FECHA_REGISTRO, ID_USUARIO) ";
        strSentenciaSQL += " VALUES(";
        //strSentenciaSQL += this.Id + ",";
        strSentenciaSQL += "" + this.IdHuesped + ",";
        strSentenciaSQL += "" + this.IdHabitacion + ",";
        strSentenciaSQL += "" + this.Dias + ",";
        strSentenciaSQL += "" + this.FormaPago + ",";
        strSentenciaSQL += "" + this.TotalAdultos + ",";
        strSentenciaSQL += "" + this.TotalNinios + ",";
        strSentenciaSQL += "" + this.TotalOcupantes + ",";
        strSentenciaSQL += "" + this.RealizoAbono + ",";
        strSentenciaSQL += "?,";
        strSentenciaSQL += "?,";
        strSentenciaSQL += "" + this.Mes + ",";
        strSentenciaSQL += "" + this.Anio + ",";
        strSentenciaSQL += "" + this.Total + ",";
        strSentenciaSQL += "'" + this.Observaciones + "',";
        strSentenciaSQL += "" + this.TotalAbono + ",";
        strSentenciaSQL += "" + this.Estado + ",";
        strSentenciaSQL += "NOW(),";
        strSentenciaSQL += "NOW(),";
        strSentenciaSQL += this.IdUsuario;
        strSentenciaSQL += ")";
        
        return strSentenciaSQL;
    }        
    
    public int getId() 
    {
        return Id;
    }

    public int getIdHuesped() 
    {
        return IdHuesped;
    }

    public int getIdHabitacion() 
    {
        return IdHabitacion;
    }

    public int getDias() 
    {
        return Dias;
    }

    public int getFormaPago()
    {
        return FormaPago;
    }

    public int getTotalAdultos() 
    {
        return TotalAdultos;
    }

    public int getTotalNinios()
    {
        return TotalNinios;
    }

    public int getTotalOcupantes() 
    {
        return TotalOcupantes;
    }

    public int getRealizoAbono() 
    {
        return RealizoAbono;
    }

    public Timestamp getFechaEntrada() 
    {
        return FechaEntrada;
    }

    public Date getFechaSalida() 
    {
        return FechaSalida;
    }

    public int getMes() 
    {
        return Mes;
    }

    public int getAnio() 
    {
        return Anio;
    }

    public double getTotal() 
    {
        return Total;
    }

    public String getObservaciones() 
    {
        return Observaciones;
    }

    public double getTotalAbono() 
    {
        return TotalAbono;
    }

    public Huesped getCliente() 
    {
        return Cliente;
    }

    public Habitacion getCuarto() 
    {
        return Cuarto;
    }

    public void setId(int Id) 
    {
        this.Id = Id;
    }

    public void setIdHuesped(int IdHuesped)
    {
        this.IdHuesped = IdHuesped;
    }

    public void setIdHabitacion(int IdHabitacion) 
    {
        this.IdHabitacion = IdHabitacion;
    }

    public void setDias(int Dias)
    {
        this.Dias = Dias;
    }

    public void setFormaPago(int FormaPago) 
    {
        this.FormaPago = FormaPago;
    }

    public void setTotalAdultos(int TotalAdultos) 
    {
        this.TotalAdultos = TotalAdultos;
    }

    public void setTotalNinios(int TotalNinios)
    {
        this.TotalNinios = TotalNinios;
    }

    public void setTotalOcupantes(int TotalOcupantes) 
    {
        this.TotalOcupantes = TotalOcupantes;
    }

    public void setRealizoAbono(int RealizoAbono)
    {
        this.RealizoAbono = RealizoAbono;
    }

    public void setFechaEntrada(Timestamp FechaEntrada) 
    {
        this.FechaEntrada = FechaEntrada;
    }

    public void setFechaSalida(Date FechaSalida) 
    {
        this.FechaSalida = FechaSalida;
    }

    public void setMes(int Mes) 
    {
        this.Mes = Mes;
    }

    public void setAnio(int Anio) 
    {
        this.Anio = Anio;
    }

    public void setTotal(double Total) 
    {
        this.Total = Total;
    }

    public void setObservaciones(String Observaciones) 
    {
        this.Observaciones = Observaciones;
    }

    public void setTotalAbono(double TotalAbono) 
    {
        this.TotalAbono = TotalAbono;
    }

    public void setCliente(Huesped Cliente) 
    {
        this.Cliente = Cliente;
    }

    public void setCuarto(Habitacion Cuarto) 
    {
        this.Cuarto = Cuarto;
    }        

    public int getEstado()
    {
        return Estado;
    }

    public void setEstado(int Estado)
    {
        this.Estado = Estado;
    }     

    public int getCtrlAire()
    {
        return CtrlAire;
    }

    public int getCtrlTv()
    {
        return CtrlTv;
    }

    public int getLlave()
    {
        return Llave;
    }

    public void setCtrlAire(int CtrlAire)
    {
        this.CtrlAire = CtrlAire;
    }

    public void setCtrlTv(int CtrlTv)
    {
        this.CtrlTv = CtrlTv;
    }

    public void setLlave(int Llave)
    {
        this.Llave = Llave;
    }

    public void setIdUsuario(int IdUsuario) 
    {
        this.IdUsuario = IdUsuario;
    }

    public int getIdUsuario() 
    {
        return IdUsuario;
    }

    public void setRevisado(int Revisado)
    {
        this.Revisado = Revisado;
    }

    public int getRevisado()
    {
        return Revisado;
    }        
}
