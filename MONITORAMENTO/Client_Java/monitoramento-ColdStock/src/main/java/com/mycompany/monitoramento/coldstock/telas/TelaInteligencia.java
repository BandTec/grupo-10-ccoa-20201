/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoramento.coldstock.telas;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mycompany.monitoramento.coldstock.modelos.Imagem;
import com.mycompany.monitoramento.coldstock.modelos.TabelaIA;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author gabri
 */
public class TelaInteligencia extends javax.swing.JFrame {

    /**
     * Creates new form TelaInteligencia
     */
    Imagem imagem = new Imagem();

    public TelaInteligencia(Integer fkMaquina) {
        initComponents();
        this.getContentPane().setBackground(Color.decode("#343C41"));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(imagem.retornarCaminho("/1601053028644.png")));
        jLabel6.setIcon(imagem.carregarImgs("/1601053028644.png"));
        tbDados.setOpaque(true);
        tbDados.setFillsViewportHeight(true);
        tbDados.setBackground(Color.decode("#FFFFFF"));
        //tbDados.setBackground(Color.decode("#343C41"));
        analisarMaquina(fkMaquina);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblAnalise = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDados = new javax.swing.JTable();
        lblTitulo = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(31, 40, 45));

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("COLD STOCK");

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("MONITORAMENTO");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(47, 47, 47))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(3, 3, 3))
        );

        lblAnalise.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        lblAnalise.setForeground(new java.awt.Color(255, 255, 255));
        lblAnalise.setText("<html>Com essas informações, prevemos que sua máquina poderá abrir cerca de X chamados<br>amanhã, caso  não haja nenhuma alteração na máquina</html>");

        tbDados.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        tbDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome componente", "Influência", "Semana anterior", "Hoje", "Amanhã"
            }
        ));
        tbDados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDados);
        if (tbDados.getColumnModel().getColumnCount() > 0) {
            tbDados.getColumnModel().getColumn(0).setResizable(false);
        }

        lblTitulo.setAlignment(java.awt.Label.CENTER);
        lblTitulo.setBackground(new java.awt.Color(31, 40, 45));
        lblTitulo.setFont(new java.awt.Font("Montserrat", 1, 17)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Medias");

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("<html>com base nos registros anteriores,<br>obtemos as informações dos componentes mais problemáticos</html>");

        lblResultado.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        lblResultado.setForeground(new java.awt.Color(255, 255, 255));
        lblResultado.setText("Resultado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblResultado)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblAnalise)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAnalise)
                .addGap(27, 27, 27)
                .addComponent(lblResultado)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDadosMouseClicked
        // TODO add your handling code here:
        // ao clickar em alguma coluna da tabela ele mudar o combobox para a
        // os dois textos recebem um conteudos pro texto.
    }//GEN-LAST:event_tbDadosMouseClicked

    private void analisarMaquina(Integer fkMaquina) {
        try {
            String path = new File("RedeNeural//Ponte_Java_Python//").getAbsolutePath();
            
            String cmd = "cmd /c cd RedeNeural";
            cmd += " && echo "+fkMaquina+" | python "+path+ "ativador.py";
            Process p = Runtime.getRuntime().exec(cmd);
            System.out.println("Comando cmd executado!");
            
            //Dando um delay para que os arquivos python tenham tempo de rodar
            TimeUnit.SECONDS.sleep(5);
            
            BufferedReader br = new BufferedReader(new FileReader(path+"//previsao.json"));
            JsonParser parser = new JsonParser();
            JsonArray arrayJS = parser.parse(br).getAsJsonArray();
            System.out.println("Array JSON coletado");
            
            TabelaIA geradorCor = new TabelaIA();
            tbDados.setDefaultRenderer(Object.class, geradorCor);
            DefaultTableModel tabela = (DefaultTableModel) tbDados.getModel();
            
            for(int i = 0; i<arrayJS.size(); i++){
                JsonObject jsonAtual = arrayJS.get(i).getAsJsonObject();
                tabela.addRow(new Object[]{
                jsonAtual.get("nomeComponente"), 
                jsonAtual.get("influencia"),
                jsonAtual.get("mediaAnterior"),
                jsonAtual.get("mediaHoje"),
                jsonAtual.get("mediaAmanha")
                });
            }
            System.out.println("Tabela Criada");
            
            JsonObject jsonFinal = arrayJS.get(arrayJS.size()-1).getAsJsonObject();
            String analise = String.format("<html>Com essas informações, prevemos que "
                    + "sua máquina poderá abrir cerca de <b>%s</b> chamados<br>amanhã, "
                    + "caso  não haja nenhuma alteração na máquina</html>", 
                    jsonFinal.get("qtdChamados"));
            lblAnalise.setText(analise);
            
            String situacao = jsonFinal.get("situacao").toString();
            StringBuilder sb = new StringBuilder(situacao);
            sb.deleteCharAt(situacao.length() - 1);
            sb.deleteCharAt(0); 
            lblResultado.setText("A situação da maquina está: " + sb.toString());
            if(sb.toString().equals("melhorando")){
                lblResultado.setForeground(Color.GREEN);
            }
            else{
                lblResultado.setForeground(Color.RED);
            }
            
            
        } catch (Exception ex) {
            System.out.println("Deu ruim! " + ex);
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
            java.util.logging.Logger.getLogger(TelaInteligencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInteligencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInteligencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInteligencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInteligencia(4).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnalise;
    private javax.swing.JLabel lblResultado;
    private java.awt.Label lblTitulo;
    private javax.swing.JTable tbDados;
    // End of variables declaration//GEN-END:variables
}
