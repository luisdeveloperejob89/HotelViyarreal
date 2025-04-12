/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Conexion.Conexion;
import Modelo.Hospedaje;
import Modelo.Usuario;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Date;
import java.util.GregorianCalendar;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author WIN7UTL64
 */
public class ReportManager 
{
    String RutaReportes = "C:\\HOTEL\\Reportes\\";
    private Usuario uUsuario = Usuario.ObtenInstancia();
    
    public ReportManager() throws Exception 
    {
    }
    
    public void GeneraReporteCorteDetHospedajes()
    {
        Conexion cConexion = null;
        Connection cObjCon = null;
        JasperReport jtTemplate = null;
        JasperPrint jpImpresora = null;        
        
        try
        {
            if ( ExisteArchivo("ReporteCorteAlDia.jasper") )
                {
                    cConexion = new Conexion();
                    cObjCon = cConexion.DameConexion();
                    jtTemplate = (JasperReport) JRLoader.loadObject(RutaReportes + "ReporteCorteAlDia.jasper");                    
                    jpImpresora = JasperFillManager.fillReport(jtTemplate, null, cObjCon);                    
                    //JasperPrintManager.printReport(jpImpresora, false);
                    JasperViewer.viewReport(jpImpresora, false);
                }                
        } 
        catch ( JRException e ) 
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            jtTemplate = null;            
        }  
    }
    
    public void GeneraReporteCorteAlDiaTotales()
    {
        Conexion cConexion = null;
        Connection cObjCon = null;
        JasperReport jtTemplate = null;
        JasperPrint jpImpresora = null;        
        
        try
        {
            if ( ExisteArchivo("ReporteCorteDiaTotales.jasper") )
                {
                    cConexion = new Conexion();
                    cObjCon = cConexion.DameConexion();
                    jtTemplate = (JasperReport) JRLoader.loadObject(RutaReportes + "ReporteCorteDiaTotales.jasper");                    
                    jpImpresora = JasperFillManager.fillReport(jtTemplate, null, cObjCon);                    
                    //JasperPrintManager.printReport(jpImpresora, false);
                    JasperViewer.viewReport(jpImpresora, false);
                }                
        } 
        catch ( JRException e ) 
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            jtTemplate = null;            
        }  
    }
    
    public void GeneraVistaReporteAltaHospedaje(Hospedaje hRegistro)
    {
        Date dtFecha = null;
        Conexion cConexion = null;
        Connection cObjCon = null;
        JasperReport jtTemplate = null;
        JasperPrint jpImpresora = null;
        Map mParametros = null;
        SimpleDateFormat sdfFormato = new SimpleDateFormat("dd/MM/yyyy");
        
        try
        {
            if ( hRegistro != null )
            {
                if ( ExisteArchivo("ReporteAltaHospedaje.jasper") )
                {
                    cConexion = new Conexion();
                    cObjCon = cConexion.DameConexion();
                    jtTemplate = (JasperReport) JRLoader.loadObject(RutaReportes + "ReporteAltaHospedaje.jasper");
                    mParametros = new HashMap();                    
                    mParametros.put("FECHA_ENTRADA", sdfFormato.format(hRegistro.getFechaEntrada()));
                    mParametros.put("FECHA_SALIDA", sdfFormato.format(hRegistro.getFechaSalida()));
                    mParametros.put("ID", hRegistro.getId());
                    //mParametros.put("FECHA_ENTRADA", "01/01/2018");
                    //mParametros.put("FECHA_SALIDA", "02/01/2018");
                    mParametros.put("NOMBRE", hRegistro.getCliente().getNombre());
                    mParametros.put("DIRECCION", hRegistro.getCliente().getDireccion());                
                    mParametros.put("TELEFONO", hRegistro.getCliente().getTelefono());
                    
                    if ( hRegistro.getCliente().getProcedencia() == 0 )
                    {
                        mParametros.put("PROCEDENCIA", "Nacional");
                    }
                    else
                    {
                        mParametros.put("PROCEDENCIA", "Extranjero");    
                    }
                    
                    mParametros.put("NACIONALIDAD", hRegistro.getCliente().getPais());
                    mParametros.put("RFC", hRegistro.getCliente().getRfc());
                    mParametros.put("HABITACION", hRegistro.getCuarto().getId());
                    mParametros.put("USUARIO", uUsuario.getNombre() + " " + uUsuario.getApPaterno() + " " + uUsuario.getApMaterno());
                    dtFecha = new Date();
                    mParametros.put("HORA_ACTUAL", dtFecha.getHours() + ":" + dtFecha.getMinutes() + ":" + dtFecha.getSeconds());
                    jpImpresora = JasperFillManager.fillReport(jtTemplate, mParametros, cObjCon);                    
                    
                    try
                    {
                        JasperPrintManager.printReport(jpImpresora, false);
                    }
                    finally
                    {
                        JasperViewer.viewReport(jpImpresora, false);
                    }
                }                
            }
        } 
        catch ( JRException e ) 
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            jtTemplate = null;
            mParametros = null;
        }
    }
    
    public boolean ExisteArchivo(/*String strArchivo, String strDirectorio, boolean bRutaLocal*/String strArchivo)
    {        
        File fDirectorio = null;
        File fArchivo = null;
        
        fDirectorio = new File(RutaReportes);
        

        fArchivo = new File(fDirectorio, strArchivo);        

        return  ( fDirectorio.exists() && fArchivo.exists());
    }
    /*
    public void GenerateViewReport(Component forma, String report, Integer parametro, String venta, String ultimaletra) 
    {
        BDConexion conexion = BDConexion.ObtenInstancia();
        Connection con = conexion.ObtenConexion();
        try {
            JOptionPane message = new JOptionPane();
            //JasperReport template = JasperManager.loadReport(rPath + report + ".jasper");
            JasperReport template = (JasperReport) JRLoader.loadObject(rPath + report + ".jasper");
            Map parameters = new HashMap();
            parameters.put("clienteID", parametro);
            parameters.put("ventaclave", venta);
            parameters.put("ultimaletra", ultimaletra);
            
            if ( report.compareTo("Historial_de_Pagos_LETTER") == 0 )
            {
                parameters.put("NombreAval", NombreAval);
                parameters.put("DireccionAval", DireccionAval);
            }
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(template, parameters, con);
            if (report.compareTo("Cartilla_de_Pagos_LETTER") != 0) {
                if (message.showConfirmDialog(forma, "Retira el recibo de dinero.\n" +
                        "Mueve la palanca de la impresora.\nPon una hoja tamaño carta.\n" +
                        "Y presiona Si para imprimir.", "Confirmacion", 0, 3) == 0) {
                    JasperPrintManager.printReport(jasperPrint, false);
                }
            } else {
                if (message.showConfirmDialog(forma, "Pon el reverso de la hoja del contrato.\n" +
                        "Y presiona Si para imprimir.", "Confirmacion", 0, 3) == 0) {
                    if (jasperPrint.getPages().size() > 1) {
                        jasperPrint.removePage(1);
                    }
                    JasperPrintManager.printReport(jasperPrint, false);
                }
            }
        //JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            conexion.Kill(con);
        }
    }
    */
}
