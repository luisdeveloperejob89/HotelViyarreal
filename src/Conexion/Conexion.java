/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author WIN7UTL64
 */
public class Conexion 
{
    private static String Servidor = "\"jdbc:mysql://localhost/hotelviyarreal\"";
    private static String Usuario = "root";
    private static String Contrasenia = "root";
    private static String Driver = "\"com.mysql.jdbc.Driver\"";
    private static Connection ConexionSQL;
    
    public Conexion()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");                                      
            ConexionSQL = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/hotelviyarreal", "root", "root12345");
            //ConexionSQL = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/hotelviyarreal", "root", "");
        }
        catch ( ClassNotFoundException | SQLException e )
        {
            System.out.println("Error: " + e.getMessage());
        }
    }  
    
    public Connection DameConexion()
    {
        return ConexionSQL;
    }
    
    public void CerrarConexion()
    {
        try
        {
            if ( ConexionSQL != null ) 
            {
                ConexionSQL.close();
            }
        }
        catch ( SQLException e )
        {
            
        }
    }
}
