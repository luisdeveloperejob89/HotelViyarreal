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
public class Huesped 
{
    private int Id;
    private String Nombre;
    private String ApPaterno;
    private String ApMaterno;
    private String Rfc;
    private String Direccion;
    private String Telefono;
    private String Pais;
    private int Procedencia;
    private String Email;
    private int AplicaDescuento;
    private int Empresa;
    private int RequiereFactura;
    private String Calle;
    private String NumExt;
    private String NumInt;
    private String Colonia;
    private String Poblacion;
    private String Municipio;
    private String Estado;
    private String CP;
    private DMBaseDatos DMObjeto;
    
    public Huesped()
    {
        this.Id = 0;
        this.Nombre = null;
        this.ApPaterno = null;
        this.ApMaterno = null;
        this.Rfc = null;
        this.Direccion = null;
        this.Telefono = null;
        this.Pais = null;
        this.Procedencia = 0;     
        this.Email = null;
        this.Calle = null;
        this.NumExt = null;
        this.NumInt = null;
        this.Colonia = null;
        this.Poblacion = null;
        this.Municipio = null;
        this.Estado = null;
        this.CP = null;
        this.DMObjeto = new DMBaseDatos();
    }

    public void setCalle(String Calle) 
    {
        this.Calle = Calle;
    }

    public void setNumExt(String NumExt) 
    {
        this.NumExt = NumExt;
    }

    public void setNumInt(String NumInt) 
    {
        this.NumInt = NumInt;
    }

    public void setColonia(String Colonia) 
    {
        this.Colonia = Colonia;
    }

    public void setPoblacion(String Poblacion) 
    {
        this.Poblacion = Poblacion;
    }

    public void setMunicipio(String Municipio) 
    {
        this.Municipio = Municipio;
    }

    public void setEstado(String Estado)
    {
        this.Estado = Estado;
    }

    public void setCP(String CP) 
    {
        this.CP = CP;
    }

    public String getCalle() 
    {
        return Calle;
    }

    public String getNumExt() 
    {
        return NumExt;
    }

    public String getNumInt() 
    {
        return NumInt;
    }

    public String getColonia() 
    {
        return Colonia;
    }

    public String getPoblacion()
    {
        return Poblacion;
    }

    public String getMunicipio()
    {
        return Municipio;
    }

    public String getEstado()
    {
        return Estado;
    }

    public String getCP()
    {
        return CP;
    }
       
    public void setEmail(String Email) 
    {
        this.Email = Email;
    }
    
    public void setId(int Id) 
    {
        this.Id = Id;
    }

    public void setNombre(String Nombre) 
    {
        this.Nombre = Nombre;
    }

    public void setApPaterno(String ApPaterno) 
    {
        this.ApPaterno = ApPaterno;
    }

    public void setApMaterno(String ApMaterno) 
    {
        this.ApMaterno = ApMaterno;
    }

    public void setRfc(String Rfc) 
    {
        this.Rfc = Rfc;
    }

    public void setDireccion(String Direccion) 
    {
        this.Direccion = Direccion;
    }

    public void setTelefono(String Telefono) 
    {
        this.Telefono = Telefono;
    }

    public void setPais(String Pais) 
    {
        this.Pais = Pais;
    }

    public void setProcedencia(int Procedencia) 
    {
        this.Procedencia = Procedencia;
    }

    public int getId() 
    {
        return Id;
    }

    public String getNombre()
    {
        return Nombre;
    }

    public String getApPaterno() 
    {
        return ApPaterno;
    }

    public String getApMaterno() 
    {
        return ApMaterno;
    }

    public String getRfc() 
    {
        return Rfc;
    }

    public String getDireccion() 
    {
        return Direccion;
    }

    public String getTelefono() 
    {
        return Telefono;
    }

    public String getPais()
    {
        return Pais;
    }

    public int getProcedencia() 
    {
        return Procedencia;
    }

    public String getEmail() 
    {
        return Email;
    }

    public void setAplicaDescuento(int AplicaDescuento) 
    {
        this.AplicaDescuento = AplicaDescuento;
    }

    public void setEmpresa(int Empresa) 
    {
        this.Empresa = Empresa;
    }

    public void setRequiereFactura(int RequiereFactura) 
    {
        this.RequiereFactura = RequiereFactura;
    }

    public int getAplicaDescuento() 
    {
        return AplicaDescuento;
    }

    public int getEmpresa() 
    {
        return Empresa;
    }

    public int getRequiereFactura() 
    {
        return RequiereFactura;
    }            
    
    public String DameSentenciaParaEliminar()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "DELETE FROM HUESPEDES WHERE ID = " + this.Id;
        
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
    
    public boolean Actualizar()
    {
        boolean bOk = true;
        String strSentenciaSQL = null;
        
        strSentenciaSQL = DameSentenciaParaUpdate();
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
    
    public String DameSentenciaParaUpdate()
    {
        String strSentenciaSQL = null;
                
        strSentenciaSQL = "UPDATE  HUESPEDES SET ";  
        strSentenciaSQL += "NOMBRE = '" + this.Nombre + "', ";        
        strSentenciaSQL += "AP_PATERNO = '" + this.ApPaterno + "', ";
        strSentenciaSQL += "AP_MATERNO = '" + this.ApMaterno + "', ";
        strSentenciaSQL += "RFC = '" + this.Rfc + "', ";
        strSentenciaSQL += "DIRECCION = '" + this.Direccion + "', ";
        strSentenciaSQL += "TELEFONO = '" + this.Telefono + "', ";
        strSentenciaSQL += "PAIS = '" + this.Pais + "', ";
        strSentenciaSQL += "PROCEDENCIA = " + this.Procedencia + ", ";
        strSentenciaSQL += "EMAIL = '" + this.Email + "', ";
        strSentenciaSQL += "APLICA_DESCUENTO = " + this.AplicaDescuento + ", ";
        strSentenciaSQL += "EMPRESA = " + this.Empresa + ", ";
        strSentenciaSQL += "REQ_FACTURA = " + this.RequiereFactura + ", ";
        strSentenciaSQL += "CALLE = '" + this.Calle + "', ";
        strSentenciaSQL += "NUM_EXT = '" + this.NumExt + "', ";
        strSentenciaSQL += "NUM_INT = '" + this.NumInt + "', ";
        strSentenciaSQL += "COLONIA = '" + this.Colonia + "', ";
        strSentenciaSQL += "POBLACION = '" + this.Poblacion + "', ";
        strSentenciaSQL += "MUNICIPIO = '" + this.Municipio + "', ";
        strSentenciaSQL += "ESTADO = '" + this.Estado + "', ";
        strSentenciaSQL += "CP = '" + this.CP + "' ";
        strSentenciaSQL += " WHERE ID = " + this.Id;        
        
        return strSentenciaSQL;
    }
    
    public String DameSentenciaParaInsercion()
    {
        String strSentenciaSQL = null;
                
        strSentenciaSQL = "INSERT INTO HUESPEDES (ID, NOMBRE, AP_PATERNO, AP_MATERNO, RFC, DIRECCION, TELEFONO, PAIS, PROCEDENCIA, EMAIL, APLICA_DESCUENTO, EMPRESA, REQ_FACTURA, CALLE, NUM_EXT, NUM_INT, COLONIA, POBLACION, MUNICIPIO, ESTADO, CP) ";
        this.Id = DameIDNuevo();
        strSentenciaSQL += "VALUES(" + this.Id + ",";
        strSentenciaSQL += "'" + this.Nombre + "',";
        strSentenciaSQL += "'" + this.ApPaterno + "',";
        strSentenciaSQL += "'" + this.ApMaterno + "',";
        strSentenciaSQL += "'" + this.Rfc + "',";
        strSentenciaSQL += "'" + this.Direccion + "',";
        strSentenciaSQL += "'" + this.Telefono + "',";
        strSentenciaSQL += "'" + this.Pais + "',";
        strSentenciaSQL += "" + this.Procedencia + ",";
        strSentenciaSQL += "'" + this.Email + "',";
        strSentenciaSQL += "" + this.AplicaDescuento + ",";
        strSentenciaSQL += "" + this.Empresa + ",";
        strSentenciaSQL += "" + this.RequiereFactura + ",";
        strSentenciaSQL += "'" + this.Calle + "',";
        strSentenciaSQL += "'" + this.NumExt + "',";
        strSentenciaSQL += "'" + this.NumInt + "',";
        strSentenciaSQL += "'" + this.Colonia + "',";
        strSentenciaSQL += "'" + this.Poblacion + "',";
        strSentenciaSQL += "'" + this.Municipio + "',";
        strSentenciaSQL += "'" + this.Estado + "',";
        strSentenciaSQL += "'" + this.CP + "'";
        strSentenciaSQL += ")";
        
        return strSentenciaSQL;
    }
    
    public int DameIDNuevo()
    {
        int iIdNuevo = 0;
        
        iIdNuevo = DameMaximoID();
        iIdNuevo++;
        
        return iIdNuevo;
    }
    
    public void InstanciaHuesped(int iID)
    {
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        strSentenciaSQL = "SELECT * FROM HUESPEDES WHERE ID = " + iID;
        rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
        
        try
        {
            if ( rsEstado != null )
            {
                rsEstado.next();
                this.Id = rsEstado.getInt("ID");
                this.Nombre = rsEstado.getString("NOMBRE");
                this.ApPaterno = rsEstado.getString("AP_PATERNO");
                this.ApMaterno = rsEstado.getString("AP_MATERNO");
                this.Rfc = rsEstado.getString("RFC");
                this.Direccion = rsEstado.getString("DIRECCION");
                this.Telefono = rsEstado.getString("TELEFONO");
                this.Pais = rsEstado.getString("PAIS");
                this.Procedencia = rsEstado.getInt("PROCEDENCIA");
                this.Email = rsEstado.getString("EMAIL");
                this.AplicaDescuento = rsEstado.getInt("APLICA_DESCUENTO");
                this.Empresa = rsEstado.getInt("EMPRESA");
                this.RequiereFactura = rsEstado.getInt("REQ_FACTURA");
                this.Calle = rsEstado.getString("CALLE");
                this.NumExt = rsEstado.getString("NUM_EXT");
                this.NumInt = rsEstado.getString("NUM_INT");
                this.Colonia = rsEstado.getString("COLONIA");
                this.Poblacion = rsEstado.getString("POBLACION");
                this.Municipio = rsEstado.getString("MUNICIPIO");
                this.Estado = rsEstado.getString("ESTADO");
                this.CP = rsEstado.getString("CP");
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public String FormateaProcedencia(int iProcedencia)
    {
        String strProcedencia = null;
        
        if ( iProcedencia == 0 )
        {
            strProcedencia = "Nacional";
        }
        else
        {
            strProcedencia = "Extranjero";
        }
        
        return strProcedencia;
    }
    
    public int DameRegistrosListaHuespedes(DefaultTableModel dmtModelo, String[] vColumnas)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM HUESPEDES";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;
            strSentenciaSQL = "SELECT ID, NOMBRE, AP_PATERNO, AP_MATERNO, TELEFONO FROM HUESPEDES";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][5];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("NOMBRE");
                objTuplas[iContadorAux][2] = rsEstado.getString("AP_PATERNO");
                objTuplas[iContadorAux][3] = rsEstado.getString("AP_MATERNO");
                objTuplas[iContadorAux][4] = rsEstado.getString("TELEFONO");                
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
    
    public int DameRegistrosCnsClientes(DefaultTableModel dmtModelo, String[] vColumnas)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM HUESPEDES";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;
            strSentenciaSQL = "SELECT ID, CONCAT(NOMBRE, ' ', AP_PATERNO, ' ', AP_MATERNO) AS NOMBRE, RFC, DIRECCION, TELEFONO, PAIS, PROCEDENCIA FROM HUESPEDES";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][7];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("NOMBRE");
                objTuplas[iContadorAux][2] = rsEstado.getString("RFC");
                objTuplas[iContadorAux][3] = rsEstado.getString("DIRECCION");
                objTuplas[iContadorAux][4] = rsEstado.getString("TELEFONO");
                objTuplas[iContadorAux][5] = rsEstado.getString("PAIS");
                objTuplas[iContadorAux][6] = FormateaProcedencia(rsEstado.getInt("PROCEDENCIA"));
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
    
    public int DameMaximoID()
    {
        int iMaximoId = 1;
        String strSentenciaSQL = null;
        ResultSet rsResultado = null;
        
        try
        {
            strSentenciaSQL = "SELECT MAX(ID) AS MAXIMO FROM HUESPEDES";
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
}
