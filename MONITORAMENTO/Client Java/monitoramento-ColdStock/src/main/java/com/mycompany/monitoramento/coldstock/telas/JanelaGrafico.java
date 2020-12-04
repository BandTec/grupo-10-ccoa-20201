/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.telas;

import com.mycompany.monitoramento.coldstock.modelos.ClsBD;
import com.mycompany.monitoramento.coldstock.modelos.Componente;
import com.mycompany.monitoramento.coldstock.modelos.Conexao;
import com.mycompany.monitoramento.coldstock.modelos.Grafico;
import com.mycompany.monitoramento.coldstock.modelos.Maquinas;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;

/**
 *
 * @author Carlos Alberto
 */
public class JanelaGrafico extends javax.swing.JFrame {
    ClsBD objBD = new ClsBD();
    List<Maquinas> retornoBD;
    private Integer fkMaquina = 0; //Essa é o atributo de instancia que recebera o valor da ComboBox
    private String metrica = "";

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
        carregarComponentes(); //Carregar os componentes da Tela
    }
    /**
     * Creates new form JanelaGrafico
     */
    
    
    public JanelaGrafico() throws SQLException {
        initComponents();
        
        // criamos um temporizador, que irá executar uma ação com um determinado intervalo de tempo
        Timer temporizador = new Timer();
        
        //aqui setamos o intervalo de tempo
        Integer tempo = 5000;
        
        //aqui criamos uma tarefa, essa tarefa sera executar a função atualizarGrafico()
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                atualizarGrafico();
                
            }
        };
        
        //aqui executamos o temporizador, especificamos a tarefa, o tempo que vai demorar pra ela começar a rodar,
        //e o tempo do intervalo entre uma execução e outra.
        temporizador.scheduleAtFixedRate(tarefa, 0, tempo);
    }
    
    private void carregarComponentes(){
        try{
            // a logica é a mesma da tela anterior, onde populamos a combo box das maquinas
            objBD.conectar();
            ResultSet retornoBD = objBD.consultarComponentes(fkMaquina);
            while(retornoBD.next()){
                String nomeComponente  = retornoBD.getString("nomeComponente");
                cbComponentes.addItem(nomeComponente);
                // na combo box, adicionamos o nome dos componentes
            }
        }
        catch(SQLException se){
            System.out.println(se);
        }
    }
    
    public void atualizarGrafico() {
        
        this.jpnGrafico.setLayout(new BorderLayout());
        this.jpnGrafico2.setLayout(new BorderLayout());
            
        //criamos 3 objetos da classe conexao, para poder utilizar cada objeto para um grafico
        Conexao campo1 = new Conexao();
        Conexao campo2 = new Conexao();
        Conexao campo3 = new Conexao();
        
        //primeiramente, criamos listas que irão receber o resultado dos selects executados
        List<Componente> listaComponente = null;
        List<Componente> listaComponente2 = null;
        List<Componente> listaComponente3 = null;
        
        //aqui executamos os devidos selects, passando como parametro o objeto que vai realizar
        //a conexao, o id do 
        
        listaComponente = campo1.trazerLista(campo1.conectar(), 1, (String) cbComponentes.getSelectedItem(), this.fkMaquina);
        listaComponente2 = campo2.trazerLista(campo2.conectar(), 2, (String) cbComponentes.getSelectedItem(), this.fkMaquina);
        listaComponente3 = campo3.trazerLista(campo3.conectar(), 3, (String) cbComponentes.getSelectedItem(), this.fkMaquina);
        
        //agora vamos começar o processo de converter essas listas para arralists
        //primeiro, criamos as arraylists
        ArrayList<Componente> listaGrafico = new ArrayList<>();
        ArrayList<Componente> listaGrafico2 = new ArrayList<>();
        ArrayList<Componente> listaGrafico3 = new ArrayList<>();

        //e começamosa rodar FORs que vao passar por cada item da listaComponente,
        //e adicionando esses itens na arraylist
        //fazemos isso para as 3 listas
        for (Componente componente : listaComponente) {
            listaGrafico.add(componente);
        }
        for (Componente componente : listaComponente2) {
            listaGrafico2.add(componente);
        }
        for (Componente componente : listaComponente3) {
            listaGrafico3.add(componente);
        }
        
        //agora criamos 2 objetos de grafico
        Grafico graficoLinha = new Grafico();
        Grafico graficoLinha2 = new Grafico();
        
        //e populamos o grafico com a arraylist
        this.jpnGrafico.add(graficoLinha.criargrafico(listaGrafico, (String) cbComponentes.getSelectedItem()));
        this.jpnGrafico2.add(graficoLinha2.criargrafico(listaGrafico2, (String) cbComponentes.getSelectedItem()));
        
        lbMedida.setText(listaGrafico3.get(0).getValor().toString());

        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnGrafico = new javax.swing.JPanel();
        jpnGrafico2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbMedida = new javax.swing.JLabel();
        lblMetrica = new javax.swing.JLabel();
        btVisualizar = new javax.swing.JButton();
        cbComponentes = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        javax.swing.GroupLayout jpnGraficoLayout = new javax.swing.GroupLayout(jpnGrafico);
        jpnGrafico.setLayout(jpnGraficoLayout);
        jpnGraficoLayout.setHorizontalGroup(
            jpnGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jpnGraficoLayout.setVerticalGroup(
            jpnGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnGrafico2Layout = new javax.swing.GroupLayout(jpnGrafico2);
        jpnGrafico2.setLayout(jpnGrafico2Layout);
        jpnGrafico2Layout.setHorizontalGroup(
            jpnGrafico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jpnGrafico2Layout.setVerticalGroup(
            jpnGrafico2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(31, 40, 45));

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("COLD STOCK");

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Gráficos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 393, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(47, 47, 47))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(56, 56, 56))
        );

        jPanel1.setBackground(new java.awt.Color(31, 40, 45));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        jLabel8.setText("Medida Atual:");

        lblMetrica.setText("GHz");

        btVisualizar.setBackground(new java.awt.Color(77, 172, 166));
        btVisualizar.setText("Visualizar");
        btVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jpnGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpnGrafico2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMetrica))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(cbComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btVisualizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btVisualizar)
                    .addComponent(cbComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnGrafico2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpnGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(lblMetrica)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizarActionPerformed
        MudarMetrica();
        atualizarGrafico();
        
    }//GEN-LAST:event_btVisualizarActionPerformed
    
    private void MudarMetrica(){
        try{
            // a logica é a mesma da tela anterior, onde populamos a combo box das maquinas
            objBD.conectar();
            ResultSet retornoBD = objBD.consultarComponentes(fkMaquina, String.valueOf(cbComponentes.getSelectedItem()));
            while(retornoBD.next()){
                lblMetrica.setText(retornoBD.getString("metrica"));
            }
        }
        catch(SQLException se){
            System.out.println(se);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(JanelaGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JanelaGrafico().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JanelaGrafico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btVisualizar;
    private javax.swing.JComboBox<String> cbComponentes;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jpnGrafico;
    private javax.swing.JPanel jpnGrafico2;
    private javax.swing.JLabel lbMedida;
    private javax.swing.JLabel lblMetrica;
    // End of variables declaration//GEN-END:variables
}
