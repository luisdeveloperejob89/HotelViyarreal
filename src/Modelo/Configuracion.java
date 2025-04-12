/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Conexion.DMBaseDatos;
import java.sql.ResultSet;

/**
 *
 * @author WIN7UTL64
 */
public class Configuracion 
{
    private int ID;
    private String Empresa;
    private String RFC;
    private String Calle;
    private String Telefono;
    private String Email;
    private String Contrasenia;
    private String EmailCont;
    private int EnvioAut;
    private String NumExt;
    private String NumInt;
    private String Colonia;
    private String Poblacion;
    private String Municipio;
    private String Pais;
    private String CP;    
    private DMBaseDatos DMObjeto;
    
    public Configuracion()
    {
        DMObjeto = new DMBaseDatos();
        this.ID = 0;
        this.Empresa = null;
        this.RFC = null;
        this.Calle = null;
        this.Telefono = null;
        this.Email = null;
        this.Contrasenia = null;
        this.EmailCont = null;
        this.EnvioAut = 0;
        this.NumExt = null;
        this.NumInt = null;
        this.Colonia = null;
        this.Poblacion = null;
        this.Municipio = null;
        this.Pais = null;
        this.CP = null;
    }
    
    public String DameSentenciaSQLUpdate()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "UPDATE CONFIGURACION";
        strSentenciaSQL += " SET ";
        strSentenciaSQL += "EMPRESA = '" + this.Empresa + "', ";
        strSentenciaSQL += "RFC = '" + this.RFC + "', ";
        strSentenciaSQL += "CALLE = '" + this.Calle + "', ";
        strSentenciaSQL += "TELEFONO = '" + this.Telefono + "', ";
        strSentenciaSQL += "EMAIL = '" + this.Email + "', ";
        strSentenciaSQL += "EMAIL_CONTADOR = '" + this.EmailCont + "', ";
        strSentenciaSQL += "CONT_EMAIL = '" + this.Contrasenia + "', ";
        strSentenciaSQL += "ENVIO_AUT_CORREO = " + this.EnvioAut + ", ";
        strSentenciaSQL += "NUM_EXT = '" + this.NumExt + "', ";
        strSentenciaSQL += "NUM_INT = '" + this.NumInt + "', ";
        strSentenciaSQL += "COLONIA = '" + this.Colonia + "', ";
        strSentenciaSQL += "POBLACION = '" + this.Poblacion + "', ";
        strSentenciaSQL += "MUNICIPIO = '" + this.Municipio + "', ";
        strSentenciaSQL += "PAIS = '" + this.Pais + "', ";
        strSentenciaSQL += "CP = '" + this.CP + "'";
        strSentenciaSQL += " WHERE ID = 1";
        System.out.println(strSentenciaSQL);
        
        return strSentenciaSQL;
    }
    
    public String DameSentenciaSQLInsercion()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "INSERT INTO CONFIGURACION (ID, EMPRESA, RFC, CALLE, TELEFONO, EMAIL, EMAIL_CONTADOR, CONT_EMAIL, ENVIO_AUT_CORREO, NUM_EXT, NUM_INT, COLONIA, POBLACION, MUNICIPIO, PAIS, CP) ";
        strSentenciaSQL += " VALUES(";
        strSentenciaSQL += "1,";
        strSentenciaSQL += "'" + this.Empresa + "', ";
        strSentenciaSQL += "'" + this.RFC + "', ";
        strSentenciaSQL += "'" + this.Calle + "', ";
        strSentenciaSQL += "'" + this.Telefono + "', ";
        strSentenciaSQL += "'" + this.Email + "', ";
        strSentenciaSQL += "'" + this.EmailCont + "', ";
        strSentenciaSQL += "'" + this.Contrasenia + "', ";
        strSentenciaSQL += "" + this.EnvioAut + ", ";
        strSentenciaSQL += "'" + this.NumExt + "', ";
        strSentenciaSQL += "'" + this.NumInt + "', ";
        strSentenciaSQL += "'" + this.Colonia + "', ";
        strSentenciaSQL += "'" + this.Poblacion + "', ";
        strSentenciaSQL += "'" + this.Municipio + "', ";
        strSentenciaSQL += "'" + this.Pais + "', ";
        strSentenciaSQL += "'" + this.CP + "'";
        strSentenciaSQL += ")";
        
        return strSentenciaSQL;
    }
    
    public void DameDatosConfiguracion()
    {
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT * FROM CONFIGURACION WHERE ID = 1";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            
            if ( rsEstado != null )
            {
                rsEstado.next();
                this.ID = rsEstado.getInt("ID");
                this.Empresa = rsEstado.getString("EMPRESA");
                this.RFC = rsEstado.getString("RFC");
                this.Calle = rsEstado.getString("CALLE");
                this.Telefono = rsEstado.getString("TELEFONO");
                this.Email = rsEstado.getString("EMAIL");
                this.EmailCont = rsEstado.getString("EMAIL_CONTADOR");
                this.Contrasenia = rsEstado.getString("CONT_EMAIL");
                this.EnvioAut = rsEstado.getInt("ENVIO_AUT_CORREO");
                this.NumExt = rsEstado.getString("NUM_EXT");
                this.NumInt = rsEstado.getString("NUM_INT");
                this.Colonia = rsEstado.getString("COLONIA");
                this.Poblacion = rsEstado.getString("POBLACION");
                this.Municipio = rsEstado.getString("MUNICIPIO");
                this.Pais = rsEstado.getString("PAIS");
                this.CP = rsEstado.getString("CP");
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public boolean ExisteRegistro()
    {
        boolean bOk = false;        
        int iTotal = 0;
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM CONFIGURACION WHERE ID = 1";        
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            
            if ( rsEstado != null )
            {
                rsEstado.next();
                iTotal = rsEstado.getInt("TOTAL"); 
                bOk = ( iTotal > 0 );
            }
        }
        catch ( Exception e )
        {
            bOk = false;
        }
        
        return bOk;
    }
    
    public boolean Insertar()
    {
        boolean bOk = true;
        String strSentenciaSQL = null;
        
        if ( !ExisteRegistro() )
        {
            strSentenciaSQL = DameSentenciaSQLInsercion();
        }
        else
        {
            strSentenciaSQL = DameSentenciaSQLUpdate();
        }
        
        bOk = DMObjeto.Insertar(strSentenciaSQL);
                
        return bOk;
    }

    public void setID(int ID) 
    {
        this.ID = ID;
    }

    public void setEmpresa(String Empresa) 
    {
        this.Empresa = Empresa;
    }

    public void setRFC(String RFC) 
    {
        this.RFC = RFC;
    }

    public void setCalle(String Calle) 
    {
        this.Calle = Calle;
    }

    public void setTelefono(String Telefono) 
    {
        this.Telefono = Telefono;
    }

    public void setEmail(String Email) 
    {
        this.Email = Email;
    }

    public void setContrasenia(String Contrasenia) 
    {
        this.Contrasenia = Contrasenia;
    }

    public void setEmailCont(String EmailCont) 
    {
        this.EmailCont = EmailCont;
    }

    public void setEnvioAut(int EnvioAut) 
    {
        this.EnvioAut = EnvioAut;
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

    public void setPais(String Pais)
    {
        this.Pais = Pais;
    }

    public void setCP(String CP) 
    {
        this.CP = CP;
    }    
    
    public int getID() 
    {
        return ID;
    }

    public String getEmpresa() 
    {
        return Empresa;
    }

    public String getRFC() 
    {
        return RFC;
    }

    public String getCalle() 
    {
        return Calle;
    }

    public String getTelefono() 
    {
        return Telefono;
    }

    public String getEmail() 
    {
        return Email;
    }

    public String getContrasenia() 
    {
        return Contrasenia;
    }

    public String getEmailCont() 
    {
        return EmailCont;
    }

    public int getEnvioAut() 
    {
        return EnvioAut;
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

    public String getPais() 
    {
        return Pais;
    }

    public String getCP() 
    {
        return CP;
    }
    
    
}
