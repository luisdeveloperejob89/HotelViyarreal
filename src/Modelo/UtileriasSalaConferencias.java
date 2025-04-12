/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.Container;
import java.util.TimerTask;

/**
 *
 * @author Usuario
 */
public class UtileriasSalaConferencias extends TimerTask
{    
    private Utilerias uAccesibilidad;
    private SalaConferencias scSala;
    private Container Contenedor;
    
    public UtileriasSalaConferencias(Container cContenedor)
    {
        uAccesibilidad = new Utilerias();
        scSala = new SalaConferencias();
        this.Contenedor = cContenedor;
    }
    
    public void RevisionEstado()
    {
        scSala.RevisionEstados();    
        
        if ( Contenedor != null )
        {
            uAccesibilidad.RepintaFormasEnMenu(Contenedor);
        }
    }
    
    @Override
    public void run() 
    {        
        RevisionEstado();
    }
}
