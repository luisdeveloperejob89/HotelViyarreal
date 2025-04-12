/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Controlador.ReportManager;
import Modelo.Bitacora;
import Modelo.Configuracion;
import Modelo.Habitacion;
import Modelo.Hospedaje;
import Modelo.Huesped;
import Modelo.Reservacion;
import Modelo.Usuario;
import Modelo.Utilerias;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author WIN7UTL64
 */
public class FrmAgregarHospedaje extends javax.swing.JDialog 
{
    Utilerias uAccesibilidad;  
    private String CorreoEmisor;
    private String CorreoDestinatario;
    private String ContraseniaEmisor;
    private Habitacion Cuarto;
    private Huesped Cliente;
    private Hospedaje Registro;
    private boolean RequiereFactura = false;    
    private Usuario uUsuario = Usuario.ObtenInstancia();
    private Bitacora bBitacora = Bitacora.ObtenInstancia();
    
    /**
     * Creates new form FrmDialogoPrueba
     */
    public FrmAgregarHospedaje(java.awt.Frame parent, boolean modal, int iNumeroHabitacion, boolean bEntregarHabitacion) 
    {
        super(parent, modal);        
        initComponents();
        AsignaEventosBotonesVentana();
        
        Date dtFecha = new Date();        
        uAccesibilidad = new Utilerias();   
        this.CorreoEmisor = null;
        this.ContraseniaEmisor = null;
        this.CorreoDestinatario = null;        
        this.Registro = new Hospedaje();
        this.Cuarto = this.Registro.getCuarto();
        this.Cliente = this.Registro.getCliente();
        this.Cuarto.DameUnaHabitacion(iNumeroHabitacion);
        this.Cliente.setId(iNumeroHabitacion);
        dtFechaInicio.setDate(dtFecha);
        txtDescuento.setText("0.0");
        AgregaDiasFechaFinal(1);
        LabelNombreHabitacion.setText("HABITACIÓN " + iNumeroHabitacion);  
        labelEncargado.setText(uUsuario.DameNombreCompletoUsuario());
        MuestraOcultaPanelFiscales();
        LlenaDatosEmail();
        txtNombre.requestFocus();
    }

    public void AsignaEventosBotonesVentana()
    {
        Action btnAccionPagar = new AbstractAction("PAGAR") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnAceptarActionPerformed(evt);
            }
        };

        Action btnAccionLimpiar = new AbstractAction("LIMPIAR") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnLimpiarActionPerformed(evt);
            }
        };

        Action btnAccionCancelar = new AbstractAction("CERRAR") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnCancelarActionPerformed(evt);
            }
        };
        
        Action btnAccionListEmpresa = new AbstractAction("CERRAR") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnEmpresasActionPerformed(evt);
            }
        };
        
        Action btnAccionListReservaciones = new AbstractAction("RESERVACIONES") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnReservacionesActionPerformed(evt);
            }
        };
         
        btnAceptar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "Pagar"); 
        btnAceptar.getActionMap().put("Pagar", btnAccionPagar);
        btnLimpiar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "Limpiar"); 
        btnLimpiar.getActionMap().put("Limpiar", btnAccionLimpiar);
        btnCancelar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "Cancelar"); 
        btnCancelar.getActionMap().put("Cancelar", btnAccionCancelar); 
        btnEmpresas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "F2"); 
        btnEmpresas.getActionMap().put("F2", btnAccionListEmpresa);        
        btnReservaciones.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "F5"); 
        btnReservaciones.getActionMap().put("F5", btnAccionListReservaciones);
    }
    
    public void LlenaDatosEmail()
    {
        Configuracion cAux = new Configuracion();
        cAux.DameDatosConfiguracion();
        this.CorreoEmisor = cAux.getEmail();
        this.ContraseniaEmisor = cAux.getContrasenia();
        this.CorreoDestinatario = cAux.getEmailCont();
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        PanelTotales = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDias = new javax.swing.JTextField();
        txtPrecioPorDia = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnCancelar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        btnReservaciones = new javax.swing.JButton();
        PanelFechas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dtFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dtFechaFinal = new com.toedter.calendar.JDateChooser();
        PanelHabitacion = new javax.swing.JPanel();
        labelImagen = new javax.swing.JLabel();
        labelTituloHabitacion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        memoDescripcion = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        labelEncargado = new javax.swing.JLabel();
        PanelCuarto = new javax.swing.JPanel();
        LabelNombreHabitacion = new javax.swing.JLabel();
        Pestanias = new javax.swing.JTabbedPane();
        PanelCliente = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtApPaterno = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtApMaterno = new javax.swing.JTextField();
        labelRFC = new javax.swing.JLabel();
        txtRfc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPais = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cmboxProcedencia = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        PanelEmpresasDescuento = new javax.swing.JPanel();
        labelNombreEmpresa = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JTextField();
        btnEmpresas = new javax.swing.JButton();
        cboxDescuentoEmpresas = new javax.swing.JCheckBox();
        cboxRequiereFactura = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        PanelFiscales = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNumExt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtPoblacion = new javax.swing.JTextField();
        txtMunicipio = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtCP = new javax.swing.JTextField();
        txtNumInt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alta de Hospedaje");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowActivated(java.awt.event.WindowEvent evt)
            {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        PanelTotales.setBorder(javax.swing.BorderFactory.createTitledBorder("Totales"));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Precio por día");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Total");

        jLabel8.setDisplayedMnemonic('d');
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setLabelFor(txtDias);
        jLabel8.setText("Días");

        txtDias.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtDias.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtDiasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtDiasKeyTyped(evt);
            }
        });

        txtPrecioPorDia.setEditable(false);
        txtPrecioPorDia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPrecioPorDia.setForeground(new java.awt.Color(0, 0, 153));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 0, 0));

        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAceptar.setText("PAGAR (F3)");
        btnAceptar.setToolTipText("Realiza pago ");
        btnAceptar.setActionCommand("");
        btnAceptar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar.setText("CANCELAR (F4)");
        btnCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLimpiar.setText("LIMPIAR (F1)");
        btnLimpiar.setToolTipText("Limpia los campos");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Descuento %");

        txtDescuento.setEditable(false);
        txtDescuento.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtDescuento.setForeground(new java.awt.Color(0, 153, 0));

        btnReservaciones.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReservaciones.setText("RESERVACIONES [F5]");
        btnReservaciones.setToolTipText("Realiza pago ");
        btnReservaciones.setActionCommand("");
        btnReservaciones.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnReservacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelTotalesLayout = new javax.swing.GroupLayout(PanelTotales);
        PanelTotales.setLayout(PanelTotalesLayout);
        PanelTotalesLayout.setHorizontalGroup(
            PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTotalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTotalesLayout.createSequentialGroup()
                        .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTotalesLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(45, 45, 45))
                            .addGroup(PanelTotalesLayout.createSequentialGroup()
                                .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDias, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(txtPrecioPorDia))
                            .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiar)
                            .addComponent(btnAceptar)
                            .addComponent(btnCancelar)
                            .addComponent(btnReservaciones))
                        .addGap(18, 18, 18))
                    .addGroup(PanelTotalesLayout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
        );

        PanelTotalesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAceptar, btnCancelar, btnLimpiar, btnReservaciones});

        PanelTotalesLayout.setVerticalGroup(
            PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTotalesLayout.createSequentialGroup()
                .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPrecioPorDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(PanelTotalesLayout.createSequentialGroup()
                        .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(btnReservaciones))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        PanelFechas.setBorder(javax.swing.BorderFactory.createTitledBorder("Hospedaje"));

        jLabel1.setDisplayedMnemonic('h');
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setLabelFor(dtFechaInicio);
        jLabel1.setText("Fecha de entrada");

        dtFechaInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                dtFechaInicioPropertyChange(evt);
            }
        });

        jLabel2.setDisplayedMnemonic('s');
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setLabelFor(dtFechaFinal);
        jLabel2.setText("Fecha de salida");

        dtFechaFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                dtFechaFinalPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout PanelFechasLayout = new javax.swing.GroupLayout(PanelFechas);
        PanelFechas.setLayout(PanelFechasLayout);
        PanelFechasLayout.setHorizontalGroup(
            PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFechasLayout.createSequentialGroup()
                .addGroup(PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PanelFechasLayout.setVerticalGroup(
            PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFechasLayout.createSequentialGroup()
                .addGroup(PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelFechasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFechasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        PanelHabitacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Habitación"));

        labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoHotel_64.png"))); // NOI18N

        labelTituloHabitacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTituloHabitacion.setText("jLabel1");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Descripción");

        memoDescripcion.setEditable(false);
        memoDescripcion.setColumns(20);
        memoDescripcion.setRows(5);
        jScrollPane1.setViewportView(memoDescripcion);

        javax.swing.GroupLayout PanelHabitacionLayout = new javax.swing.GroupLayout(PanelHabitacion);
        PanelHabitacion.setLayout(PanelHabitacionLayout);
        PanelHabitacionLayout.setHorizontalGroup(
            PanelHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelImagen)
                .addGap(35, 35, 35)
                .addGroup(PanelHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelHabitacionLayout.createSequentialGroup()
                        .addComponent(labelTituloHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))
                    .addGroup(PanelHabitacionLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        PanelHabitacionLayout.setVerticalGroup(
            PanelHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelHabitacionLayout.createSequentialGroup()
                        .addComponent(labelTituloHabitacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(213, 213, 213))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Encargado"));

        labelEncargado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelEncargado.setText("Precio por día");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelEncargado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelEncargado)
                .addContainerGap())
        );

        PanelCuarto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        LabelNombreHabitacion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelNombreHabitacion.setText("HABITACIÓN 1");

        javax.swing.GroupLayout PanelCuartoLayout = new javax.swing.GroupLayout(PanelCuarto);
        PanelCuarto.setLayout(PanelCuartoLayout);
        PanelCuartoLayout.setHorizontalGroup(
            PanelCuartoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCuartoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelNombreHabitacion)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        PanelCuartoLayout.setVerticalGroup(
            PanelCuartoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCuartoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(LabelNombreHabitacion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Húesped"));

        jLabel16.setDisplayedMnemonic('N');
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setLabelFor(txtNombre);
        jLabel16.setText("* Nombre");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel17.setDisplayedMnemonic('P');
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setLabelFor(txtApPaterno);
        jLabel17.setText("* Apellido Paterno");
        jLabel17.setToolTipText("");

        txtApPaterno.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtApPaternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtApPaternoKeyTyped(evt);
            }
        });

        jLabel18.setDisplayedMnemonic('m');
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setLabelFor(txtApMaterno);
        jLabel18.setText("Apellido Materno");

        txtApMaterno.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtApMaternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtApMaternoKeyTyped(evt);
            }
        });

        labelRFC.setDisplayedMnemonic('r');
        labelRFC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelRFC.setLabelFor(txtRfc);
        labelRFC.setText("RFC");

        txtRfc.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtRfcKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtRfcKeyTyped(evt);
            }
        });

        jLabel5.setDisplayedMnemonic('d');
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setLabelFor(txtDireccion);
        jLabel5.setText("Dirección");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtDireccionKeyPressed(evt);
            }
        });

        jLabel6.setDisplayedMnemonic('t');
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setLabelFor(txtTelefono);
        jLabel6.setText("Teléfono");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtTelefonoKeyPressed(evt);
            }
        });

        jLabel7.setDisplayedMnemonic('s');
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setLabelFor(txtPais);
        jLabel7.setText("País");

        jLabel19.setDisplayedMnemonic('o');
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setLabelFor(cmboxProcedencia);
        jLabel19.setText("Procedencia");

        cmboxProcedencia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nacional", "Extranjero" }));
        cmboxProcedencia.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                cmboxProcedenciaPropertyChange(evt);
            }
        });

        jLabel9.setDisplayedMnemonic('l');
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setLabelFor(txtEmail);
        jLabel9.setText("Email");

        PanelEmpresasDescuento.setBorder(javax.swing.BorderFactory.createTitledBorder("Descuento a empresas"));
        PanelEmpresasDescuento.setEnabled(false);

        labelNombreEmpresa.setDisplayedMnemonic('e');
        labelNombreEmpresa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelNombreEmpresa.setLabelFor(btnEmpresas);
        labelNombreEmpresa.setText("Empresa:");
        labelNombreEmpresa.setEnabled(false);

        txtEmpresa.setEnabled(false);

        btnEmpresas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEmpresas.setText("F2");
        btnEmpresas.setEnabled(false);
        btnEmpresas.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEmpresasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelEmpresasDescuentoLayout = new javax.swing.GroupLayout(PanelEmpresasDescuento);
        PanelEmpresasDescuento.setLayout(PanelEmpresasDescuentoLayout);
        PanelEmpresasDescuentoLayout.setHorizontalGroup(
            PanelEmpresasDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEmpresasDescuentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNombreEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpresas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelEmpresasDescuentoLayout.setVerticalGroup(
            PanelEmpresasDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEmpresasDescuentoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(PanelEmpresasDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombreEmpresa)
                    .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmpresas))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        cboxDescuentoEmpresas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cboxDescuentoEmpresas.setMnemonic('a');
        cboxDescuentoEmpresas.setText("Aplica descuento a empresa");
        cboxDescuentoEmpresas.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cboxDescuentoEmpresasActionPerformed(evt);
            }
        });

        cboxRequiereFactura.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cboxRequiereFactura.setMnemonic('f');
        cboxRequiereFactura.setText("Requiere factura");
        cboxRequiereFactura.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cboxRequiereFacturaActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("(*) Campos obligatorios");

        javax.swing.GroupLayout PanelClienteLayout = new javax.swing.GroupLayout(PanelCliente);
        PanelCliente.setLayout(PanelClienteLayout);
        PanelClienteLayout.setHorizontalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(labelRFC)
                    .addComponent(jLabel6)
                    .addComponent(txtRfc)
                    .addComponent(txtTelefono))
                .addGap(18, 18, 18)
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(cmboxProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(txtApPaterno, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel18)
                                .addComponent(txtApMaterno, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                .addComponent(txtPais))))
                    .addComponent(txtDireccion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(PanelEmpresasDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelClienteLayout.createSequentialGroup()
                            .addComponent(cboxDescuentoEmpresas)
                            .addGap(27, 27, 27)
                            .addComponent(cboxRequiereFactura)))
                    .addComponent(jLabel25))
                .addGap(0, 91, Short.MAX_VALUE))
        );
        PanelClienteLayout.setVerticalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelRFC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelClienteLayout.createSequentialGroup()
                        .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelClienteLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelClienteLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel19)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmboxProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxDescuentoEmpresas)
                    .addComponent(cboxRequiereFactura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelEmpresasDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        Pestanias.addTab("Húesped", PanelCliente);

        PanelFiscales.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Fiscales"));

        jLabel10.setDisplayedMnemonic('a');
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setLabelFor(txtCalle);
        jLabel10.setText("* Calle:");

        txtCalle.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtCalleKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtCalleKeyTyped(evt);
            }
        });

        jLabel11.setDisplayedMnemonic('x');
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setLabelFor(txtNumExt);
        jLabel11.setText("Num Ext:");

        txtNumExt.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtNumExtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtNumExtKeyTyped(evt);
            }
        });

        jLabel12.setDisplayedMnemonic('u');
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setLabelFor(txtNumInt);
        jLabel12.setText("Num Int:");
        jLabel12.setToolTipText("");

        jLabel20.setDisplayedMnemonic('l');
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setLabelFor(txtColonia);
        jLabel20.setText("* Colonia:");

        txtColonia.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtColoniaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtColoniaKeyTyped(evt);
            }
        });

        jLabel21.setDisplayedMnemonic('o');
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setLabelFor(txtPoblacion);
        jLabel21.setText("* Población:");

        txtPoblacion.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtPoblacionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtPoblacionKeyTyped(evt);
            }
        });

        txtMunicipio.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtMunicipioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtMunicipioKeyTyped(evt);
            }
        });

        jLabel22.setDisplayedMnemonic('m');
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setLabelFor(txtMunicipio);
        jLabel22.setText("* Municipio:");

        jLabel23.setDisplayedMnemonic('e');
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setLabelFor(txtEstado);
        jLabel23.setText("* Estado:");

        txtEstado.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtEstadoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtEstadoKeyTyped(evt);
            }
        });

        jLabel24.setDisplayedMnemonic('c');
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setLabelFor(txtCP);
        jLabel24.setText("* CP:");

        txtCP.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtCPKeyTyped(evt);
            }
        });

        txtNumInt.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtNumIntKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtNumIntKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("(*) Campos obligatorios");

        javax.swing.GroupLayout PanelFiscalesLayout = new javax.swing.GroupLayout(PanelFiscales);
        PanelFiscales.setLayout(PanelFiscalesLayout);
        PanelFiscalesLayout.setHorizontalGroup(
            PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFiscalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFiscalesLayout.createSequentialGroup()
                        .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(293, 293, 293))
                    .addGroup(PanelFiscalesLayout.createSequentialGroup()
                        .addComponent(txtNumExt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumInt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(292, 292, 292))))
            .addGroup(PanelFiscalesLayout.createSequentialGroup()
                .addComponent(jLabel26)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelFiscalesLayout.setVerticalGroup(
            PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFiscalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNumExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtNumInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(74, 74, 74))
        );

        Pestanias.addTab("Fiscales", PanelFiscales);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Pestanias, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTotales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelCuarto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PanelTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Pestanias))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
                
    public boolean RealizoIngresoEfectivo()
    {
        boolean bOk = true;        
        
        FrmDlgCapturaEfectivoEnVenta frm = new FrmDlgCapturaEfectivoEnVenta(null, true, Double.parseDouble(txtTotal.getText()));
        
        frm.show();
        
        return bOk;
    }        
    
    public boolean HabitacionDisponibleEnFechas()
    {
        boolean bOk = true;
        int i = 0;
        Reservacion rAux = null;
        List<Date> lstFechas = new ArrayList<Date>();
        
        try
        {
            lstFechas = uAccesibilidad.DameListaIntervaloFechas(dtFechaInicio.getDate(), dtFechaFinal.getDate());
            
            if ( lstFechas != null )
            {
                rAux = new Reservacion();
                
                while ( i < lstFechas.size() )
                {                    
                    if ( rAux.HabitacionReservada(this.Cuarto.getId(), lstFechas.get(i)) )
                    {
                        bOk = false;                     
                    }
                    
                    i++;
                }
            }
        }
        catch ( Exception e )
        {
            bOk = false;
            System.err.println("Error en HabitacionDisponibleEnFechas(): " + e.getMessage());
        }
        
        return bOk;
    }
    
    public void AgregaHospedaje()
    {               
        Date dtFecha = null;                  
        java.sql.Timestamp sq = new java.sql.Timestamp(dtFechaInicio.getDate().getTime());        
        
        if ( HuespedValido() )
        {
            if ( HabitacionDisponibleEnFechas() )
            {
                if ( uAccesibilidad.MuestraMensajeConfirmacion("¿Realmente quieres agregar el hospedaje?") == 0 )
                {
                    if ( RealizoIngresoEfectivo() )
                    {
                        AgregaCliente();
                        dtFecha = new Date();
                        Registro.setCliente(this.Cliente);        
                        Registro.setIdHabitacion(this.Cuarto.getId());
                        //Registro.setFechaEntrada(dtFechaInicio.getDate());            
                        Registro.setFechaEntrada(sq);
                        Registro.setFechaSalida(dtFechaFinal.getDate());
                        Registro.setDias(uAccesibilidad.DameDiasEntreFechas(dtFechaInicio.getDate(), dtFechaFinal.getDate()));
                        Registro.setFormaPago(0);
                        Registro.setEstado(1);
                        Registro.setMes(dtFecha.getMonth());
                        Registro.setAnio(dtFecha.getYear() + 1990);
                        Registro.setTotal(Double.parseDouble(txtTotal.getText()));
                        Registro.setIdUsuario(uUsuario.getID());

                        if ( Registro.Insertar(true) )
                        {
                            uAccesibilidad.MuestraMensaje("Se ha registrado correctamente la Habitación: " + this.Cuarto.getId());                
                            bBitacora.InsertarAccion(uUsuario.getID(), "EL USUARIO: " + uUsuario.getNomUsuario() + " AGREGÓ UN HOSPEDAJE CON UN TOTAL DE " + txtTotal.getText() + " PARA LA HABITACIÓN: " + this.Cuarto.getId() , "AGREGAR HOSPEDAJE", Bitacora.AGREGAR_HOSPEDAJE);                        
                            GeneraRecibo();                
                            EnviarCorreoElectronico();
                            LimpiaCampos();
                            this.dispose();      

                        }
                        else
                        {
                            uAccesibilidad.MuestraMensaje("Ocurrió un error al tratar de agregar la reservación");
                        }
                    }
                }   
            }
            else
            {
                uAccesibilidad.MuestraMensaje("La habitación se encuentra reservada en alguna de las Fechas que ha ingresado, favor de ingresar otro rango de fechas.");
            }                        
        }
        else
        {
            MuestraMensajeCampoVacio();
        }
    }
    
    public void EnviarCorreoElectronico()
    {                
        if ( RequiereFactura )
        {            
            if ( enviarConGMail() )
            {
                uAccesibilidad.MuestraMensaje("Correo electrónico enviado correctamente al contador");
            }
        }
    }
    
    public String DameCuerpoMensaje()
    {
        String strCuerpoMensaje = null;
        
        strCuerpoMensaje  = "Datos de Facturación: ";
        strCuerpoMensaje += "\nNombre: " + txtNombre.getText();
        strCuerpoMensaje += "\nApellido Paterno: " + txtApPaterno.getText();
        strCuerpoMensaje += "\nApellido Materno: " + txtApMaterno.getText();
        strCuerpoMensaje += "\nRFC: " + txtRfc.getText();
        strCuerpoMensaje += "\nTeléfono: " + txtTelefono.getText();
        strCuerpoMensaje += "\nEmail: " + txtEmail.getText();      
        strCuerpoMensaje += "\nCalle: " + txtCalle.getText();
        strCuerpoMensaje += "\nColonia: " + txtColonia.getText();
        strCuerpoMensaje += "\nPoblación: " + txtPoblacion.getText();
        strCuerpoMensaje += "\nMunicipio: " + txtMunicipio.getText();
        strCuerpoMensaje += "\nEstado: " + txtEstado.getText();
        strCuerpoMensaje += "\nCódigo Postal: " + txtCP.getText();
        
        return strCuerpoMensaje;
    }
    
   private boolean enviarConGMail() 
   {
       boolean bOk = false;
       String destinatario = this.CorreoDestinatario;
       String asunto = "Se solicita la generación de una factura electrónica para RFC:" + txtRfc.getText();
       String cuerpo = DameCuerpoMensaje();
       /*
        this.CorreoEmisor = cAux.getEmail();
        this.ContraseniaEmisor = cAux.getContrasenia();
        this.CorreoDestinatario = cAux.getEmailCont();
       */
    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
    //String remitente = "HotelVillarealFacturacion@gmail.com";  //Para la dirección nomcuenta@gmail.co       
       String remitente = this.CorreoEmisor;

    Properties props = System.getProperties();
    //props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.user", remitente);
    props.put("mail.smtp.clave", this.ContraseniaEmisor);    //La clave de la cuenta
    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try 
    {
        message.setFrom(new InternetAddress(remitente));
        //message.addRecipient(Message.RecipientType.TO, "luis.ejob@gmail.com");   //Se podrían añadir varios de la misma manera
        message.addRecipients(Message.RecipientType.TO, destinatario);
        message.setSubject(asunto);
        message.setText(cuerpo);
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", remitente, this.ContraseniaEmisor);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        bOk = true;
    }
    catch (MessagingException me) 
    {
        me.printStackTrace();   //Si se produce un error
        bOk = false;
    }
    
    return bOk;
}
    
    public void LimpiarCampos()
    {
        txtNombre.setText(null);
        txtApPaterno.setText(null);
        txtApMaterno.setText(null);
        txtRfc.setText(null);
        txtDireccion.setText(null);
        txtTelefono.setText(null);
        txtPais.setText(null);
        cmboxProcedencia.setSelectedIndex(0);
        txtEmail.setText(null);
        cboxDescuentoEmpresas.setSelected(false);
        cboxRequiereFactura.setSelected(false);
        txtEmpresa.setText(null);
        txtCalle.setText(null);
        txtNumExt.setText(null);
        txtNumInt.setText(null);
        txtColonia.setText(null);
        txtPoblacion.setText(null);
        txtMunicipio.setText(null);
        txtEstado.setText(null);
        txtCP.setText(null);
    }
    
    public void GeneraRecibo()
    {
        try
        {
            ReportManager rmReporte = new ReportManager();
            
            Registro.setId(Registro.DameUltimoIDHospedado());
            rmReporte.GeneraVistaReporteAltaHospedaje(Registro);
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public void HabilitaDeshEmpresa(boolean bActivar)
    {
        PanelEmpresasDescuento.setEnabled(bActivar);
        labelNombreEmpresa.setEnabled(bActivar);
        btnEmpresas.setEnabled(bActivar);
        
        if ( !bActivar )
        {
            Cliente.setEmpresa(0);
            txtEmpresa.setText(null);
            txtDescuento.setText("0");
            CalularDiasEntreFechas();
        }
    }
    
    public void MuestraListaEmpresasDescuentos()
    {
        FrmListaEmpresas frm = new FrmListaEmpresas(null, true);
        
        frm.show(true);
        this.Cliente.setEmpresa(frm.getIDEmpresa());
        txtEmpresa.setText(frm.getNombreEmpresa());
        txtDescuento.setText(String.valueOf(frm.getDescuento()));
        CalularDiasEntreFechas();
    }
    
    public void LimpiaCampos()
    {
        Date dtFecha = new Date();
        
        dtFechaInicio.setDate(dtFecha);
        dtFechaFinal.setDate(dtFecha);
        CalularDiasEntreFechas();
        txtDescuento.setText("0.0");
        txtNombre.setText(null);
        txtApPaterno.setText(null);
        txtApMaterno.setText(null);
        txtRfc.setText(null);
        txtDireccion.setText(null);
        txtTelefono.setText(null);
        txtPais.setText(null);
        cmboxProcedencia.setSelectedIndex(0);
        txtEmail.setText(null);
        cboxDescuentoEmpresas.setSelected(false);
        cboxRequiereFactura.setSelected(false);
        txtEmpresa.setText(null);
        txtCalle.setText(null);
        txtNumExt.setText(null);
        txtNumInt.setText(null);
        txtColonia.setText(null);
        txtPoblacion.setText(null);
        txtMunicipio.setText(null);
        txtEstado.setText(null);
        txtCP.setText(null);
        txtNombre.requestFocus();
    }
    
    public void AgregarCliente()
    {                       
        if ( HuespedValido() )
        {
            
            
            if ( Cliente.Insertar() )
            {                
                uAccesibilidad.MuestraMensaje("Huesped agregado correctamente");
                LimpiarCampos();
            }            
        }
        else
        {
            MuestraMensajeCampoVacio();
        }
    }
    
    public void MuestraMensajeCampoVacio()
    {
        String strMensaje = "";
        
        if ( txtNombre.getText().isEmpty() )
        {
            strMensaje = "Nombre";
        }
        else if ( txtApPaterno.getText().isEmpty() )
        {
            strMensaje = "Apellido Paterno";    
        }
        else if ( txtPais.getText().isEmpty() )
        {
            strMensaje = "País";   
        }
        
        if ( RequiereFactura )
        {
            if ( txtCalle.getText().isEmpty() )
            {
                strMensaje = "Calle";
            }
            else if ( txtNumExt.getText().isEmpty() )
            {
                strMensaje = "Número Exterior";
            }
            else if ( txtColonia.getText().isEmpty() )
            {
                strMensaje = "Colonia";
            }
            else if ( txtPoblacion.getText().isEmpty() )
            {
                strMensaje = "Población";
            }
            else if ( txtMunicipio.getText().isEmpty() )
            {
                strMensaje = "Municipio";
            }
            else if ( txtEstado.getText().isEmpty() )
            {
                strMensaje = "Estado";
            }
            else if ( txtCP.getText().isEmpty() )
            {
                strMensaje = "Código Postal";
            }
        }
        
        uAccesibilidad.MuestraMensaje("El campo " + strMensaje + " no puede ser vacío");
        
        if ( txtNombre.getText().isEmpty() )
        {
            txtNombre.requestFocus();
            txtNombre.selectAll();
        }
        else if ( txtApPaterno.getText().isEmpty() )
        {
            txtApPaterno.requestFocus();
            txtApPaterno.selectAll();
        }
        else if ( txtPais.getText().isEmpty() )
        {
            txtPais.requestFocus();
            txtPais.selectAll();
        }
        
        if ( RequiereFactura )
        {
            if ( txtCalle.getText().isEmpty() )
            {
                txtCalle.requestFocus();
                txtCalle.selectAll();
            }
            else if ( txtNumExt.getText().isEmpty() )
            {
                txtNumExt.requestFocus();
                txtNumExt.selectAll();
            }
            else if ( txtColonia.getText().isEmpty() )
            {
                txtColonia.requestFocus();
                txtColonia.selectAll();
            }
            else if ( txtPoblacion.getText().isEmpty() )
            {
                txtPoblacion.requestFocus();
                txtPoblacion.selectAll();
            }
            else if ( txtMunicipio.getText().isEmpty() )
            {
                txtMunicipio.requestFocus();
                txtMunicipio.selectAll();
            }
            else if ( txtEstado.getText().isEmpty() )
            {
                txtEstado.requestFocus();
                txtEstado.selectAll();
            }
            else if ( txtCP.getText().isEmpty() )
            {
                txtCP.requestFocus();
                txtCP.selectAll();
            }
        }
    }
    
    public boolean HuespedValido()
    {
        boolean bOk = true;
                
        if ( txtNombre.getText().isEmpty() || txtApPaterno.getText().isEmpty() || txtPais.getText().isEmpty())
        {
            bOk = false;
        }
        
        if ( RequiereFactura )
        {
            if ( txtRfc.getText().isEmpty() || txtCalle.getText().isEmpty() || txtColonia.getText().isEmpty() || txtPoblacion.getText().isEmpty() || 
                 txtMunicipio.getText().isEmpty() || txtEstado.getText().isEmpty() || txtCP.getText().isEmpty() )
            {
                bOk = false;
            }
        }
        
        return bOk;
    }
       
    public void AgregaCliente()
    {
        Cliente.setNombre(txtNombre.getText());
        Cliente.setApPaterno(txtApPaterno.getText());
        Cliente.setApMaterno(txtApMaterno.getText());
        Cliente.setRfc(txtRfc.getText());
        Cliente.setDireccion(txtDireccion.getText());
        Cliente.setTelefono(txtTelefono.getText());
        Cliente.setPais(txtPais.getText());
        Cliente.setProcedencia(cmboxProcedencia.getSelectedIndex());
        Cliente.setEmail(txtEmail.getText());
        Cliente.setCalle(txtCalle.getText());
        Cliente.setNumExt(txtNumExt.getText());
        Cliente.setNumInt(txtNumInt.getText());
        Cliente.setColonia(txtColonia.getText());
        Cliente.setPoblacion(txtPoblacion.getText());
        Cliente.setMunicipio(txtMunicipio.getText());
        Cliente.setEstado(txtEstado.getText());
        Cliente.setCP(txtCP.getText());
    }
    
    public void CalularDiasEntreFechas()
    {
        int iDias = 0;
        double dDescuento = 0.0;
        double dTotal = 0.0;

        txtDias.setText(String.valueOf(uAccesibilidad.DameDiasEntreFechas(dtFechaInicio.getDate(), dtFechaFinal.getDate())));
        iDias = Integer.parseInt(txtDias.getText());                
        dTotal = Cuarto.getPrecioDia() * iDias;
        
        if ( cboxDescuentoEmpresas.isSelected() )
        {
            dDescuento = ( dTotal * Double.parseDouble(txtDescuento.getText()) ) / 100;
            dTotal -= dDescuento;
        }
        
        txtTotal.setText(String.valueOf(dTotal));
    }
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        if ( Cuarto != null )
        {
            labelTituloHabitacion.setText(Cuarto.getHabitacion());
            memoDescripcion.setText(Cuarto.getDescripcion());            
            txtPrecioPorDia.setText(String.valueOf(Cuarto.getPrecioDia()));
       }
    }//GEN-LAST:event_formWindowActivated

    private void dtFechaFinalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFechaFinalPropertyChange
        // TODO add your handling code here:
        if ( dtFechaFinal != null )
        {
            if ( dtFechaFinal.getDate() != null )
            {             
                if ( dtFechaFinal.getDate().getTime() >= dtFechaInicio.getDate().getTime() )
                {
                    CalularDiasEntreFechas();
                }
                else
                {
                    uAccesibilidad.MuestraMensaje("La fecha final no puede ser menor a la inicial");
                    dtFechaFinal.setDate(dtFechaInicio.getDate());
                }
            }
        }
    }//GEN-LAST:event_dtFechaFinalPropertyChange

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        dtFechaInicio = null;
        dtFechaFinal = null;
    }//GEN-LAST:event_formWindowClosing

    private void dtFechaInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFechaInicioPropertyChange
        // TODO add your handling code here:
        /*Date dtFecha = new Date();
        
        if ( dtFechaInicio != null && dtFechaFinal != null )
        {
            if ( dtFechaInicio.getDate() != null && dtFechaFinal.getDate() != null )
            {             
                if ( dtFechaInicio.getDate().getTime() < dtFechaFinal.getDate().getTime()  )
                {
                    CalularDiasEntreFechas();
                }
                else
                {
                    uAccesibilidad.MuestraMensaje("La fecha inicial no puede ser menor a la final");
                    dtFechaInicio.setDate(dtFecha);
                }
            }
        }*/
    }//GEN-LAST:event_dtFechaInicioPropertyChange

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNombreKeyTyped
    {//GEN-HEADEREND:event_txtNombreKeyTyped
        // TODO add your handling code here:
        uAccesibilidad.RestringirCaracteres(txtNombre, evt, 50);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApPaternoKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtApPaternoKeyTyped
    {//GEN-HEADEREND:event_txtApPaternoKeyTyped
        // TODO add your handling code here:
        uAccesibilidad.RestringirCaracteres(txtApPaterno, evt, 50);
    }//GEN-LAST:event_txtApPaternoKeyTyped

    private void txtApMaternoKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtApMaternoKeyTyped
    {//GEN-HEADEREND:event_txtApMaternoKeyTyped
        // TODO add your handling code here:
        uAccesibilidad.RestringirCaracteres(txtApMaterno, evt, 45);
    }//GEN-LAST:event_txtApMaternoKeyTyped

    private void txtRfcKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtRfcKeyTyped
    {//GEN-HEADEREND:event_txtRfcKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String strCadena = null;
                
        //uAccesibilidad.RestringirCaracteres(txtRfc, evt, 20);                                 
        
        if ( Character.isLowerCase(c) )
        {
            strCadena = ("" + c).toUpperCase();
            c = strCadena.charAt(0);
            evt.setKeyChar(c);
        }
        
        if ( txtRfc.getText().length() == 20 )
        {
            evt.consume();            
        }
    }//GEN-LAST:event_txtRfcKeyTyped

    private void btnEmpresasActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEmpresasActionPerformed
    {//GEN-HEADEREND:event_btnEmpresasActionPerformed
        // TODO add your handling code here:
        MuestraListaEmpresasDescuentos();
    }//GEN-LAST:event_btnEmpresasActionPerformed
   
    
    private void cboxDescuentoEmpresasActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cboxDescuentoEmpresasActionPerformed
    {//GEN-HEADEREND:event_cboxDescuentoEmpresasActionPerformed
        // TODO add your handling code here:
        Cliente.setAplicaDescuento(cboxDescuentoEmpresas.isSelected() ? 1 : 0);
        HabilitaDeshEmpresa(cboxDescuentoEmpresas.isSelected());
    }//GEN-LAST:event_cboxDescuentoEmpresasActionPerformed

    private void cboxRequiereFacturaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cboxRequiereFacturaActionPerformed
    {//GEN-HEADEREND:event_cboxRequiereFacturaActionPerformed
        // TODO add your handling code here:
        RequiereFactura = cboxRequiereFactura.isSelected();
        Cliente.setRequiereFactura(RequiereFactura ? 1 : 0);
        MuestraOcultaPanelFiscales();
    }//GEN-LAST:event_cboxRequiereFacturaActionPerformed

    public void MuestraOcultaPanelFiscales()
    {
        if ( RequiereFactura )
        {            
            if ( Pestanias.getTabCount() == 1 )
            {
                labelRFC.setText("* RFC");
                Pestanias.addTab("Fiscales", PanelFiscales);
            }
        }
        else
        {
            Pestanias.remove(PanelFiscales);
        }
    }
    
    private void txtCalleKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtCalleKeyTyped
    {//GEN-HEADEREND:event_txtCalleKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCalleKeyTyped

    private void txtNumExtKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNumExtKeyTyped
    {//GEN-HEADEREND:event_txtNumExtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumExtKeyTyped

    private void txtColoniaKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtColoniaKeyTyped
    {//GEN-HEADEREND:event_txtColoniaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColoniaKeyTyped

    private void txtPoblacionKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtPoblacionKeyTyped
    {//GEN-HEADEREND:event_txtPoblacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPoblacionKeyTyped

    private void txtMunicipioKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtMunicipioKeyTyped
    {//GEN-HEADEREND:event_txtMunicipioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMunicipioKeyTyped

    private void txtEstadoKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtEstadoKeyTyped
    {//GEN-HEADEREND:event_txtEstadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoKeyTyped

    private void txtCPKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtCPKeyTyped
    {//GEN-HEADEREND:event_txtCPKeyTyped
        // TODO add your handling code here:
        char cCaracter = evt.getKeyChar();
        
        if ( txtCP.getText().length() == 5 || !Character.isDigit(cCaracter) )
        {
            evt.consume();
        }                                    
    }//GEN-LAST:event_txtCPKeyTyped

    private void txtNumIntKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNumIntKeyTyped
    {//GEN-HEADEREND:event_txtNumIntKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumIntKeyTyped

    private void cmboxProcedenciaPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_cmboxProcedenciaPropertyChange
    {//GEN-HEADEREND:event_cmboxProcedenciaPropertyChange
        // TODO add your handling code here:
        if ( cmboxProcedencia.getSelectedIndex() == 0 )
        {
            txtPais.setText("México");
            txtPais.setEnabled(false);
        }
        else
        {
            txtPais.setText(null);
            txtPais.setEnabled(true);
        }
    }//GEN-LAST:event_cmboxProcedenciaPropertyChange

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNombreKeyPressed
    {//GEN-HEADEREND:event_txtNombreKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) ) 
        {
            txtApPaterno.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtApPaternoKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtApPaternoKeyPressed
    {//GEN-HEADEREND:event_txtApPaternoKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtApMaterno.requestFocus();
        }
    }//GEN-LAST:event_txtApPaternoKeyPressed

    private void txtApMaternoKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtApMaternoKeyPressed
    {//GEN-HEADEREND:event_txtApMaternoKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtRfc.requestFocus();
        }
    }//GEN-LAST:event_txtApMaternoKeyPressed

    private void txtRfcKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtRfcKeyPressed
    {//GEN-HEADEREND:event_txtRfcKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtDireccion.requestFocus();
        }
    }//GEN-LAST:event_txtRfcKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtDireccionKeyPressed
    {//GEN-HEADEREND:event_txtDireccionKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtTelefonoKeyPressed
    {//GEN-HEADEREND:event_txtTelefonoKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void txtCalleKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtCalleKeyPressed
    {//GEN-HEADEREND:event_txtCalleKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtNumExt.requestFocus();
        }
    }//GEN-LAST:event_txtCalleKeyPressed

    private void txtNumExtKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNumExtKeyPressed
    {//GEN-HEADEREND:event_txtNumExtKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtNumInt.requestFocus();
        }
    }//GEN-LAST:event_txtNumExtKeyPressed

    private void txtNumIntKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNumIntKeyPressed
    {//GEN-HEADEREND:event_txtNumIntKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtColonia.requestFocus();
        }
    }//GEN-LAST:event_txtNumIntKeyPressed

    private void txtColoniaKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtColoniaKeyPressed
    {//GEN-HEADEREND:event_txtColoniaKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtPoblacion.requestFocus();
        }
    }//GEN-LAST:event_txtColoniaKeyPressed

    private void txtPoblacionKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtPoblacionKeyPressed
    {//GEN-HEADEREND:event_txtPoblacionKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtMunicipio.requestFocus();
        }
    }//GEN-LAST:event_txtPoblacionKeyPressed

    private void txtMunicipioKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtMunicipioKeyPressed
    {//GEN-HEADEREND:event_txtMunicipioKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtEstado.requestFocus();
        }
    }//GEN-LAST:event_txtMunicipioKeyPressed

    private void txtEstadoKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtEstadoKeyPressed
    {//GEN-HEADEREND:event_txtEstadoKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtCP.requestFocus();
        }
    }//GEN-LAST:event_txtEstadoKeyPressed

    private void btnReservacionesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnReservacionesActionPerformed
    {//GEN-HEADEREND:event_btnReservacionesActionPerformed
        // TODO add your handling code here:
        DespliegaListaReservaciones();
    }//GEN-LAST:event_btnReservacionesActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLimpiarActionPerformed
    {//GEN-HEADEREND:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        LimpiaCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelarActionPerformed
    {//GEN-HEADEREND:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAceptarActionPerformed
    {//GEN-HEADEREND:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        AgregaHospedaje();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtDiasKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtDiasKeyTyped
    {//GEN-HEADEREND:event_txtDiasKeyTyped
        // TODO add your handling code here:
        char cCaracter = evt.getKeyChar();

        // Verificar si la tecla pulsada no es un digito
        if ( !Character.isDigit(cCaracter) /*|| cCaracter == '0'*/ )
        {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_txtDiasKeyTyped

    private void txtDiasKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtDiasKeyReleased
    {//GEN-HEADEREND:event_txtDiasKeyReleased
        // TODO add your handling code here:
        int iDias = 0;
        char cCaracter = evt.getKeyChar();

        if ( Character.isDigit(cCaracter) )
        {
            //iDias = Integer.parseInt(String.valueOf(cCaracter));
            iDias = Integer.parseInt(txtDias.getText());

            if ( iDias > 0 )
            {
                AgregaDiasFechaFinal(iDias);
            }
            else
            {
                uAccesibilidad.MuestraMensaje("Los días no pueden ser 0 o negativos.");
            }
        }
    }//GEN-LAST:event_txtDiasKeyReleased
                   
    public void DespliegaListaReservaciones()
    {
        CnsReservacionesHabitaciones frm = new CnsReservacionesHabitaciones(null, true, this.Cuarto.getId());
        
        frm.show();
    }
    
    public void AgregaDiasFechaFinal(int iDias)
    {
        Calendar calendar = Calendar.getInstance();
        Date dtFecha = new Date();
        
        dtFechaFinal.setDate(dtFecha);            
        calendar.setTime(dtFechaFinal.getDate());            
        calendar.add(Calendar.DAY_OF_YEAR, iDias);
        dtFechaFinal.setDate(calendar.getTime());                   
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarHospedaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarHospedaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarHospedaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarHospedaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAgregarHospedaje dialog = new FrmAgregarHospedaje(new javax.swing.JFrame(), true, 1, false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelNombreHabitacion;
    private javax.swing.JPanel PanelCliente;
    private javax.swing.JPanel PanelCuarto;
    private javax.swing.JPanel PanelEmpresasDescuento;
    private javax.swing.JPanel PanelFechas;
    private javax.swing.JPanel PanelFiscales;
    private javax.swing.JPanel PanelHabitacion;
    private javax.swing.JPanel PanelTotales;
    private javax.swing.JTabbedPane Pestanias;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEmpresas;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnReservaciones;
    private javax.swing.JCheckBox cboxDescuentoEmpresas;
    private javax.swing.JCheckBox cboxRequiereFactura;
    private javax.swing.JComboBox cmboxProcedencia;
    private com.toedter.calendar.JDateChooser dtFechaFinal;
    private com.toedter.calendar.JDateChooser dtFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelEncargado;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelNombreEmpresa;
    private javax.swing.JLabel labelRFC;
    private javax.swing.JLabel labelTituloHabitacion;
    private javax.swing.JTextArea memoDescripcion;
    private javax.swing.JTextField txtApMaterno;
    private javax.swing.JTextField txtApPaterno;
    private javax.swing.JTextField txtCP;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDias;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumExt;
    private javax.swing.JTextField txtNumInt;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtPoblacion;
    private javax.swing.JTextField txtPrecioPorDia;
    private javax.swing.JTextField txtRfc;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
