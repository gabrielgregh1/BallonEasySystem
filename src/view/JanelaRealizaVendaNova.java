    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bemajava.BemaString;
import bemajava.Bematech;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Produto;
import model.bean.Vendedor;
import model.dao.ProdutoDAO;
import model.dao.VendedorDAO;

/**
 *
 * @author gabri
 */
public class JanelaRealizaVendaNova extends javax.swing.JDialog {

    List<Produto> list = new ArrayList();
    
    public JanelaRealizaVendaNova(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        condicaoInicial();
      //  cupom();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        
    }
    
    public JanelaRealizaVendaNova(JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        condicaoInicial();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       // cupom();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
    }
    
    public JanelaRealizaVendaNova() {
        initComponents();
        condicaoInicial();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       // cupom();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
    }
 
    private void atalhos(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_F2)
            FecharVenda();
        
        if(evt.getKeyCode() == KeyEvent.VK_F1)
            visibilidadePreInsercao();
        
        if(evt.getKeyCode() == KeyEvent.VK_F12)
            this.setVisible(false);
        
        if(evt.getKeyCode() == KeyEvent.VK_F7)
            Bematech.CancelaCupom();
        
        if(evt.getKeyCode() == KeyEvent.VK_F5){
            limparCampos();
            if(JanelaEmitirNotaFiscal.StructureColetaDados.cupomAberto)
                Bematech.CancelaCupom();
            }
    }
      
     private void limparCampos(){
        jTextFieldInsere.setText("");
        jTextFieldPreco.setText("");
        jTextFieldTotal.setText("");
        jTextFieldTotalVenda.setText("");
        jTextFieldQuantidadeTotal.setText("");
        jFormattedTextFieldDescontoTotal.setText("");
        jFormattedTextFieldDesconto.setText("");
        jFormattedTextFieldQtde.setText("");
        jComboBoxVendedor.setSelectedIndex(0);
        jLabelConsumidor.setText("CONSUMIDOR");
        jLabelRua.setText("Rua Consumidor");
        DefaultTableModel model = (DefaultTableModel) jTableVenda.getModel();
            while (jTableVenda.getModel().getRowCount() > 0)
                model.removeRow(0);
            
        
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
        
        if( id+3 > campoReal.length())
            campoReal = campoReal+"0";
        
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
  
    private void exibirNomeCliente(){
        String nome = JanelaEmitirNotaFiscal.StructureColetaDados.nome;
        String rua = JanelaEmitirNotaFiscal.StructureColetaDados.rua;
        if(!(nome.equals("")) || !(rua.equals(""))){
            jLabelConsumidor.setText(nome);
            jLabelRua.setText(rua);       
        }
    }
    
    private void RealizaDesconto(){
        final double MAX = 100;
        final double MIN = 0;
        try{
            realizarCalculoTotal(MIN);
            if(jFormattedTextFieldDescontoTotal.getText().equals(""))
                return;     
            double valor = transformaDouble(jFormattedTextFieldDescontoTotal.getText()); 
            if(valor < MAX && valor >= MIN){         
                double desconto;
                if(jFormattedTextFieldDescontoTotal.getText().equals(""))
                    desconto = 0;
               else
                    desconto = transformaDouble(jFormattedTextFieldDescontoTotal.getText());
                double totalBruto = transformaDouble(jTextFieldTotalVenda.getText());
                double totalComDesconto = totalBruto - totalBruto * (desconto/100);
                jTextFieldTotalVenda.setText(   transformaReais(totalComDesconto));
            }else{
                jFormattedTextFieldDescontoTotal.setText("");
                realizarCalculoTotal(MIN);
            }
        }catch(NumberFormatException ex){
            jTextFieldTotalVenda.setText("");
        }
    
    }
    
    private void realizarCalculoTotal(Double quantidadePosterior){
        DefaultTableModel model = (DefaultTableModel) jTableVenda.getModel();
        double qtdAnterior = 0;
        if(!(jTextFieldQuantidadeTotal.getText().equals("")))     
            qtdAnterior = transformaDouble(jTextFieldQuantidadeTotal.getText());
        
        double preco;
        double total = 0;
        for(int i = 0; i < model.getRowCount() ; i++){
            preco = transformaDouble((String) model.getValueAt(i, 4));
            total += preco;
        }
        jTextFieldTotalVenda.setText(transformaReais(total));
        jTextFieldQuantidadeTotal.setText(transformaReais(qtdAnterior + quantidadePosterior));
        
        
        jTextFieldPreco.setText("");
        jFormattedTextFieldDesconto.setText("");
        jFormattedTextFieldQtde.setText("");
        jTextFieldTotal.setText("");
        
    }
    
    private void FecharVenda(){
        try{
            DefaultTableModel model = (DefaultTableModel) jTableVenda.getModel();
            List<Produto> lista = new ArrayList();
            Produto p;
            if(jFormattedTextFieldDescontoTotal.getText().equals(""))
                jFormattedTextFieldDescontoTotal.setText("0");
            
                   
            if(!(jTextFieldTotalVenda.getText().equals("")) && !(jTextFieldTotalVenda.getText().equals("0")) && 
               !(jTextFieldTotalVenda.getText().equals("0,00")) && !(jTextFieldTotalVenda.getText().equals("0,0"))) {
                
                String consumidor = jLabelConsumidor.getText();
                
                String vendedorString = String.valueOf(jComboBoxVendedor.getSelectedItem());
                int idx = vendedorString.indexOf(" ");
                int idVendedor = Integer.parseInt(vendedorString.substring(0, idx));
                String nomeVendedor = vendedorString.substring(idx+3);
                
                String campoReal = jTextFieldQuantidadeTotal.getText();
                int achou = campoReal.indexOf(",");
                    if(achou != -1)
                        campoReal = campoReal.substring(0, achou)+campoReal.substring(achou+1);
                    
                int itens = Integer.parseInt(campoReal);
                double desconto = transformaDouble(jFormattedTextFieldDescontoTotal.getText());
                double totalApagar = transformaDouble(jTextFieldTotalVenda.getText());
                String unidadeMedida = jLabelUnidade.getText();

                for(int i = 0; i < model.getRowCount() ; i++){        
                    int codigo = (int) model.getValueAt(i, 0);
                    String descricao = (String) model.getValueAt(i, 1);
                    double quantidade = transformaDouble(String.valueOf(model.getValueAt(i, 2)));
                    double descontoValor = transformaDouble((String)model.getValueAt(i, 3));   
                    double preco = transformaDouble((String) model.getValueAt(i, 4));   
                    double valorUnitario = (preco+desconto)/quantidade;
                
                    p =  new Produto(codigo, descricao, preco, quantidade, unidadeMedida, descontoValor, valorUnitario);
                    lista.add(p);        
                }
                double descontoNoTotalDouble = transformaDouble(jFormattedTextFieldDescontoTotal.getText());
                String descontoNoTotal = transformaReais(desconto);
                
                if(JanelaEmitirNotaFiscal.StructureColetaDados.cupomAberto){
                    Bematech.SubTotalizaRecebimentoMFD();
                    Bematech.AcrescimoDescontoSubtotalMFD("D", "%", descontoNoTotal);
                }
                JanelaRealizaVendaNova.StructureRealizarVenda struct = new JanelaRealizaVendaNova.StructureRealizarVenda(consumidor, idVendedor, nomeVendedor ,
                                                                    itens, desconto, totalApagar, list);
               
                JanelaFinalizarVendaNova janelaVenda = new JanelaFinalizarVendaNova(this, true);       
                janelaVenda.setVisible(true);
                limparCampos();
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informe apenas números no campos acima.");
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Erro inesperado.");
        }
    }
        
    public void configuraComboBox(){
        VendedorDAO dao = new VendedorDAO(); 
            for(Vendedor vendedorPonteiro: dao.read()){
              jComboBoxVendedor.addItem(vendedorPonteiro.getCodigoVendedor()+" - "+vendedorPonteiro.getNome());
            }
    }
    
    private void condicaoInicial(){
        visibilidadePreInsercao();
        jTextFieldInsere.setEnabled(false);
        jFormattedTextFieldDescontoTotal.setEnabled(false);
        jLabelValorComprado.setVisible(false);
        configuraComboBox();
    }
    
    private void liberaCampos(){
        if(jComboBoxVendedor.getSelectedItem() == ""){
            jTextFieldInsere.setEnabled(false);
            jFormattedTextFieldDescontoTotal.setEnabled(false);
        }else{       
            visibilidadePreInsercao();               
        }
    } 
    
    private void visibilidadePosInsercao(){
        jTextFieldInsere.setEnabled(false);
        jFormattedTextFieldDesconto.setEnabled(true);
        jTextFieldPreco.setEnabled(true);
        jTextFieldPreco.setEditable(false);
        jTextFieldTotal.setEnabled(true);
        jTextFieldTotal.setEditable(false);    
        jFormattedTextFieldQtde.setEnabled(true);
        jFormattedTextFieldQtde.requestFocus();
        
    }    
    
    private void realizaCalculo(){     
        try{
            int qtde;
            double precoProd = Double.parseDouble(jTextFieldPreco.getText());
            double desconto;
            if(jFormattedTextFieldQtde.getText().equals(""))
                qtde = 1;
            else
                qtde = Integer.parseInt(jFormattedTextFieldQtde.getText());
            
            if(jFormattedTextFieldDesconto.getText().equals("")){
               desconto = 0;
            }            
            else         
                if(Double.parseDouble(jFormattedTextFieldDesconto.getText()) > 0 && 
                   Double.parseDouble(jFormattedTextFieldDesconto.getText()) < 100)
                    desconto = Integer.parseInt(jFormattedTextFieldDesconto.getText());
                else{
                    desconto = 0;
                    jFormattedTextFieldDesconto.setText("");  
                }
            double semiTotal = (qtde * precoProd);
            semiTotal = semiTotal - semiTotal*(desconto/100);  
            jTextFieldTotal.setText(tamanhoString(semiTotal));
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informar apenas números");
            jFormattedTextFieldDesconto.setText("");
            jFormattedTextFieldQtde.setText("");
        }
        
    }      

    private void imprimirCompra(int codigoInt, String descricao, String unidadeMedida, 
        double quantidadeDouble, double precoUnidadeDouble, double descontoDouble){
        
        String codigo = String.valueOf(codigoInt);
        String quantidade = tamanhoString(quantidadeDouble);
        String precoUnidade = tamanhoString(precoUnidadeDouble);
        String desconto = tamanhoString(descontoDouble);
        if(JanelaEmitirNotaFiscal.StructureColetaDados.cupomAberto){
            Bematech.VendeItemArredondamentoMFD(codigo, descricao, "II", unidadeMedida,
                                                quantidade, precoUnidade, "0,00", desconto, true);
        }
    }
    
    private void inserirProduto(){        
        double desc = 0;
        double quantidade = 1;
        
        if(!(jFormattedTextFieldDesconto.getText().equals("")))
            desc = transformaDouble(jFormattedTextFieldDesconto.getText());
        
        if(!(jFormattedTextFieldQtde.getText().equals("")))
            quantidade = transformaDouble(jFormattedTextFieldQtde.getText());
        
        Produto p;
        int codigoProduto = Integer.parseInt(jTextFieldInsere.getText());
        double qtde = quantidade;
        double semiTotal = transformaDouble(jTextFieldTotal.getText());
        String resumida = jLabelTipoProduto.getText();
        String unidadeMedida = jLabelUnidade.getText();
        double preco = Double.parseDouble(jTextFieldPreco.getText());
        double ValorComprado = transformaDouble(jLabelValorComprado.getText());
        double descontoNota = desc;
        double desconto = qtde*preco*descontoNota/100;

        
        if(jTableVenda.getRowCount() == 0){
            if(jComboBoxVendedor.getSelectedIndex() != 0){
                JanelaRealizaVendaNova.StructureRealizarVenda.nomeVendedor = String.valueOf(jComboBoxVendedor.getSelectedItem());
                JanelaEmitirNotaFiscal emiteFiscal = new JanelaEmitirNotaFiscal(this,true);
                
                emiteFiscal.setVisible(true);
            }
        }
        DefaultTableModel model = (DefaultTableModel) jTableVenda.getModel();
        
        model.addRow(new Object[]{
            codigoProduto,
            resumida,
            qtde,
            transformaReais(desconto),
            transformaReais(semiTotal)
        });
        double valorUnitario = (preco+desconto)/qtde;
                
        p =  new Produto(codigoProduto, resumida, preco, qtde, unidadeMedida, desconto, valorUnitario, ValorComprado);
        list.add(p);
        
        if(JanelaEmitirNotaFiscal.StructureColetaDados.cupomAberto)
          //  cupom();
            imprimirCompra(codigoProduto, resumida, unidadeMedida, qtde, preco, descontoNota);
        
        visibilidadePreInsercao();
        
        realizarCalculoTotal(quantidade);
    }
    
    private void visibilidadePreInsercao(){
        jTextFieldInsere.setEnabled(true);
        jTextFieldInsere.requestFocus();
        jTextFieldPreco.setEnabled(false);
        jTextFieldTotal.setEnabled(false);
        jFormattedTextFieldDesconto.setEnabled(false);
        jFormattedTextFieldQtde.setEnabled(false); 
        jTextFieldQuantidadeTotal.setEnabled(false);
        jTextFieldTotalVenda.setEnabled(false);
        jTextFieldTotalVenda.setEnabled(false);
        jFormattedTextFieldDescontoTotal.setEnabled(true);
    }
    
    private void InserirDados(){
        try{
            int produtoInformado = Integer.parseInt(jTextFieldInsere.getText());
            ProdutoDAO dao = new ProdutoDAO();
          
            for(Produto produtoPonteiro : dao.read()){
                if(produtoInformado == produtoPonteiro.getCodigoProduto()){
                    
                    
                    jLabelTipoProduto.setText(produtoPonteiro.getResumida());
                    jLabelUnidade.setText(produtoPonteiro.getUnidadeMedida());
                    jFormattedTextFieldQtde.setText("1");
                    jTextFieldPreco.setText(String.valueOf(produtoPonteiro.getPreco()));
                    jLabelValorComprado.setText(String.valueOf(produtoPonteiro.getCustoUnidade()));
                    visibilidadePosInsercao();
                    realizaCalculo();   
                    return;
                }           
            }
            JOptionPane.showMessageDialog(rootPane, "Produto não esta disponível em estoque");
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informe apenas números");
        }   
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabelConsumidor = new javax.swing.JLabel();
        jLabelRua = new javax.swing.JLabel();
        jLabelTelefone = new javax.swing.JLabel();
        jComboBoxVendedor = new javax.swing.JComboBox<>();
        jLabelSequencial = new javax.swing.JLabel();
        jLabelControle = new javax.swing.JLabel();
        jLabelBarra = new javax.swing.JLabel();
        jTextFieldInsere = new javax.swing.JTextField();
        jLabelTipoProduto = new javax.swing.JLabel();
        jLabelQuatidade = new javax.swing.JLabel();
        jFormattedTextFieldQtde = new javax.swing.JFormattedTextField();
        jLabelQuatidade1 = new javax.swing.JLabel();
        jLabelPreco = new javax.swing.JLabel();
        jTextFieldPreco = new javax.swing.JTextField();
        jLabelDesc = new javax.swing.JLabel();
        jFormattedTextFieldDesconto = new javax.swing.JFormattedTextField();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabelPreco4 = new javax.swing.JLabel();
        jLabelPreco3 = new javax.swing.JLabel();
        jLabelItens = new javax.swing.JLabel();
        jTextFieldQuantidadeTotal = new javax.swing.JTextField();
        jLabelDesconto = new javax.swing.JLabel();
        jLabelTotalVenda = new javax.swing.JLabel();
        jTextFieldTotalVenda = new javax.swing.JTextField();
        jLabelUnidade = new javax.swing.JLabel();
        jLabelValorComprado = new javax.swing.JLabel();
        jFormattedTextFieldDescontoTotal = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVenda = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelAlteracaoLucrativo3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel9.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1107, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("Realizar Venda");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconVenda_80px.png"))); // NOI18N

        jLabelConsumidor.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabelConsumidor.setText("CONSUMIDOR");

        jLabelRua.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelRua.setText("Rua Consumidor");

        jLabelTelefone.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelTelefone.setText("Telefone (xx) xxxxx - xxxx");

        jComboBoxVendedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxVendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        jComboBoxVendedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxVendedorItemStateChanged(evt);
            }
        });

        jLabelSequencial.setBackground(new java.awt.Color(51, 51, 51));
        jLabelSequencial.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelSequencial.setForeground(new java.awt.Color(51, 51, 51));
        jLabelSequencial.setText("Sequencial");

        jLabelControle.setBackground(new java.awt.Color(51, 51, 51));
        jLabelControle.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelControle.setForeground(new java.awt.Color(51, 51, 51));
        jLabelControle.setText("Controle");

        jLabelBarra.setBackground(new java.awt.Color(51, 51, 51));
        jLabelBarra.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelBarra.setForeground(new java.awt.Color(51, 51, 51));
        jLabelBarra.setText("Barra");

        jTextFieldInsere.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInsere.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldInsere.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInsereKeyPressed(evt);
            }
        });

        jLabelTipoProduto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelTipoProduto.setText("Tipo Produto");

        jLabelQuatidade.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelQuatidade.setText("Quantidade");

        jFormattedTextFieldQtde.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextFieldQtde.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldQtde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldQtdeFocusGained(evt);
            }
        });
        jFormattedTextFieldQtde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldQtdeActionPerformed(evt);
            }
        });
        jFormattedTextFieldQtde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldQtdeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldQtdeKeyReleased(evt);
            }
        });

        jLabelQuatidade1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelQuatidade1.setText("x");

        jLabelPreco.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelPreco.setText("Preço");

        jTextFieldPreco.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldPreco.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPrecoKeyPressed(evt);
            }
        });

        jLabelDesc.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelDesc.setText("Desc(%)");

        jFormattedTextFieldDesconto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jFormattedTextFieldDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextFieldDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDescontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDescontoKeyReleased(evt);
            }
        });

        jTextFieldTotal.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTotalKeyReleased(evt);
            }
        });

        jLabelPreco4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelPreco4.setText("Total");

        jLabelPreco3.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelPreco3.setText("=");

        jLabelItens.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelItens.setText("Itens");

        jTextFieldQuantidadeTotal.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jTextFieldQuantidadeTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldQuantidadeTotalKeyPressed(evt);
            }
        });

        jLabelDesconto.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelDesconto.setText("Desconto");

        jLabelTotalVenda.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelTotalVenda.setText("Total a Venda");

        jTextFieldTotalVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jTextFieldTotalVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTotalVendaKeyPressed(evt);
            }
        });

        jLabelUnidade.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelUnidade.setText("un");

        jLabelValorComprado.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabelValorComprado.setText(".");

        jFormattedTextFieldDescontoTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jFormattedTextFieldDescontoTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextFieldDescontoTotal.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jFormattedTextFieldDescontoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDescontoTotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDescontoTotalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelRua)
                    .addComponent(jLabelConsumidor))
                .addGap(174, 174, 174)
                .addComponent(jLabelTelefone)
                .addGap(76, 76, 76))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelItens))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(jLabelDesconto))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(jFormattedTextFieldDescontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabelValorComprado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTotalVenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabelTotalVenda)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabelSequencial)
                            .addGap(18, 18, 18)
                            .addComponent(jLabelControle)
                            .addGap(18, 18, 18)
                            .addComponent(jLabelBarra))
                        .addComponent(jTextFieldInsere, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTipoProduto)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextFieldQtde)
                                    .addComponent(jLabelQuatidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabelQuatidade1)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabelPreco)
                                        .addGap(70, 70, 70)
                                        .addComponent(jLabelDesc))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jFormattedTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelPreco3)
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabelPreco4)))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabelConsumidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRua)
                    .addComponent(jLabelTelefone))
                .addGap(27, 27, 27)
                .addComponent(jComboBoxVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSequencial)
                    .addComponent(jLabelControle)
                    .addComponent(jLabelBarra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldInsere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTipoProduto)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuatidade)
                    .addComponent(jLabelQuatidade1)
                    .addComponent(jLabelPreco)
                    .addComponent(jLabelDesc)
                    .addComponent(jLabelPreco4)
                    .addComponent(jLabelPreco3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldQtde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUnidade))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelItens)
                    .addComponent(jLabelDesconto))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDescontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabelTotalVenda)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelValorComprado))
                .addContainerGap())
        );

        jPanel6.setForeground(new java.awt.Color(204, 204, 204));

        jTableVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTableVenda.setForeground(new java.awt.Color(0, 0, 204));
        jTableVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIÇÃO", "QUANTIDADE", "DESCONTO", "PREÇO"
            }
        ));
        jScrollPane1.setViewportView(jTableVenda);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 255));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("F2 - Finalizar venda");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("F1 - Escolher outro produto");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("F5 - Limpar dados");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("F12 - Fechar janela");

        jLabelAlteracaoLucrativo3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoLucrativo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N
        jLabelAlteracaoLucrativo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoLucrativo3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BALLOON");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Space - Informar outro Produto");

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("F7 - Cancelar Cupom");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAlteracaoLucrativo3))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13))
                            .addComponent(jLabel5)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelAlteracaoLucrativo3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/closeWindow_30 (2).png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxVendedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxVendedorItemStateChanged
        liberaCampos();
    }//GEN-LAST:event_jComboBoxVendedorItemStateChanged

    private void jTextFieldInsereKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInsereKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!(jTextFieldInsere.getText().equals(""))){
                InserirDados();
                exibirNomeCliente();
            }

        }
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInsereKeyPressed

    private void jFormattedTextFieldQtdeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQtdeFocusGained

    }//GEN-LAST:event_jFormattedTextFieldQtdeFocusGained

    private void jFormattedTextFieldQtdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQtdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldQtdeActionPerformed

    private void jFormattedTextFieldQtdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQtdeKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!(jTextFieldTotal.getText().equals("0.0"))){
                inserirProduto();
            }
        }
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            visibilidadePreInsercao();
        }
        atalhos(evt);

    }//GEN-LAST:event_jFormattedTextFieldQtdeKeyPressed

    private void jFormattedTextFieldQtdeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQtdeKeyReleased
        if(jFormattedTextFieldQtde.getText().equals(""))
        if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            jFormattedTextFieldQtde.setText("");
        }
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
        realizaCalculo();
    }//GEN-LAST:event_jFormattedTextFieldQtdeKeyReleased

    private void jTextFieldPrecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            visibilidadePreInsercao();
        }
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldPrecoKeyPressed

    private void jFormattedTextFieldDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDescontoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!(jTextFieldTotal.getText().equals("0.0"))){
                inserirProduto();
                
            }
        }
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            visibilidadePreInsercao();
        }
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldDescontoKeyPressed

    private void jFormattedTextFieldDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDescontoKeyReleased
        realizaCalculo();
    }//GEN-LAST:event_jFormattedTextFieldDescontoKeyReleased

    private void jTextFieldTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            visibilidadePreInsercao();
        }
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTotalKeyPressed

    private void jTextFieldTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalKeyReleased
        realizaCalculo();
    }//GEN-LAST:event_jTextFieldTotalKeyReleased

    private void jTextFieldQuantidadeTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeTotalKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldQuantidadeTotalKeyPressed

    private void jTextFieldTotalVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalVendaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTotalVendaKeyPressed

    private void jLabelAlteracaoLucrativo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoLucrativo3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoLucrativo3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jFormattedTextFieldDescontoTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDescontoTotalKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            FecharVenda();

        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldDescontoTotalKeyPressed

    private void jFormattedTextFieldDescontoTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDescontoTotalKeyReleased
        RealizaDesconto();
    }//GEN-LAST:event_jFormattedTextFieldDescontoTotalKeyReleased

    public static class StructureRealizarVenda {
        public static String consumidor;
        public static int idVendedor;
        public static String nomeVendedor;
        public static int  itens;
        public static double desconto;
        public static double totalApagar;
        public static List<Produto> lista = new ArrayList();

        public StructureRealizarVenda(String consumidor, int idVendedor,String nomeVendedor, int itens,
                                      double desconto, double totalApagar, List<Produto> lista) {
            StructureRealizarVenda.consumidor = consumidor;
            StructureRealizarVenda.idVendedor = idVendedor;
            StructureRealizarVenda.itens = itens; 
            StructureRealizarVenda.desconto = desconto;
            StructureRealizarVenda.totalApagar = totalApagar;
            StructureRealizarVenda.lista = lista;
            StructureRealizarVenda.nomeVendedor = nomeVendedor;
        } 

    }
    
    
    
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
            java.util.logging.Logger.getLogger(JanelaRealizaVendaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaRealizaVendaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaRealizaVendaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaRealizaVendaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaRealizaVendaNova dialog = new JanelaRealizaVendaNova(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBoxVendedor;
    private javax.swing.JFormattedTextField jFormattedTextFieldDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldDescontoTotal;
    private javax.swing.JFormattedTextField jFormattedTextFieldQtde;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAlteracaoLucrativo3;
    private javax.swing.JLabel jLabelBarra;
    private javax.swing.JLabel jLabelConsumidor;
    private javax.swing.JLabel jLabelControle;
    private javax.swing.JLabel jLabelDesc;
    private javax.swing.JLabel jLabelDesconto;
    private javax.swing.JLabel jLabelItens;
    private javax.swing.JLabel jLabelPreco;
    private javax.swing.JLabel jLabelPreco3;
    private javax.swing.JLabel jLabelPreco4;
    private javax.swing.JLabel jLabelQuatidade;
    private javax.swing.JLabel jLabelQuatidade1;
    private javax.swing.JLabel jLabelRua;
    private javax.swing.JLabel jLabelSequencial;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JLabel jLabelTipoProduto;
    private javax.swing.JLabel jLabelTotalVenda;
    private javax.swing.JLabel jLabelUnidade;
    private javax.swing.JLabel jLabelValorComprado;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVenda;
    private javax.swing.JTextField jTextFieldInsere;
    private javax.swing.JTextField jTextFieldPreco;
    private javax.swing.JTextField jTextFieldQuantidadeTotal;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldTotalVenda;
    // End of variables declaration//GEN-END:variables
}
