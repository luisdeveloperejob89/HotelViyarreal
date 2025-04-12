/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Controlador.ControlHabitaciones;
import Controlador.ReportManager;
import Modelo.Habitacion;
import Modelo.Hospedaje;
import Modelo.Reservacion;
import Modelo.Utilerias;
import Modelo.UtileriasHabitaciones;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Usuario
 */
public class FrmListaHabitaciones extends javax.swing.JInternalFrame
{    
    private List<Habitacion> ListaHabitaciones = null;
    private ControlHabitaciones ControlCuartos = null;
    private Reservacion ReservacionHabitacion = null;
    private Utilerias uAccesibilidad;
    
    /**
     * Creates new form FrmListaHabitaciones
     */
    public FrmListaHabitaciones()
    {
        initComponents();        
        ListaHabitaciones = new ArrayList<Habitacion>();
        ControlCuartos = new ControlHabitaciones();
        uAccesibilidad = new Utilerias();        
        ReservacionHabitacion = new Reservacion();
        //PanelHabitacion_2.setComponentPopupMenu(popMenu);
        AsignaEventosBotonesVentana();
    }
    
    public void RevisaEstados()
    {        
        Timer timer = new Timer();
        timer.schedule(new UtileriasHabitaciones(this.getParent()), 0, 60000);
    }

    public void AsignaEventosBotonesVentana()
    {   
        Action btnAccionAlta = new AbstractAction("ALTA") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnBarraAltaActionPerformed(evt);
            }
        };

        Action btnAccionEntregar = new AbstractAction("ENTREGAR") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnBarraEntregarActionPerformed(evt);
            }
        };

        Action btnAccionLimpieza = new AbstractAction("LIMPIEZA") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnBarraLimpiezaActionPerformed(evt);
            }
        };
        
        Action btnAccionMantenimiento = new AbstractAction("MANTENIMIENTO") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnBarraMantenimientoActionPerformed(evt);
            }
        };
        
        Action btnAccionReservar = new AbstractAction("RESERVAR") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnBarraReservacionActionPerformed(evt);
            }
        };
        
        Action btnAccionImpRecibo = new AbstractAction("IMPRIMIR") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnImprimirReciboActionPerformed(evt);
            }
        };                
        
        Action btnAccionSalir = new AbstractAction("SALIR") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnBarraSalirActionPerformed(evt);
            }
        };                      
        
        btnBarraAlta.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "Pagar"); 
        btnBarraAlta.getActionMap().put("Pagar", btnAccionAlta);
        btnBarraEntregar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "Limpiar"); 
        btnBarraEntregar.getActionMap().put("Limpiar", btnAccionEntregar);
        btnBarraLimpieza.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "Cancelar"); 
        btnBarraLimpieza.getActionMap().put("Cancelar", btnAccionLimpieza); 
        btnBarraMantenimiento.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "F8"); 
        btnBarraMantenimiento.getActionMap().put("F8", btnAccionMantenimiento);        
        btnBarraReservacion.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "F9"); 
        btnBarraReservacion.getActionMap().put("F9", btnAccionReservar);
        btnImprimirRecibo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "F10"); 
        btnImprimirRecibo.getActionMap().put("F10", btnAccionImpRecibo);                
        btnBarraSalir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "F11"); 
        btnBarraSalir.getActionMap().put("F11", btnAccionSalir);
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

        popMenu_H1 = new javax.swing.JPopupMenu();
        miMenuReservar = new javax.swing.JMenuItem();
        miMenuEntregarHabitacion = new javax.swing.JMenuItem();
        miMenuLimpiar = new javax.swing.JMenuItem();
        miMenuMantenimiento = new javax.swing.JMenuItem();
        miMenuDetalleHabitacion = new javax.swing.JMenuItem();
        popMenu_H2 = new javax.swing.JPopupMenu();
        miMenuReservar1 = new javax.swing.JMenuItem();
        miMenuEntregarHabitacion1 = new javax.swing.JMenuItem();
        miMenuLimpiar1 = new javax.swing.JMenuItem();
        miMenuMantenimiento1 = new javax.swing.JMenuItem();
        miMenuDetalleHabitacion1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        PanelHabitacion_1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelEdoH1 = new javax.swing.JLabel();
        btnAceptarH1 = new javax.swing.JButton();
        PanelHabitacion_2 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        labelEdoH2 = new javax.swing.JLabel();
        btnAceptarH2 = new javax.swing.JButton();
        PanelHabitacion_3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        labelEdoH3 = new javax.swing.JLabel();
        btnAceptarH3 = new javax.swing.JButton();
        PanelHabitacion_4 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        labelEdoH4 = new javax.swing.JLabel();
        btnAceptarH4 = new javax.swing.JButton();
        PanelHabitacion_5 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        labelEdoH5 = new javax.swing.JLabel();
        btnAceptarH5 = new javax.swing.JButton();
        PanelHabitacion_6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        labelEdoH6 = new javax.swing.JLabel();
        btnAceptarH6 = new javax.swing.JButton();
        PanelHabitacion_7 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        labelEdoH7 = new javax.swing.JLabel();
        btnAceptarH7 = new javax.swing.JButton();
        PanelHabitacion_8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelEdoH8 = new javax.swing.JLabel();
        btnAceptarH8 = new javax.swing.JButton();
        PanelHabitacion_9 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        labelEdoH9 = new javax.swing.JLabel();
        btnAceptarH9 = new javax.swing.JButton();
        PanelHabitacion_10 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        labelEdoH10 = new javax.swing.JLabel();
        btnAceptarH10 = new javax.swing.JButton();
        PanelHabitacion_11 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        labelEdoH11 = new javax.swing.JLabel();
        btnAceptarH11 = new javax.swing.JButton();
        PanelHabitacion_12 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        labelEdoH12 = new javax.swing.JLabel();
        btnAceptarH12 = new javax.swing.JButton();
        PanelHabitacion_13 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        labelEdoH13 = new javax.swing.JLabel();
        btnAceptarH13 = new javax.swing.JButton();
        PanelHabitacion_14 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        labelEdoH14 = new javax.swing.JLabel();
        btnAceptarH14 = new javax.swing.JButton();
        PanelHabitacion_15 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        labelEdoH15 = new javax.swing.JLabel();
        btnAceptarH15 = new javax.swing.JButton();
        PanelHabitacion_16 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        labelEdoH16 = new javax.swing.JLabel();
        btnAceptarH16 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnBarraAlta = new javax.swing.JButton();
        btnBarraEntregar = new javax.swing.JButton();
        btnBarraLimpieza = new javax.swing.JButton();
        btnBarraMantenimiento = new javax.swing.JButton();
        btnBarraReservacion = new javax.swing.JButton();
        btnImprimirRecibo = new javax.swing.JButton();
        btnBarraSalir = new javax.swing.JButton();

        miMenuReservar.setText("Reservar habitación");
        miMenuReservar.setToolTipText("Generar una reservación para la habitación");
        miMenuReservar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                miMenuReservarActionPerformed(evt);
            }
        });
        popMenu_H1.add(miMenuReservar);

        miMenuEntregarHabitacion.setText("Entregar Habitación");
        popMenu_H1.add(miMenuEntregarHabitacion);

        miMenuLimpiar.setText("Servicio de limpieza");
        miMenuLimpiar.setToolTipText("Enviar servicio de limpieza a la habitación");
        miMenuLimpiar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                miMenuLimpiarActionPerformed(evt);
            }
        });
        popMenu_H1.add(miMenuLimpiar);

        miMenuMantenimiento.setText("Habitación de Mantenimiento");
        popMenu_H1.add(miMenuMantenimiento);

        miMenuDetalleHabitacion.setText("Editar Habitación");
        miMenuDetalleHabitacion.setToolTipText("");
        miMenuDetalleHabitacion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                miMenuDetalleHabitacionActionPerformed(evt);
            }
        });
        popMenu_H1.add(miMenuDetalleHabitacion);

        miMenuReservar1.setText("Reservar habitación");
        miMenuReservar1.setToolTipText("Generar una reservación para la habitación");
        miMenuReservar1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                miMenuReservar1ActionPerformed(evt);
            }
        });
        popMenu_H2.add(miMenuReservar1);

        miMenuEntregarHabitacion1.setText("Entregar Habitación");
        popMenu_H2.add(miMenuEntregarHabitacion1);

        miMenuLimpiar1.setText("Servicio de limpieza");
        miMenuLimpiar1.setToolTipText("Enviar servicio de limpieza a la habitación");
        miMenuLimpiar1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                miMenuLimpiar1ActionPerformed(evt);
            }
        });
        popMenu_H2.add(miMenuLimpiar1);

        miMenuMantenimiento1.setText("Habitación de Mantenimiento");
        popMenu_H2.add(miMenuMantenimiento1);

        miMenuDetalleHabitacion1.setText("Editar Habitación");
        miMenuDetalleHabitacion1.setToolTipText("");
        miMenuDetalleHabitacion1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                miMenuDetalleHabitacion1ActionPerformed(evt);
            }
        });
        popMenu_H2.add(miMenuDetalleHabitacion1);

        setClosable(true);
        setIconifiable(true);
        setTitle("Habitaciones");
        setAutoscrolls(true);

        PanelHabitacion_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_1.setComponentPopupMenu(popMenu_H1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HABITACION 1");

        labelEdoH1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH1.setText("DISPONIBLE");

        btnAceptarH1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_1Layout = new javax.swing.GroupLayout(PanelHabitacion_1);
        PanelHabitacion_1.setLayout(PanelHabitacion_1Layout);
        PanelHabitacion_1Layout.setHorizontalGroup(
            PanelHabitacion_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_1Layout.setVerticalGroup(
            PanelHabitacion_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_2.setComponentPopupMenu(popMenu_H2);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("HABITACION 2");

        labelEdoH2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH2.setText("DISPONIBLE");

        btnAceptarH2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_2Layout = new javax.swing.GroupLayout(PanelHabitacion_2);
        PanelHabitacion_2.setLayout(PanelHabitacion_2Layout);
        PanelHabitacion_2Layout.setHorizontalGroup(
            PanelHabitacion_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_2Layout.setVerticalGroup(
            PanelHabitacion_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_3.setComponentPopupMenu(popMenu_H1);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("HABITACION 3");

        labelEdoH3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH3.setText("DISPONIBLE");

        btnAceptarH3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_3Layout = new javax.swing.GroupLayout(PanelHabitacion_3);
        PanelHabitacion_3.setLayout(PanelHabitacion_3Layout);
        PanelHabitacion_3Layout.setHorizontalGroup(
            PanelHabitacion_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_3Layout.setVerticalGroup(
            PanelHabitacion_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_4.setComponentPopupMenu(popMenu_H1);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("HABITACION 4");

        labelEdoH4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH4.setText("DISPONIBLE");

        btnAceptarH4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_4Layout = new javax.swing.GroupLayout(PanelHabitacion_4);
        PanelHabitacion_4.setLayout(PanelHabitacion_4Layout);
        PanelHabitacion_4Layout.setHorizontalGroup(
            PanelHabitacion_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_4Layout.setVerticalGroup(
            PanelHabitacion_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_5.setComponentPopupMenu(popMenu_H1);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("HABITACION 5");

        labelEdoH5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH5.setText("DISPONIBLE");

        btnAceptarH5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_5Layout = new javax.swing.GroupLayout(PanelHabitacion_5);
        PanelHabitacion_5.setLayout(PanelHabitacion_5Layout);
        PanelHabitacion_5Layout.setHorizontalGroup(
            PanelHabitacion_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_5Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_5Layout.setVerticalGroup(
            PanelHabitacion_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_6.setComponentPopupMenu(popMenu_H1);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("HABITACION 6");

        labelEdoH6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH6.setText("DISPONIBLE");

        btnAceptarH6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_6Layout = new javax.swing.GroupLayout(PanelHabitacion_6);
        PanelHabitacion_6.setLayout(PanelHabitacion_6Layout);
        PanelHabitacion_6Layout.setHorizontalGroup(
            PanelHabitacion_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_6Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_6Layout.setVerticalGroup(
            PanelHabitacion_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_7.setComponentPopupMenu(popMenu_H1);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("HABITACION 7");

        labelEdoH7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH7.setText("DISPONIBLE");

        btnAceptarH7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH7.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_7Layout = new javax.swing.GroupLayout(PanelHabitacion_7);
        PanelHabitacion_7.setLayout(PanelHabitacion_7Layout);
        PanelHabitacion_7Layout.setHorizontalGroup(
            PanelHabitacion_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_7Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_7Layout.setVerticalGroup(
            PanelHabitacion_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_8.setComponentPopupMenu(popMenu_H1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("HABITACION 8");

        labelEdoH8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH8.setText("DISPONIBLE");

        btnAceptarH8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH8.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_8Layout = new javax.swing.GroupLayout(PanelHabitacion_8);
        PanelHabitacion_8.setLayout(PanelHabitacion_8Layout);
        PanelHabitacion_8Layout.setHorizontalGroup(
            PanelHabitacion_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_8Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_8Layout.setVerticalGroup(
            PanelHabitacion_8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_9.setComponentPopupMenu(popMenu_H1);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("HABITACION 9");

        labelEdoH9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH9.setText("DISPONIBLE");

        btnAceptarH9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH9.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_9Layout = new javax.swing.GroupLayout(PanelHabitacion_9);
        PanelHabitacion_9.setLayout(PanelHabitacion_9Layout);
        PanelHabitacion_9Layout.setHorizontalGroup(
            PanelHabitacion_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_9Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_9Layout.setVerticalGroup(
            PanelHabitacion_9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_10.setComponentPopupMenu(popMenu_H1);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("HABITACION 10");

        labelEdoH10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH10.setText("DISPONIBLE");

        btnAceptarH10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH10.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_10Layout = new javax.swing.GroupLayout(PanelHabitacion_10);
        PanelHabitacion_10.setLayout(PanelHabitacion_10Layout);
        PanelHabitacion_10Layout.setHorizontalGroup(
            PanelHabitacion_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_10Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_10Layout.setVerticalGroup(
            PanelHabitacion_10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_11.setComponentPopupMenu(popMenu_H1);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("HABITACION 11");

        labelEdoH11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH11.setText("DISPONIBLE");

        btnAceptarH11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH11.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_11Layout = new javax.swing.GroupLayout(PanelHabitacion_11);
        PanelHabitacion_11.setLayout(PanelHabitacion_11Layout);
        PanelHabitacion_11Layout.setHorizontalGroup(
            PanelHabitacion_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_11Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_11Layout.setVerticalGroup(
            PanelHabitacion_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_12.setComponentPopupMenu(popMenu_H1);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("HABITACION 12");

        labelEdoH12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH12.setText("DISPONIBLE");

        btnAceptarH12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH12.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_12Layout = new javax.swing.GroupLayout(PanelHabitacion_12);
        PanelHabitacion_12.setLayout(PanelHabitacion_12Layout);
        PanelHabitacion_12Layout.setHorizontalGroup(
            PanelHabitacion_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_12Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_12Layout.setVerticalGroup(
            PanelHabitacion_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_13.setComponentPopupMenu(popMenu_H1);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("HABITACION 13");

        labelEdoH13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH13.setText("DISPONIBLE");

        btnAceptarH13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH13.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_13Layout = new javax.swing.GroupLayout(PanelHabitacion_13);
        PanelHabitacion_13.setLayout(PanelHabitacion_13Layout);
        PanelHabitacion_13Layout.setHorizontalGroup(
            PanelHabitacion_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_13Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_13Layout.setVerticalGroup(
            PanelHabitacion_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_14.setComponentPopupMenu(popMenu_H1);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("HABITACION 14");

        labelEdoH14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH14.setText("DISPONIBLE");

        btnAceptarH14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH14.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_14Layout = new javax.swing.GroupLayout(PanelHabitacion_14);
        PanelHabitacion_14.setLayout(PanelHabitacion_14Layout);
        PanelHabitacion_14Layout.setHorizontalGroup(
            PanelHabitacion_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_14Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_14Layout.setVerticalGroup(
            PanelHabitacion_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_15.setComponentPopupMenu(popMenu_H1);
        PanelHabitacion_15.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("HABITACION 15");

        labelEdoH15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH15.setText("DISPONIBLE");

        btnAceptarH15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH15.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_15Layout = new javax.swing.GroupLayout(PanelHabitacion_15);
        PanelHabitacion_15.setLayout(PanelHabitacion_15Layout);
        PanelHabitacion_15Layout.setHorizontalGroup(
            PanelHabitacion_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_15Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_15Layout.setVerticalGroup(
            PanelHabitacion_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelHabitacion_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelHabitacion_16.setComponentPopupMenu(popMenu_H1);
        PanelHabitacion_16.setEnabled(false);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("HABITACION 16");

        labelEdoH16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEdoH16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEdoH16.setText("DISPONIBLE");

        btnAceptarH16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Disponible.png"))); // NOI18N
        btnAceptarH16.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarH16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelHabitacion_16Layout = new javax.swing.GroupLayout(PanelHabitacion_16);
        PanelHabitacion_16.setLayout(PanelHabitacion_16Layout);
        PanelHabitacion_16Layout.setHorizontalGroup(
            PanelHabitacion_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelEdoH16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHabitacion_16Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnAceptarH16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        PanelHabitacion_16Layout.setVerticalGroup(
            PanelHabitacion_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHabitacion_16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEdoH16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptarH16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PanelHabitacion_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PanelHabitacion_8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PanelHabitacion_15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelHabitacion_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(514, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelHabitacion_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelHabitacion_8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelHabitacion_15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelHabitacion_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(434, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jToolBar1.setRollover(true);

        btnBarraAlta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBarraAlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ReservarHabitacion48.png"))); // NOI18N
        btnBarraAlta.setText("ALTA [F5]");
        btnBarraAlta.setActionCommand("");
        btnBarraAlta.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBarraAlta.setFocusable(false);
        btnBarraAlta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBarraAlta.setPreferredSize(new java.awt.Dimension(70, 73));
        btnBarraAlta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBarraAlta.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBarraAltaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBarraAlta);

        btnBarraEntregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBarraEntregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/EntregarHabitacion48.png"))); // NOI18N
        btnBarraEntregar.setText("ENTREGAR [F6]");
        btnBarraEntregar.setToolTipText("");
        btnBarraEntregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBarraEntregar.setFocusable(false);
        btnBarraEntregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBarraEntregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBarraEntregar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBarraEntregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBarraEntregar);

        btnBarraLimpieza.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBarraLimpieza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LimpiezaHabitacion48.png"))); // NOI18N
        btnBarraLimpieza.setText("LIMPIEZA [F7]");
        btnBarraLimpieza.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBarraLimpieza.setFocusable(false);
        btnBarraLimpieza.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBarraLimpieza.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBarraLimpieza.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBarraLimpiezaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBarraLimpieza);

        btnBarraMantenimiento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBarraMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/HabitacionMantenimiento48.png"))); // NOI18N
        btnBarraMantenimiento.setText("MANTENIMIENTO [F8]");
        btnBarraMantenimiento.setToolTipText("Enviar una habitación a servicio de mantenimiento");
        btnBarraMantenimiento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBarraMantenimiento.setFocusable(false);
        btnBarraMantenimiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBarraMantenimiento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBarraMantenimiento.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBarraMantenimientoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBarraMantenimiento);

        btnBarraReservacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBarraReservacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/HabitacionMantenimiento48.png"))); // NOI18N
        btnBarraReservacion.setText("RESERVAR [F9]");
        btnBarraReservacion.setToolTipText("Generar una reservación para una Habitación");
        btnBarraReservacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBarraReservacion.setFocusable(false);
        btnBarraReservacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBarraReservacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBarraReservacion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBarraReservacionActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBarraReservacion);

        btnImprimirRecibo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnImprimirRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/HabitacionMantenimiento48.png"))); // NOI18N
        btnImprimirRecibo.setText("RECIBO [F10]");
        btnImprimirRecibo.setToolTipText("Generar una reservación para una Habitación");
        btnImprimirRecibo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImprimirRecibo.setFocusable(false);
        btnImprimirRecibo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImprimirRecibo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImprimirRecibo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnImprimirReciboActionPerformed(evt);
            }
        });
        jToolBar1.add(btnImprimirRecibo);

        btnBarraSalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBarraSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Salida48.png"))); // NOI18N
        btnBarraSalir.setText("SALIR [F11]");
        btnBarraSalir.setToolTipText("Cerrar ventana");
        btnBarraSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBarraSalir.setFocusable(false);
        btnBarraSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBarraSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBarraSalir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBarraSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBarraSalir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH1ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(1);
    }//GEN-LAST:event_btnAceptarH1ActionPerformed

    private void btnAceptarH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH2ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(2);
    }//GEN-LAST:event_btnAceptarH2ActionPerformed

    private void btnAceptarH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH3ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(3);
    }//GEN-LAST:event_btnAceptarH3ActionPerformed

    private void btnAceptarH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH4ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(4);
    }//GEN-LAST:event_btnAceptarH4ActionPerformed

    private void btnAceptarH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH5ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(5);
    }//GEN-LAST:event_btnAceptarH5ActionPerformed

    private void btnAceptarH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH6ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(6);
    }//GEN-LAST:event_btnAceptarH6ActionPerformed

    private void btnAceptarH7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH7ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(7);
    }//GEN-LAST:event_btnAceptarH7ActionPerformed

    private void btnAceptarH8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH8ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(8);
    }//GEN-LAST:event_btnAceptarH8ActionPerformed

    private void btnAceptarH9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH9ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(9);
    }//GEN-LAST:event_btnAceptarH9ActionPerformed

    private void btnAceptarH10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH10ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(10);
    }//GEN-LAST:event_btnAceptarH10ActionPerformed

    private void btnAceptarH11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH11ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(11);
    }//GEN-LAST:event_btnAceptarH11ActionPerformed

    private void btnAceptarH12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH12ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(12);
    }//GEN-LAST:event_btnAceptarH12ActionPerformed

    private void btnAceptarH13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH13ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(13);
    }//GEN-LAST:event_btnAceptarH13ActionPerformed

    private void btnAceptarH14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH14ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(14);
    }//GEN-LAST:event_btnAceptarH14ActionPerformed

    private void btnAceptarH15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH15ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(15);
    }//GEN-LAST:event_btnAceptarH15ActionPerformed

    private void btnAceptarH16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarH16ActionPerformed
        // TODO add your handling code here:
        AgregarArrendamiento(16);
    }//GEN-LAST:event_btnAceptarH16ActionPerformed

    private void miMenuLimpiarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miMenuLimpiarActionPerformed
    {//GEN-HEADEREND:event_miMenuLimpiarActionPerformed
        // TODO add your handling code here:        
        //uAccesibilidad.MuestraMensaje("En servicio... " + evt.getSource().getClass().getName());        
        System.out.println("En servicio... " + evt.getSource().getClass().getName());
    }//GEN-LAST:event_miMenuLimpiarActionPerformed

    private void miMenuDetalleHabitacionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miMenuDetalleHabitacionActionPerformed
    {//GEN-HEADEREND:event_miMenuDetalleHabitacionActionPerformed
        // TODO add your handling code here:
        MuestraDetalleHabitacionPopUp(1);
    }//GEN-LAST:event_miMenuDetalleHabitacionActionPerformed

    public void MuestraDetalleHabitacionPopUp(int iHabitacion)
    {
        FrmDetalleHabitacionD frm = new FrmDetalleHabitacionD(null, true, iHabitacion);
        
        frm.show();
    }
    
    private void btnBarraEntregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarraEntregarActionPerformed
        // TODO add your handling code here:
        EntregaHabitacion(true, -1);
    }//GEN-LAST:event_btnBarraEntregarActionPerformed

    public void EntregaHabitacion(boolean bRequiereVentana, int iIdHabAux)
    {
        int iIdHabitacion = -1;
        FrmDlgCheckOut frmAux = null;
        Hospedaje hRegistro = null;
        FrmDlgSeleccionarHabitacion frm = null;
        
        if ( bRequiereVentana )
        {
            frm = new FrmDlgSeleccionarHabitacion(null, true, 0);
            frm.show(true);
            iIdHabitacion = frm.getHabitacion();  

            if ( iIdHabitacion != -1 )
            {
                hRegistro = new Hospedaje();

                if ( hRegistro.ExisteHospedajeConEsaHabitacion(iIdHabitacion) )
                {
                    if ( uAccesibilidad.MuestraMensajeConfirmacion("¿Deseas entregar la habitación: " + iIdHabitacion) == 0 )
                    {
                        frmAux = new FrmDlgCheckOut(null, true);            
                        frmAux.show();

                        if ( frmAux.isPresionoAceptar() && frmAux.isPuedeEntregarHabitacion() )
                        {
                            hRegistro.setIdHabitacion(iIdHabitacion);                

                            if ( hRegistro.EntregaHabitacionDeHospedaje(frmAux.getObservaciones()) )
                            {
                                uAccesibilidad.MuestraMensaje("Se ha liberado la habitación.");
                                uAccesibilidad.RepintaFormasEnMenu(this.getParent());              
                            }
                            else
                            {
                                uAccesibilidad.MuestraMensaje("Ocurrió un problema al tratar de cambiar el Estado de la Habitación.");
                            }
                        }            
                    }
                }
                else
                {
                    uAccesibilidad.MuestraMensaje("La Habitación actual no está ocupada.");
                }                  
            }   
        }
        else
        {
            iIdHabitacion = iIdHabAux; 

            if ( iIdHabitacion != -1 )
            {
                hRegistro = new Hospedaje();

                if ( uAccesibilidad.MuestraMensajeConfirmacion("¿Deseas entregar la habitación: " + iIdHabitacion) == 0 )
                {
                    frmAux = new FrmDlgCheckOut(null, true);            
                    frmAux.show();

                    if ( frmAux.isPresionoAceptar() && frmAux.isPuedeEntregarHabitacion() )
                    {
                        hRegistro.setIdHabitacion(iIdHabitacion);                

                        if ( hRegistro.EntregaHabitacionDeHospedaje(frmAux.getObservaciones()) )
                        {
                            uAccesibilidad.MuestraMensaje("Se ha liberado la habitación.");
                            uAccesibilidad.RepintaFormasEnMenu(this.getParent());              
                        }
                        else
                        {
                            uAccesibilidad.MuestraMensaje("Ocurrió un problema al tratar de cambiar el Estado de la Habitación.");
                        }
                    }            
                }                                
            }
        }                                                                   
    }       
   
    private void btnBarraAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarraAltaActionPerformed
        // TODO add your handling code here:
        MuestraSeleccionHabitaciones(0);
    }//GEN-LAST:event_btnBarraAltaActionPerformed

    private void btnBarraSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarraSalirActionPerformed
        // TODO add your handling code here:
        Salir();
    }//GEN-LAST:event_btnBarraSalirActionPerformed

    private void btnBarraLimpiezaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBarraLimpiezaActionPerformed
    {//GEN-HEADEREND:event_btnBarraLimpiezaActionPerformed
        // TODO add your handling code here:
        CambiaEdoHabitacion(3); //En servicio.
    }//GEN-LAST:event_btnBarraLimpiezaActionPerformed

    private void btnBarraMantenimientoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBarraMantenimientoActionPerformed
    {//GEN-HEADEREND:event_btnBarraMantenimientoActionPerformed
        // TODO add your handling code here:
        CambiaEdoHabitacion(4); //En mantenimiento.
    }//GEN-LAST:event_btnBarraMantenimientoActionPerformed

    private void miMenuReservarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miMenuReservarActionPerformed
    {//GEN-HEADEREND:event_miMenuReservarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miMenuReservarActionPerformed

    private void miMenuReservar1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miMenuReservar1ActionPerformed
    {//GEN-HEADEREND:event_miMenuReservar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miMenuReservar1ActionPerformed

    private void miMenuLimpiar1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miMenuLimpiar1ActionPerformed
    {//GEN-HEADEREND:event_miMenuLimpiar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miMenuLimpiar1ActionPerformed

    private void miMenuDetalleHabitacion1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miMenuDetalleHabitacion1ActionPerformed
    {//GEN-HEADEREND:event_miMenuDetalleHabitacion1ActionPerformed
        // TODO add your handling code here:
        MuestraDetalleHabitacionPopUp(2);
    }//GEN-LAST:event_miMenuDetalleHabitacion1ActionPerformed

    private void btnBarraReservacionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBarraReservacionActionPerformed
    {//GEN-HEADEREND:event_btnBarraReservacionActionPerformed
        // TODO add your handling code here:
        GenerarReservacion();
    }//GEN-LAST:event_btnBarraReservacionActionPerformed

    private void btnImprimirReciboActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnImprimirReciboActionPerformed
    {//GEN-HEADEREND:event_btnImprimirReciboActionPerformed
        // TODO add your handling code here:
        ImprimeRecibo();
    }//GEN-LAST:event_btnImprimirReciboActionPerformed

    public void ImprimeRecibo()
    {
        int iIdHabitacion = -1;
        int iEstado = 0;
        FrmDlgSeleccionarHabitacion frm = new FrmDlgSeleccionarHabitacion(null, true, 0);
        
        frm.show(true);
        iIdHabitacion = frm.getHabitacion();  
        iEstado = ListaHabitaciones.get(iIdHabitacion - 1).getEstado();
        
        if ( iIdHabitacion != -1 )
        {
            if ( ( iEstado == 1 ) || ( iEstado == 5 ) )
            {
                try
                {
                    ReportManager rmReporte = new ReportManager();
                    Hospedaje Registro = new Hospedaje();
                    
                    if ( Registro.InstanciaObjetoPorHabitacion(iIdHabitacion) )
                    {                    
                        rmReporte.GeneraVistaReporteAltaHospedaje(Registro);
                    }
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
            }
            else
            {
                uAccesibilidad.MuestraMensaje("No se puede imprimir el Recibo a una Habitación que NO esté Ocupada.");
            }            
        }                
    }
    
    public void GenerarReservacion()
    {
        FrmDlgReservarHabitacion frm = new FrmDlgReservarHabitacion(null, true);
        
        frm.show(true);                
    }
            
    public void CambiaEdoHabitacion(int iEstado)
    {
        int iIdHabitacion = -1;
        String strMensaje = null;
        Habitacion hAux = null;
        FrmDlgSeleccionarHabitacion frm = new FrmDlgSeleccionarHabitacion(null, true, 0);
        
        frm.show(true);
        iIdHabitacion = frm.getHabitacion();  

        if ( iIdHabitacion != -1 )
        {
            if ( iEstado == 3 )
            {
                strMensaje = "¿Deseas enviar la Habitación: " + iIdHabitacion + " a Servicio de Limpieza?";
            }
            else
            {
                strMensaje = "¿Deseas enviar la Habitación: " + iIdHabitacion + " a Mantenimiento?";
            }
            
            if ( uAccesibilidad.MuestraMensajeConfirmacion(strMensaje) == 0 )
            {
                hAux = new Habitacion();
                
                if ( hAux.CambiaEdoHabitacion(iIdHabitacion, iEstado) )
                {
                    uAccesibilidad.MuestraMensaje("Habitación actualizada correctamente.");
                    uAccesibilidad.RepintaFormasEnMenu(this.getParent());
                }
                else
                {
                    uAccesibilidad.MuestraMensaje("Ocurrió un error al tratar de actualizar la Habitación.");
                }
            }                        
        }                
    }
    
    public void Salir()
    {
        this.dispose();
    }
       
    public void MuestraSeleccionHabitaciones(int iTipoOperacion)
    {        
        FrmDlgSeleccionarHabitacion frm = new FrmDlgSeleccionarHabitacion(null, true, iTipoOperacion);
        
        frm.show(true);
        AgregarArrendamiento(frm.getHabitacion());   
    }
    
    public void LlenaListaDeHabitaciones()
    {        
        ListaHabitaciones = ControlCuartos.DameListaHabitaciones();
        
        if ( ListaHabitaciones != null )
        {                                    
            uAccesibilidad.ConfiguraElementos(labelEdoH1, btnAceptarH1, ListaHabitaciones.get(0).getEstado());
            uAccesibilidad.ConfiguraElementos(labelEdoH2, btnAceptarH2, ListaHabitaciones.get(1).getEstado());            
            uAccesibilidad.ConfiguraElementos(labelEdoH3, btnAceptarH3, ListaHabitaciones.get(2).getEstado());
            uAccesibilidad.ConfiguraElementos(labelEdoH4, btnAceptarH4, ListaHabitaciones.get(3).getEstado());
            uAccesibilidad.ConfiguraElementos(labelEdoH5, btnAceptarH5, ListaHabitaciones.get(4).getEstado());            
            uAccesibilidad.ConfiguraElementos(labelEdoH6, btnAceptarH6, ListaHabitaciones.get(5).getEstado());
            uAccesibilidad.ConfiguraElementos(labelEdoH7, btnAceptarH7, ListaHabitaciones.get(6).getEstado());            
            uAccesibilidad.ConfiguraElementos(labelEdoH8, btnAceptarH8, ListaHabitaciones.get(7).getEstado());
            uAccesibilidad.ConfiguraElementos(labelEdoH9, btnAceptarH9, ListaHabitaciones.get(8).getEstado());
            uAccesibilidad.ConfiguraElementos(labelEdoH10, btnAceptarH10, ListaHabitaciones.get(9).getEstado());            
            uAccesibilidad.ConfiguraElementos(labelEdoH11, btnAceptarH11, ListaHabitaciones.get(10).getEstado());
            uAccesibilidad.ConfiguraElementos(labelEdoH12, btnAceptarH12, ListaHabitaciones.get(11).getEstado());            
            uAccesibilidad.ConfiguraElementos(labelEdoH13, btnAceptarH13, ListaHabitaciones.get(12).getEstado());
            uAccesibilidad.ConfiguraElementos(labelEdoH14, btnAceptarH14, ListaHabitaciones.get(13).getEstado());
            uAccesibilidad.ConfiguraElementos(labelEdoH15, btnAceptarH15, ListaHabitaciones.get(14).getEstado());            
            uAccesibilidad.ConfiguraElementos(labelEdoH16, btnAceptarH16, ListaHabitaciones.get(15).getEstado());            
        }
    }
            
    public void AgregarArrendamiento(int iID)
    {                
        int iEstado = 0;
        FrmAgregarHospedaje frmPrueba = null;
                
        iEstado = ListaHabitaciones.get(iID - 1).getEstado();
        
        if ( iEstado == 0 )
        {
            frmPrueba = new FrmAgregarHospedaje(null, true, iID, true);
            frmPrueba.show(true);
        }
        else if ( ( iEstado == 1 ) || ( iEstado == 5 ) ) //Ocupado y Ocupado por Reservación
        {
            EntregaHabitacion(false, iID);
        }
        else if ( iEstado == 3 || iEstado == 4 ) //En Servicio o Mantenimiento
        {
            if ( uAccesibilidad.MuestraMensajeConfirmacion("¿Desea dejar disponible la Habitación: " + iID + "?") == 0 )
            {
                if ( ListaHabitaciones.get(iID - 1).LiberaHabitacion(iID) )
                {
                    uAccesibilidad.MuestraMensaje("Habitación " + iID + " disponible");
                }
                else
                {
                    uAccesibilidad.MuestraMensaje("Ocurrió un mensaje al tratar de actualizar la Habitación.");
                }
            }
        }
                    
        uAccesibilidad.RepintaFormasEnMenu(this.getParent());
        //LlenaListaDeHabitaciones();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelHabitacion_1;
    private javax.swing.JPanel PanelHabitacion_10;
    private javax.swing.JPanel PanelHabitacion_11;
    private javax.swing.JPanel PanelHabitacion_12;
    private javax.swing.JPanel PanelHabitacion_13;
    private javax.swing.JPanel PanelHabitacion_14;
    private javax.swing.JPanel PanelHabitacion_15;
    private javax.swing.JPanel PanelHabitacion_16;
    private javax.swing.JPanel PanelHabitacion_2;
    private javax.swing.JPanel PanelHabitacion_3;
    private javax.swing.JPanel PanelHabitacion_4;
    private javax.swing.JPanel PanelHabitacion_5;
    private javax.swing.JPanel PanelHabitacion_6;
    private javax.swing.JPanel PanelHabitacion_7;
    private javax.swing.JPanel PanelHabitacion_8;
    private javax.swing.JPanel PanelHabitacion_9;
    private javax.swing.JButton btnAceptarH1;
    private javax.swing.JButton btnAceptarH10;
    private javax.swing.JButton btnAceptarH11;
    private javax.swing.JButton btnAceptarH12;
    private javax.swing.JButton btnAceptarH13;
    private javax.swing.JButton btnAceptarH14;
    private javax.swing.JButton btnAceptarH15;
    private javax.swing.JButton btnAceptarH16;
    private javax.swing.JButton btnAceptarH2;
    private javax.swing.JButton btnAceptarH3;
    private javax.swing.JButton btnAceptarH4;
    private javax.swing.JButton btnAceptarH5;
    private javax.swing.JButton btnAceptarH6;
    private javax.swing.JButton btnAceptarH7;
    private javax.swing.JButton btnAceptarH8;
    private javax.swing.JButton btnAceptarH9;
    private javax.swing.JButton btnBarraAlta;
    private javax.swing.JButton btnBarraEntregar;
    private javax.swing.JButton btnBarraLimpieza;
    private javax.swing.JButton btnBarraMantenimiento;
    private javax.swing.JButton btnBarraReservacion;
    private javax.swing.JButton btnBarraSalir;
    private javax.swing.JButton btnImprimirRecibo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelEdoH1;
    private javax.swing.JLabel labelEdoH10;
    private javax.swing.JLabel labelEdoH11;
    private javax.swing.JLabel labelEdoH12;
    private javax.swing.JLabel labelEdoH13;
    private javax.swing.JLabel labelEdoH14;
    private javax.swing.JLabel labelEdoH15;
    private javax.swing.JLabel labelEdoH16;
    private javax.swing.JLabel labelEdoH2;
    private javax.swing.JLabel labelEdoH3;
    private javax.swing.JLabel labelEdoH4;
    private javax.swing.JLabel labelEdoH5;
    private javax.swing.JLabel labelEdoH6;
    private javax.swing.JLabel labelEdoH7;
    private javax.swing.JLabel labelEdoH8;
    private javax.swing.JLabel labelEdoH9;
    private javax.swing.JMenuItem miMenuDetalleHabitacion;
    private javax.swing.JMenuItem miMenuDetalleHabitacion1;
    private javax.swing.JMenuItem miMenuEntregarHabitacion;
    private javax.swing.JMenuItem miMenuEntregarHabitacion1;
    private javax.swing.JMenuItem miMenuLimpiar;
    private javax.swing.JMenuItem miMenuLimpiar1;
    private javax.swing.JMenuItem miMenuMantenimiento;
    private javax.swing.JMenuItem miMenuMantenimiento1;
    private javax.swing.JMenuItem miMenuReservar;
    private javax.swing.JMenuItem miMenuReservar1;
    private javax.swing.JPopupMenu popMenu_H1;
    private javax.swing.JPopupMenu popMenu_H2;
    // End of variables declaration//GEN-END:variables
}
