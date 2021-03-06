/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.telas;

import com.mycompany.monitoramento.coldstock.modelos.Consulta;
import com.mycompany.monitoramento.coldstock.modelos.Operacao;
import com.mycompany.monitoramento.coldstock.modelos.Imagem;
import com.mycompany.monitoramento.coldstock.modelos.Maquina;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaEscolha extends javax.swing.JFrame {

    // aqui criamos objetos das classes que iremos utilizar
    TelaEditarMaquina telaEditarMaquina = new TelaEditarMaquina();
    Consulta objBD = new Consulta();
    List<Maquina> retornoBD;
    Maquina maquina;
    //Imagem imagem = new Imagem();

    //public Integer idMaquina;
    public TelaEscolha() {

        initComponents();
        this.getContentPane().setBackground(Color.decode("#343C41"));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("1601053028644.png")));
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(imagem.retornarCaminho("/1601053028644.png")));
        ImageIcon imagem = new ImageIcon(getClass().getClassLoader().getResource("1601053028644.png"));
        ImageIcon imagem2 = new ImageIcon(getClass().getClassLoader().getResource("Pingulinomonitoramento.png"));
        jLabel2.setIcon(imagem);
        jLabel1.setIcon(imagem2);
        carregarMaquinas();
        btnIA.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        cbEscolhaMaquina = new javax.swing.JComboBox<>();
        btnOk = new javax.swing.JButton();
        btnOk1 = new javax.swing.JButton();
        btnIA = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MONITORAMENTO");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        setSize(new java.awt.Dimension(736, 465));

        jPanel1.setBackground(new java.awt.Color(31, 40, 45));

        jLabel1.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
        jLabel4.setText("MONITORAMENTO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        btnCadastrar.setBackground(new java.awt.Color(77, 172, 166));
        btnCadastrar.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("CADASTRAR");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("<html>Bem vindo ao monitoramento de <br>máquinas do Cold Stock, Qureeck!<br>Para começar, basta selecionar uma<br>máquina e começar a edita-la! Qureeck!</html>");

        btnExcluir.setBackground(new java.awt.Color(77, 172, 166));
        btnExcluir.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluir.setText("EXCLUIR");
        btnExcluir.setMaximumSize(new java.awt.Dimension(131, 29));
        btnExcluir.setMinimumSize(new java.awt.Dimension(131, 29));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        cbEscolhaMaquina.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        cbEscolhaMaquina.setForeground(new java.awt.Color(153, 153, 153));
        cbEscolhaMaquina.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEscolhaMaquinaItemStateChanged(evt);
            }
        });
        cbEscolhaMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEscolhaMaquinaActionPerformed(evt);
            }
        });

        btnOk.setBackground(new java.awt.Color(77, 172, 166));
        btnOk.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        btnOk.setForeground(new java.awt.Color(255, 255, 255));
        btnOk.setText("EDITAR");
        btnOk.setMaximumSize(new java.awt.Dimension(131, 29));
        btnOk.setMinimumSize(new java.awt.Dimension(131, 29));
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnOk1.setBackground(new java.awt.Color(77, 172, 166));
        btnOk1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        btnOk1.setForeground(new java.awt.Color(255, 255, 255));
        btnOk1.setText("Dashboard");
        btnOk1.setMaximumSize(new java.awt.Dimension(131, 29));
        btnOk1.setMinimumSize(new java.awt.Dimension(131, 29));
        btnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk1ActionPerformed(evt);
            }
        });

        btnIA.setBackground(new java.awt.Color(77, 172, 166));
        btnIA.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        btnIA.setForeground(new java.awt.Color(255, 255, 255));
        btnIA.setText("Inteligência Artificial");
        btnIA.setMaximumSize(new java.awt.Dimension(131, 29));
        btnIA.setMinimumSize(new java.awt.Dimension(131, 29));
        btnIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCadastrar)
                .addGap(211, 211, 211))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEscolhaMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnIA, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCadastrar)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEscolhaMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (cbEscolhaMaquina.getSelectedItem().equals("-- Escolha --")) {
            return;
        }
        // Separar palavra por "-"
        String[] separador = String.valueOf((cbEscolhaMaquina.getSelectedItem())).split(" - ");
        // Setando o atributo estatico fkMaquina
        Maquina.fkmaquina = Integer.valueOf(separador[1]);
        // Confirm para deletar alguma maquina e colocando o retorno dentro do "retorno"
        Integer retorno = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir está máquina?", "Aviso",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (retorno == JOptionPane.YES_OPTION) {
            // excluindo as maquinas
            new Operacao().excluirMaquina(Maquina.fkmaquina);
            //reload das maquinas
            carregarMaquinas();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if (cbEscolhaMaquina.getSelectedItem().equals("-- Escolha --")) {
            return;
        }

        // aqui pegamos um item especifico da lista, sendo aquele que é equivalente ao indice selecionado da combobox
        // Separar palavra por "-"
        String[] separador = String.valueOf((cbEscolhaMaquina.getSelectedItem())).split(" - ");
        // Setando o atributo estatico fkMaquina
        Maquina.fkmaquina = Integer.valueOf(separador[1]);
        //para saber quais informações teremos que passar pra proxima tela, temos que saber qual é a maquina
        //aqui iremos pegar qual o indice do item da combo box que foi escolhido

        Integer num = Integer.valueOf(separador[1]);
        maquina = retornoBD.get(num - 1);
        //por fim, passamos o item buscado para a proxima tela, mandando o id e o nome da maquina
        telaEditarMaquina.carregarTabela(Maquina.fkmaquina, maquina.getNomeMaquina());
        // e deixamos a proxima tela visivel
        telaEditarMaquina.setVisible(true);
        System.out.println(maquina.getIdMaquina());
        System.out.println(maquina.getNomeMaquina());

    }//GEN-LAST:event_btnOkActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk1ActionPerformed
        /*
        Aqui é onde iremos mandar o valor da comboBox para o grafico
        Pegando o Index dela, que por enquanto Varia de 0 e 1, mas precisamos de
        1 e 2, e por isso somamos mais um, e tambem eu fiz uma alteração na função
        trazerGrafico();
        onde ele recebe mais um parametro, que seria a FkMaquina
         */
        if (cbEscolhaMaquina.getSelectedItem().equals("-- Escolha --")) {
            return;
        }
        String[] separador = String.valueOf((cbEscolhaMaquina.getSelectedItem())).split(" - ");
        Maquina.fkmaquina = Integer.valueOf(separador[1]);

        ResultSet listaComponente = null;
        try {
            listaComponente = new Consulta().consultarRegistros(1);
            //Verificando se existem registros daquela maquina
            if (listaComponente.next()) {
                JanelaGrafico grafico = null;
                // Iniciando a JanelaGrafico
                try {
                    grafico = new JanelaGrafico();

                } catch (SQLException ex) {
                    Logger.getLogger(TelaEscolha.class.getName()).log(Level.SEVERE, null, ex);
                }

                grafico.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Está máquina não possui registros", "ERRO", JOptionPane.OK_OPTION);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaEscolha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOk1ActionPerformed


    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        GridLayout layout = new GridLayout(2, 2);

        // Configurações para a caixa de texto, com 2 campos de texto com tamanho 10,  e 2 labels
        JTextField nome = new JTextField(10);
        JTextField tipo = new JTextField(10);
        JPanel myPanel = new JPanel();
        myPanel.setLayout(layout);

        myPanel.add(new JLabel("Nome:"));
        myPanel.add(nome);

        myPanel.add(new JLabel("Tipo: "));
        myPanel.add(tipo);
        // adicionando o painel dentro do ShowConfirmDialog,  e pegando o resultado com o result
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Coloque o nome e o tipo da maquina da qual deseja adicionar.", JOptionPane.YES_NO_OPTION);
        // Adicionando o os dados das caixas de textos no banco de dados. 
        if (result == JOptionPane.YES_OPTION && !(nome.getText().equals("") || tipo.getText().equals(""))) {
            new Operacao().adicionarMaquina(nome.getText(), tipo.getText());
            carregarMaquinas();
            System.out.println("Foi");
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void cbEscolhaMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEscolhaMaquinaActionPerformed
//        btnIA.enable(false);
//        if(cbEscolhaMaquina.getSelectedItem() == (null))
//            return;
//        if (cbEscolhaMaquina.getSelectedItem().equals("-- Escolha --"))
//            return;
//        else {
//            String[] separador = String.valueOf((cbEscolhaMaquina.getSelectedItem())).split(" - ");
//            Integer fkMaquina = Integer.valueOf(separador[1]);
//            try {
//                ResultSet rs = objBD.consultarQtdRegistros(fkMaquina);
//                if(rs.next()){
//                    Integer qtdItens = rs.getInt("qtdComponentes");
//                    if(qtdItens > 30){
//                        System.out.println("Habilitar IA");
//                        btnIA.setVisible(true);
//                        //btnIA.enable(true);
//                    }
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex);
//            }
//        }
    }//GEN-LAST:event_cbEscolhaMaquinaActionPerformed

    private void btnIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIAActionPerformed
        System.out.println("Iniciando acao do botao IA");
        String[] separador = String.valueOf((cbEscolhaMaquina.getSelectedItem())).split(" - ");
        Integer fkMaquina = Integer.valueOf(separador[1]);
        TelaInteligencia tela = new TelaInteligencia(fkMaquina);
        tela.show();
    }//GEN-LAST:event_btnIAActionPerformed

    private void cbEscolhaMaquinaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEscolhaMaquinaItemStateChanged
        // TODO add your handling code here:
        btnIA.enable(false);
        if(cbEscolhaMaquina.getSelectedItem() == (null))
            return;
        if (cbEscolhaMaquina.getSelectedItem().equals("-- Escolha --"))
            return;
        else {
            String[] separador = String.valueOf((cbEscolhaMaquina.getSelectedItem())).split(" - ");
            Integer fkMaquina = Integer.valueOf(separador[1]);
            try {
                ResultSet rs = objBD.consultarQtdRegistros(fkMaquina);
                if(rs.next()){
                    Integer qtdItens = rs.getInt("qtdComponentes");
                    if(qtdItens > 30){
                        System.out.println("Habilitar IA");
                        btnIA.setVisible(true);
                        //btnIA.enable(true);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_cbEscolhaMaquinaItemStateChanged

    /**
     * @param args the command line arguments
     */
    private void carregarMaquinas() {
        //essa função popul o combo box que tem todas as maquinas
        try {
            //realizamos a conexao
            objBD.conectar();
            //pegamos o resultado do select das maquinas
            retornoBD = objBD.consultarMaquinas();
            //Limpamos a combobox para não repetir itens
            cbEscolhaMaquina.removeAllItems();
            cbEscolhaMaquina.addItem("-- Escolha --");
            for (Maquina maquina : retornoBD) {
                //separamos cada item da lista que foi gerada
                //separamos de cada item, o nome da maquina e o tipo(se é servidor ou maquina comum)
                String nomeMaquina = maquina.getNomeMaquina();
                Integer idMaquina = maquina.getIdMaquina();
                //e criamos um item na combo box que ira receber o nome da maquina
                cbEscolhaMaquina.addItem(nomeMaquina + " - " + idMaquina);
            }

        } catch (SQLException se) {
            System.out.println(se);
        }
//        String[] separador = String.valueOf((cbEscolhaMaquina.getSelectedItem())).split(" - ");
//        idMaquina = Integer.valueOf(separador[1]);

    }

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
            java.util.logging.Logger.getLogger(TelaEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaEscolha().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIA;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnOk1;
    private javax.swing.JComboBox<String> cbEscolhaMaquina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
