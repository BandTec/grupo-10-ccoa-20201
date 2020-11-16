/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.modelos;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Carlos Alberto
 */
public class Grafico {
    
    public CategoryDataset createDataset(ArrayList<Componente> listaGrafico){
        
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        
        for(Componente componente : listaGrafico){
            dataSet.addValue(componente.getValor(), componente.getNomeComponente()
                    , componente.getDataHora());
        }
        
        return dataSet;
        
    }
    
    public JFreeChart createChart(CategoryDataset dataSet){
        
        JFreeChart graficoLinha = ChartFactory.createLineChart("CPU", 
                "Horário", "Valor", dataSet, PlotOrientation.VERTICAL,
                false, false, false);
        
        return graficoLinha;
        
    }
    
    public ChartPanel criargrafico(ArrayList<Componente> listaGrafico){
        
        CategoryDataset dataSet = this.createDataset(listaGrafico);
        
        JFreeChart grafico = this.createChart(dataSet);
        
        ChartPanel painelGrafico = new ChartPanel(grafico);
        painelGrafico.setPreferredSize(new Dimension(270, 213));
        
        return painelGrafico;
    }
    
}
