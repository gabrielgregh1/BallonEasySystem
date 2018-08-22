/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Despesas;
import model.dao.DespesasDAO;

/**
 *
 * @author gabri
 */
public class JanelaDespesasNovas extends javax.swing.JDialog {

    /**
     * Creates new form JanelaDespesasNovas
     */
    public JanelaDespesasNovas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        condicoesIniciais();
//        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
    }

    private double transformaDouble(String campo){
       String campoReal = campo;
        int achou ;
        do{
            achou = campoReal.indexOf(".");
            if(achou != -1)
                campoReal = campoReal.substring(0, achou)+campoReal.substring(achou+1);
            else
                break;
        }while(true);
        
        int id = campoReal.indexOf(",");
        campoReal = campoReal.substring(0, id)+"."+campoReal.substring(id+1);
        
        double campoDouble = Double.parseDouble(campoReal);
        return campoDouble;
    
    }
    
    private String transformaReais(Double campo){
       String campoReal = String.valueOf(campo);
        
        int id = campoReal.indexOf(".");
      
        campoReal = campoReal.substring(0, id)+","+campoReal.substring(id+1);
        
        int colocaPonto = id - 1;
        int ponto = 2;
        while(colocaPonto > 0){
            if(colocaPonto - 2 > 0){
                campoReal = campoReal.substring(0, colocaPonto - ponto)+"."+campoReal.substring(colocaPonto - ponto);
               
            }else
                break;
           
            colocaPonto -= 3;  
        }
        
        return campoReal;
    
    }
    
    private void atalhos(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_F12)
            this.dispose();
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            ConfirmarDespesas();
    
    }
    
    private void condicoesIniciais(){
    //OBTER DATA ATUAL PELO JAVA
        Date dataAtual = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
        jLabelDia.setText(formatador.format( dataAtual ));
        
    //CONFIGURAR JTABLE
        DespesasDAO dao = new DespesasDAO();
        DefaultTableModel model = (DefaultTableModel) jTableDespesas.getModel();
        while (jTableDespesas.getModel().getRowCount() > 0)
            model.removeRow(0);
        
        double total = 0;
        //CAPTURA O MES INFORMADO PELO JAVA QUEBRANDO POR SUBSTRING
        String mes = jLabelDia.getText();
        mes = mes.substring(5, 7);

                
        for(Despesas d : dao.read(mes)){    
            String valorString = transformaReais(d.getValor());
            int id = valorString.indexOf(",");
            if(valorString.length()  == id+2)
                valorString += "0";

            
            model.addRow(new Object[]{
                d.getData(),
                d.getTipo(),
                d.getObservacao(),
                valorString,
            });
            total += d.getValor();
        }
        jLabelTotal.setText(String.valueOf(total));
    
    }
    private void limparCampos(){
        jTextFieldTipo.setText("");
        jTextFieldObservacao.setText("");
        jFormattedTextFieldDespesa.setText(""); 
    }
    
    private void ConfirmarDespesas(){
        try{
            DespesasDAO dao = new DespesasDAO();
            String tipo = jTextFieldTipo.getText();
            String obs = jTextFieldObservacao.getText();
            double valor = transformaDouble(jFormattedTextFieldDespesa.getText());
            
            Despesas des = new Despesas(tipo, obs, valor);
            dao.create(des);
            
            condicoesIniciais();
            limparCampos();
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informe o valor da Despesa");
            jFormattedTextFieldDespesa.setText("");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelEsstatisticas = new javax.swing.JLabel();
        jLabeIcon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelDia = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTipo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDespesas = new javax.swing.JTable();
        jTextFieldObservacao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonConfimar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jFormattedTextFieldDespesa = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabelBalao = new javax.swing.JLabel();
        jLabelBallom = new javax.swing.JLabel();
        jLabeSair = new javax.swing.JLabel();
        jLabeSair1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabelEsstatisticas.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelEsstatisticas.setForeground(new java.awt.Color(0, 153, 255));
        jLabelEsstatisticas.setText("Despesas Mensais");

        jLabeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsGastos_80px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel3KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("DIA: ");

        jLabelDia.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelDia.setForeground(new java.awt.Color(0, 153, 255));
        jLabelDia.setText("000-00-00");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Típo da Dispesa: ");

        jTextFieldTipo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldTipo.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTipoKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Valor da Dispesa:");

        jTableDespesas.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jTableDespesas.setForeground(new java.awt.Color(51, 51, 51));
        jTableDespesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Tipo da Dispesa", "Observação", "Valor"
            }
        ));
        jScrollPane1.setViewportView(jTableDespesas);

        jTextFieldObservacao.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldObservacao.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldObservacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldObservacaoKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Observação: ");

        jButtonConfimar.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButtonConfimar.setText("Confirmar");
        jButtonConfimar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfimarActionPerformed(evt);
            }
        });
        jButtonConfimar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonConfimarKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 2, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 255));
        jLabel5.setText("Díspesas do Mês");

        jLabelTotal.setFont(new java.awt.Font("Trebuchet MS", 2, 16)); // NOI18N
        jLabelTotal.setForeground(new java.awt.Color(0, 153, 255));
        jLabelTotal.setText("00,00");

        jFormattedTextFieldDespesa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldDespesa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jButtonConfimar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelDia)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTotal)
                        .addGap(36, 36, 36))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelDia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButtonConfimar)
                    .addComponent(jFormattedTextFieldDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        jLabelBalao.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelBalao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N

        jLabelBallom.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelBallom.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBallom.setText("BALLOON");

        jLabeSair.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabeSair.setForeground(new java.awt.Color(255, 255, 255));
        jLabeSair.setText("ENTER - Confirmar");

        jLabeSair1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabeSair1.setForeground(new java.awt.Color(255, 255, 255));
        jLabeSair1.setText("F12 - Sair");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(495, 495, 495)
                .addComponent(jLabeSair)
                .addGap(18, 18, 18)
                .addComponent(jLabeSair1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(356, 356, 356)
                .addComponent(jLabelBallom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBalao, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelBalao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabelBallom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabeSair1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabeSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/closeWindow_30 (2).png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabeIcon)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelEsstatisticas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabelEsstatisticas))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabeIcon))
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTipoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTipoKeyPressed

    private void jTextFieldObservacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldObservacaoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldObservacaoKeyPressed

    private void jButtonConfimarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfimarActionPerformed
        ConfirmarDespesas();

    }//GEN-LAST:event_jButtonConfimarActionPerformed

    private void jButtonConfimarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonConfimarKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jButtonConfimarKeyPressed

    private void jPanel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jPanel3KeyPressed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

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
            java.util.logging.Logger.getLogger(JanelaDespesasNovas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaDespesasNovas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaDespesasNovas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaDespesasNovas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaDespesasNovas dialog = new JanelaDespesasNovas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonConfimar;
    private javax.swing.JFormattedTextField jFormattedTextFieldDespesa;
    private javax.swing.JLabel jLabeIcon;
    private javax.swing.JLabel jLabeSair;
    private javax.swing.JLabel jLabeSair1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelBalao;
    private javax.swing.JLabel jLabelBallom;
    private javax.swing.JLabel jLabelDia;
    private javax.swing.JLabel jLabelEsstatisticas;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDespesas;
    private javax.swing.JTextField jTextFieldObservacao;
    private javax.swing.JTextField jTextFieldTipo;
    // End of variables declaration//GEN-END:variables
}
