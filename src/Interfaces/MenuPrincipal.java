/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Modelo.CorteCaja;
import Modelo.Turnos;
import Modelo.Usuario;
import Modelo.Utilerias;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author Luis Alberto Flores Castillo
 */
public class MenuPrincipal extends javax.swing.JFrame 
{
    private Usuario uUsuario = Usuario.ObtenInstancia();   
    private CorteCaja cCorteCaja = CorteCaja.ObtenInstancia();
    private Turnos tTurnos = Turnos.ObtenInstancia();
    
    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal()
    {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        UIManager.put("Button.focusInputMap", new UIDefaults.LazyInputMap(new Object[]{
                    "ENTER", "pressed",
                    "released ENTER", "released"
                }));
        initComponents();
        AsignaEventosBotonesVentana();
        btnIniciarTurno.setVisible(false);
        btnCerrarTurno.setVisible(false);
    }
    
    public void AsignaEventosBotonesVentana()
    {
        Action btnAccionClientesIzq = new AbstractAction("CLIENTES") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnClientesIzqActionPerformed(evt);
            }
        };
        
        Action btnAccionHabIzq = new AbstractAction("HABITACIONES") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnHabitacionesIzqActionPerformed(evt);
            }
        };
                 
        Action btnAccionHospIzq = new AbstractAction("HABITACIONES") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnHospedajesIzqActionPerformed(evt);
            }
        };
        
        Action btnAccionConfIzq = new AbstractAction("CONFERENCIAS") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnConferenciasIzqActionPerformed(evt);
            }
        };
        
        Action btnAccionSalIzq = new AbstractAction("CONFERENCIAS") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnSalirIzquierdaActionPerformed(evt);
            }
        };                        
        
        btnClientesIzq.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "CLIENTES"); 
        btnClientesIzq.getActionMap().put("CLIENTES", btnAccionClientesIzq);
        btnHabitacionesIzq.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "HABITACIONES"); 
        btnHabitacionesIzq.getActionMap().put("HABITACIONES", btnAccionHabIzq);
        btnHospedajesIzq.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "HOSPEDAJES"); 
        btnHospedajesIzq.getActionMap().put("HOSPEDAJES", btnAccionHospIzq);
        btnConferenciasIzq.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "CONFERENCIAS"); 
        btnConferenciasIzq.getActionMap().put("CONFERENCIAS", btnAccionConfIzq);
        btnSalirIzquierda.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "SALIR"); 
        btnSalirIzquierda.getActionMap().put("SALIR", btnAccionSalIzq);
    }
    
    public void IniciaSesionUsuario()
    {
        switch ( uUsuario.getTipo() )
        {
            case 0:
                HabilitaDeshabilitaTodos(true);
            break;
            case 1:
                HabilitaParaRecepcionista();
            break;
        }
    }
    
    public void HabilitaParaRecepcionista()
    {
        mMenu.setEnabled(true);
        mClientes.setEnabled(true);
        mHabitaciones.setEnabled(false);
        mReservaciones.setEnabled(true);
        mRecepcionistas.setEnabled(true);
        mRecepAlta.setEnabled(false);
        mRecepCns.setEnabled(false);
        mRecepSesion.setEnabled(true);
        mEmpresas.setEnabled(false);
        mConfiguracion.setEnabled(false);
        mAyuda.setEnabled(true);         
        PanelIzquierdo.setEnabled(true);
        btnClientesIzq.setEnabled(true);
        btnHospedajesIzq.setEnabled(false);
        btnHabitacionesIzq.setEnabled(true);
        btnConferenciasIzq.setEnabled(true);
        btnSesion.setEnabled(true);
        btnIniciarTurno.setEnabled(false);
        mConfOpcAvan.setEnabled(false);
        mGastos.setEnabled(true);
    }
    
    public void HabilitaDeshabilitaTodos(boolean bHabilitar)
    {
        mMenu.setEnabled(bHabilitar);
        mClientes.setEnabled(bHabilitar);
        mHabitaciones.setEnabled(bHabilitar);
        mReservaciones.setEnabled(bHabilitar);
        mRecepcionistas.setEnabled(bHabilitar);
        mEmpresas.setEnabled(bHabilitar);
        mConfiguracion.setEnabled(bHabilitar);
        mAyuda.setEnabled(bHabilitar);         
        PanelIzquierdo.setEnabled(bHabilitar);
        btnClientesIzq.setEnabled(bHabilitar);
        btnHospedajesIzq.setEnabled(bHabilitar);
        btnHabitacionesIzq.setEnabled(bHabilitar);
        btnConferenciasIzq.setEnabled(bHabilitar);
        btnIniciarTurno.setEnabled(bHabilitar);
        mConfOpcAvan.setEnabled(bHabilitar);
        mGastos.setEnabled(bHabilitar);
    }        
    
    public void MostrarHabitaciones()
    {
        FrmListaHabitaciones frm = new FrmListaHabitaciones();
        jDesktopPane1.add(frm);
        frm.show();
        frm.LlenaListaDeHabitaciones();   
        frm.RevisaEstados();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        PanelIzquierdo = new javax.swing.JPanel();
        btnClientesIzq = new javax.swing.JButton();
        btnHabitacionesIzq = new javax.swing.JButton();
        btnHospedajesIzq = new javax.swing.JButton();
        btnConferenciasIzq = new javax.swing.JButton();
        btnSalirIzquierda = new javax.swing.JButton();
        btnSesion = new javax.swing.JButton();
        btnIniciarTurno = new javax.swing.JButton();
        btnCerrarTurno = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        mClientes = new javax.swing.JMenu();
        mCtesAgregar = new javax.swing.JMenuItem();
        mCtesCnsHues = new javax.swing.JMenuItem();
        mHabitaciones = new javax.swing.JMenu();
        mHabCns = new javax.swing.JMenuItem();
        mReservaciones = new javax.swing.JMenu();
        mResAlta = new javax.swing.JMenuItem();
        mRecepcionistas = new javax.swing.JMenu();
        mRecepAlta = new javax.swing.JMenuItem();
        mRecepCns = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mRecepSesion = new javax.swing.JMenuItem();
        mGastos = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        mEmpresas = new javax.swing.JMenu();
        mEmpAlta = new javax.swing.JMenuItem();
        mEmpCns = new javax.swing.JMenuItem();
        mConfiguracion = new javax.swing.JMenu();
        mConfOpc = new javax.swing.JMenuItem();
        mConfOpcAvan = new javax.swing.JMenuItem();
        mAyuda = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel");
        setName("FrM"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowOpened(java.awt.event.WindowEvent evt)
            {
                formWindowOpened(evt);
            }
        });

        jDesktopPane1.setAutoscrolls(true);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1094, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jDesktopPane1);

        PanelIzquierdo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));

        javax.swing.GroupLayout PanelIzquierdoLayout = new javax.swing.GroupLayout(PanelIzquierdo);
        PanelIzquierdo.setLayout(PanelIzquierdoLayout);
        PanelIzquierdoLayout.setHorizontalGroup(
            PanelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelIzquierdoLayout.setVerticalGroup(
            PanelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1073, Short.MAX_VALUE)
        );

        btnClientesIzq.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnClientesIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clientes_Barra.png"))); // NOI18N
        btnClientesIzq.setText("Clientes (F1)");
        btnClientesIzq.setToolTipText("Muestra el listado de los Huespedes agregados");
        btnClientesIzq.setBorder(null);
        btnClientesIzq.setBorderPainted(false);
        btnClientesIzq.setFocusable(false);
        btnClientesIzq.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClientesIzq.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClientesIzq.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnClientesIzqActionPerformed(evt);
            }
        });

        btnHabitacionesIzq.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHabitacionesIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Habitacion_Barra.png"))); // NOI18N
        btnHabitacionesIzq.setText("Habitaciones (F2)");
        btnHabitacionesIzq.setToolTipText("Muestra las Habitaciones y el estado de las mismas");
        btnHabitacionesIzq.setBorder(null);
        btnHabitacionesIzq.setBorderPainted(false);
        btnHabitacionesIzq.setFocusable(false);
        btnHabitacionesIzq.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHabitacionesIzq.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHabitacionesIzq.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHabitacionesIzqActionPerformed(evt);
            }
        });

        btnHospedajesIzq.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHospedajesIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Hospedajes_BarraIzquierda.png"))); // NOI18N
        btnHospedajesIzq.setText("Hospedajes (F3)");
        btnHospedajesIzq.setToolTipText("Muestra los registros generados ");
        btnHospedajesIzq.setBorder(null);
        btnHospedajesIzq.setBorderPainted(false);
        btnHospedajesIzq.setFocusable(false);
        btnHospedajesIzq.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHospedajesIzq.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHospedajesIzq.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHospedajesIzqActionPerformed(evt);
            }
        });

        btnConferenciasIzq.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnConferenciasIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Conferencias48.png"))); // NOI18N
        btnConferenciasIzq.setText("Conferencias (F4)");
        btnConferenciasIzq.setToolTipText("Sala de conferencias");
        btnConferenciasIzq.setBorder(null);
        btnConferenciasIzq.setBorderPainted(false);
        btnConferenciasIzq.setFocusable(false);
        btnConferenciasIzq.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConferenciasIzq.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConferenciasIzq.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnConferenciasIzqActionPerformed(evt);
            }
        });

        btnSalirIzquierda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalirIzquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Salida48.png"))); // NOI18N
        btnSalirIzquierda.setText("Salir (F12)");
        btnSalirIzquierda.setToolTipText("Salir del sistema");
        btnSalirIzquierda.setBorder(null);
        btnSalirIzquierda.setBorderPainted(false);
        btnSalirIzquierda.setFocusable(false);
        btnSalirIzquierda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalirIzquierda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalirIzquierda.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSalirIzquierdaActionPerformed(evt);
            }
        });

        btnSesion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/InicioSesion.png"))); // NOI18N
        btnSesion.setText("Iniciar / Cerrar sesión");
        btnSesion.setToolTipText("Cambio de sesión");
        btnSesion.setBorder(null);
        btnSesion.setBorderPainted(false);
        btnSesion.setFocusable(false);
        btnSesion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSesion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSesion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSesionActionPerformed(evt);
            }
        });

        btnIniciarTurno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnIniciarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/InicioSesion.png"))); // NOI18N
        btnIniciarTurno.setText("Iniciar turno");
        btnIniciarTurno.setToolTipText("Cambio de sesión");
        btnIniciarTurno.setBorder(null);
        btnIniciarTurno.setBorderPainted(false);
        btnIniciarTurno.setEnabled(false);
        btnIniciarTurno.setFocusable(false);
        btnIniciarTurno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIniciarTurno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIniciarTurno.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnIniciarTurnoActionPerformed(evt);
            }
        });

        btnCerrarTurno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCerrarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/InicioSesion.png"))); // NOI18N
        btnCerrarTurno.setText("Cerrar turno");
        btnCerrarTurno.setToolTipText("Cambio de sesión");
        btnCerrarTurno.setBorder(null);
        btnCerrarTurno.setBorderPainted(false);
        btnCerrarTurno.setEnabled(false);
        btnCerrarTurno.setFocusable(false);
        btnCerrarTurno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrarTurno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCerrarTurno.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCerrarTurnoActionPerformed(evt);
            }
        });

        jMenuBar1.setBorder(null);

        mMenu.setText("Menú");

        jMenuItem2.setText("Cerrar todas las ventanas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mMenu.add(jMenuItem2);
        mMenu.add(jSeparator2);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mMenu.add(jMenuItem1);

        jMenuBar1.add(mMenu);

        mClientes.setText("Clientes");

        mCtesAgregar.setText("Agregar huesped");
        mCtesAgregar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mCtesAgregarActionPerformed(evt);
            }
        });
        mClientes.add(mCtesAgregar);

        mCtesCnsHues.setText("Listar huéspedes");
        mCtesCnsHues.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mCtesCnsHuesActionPerformed(evt);
            }
        });
        mClientes.add(mCtesCnsHues);

        jMenuBar1.add(mClientes);

        mHabitaciones.setText("Habitaciones");

        mHabCns.setText("Consulta de Habitaciones");
        mHabCns.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mHabCnsActionPerformed(evt);
            }
        });
        mHabitaciones.add(mHabCns);

        jMenuBar1.add(mHabitaciones);

        mReservaciones.setText("Reservaciones");

        mResAlta.setText("Alta de reservaciones");
        mReservaciones.add(mResAlta);

        jMenuBar1.add(mReservaciones);

        mRecepcionistas.setText("Recepcionistas");

        mRecepAlta.setText("Alta de recepcionistas");
        mRecepAlta.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mRecepAltaActionPerformed(evt);
            }
        });
        mRecepcionistas.add(mRecepAlta);

        mRecepCns.setText("Consulta de recepcionistas");
        mRecepCns.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mRecepCnsActionPerformed(evt);
            }
        });
        mRecepcionistas.add(mRecepCns);
        mRecepcionistas.add(jSeparator1);

        mRecepSesion.setText("Iniciar sesión");
        mRecepcionistas.add(mRecepSesion);

        jMenuBar1.add(mRecepcionistas);

        mGastos.setText("Gastos");

        jMenuItem4.setText("Alta de gastos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mGastos.add(jMenuItem4);

        jMenuItem5.setText("Consulta de gastos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem5ActionPerformed(evt);
            }
        });
        mGastos.add(jMenuItem5);

        jMenuBar1.add(mGastos);

        mEmpresas.setText("Empresas");

        mEmpAlta.setText("Agregar empresas para descuentos");
        mEmpAlta.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mEmpAltaActionPerformed(evt);
            }
        });
        mEmpresas.add(mEmpAlta);

        mEmpCns.setText("Listar empresas con descuentos");
        mEmpCns.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mEmpCnsActionPerformed(evt);
            }
        });
        mEmpresas.add(mEmpCns);

        jMenuBar1.add(mEmpresas);

        mConfiguracion.setText("Configuración");

        mConfOpc.setText("Opciones del Sistema");
        mConfOpc.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mConfOpcActionPerformed(evt);
            }
        });
        mConfiguracion.add(mConfOpc);

        mConfOpcAvan.setText("Opciones avanzadas del Sistema");
        mConfOpcAvan.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mConfOpcAvanActionPerformed(evt);
            }
        });
        mConfiguracion.add(mConfOpcAvan);

        jMenuBar1.add(mConfiguracion);

        mAyuda.setText("Ayuda");

        jMenuItem3.setText("Ayuda del Sistema");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mAyuda.add(jMenuItem3);

        jMenuBar1.add(mAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalirIzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(btnIniciarTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrarTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConferenciasIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHospedajesIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHabitacionesIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClientesIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelIzquierdo, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelIzquierdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClientesIzq, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(btnHabitacionesIzq)
                        .addGap(1, 1, 1)
                        .addComponent(btnHospedajesIzq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConferenciasIzq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSesion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIniciarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrarTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalirIzquierda)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClientesIzq, btnConferenciasIzq, btnHabitacionesIzq, btnHospedajesIzq, btnSalirIzquierda, btnSesion});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesIzqActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnClientesIzqActionPerformed
    {//GEN-HEADEREND:event_btnClientesIzqActionPerformed
        // TODO add your handling code here:
        MostrarConsultaClientes();
    }//GEN-LAST:event_btnClientesIzqActionPerformed

    private void btnHabitacionesIzqActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHabitacionesIzqActionPerformed
    {//GEN-HEADEREND:event_btnHabitacionesIzqActionPerformed
        // TODO add your handling code here:
        MostrarHabitaciones();
    }//GEN-LAST:event_btnHabitacionesIzqActionPerformed

    private void mCtesCnsHuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCtesCnsHuesActionPerformed
        // TODO add your handling code here:
        MostrarConsultaClientes();
    }//GEN-LAST:event_mCtesCnsHuesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Salir(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mCtesAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCtesAgregarActionPerformed
        // TODO add your handling code here:
        AgregarCliente();
    }//GEN-LAST:event_mCtesAgregarActionPerformed

    private void mHabCnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mHabCnsActionPerformed
        // TODO add your handling code here:
        MostrarCnsHabitaciones();
    }//GEN-LAST:event_mHabCnsActionPerformed

    public void MostrarCnsHabitaciones()
    {
        CnsHabitacionesConf frm = new CnsHabitacionesConf();
                
        jDesktopPane1.add(frm);
        frm.show();
    }
    
    private void btnHospedajesIzqActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHospedajesIzqActionPerformed
    {//GEN-HEADEREND:event_btnHospedajesIzqActionPerformed
        // TODO add your handling code here:
        MostrarHospedajes();
    }//GEN-LAST:event_btnHospedajesIzqActionPerformed

    private void btnSalirIzquierdaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSalirIzquierdaActionPerformed
    {//GEN-HEADEREND:event_btnSalirIzquierdaActionPerformed
        // TODO add your handling code here:
        Salir(false);
    }//GEN-LAST:event_btnSalirIzquierdaActionPerformed

    private void mConfOpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mConfOpcActionPerformed
        // TODO add your handling code here:
        FrmConfiguracionSistema frm = new FrmConfiguracionSistema(this, true);
        
        frm.show(true);
    }//GEN-LAST:event_mConfOpcActionPerformed

    private void mEmpCnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEmpCnsActionPerformed
        // TODO add your handling code here:
        CnsEmpresasDescuentos frm = new CnsEmpresasDescuentos();
        
        jDesktopPane1.add(frm);
        frm.show();
    }//GEN-LAST:event_mEmpCnsActionPerformed

    private void mEmpAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEmpAltaActionPerformed
        // TODO add your handling code here:
        FrmDetEmpresasDesctos frm = new FrmDetEmpresasDesctos(this, true);
        
        frm.show(true);
    }//GEN-LAST:event_mEmpAltaActionPerformed

    private void btnConferenciasIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConferenciasIzqActionPerformed
        // TODO add your handling code here:
        MuestraSalaDeConferencias();
    }//GEN-LAST:event_btnConferenciasIzqActionPerformed

    private void mRecepAltaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mRecepAltaActionPerformed
    {//GEN-HEADEREND:event_mRecepAltaActionPerformed
        // TODO add your handling code here:
        AgregarRecepcionista();
    }//GEN-LAST:event_mRecepAltaActionPerformed

    public void AgregarRecepcionista()
    {
        FrmDetalleRecepcionista frm = null;
        
        frm = new FrmDetalleRecepcionista(this,true);
        frm.show();
    }
    
    public void HabilitaSistemaHotel()
    {
        MostrarHabitaciones();                   
        IniciaSesionUsuario();        
    }
    
    public void ValidarInicioSesion()
    {        
        ufrmDlgLogin frm = null;
        
        HabilitaDeshabilitaTodos(false);
        frm = new ufrmDlgLogin(this, true);        
        frm.show();
        
        if ( frm.isAceptar() && frm.isLogueado() )
        {
            if ( MuestraVentanaCapturaEfectivo() )
            {
                uUsuario = Usuario.ObtenInstancia();
            
                if ( uUsuario.getTipo() == 0 )
                {
                    HabilitaSistemaHotel();
                    btnIniciarTurno.setEnabled(false);
                    btnIniciarTurno.setEnabled(false);
                    btnIniciarTurno.setVisible(false);
                    btnCerrarTurno.setEnabled(false);
                    btnCerrarTurno.setVisible(false);
                }
                else
                {                                
                    BotonTurnoAbierto(tTurnos.TurnoAbierto());
                    IniciaTurno();
                }
            
                this.setTitle("*** HOTEL VIYARREAL ===== USUARIO: " + uUsuario.getNomUsuario() + " ===== ***");            
          }
          else
          {             
              HabilitaDeshabilitaTodos(false);
              CerrarTodasLasVentanas();
              uUsuario.CerrarSesion();
              uUsuario = null;
          }
       }
    }
    
    public void BotonTurnoAbierto(boolean bTurnoAbierto)
    {
        if ( bTurnoAbierto )
        {
            btnIniciarTurno.setEnabled(false);
            btnIniciarTurno.setVisible(false);
            btnCerrarTurno.setEnabled(true);
            btnCerrarTurno.setVisible(true);
        }
        else
        {
            btnIniciarTurno.setEnabled(true);
            btnIniciarTurno.setVisible(true);
            btnCerrarTurno.setEnabled(false);
            btnCerrarTurno.setVisible(false);
        }
    }
    
    public boolean MuestraVentanaCapturaEfectivo()
    {
        boolean bOk = true;
        
        if ( cCorteCaja.ExisteCorteCajaActivo() )
        {
            bOk = LanzaVentanaCapturaEfectivo();
        }
        
        return bOk;
    }
    
    public boolean LanzaVentanaCapturaEfectivo()
    {
        boolean bOk = false;
        
        FrmDlgEfectivoCaja frm = new FrmDlgEfectivoCaja(this, true);
        
        frm.show();
        bOk = frm.isIngresoEfectivo();
        
        return bOk;
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        // TODO add your handling code here:
        ValidarInicioSesion();
    }//GEN-LAST:event_formWindowOpened

    private void mRecepCnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRecepCnsActionPerformed
        // TODO add your handling code here:
        MuestraConsultaUsuarios();
    }//GEN-LAST:event_mRecepCnsActionPerformed

    private void btnSesionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSesionActionPerformed
    {//GEN-HEADEREND:event_btnSesionActionPerformed
        // TODO add your handling code here:
        GeneraSesion();
    }//GEN-LAST:event_btnSesionActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem2ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        CerrarTodasLasVentanas();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem3ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
       MuestraAyuda();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnIniciarTurnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnIniciarTurnoActionPerformed
    {//GEN-HEADEREND:event_btnIniciarTurnoActionPerformed
        // TODO add your handling code here:
        IniciaTurno();                
    }//GEN-LAST:event_btnIniciarTurnoActionPerformed

    public void IniciaTurno()
    {                
        if ( !tTurnos.TurnoAbierto() )
        {            
            tTurnos.Insertar();
        }
        
        HabilitaSistemaHotel();
        BotonTurnoAbierto(true);
    }
    
    private void mConfOpcAvanActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mConfOpcAvanActionPerformed
    {//GEN-HEADEREND:event_mConfOpcAvanActionPerformed
        // TODO add your handling code here:
        MuestraOpcionesAvanzadas();
    }//GEN-LAST:event_mConfOpcAvanActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem4ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        LanzaCapturaGastos();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnCerrarTurnoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCerrarTurnoActionPerformed
    {//GEN-HEADEREND:event_btnCerrarTurnoActionPerformed
        // TODO add your handling code here:luis
        CerrarTurno();
    }//GEN-LAST:event_btnCerrarTurnoActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem5ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        MuestraConsultaGastos();
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    
    public void MuestraConsultaGastos()
    {
        CnsGastos frm = new CnsGastos();
        
        jDesktopPane1.add(frm);
        frm.show();
    }
    
    public void CerrarTurno()
    {
        Utilerias uAccesibilidad = new Utilerias();
        
        if ( uAccesibilidad.MuestraMensajeConfirmacion("¿Desea cerrar la sesión?.\nTodas las ventans se cerrarán.") == 0 )
        {
            if ( tTurnos.TurnoAbierto() )
            {            
                tTurnos.CerrarTurno();
            }

            CerrarTodasLasVentanas();        
            HabilitaDeshabilitaTodos(false);
            BotonTurnoAbierto(false);   
        }        
    }
    
    public void LanzaCapturaGastos()
    {
        FrmDlgDetGastos frm = new FrmDlgDetGastos(null, true);
        
        frm.show();
    }
    
    public void MuestraOpcionesAvanzadas()
    {
        FrmDlgOpcAvanzadas frm = new FrmDlgOpcAvanzadas(this, true);
        
        CerrarTodasLasVentanas();
        frm.show();
    }
    
    public void MuestraAyuda()
    {
        
    }
    
    public void GeneraSesion()
    {                
        if ( uUsuario == null )
        {
            uUsuario = Usuario.ObtenInstancia();
        }
        
        if ( cCorteCaja == null )
        {
            cCorteCaja = CorteCaja.ObtenInstancia();
        }
                    
        if ( tTurnos == null )
        {
            tTurnos = Turnos.ObtenInstancia();
        }
                        
        HabilitaDeshabilitaTodos(false);
        CerrarTodasLasVentanas();
        uUsuario.CerrarSesion();
        uUsuario = null;
        ValidarInicioSesion();
    }
       
    public void CerrarTodasLasVentanas()
    {                
        jDesktopPane1.removeAll();
        jDesktopPane1.repaint();        
    }
    
    public void MuestraConsultaUsuarios()
    {
        CnsUsuarios frm = new CnsUsuarios();
        
        jDesktopPane1.add(frm);
        frm.show();
    }
    
    public void MuestraSalaDeConferencias()
    {
        FrmListaSalaConferencias frm = new FrmListaSalaConferencias();
        //FrmListaSalaConferencias frm2 = new FrmListaSalaConferencias();
        
        jDesktopPane1.add(frm);
        frm.show();
        frm.LlenaSalaDeConferencias();
        frm.RevisaEstado();
        //Thread t1 = new Thread(frm);
        //Thread t2 = new Thread(frm2);
        //t1.start();
        //t2.start();
    }
    
    public void MostrarHospedajes()
    {
        CnsHospedajes frmGlobal = new CnsHospedajes();
        
        jDesktopPane1.add(frmGlobal);
        frmGlobal.show();
    }
        
    public void AgregarCliente()
    {
        FrmDetCliente frm = new FrmDetCliente(null, true);
        
        frm.show();
    }        
    
    public int Salir(boolean bRequieroEvento)
    {
        int iRespuesta = 1;
        Utilerias uAccesibilidad = new Utilerias();
        
        iRespuesta = uAccesibilidad.MuestraMensajeConfirmacion("¿Deseas realmente salir del Sistema?");
        
        if ( !bRequieroEvento )
        {
            if ( iRespuesta == 0 )
            {
                System.exit(0);
            }
        }
        
        return iRespuesta;
    }
    
    public void MostrarConsultaClientes()
    {
        CnsClientes frmClientes = new CnsClientes();
        
        jDesktopPane1.add(frmClientes);
        frmClientes.show();        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {                
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelIzquierdo;
    private javax.swing.JButton btnCerrarTurno;
    private javax.swing.JButton btnClientesIzq;
    private javax.swing.JButton btnConferenciasIzq;
    private javax.swing.JButton btnHabitacionesIzq;
    private javax.swing.JButton btnHospedajesIzq;
    private javax.swing.JButton btnIniciarTurno;
    private javax.swing.JButton btnSalirIzquierda;
    private javax.swing.JButton btnSesion;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu mAyuda;
    private javax.swing.JMenu mClientes;
    private javax.swing.JMenuItem mConfOpc;
    private javax.swing.JMenuItem mConfOpcAvan;
    private javax.swing.JMenu mConfiguracion;
    private javax.swing.JMenuItem mCtesAgregar;
    private javax.swing.JMenuItem mCtesCnsHues;
    private javax.swing.JMenuItem mEmpAlta;
    private javax.swing.JMenuItem mEmpCns;
    private javax.swing.JMenu mEmpresas;
    private javax.swing.JMenu mGastos;
    private javax.swing.JMenuItem mHabCns;
    private javax.swing.JMenu mHabitaciones;
    private javax.swing.JMenu mMenu;
    private javax.swing.JMenuItem mRecepAlta;
    private javax.swing.JMenuItem mRecepCns;
    private javax.swing.JMenuItem mRecepSesion;
    private javax.swing.JMenu mRecepcionistas;
    private javax.swing.JMenuItem mResAlta;
    private javax.swing.JMenu mReservaciones;
    // End of variables declaration//GEN-END:variables
}
