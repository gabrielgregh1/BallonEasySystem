/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Vendedor;
import model.dao.VendedorDAO;

/**
 *
 * @author gabri
 */
public class JanelaCadastroVendedorNova extends javax.swing.JDialog {

    /**
     * Creates new form JanelaCadastroVendedorNova
     */
    public JanelaCadastroVendedorNova(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        completarTabela();
        condicaoInicialIns();
        condicaoInicialAlt();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_BOTH); 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
    }

    private void atalhosAlt(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            pesquisaAlteracao();
   
    }
    
    private void atalhosIns(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            realizarCadastro();
   
    }

    private void completarTabela(){
        DefaultTableModel modelIns = (DefaultTableModel) jTableInclusaoFornecedor.getModel();
        DefaultTableModel modelAlt = (DefaultTableModel) jTableAlteracaoFornecedor.getModel();
        
        while (jTableInclusaoFornecedor.getModel().getRowCount() > 0)
            modelIns.removeRow(0);
        
        while (jTableAlteracaoFornecedor.getModel().getRowCount() > 0)
            modelAlt.removeRow(0);
        
        VendedorDAO dao = new VendedorDAO();
        

        for(Vendedor v : dao.read()){
            modelIns.addRow(new Object[]{
                v.getCodigoVendedor(),
                v.getNome(),
            });
        }
        
        for(Vendedor v : dao.read()){
            modelAlt.addRow(new Object[]{
                v.getCodigoVendedor(),
                v.getNome(),
            });
        }
    }
    
    private void condicaoInicialIns(){
        VendedorDAO dao = new VendedorDAO();
        
        int i = 0;
        for(Vendedor v : dao.read()){
            if(i < v.getCodigoVendedor())
                i = v.getCodigoVendedor();
        }
        i++;
        jTextFieldInclusaoCodigo.setText(String.valueOf(i));
        jTextFieldInclusaoCodigo.setEnabled(false);
        jTextFieldInclusaoFornecedor.setText("");
    }
    
    private void realizarCadastro(){
        int codigo = Integer.parseInt(jTextFieldInclusaoCodigo.getText());
        String nome = jTextFieldInclusaoFornecedor.getText();
        
        Vendedor func = new Vendedor(codigo, nome);
        VendedorDAO dao = new VendedorDAO();
        
        dao.create(func);
        
        completarTabela();
        condicaoInicialIns();
    }
    
    private void condicaoInicialAlt(){
        jTextFieldAlteracaoFornecedor.setText("");
        
        jTextFieldAlteracaoCodigo.setEnabled(true);
        jTextFieldAlteracaoFornecedor.setEnabled(false);
    }
    
    private void condicaoPosAlt(){
        jTextFieldAlteracaoCodigo.setEnabled(false);
        jTextFieldAlteracaoFornecedor.setEnabled(true);
    }
    
    private void pesquisaAlteracao(){
        try{
            int codigo = Integer.parseInt(jTextFieldAlteracaoCodigo.getText());
            boolean temVendedor = false;
            VendedorDAO dao = new VendedorDAO();
            for(Vendedor v : dao.read())
                if(v.getCodigoVendedor() == codigo){
                    jTextFieldAlteracaoFornecedor.setText(v.getNome());
                    temVendedor = true;
                }
        
            if(temVendedor)
             condicaoPosAlt();
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(jPanelTopo, "Informe apenas numeros no campo Código");
            jTextFieldAlteracaoCodigo.setText("");
        }
    }
    
    private void realizaAlteracao(){
        int codigo = Integer.parseInt(jTextFieldAlteracaoCodigo.getText());
        String nome = jTextFieldAlteracaoFornecedor.getText();
        
        Vendedor func = new Vendedor(codigo, nome);
        VendedorDAO dao = new VendedorDAO();
        
        dao.update(func);
        completarTabela();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTopo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTabbedPaneRaiz = new javax.swing.JTabbedPane();
        jPanelInsercao = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldInclusaoCodigo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldInclusaoFornecedor = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableInclusaoFornecedor = new javax.swing.JTable();
        jButtonInclusaoCadastrar = new javax.swing.JButton();
        jPanelAlteracao = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldAlteracaoCodigo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldAlteracaoFornecedor = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableAlteracaoFornecedor = new javax.swing.JTable();
        jLabelAlteracaoPesquisa = new javax.swing.JLabel();
        jButtonAlteracaoAlterar = new javax.swing.JButton();
        jPanelRodape = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanelTopo.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanelTopoLayout = new javax.swing.GroupLayout(jPanelTopo);
        jPanelTopo.setLayout(jPanelTopoLayout);
        jPanelTopoLayout.setHorizontalGroup(
            jPanelTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelTopoLayout.setVerticalGroup(
            jPanelTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jLabelTitulo.setFont(new java.awt.Font("Trebuchet MS", 1, 28)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(0, 153, 255));
        jLabelTitulo.setText("Cadastro de Vendedor");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsFuncionario_80px.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel13.setText("Código: ");

        jTextFieldInclusaoCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel14.setText("Fornecedor: ");

        jTextFieldInclusaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTextFieldInclusaoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoFornecedorKeyPressed(evt);
            }
        });

        jTableInclusaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jTableInclusaoFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome Vendedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTableInclusaoFornecedor);

        jButtonInclusaoCadastrar.setText("Cadastrar");
        jButtonInclusaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInclusaoCadastrarActionPerformed(evt);
            }
        });
        jButtonInclusaoCadastrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonInclusaoCadastrarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInsercaoLayout = new javax.swing.GroupLayout(jPanelInsercao);
        jPanelInsercao.setLayout(jPanelInsercaoLayout);
        jPanelInsercaoLayout.setHorizontalGroup(
            jPanelInsercaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInsercaoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldInclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldInclusaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInclusaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelInsercaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanelInsercaoLayout.setVerticalGroup(
            jPanelInsercaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInsercaoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelInsercaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInsercaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jTextFieldInclusaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonInclusaoCadastrar))
                    .addGroup(jPanelInsercaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jTextFieldInclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneRaiz.addTab("INCLUSÃO", jPanelInsercao);

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel11.setText("Código: ");

        jTextFieldAlteracaoCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTextFieldAlteracaoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCodigoKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel12.setText("Fornecedor: ");

        jTextFieldAlteracaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTextFieldAlteracaoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoFornecedorKeyPressed(evt);
            }
        });

        jTableAlteracaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jTableAlteracaoFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome Vendedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTableAlteracaoFornecedor);

        jLabelAlteracaoPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N
        jLabelAlteracaoPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAlteracaoPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoPesquisaMouseClicked(evt);
            }
        });

        jButtonAlteracaoAlterar.setText("Alterar");
        jButtonAlteracaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlteracaoAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAlteracaoLayout = new javax.swing.GroupLayout(jPanelAlteracao);
        jPanelAlteracao.setLayout(jPanelAlteracaoLayout);
        jPanelAlteracaoLayout.setHorizontalGroup(
            jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlteracaoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAlteracaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAlteracaoPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldAlteracaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlteracaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelAlteracaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanelAlteracaoLayout.setVerticalGroup(
            jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlteracaoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlteracaoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jTextFieldAlteracaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAlteracaoAlterar))
                    .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jTextFieldAlteracaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneRaiz.addTab("ALTERAÇÃO", jPanelAlteracao);

        jPanelRodape.setBackground(new java.awt.Color(0, 153, 255));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("BALLOON");

        javax.swing.GroupLayout jPanelRodapeLayout = new javax.swing.GroupLayout(jPanelRodape);
        jPanelRodape.setLayout(jPanelRodapeLayout);
        jPanelRodapeLayout.setHorizontalGroup(
            jPanelRodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRodapeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(0, 0, 0))
        );
        jPanelRodapeLayout.setVerticalGroup(
            jPanelRodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRodapeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelRodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/closeWindow_30 (2).png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTopo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanelRodape, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneRaiz, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitulo)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelTopo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaneRaiz, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelRodape, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldInclusaoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoFornecedorKeyPressed
        atalhosIns(evt);
    }//GEN-LAST:event_jTextFieldInclusaoFornecedorKeyPressed

    private void jButtonInclusaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInclusaoCadastrarActionPerformed
        realizarCadastro();
    }//GEN-LAST:event_jButtonInclusaoCadastrarActionPerformed

    private void jButtonInclusaoCadastrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonInclusaoCadastrarKeyPressed
        atalhosIns(evt);
    }//GEN-LAST:event_jButtonInclusaoCadastrarKeyPressed

    private void jTextFieldAlteracaoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCodigoKeyPressed
        atalhosAlt(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCodigoKeyPressed

    private void jTextFieldAlteracaoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoFornecedorKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        realizaAlteracao();
    }//GEN-LAST:event_jTextFieldAlteracaoFornecedorKeyPressed

    private void jLabelAlteracaoPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoPesquisaMouseClicked
        pesquisaAlteracao();
        condicaoInicialAlt();

    }//GEN-LAST:event_jLabelAlteracaoPesquisaMouseClicked

    private void jButtonAlteracaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlteracaoAlterarActionPerformed
        realizaAlteracao();
    }//GEN-LAST:event_jButtonAlteracaoAlterarActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(JanelaCadastroVendedorNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroVendedorNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroVendedorNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroVendedorNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaCadastroVendedorNova dialog = new JanelaCadastroVendedorNova(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlteracaoAlterar;
    private javax.swing.JButton jButtonInclusaoCadastrar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelAlteracaoPesquisa;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelAlteracao;
    private javax.swing.JPanel jPanelInsercao;
    private javax.swing.JPanel jPanelRodape;
    private javax.swing.JPanel jPanelTopo;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPaneRaiz;
    private javax.swing.JTable jTableAlteracaoFornecedor;
    private javax.swing.JTable jTableInclusaoFornecedor;
    private javax.swing.JTextField jTextFieldAlteracaoCodigo;
    private javax.swing.JTextField jTextFieldAlteracaoFornecedor;
    private javax.swing.JTextField jTextFieldInclusaoCodigo;
    private javax.swing.JTextField jTextFieldInclusaoFornecedor;
    // End of variables declaration//GEN-END:variables
}
