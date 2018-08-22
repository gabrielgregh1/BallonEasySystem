/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Despesas;
import model.bean.Produto_vendido;
import model.bean.Venda;
import model.dao.DespesasDAO;
import model.dao.VendaDAO;
import model.dao.produto_vendidoDAO;

/**
 *
 * @author gabri
 */
public class JanelaRelatorioNova extends javax.swing.JDialog {

    private final JFXPanel jfxPanel = new JFXPanel();
    
    private ObservableList<PieChart.Data> data2d = FXCollections.observableArrayList();
   
    private PieChart pieChart = new PieChart();
    
    public JanelaRelatorioNova(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         
        setLocationRelativeTo(null);    
        createScene();

        condicaoInicial();
        
    }

    private void condicaoInicial(){
        jPanelGraficoGastos.setLayout(new BorderLayout());
        jPanelGraficoGastos.add(jfxPanel,BorderLayout.CENTER);
        jLabelDinheiroOrcamento1.setForeground(java.awt.Color.GREEN);
        jLabelMesOrcamento1.setForeground(java.awt.Color.GREEN);
        jLabelDinheiroEntrada.setForeground(java.awt.Color.GREEN);
        jLabelDinheiroSaida.setForeground(java.awt.Color.red);
        jLabelMesOrcamento.setForeground(java.awt.Color.GREEN);
        jLabelDinheiroOrcamento.setForeground(java.awt.Color.GREEN);
        jLabelDinheiroEntrada1.setForeground(java.awt.Color.GREEN);
        jLabelDinheiroSaida1.setForeground(java.awt.Color.red);
        jLabelMesEntrada1.setForeground(java.awt.Color.GREEN);
        jLabelMesSaida1.setForeground(java.awt.Color.red);
        jLabelMesEntrada.setForeground(java.awt.Color.GREEN);
        jLabelMesSaida.setForeground(java.awt.Color.red);
        
        DefaultTableModel model = (DefaultTableModel) jTableMovimentos.getModel();
        jTableMovimentos.setRowSorter(new TableRowSorter(model));
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void atalho(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_F12)
            this.dispose();
    
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

    private void createScene(){
        Platform.runLater(() -> {
            pieChart.setTitle("Estatísticas");
            pieChart.setLegendSide(Side.LEFT);
            pieChart.setData(getChartData());
            pieChart.setPrefSize(750, 400);
            pieChart.setMaxSize(750, 400);
            pieChart.setMinSize(750, 400);

            updateColors();
            jfxPanel.setScene(new Scene(pieChart));
        });
    }
    
    private ObservableList<PieChart.Data> getChartData(){

        return data2d;
    }
    
    private void updateColors(){
        Color[] colors = {Color.web("#04B404"), Color.web("F40404"),
                          Color.web("#31B404"), Color.web("#088A29"),
                          Color.web("#088A29"), Color.web("#8904B1") };
        int i = 0;
        
        for(PieChart.Data data : data2d){
            String hex = String.format("#%02X%02X%02X",
                        (int) (colors[i].getRed() * 225),
                        (int) (colors[i].getGreen() * 225),        
                        (int) (colors[i].getBlue() * 225) );
            data.getNode().setStyle("-fx-pie-color: "+hex+";");
            i++;        
            
        }
    
        Set<Node> items; items = pieChart.lookupAll("Label.chart-legend-item");
        i = 0;
        for(Node item: items){
            Label label = (Label) item;
            final Rectangle rectangle = new Rectangle(5, 5, colors[i]);
            label.setGraphic(rectangle);
            i++;
        }
    }
   
    private void setChartData(String resumida, double percentual){
        Platform.runLater(() -> {
           // data2d.clear();
            data2d.add(new PieChart.Data(resumida, percentual));
            updateColors();        

        });   
    }
    
    private void preAlteracao(){
        jLabelDinheiroEntrada.setText("00,00");
        jLabelDinheiroSaida.setText("00,00");
        jLabelDinheiroOrcamento.setText("00,00");
            
        jLabelDinheiroEntrada1.setText("00,00");
        jLabelDinheiroSaida1.setText("00,00");
        jLabelDinheiroOrcamento1.setText("00,00");
        jPanelGraficoGastos.setVisible(false);
    }
    
    private void alteraGraficoGastos(){
        preAlteracao();
        if(jComboBoxMes.getSelectedIndex() != 0){
            jLabelMesOrcamento1.setVisible(true);
            jLabelDinheiroOrcamento1.setVisible(true);
            geral();
          //  jLabelMes11.setText(String.valueOf(jComboBoxMes.getSelectedItem()));
            String mes = String.valueOf(jComboBoxMes.getSelectedIndex());
            if(mes.length() < 2)
               mes = "0"+mes;
            
            produto_vendidoDAO daoVendido = new produto_vendidoDAO();
           
            int data = 0; 
            double percentualLiquido = 0;
            boolean mesTemDados = false;
          //  double ganhoLiquido = 0;
            double valorVenda = 0;
            
            for(Produto_vendido p: daoVendido.readDespesas(mes)){
                data = Integer.parseInt(p.getVenda().getDataVenda().substring(5, 7));
                if(data ==  jComboBoxMes.getSelectedIndex()){
                    valorVenda += p.getVenda().getTotal();
                    mesTemDados = true;
                    jPanelGraficoGastos.setVisible(true);
                }
            }

            if(mesTemDados){
                Platform.runLater(() -> {
                data2d.clear();
                });
            }else{
                return;
            }
            
            double despesasGerais = 0;
            DespesasDAO despDAO = new DespesasDAO();
            for(Despesas desp : despDAO.read(mes)){
                data = Integer.parseInt(desp.getData().substring(5, 7));
   
                List<PieChart>listaValores = new ArrayList(); 
                
                if(data ==  jComboBoxMes.getSelectedIndex()){                          
                    despesasGerais += desp.getValor();

                }
            }
            double total = valorVenda + despesasGerais;
            double porcentagemLiquida = (valorVenda * 100)/total;
            double porcentagemDespesa = (despesasGerais * 100)/total;
           
            setChartData("Ganhos", porcentagemLiquida);
            setChartData("Gastos", porcentagemDespesa);
            
           // jLabelLucroLiquido1.setText(String.valueOf(ganhoLiquido));
           // jLabelGastosTotais1.setText(String.valueOf(despesasGerais));
            
            double saldo = valorVenda - despesasGerais;
            
            jLabelDinheiroEntrada.setText(tamanhoString(valorVenda));
            jLabelDinheiroSaida.setText(tamanhoString(despesasGerais));
            jLabelDinheiroOrcamento.setText(tamanhoString(saldo));
            
            jLabelDinheiroEntrada1.setText(tamanhoString(valorVenda));
            jLabelDinheiroSaida1.setText(tamanhoString(despesasGerais));
            jLabelDinheiroOrcamento1.setText(tamanhoString(saldo));
            
            
            if(saldo < 0){
                
                jLabelDinheiroOrcamento.setForeground(java.awt.Color.red);
                jLabelDinheiroOrcamento1.setForeground(java.awt.Color.red);
                jLabelMesOrcamento1.setForeground(java.awt.Color.red);
                jLabelMesOrcamento.setForeground(java.awt.Color.red);

               
            }else{
                
                jLabelDinheiroOrcamento.setForeground(java.awt.Color.GREEN);
                jLabelDinheiroOrcamento1.setForeground(java.awt.Color.GREEN);
                jLabelMesOrcamento1.setForeground(java.awt.Color.GREEN);
                jLabelMesOrcamento.setForeground(java.awt.Color.GREEN);
              
            }

        }
        //}catch(){}
    }
    
    private void geral(){
        DefaultTableModel model = (DefaultTableModel) jTableMovimentos.getModel();
        
        DefaultListModel listModelSaida = new DefaultListModel();
        DefaultListModel listModelEntrada = new DefaultListModel();

        String mes = String.valueOf(jComboBoxMes.getSelectedIndex());
        
        while (jTableMovimentos.getModel().getRowCount() > 0)
            model.removeRow(0);
        
        double somaGanhos = 0;
        double somaGastos = 0;
        
        if(mes.length() < 2)
            mes = "0"+mes;
      
        VendaDAO venDAO = new VendaDAO();
        for(Venda v : venDAO.readMes(mes)){
            model.addRow(new Object[]{
                v.getDataVenda(),
                "VENDA",
                tamanhoString(v.getTotal()),
                " ",
                
            });
            somaGanhos += v.getTotal();
        }
        
        DespesasDAO despDAO = new DespesasDAO();
        listModelSaida.addElement("GASTOS EM DINHEIRO");
        for(Despesas d : despDAO.read(mes)){
            model.addRow(new Object[]{
                d.getData().toUpperCase(),
                d.getTipo().toUpperCase(),
                " ",
                tamanhoString(d.getValor()),
            });
            somaGastos += d.getValor();
           // jListSaida.setT
            String listaValor =  d.getTipo()+" -> R$"+tamanhoString(d.getValor());
            listModelSaida.addElement(listaValor.toUpperCase());
        }
        model.addRow(new Object[]{
                "ATUAL",
                "RESUMO",
                tamanhoString(somaGanhos),
                tamanhoString(somaGastos),
            });
        
        jListSaida.setModel(listModelSaida);
        
        produto_vendidoDAO prodDAO = new produto_vendidoDAO();
        listModelEntrada.addElement("VENDAS EM QUANTIDADE");
        for(Produto_vendido p : prodDAO.read(mes)){

            String listaValor =  p.getProduto().getResumida()+" -> "+tamanhoString(p.getQtdProdutoVendido());
            
            listModelEntrada.addElement(listaValor.toUpperCase());
    
        }
        jListEntradas.setModel(listModelEntrada);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableMovimentos = new javax.swing.JTable();
        jListSaida = new javax.swing.JList<>();
        jListEntradas = new javax.swing.JList<>();
        jLabelMesSaida = new javax.swing.JLabel();
        jLabelDinheiroSaida = new javax.swing.JLabel();
        jLabelDinheiroOrcamento = new javax.swing.JLabel();
        jLabelMesOrcamento = new javax.swing.JLabel();
        jLabelDinheiroEntrada = new javax.swing.JLabel();
        jLabelMesEntrada = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanelGraficoGastos = new javax.swing.JPanel();
        jLabelMesOrcamento1 = new javax.swing.JLabel();
        jLabelDinheiroOrcamento1 = new javax.swing.JLabel();
        jLabelMesSaida1 = new javax.swing.JLabel();
        jLabelDinheiroSaida1 = new javax.swing.JLabel();
        jLabelDinheiroEntrada1 = new javax.swing.JLabel();
        jLabelMesEntrada1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelF1 = new javax.swing.JLabel();
        jLabelLogoBalloon = new javax.swing.JLabel();
        jLabelBalloon = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel8.setText("MOVIMENTO DO CAIXA");

        jComboBoxMes.setFont(new java.awt.Font("Trebuchet MS", 2, 12)); // NOI18N
        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE O MÊS", "JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO" }));
        jComboBoxMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel38.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 255));
        jLabel38.setText("Relatório Completo");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsContabilidade_80px.png"))); // NOI18N

        jTabbedPane1.setFont(new java.awt.Font("Trebuchet MS", 2, 12)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTableMovimentos.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jTableMovimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Descrição", "Entrada", "Saida"
            }
        ));
        jScrollPane3.setViewportView(jTableMovimentos);

        jListSaida.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Saídas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 3, 14), new java.awt.Color(255, 0, 0))); // NOI18N
        jListSaida.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jListSaida.setForeground(new java.awt.Color(255, 0, 0));

        jListEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entradas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 3, 14), new java.awt.Color(0, 204, 0))); // NOI18N
        jListEntradas.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jListEntradas.setForeground(new java.awt.Color(0, 204, 0));
        jListEntradas.setToolTipText("");

        jLabelMesSaida.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelMesSaida.setText("Total Saida:");

        jLabelDinheiroSaida.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelDinheiroSaida.setText("R$ 00,00");

        jLabelDinheiroOrcamento.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelDinheiroOrcamento.setText("R$ 00,00");

        jLabelMesOrcamento.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelMesOrcamento.setText("Orçamento do Mês");

        jLabelDinheiroEntrada.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelDinheiroEntrada.setText("R$ 00,00");

        jLabelMesEntrada.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelMesEntrada.setText("Total Entrada:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jListEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jListSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelMesOrcamento)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelDinheiroOrcamento))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMesSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelMesEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDinheiroSaida, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDinheiroEntrada, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(96, 96, 96))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jListEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jListSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMesEntrada)
                            .addComponent(jLabelDinheiroEntrada))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDinheiroSaida)
                            .addComponent(jLabelMesSaida))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMesOrcamento)
                            .addComponent(jLabelDinheiroOrcamento)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Relatório Númerico", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelGraficoGastosLayout = new javax.swing.GroupLayout(jPanelGraficoGastos);
        jPanelGraficoGastos.setLayout(jPanelGraficoGastosLayout);
        jPanelGraficoGastosLayout.setHorizontalGroup(
            jPanelGraficoGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );
        jPanelGraficoGastosLayout.setVerticalGroup(
            jPanelGraficoGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        jLabelMesOrcamento1.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelMesOrcamento1.setText("Orçamento do Mês");

        jLabelDinheiroOrcamento1.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelDinheiroOrcamento1.setText("R$ 00,00");

        jLabelMesSaida1.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelMesSaida1.setText("Total Saida:");

        jLabelDinheiroSaida1.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelDinheiroSaida1.setText("R$ 00,00");

        jLabelDinheiroEntrada1.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelDinheiroEntrada1.setText("R$ 00,00");

        jLabelMesEntrada1.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelMesEntrada1.setText("Total Entrada:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMesSaida1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMesEntrada1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDinheiroSaida1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDinheiroEntrada1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabelMesOrcamento1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDinheiroOrcamento1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addComponent(jPanelGraficoGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelGraficoGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMesEntrada1)
                            .addComponent(jLabelDinheiroEntrada1))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDinheiroSaida1)
                            .addComponent(jLabelMesSaida1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMesOrcamento1)
                            .addComponent(jLabelDinheiroOrcamento1))))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Relatório Gráfico", jPanel6);

        jPanel5.setBackground(new java.awt.Color(0, 153, 255));

        jLabelF1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF1.setText("F1 - Historico de Manutenção");

        jLabelLogoBalloon.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelLogoBalloon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N
        jLabelLogoBalloon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogoBalloonMouseClicked(evt);
            }
        });

        jLabelBalloon.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelBalloon.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBalloon.setText("BALLOON");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelF1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelBalloon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLogoBalloon)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogoBalloon, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(70, 70, 70))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelBalloon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(56, 56, 56))))
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/closeWindow_30 (2).png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMesItemStateChanged
        alteraGraficoGastos();
    }//GEN-LAST:event_jComboBoxMesItemStateChanged

    private void jLabelLogoBalloonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoBalloonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLogoBalloonMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jLabel9MouseClicked

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
            java.util.logging.Logger.getLogger(JanelaRelatorioNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaRelatorioNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaRelatorioNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaRelatorioNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaRelatorioNova dialog = new JanelaRelatorioNova(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBoxMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBalloon;
    private javax.swing.JLabel jLabelDinheiroEntrada;
    private javax.swing.JLabel jLabelDinheiroEntrada1;
    private javax.swing.JLabel jLabelDinheiroOrcamento;
    private javax.swing.JLabel jLabelDinheiroOrcamento1;
    private javax.swing.JLabel jLabelDinheiroSaida;
    private javax.swing.JLabel jLabelDinheiroSaida1;
    private javax.swing.JLabel jLabelF1;
    private javax.swing.JLabel jLabelLogoBalloon;
    private javax.swing.JLabel jLabelMesEntrada;
    private javax.swing.JLabel jLabelMesEntrada1;
    private javax.swing.JLabel jLabelMesOrcamento;
    private javax.swing.JLabel jLabelMesOrcamento1;
    private javax.swing.JLabel jLabelMesSaida;
    private javax.swing.JLabel jLabelMesSaida1;
    private javax.swing.JList<String> jListEntradas;
    private javax.swing.JList<String> jListSaida;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelGraficoGastos;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableMovimentos;
    // End of variables declaration//GEN-END:variables
}
