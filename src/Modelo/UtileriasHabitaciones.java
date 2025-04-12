/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Controlador.ControlHabitaciones;
import java.awt.Container;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author Usuario
 */
public class UtileriasHabitaciones extends TimerTask
{    
    private Utilerias uAccesibilidad;    
    private Reservacion rHabitacionesReservadas;
    private ControlHabitaciones ControlCuartos = null;
    private Container Contenedor;
    private Usuario uUsuario = Usuario.ObtenInstancia();
    private Bitacora bBitacora = Bitacora.ObtenInstancia();
    
    public UtileriasHabitaciones(Container cContenedor)
    {
        uAccesibilidad = new Utilerias();        
        ControlCuartos = new ControlHabitaciones();
        rHabitacionesReservadas = new Reservacion();
        this.Contenedor = cContenedor;
    }
    
    public void RevisionEstado()
    {
        int i = 0;        
        List<Habitacion> ListaHabitaciones = null;        
        
        if ( Contenedor != null )
        {
            ListaHabitaciones = ControlCuartos.DameListaHabitaciones();
        
            if ( ListaHabitaciones != null )
            { 
                while ( i < ListaHabitaciones.size() )
                {
                    /*
                      0: Habitación Disponible 
                      1: Habitación Ocupada
                      2: Habitación Reservada
                      3: Habitación en Servicio
                      4: Habitación en mantenimiento
                      5: Ocupada por Reservación.
                    */
                    Habitacion hCuarto = ListaHabitaciones.get(i);
                    
                    if ( hCuarto.getEstado() == 0 || hCuarto.getEstado() == 3 ) //"Disponible" o "En Servicio"
                    {
                        Date dtFecha = new Date();
                        
                        if ( rHabitacionesReservadas.HabitacionReservada(hCuarto.getId(), dtFecha) )
                        {
                            if ( rHabitacionesReservadas.InstanciaReservacion(hCuarto.getId(), dtFecha) )
                            {
                                Huesped cCliente = new Huesped();
                                Hospedaje Registro = new Hospedaje();
                                java.sql.Timestamp sq = new java.sql.Timestamp(dtFecha.getTime());
                                
                                cCliente.InstanciaHuesped(rHabitacionesReservadas.getIdHuesped());
                                Registro.setCliente(cCliente);
                                Registro.setIdHabitacion(hCuarto.getId());
                                Registro.setFechaEntrada(sq);
                                dtFecha = AgregaDiasEnFecha(1);
                                Registro.setFechaSalida(dtFecha);
                                Registro.setDias(1);
                                Registro.setFormaPago(0);
                                Registro.setEstado(1);
                                Registro.setMes(dtFecha.getMonth());
                                Registro.setAnio(dtFecha.getYear() + 1990);
                                Registro.setTotal(hCuarto.getPrecioDia());
                                Registro.setIdUsuario(uUsuario.getID());
                                
                                if ( Registro.InsertarDeHabitacionReservada() )
                                {                                                                        
                                    bBitacora.InsertarAccion(uUsuario.getID(), "EL USUARIO: " + uUsuario.getNomUsuario() + " AGREGÓ UN HOSPEDAJE CON UN TOTAL DE " + Registro.getTotal() + " PARA LA HABITACIÓN: " + hCuarto.getId() + " ESTE HOSPEDAJE SE REALIZÓ AUTOMÁTICAMENTE DE UNA RESERVACIÓN", "AGREGAR HOSPEDAJE", Bitacora.AGREGAR_HOSPEDAJE);
                                }
                            }                        
                        }
                    }
                    else if ( hCuarto.getEstado() == 1 || hCuarto.getEstado() == 5 )
                    {         
                        Date dtFecha = new Date();
                        Hospedaje hRegistro = new Hospedaje();
                        
                        if ( hRegistro.FechaSalidaEsLaActualParaHabitacion(hCuarto.getId(), dtFecha) )
                        {
                            hRegistro.setIdHabitacion(hCuarto.getId());
                        
                            if ( hRegistro.EntregaHabitacionDeHospedaje("Se liberó la Habitación automáticamente al llegar el día de Finalización.") )
                            {
                                bBitacora.InsertarAccion(uUsuario.getID(), "EL USUARIO: " + uUsuario.getNomUsuario() + " LOGUEADO, SE REALIZÓ LIBERACIÓN AUTOMÁTICAMENTE PARA LA HABITACIÓN: " + hCuarto.getId() + " ESTE HOSPEDAJE SE LIBERÓ AUTOMÁTICAMENTE", "LIBERAR HOSPEDAJE", Bitacora.LIBERAR_HABITACION);
                            }   
                        }                                                
                    }
                    
                    i++;
                }
                
                uAccesibilidad.RepintaFormasEnMenu(Contenedor);
            }
        }
    }
    
    public Date AgregaDiasEnFecha(int iDias)
    {
        Calendar calendar = Calendar.getInstance();
        Date dtFecha = new Date();
                
        calendar.setTime(dtFecha);            
        calendar.add(Calendar.DAY_OF_YEAR, iDias);
        dtFecha = calendar.getTime();
        
        return dtFecha;
    }
    
    @Override
    public void run() 
    {        
        RevisionEstado();
    }
}
