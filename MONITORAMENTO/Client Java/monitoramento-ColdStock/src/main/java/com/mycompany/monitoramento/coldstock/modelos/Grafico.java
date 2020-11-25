/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.modelos;

import com.sun.javafx.charts.Legend;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.block.LabelBlock;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Carlos Alberto
 */
public class Grafico {
    
    public CategoryDataset createDataset(ArrayList<Componente> listaGrafico){
        
        /*
        Essa função cria itens da lista gerada na classe Conexao.
        */
        // aqui criamos um DataSet que ira popular o grafico
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        
        // aqui separamos a lista(listaGrafico) em itens (componente) e adiconamos esses itens no DataSet criado 
        for(Componente componente : listaGrafico){
            dataSet.addValue(componente.getValor(), componente.getNomeComponente()
                    , componente.getDataHora());
        }
        
        return dataSet;
        
    }
    
    public JFreeChart createChart(CategoryDataset dataSet){
        // aqui criamos o gráfico, colocando as legendas, passando os valores (dataSet) e dizemos que a orientação dele sera vertical
        JFreeChart graficoLinha = ChartFactory.createLineChart("CPU", 
                "Horário", "Valor", dataSet, PlotOrientation.VERTICAL,
                true, false, false);
        return graficoLinha;
        
    }
    
    public ChartPanel criargrafico(ArrayList<Componente> listaGrafico){
        
        CategoryDataset dataSet = this.createDataset(listaGrafico);
        
        JFreeChart grafico = this.createChart(dataSet);
        ChartPanel painelGrafico = new ChartPanel(grafico);
        //painelGrafico.setPreferredSize(new Dimension(700, 313));
        return painelGrafico;
    }
    
}
