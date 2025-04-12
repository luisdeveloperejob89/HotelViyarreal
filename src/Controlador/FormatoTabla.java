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
      
    public Component getTableCellRedeComponent( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {
       // setBackground(Color.white);
        //table.setForeground(Color.black);        
        //setBackground(Color.red);
        setForeground(Color.red);
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);       
        return this;
    }           
}
