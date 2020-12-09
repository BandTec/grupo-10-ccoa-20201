/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.modelos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Carlos Alberto
 */
public class Grafico {

    private CategoryDataset createDataset(ArrayList<Registros> listaGrafico) {

        /*
        Essa função cria itens da lista gerada na classe Conexao.
         */
        // aqui criamos um DataSet que ira popular o grafico
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        // aqui separamos a lista(listaGrafico) em itens (componente) e adiconamos esses itens no DataSet criado 
        for (Registros componente : listaGrafico) {
            dataSet.addValue(componente.getValor(), componente.getNomeComponente(),
                    componente.getDataHora());
        }

        return dataSet;

    }

    private PieDataset createDatasetPie(ArrayList<Registros> listaGrafico) throws SQLException {
        /*
        Essa função cria itens da lista gerada na classe Conexao.
         */
        // aqui criamos um DataSet que ira popular o grafico
        DefaultPieDataset dataset = new DefaultPieDataset();
        Double livre = 0.00;
        ResultSet rs = new Consultas().consultarMaximas();
        while (rs.next()) {
            if (rs.getInt("fkcomponente") == 3) {
                livre = rs.getDouble("capacidadeMax");
            }
        }
        // aqui separamos a lista(listaGrafico) em itens (componente) e adiconamos esses itens no DataSet criado 
        for (Registros componente : listaGrafico) {
            dataset.setValue("Ocupado", componente.getValor());
            dataset.setValue("Livre", (livre - componente.getValor()));

        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataSet, String componente) {
        // aqui criamos o gráfico, colocando as legendas, passando os valores (dataSet) e dizemos que a orientação dele sera vertical
        JFreeChart graficoLinha = ChartFactory.createLineChart(componente,
                "Horário", "Valor", dataSet, PlotOrientation.VERTICAL,
                true, false, false);

        return graficoLinha;

    }

    public ChartPanel criargrafico(ArrayList<Registros> listaGrafico, String componente) throws SQLException {
        
        
        JFreeChart grafico = null;
        ChartPanel painelGrafico = null;
        if (!componente.toUpperCase().equals("DISCO")) {
            CategoryDataset dataSet = this.createDataset(listaGrafico);

            grafico = this.createChart(dataSet, componente);
            AbstractCategoryItemRenderer renderer = (AbstractCategoryItemRenderer) grafico.getCategoryPlot().getRenderer();
            //setando a largura da maquina
            BasicStroke stroke = new BasicStroke(3f);
            renderer.setSeriesStroke(0, stroke);
            //Setando a cor da linha do grafico
            renderer.setSeriesPaint(0, new Color(99, 182, 177));
            
            CategoryPlot plot = (CategoryPlot) grafico.getPlot();
            org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
            // colocando a a label virada pra baixo
            domainAxis.setCategoryLabelPositions(
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));
            
        } else {
            PieDataset dataset = createDatasetPie(listaGrafico);
            grafico = ChartFactory.createPieChart(
                    componente,
                    dataset,
                    true,
                    true,
                    false);

            PiePlot p = (PiePlot) grafico.getPlot();
            p.setBackgroundPaint(null);

            p.setSectionPaint("Ocupado", Color.BLACK);
            p.setSectionPaint("Livre", new Color(99, 182, 177));
        }

        painelGrafico = new ChartPanel(grafico);
        
        if(!componente.toUpperCase().equals("DISCO")){
            MouseListener ml[] = painelGrafico.getMouseListeners();

            if (ml != null) {
                for (int i = 0; i < ml.length; i++) {
                    painelGrafico.removeMouseListener(ml[i]);
                }
            }
        }
        
        painelGrafico.setPreferredSize(new Dimension(640, 480));

        return painelGrafico;
    }

}
