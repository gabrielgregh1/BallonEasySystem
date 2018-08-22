/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import bemajava.Bematech;
import java.awt.Toolkit;


public class JanelaPrincipal extends javax.swing.JFrame {  
    
    public JanelaPrincipal() {
        initComponents();
        icon();
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }

    private void icon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/img_azul/iconsBalloonPrincipal_64.png")));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRaiz = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonVenda = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonVendas = new javax.swing.JButton();
        jButtonEstatisticas = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jToolBar4 = new javax.swing.JToolBar();
        jButtonControleEstoque = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jToolBar6 = new javax.swing.JToolBar();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Balloon Easy");

        jToolBar1.setRollover(true);

        jButtonVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButtonVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconVendaCaixa_40px.png"))); // NOI18N
        jButtonVenda.setText("Venda");
        jButtonVenda.setFocusable(false);
        jButtonVenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonVenda.setMaximumSize(new java.awt.Dimension(70, 90));
        jButtonVenda.setMinimumSize(new java.awt.Dimension(70, 90));
        jButtonVenda.setPreferredSize(new java.awt.Dimension(70, 90));
        jButtonVenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVendaActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonVenda);

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsGastos_40px.png"))); // NOI18N
        jButton2.setText("Despesas");
        jButton2.setAutoscrolls(true);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(70, 90));
        jButton2.setMinimumSize(new java.awt.Dimension(70, 90));
        jButton2.setName(""); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(70, 90));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButtonVendas.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButtonVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsContabilidade_40px.png"))); // NOI18N
        jButtonVendas.setText("Relatório");
        jButtonVendas.setToolTipText("");
        jButtonVendas.setFocusable(false);
        jButtonVendas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonVendas.setMaximumSize(new java.awt.Dimension(70, 90));
        jButtonVendas.setMinimumSize(new java.awt.Dimension(70, 90));
        jButtonVendas.setPreferredSize(new java.awt.Dimension(70, 90));
        jButtonVendas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVendasActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonVendas);

        jButtonEstatisticas.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButtonEstatisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconEstatisticas_40px.png"))); // NOI18N
        jButtonEstatisticas.setText("Estatísticas");
        jButtonEstatisticas.setFocusable(false);
        jButtonEstatisticas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEstatisticas.setMaximumSize(new java.awt.Dimension(70, 90));
        jButtonEstatisticas.setMinimumSize(new java.awt.Dimension(70, 90));
        jButtonEstatisticas.setPreferredSize(new java.awt.Dimension(70, 90));
        jButtonEstatisticas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEstatisticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEstatisticasMouseClicked(evt);
            }
        });
        jButtonEstatisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEstatisticasActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonEstatisticas);

        jButton9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsDicas_40px.png"))); // NOI18N
        jButton9.setText("Dicas");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setMaximumSize(new java.awt.Dimension(70, 90));
        jButton9.setMinimumSize(new java.awt.Dimension(70, 90));
        jButton9.setPreferredSize(new java.awt.Dimension(70, 90));
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.addTab("Manutenção", jPanel1);

        jToolBar4.setRollover(true);

        jButtonControleEstoque.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButtonControleEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsProduto_40px.png"))); // NOI18N
        jButtonControleEstoque.setText("Controle de Estoque");
        jButtonControleEstoque.setToolTipText("");
        jButtonControleEstoque.setFocusable(false);
        jButtonControleEstoque.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonControleEstoque.setMaximumSize(new java.awt.Dimension(115, 90));
        jButtonControleEstoque.setMinimumSize(new java.awt.Dimension(115, 90));
        jButtonControleEstoque.setPreferredSize(new java.awt.Dimension(115, 90));
        jButtonControleEstoque.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonControleEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonControleEstoqueActionPerformed(evt);
            }
        });
        jToolBar4.add(jButtonControleEstoque);

        jButton35.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsFuncionario_40px.png"))); // NOI18N
        jButton35.setText("Cadastro Vendedor");
        jButton35.setFocusable(false);
        jButton35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton35.setMaximumSize(new java.awt.Dimension(115, 90));
        jButton35.setMinimumSize(new java.awt.Dimension(115, 90));
        jButton35.setPreferredSize(new java.awt.Dimension(115, 90));
        jButton35.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton35);

        jButton33.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsFornecedor_40px_1.png"))); // NOI18N
        jButton33.setText("Cadastro Fornecedor");
        jButton33.setFocusable(false);
        jButton33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton33.setMaximumSize(new java.awt.Dimension(115, 90));
        jButton33.setMinimumSize(new java.awt.Dimension(115, 90));
        jButton33.setPreferredSize(new java.awt.Dimension(115, 90));
        jButton33.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton33);

        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconCadastroCliente_40px.png"))); // NOI18N
        jButton34.setText("Cadastro Cliente");
        jButton34.setFocusable(false);
        jButton34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton34.setMaximumSize(new java.awt.Dimension(115, 90));
        jButton34.setMinimumSize(new java.awt.Dimension(115, 90));
        jButton34.setPreferredSize(new java.awt.Dimension(115, 90));
        jButton34.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton34);

        jTabbedPane1.addTab("Cadastros e outras Operações", jToolBar4);

        jToolBar6.setRollover(true);

        jButton53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsLeituraX_40px.png"))); // NOI18N
        jButton53.setText("Leitura X");
        jButton53.setFocusable(false);
        jButton53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton53.setMaximumSize(new java.awt.Dimension(95, 90));
        jButton53.setMinimumSize(new java.awt.Dimension(95, 90));
        jButton53.setPreferredSize(new java.awt.Dimension(95, 90));
        jButton53.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton53);

        jButton54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsReducaoZ_40px.png"))); // NOI18N
        jButton54.setText("Redução Z");
        jButton54.setFocusable(false);
        jButton54.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton54.setMaximumSize(new java.awt.Dimension(95, 90));
        jButton54.setMinimumSize(new java.awt.Dimension(95, 90));
        jButton54.setPreferredSize(new java.awt.Dimension(95, 90));
        jButton54.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton54);

        jButton52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsFluxoCaixa_40px.png"))); // NOI18N
        jButton52.setText("Transferência");
        jButton52.setFocusable(false);
        jButton52.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton52.setMaximumSize(new java.awt.Dimension(95, 90));
        jButton52.setMinimumSize(new java.awt.Dimension(95, 90));
        jButton52.setPreferredSize(new java.awt.Dimension(95, 90));
        jButton52.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton52);

        jButton51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsCaixaAutomatico_40px.png"))); // NOI18N
        jButton51.setText("Fechamento Caixa");
        jButton51.setFocusable(false);
        jButton51.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton51.setMaximumSize(new java.awt.Dimension(95, 90));
        jButton51.setMinimumSize(new java.awt.Dimension(95, 90));
        jButton51.setPreferredSize(new java.awt.Dimension(95, 90));
        jButton51.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton51);

        jTabbedPane1.addTab("Fiscal", jToolBar6);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("BALLOON");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N

        javax.swing.GroupLayout jPanelRaizLayout = new javax.swing.GroupLayout(jPanelRaiz);
        jPanelRaiz.setLayout(jPanelRaizLayout);
        jPanelRaizLayout.setHorizontalGroup(
            jPanelRaizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRaizLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
        );
        jPanelRaizLayout.setVerticalGroup(
            jPanelRaizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRaizLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
                .addGroup(jPanelRaizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRaizLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelRaiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelRaiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        JanelaFechamentoCaixaNova exibeVendas = new JanelaFechamentoCaixaNova(this,true);
        exibeVendas.setVisible(true);

    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        Bematech.ReducaoZ("", "");
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        Bematech.LeituraX();
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        JanelaRealizarTransferenciaNova transferencia = new JanelaRealizarTransferenciaNova(this,true);
        transferencia.setVisible(true);

    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        JanelaCadastroClienteNova cliente = new JanelaCadastroClienteNova(this,true);
        cliente.setVisible(true);

    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        JanelaCadastroFornecedorNova fornecedor = new JanelaCadastroFornecedorNova(this,true);
        fornecedor.setVisible(true);

    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed

        JanelaCadastroVendedorNova vendedor = new JanelaCadastroVendedorNova(this,true);
        vendedor.setVisible(true);

    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButtonControleEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonControleEstoqueActionPerformed
        JanelaManutencaoEstoqueNova manutencaoEstoque = new JanelaManutencaoEstoqueNova(this,true);
        manutencaoEstoque.setVisible(true);
    }//GEN-LAST:event_jButtonControleEstoqueActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        JanelaDicasNova dica = new JanelaDicasNova(this,true);
        dica.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButtonEstatisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEstatisticasActionPerformed
        JanelaEstatisticasNova estatistica = new JanelaEstatisticasNova(this,true);
        estatistica.dispose();

        estatistica.setVisible(true);
    }//GEN-LAST:event_jButtonEstatisticasActionPerformed

    private void jButtonEstatisticasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEstatisticasMouseClicked

    }//GEN-LAST:event_jButtonEstatisticasMouseClicked

    private void jButtonVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVendasActionPerformed
        JanelaRelatorioNova relatorio = new JanelaRelatorioNova(this,true);
        relatorio.dispose();
        relatorio.setVisible(true);

    }//GEN-LAST:event_jButtonVendasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JanelaDespesasNovas despesas = new JanelaDespesasNovas(this,true);
        despesas.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVendaActionPerformed
        JanelaRealizaVendaNova realizarVenda = new JanelaRealizaVendaNova(this,true);
        realizarVenda.setVisible(true);
        // if(!(realizarVenda.isVisible())){
            //        realizarVenda = new JanelaRealizaVenda();
            //       realizarVenda.setVisible(true);
            //  }
    }//GEN-LAST:event_jButtonVendaActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonControleEstoque;
    private javax.swing.JButton jButtonEstatisticas;
    private javax.swing.JButton jButtonVenda;
    private javax.swing.JButton jButtonVendas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelRaiz;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar6;
    // End of variables declaration//GEN-END:variables
}
