/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;

import Controlador.FormatoTabla;
import Modelo.Configuracion;
import Modelo.Paquete;
import Modelo.Utilerias;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
/**
 *
 * @author WIN7UTL64
 */
public class FrmConfiguracionSistema extends javax.swing.JDialog 
{
    private Configuracion ParametrosSis;
    private Utilerias uAccesibilidad;
    private DefaultTableModel dtmTabla;
    private Paquete pPaquete;
    /**
     * Creates new form FrmConfiguracionSistema
     */
    public FrmConfiguracionSistema(java.awt.Frame parent, boolean modal) 
    {
        super(parent, modal);        
        initComponents();    
        AsignaEventosBotonesVentana();
        ParametrosSis = new Configuracion();
        uAccesibilidad = new Utilerias();
        pPaquete = new Paquete();
        ConfiguraForma();
        RefrescarConsultaPaquetes();
        /*Pestanias.remove(PanelEmail);
        PanelDatEmp.setVisible(true);
        PanelEmail.setVisible(false);*/     
        Pestanias.addKeyListener(new PresionarTecla());
        txtNombreEmpresa.requestFocus();
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

        Action btnAccionAplicar = new AbstractAction("LIMPIAR") 
        {

            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                btnAplicarActionPerformed(evt);
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
               
         
        btnAceptar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "Pagar"); 
        btnAceptar.getActionMap().put("Pagar", btnAccionPagar);
        btnAplicar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "Aplicar"); 
        btnAplicar.getActionMap().put("Aplicar", btnAccionAplicar);
        btnCancelar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "Cancelar"); 
        btnCancelar.getActionMap().put("Cancelar", btnAccionCancelar);         
    }
    
    public class PresionarTecla extends KeyAdapter 
    {

      public void keyPressed(KeyEvent ke) 
      {
          if (ke.getKeyCode() == KeyEvent.VK_F3) 
          {
              btnAceptarActionPerformed(null);
          }
      }
}
    
    public void RefrescarConsultaPaquetes()
    {
        try
        {                                  
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));  
            tblClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tblClientes.doLayout();                        
            dtmTabla = new DefaultTableModel(null, GetColumnas());                        
            tblClientes.setDefaultRenderer (Object.class, new FormatoTabla() );
            InicializarFilas();            
            tblClientes.setModel(dtmTabla);            
            tblClientes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            AjustaColumnas();         
            this.setCursor(Cursor.getDefaultCursor());
        }
        catch ( Exception e )
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Error al llenar el Catálogo");
        }
    }
    
    public void AjustaColumnas()
    {
        TableColumnModel tblModeloColumnas = tblClientes.getColumnModel();
        
        tblModeloColumnas.getColumn(0).setPreferredWidth(100);
        tblModeloColumnas.getColumn(1).setPreferredWidth(250);
        tblModeloColumnas.getColumn(2).setPreferredWidth(200);        
        tblModeloColumnas.getColumn(3).setPreferredWidth(200);
        tblModeloColumnas.getColumn(4).setPreferredWidth(200);        
    }       
    
    public void InicializarFilas()
    {                        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        pPaquete.DameRegistroCnsPaquetes(dtmTabla, GetColumnas());        
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public String[] GetColumnas()
    {                    
        String strColumnas[] = new String[]{"Id"/*0*/, "Sala de Conferencias"/*1*/, "Precio solamente Sala"/*2*/, "Precio con Coffee Break" /*3*/,"Precio con Almuerzo (Por Persona)"/*4*/};
        
        return strColumnas;
    }
    
     public void GuardarDatos()
    {
        if ( DatosCompletos() )
        {
            ParametrosSis.setEmpresa(txtNombreEmpresa.getText());
            ParametrosSis.setRFC(txtRFC.getText());
            ParametrosSis.setTelefono(txtTelefono.getText());
            ParametrosSis.setCalle(txtCalle.getText());
            ParametrosSis.setNumExt(txtNumExt.getText());
            ParametrosSis.setNumInt(txtNumInt.getText());
            ParametrosSis.setColonia(txtColonia.getText());
            ParametrosSis.setPoblacion(txtPoblacion.getText());
            ParametrosSis.setMunicipio(txtMunicipio.getText());
            ParametrosSis.setPais(txtPais.getText());
            ParametrosSis.setCP(txtCP.getText());            
            ParametrosSis.setEmail(txtEmaiEmisor.getText());
            ParametrosSis.setEmailCont(txtEmailDestinatario.getText());
            ParametrosSis.setContrasenia(txtPasswordEmisor.getText());
            ParametrosSis.setEnvioAut(cboxEnvioAut.isSelected() ? 1 : 0);
            
            if ( ParametrosSis.Insertar() )
            {
                uAccesibilidad.MuestraMensaje("Datos guardados correctamente.");
            }
            else
            {
                uAccesibilidad.MuestraMensaje("Ocurrió un error al actualizar los datos de la Configuración.");
            }
        }
        else
        {
            uAccesibilidad.MuestraMensaje("Es obligatorio capturar la Razón Social de la Empresa.");
        }
    }
    
    public boolean DatosCompletos()
    {
        boolean bOk = true;
        
        if ( txtNombreEmpresa.getText().isEmpty() )
        {
            bOk = false;
        }        
        
        return bOk;
    }
    
    public void ConfiguraForma()
    {
        ParametrosSis.DameDatosConfiguracion();
        txtNombreEmpresa.setText(ParametrosSis.getEmpresa());
        txtEmaiEmisor.setText(ParametrosSis.getEmail());
        txtPasswordEmisor.setText(ParametrosSis.getContrasenia());
        txtEmailDestinatario.setText(ParametrosSis.getEmailCont());
        txtTelefono.setText(ParametrosSis.getTelefono());
        txtCalle.setText(ParametrosSis.getCalle());
        txtNumExt.setText(ParametrosSis.getNumExt());
        txtNumInt.setText(ParametrosSis.getNumInt());
        txtColonia.setText(ParametrosSis.getColonia());
        txtPoblacion.setText(ParametrosSis.getPoblacion());
        txtMunicipio.setText(ParametrosSis.getMunicipio());
        txtPais.setText(ParametrosSis.getPais());
        txtCP.setText(ParametrosSis.getCP());
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

        jPanel3 = new javax.swing.JPanel();
        Pestanias = new javax.swing.JTabbedPane();
        PanelDatEmp = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRFC = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        PanelDatosFiscales = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNumExt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNumInt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtPoblacion = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtPais = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtCP = new javax.swing.JTextField();
        PanelEmail = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtEmaiEmisor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPasswordEmisor = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtEmailDestinatario = new javax.swing.JTextField();
        cboxEnvioAut = new javax.swing.JCheckBox();
        PanelSalaConferencias = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        tbHerramientas = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnAplicar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setDisplayedMnemonic('s');
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setLabelFor(txtNombreEmpresa);
        jLabel1.setText("* Razón social:");

        txtNombreEmpresa.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtNombreEmpresaKeyPressed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoHotel_200.png"))); // NOI18N

        jLabel2.setDisplayedMnemonic('r');
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setLabelFor(txtRFC);
        jLabel2.setText("RFC:");

        txtRFC.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtRFCKeyPressed(evt);
            }
        });

        jLabel15.setDisplayedMnemonic('t');
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setLabelFor(txtTelefono);
        jLabel15.setText("Teléfono:");

        javax.swing.GroupLayout PanelDatEmpLayout = new javax.swing.GroupLayout(PanelDatEmp);
        PanelDatEmp.setLayout(PanelDatEmpLayout);
        PanelDatEmpLayout.setHorizontalGroup(
            PanelDatEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDatEmpLayout.createSequentialGroup()
                        .addGroup(PanelDatEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel15))
                        .addGap(33, 33, 33)
                        .addGroup(PanelDatEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        PanelDatEmpLayout.setVerticalGroup(
            PanelDatEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDatEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDatEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDatEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Pestanias.addTab("Datos de la empresa", PanelDatEmp);

        jLabel16.setDisplayedMnemonic('c');
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setLabelFor(txtCalle);
        jLabel16.setText("Calle:");

        txtCalle.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtCalleKeyPressed(evt);
            }
        });

        jLabel17.setDisplayedMnemonic('x');
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setLabelFor(txtNumExt);
        jLabel17.setText("Num Ext:");

        txtNumExt.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtNumExtKeyPressed(evt);
            }
        });

        jLabel18.setDisplayedMnemonic('i');
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setLabelFor(txtNumInt);
        jLabel18.setText("Num Int:");

        txtNumInt.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtNumIntKeyPressed(evt);
            }
        });

        jLabel19.setDisplayedMnemonic('o');
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setLabelFor(txtColonia);
        jLabel19.setText("Colonia:");

        txtColonia.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtColoniaKeyPressed(evt);
            }
        });

        jLabel20.setDisplayedMnemonic('p');
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setLabelFor(txtPoblacion);
        jLabel20.setText("Población:");

        txtPoblacion.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtPoblacionKeyPressed(evt);
            }
        });

        jLabel21.setDisplayedMnemonic('m');
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setLabelFor(txtMunicipio);
        jLabel21.setText("Municipio:");

        txtMunicipio.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtMunicipioKeyPressed(evt);
            }
        });

        jLabel22.setDisplayedMnemonic('a');
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setLabelFor(txtPais);
        jLabel22.setText("País:");

        txtPais.setText("México");
        txtPais.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtPaisKeyPressed(evt);
            }
        });

        jLabel23.setDisplayedMnemonic('d');
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setLabelFor(txtCP);
        jLabel23.setText("Código Postal:");

        txtCP.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtCPKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PanelDatosFiscalesLayout = new javax.swing.GroupLayout(PanelDatosFiscales);
        PanelDatosFiscales.setLayout(PanelDatosFiscalesLayout);
        PanelDatosFiscalesLayout.setHorizontalGroup(
            PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosFiscalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel23))
                .addGap(27, 27, 27)
                .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMunicipio)
                    .addComponent(txtPais)
                    .addComponent(txtColonia)
                    .addComponent(txtCalle)
                    .addGroup(PanelDatosFiscalesLayout.createSequentialGroup()
                        .addComponent(txtNumExt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumInt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelDatosFiscalesLayout.createSequentialGroup()
                        .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 99, Short.MAX_VALUE)))
                .addGap(125, 125, 125))
        );
        PanelDatosFiscalesLayout.setVerticalGroup(
            PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosFiscalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtNumExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDatosFiscalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        Pestanias.addTab("Datos Fiscales", PanelDatosFiscales);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Emisor"));

        jLabel3.setDisplayedMnemonic('c');
        jLabel3.setLabelFor(txtEmaiEmisor);
        jLabel3.setText("Correo electrónico");

        txtEmaiEmisor.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtEmaiEmisorKeyPressed(evt);
            }
        });

        jLabel4.setDisplayedMnemonic('o');
        jLabel4.setLabelFor(txtPasswordEmisor);
        jLabel4.setText("Contraseña:");

        txtPasswordEmisor.setText("password");
        txtPasswordEmisor.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtPasswordEmisorKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmaiEmisor)
                    .addComponent(txtPasswordEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEmaiEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPasswordEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Destinatario"));

        jLabel5.setDisplayedMnemonic('t');
        jLabel5.setLabelFor(txtEmailDestinatario);
        jLabel5.setText("Correo electrónico");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmailDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(327, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmailDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        cboxEnvioAut.setText("Envío automático de datos de facturación a destinatario");
        cboxEnvioAut.setToolTipText("Al activar este campo cada que una persona solicite una factura, automáticamente se enviará un correo a la personas destinataria para facturación.");

        javax.swing.GroupLayout PanelEmailLayout = new javax.swing.GroupLayout(PanelEmail);
        PanelEmail.setLayout(PanelEmailLayout);
        PanelEmailLayout.setHorizontalGroup(
            PanelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEmailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelEmailLayout.createSequentialGroup()
                        .addComponent(cboxEnvioAut)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelEmailLayout.setVerticalGroup(
            PanelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEmailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxEnvioAut)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        Pestanias.addTab("Correo electrónico", PanelEmail);

        btnAgregar.setText("Agregar paquete");
        btnAgregar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar paquete");
        btnEditar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEditarActionPerformed(evt);
            }
        });

        tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        tblClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblClientes.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                tblClientesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout PanelSalaConferenciasLayout = new javax.swing.GroupLayout(PanelSalaConferencias);
        PanelSalaConferencias.setLayout(PanelSalaConferenciasLayout);
        PanelSalaConferenciasLayout.setHorizontalGroup(
            PanelSalaConferenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSalaConferenciasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addContainerGap(443, Short.MAX_VALUE))
            .addGroup(PanelSalaConferenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelSalaConferenciasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        PanelSalaConferenciasLayout.setVerticalGroup(
            PanelSalaConferenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSalaConferenciasLayout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addGroup(PanelSalaConferenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEditar))
                .addGap(128, 128, 128))
            .addGroup(PanelSalaConferenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelSalaConferenciasLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(158, Short.MAX_VALUE)))
        );

        Pestanias.addTab("Sala de conferencias", PanelSalaConferencias);

        tbHerramientas.setFloatable(false);
        tbHerramientas.setOrientation(javax.swing.SwingConstants.VERTICAL);
        tbHerramientas.setRollover(true);
        tbHerramientas.setAutoscrolls(true);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Datos de la empresa");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });
        tbHerramientas.add(jButton1);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("Datos Fiscales");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton4ActionPerformed(evt);
            }
        });
        tbHerramientas.add(jButton4);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Correo electrónico");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });
        tbHerramientas.add(jButton3);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Sala de conferencias");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });
        tbHerramientas.add(jButton2);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAceptar.setText("Guardar (F3)");
        btnAceptar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarActionPerformed(evt);
            }
        });

        btnAplicar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAplicar.setText("Aplicar (F1)");
        btnAplicar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAplicarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("Cancelar (F4)");
        btnCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAplicar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnAplicar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("(*) Campos obligatorios");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(tbHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Pestanias))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tbHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addComponent(Pestanias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Pestanias.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:        
       // Pestanias.add(PanelDatEmp);
       // Pestanias.remove(PanelEmail);
        /*PanelDatEmp.setVisible(true);
        PanelEmail.setVisible(false);*/
        Pestanias.setSelectedIndex(2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       /* Pestanias.add(PanelEmail);
        Pestanias.remove(PanelDatEmp);
        PanelDatEmp.setVisible(false);
        PanelEmail.setVisible(true);*/
        Pestanias.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        GuardarDatos();
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Pestanias.setSelectedIndex(3);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        AgregarPaquete();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tblClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClientesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblClientesKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
       Pestanias.setSelectedIndex(1); 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarActionPerformed
        // TODO add your handling code here:
        GuardarDatos();
    }//GEN-LAST:event_btnAplicarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        EditarPaquete();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelarActionPerformed
    {//GEN-HEADEREND:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNombreEmpresaKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNombreEmpresaKeyPressed
    {//GEN-HEADEREND:event_txtNombreEmpresaKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtRFC.requestFocus();
        }
    }//GEN-LAST:event_txtNombreEmpresaKeyPressed

    private void txtRFCKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtRFCKeyPressed
    {//GEN-HEADEREND:event_txtRFCKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtRFCKeyPressed

    private void txtCPKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtCPKeyTyped
    {//GEN-HEADEREND:event_txtCPKeyTyped
        // TODO add your handling code here:
        char cCaracter = evt.getKeyChar();
        
        if ( txtCP.getText().length() == 5 || !Character.isDigit(cCaracter) )
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCPKeyTyped

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
            txtPais.requestFocus();
        }
    }//GEN-LAST:event_txtMunicipioKeyPressed

    private void txtPaisKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtPaisKeyPressed
    {//GEN-HEADEREND:event_txtPaisKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtCP.requestFocus();
        }
    }//GEN-LAST:event_txtPaisKeyPressed

    private void txtEmaiEmisorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtEmaiEmisorKeyPressed
    {//GEN-HEADEREND:event_txtEmaiEmisorKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtPasswordEmisor.requestFocus();
        }
    }//GEN-LAST:event_txtEmaiEmisorKeyPressed

    private void txtPasswordEmisorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtPasswordEmisorKeyPressed
    {//GEN-HEADEREND:event_txtPasswordEmisorKeyPressed
        // TODO add your handling code here:
        if ( uAccesibilidad.PresionoENTER(evt) )
        {
            txtEmailDestinatario.requestFocus();
       }
    }//GEN-LAST:event_txtPasswordEmisorKeyPressed
       
    public void EditarPaquete()
    {        
        int iIdRegistro = 0;    
        FrmDetPaquete frm = null;        
        
        if ( tblClientes != null )
        {        
            iIdRegistro = Integer.parseInt(tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString());
            frm = new FrmDetPaquete(null, true, iIdRegistro);
            frm.show();
            RefrescarConsultaPaquetes();
        }        
    }
    
    public void AgregarPaquete()
    {
        FrmDetPaquete frm = new FrmDetPaquete(null, true);
        
        frm.show();
        RefrescarConsultaPaquetes();
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmConfiguracionSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmConfiguracionSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmConfiguracionSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmConfiguracionSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmConfiguracionSistema dialog = new FrmConfiguracionSistema(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel PanelDatEmp;
    private javax.swing.JPanel PanelDatosFiscales;
    private javax.swing.JPanel PanelEmail;
    private javax.swing.JPanel PanelSalaConferencias;
    private javax.swing.JTabbedPane Pestanias;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JCheckBox cboxEnvioAut;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar tbHerramientas;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCP;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtEmaiEmisor;
    private javax.swing.JTextField txtEmailDestinatario;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtNumExt;
    private javax.swing.JTextField txtNumInt;
    private javax.swing.JTextField txtPais;
    private javax.swing.JPasswordField txtPasswordEmisor;
    private javax.swing.JTextField txtPoblacion;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
