/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bemajava.Bematech;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import model.bean.CaixaInterno;
import model.bean.CaixaPadrao;
import model.dao.CaixaInternoDAO;
import model.dao.CaixaPadraoDAO;

/**
 *
 * @author gabri
 */
public class JanelaFechamentoCaixaNova extends javax.swing.JDialog {

    /**
     * Creates new form JanelaFechamentoCaixaNova
     */
    public JanelaFechamentoCaixaNova(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ExibirFechamento();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       // setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }

    private String dataInterna;
    private String historicoInterna;
    private double totalCreditoInterna;
    private double totalDebitoInterna;
    private double saldoInterna;

    private void atalho(KeyEvent evt){
    
        if(evt.getKeyCode() == KeyEvent.VK_F2){  
            fecharCaixa();
            Bematech.FechamentoDoDia();
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){  
            this.dispose();
        }
            
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
        if(id!= -1)
            campoReal = campoReal.substring(0, id)+"."+campoReal.substring(id+1);
        
        double campoDouble = Double.parseDouble(campoReal);
        return campoDouble;
    
    }
    
    private String transformaReais(Double campo){
       String campoReal = String.valueOf(campo);
        
        int id = campoReal.indexOf(".");
      
        campoReal = campoReal.substring(0, id)+","+campoReal.substring(id+1);
        
        if( id+2 < campoReal.length())
            campoReal = campoReal.substring(0, id+3);
        
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
    
    private String tamanhoString(double valor){
        String valorString = transformaReais(valor);
        int id = valorString.indexOf(",");
        if(valorString.length()  == id+2)
            valorString += "0";
        return valorString;
    }
    
    private void ExibirFechamento(){
        
        CaixaPadraoDAO cpDAO = new CaixaPadraoDAO();
        CaixaInternoDAO ciDAO = new CaixaInternoDAO();
        
        String dataPadrao = "0000-00-00";
        String dataCaixa = "0000-00-00";
        double totalCredito = 0;
        double totalDebito = 0;
        int numeroInterno = 0;
        int numeroPadrao = 0;
        DefaultTableModel model = (DefaultTableModel) jTableFluxo.getModel();
        //OBTER DATA ATUAL PELO JAVA
        Date dataAtual = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
        formatador.format( dataAtual );
        
        for(CaixaPadrao c : cpDAO.read()){
            if(numeroPadrao < c.getNumero()){
                numeroPadrao = c.getNumero();
                dataPadrao = c.getDataReferencia();
            }
        }    

        for(CaixaInterno ci: ciDAO.read()){
            if(numeroInterno < ci.getNumero()){
                dataCaixa = ci.getDataReferencia();
                numeroInterno = ci.getNumero();
            }
        }
        if((!dataPadrao.equals(dataCaixa))){
            
            for(CaixaPadrao c : cpDAO.read()){
                if(dataPadrao.equals(c.getDataReferencia())){
                    
                    model.addRow(new Object[]{
                        c.getNumero(),
                        c.getDataReferencia(),
                        c.getHistorico(),
                        tamanhoString(c.getVlrCredito()),
                        tamanhoString(c.getVlrDebito()),
                        tamanhoString(c.getVlrSaldo()),
                    });
                    totalCredito += c.getVlrCredito();
                    totalDebito += c.getVlrDebito();
                }
            }  
        
        }
        //MODIFICA VALOR NAS JLABELS
        
        jLabelUltimoFechamento.setText(dataCaixa);
        jLabelAtualFechamento.setText(dataPadrao);
        jLabelCredito.setText(tamanhoString(totalCredito));
        jLabelDebito.setText(tamanhoString(totalDebito));
        jLabelCaixa.setText(tamanhoString(totalCredito - totalDebito));
        
//FAZ A ULTIMA LINHA DA JTABLE
        dataInterna = dataPadrao;
        historicoInterna = "Resumo dia "+dataPadrao;
        totalCreditoInterna = totalCredito;
        totalDebitoInterna = totalDebito; 
        
        model.addRow(new Object[]{
                " ",
                dataInterna,
                historicoInterna,   
                tamanhoString(totalCredito),
                tamanhoString(totalDebito),
                tamanhoString(saldoInterna = totalCredito - totalDebito)
                
            });
        /*
        DefaultTableCellRenderer corVermalha = new DefaultTableCellRenderer() {
            public void setValue(Object value) {
                setForeground(Color.RED);
                super.setValue(value);
            }
        };
        DefaultTableCellRenderer corAzul = new DefaultTableCellRenderer() {
            public void setValue(Object value) {
                setForeground(Color.BLUE);
                super.setValue(value);
            }
        };
 
    jTableFluxo.getColumnModel().getColumn(4).setCellRenderer(corVermalha);
    jTableFluxo.getColumnModel().getColumn(5).setCellRenderer(corVermalha);
    jTableFluxo.getco
*/

    }
    
    private void fecharCaixa(){
        
        CaixaInterno ci = new CaixaInterno(dataInterna, historicoInterna, totalCreditoInterna, totalDebitoInterna, saldoInterna);
        CaixaInternoDAO ciDAO = new CaixaInternoDAO();
        ciDAO.create(ci);
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFluxo = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelCredito = new javax.swing.JLabel();
        jLabelDebito = new javax.swing.JLabel();
        jLabelCaixa = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelUltimoFechamento = new javax.swing.JLabel();
        jLabelAtualFechamento = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jLabel38.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 255));
        jLabel38.setText("Fechamento de Caixa");

        jLabel39.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 51, 255));
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsCaixaAutomatico_80px.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/closeWindow_30 (2).png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jTableFluxo.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jTableFluxo.setForeground(new java.awt.Color(51, 51, 51));
        jTableFluxo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num.", "Data Registrada", "Histórico", "Vlr.Crédito", "Vlr.Débito", "Vlr. Saldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableFluxo);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fluxo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("( + ) Crédito");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("( - )  Débito");

        jLabelCredito.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelCredito.setForeground(new java.awt.Color(0, 153, 255));
        jLabelCredito.setText("00.00");

        jLabelDebito.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelDebito.setForeground(new java.awt.Color(255, 0, 0));
        jLabelDebito.setText("00.00");

        jLabelCaixa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelCaixa.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCaixa.setText("00,00");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Caixa Padrão");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelCredito, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabelCaixa))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelCredito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelDebito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabelCaixa)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 255));
        jLabel9.setText("Ultimo Fechamento");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Data Fechamento");

        jLabelUltimoFechamento.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelUltimoFechamento.setForeground(new java.awt.Color(0, 153, 255));
        jLabelUltimoFechamento.setText("00-00-0000");

        jLabelAtualFechamento.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAtualFechamento.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAtualFechamento.setText("00-00-0000");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAtualFechamento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelUltimoFechamento, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelUltimoFechamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabelAtualFechamento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Processar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(83, 83, 83)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(59, 59, 59)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BALLOON");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("F2 - Fechar Venda");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Esc- Fechar Janela");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fecharCaixa();
        Bematech.FechamentoDoDia();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

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
            java.util.logging.Logger.getLogger(JanelaFechamentoCaixaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaFechamentoCaixaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaFechamentoCaixaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaFechamentoCaixaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaFechamentoCaixaNova dialog = new JanelaFechamentoCaixaNova(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAtualFechamento;
    private javax.swing.JLabel jLabelCaixa;
    private javax.swing.JLabel jLabelCredito;
    private javax.swing.JLabel jLabelDebito;
    private javax.swing.JLabel jLabelUltimoFechamento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFluxo;
    // End of variables declaration//GEN-END:variables
}
