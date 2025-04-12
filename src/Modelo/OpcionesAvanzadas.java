/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Conexion.DMBaseDatos;

/**
 *
 * @author LCC Luis Alberto Flores Castillo <luis.ejob@gmail.com>
 */
public class OpcionesAvanzadas
{
    private DMBaseDatos DMObjeto = null;
    
    public OpcionesAvanzadas()
    {
        DMObjeto = new DMBaseDatos();
    }
    
    public boolean RestauraBDOriginal()
    {
        boolean bOk = false;
        
        bOk = DMObjeto.RestauraBDAFabrica();
        
        return bOk;
                
    }
}
