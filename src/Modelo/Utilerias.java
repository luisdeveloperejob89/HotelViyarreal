/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Interfaces.CnsHospedajes;
import Interfaces.FrmListaHabitaciones;
import Interfaces.FrmListaSalaConferencias;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author WIN7UTL64
 */
public class Utilerias
{     
    public Utilerias()
    {
        
    }
    
    public void RestringirCaracteres(JTextField jtxtCampo, java.awt.event.KeyEvent evt, int iNumeroCaracteres)
    {
        if ( jtxtCampo.getText().length() >= iNumeroCaracteres )
        {
            evt.consume();            
        }
    }
    
    public int DameDiasEntreFechas(Date dtFechaInicio, Date dtFechaFinal)
    {
        int iDias = 0;
        
        iDias = (int)( ( dtFechaFinal.getTime() - dtFechaInicio.getTime() ) / 86400000 );
        
        return iDias;
    }
    
    public void MuestraMensaje(String strMensaje)
    {
        javax.swing.JOptionPane.showMessageDialog(null, strMensaje);
    }
    
    public int MuestraMensajeConfirmacion(String strMensaje)
    {
        int iRespuesta = 0;
                
        iRespuesta = javax.swing.JOptionPane.showConfirmDialog(null, strMensaje, "Confirmación", 0);
        return iRespuesta;
    }
    
    public void RepintaFormasEnMenu(Container cContenedor)
    {        
        int i = 0;
        String strNombre = null;                
                        
        while ( i < cContenedor.getComponentCount() )
        {                                        
            strNombre = null;
            strNombre = cContenedor.getComponent(i).getClass().getName();
            
            if ( strNombre.compareTo("Interfaces.CnsHospedajes") == 0 )
            {
                ((CnsHospedajes)cContenedor.getComponent(i)).RefrescarForma(0);
            }
            else if ( strNombre.compareTo("Interfaces.FrmListaHabitaciones") == 0 )
            {
                ((FrmListaHabitaciones)cContenedor.getComponent(i)).LlenaListaDeHabitaciones();
            }
            else if ( strNombre.compareTo("Interfaces.FrmListaSalaConferencias") == 0 )
            {
                ((FrmListaSalaConferencias)cContenedor.getComponent(i)).LlenaSalaDeConferencias();
            }
            
            i++;
        }
    }
    
    public boolean PresionoENTER(java.awt.event.KeyEvent evt)
    {
        boolean bOk = false;
        
        bOk = ( evt.getKeyCode() == KeyEvent.VK_ENTER );
        
        return bOk;
    }        
    
    public String DameEstadoFormateadoHabitacion(int iEstado)
    {
        String strEstado = null;
        
        switch ( iEstado )
        {
            case 0:
                strEstado = "DISPONIBLE";
            break;
            case 1:            
                strEstado = "OCUPADO";
            break;
            case 2:
                strEstado = "RESERVADO";
            break;
            case 3:
                strEstado = "LIMPIEZA";
            break;
            case 4:
                strEstado = "MANTENIMIENTO";
            break;
            case 5:
                strEstado = "OCUPADO DE RESERVACIÓN";
            break;
            default:
                strEstado = "DISPONIBLE";
        }
        
        return strEstado;
    }        
    
    public String DameRutaImagenPorEstadoHabitacion(int iEstado)
    {
        String strRuta = null;
        
        switch ( iEstado )
        {
            case 0:
                strRuta = "Imagenes/Habitacion_Disponible.png";
            break;
            case 1:    
            case 5:
                strRuta = "Imagenes/Habitacion_Ocupada.png";
            break;
            case 2:
                strRuta = "Imagenes/HabitacionReservada64.png";
            break;
            case 3:
                strRuta = "Imagenes/Habitacion_Servicio.png";
            break;
            case 4:
                strRuta = "Imagenes/Habitacion_Mantenimiento.png";
            break; 
            default:
                strRuta = "Imagenes/Habitacion_Disponible.png";
        }
        
        return strRuta;   
    }
    
    public void ConfiguraElementos(JLabel txtLabel, JButton btnBoton, int iEstado)
    {
        ImageIcon imgIcono = null;
        
        txtLabel.setText(DameEstadoFormateadoHabitacion(iEstado));    
        CambiaColorLabel(txtLabel, iEstado);
        imgIcono = new ImageIcon(DameRutaImagenPorEstadoHabitacion(iEstado));            
        btnBoton.setIcon(imgIcono);
        
        if ( iEstado == 1 )
        {
            //btnBoton.setEnabled(false);
            btnBoton.setToolTipText("Presione para liberar la habitación");
        }
    }
    
    public void ConfiguraElementosSalaConferencias(JLabel txtLabel, JButton btnBoton, int iEstado)
    {
        ImageIcon imgIcono = null;
        
        txtLabel.setText(DameEstadoFormateadoHabitacion(iEstado));     
        CambiaColorLabel(txtLabel, iEstado);
        imgIcono = new ImageIcon(DameRutaImagenPorEstadoHabitacion(iEstado));            
        btnBoton.setIcon(imgIcono);
        
        if ( iEstado == 1 )
        {
            //btnBoton.setEnabled(false);
            btnBoton.setToolTipText("Presione para liberar la habitación");
        }
    }

    public String DameAmPM(int iHoras) 
    {
        String strAmPm = null;
        
        strAmPm = "AM";
        
        if ( iHoras >= 12 )
        {
            strAmPm = "PM";
        }
        
        return strAmPm;
    }

    public String FormateaA24Horas(String strHora) 
    {
        int iIndice = 0;
        int iHoras = 0;
        int iMinutos = 0;
        int iSegundos = 0;
        String strAmPm = null;
        String strHoraFormateada = null;
        
        try
        {            
            iHoras = Integer.parseInt(strHora.substring(0, 2));            
            iIndice = strHora.indexOf("AM");
            iMinutos = Integer.parseInt(strHora.substring(iIndice, 1));
        }
        catch ( Exception e )
        {
            strHoraFormateada = strHora;
        }
        
        return strHoraFormateada;
    }            

    private void CambiaColorLabel(JLabel txtLabel, int iEstado)
    {
        switch ( iEstado )
        {
            case 0:
                //strEstado = "DISPONIBLE";
                txtLabel.setForeground(Color.BLACK);
            break;
            case 1:
            case 5:
                //strEstado = "OCUPADO";
                //strEstado = "OCUPADO DE RESERVACIÓN";
                txtLabel.setForeground(Color.RED);
            break;
            case 2:
                //strEstado = "RESERVADO";
                txtLabel.setForeground(Color.PINK);
            break;
            case 3:
                //strEstado = "LIMPIEZA";
                txtLabel.setForeground(Color.BLUE);
            break;
            case 4:
                //strEstado = "MANTENIMIENTO";
                txtLabel.setForeground(Color.DARK_GRAY);
            break;
            default:
                //strEstado = "DISPONIBLE";
                txtLabel.setForeground(Color.BLACK);
        }   
    }       
    
    public List<Date> DameListaIntervaloFechas(Date dtFechaInicio, Date dtFechaFinal)
    {
        Calendar cFechaInicio = Calendar.getInstance();
        Calendar cFechaFin = Calendar.getInstance();
        List<Date> lstFechas = new ArrayList<Date>();
        
        cFechaInicio.setTime(dtFechaInicio);
        cFechaFin.setTime(dtFechaFinal);
        
        while ( !cFechaInicio.after(cFechaFin) )
        {
            lstFechas.add(cFechaInicio.getTime());
            cFechaInicio.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        return lstFechas;
    }
}
