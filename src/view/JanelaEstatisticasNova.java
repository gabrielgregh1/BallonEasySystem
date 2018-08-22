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
import javax.swing.JFrame;
import model.bean.Despesas;
import model.bean.Produto_vendido;
import model.dao.DespesasDAO;
import model.dao.produto_vendidoDAO;

/**
 *
 * @author gabri
 */
public class JanelaEstatisticasNova extends javax.swing.JDialog {

    private final JFXPanel jfxPanel = new JFXPanel();
    
    private ObservableList<PieChart.Data> data2d = FXCollections.observableArrayList();
   
    private PieChart pieChart = new PieChart();
    
    public JanelaEstatisticasNova(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

//        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        createScene();
        condicaoInicial();
 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
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
            pieChart.setPrefSize(800, 400);
            pieChart.setMaxSize(800, 400);
            pieChart.setMinSize(800, 400);

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
    
    private void condicaoFinal(){
        jLabel2.setVisible(true);
        jFormattedTextFieldTotal.setVisible(true);
        jLabel1.setVisible(true);
        jLabelMes.setVisible(true);
        jLabel2.setVisible(true);
        jLabel26.setVisible(true);

        jPanelGraficoProdutos.setVisible(true);
    }
    
    private void condicaoInicial(){
        jLabel2.setVisible(false);
        jFormattedTextFieldTotal.setVisible(false);
        jLabel1.setVisible(false);
        jLabelMes.setVisible(false);
        jLabelQ.setVisible(false);
        jLabelQQ.setVisible(false);
        jLabelSS.setVisible(false);
        jLabel26.setVisible(false);
        jLabelP.setVisible(false);
        jLabelS.setVisible(false);
        jLabelT.setVisible(false);
        jLabelPrimeiro.setVisible(false);
        jLabelPrimeiroLucro.setVisible(false);
        jLabelSegundo.setVisible(false);
        jLabelSegundoLucro.setVisible(false);
        jFormattedTextFieldPrimeiro.setVisible(false);
        jFormattedTextFieldSegundo.setVisible(false);
        jFormattedTextFieldTerceiro.setVisible(false);
        jFormattedTextFieldQuarto.setVisible(false);
        jFormattedTextFieldQuinto.setVisible(false);
        jFormattedTextFieldSexto.setVisible(false);
        jFormattedTextFieldPrimeiro.setEditable(false);
        jFormattedTextFieldSegundo.setEditable(false);
        jFormattedTextFieldTerceiro.setEditable(false);
        jFormattedTextFieldQuarto.setEditable(false);
        jFormattedTextFieldQuinto.setEditable(false);
        jFormattedTextFieldSexto.setEditable(false);
        jFormattedTextFieldTotal.setEditable(false);
        jLabelTerceiro.setVisible(false);
        jLabelTerceiroLucro.setVisible(false);
        jLabelQuarto.setVisible(false);
        jLabelQuartoLucro.setVisible(false);
        jLabelQuinto.setVisible(false);
        jLabelQuintoLucro.setVisible(false);
        jLabelSexto.setVisible(false);
        jLabelSextoLucro.setVisible(false);
        
        jPanelGraficoProdutos.setVisible(false);
    }
    
    private void alteraGrafico(){
        jPanelGraficoGastos.setLayout(new BorderLayout());
        jPanelGraficoGastos.removeAll();
        
        jPanelGraficoProdutos.setLayout(new BorderLayout());
        jPanelGraficoProdutos.add(jfxPanel,BorderLayout.CENTER);
        condicaoInicial();
        
        if(jComboBoxMes.getSelectedIndex() != 0){
            jComboBoxMes1.setSelectedIndex(0);
            jLabelMes.setVisible(true);
            jLabelMes.setText(String.valueOf(jComboBoxMes.getSelectedItem()));
            String mes = String.valueOf(jComboBoxMes.getSelectedIndex());
            if(mes.length() < 2)
               mes = "0"+mes;
            
            produto_vendidoDAO daoVendido = new produto_vendidoDAO();
            int data = 0;
            
            double percentualTotal = 0;
            boolean mesTemDados = false;
            
            for(Produto_vendido p: daoVendido.read(mes)){
                data = Integer.parseInt(p.getVenda().getDataVenda().substring(5, 7));
                if(data ==  jComboBoxMes.getSelectedIndex()){
                    percentualTotal = percentualTotal + p.getQtdProdutoVendido();
                    mesTemDados = true;
                }
            }
            
            if(mesTemDados){
                Platform.runLater(() -> {
                data2d.clear();
                });
                condicaoFinal();
            }else{
                condicaoInicial();
                return;
            }
            
            double soma = 0 ;
            int contador = 1;
            int limite = 0;
            
            
            for(Produto_vendido pv : daoVendido.read(mes)){
                data = Integer.parseInt(pv.getVenda().getDataVenda().substring(5, 7));
               
                double percentual;
                List<PieChart>listaValores = new ArrayList(); 
                
                if(data ==  jComboBoxMes.getSelectedIndex()){                          
                    percentual = pv.getQtdProdutoVendido()*100/percentualTotal;
                    
                    setChartData(pv.getProduto().getResumida() , percentual);
                    
                    soma = soma + pv.getVenda().getTotal();
                    
                    String valorString = transformaReais(soma);
                    int id = valorString.indexOf(",");
                    if(valorString.length()  == id+2)
                        valorString += "0";
                    
                    switch(contador){
                        case 1:
                            jLabelPrimeiro.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelPrimeiroLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                        
                            jFormattedTextFieldPrimeiro.setText(transformaReais(pv.getVenda().getTotal()));         
                            
                            jLabelP.setVisible(true);
                            jLabelPrimeiro.setVisible(true);
                            jLabelPrimeiroLucro.setVisible(true);
                            jFormattedTextFieldPrimeiro.setVisible(true);
                            break;
                        case 2:
                            jLabelSegundo.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelSegundoLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                   
                            jFormattedTextFieldSegundo.setText(transformaReais(pv.getVenda().getTotal()));         
                                 
                            jLabelS.setVisible(true); 
                            jLabelSegundo.setVisible(true);
                            jLabelSegundoLucro.setVisible(true);
                            jFormattedTextFieldSegundo.setVisible(true);
                            
                            break;
                        case 3:
                            jLabelTerceiro.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelTerceiroLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                    
                            jFormattedTextFieldTerceiro.setText(transformaReais(pv.getVenda().getTotal()));         
                                  
                            jLabelT.setVisible(true);
                            jLabelTerceiro.setVisible(true);
                            jLabelTerceiroLucro.setVisible(true);
                            jFormattedTextFieldTerceiro.setVisible(true);
                            break;
                        case 4:
                            jLabelQuarto.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelQuartoLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                   
                            jFormattedTextFieldQuarto.setText(transformaReais(pv.getVenda().getTotal()));         
                            
                            jLabelQ.setVisible(true);
                            jLabelQuarto.setVisible(true);
                            jLabelQuartoLucro.setVisible(true);
                            jFormattedTextFieldQuarto.setVisible(true);
                            break;
                        case 5:
                            jLabelQuinto.setText(pv.getProduto().getResumida()+"("+ pv.getQtdProdutoVendido() +")");
                            jLabelQuintoLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                   
                            jFormattedTextFieldQuinto.setText(transformaReais(pv.getVenda().getTotal()));         
                                  
                            jLabelQQ.setVisible(true);
                            jLabelQuinto.setVisible(true);
                            jLabelQuintoLucro.setVisible(true);
                            jFormattedTextFieldQuinto.setVisible(true);
                            break;
                        case 6:
                            jLabelSexto.setText(pv.getProduto().getResumida()+" ("+ pv.getQtdProdutoVendido() +")");
                            jLabelSextoLucro.setText(pv.getProduto().getResumida() +" gerou um lucro bruto de R$");                   
                            jFormattedTextFieldSexto.setText(transformaReais(pv.getVenda().getTotal()));         
                                   
                            jLabelSS.setVisible(true);
                            jLabelSexto.setVisible(true);
                            jLabelSextoLucro.setVisible(true);
                            jFormattedTextFieldSexto.setVisible(true);
                            break;
                        default:
                            break;
                    }
                    contador++;
                    limite++;
                    if(limite >= 6)
                        break;
                }
                
            }
            String valorString = transformaReais(soma);
            int id = valorString.indexOf(",");
            if(valorString.length()  == id+2)
                valorString += "0";
            jFormattedTextFieldTotal.setText(valorString);
            
        }
            
    }
    
    private void alteraGraficoGastos(){
        condicaoInicial();
        jLabel26.setVisible(false);
        jFormattedTextFieldTotal.setVisible(false);
        
        if(jComboBoxMes1.getSelectedIndex() != 0){
            jComboBoxMes.setSelectedIndex(0);
            
            jLabelMes11.setText(String.valueOf(jComboBoxMes1.getSelectedItem()));
            String mes = String.valueOf(jComboBoxMes1.getSelectedIndex());
            if(mes.length() < 2)
               mes = "0"+mes;
            
            produto_vendidoDAO daoVendido = new produto_vendidoDAO();
            int data = 0;
            
            double percentualLiquido = 0;
            boolean mesTemDados = false;
            double ganhoLiquido = 0;
            double valorVenda = 0;
            for(Produto_vendido p: daoVendido.readDespesas(mes)){
                data = Integer.parseInt(p.getVenda().getDataVenda().substring(5, 7));
                
                if(data ==  jComboBoxMes1.getSelectedIndex()){
                    jPanelGraficoProdutos.setLayout(new BorderLayout());
                    jPanelGraficoProdutos.removeAll();
        
                    jPanelGraficoGastos.setLayout(new BorderLayout());
                    jPanelGraficoGastos.add(jfxPanel,BorderLayout.CENTER);
                   
                    ganhoLiquido += p.getProduto().getCustoUnidade() *  p.getQtdProdutoVendido();
                    valorVenda += p.getVenda().getTotal();
                    mesTemDados = true;
                }
            }
            ganhoLiquido = valorVenda - ganhoLiquido;
            

            if(mesTemDados){
                Platform.runLater(() -> {
                data2d.clear();
                });
                condicaoFinal();
            }else{
                condicaoInicial();
                return;
            }
            
            double despesasGerais = 0;
            DespesasDAO despDAO = new DespesasDAO();
            for(Despesas desp : despDAO.read(mes)){
                data = Integer.parseInt(desp.getData().substring(5, 7));
               
                
                List<PieChart>listaValores = new ArrayList(); 
                
                if(data ==  jComboBoxMes1.getSelectedIndex()){                          
                    despesasGerais += desp.getValor();

                }
            }
            
            double total = ganhoLiquido + despesasGerais;
            double porcentagemLiquida = (ganhoLiquido * 100)/total;
            double porcentagemDespesa = (despesasGerais * 100)/total;
           
            setChartData("Ganhos", porcentagemLiquida);
            setChartData("Gastos", porcentagemDespesa);
            
            jLabelLucroLiquido1.setText(transformaReais(ganhoLiquido));
            jLabelGastosTotais1.setText(transformaReais(despesasGerais));
            
            double saldo = ganhoLiquido - despesasGerais;
            
            jLabelSaldoTotal1.setText(transformaReais(saldo));
            
            if(saldo < 0){
                jLabelSaldoTotal1.setForeground(java.awt.Color.red);
                jLabelSaldo1.setForeground(java.awt.Color.red);
            }else{
                jLabelSaldoTotal1.setForeground(java.awt.Color.blue);
                jLabelSaldo1.setForeground(java.awt.Color.blue);
            }  
        }
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelEsstatisticas = new javax.swing.JLabel();
        jLabeIcon = new javax.swing.JLabel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanelExibeGraficoProdutos5 = new javax.swing.JPanel();
        jPanelGraficoProdutos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelPrimeiro = new javax.swing.JLabel();
        jLabelSegundo = new javax.swing.JLabel();
        jLabelTerceiro = new javax.swing.JLabel();
        jLabelP = new javax.swing.JLabel();
        jLabelS = new javax.swing.JLabel();
        jLabelT = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabelMes = new javax.swing.JLabel();
        jLabelPrimeiroLucro = new javax.swing.JLabel();
        jLabelSegundoLucro = new javax.swing.JLabel();
        jLabelTerceiroLucro = new javax.swing.JLabel();
        jLabelSexto = new javax.swing.JLabel();
        jLabelQuinto = new javax.swing.JLabel();
        jLabelQuarto = new javax.swing.JLabel();
        jLabelQ = new javax.swing.JLabel();
        jLabelQQ = new javax.swing.JLabel();
        jLabelSS = new javax.swing.JLabel();
        jLabelSextoLucro = new javax.swing.JLabel();
        jLabelQuintoLucro = new javax.swing.JLabel();
        jLabelQuartoLucro = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButtonOk = new javax.swing.JButton();
        jLabelPrimeiroLucro1 = new javax.swing.JLabel();
        jFormattedTextFieldPrimeiro = new javax.swing.JFormattedTextField();
        jFormattedTextFieldSegundo = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTerceiro = new javax.swing.JFormattedTextField();
        jFormattedTextFieldQuarto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldQuinto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldSexto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTotal = new javax.swing.JFormattedTextField();
        jPanelExibeGraficoProdutos6 = new javax.swing.JPanel();
        jPanelGraficoGastos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxMes1 = new javax.swing.JComboBox<>();
        jLabelMes11 = new javax.swing.JLabel();
        jLabelMes1 = new javax.swing.JLabel();
        jButtonOk1 = new javax.swing.JButton();
        jLabelPrimeiroLucro3 = new javax.swing.JLabel();
        jLabelLucro1 = new javax.swing.JLabel();
        jLabelLucroLiquido1 = new javax.swing.JLabel();
        jLabelGastosTotais1 = new javax.swing.JLabel();
        jLabelGastos1 = new javax.swing.JLabel();
        jLabelSaldo1 = new javax.swing.JLabel();
        jLabelSaldoTotal1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelBalao = new javax.swing.JLabel();
        jLabelBallom = new javax.swing.JLabel();
        jLabeSair = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

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

        jLabelEsstatisticas.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelEsstatisticas.setForeground(new java.awt.Color(0, 153, 255));
        jLabelEsstatisticas.setText("Estátisticas Avançadas");

        jLabeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconEstatisticas_80px.png"))); // NOI18N

        jTabbedPane6.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N

        jPanelExibeGraficoProdutos5.setBackground(new java.awt.Color(255, 255, 255));
        jPanelExibeGraficoProdutos5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanelExibeGraficoProdutos5KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGraficoProdutosLayout = new javax.swing.GroupLayout(jPanelGraficoProdutos);
        jPanelGraficoProdutos.setLayout(jPanelGraficoProdutosLayout);
        jPanelGraficoProdutosLayout.setHorizontalGroup(
            jPanelGraficoProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        jPanelGraficoProdutosLayout.setVerticalGroup(
            jPanelGraficoProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel2.setText("Produtos mais ");

        jLabelPrimeiro.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelPrimeiro.setForeground(new java.awt.Color(51, 51, 51));
        jLabelPrimeiro.setText("Comida Caseira (80 vendas)");

        jLabelSegundo.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelSegundo.setForeground(new java.awt.Color(51, 51, 51));
        jLabelSegundo.setText("Cantinho de Minas (72 vendas)");

        jLabelTerceiro.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelTerceiro.setForeground(new java.awt.Color(51, 51, 51));
        jLabelTerceiro.setText("Resenha Carioca (67 vendas)");

        jLabelP.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelP.setForeground(new java.awt.Color(0, 0, 153));
        jLabelP.setText("1°");

        jLabelS.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelS.setForeground(new java.awt.Color(0, 51, 204));
        jLabelS.setText("2°");

        jLabelT.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelT.setForeground(new java.awt.Color(0, 51, 204));
        jLabelT.setText("3°");

        jComboBoxMes.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jComboBoxMes.setForeground(new java.awt.Color(51, 51, 255));
        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE O MÊS", "JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO" }));
        jComboBoxMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        jComboBoxMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMesItemStateChanged(evt);
            }
        });
        jComboBoxMes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxMesKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("VENDIDOS:");

        jLabelMes.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelMes.setForeground(new java.awt.Color(0, 0, 255));
        jLabelMes.setText("MARÇO");

        jLabelPrimeiroLucro.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelPrimeiroLucro.setForeground(new java.awt.Color(51, 51, 51));
        jLabelPrimeiroLucro.setText("Comida Caseira gerou um lucro bruto de R$");

        jLabelSegundoLucro.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelSegundoLucro.setForeground(new java.awt.Color(51, 51, 51));
        jLabelSegundoLucro.setText("Cantinho de Minas gerou um lucro bruto de R$");

        jLabelTerceiroLucro.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelTerceiroLucro.setForeground(new java.awt.Color(51, 51, 51));
        jLabelTerceiroLucro.setText("Resenha Carioca gerou um lucro bruto de R$");

        jLabelSexto.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelSexto.setForeground(new java.awt.Color(51, 51, 51));
        jLabelSexto.setText("Comida Masonica (37 vendas)");

        jLabelQuinto.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelQuinto.setForeground(new java.awt.Color(51, 51, 51));
        jLabelQuinto.setText("Salada a Moda da Casa (42 vendas)");

        jLabelQuarto.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelQuarto.setForeground(new java.awt.Color(51, 51, 51));
        jLabelQuarto.setText("Comida Bahiana (56 vendas)");

        jLabelQ.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelQ.setForeground(new java.awt.Color(0, 0, 153));
        jLabelQ.setText("4°");

        jLabelQQ.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelQQ.setForeground(new java.awt.Color(0, 51, 204));
        jLabelQQ.setText("5°");

        jLabelSS.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelSS.setForeground(new java.awt.Color(0, 51, 204));
        jLabelSS.setText("6°");

        jLabelSextoLucro.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelSextoLucro.setForeground(new java.awt.Color(51, 51, 51));
        jLabelSextoLucro.setText("Comida Masonica gerou um lucro bruto de R$");

        jLabelQuintoLucro.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelQuintoLucro.setForeground(new java.awt.Color(51, 51, 51));
        jLabelQuintoLucro.setText("Salada a Moda da Casa gerou um lucro bruto de R$");

        jLabelQuartoLucro.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelQuartoLucro.setForeground(new java.awt.Color(51, 51, 51));
        jLabelQuartoLucro.setText("Comida Bahiana gerou um lucro bruto de R$");

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 255));
        jLabel26.setText("Lucro bruto total: ");

        jButtonOk.setText("OK");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });
        jButtonOk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonOkKeyPressed(evt);
            }
        });

        jLabelPrimeiroLucro1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jFormattedTextFieldPrimeiro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldPrimeiro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jFormattedTextFieldSegundo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldSegundo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jFormattedTextFieldTerceiro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldTerceiro.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jFormattedTextFieldQuarto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldQuarto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jFormattedTextFieldQuinto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldQuinto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jFormattedTextFieldSexto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldSexto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jFormattedTextFieldTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldTotal.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N

        javax.swing.GroupLayout jPanelExibeGraficoProdutos5Layout = new javax.swing.GroupLayout(jPanelExibeGraficoProdutos5);
        jPanelExibeGraficoProdutos5.setLayout(jPanelExibeGraficoProdutos5Layout);
        jPanelExibeGraficoProdutos5Layout.setHorizontalGroup(
            jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                        .addComponent(jLabelPrimeiroLucro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                        .addComponent(jLabelTerceiroLucro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldTerceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                        .addComponent(jLabelQuartoLucro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(115, 115, 115)
                                .addComponent(jLabelPrimeiroLucro1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addComponent(jLabelSegundoLucro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addComponent(jLabelQuintoLucro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldQuinto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addComponent(jLabelSextoLucro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldSexto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelQ)
                                    .addComponent(jLabelQQ)
                                    .addComponent(jLabelSS))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelQuinto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelSexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelP)
                                    .addComponent(jLabelS)
                                    .addComponent(jLabelT))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelPrimeiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelSegundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelTerceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1)))
                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonOk))
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                                .addGap(243, 243, 243)
                                .addComponent(jLabelMes)))))
                .addGap(6, 6, 6)
                .addComponent(jPanelGraficoProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        jPanelExibeGraficoProdutos5Layout.setVerticalGroup(
            jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonOk))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelMes)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPrimeiro)
                            .addComponent(jLabelP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSegundo)
                            .addComponent(jLabelS))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTerceiro)
                            .addComponent(jLabelT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuarto)
                            .addComponent(jLabelQ))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuinto)
                            .addComponent(jLabelQQ))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSexto)
                            .addComponent(jLabelSS))
                        .addGap(16, 16, 16)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelPrimeiroLucro)
                                .addComponent(jFormattedTextFieldPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelPrimeiroLucro1))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSegundoLucro)
                            .addComponent(jFormattedTextFieldSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTerceiroLucro)
                            .addComponent(jFormattedTextFieldTerceiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuartoLucro)
                            .addComponent(jFormattedTextFieldQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuintoLucro)
                            .addComponent(jFormattedTextFieldQuinto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSextoLucro)
                            .addComponent(jFormattedTextFieldSexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelExibeGraficoProdutos5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelGraficoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelExibeGraficoProdutos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jFormattedTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Gráfico dos Produtos", jPanelExibeGraficoProdutos5);

        jPanelExibeGraficoProdutos6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelGraficoGastosLayout = new javax.swing.GroupLayout(jPanelGraficoGastos);
        jPanelGraficoGastos.setLayout(jPanelGraficoGastosLayout);
        jPanelGraficoGastosLayout.setHorizontalGroup(
            jPanelGraficoGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );
        jPanelGraficoGastosLayout.setVerticalGroup(
            jPanelGraficoGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabel3.setText("Resumo do Mês");

        jComboBoxMes1.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jComboBoxMes1.setForeground(new java.awt.Color(51, 51, 255));
        jComboBoxMes1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE O MÊS", "JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO" }));
        jComboBoxMes1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        jComboBoxMes1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMes1ItemStateChanged(evt);
            }
        });
        jComboBoxMes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxMes1KeyPressed(evt);
            }
        });

        jLabelMes11.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelMes11.setForeground(new java.awt.Color(0, 0, 255));
        jLabelMes11.setText("MARÇO:");

        jLabelMes1.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelMes1.setForeground(new java.awt.Color(0, 0, 255));
        jLabelMes1.setText("Estatísticas do Mês Ganhos/Gastos");

        jButtonOk1.setText("OK");
        jButtonOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOk1ActionPerformed(evt);
            }
        });
        jButtonOk1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonOk1KeyPressed(evt);
            }
        });

        jLabelPrimeiroLucro3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jLabelLucro1.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelLucro1.setText("Lucro Liquidos de Suas Vendas: ");

        jLabelLucroLiquido1.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelLucroLiquido1.setForeground(new java.awt.Color(0, 0, 255));
        jLabelLucroLiquido1.setText("00.00");

        jLabelGastosTotais1.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabelGastosTotais1.setForeground(new java.awt.Color(0, 0, 255));
        jLabelGastosTotais1.setText("00.00");

        jLabelGastos1.setFont(new java.awt.Font("Trebuchet MS", 2, 14)); // NOI18N
        jLabelGastos1.setText("Gastos Totais de Suas Vendas: ");

        jLabelSaldo1.setFont(new java.awt.Font("Trebuchet MS", 2, 18)); // NOI18N
        jLabelSaldo1.setText("Saldo do Mês:");

        jLabelSaldoTotal1.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        jLabelSaldoTotal1.setForeground(new java.awt.Color(0, 0, 255));
        jLabelSaldoTotal1.setText("00.00");

        javax.swing.GroupLayout jPanelExibeGraficoProdutos6Layout = new javax.swing.GroupLayout(jPanelExibeGraficoProdutos6);
        jPanelExibeGraficoProdutos6.setLayout(jPanelExibeGraficoProdutos6Layout);
        jPanelExibeGraficoProdutos6Layout.setHorizontalGroup(
            jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                        .addGap(404, 404, 404)
                        .addComponent(jLabelPrimeiroLucro3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                                .addComponent(jComboBoxMes1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonOk1))
                            .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                                .addGap(243, 243, 243)
                                .addComponent(jLabelMes1))))
                    .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMes11))
                    .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelLucro1)
                            .addComponent(jLabelGastos1)
                            .addComponent(jLabelSaldo1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelLucroLiquido1)
                            .addComponent(jLabelGastosTotais1)
                            .addComponent(jLabelSaldoTotal1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                .addComponent(jPanelGraficoGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelExibeGraficoProdutos6Layout.setVerticalGroup(
            jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxMes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonOk1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelMes1)
                        .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabelMes11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelLucro1)
                            .addComponent(jLabelLucroLiquido1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelGastos1)
                            .addComponent(jLabelGastosTotais1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExibeGraficoProdutos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSaldoTotal1))
                        .addGap(94, 94, 94)
                        .addComponent(jLabelPrimeiroLucro3))
                    .addGroup(jPanelExibeGraficoProdutos6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelGraficoGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Gráfico Ganhos/Gastos", jPanelExibeGraficoProdutos6);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        jLabelBalao.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelBalao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N

        jLabelBallom.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelBallom.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBallom.setText("BALLOON");

        jLabeSair.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabeSair.setForeground(new java.awt.Color(255, 255, 255));
        jLabeSair.setText("F12 - Sair");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(555, 555, 555)
                .addComponent(jLabeSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(348, 348, 348)
                .addComponent(jLabelBallom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBalao, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabeSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabelBallom))
                .addGap(5, 5, 5))
            .addComponent(jLabelBalao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabeIcon)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelEsstatisticas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jTabbedPane6, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabeIcon)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelEsstatisticas)
                            .addComponent(jLabel6))
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane6)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMesItemStateChanged
        alteraGrafico();
    }//GEN-LAST:event_jComboBoxMesItemStateChanged

    private void jComboBoxMesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxMesKeyPressed
        atalho(evt);
    }//GEN-LAST:event_jComboBoxMesKeyPressed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        alteraGrafico();
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonOkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonOkKeyPressed
        alteraGrafico();
        atalho(evt);
    }//GEN-LAST:event_jButtonOkKeyPressed

    private void jPanelExibeGraficoProdutos5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanelExibeGraficoProdutos5KeyPressed
        atalho(evt);
    }//GEN-LAST:event_jPanelExibeGraficoProdutos5KeyPressed

    private void jComboBoxMes1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMes1ItemStateChanged
        alteraGraficoGastos();
    }//GEN-LAST:event_jComboBoxMes1ItemStateChanged

    private void jComboBoxMes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxMes1KeyPressed
        atalho(evt);
    }//GEN-LAST:event_jComboBoxMes1KeyPressed

    private void jButtonOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOk1ActionPerformed
        alteraGraficoGastos();
    }//GEN-LAST:event_jButtonOk1ActionPerformed

    private void jButtonOk1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonOk1KeyPressed
        atalho(evt);
    }//GEN-LAST:event_jButtonOk1KeyPressed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.setVisible(false);
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
            java.util.logging.Logger.getLogger(JanelaEstatisticasNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaEstatisticasNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaEstatisticasNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaEstatisticasNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaEstatisticasNova dialog = new JanelaEstatisticasNova(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonOk1;
    private javax.swing.JComboBox<String> jComboBoxMes;
    private javax.swing.JComboBox<String> jComboBoxMes1;
    private javax.swing.JFormattedTextField jFormattedTextFieldPrimeiro;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuarto;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuinto;
    private javax.swing.JFormattedTextField jFormattedTextFieldSegundo;
    private javax.swing.JFormattedTextField jFormattedTextFieldSexto;
    private javax.swing.JFormattedTextField jFormattedTextFieldTerceiro;
    private javax.swing.JFormattedTextField jFormattedTextFieldTotal;
    private javax.swing.JLabel jLabeIcon;
    private javax.swing.JLabel jLabeSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelBalao;
    private javax.swing.JLabel jLabelBallom;
    private javax.swing.JLabel jLabelEsstatisticas;
    private javax.swing.JLabel jLabelGastos1;
    private javax.swing.JLabel jLabelGastosTotais1;
    private javax.swing.JLabel jLabelLucro1;
    private javax.swing.JLabel jLabelLucroLiquido1;
    private javax.swing.JLabel jLabelMes;
    private javax.swing.JLabel jLabelMes1;
    private javax.swing.JLabel jLabelMes11;
    private javax.swing.JLabel jLabelP;
    private javax.swing.JLabel jLabelPrimeiro;
    private javax.swing.JLabel jLabelPrimeiroLucro;
    private javax.swing.JLabel jLabelPrimeiroLucro1;
    private javax.swing.JLabel jLabelPrimeiroLucro3;
    private javax.swing.JLabel jLabelQ;
    private javax.swing.JLabel jLabelQQ;
    private javax.swing.JLabel jLabelQuarto;
    private javax.swing.JLabel jLabelQuartoLucro;
    private javax.swing.JLabel jLabelQuinto;
    private javax.swing.JLabel jLabelQuintoLucro;
    private javax.swing.JLabel jLabelS;
    private javax.swing.JLabel jLabelSS;
    private javax.swing.JLabel jLabelSaldo1;
    private javax.swing.JLabel jLabelSaldoTotal1;
    private javax.swing.JLabel jLabelSegundo;
    private javax.swing.JLabel jLabelSegundoLucro;
    private javax.swing.JLabel jLabelSexto;
    private javax.swing.JLabel jLabelSextoLucro;
    private javax.swing.JLabel jLabelT;
    private javax.swing.JLabel jLabelTerceiro;
    private javax.swing.JLabel jLabelTerceiroLucro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelExibeGraficoProdutos5;
    private javax.swing.JPanel jPanelExibeGraficoProdutos6;
    private javax.swing.JPanel jPanelGraficoGastos;
    private javax.swing.JPanel jPanelGraficoProdutos;
    private javax.swing.JTabbedPane jTabbedPane6;
    // End of variables declaration//GEN-END:variables
}
