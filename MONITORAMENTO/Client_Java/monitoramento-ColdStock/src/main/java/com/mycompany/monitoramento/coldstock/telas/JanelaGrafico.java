/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.telas;

import com.mycompany.monitoramento.coldstock.modelos.Consulta;
import com.mycompany.monitoramento.coldstock.modelos.Registro;
import com.mycompany.monitoramento.coldstock.modelos.Operacao;
import com.mycompany.monitoramento.coldstock.modelos.Grafico;
import com.mycompany.monitoramento.coldstock.modelos.Imagem;
import com.mycompany.monitoramento.coldstock.modelos.Maquina;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    Consulta objBD = new Consulta();
    List<Maquina> retornoBD;
    Imagem imagem = new Imagem();
    //private Integer fkMaquina = 0; //Essa é o atributo de instancia que recebera o valor da ComboBox
    /**
     * Creates new form JanelaGrafico
     */
    public JanelaGrafico() throws SQLException {
        this.getContentPane().setBackground(Color.decode("#343C41"));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(imagem.retornarCaminho("/1601053028644.png")));
        
        initComponents();
        //carregar os componentes ex comboBox, e grafico
        carregarComponentes();
        // criamos um temporizador, que irá executar uma ação com um determinado intervalo de tempo
        atualizarGrafico();
        Timer temporizador = new Timer();
        jLabel6.setIcon(imagem.carregarImgs("/1601053028644.png"));
        //aqui setamos o intervalo de tempo
        Integer tempo = 5000;

        //aqui criamos uma tarefa, essa tarefa sera executar a função atualizarGrafico()
        TimerTask tarefa = new TimerTask() {
            @Override
            
            public void run() {
                
                try {
                    atualizarGrafico();
                } catch (SQLException ex) {
                    Logger.getLogger(JanelaGrafico.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    new Operacao().comparacao();
                } catch (SQLException ex) {
                    Logger.getLogger(TelaEscolha.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        //aqui executamos o temporizador, especificamos a tarefa, o tempo que vai demorar pra ela começar a rodar,
        //e o tempo do intervalo entre uma execução e outra.
        
        temporizador.scheduleAtFixedRate(tarefa, 0, tempo);
    }

    protected void carregarComponentes() {
        try {
            // a logica é a mesma da tela anterior, onde populamos a combo box das maquinas
            objBD.conectar();
            ResultSet retornoBD = objBD.consultarComponentes(Maquina.fkmaquina);
            cbComponentes.removeAll();
            while (retornoBD.next()) {
                String nomeComponente = retornoBD.getString("nomeComponente");
                cbComponentes.addItem(nomeComponente);
                // na combo box, adicionamos o nome dos componentes
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
    }

    protected void atualizarGrafico() throws SQLException {
        if(!this.isShowing()){
            return;
        }
        this.jpnGrafico.setLayout(new BorderLayout());
        this.jpnGrafico2.setLayout(new BorderLayout());

        //criamos 3 objetos da classe conexao, para poder utilizar cada objeto para um grafico
        Operacao campo1 = new Operacao();
        Operacao campo2 = new Operacao();
        Operacao campo3 = new Operacao();

        //primeiramente, criamos listas que irão receber o resultado dos selects executados
        List<Registro> listaComponente = null;
        List<Registro> listaComponente2 = null;
        List<Registro> listaComponente3 = null;

        //aqui executamos os devidos selects, passando como parametro o objeto que vai realizar
        //a conexao, o id do 
        System.out.println(Maquina.fkmaquina);

        listaComponente = campo1.trazerLista(campo1.conectar(), 1, (String) cbComponentes.getSelectedItem(), Maquina.fkmaquina);
        listaComponente2 = campo2.trazerLista(campo2.conectar(), 2, (String) cbComponentes.getSelectedItem(), Maquina.fkmaquina);
        listaComponente3 = campo3.trazerLista(campo3.conectar(), 3, (String) cbComponentes.getSelectedItem(), Maquina.fkmaquina);

        
        //agora vamos começar o processo de converter essas listas para arralists
        //primeiro, criamos as arraylists
        ArrayList<Registro> listaGrafico = new ArrayList<>();
        ArrayList<Registro> listaGrafico2 = new ArrayList<>();
        ArrayList<Registro> listaGrafico3 = new ArrayList<>();
        System.out.println(Maquina.fkmaquina);
        //e começamosa rodar FORs que vao passar por cada item da listaComponente,
        //e adicionando esses itens na arraylist
        //fazemos isso para as 3 listas
        if (!listaComponente.get(0).getNomeComponente().toUpperCase().equals("DISCO")) {
            for (Registro componente : listaComponente) {

                listaGrafico.add(componente);

            }
            for (Registro componente : listaComponente2) {
                listaGrafico2.add(componente);
            }
            for (Registro componente : listaComponente3) {
                listaGrafico3.add(componente);
            }
        }
        else{
            for (Registro componente : listaComponente3) {
                listaGrafico.add(componente);
                listaGrafico2.add(componente);
                listaGrafico3.add(componente);
            }
        }
        //agora criamos 2 objetos de grafico
        Grafico graficoLinha = new Grafico();
        Grafico graficoLinha2 = new Grafico();
        //Removendo graficos antigos
        this.jpnGrafico.removeAll();
        this.jpnGrafico2.removeAll();
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
        btVisualizar = new javax.swing.JButton();
        cbComponentes = new javax.swing.JComboBox<>();
        lbMedida = new javax.swing.JLabel();
        lbMetrica = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setResizable(false);

        jpnGrafico.setEnabled(false);

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

        jpnGrafico2.setEnabled(false);

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

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Medida Atual:");

        btVisualizar.setBackground(new java.awt.Color(77, 172, 166));
        btVisualizar.setText("Visualizar");
        btVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVisualizarActionPerformed(evt);
            }
        });

        lbMedida.setFont(new java.awt.Font("Montserrat", 0, 30)); // NOI18N
        lbMedida.setForeground(new java.awt.Color(255, 255, 255));

        lbMetrica.setFont(new java.awt.Font("Montserrat", 0, 30)); // NOI18N
        lbMetrica.setForeground(new java.awt.Color(255, 255, 255));
        lbMetrica.setText("GHz");

        jLabel9.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Médias semanais");

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Médias Mensais");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel8)
                        .addGap(30, 30, 30)
                        .addComponent(lbMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbMetrica))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btVisualizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnGrafico2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btVisualizar)
                    .addComponent(cbComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnGrafico2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpnGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(lbMetrica))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVisualizarActionPerformed
        MudarMetrica();
        try {
            atualizarGrafico();
        } catch (SQLException ex) {
            Logger.getLogger(JanelaGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btVisualizarActionPerformed

    private void MudarMetrica() {
        try {
            // a logica é a mesma da tela anterior, onde populamos a combo box das maquinas
            objBD.conectar();
            ResultSet retornoBD = objBD.consultarComponentes(Maquina.fkmaquina, String.valueOf(cbComponentes.getSelectedItem()));
            while (retornoBD.next()) {
                lbMetrica.setText(retornoBD.getString("metrica"));
            }
            retornoBD.close();
        } catch (SQLException se) {
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
            java.util.logging.Logger.getLogger(JanelaGrafico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaGrafico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaGrafico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaGrafico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JanelaGrafico().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JanelaGrafico.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btVisualizar;
    private javax.swing.JComboBox<String> cbComponentes;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jpnGrafico;
    private javax.swing.JPanel jpnGrafico2;
    private javax.swing.JLabel lbMedida;
    private javax.swing.JLabel lbMetrica;
    // End of variables declaration//GEN-END:variables
}
