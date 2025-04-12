/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WIN7UTL64
 */
public class TablaModelo extends DefaultTableModel
{
    public TablaModelo(Object[][] data, Object[] columnNames)
    {
        super(data, columnNames);
    }
    
     @Override
     public boolean isCellEditable(int row, int column) 
     {
        return false;
     }          
}
