/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Conexion.DMBaseDatos;
import Modelo.Habitacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ControlHabitaciones
{
    private DMBaseDatos DMObjeto;
            
    public ControlHabitaciones()
    {
        DMObjeto = new DMBaseDatos();
    }
    
    public List<Habitacion> DameListaHabitaciones()
    {
        int i = 1;        
        Habitacion hCuarto = null;
        List<Habitacion> lstHabitaciones = new ArrayList<Habitacion>();               
        
        try
        {            
            while ( i <= 16 )
            {
                hCuarto = new Habitacion();
                lstHabitaciones.add(hCuarto.DameHabitacionInstanciada(i));
                i++;
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        return lstHabitaciones;
    }   
}
