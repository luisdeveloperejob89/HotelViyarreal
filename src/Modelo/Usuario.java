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
public class Usuario 
{
    private int ID;
    private String NomUsuario;
    private String Contrasenia;
    private int Tipo;
    private String Nombre;
    private String ApPaterno;
    private String ApMaterno;
    private String Telefono;
    private String Email;
    private String Direccion;
    private static Usuario uUsuario;
    private DMBaseDatos DMObjeto;
    
    public static Usuario ObtenInstancia()
    {
        if ( uUsuario == null )
        {
            uUsuario = new Usuario();            
        }
        
        return uUsuario;        
    }
    
    private Usuario()
    {
        InstanciaPropiedades();
        this.DMObjeto = new DMBaseDatos();
    }
    
    public void InstanciaPropiedades()
    {
        this.ID = 0;
        this.NomUsuario = null;
        this.Contrasenia = null;
        this.Tipo = 0;
        this.Nombre = null;
        this.ApPaterno = null;
        this.ApMaterno = null;
        this.Telefono = null;
        this.Email = null;
        this.Direccion = null;
    }
    
    public String DameNombreCompletoUsuario()
    {
        String strNombreCompleto = null;
        
        strNombreCompleto = this.Nombre + " " + this.ApPaterno + " " + this.ApMaterno;
        
        return strNombreCompleto;
    }
    
    public boolean Actualizar(String strSentenciaSQL)
    {
        boolean bOk = false;        
        
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }
    
    public ResultSet DameInstanciaEdicionUsuario(int iIDUsuarioAux)
    {
        ResultSet rsEstado = null;        
        String strSentenciaSQL = null;
        
        try
        {
            strSentenciaSQL = "SELECT * FROM USUARIOS WHERE ID = " + iIDUsuarioAux;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);                                   
        }
        catch ( Exception e )
        {
            
        }
        
        return rsEstado;
    }
    
    public int DameRegistrosUsuarios(DefaultTableModel dmtModelo, String[] vColumnas)
    {
        int iContadorAux = 0;
        int iTotalRegistros = 0;
        String strSentenciaSQL = null;
        Object[][] objTuplas = null;
        ResultSet rsEstado = null;
        
        try
        {
            strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM USUARIOS";            
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
            rsEstado.next();
            iTotalRegistros = rsEstado.getInt("TOTAL");
            strSentenciaSQL = null;            
            strSentenciaSQL = "SELECT ID, USUARIO, CONTRASENIA, TIPO, CONCAT(NOMBRE, ' ', AP_PATERNO, ' ', AP_MATERNO) AS NOMBRE, TELEFONO, EMAIL FROM USUARIOS";
            rsEstado = null;
            rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);            
            objTuplas = new Object[iTotalRegistros][7];
            
            while ( rsEstado.next() ) 
            {
                objTuplas[iContadorAux][0] = rsEstado.getInt("ID");
                objTuplas[iContadorAux][1] = rsEstado.getString("USUARIO");
                objTuplas[iContadorAux][2] = rsEstado.getString("CONTRASENIA");
                objTuplas[iContadorAux][3] = FormateaTipoUsuario(rsEstado.getInt("TIPO"));
                objTuplas[iContadorAux][4] = rsEstado.getString("NOMBRE");
                objTuplas[iContadorAux][5] = rsEstado.getString("TELEFONO");
                objTuplas[iContadorAux][6] = rsEstado.getString("EMAIL");
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
    
    public String FormateaTipoUsuario(int iTipo)
    {
        String strTipo = null;
        
        switch ( iTipo )
        {
            case 0:
                strTipo = "ADMINISTRADOR";
            break;
            case 1:
                strTipo = "RECEPCIONISTA";
            break;
            default:
                strTipo = "ADMINISTRADOR";
        }
        
        return strTipo;
    }

    public void setID(int ID) 
    {
        this.ID = ID;
    }

    public void setNomUsuario(String NomUsuario) 
    {
        this.NomUsuario = NomUsuario;
    }

    public void setContrasenia(String Contrasenia) 
    {
        this.Contrasenia = Contrasenia;
    }

    public void setTipo(int Tipo) 
    {
        this.Tipo = Tipo;
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

    public void setTelefono(String Telefono) 
    {
        this.Telefono = Telefono;
    }

    public void setEmail(String Email) 
    {
        this.Email = Email;
    }

    public void setDireccion(String Direccion) 
    {
        this.Direccion = Direccion;
    }

    public int getID() 
    {
        return ID;
    }

    public String getNomUsuario() 
    {
        return NomUsuario;
    }

    public String getContrasenia() 
    {
        return Contrasenia;
    }

    public int getTipo() 
    {
        return Tipo;
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

    public String getTelefono() 
    {
        return Telefono;
    }

    public String getEmail() 
    {
        return Email;
    }

    public String getDireccion() 
    {
        return Direccion;
    }
    
    public void InstanciaUsuarioLogueado(String strUsuario, String strContrasenia)
    {             
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        strSentenciaSQL = "SELECT * FROM USUARIOS WHERE USUARIO = '" + strUsuario + "' AND CONTRASENIA = '" + strContrasenia + "'";
        rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
        
        try
        {
            if ( rsEstado != null )
            {
                rsEstado.next();
                this.ID = rsEstado.getInt("ID");
                this.NomUsuario = rsEstado.getString("USUARIO");
                this.Contrasenia = rsEstado.getString("CONTRASENIA");
                this.Tipo = rsEstado.getInt("TIPO");
                this.Nombre = rsEstado.getString("NOMBRE");
                this.ApPaterno = rsEstado.getString("AP_PATERNO");
                this.ApMaterno = rsEstado.getString("AP_MATERNO");
                this.Telefono = rsEstado.getString("TELEFONO");
                this.Email = rsEstado.getString("EMAIL");
                this.Direccion = rsEstado.getString("DIRECCION");
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }   
    }
    
    public String DameSentenciaSQLInsercion()
    {
        String strSentenciaSQL = null;
        
        strSentenciaSQL = "INSERT INTO USUARIOS (USUARIO, CONTRASENIA, TIPO, NOMBRE, AP_PATERNO, AP_MATERNO, TELEFONO, EMAIL, DIRECCION) ";
        strSentenciaSQL += " VALUES(";
        strSentenciaSQL += "'" + this.NomUsuario + "', ";
        strSentenciaSQL += "'" + this.Contrasenia + "', ";
        strSentenciaSQL += "" + this.Tipo + ", ";
        strSentenciaSQL += "'" + this.Nombre + "', ";
        strSentenciaSQL += "'" + this.ApPaterno + "', ";
        strSentenciaSQL += "'" + this.ApMaterno + "', ";
        strSentenciaSQL += "'" + this.Email + "', ";
        strSentenciaSQL += "'" + this.Telefono + "', ";
        strSentenciaSQL += "'" + this.Direccion + "' ";
        strSentenciaSQL += ")";
        
        return strSentenciaSQL;
    }
    
    public boolean ExisteUsuario(String strUsuario)
    {
        boolean bOk = true;
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM USUARIOS WHERE USUARIO = '" + strUsuario + "'";
        rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
        
        try
        {
            if ( rsEstado != null )
            {
                rsEstado.next();
                bOk = ( rsEstado.getInt("TOTAL") == 1 );                                
            }
            else
            {
                bOk = false;
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            bOk = true;
        }
                
        return bOk;
    }
    
    public boolean Insertar()
    {
        boolean bOk = false;
        String strSentenciaSQL = null;
        
        strSentenciaSQL = DameSentenciaSQLInsercion();        
        bOk = DMObjeto.Insertar(strSentenciaSQL);
        
        return bOk;
    }
    
    public void CerrarSesion()
    {
        InstanciaPropiedades();
        uUsuario = null;
    }
    
    public boolean ValidaUsuario(String strUsuario, String strContrasenia)
    {
        boolean bOk = false;
        
        String strSentenciaSQL = null;
        ResultSet rsEstado = null;
        
        strSentenciaSQL = "SELECT COUNT(ID) AS TOTAL FROM USUARIOS WHERE USUARIO = '" + strUsuario + "' AND CONTRASENIA = '" + strContrasenia + "'";
        rsEstado = DMObjeto.DameUnDato(strSentenciaSQL);
        
        try
        {
            if ( rsEstado != null )
            {
                rsEstado.next();
                bOk = ( rsEstado.getInt("TOTAL") == 1 );
                
                if ( bOk )
                {
                    InstanciaUsuarioLogueado(strUsuario, strContrasenia);
                }
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            bOk = false;
        }
        
        return bOk;
    }
    
}
