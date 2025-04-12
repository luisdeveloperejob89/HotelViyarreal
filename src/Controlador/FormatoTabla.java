/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author LCC. Luis Alberto Flores Castillo
 */
public class FormatoTabla extends DefaultTableCellRenderer 
{
  /*  private int ColumnaPatron;
    
    public FormatoTabla(int iColumnaPatron)
    {
        this.ColumnaPatron = iColumnaPatron;
    }
    */
    public Component getTableCellRedeComponent( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {
       // setBackground(Color.white);
        //table.setForeground(Color.black);        
        //setBackground(Color.red);
        setForeground(Color.red);
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);       
        return this;
    }
    
    
    /*
     private int columna_patron ;

    public FormatoTabla(int Colpatron)
    {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {        
        setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        //Si la celda corresponde a una fila con estado FALSE, se cambia el color de fondo a rojo
        if( table.getValueAt(row,columna_patron).equals(false) )
        {
            setBackground(Color.red);
        }

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
 }
    */
}
