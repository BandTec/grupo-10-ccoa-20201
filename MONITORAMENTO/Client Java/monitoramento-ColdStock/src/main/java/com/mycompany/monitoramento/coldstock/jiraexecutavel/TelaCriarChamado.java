/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.jiraexecutavel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.monitoramento.coldstock.jiramodelos.Issue;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class TelaCriarChamado extends javax.swing.JFrame {
    
    /**
     * Creates new form TelaCriarChamado
     */
    public TelaCriarChamado() {
        initComponents();
        
    }
    
Gson gson = new GsonBuilder().setPrettyPrinting().create();

    ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
            "coldstock.atlassian.net",
            "201grupo10c@bandtec.com.br",
            "xYT7D7fZZRvpKWl0svMyC6C9"
    );

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfNomeChave = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfResumo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbTipoChamado = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescricao = new javax.swing.JTextArea();
        btCriarChamado = new javax.swing.JButton();
        lbResultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(31, 40, 45));

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("COLD STOCK");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CHAMADOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(3, 3, 3))
        );

        jLabel1.setText("Nome chave:");

        tfNomeChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeChaveActionPerformed(evt);
            }
        });

        jLabel3.setText("Resumo chamado:");

        jLabel5.setText("Descrição chamado:");

        jLabel6.setText("Tipo chamdo:");

        cbTipoChamado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALERTA-CPU", "ALERTA-MEMÓRIA", "ALERTA-DISCO", "ALERTA-CONEXÃO", "ALERTA-TEMPERATURA" }));
        cbTipoChamado.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                cbTipoChamadoComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                cbTipoChamadoComponentResized(evt);
            }
        });
        cbTipoChamado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoChamadoActionPerformed(evt);
            }
        });

        taDescricao.setColumns(20);
        taDescricao.setRows(5);
        jScrollPane1.setViewportView(taDescricao);

        btCriarChamado.setBackground(new java.awt.Color(77, 172, 166));
        btCriarChamado.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        btCriarChamado.setForeground(new java.awt.Color(255, 255, 255));
        btCriarChamado.setText("Criar Chamado");
        btCriarChamado.setMaximumSize(new java.awt.Dimension(131, 29));
        btCriarChamado.setMinimumSize(new java.awt.Dimension(131, 29));
        btCriarChamado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCriarChamadoActionPerformed(evt);
            }
        });

        lbResultado.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        lbResultado.setForeground(new java.awt.Color(0, 200, 200));
        lbResultado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbResultadoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomeChave, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfResumo, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoChamado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btCriarChamado, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfNomeChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfResumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbTipoChamado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(btCriarChamado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tfNomeChaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeChaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeChaveActionPerformed

    private void cbTipoChamadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoChamadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoChamadoActionPerformed

    private void btCriarChamadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCriarChamadoActionPerformed

        Issue novaIssue = new Issue();
       novaIssue.setProjectKey(tfNomeChave.getText().toUpperCase());
        novaIssue.setSummary(tfResumo.getText());
        novaIssue.setDescription(taDescricao.getText());
        novaIssue.setLabels(cbTipoChamado.getActionCommand());
        

        try {
            clienteJiraApi.criarIssue(novaIssue);
            lbResultado.setText("Chamado criado com sucesso!!!");
            
        } catch (IOException ex) {
            Logger.getLogger(TelaCriarChamado.class.getName()).log(Level.SEVERE, null, ex);
            
            lbResultado.setText("Erro ao criar chamado,tente novaCkCKsmente!!");
            lbResultado.setForeground(Color.red);
            
        }
        System.out.println("Issue criada: "+gson.toJson(novaIssue));
        
          

    }//GEN-LAST:event_btCriarChamadoActionPerformed

    private void cbTipoChamadoComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cbTipoChamadoComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoChamadoComponentResized

    private void cbTipoChamadoComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cbTipoChamadoComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoChamadoComponentMoved

    private void lbResultadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbResultadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbResultadoMouseClicked

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
            java.util.logging.Logger.getLogger(TelaCriarChamado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCriarChamado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCriarChamado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCriarChamado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCriarChamado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCriarChamado;
    private javax.swing.JComboBox<String> cbTipoChamado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbResultado;
    private javax.swing.JTextArea taDescricao;
    private javax.swing.JTextField tfNomeChave;
    private javax.swing.JTextField tfResumo;
    // End of variables declaration//GEN-END:variables
}
