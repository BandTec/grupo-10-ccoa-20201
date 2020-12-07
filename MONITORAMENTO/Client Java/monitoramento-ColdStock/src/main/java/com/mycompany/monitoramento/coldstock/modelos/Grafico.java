/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.modelos;

import com.mycompany.monitoramento.coldstock.telas.JanelaGrafico;
import com.sun.javafx.charts.Legend;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.CategoryAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.LabelBlock;
import org.jfree.chart.labels.CategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author Carlos Alberto
 */
public class Grafico {

    public CategoryDataset createDataset(ArrayList<Componente> listaGrafico) {

        /*
        Essa função cria itens da lista gerada na classe Conexao.
         */
        // aqui criamos um DataSet que ira popular o grafico
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        // aqui separamos a lista(listaGrafico) em itens (componente) e adiconamos esses itens no DataSet criado 
        for (Componente componente : listaGrafico) {
            dataSet.addValue(componente.getValor(), componente.getNomeComponente(),
                     componente.getDataHora());
        }

        return dataSet;

    }

    public JFreeChart createChart(CategoryDataset dataSet, String componente) {
        // aqui criamos o gráfico, colocando as legendas, passando os valores (dataSet) e dizemos que a orientação dele sera vertical
        JFreeChart graficoLinha = ChartFactory.createLineChart(componente,
                "Horário", "Valor", dataSet, PlotOrientation.VERTICAL,
                true, false, false);

        return graficoLinha;

    }

    public ChartPanel criargrafico(ArrayList<Componente> listaGrafico, String componente) {

        CategoryDataset dataSet = this.createDataset(listaGrafico);

        JFreeChart grafico = this.createChart(dataSet, componente);
        AbstractCategoryItemRenderer renderer = (AbstractCategoryItemRenderer) grafico.getCategoryPlot().getRenderer();
        //setando a largura da maquina
        BasicStroke stroke = new BasicStroke(3f);
        renderer.setSeriesStroke(0, stroke);
        //Setando a cor da linha do grafico
        renderer.setSeriesPaint(0, new Color(99, 182, 177));
        ChartPanel painelGrafico = new ChartPanel(grafico);

        CategoryPlot plot = (CategoryPlot) grafico.getPlot();
        org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
        // colocando a a label virada pra baixo
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));

        painelGrafico.setPreferredSize(new Dimension(640, 480));
        return painelGrafico;
    }

}
