/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.modelos;
 
import java.awt.Color;
import java.awt.Component;
 
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
 
public class TabelaIA implements TableCellRenderer {
 
    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);
        
        // Apply zebra style on table rows
        if (row % 2 == 0) {
            c.setBackground(Color.WHITE);
        } else {
            c.setBackground(Color.decode("#F8F8F8"));
        }
 
        //Coluna da influencia
        if (column == 1) 
        {
            Object objInfluencia = table.getModel().getValueAt(row,1);
            double influencia = 0.0;
            try {influencia = Double.parseDouble(objInfluencia.toString());}
            catch (Exception ex){
                c.setForeground(Color.BLACK);
                return c;
            }
            Color color;
            if (influencia > 0.4) {
                color = Color.RED;
            } else if(influencia > 0.2){
                color = Color.ORANGE;
            } else{
                color = Color.GREEN;
            }
            c.setForeground(color);
            return c;
        }
        
        if (column == 4) 
        {
            Object objHoje = table.getModel().getValueAt(row,3);
            Object objAmanha = table.getModel().getValueAt(row,4);
            
            double valorHoje = 0.0;
            double valorAmanha = 0.0;
            
            try {
                valorHoje = Double.parseDouble(objHoje.toString());
                valorAmanha = Double.parseDouble(objAmanha.toString());
            }
            catch (Exception ex){
                c.setForeground(Color.BLACK);
                return c;
            }
            Color color;
            if (valorAmanha > valorHoje) {
                color = Color.RED;
            } else if(valorHoje > valorAmanha){
                color = Color.GREEN;
            } else{
                color = Color.BLACK;
            }
            c.setForeground(color);
            return c;
        }
        
        c.setForeground(Color.BLACK);
 
        return c;
    }
 
}