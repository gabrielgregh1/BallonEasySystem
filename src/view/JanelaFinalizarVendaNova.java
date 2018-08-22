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
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.CaixaPadrao;
import model.bean.FormaPagamento;
import model.bean.Produto;
import model.bean.Produto_vendido;
import model.bean.Venda;
import model.bean.Vendedor;
import model.dao.CaixaPadraoDAO;
import model.dao.ProdutoDAO;
import model.dao.VendaDAO;
import model.dao.produto_vendidoDAO;

/**
 *
 * @author gabri
 */
public class JanelaFinalizarVendaNova extends javax.swing.JDialog {

    private final double  INICIAL = 0.0;
    
    private static final long serialVersionUID = 7813064106536867782L;
    public int iReturn;
    String Retornos;
    
    public JanelaFinalizarVendaNova(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        condicaoInicial();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       // setExtendedState(JFrame.MAXIMIZED_BOTH); 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
    }
    
    public JanelaFinalizarVendaNova(JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        condicaoInicial();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       // setExtendedState(JFrame.MAXIMIZED_BOTH); 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(-10, 0, screenSize.width+20, screenSize.height-25);
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

    private void atalhos(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_F2)
            FecharCompra();   
        if(evt.getKeyCode() == KeyEvent.VK_F5)
            condicaoInicial();
        if(evt.getKeyCode() == KeyEvent.VK_F12)
            this.setVisible(false);
        
        
    }
    
    private void condicaoInicial(){
      //  resto = JanelaRealizarVenda.StructureRealizarVenda.totalApagar;
        DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
        double totalApagar = JanelaRealizaVendaNova.StructureRealizarVenda.totalApagar;
        jFormattedTextFieldTotal.setText(transformaReais(totalApagar)); 
        jFormattedTextFieldTotal.setEditable(false);
        jTextFieldTituloCheque.setEditable(false);
        jTextFieldTituloCrediario.setEditable(false);
        jFormattedTextFieldTroco.setEditable(false);
        jTextFieldTituloCredito.setEditable(false);
        jTextFieldTituloDebito.setEditable(false);
        jTextFieldTituloDinheiro.setEditable(false);
        jTextFieldTituloPre.setEditable(false);
        jFormattedTextFieldFalta.setEditable(false);
        camposVazios();
        jFormattedTextFieldDinheiro.setText(""); 
        jComboBoxCheque.setSelectedIndex(0);
        jComboBoxCredito.setSelectedIndex(0);
        jFormattedTextFieldCrediario.setVisible(false);
        jTextFieldTituloCrediario.setVisible(false);
                
        model.setValueAt(INICIAL, 1, 2);
        model.setValueAt(INICIAL, 3, 2);
        model.setValueAt(INICIAL, 4, 2);
        model.setValueAt(INICIAL, 6, 2);
        model.setValueAt(INICIAL, 7, 2);
        jFormattedTextFieldDinheiro.requestFocus();
        jFormattedTextFieldFalta.setText("");
        jFormattedTextFieldTroco.setText("");
        
    } 
    
    private void camposVazios(){
        jFormattedTextFieldPre.setText("");  
        jFormattedTextFieldCheque.setText("");  
        jFormattedTextFieldCartaoDebito.setText("");  
        jFormattedTextFieldCartaoCredito.setText("");  
        jFormattedTextFieldCrediario.setText("");  
        jFormattedTextFieldDinheiro.setText("");  
    }
    
    private String configuraValorMaximo(java.awt.event.KeyEvent evt, String campoJtextField , int row, int col, int formaPagamento){
        try{
            DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
            if(verificaNaoEspacos(campoJtextField)){
            
                double valorCampo = transformaDouble(campoJtextField);

                  //  limitarTamanhoCampo(campoJtextField);
                
                if(KeyEvent.VK_ENTER == evt.getKeyCode() && verificaPagamento(valorCampo, formaPagamento)) {
                    model.setValueAt(valorCampo, row, col);  
                    camposVazios();
                }                
                
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informar apenas números");   
            return "";
        }
        return campoJtextField;
    }
    
  
    
    private void showMessage(List<FormaPagamento> listaPagamento){
        
        String[] opcao = {"Sim", "Não"}; 
        
        int escolha = JOptionPane.showOptionDialog(rootPane, "Deseja Finalizar a compra?",
                                     "Escolha um opção", WIDTH, HEIGHT, null, opcao, opcao[0]);
        
        if(escolha == 0){    
          
            String nomeVendedor = JanelaRealizaVendaNova.StructureRealizarVenda.nomeVendedor;        
            int idVendedor = JanelaRealizaVendaNova.StructureRealizarVenda.idVendedor;
            double totalApagar = JanelaRealizaVendaNova.StructureRealizarVenda.totalApagar;
            
            //CADASTRAR VENDA        
            Vendedor vendedor = new Vendedor(idVendedor, nomeVendedor);
            Venda venda = new Venda(vendedor, totalApagar);
            VendaDAO venDAO = new VendaDAO();
            venDAO.create(venda);
            Venda vendinha = new Venda();
            
            //CADASTRAR CaixaPadrao
            CaixaPadrao cp = new CaixaPadrao("Venda" ,totalApagar, 0);
            CaixaPadraoDAO cPDao = new CaixaPadraoDAO();
            cPDao.create(cp);
            
            for(Venda v :venDAO.read()){
                if(vendinha.getCodigo() < v.getCodigo())
                    vendinha = v;
            }
            //CADASTRAR Produto_vendido
            Produto_vendido produto_vendido;
            List<Produto> lista = JanelaRealizaVendaNova.StructureRealizarVenda.lista;
            ProdutoDAO dao = new ProdutoDAO();
            produto_vendidoDAO daoVendido = new produto_vendidoDAO();
            for(Produto p : lista){
                dao.venderProdutoUpdate(p.getQtdEstoque(), p.getCodigoProduto()); 
                produto_vendido = new Produto_vendido(p, vendinha);
                daoVendido.create(produto_vendido);
            }
            
            if(JanelaEmitirNotaFiscal.StructureColetaDados.cupomAberto){
                for(FormaPagamento pagamento : listaPagamento){
                String formaPagamento = pagamento.getTipoPagamento();
                String valor = pagamento.getValor();
            
                Bematech.EfetuaFormaPagamento(formaPagamento, valor);
            }
        }
        if(JanelaEmitirNotaFiscal.StructureColetaDados.cupomAberto)    
            Bematech.TerminaFechamentoCupom("Obrigado, volte sempre!!");
        
        this.dispose();

        }
        
    }
    
    private double pegarValoresJTable(int campoTable){
        DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
        //Parece complexo, porém é apenas um vetor que consulta os dados da table        
        double[] lerCampo = {(double)model.getValueAt(1, 2),    // 0 para dinheiro
                             (double)model.getValueAt(3, 2),    // 1 para cheque a vista
                             (double)model.getValueAt(4, 2),    // 2 para cheque pre
                             (double)model.getValueAt(6, 2),    // 3 para cartao Debito
                             (double)model.getValueAt(7, 2)};   // 4 para cartao credito
        
        
        return lerCampo[campoTable];
    }
    
    private void FecharCompra(){
        try{
            DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
            double totalVenda = transformaDouble(jFormattedTextFieldTotal.getText());
        
            double totalFormasPagar = (double)model.getValueAt(8, 2); 

            if(totalFormasPagar >= totalVenda){
                jFormattedTextFieldTroco.setText(transformaReais(totalFormasPagar - totalVenda));
                //jFormattedTextFieldTroco.setText(limitarTamanhoCampo(jFormattedTextFieldTroco.getText()));
                
                
                List<FormaPagamento> listaPagamento = new ArrayList();
                FormaPagamento pagamento;
                if(!(model.getValueAt(1, 2).equals(INICIAL)) && !(model.getValueAt(1, 2).equals(""))){
                    //pega o número da jTable
                    String valor = String.valueOf(model.getValueAt(1, 2));
                    //encontra o ponto
                    int idx = valor.indexOf(".");
                    //substitui o ponto por virgula
                    String valorv = valor.substring(0, idx)+","+valor.substring(idx+1);
                   
                    if(idx+3 > valorv.length())
                        valorv +="0";
                            
                    String tipoPagameto = String.valueOf(model.getValueAt(1, 1));
                    
                    pagamento = new FormaPagamento(tipoPagameto, valorv);
                    listaPagamento.add(pagamento);
                }
                
                if(!(model.getValueAt(3, 2).equals(INICIAL)) && !(model.getValueAt(1, 2).equals(""))){
                    //pega o número da jTable
                    String valor = String.valueOf(model.getValueAt(3, 2));
                    //encontra o ponto
                    int idx = valor.indexOf(".");
                    //substitui o ponto por virgula
                    String valorv = valor.substring(0, idx)+",0"+valor.substring(idx+1);
                   
                    if(idx+3 > valorv.length())
                        valorv +="0";
                    
                    String tipoPagameto = String.valueOf(model.getValueAt(3, 1));
                    
                    pagamento = new FormaPagamento(tipoPagameto, valorv);
                    listaPagamento.add(pagamento);
                }
                
                if(!(model.getValueAt(4, 2).equals(INICIAL)) && !(model.getValueAt(1, 2).equals(""))){
                    //pega o número da jTable
                    String valor = String.valueOf(model.getValueAt(4, 2));
                    //encontra o ponto
                    int idx = valor.indexOf(".");
                    //substitui o ponto por virgula
                    String valorv = valor.substring(0, idx)+",0"+valor.substring(idx+1);
                   
                    if(idx+3 > valorv.length())
                        valorv +="0";
                    
                    String tipoPagameto = String.valueOf(model.getValueAt(4, 1));
                    
                    pagamento = new FormaPagamento(tipoPagameto, valorv);
                    listaPagamento.add(pagamento);
                }
                
                if(!(model.getValueAt(6, 2).equals(INICIAL)) && !(model.getValueAt(1, 2).equals(""))){
                    //pega o número da jTable
                    String valor = String.valueOf(model.getValueAt(6, 2));
                    //encontra o ponto
                    int idx = valor.indexOf(".");
                    //substitui o ponto por virgula
                    String valorv = valor.substring(0, idx)+",0"+valor.substring(idx+1);
                   
                    if(idx+3 > valorv.length())
                        valorv +="0";
                    
                    String tipoPagameto = String.valueOf(model.getValueAt(6, 1));
                    
                    pagamento = new FormaPagamento(tipoPagameto, valorv);
                    listaPagamento.add(pagamento);
                }
                
                if(!(model.getValueAt(7, 2).equals(INICIAL)) && !(model.getValueAt(1, 2).equals(""))){
                    //pega o número da jTable
                    String valor = String.valueOf(model.getValueAt(7, 2));
                    //encontra o ponto
                    int idx = valor.indexOf(".");
                    //substitui o ponto por virgula
                    String valorv = valor.substring(0, idx)+",0"+valor.substring(idx+1);
                   
                    if(idx+3 > valorv.length())
                        valorv +="0";
                    
                    String tipoPagameto = String.valueOf(model.getValueAt(7, 1));
                    
                    pagamento = new FormaPagamento(tipoPagameto, valorv);
                    listaPagamento.add(pagamento);
                }
  
                showMessage(listaPagamento);
                
            }else{
                JOptionPane.showMessageDialog(rootPane,"Dinheiro Insuficiente para finalizar compra");
            }
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(rootPane, "Informe a forma de pagamento");
        }
    }
        
    private boolean verificaPagamento(double valor, int campo){
        if(!(campo == 2 && jComboBoxCheque.getSelectedIndex() == 0) && !(campo == 5 && jComboBoxCredito.getSelectedIndex() == 0)){
        jFormattedTextFieldTroco.setText(limitarTamanhoCampo("")); 
        jFormattedTextFieldFalta.setText(limitarTamanhoCampo("")); 
        DefaultTableModel model = (DefaultTableModel) jTableResultado.getModel();
        double totalVenda = transformaDouble(jFormattedTextFieldTotal.getText());
        
        double dinheiro = pegarValoresJTable(0);         
        double chequeAvista = pegarValoresJTable(1);
        double chequePre = pegarValoresJTable(2);
        double cartaoDebito = pegarValoresJTable(3);
        double cartaoCredito = pegarValoresJTable(4);
                    
        switch(campo){
            case 1:
                dinheiro = valor;
                break;
            case 2:
                chequeAvista = valor;
                break;
            case 3:
                chequePre = valor;
                break;
            case 4:
                cartaoDebito = valor;
                break;
            case 5:
                cartaoCredito = valor;
                break;
        }
                
        double totalFormasPagar = chequeAvista + chequePre + cartaoDebito + cartaoCredito + dinheiro;
       // resto = totalVenda - totalFormasPagar;      
      //  if(resto < 0)
        //    resto = totalFormasPagar - totalVenda;
        model.setValueAt(totalFormasPagar, 8, 2); 
        
        if(totalFormasPagar >= totalVenda){
            jFormattedTextFieldTroco.setText(transformaReais(totalFormasPagar - totalVenda));
           // jTextFieldTroco.setText(limitarTamanhoCampo(jTextFieldTroco.getText()));
        //    showMessage();
        }else{
            jFormattedTextFieldFalta.setText(transformaReais(totalVenda - totalFormasPagar)); 
        }
        
        return true;
        
        }
        JOptionPane.showMessageDialog(rootPane, "Informe as parcelas");
        return false;
    }
    
    private String limitarTamanhoCampo(String campoJtextField){
        int idProcura = campoJtextField.indexOf(".");
        if(idProcura != -1)
            while(idProcura+3 < campoJtextField.length())
                campoJtextField = campoJtextField.substring(0, campoJtextField.length()-1);

        return campoJtextField;
    } 
    
    
    
    private boolean verificaNaoEspacos(String campo){
        int espaco = campo.indexOf(" ");  
        return espaco == -1 && !(campo.equals(""));
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTituloDinheiro = new javax.swing.JTextField();
        jTextFieldTituloCheque = new javax.swing.JTextField();
        jTextFieldTituloDebito = new javax.swing.JTextField();
        jTextFieldTituloCredito = new javax.swing.JTextField();
        jTextFieldTituloPre = new javax.swing.JTextField();
        jTextFieldTituloCrediario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxCredito = new javax.swing.JComboBox<>();
        jComboBoxCheque = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableResultado = new javax.swing.JTable();
        jFormattedTextFieldDinheiro = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCheque = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCartaoDebito = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCartaoCredito = new javax.swing.JFormattedTextField();
        jFormattedTextFieldPre = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCrediario = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTotal = new javax.swing.JFormattedTextField();
        jFormattedTextFieldFalta = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTroco = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabelLogoBalloon = new javax.swing.JLabel();
        jLabelBalloon = new javax.swing.JLabel();
        jLabelF2 = new javax.swing.JLabel();
        jLabelF5 = new javax.swing.JLabel();
        jLabelF12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconFinalizaVenda_80px.png"))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 255));
        jLabel38.setText("Forma de Pagamento");

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("FALTA:");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("TOTAL A PAGAR:");

        jTextFieldTituloDinheiro.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloDinheiro.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloDinheiro.setText("Dinheiro");
        jTextFieldTituloDinheiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloDinheiroMouseClicked(evt);
            }
        });
        jTextFieldTituloDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloDinheiroActionPerformed(evt);
            }
        });
        jTextFieldTituloDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloDinheiroKeyPressed(evt);
            }
        });

        jTextFieldTituloCheque.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloCheque.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloCheque.setText("Cheque");
        jTextFieldTituloCheque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloChequeMouseClicked(evt);
            }
        });
        jTextFieldTituloCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloChequeActionPerformed(evt);
            }
        });
        jTextFieldTituloCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloChequeKeyPressed(evt);
            }
        });

        jTextFieldTituloDebito.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloDebito.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloDebito.setText("Cart.Débito");
        jTextFieldTituloDebito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloDebitoMouseClicked(evt);
            }
        });
        jTextFieldTituloDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloDebitoActionPerformed(evt);
            }
        });
        jTextFieldTituloDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloDebitoKeyPressed(evt);
            }
        });

        jTextFieldTituloCredito.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloCredito.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloCredito.setText("Cart.Crédito");
        jTextFieldTituloCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloCreditoMouseClicked(evt);
            }
        });
        jTextFieldTituloCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloCreditoActionPerformed(evt);
            }
        });
        jTextFieldTituloCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloCreditoKeyPressed(evt);
            }
        });

        jTextFieldTituloPre.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloPre.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloPre.setText("Cheque Pré");
        jTextFieldTituloPre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloPreMouseClicked(evt);
            }
        });
        jTextFieldTituloPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloPreActionPerformed(evt);
            }
        });
        jTextFieldTituloPre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloPreKeyPressed(evt);
            }
        });

        jTextFieldTituloCrediario.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextFieldTituloCrediario.setForeground(new java.awt.Color(0, 153, 255));
        jTextFieldTituloCrediario.setText("Crediário");
        jTextFieldTituloCrediario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldTituloCrediarioMouseClicked(evt);
            }
        });
        jTextFieldTituloCrediario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloCrediarioActionPerformed(evt);
            }
        });
        jTextFieldTituloCrediario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTituloCrediarioKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("TROCO:");

        jComboBoxCredito.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jComboBoxCredito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jComboBoxCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxCreditoKeyPressed(evt);
            }
        });

        jComboBoxCheque.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jComboBoxCheque.setForeground(new java.awt.Color(0, 153, 255));
        jComboBoxCheque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jComboBoxCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxChequeKeyPressed(evt);
            }
        });

        jTableResultado.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jTableResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Dinheiro", null, null},
                {null, "Dinheiro", ""},
                {"Cheque", null, null},
                {null, "Cheque", ""},
                {"", "Cheque", ""},
                {"Cartões", null, null},
                {null, "Cartão de Debito", ""},
                {null, "Cartão de Credito", ""},
                {"Total", null, null}
            },
            new String [] {
                " ", "Operação", "Valor"
            }
        ));
        jTableResultado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableResultadoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableResultado);

        jFormattedTextFieldDinheiro.setForeground(new java.awt.Color(0, 153, 255));
        jFormattedTextFieldDinheiro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldDinheiro.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jFormattedTextFieldDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDinheiroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldDinheiroKeyReleased(evt);
            }
        });

        jFormattedTextFieldCheque.setForeground(new java.awt.Color(0, 153, 255));
        jFormattedTextFieldCheque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldCheque.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jFormattedTextFieldCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldChequeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldChequeKeyReleased(evt);
            }
        });

        jFormattedTextFieldCartaoDebito.setForeground(new java.awt.Color(0, 153, 255));
        jFormattedTextFieldCartaoDebito.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldCartaoDebito.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jFormattedTextFieldCartaoDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCartaoDebitoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCartaoDebitoKeyReleased(evt);
            }
        });

        jFormattedTextFieldCartaoCredito.setForeground(new java.awt.Color(0, 153, 255));
        jFormattedTextFieldCartaoCredito.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldCartaoCredito.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jFormattedTextFieldCartaoCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCartaoCreditoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCartaoCreditoKeyReleased(evt);
            }
        });

        jFormattedTextFieldPre.setForeground(new java.awt.Color(0, 153, 255));
        jFormattedTextFieldPre.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldPre.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jFormattedTextFieldPre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldPreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldPreKeyReleased(evt);
            }
        });

        jFormattedTextFieldCrediario.setForeground(new java.awt.Color(0, 153, 255));
        jFormattedTextFieldCrediario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldCrediario.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jFormattedTextFieldCrediario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCrediarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCrediarioKeyReleased(evt);
            }
        });

        jFormattedTextFieldTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        jFormattedTextFieldTotal.setForeground(new java.awt.Color(0, 153, 255));
        jFormattedTextFieldTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldTotal.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        jFormattedTextFieldTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTotalKeyReleased(evt);
            }
        });

        jFormattedTextFieldFalta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));
        jFormattedTextFieldFalta.setForeground(new java.awt.Color(255, 0, 0));
        jFormattedTextFieldFalta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldFalta.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        jFormattedTextFieldFalta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldFaltaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldFaltaKeyReleased(evt);
            }
        });

        jFormattedTextFieldTroco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));
        jFormattedTextFieldTroco.setForeground(new java.awt.Color(255, 0, 0));
        jFormattedTextFieldTroco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldTroco.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        jFormattedTextFieldTroco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTrocoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTrocoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldTituloDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloCrediario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTituloPre, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jFormattedTextFieldPre)
                                    .addComponent(jFormattedTextFieldDinheiro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldCheque, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldCartaoDebito)
                                    .addComponent(jFormattedTextFieldCartaoCredito, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldCrediario, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextFieldTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldTotal)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldFalta, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jFormattedTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldFalta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTituloDinheiro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTituloCheque)
                            .addComponent(jComboBoxCheque))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldCartaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTituloDebito))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldCartaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTituloCredito))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCredito, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jFormattedTextFieldPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTituloPre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldTituloCrediario, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jFormattedTextFieldTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldCrediario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

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

        jLabelF2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF2.setText("F2 - Finalizar Venda");

        jLabelF5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF5.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF5.setText("F5 - Limpar dados");

        jLabelF12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF12.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF12.setText("F12 - Fechar janela");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(191, Short.MAX_VALUE)
                .addComponent(jLabelF2)
                .addGap(36, 36, 36)
                .addComponent(jLabelF5)
                .addGap(18, 18, 18)
                .addComponent(jLabelF12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                .addComponent(jLabelBalloon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelLogoBalloon)
                .addGap(44, 44, 44))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogoBalloon, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelF2)
                        .addComponent(jLabelF5)
                        .addComponent(jLabelF12))
                    .addComponent(jLabelBalloon)))
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/closeWindow_30 (2).png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTituloDinheiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloDinheiroMouseClicked
        jFormattedTextFieldDinheiro.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloDinheiroMouseClicked

    private void jTextFieldTituloDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloDinheiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloDinheiroActionPerformed

    private void jTextFieldTituloDinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloDinheiroKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloDinheiroKeyPressed

    private void jTextFieldTituloChequeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloChequeMouseClicked
        jFormattedTextFieldCheque.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloChequeMouseClicked

    private void jTextFieldTituloChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloChequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloChequeActionPerformed

    private void jTextFieldTituloChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloChequeKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloChequeKeyPressed

    private void jTextFieldTituloDebitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloDebitoMouseClicked
        jFormattedTextFieldCartaoDebito.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloDebitoMouseClicked

    private void jTextFieldTituloDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloDebitoActionPerformed

    private void jTextFieldTituloDebitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloDebitoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloDebitoKeyPressed

    private void jTextFieldTituloCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloCreditoMouseClicked
        jFormattedTextFieldCartaoCredito.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloCreditoMouseClicked

    private void jTextFieldTituloCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloCreditoActionPerformed

    private void jTextFieldTituloCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloCreditoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloCreditoKeyPressed

    private void jTextFieldTituloPreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloPreMouseClicked
        jFormattedTextFieldPre.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloPreMouseClicked

    private void jTextFieldTituloPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloPreActionPerformed

    private void jTextFieldTituloPreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloPreKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloPreKeyPressed

    private void jTextFieldTituloCrediarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTituloCrediarioMouseClicked
        jFormattedTextFieldCrediario.requestFocus();
    }//GEN-LAST:event_jTextFieldTituloCrediarioMouseClicked

    private void jTextFieldTituloCrediarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloCrediarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloCrediarioActionPerformed

    private void jTextFieldTituloCrediarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTituloCrediarioKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldTituloCrediarioKeyPressed

    private void jComboBoxCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxCreditoKeyPressed
        configuraValorMaximo(evt,jFormattedTextFieldCartaoCredito.getText(), 7, 2, 5);
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxCreditoKeyPressed

    private void jComboBoxChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxChequeKeyPressed
        jFormattedTextFieldCheque.setText(configuraValorMaximo(evt,jFormattedTextFieldCheque.getText(), 3, 2, 2));
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxChequeKeyPressed

    private void jTableResultadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableResultadoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTableResultadoKeyPressed

    private void jLabelLogoBalloonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoBalloonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLogoBalloonMouseClicked

    private void jFormattedTextFieldDinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDinheiroKeyPressed
        configuraValorMaximo(evt,jFormattedTextFieldDinheiro.getText(), 1, 2, 1);
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldDinheiroKeyPressed

    private void jFormattedTextFieldDinheiroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDinheiroKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
          //  jFormattedTextFieldDinheiro.setText(limitarTamanhoCampo(jTextFieldDinheiro.getText()));
            if(verificaNaoEspacos(jFormattedTextFieldDinheiro.getText())){
                jFormattedTextFieldDinheiro.setText(configuraValorMaximo(evt,jFormattedTextFieldDinheiro.getText(), 1, 2, 1));
                //      jTextFieldDinheiro.setText(limitarTamanhoCampo(jTextFieldDinheiro.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jFormattedTextFieldDinheiro.setText("");
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldDinheiroKeyReleased

    private void jFormattedTextFieldChequeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldChequeKeyPressed
        jFormattedTextFieldCheque.setText(configuraValorMaximo(evt,jFormattedTextFieldCheque.getText(), 3, 2, 2));
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldChequeKeyPressed

    private void jFormattedTextFieldChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldChequeKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
          //  jFormattedTextFieldCheque.setText(limitarTamanhoCampo(jFormattedTextFieldCheque.getText()));
            if(verificaNaoEspacos(jFormattedTextFieldCheque.getText())){
                jFormattedTextFieldCheque.setText(configuraValorMaximo(evt,jFormattedTextFieldCheque.getText(), 3, 2, 2));
                //  jTextFieldCheque.setText(limitarTamanhoCampo(jTextFieldCheque.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jFormattedTextFieldCheque.setText("");
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldChequeKeyReleased

    private void jFormattedTextFieldCartaoDebitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCartaoDebitoKeyPressed
        configuraValorMaximo(evt,jFormattedTextFieldCartaoDebito.getText(), 6, 2, 4);
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldCartaoDebitoKeyPressed

    private void jFormattedTextFieldCartaoDebitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCartaoDebitoKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
          //  jFormattedTextFieldCartaoDebito.setText(limitarTamanhoCampo(jFormattedTextFieldCartaoDebito.getText()));
            if(verificaNaoEspacos(jFormattedTextFieldCartaoDebito.getText())){
                jFormattedTextFieldCartaoDebito.setText(configuraValorMaximo(evt,jFormattedTextFieldCartaoDebito.getText(), 6, 2, 4));
                //  jTextFieldCartaoDebito.setText(limitarTamanhoCampo(jTextFieldCartaoDebito.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jFormattedTextFieldCartaoDebito.setText("");
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldCartaoDebitoKeyReleased

    private void jFormattedTextFieldCartaoCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCartaoCreditoKeyPressed
        configuraValorMaximo(evt,jFormattedTextFieldCartaoCredito.getText(), 7, 2, 5);
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldCartaoCreditoKeyPressed

    private void jFormattedTextFieldCartaoCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCartaoCreditoKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
            //jFormattedTextFieldCartaoCredito.setText(limitarTamanhoCampo(jFormattedTextFieldCartaoCredito.getText()));
            if(verificaNaoEspacos(jFormattedTextFieldCartaoCredito.getText())){
                jFormattedTextFieldCartaoCredito.setText(configuraValorMaximo(evt,jFormattedTextFieldCartaoCredito.getText(), 7, 2, 5));
                //   jTextFieldCredito.setText(limitarTamanhoCampo(jTextFieldCredito.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jFormattedTextFieldCartaoCredito.setText("");
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldCartaoCreditoKeyReleased

    private void jFormattedTextFieldPreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPreKeyPressed
        configuraValorMaximo(evt,jFormattedTextFieldPre.getText(), 4, 2, 3);
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldPreKeyPressed

    private void jFormattedTextFieldPreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPreKeyReleased
        if(!(evt.getKeyCode() == KeyEvent.VK_ENTER)){
            //jFormattedTextFieldPre.setText(limitarTamanhoCampo(jFormattedTextFieldPre.getText()));
            if(verificaNaoEspacos(jFormattedTextFieldPre.getText())){
                jFormattedTextFieldPre.setText(configuraValorMaximo(evt,jFormattedTextFieldPre.getText(), 4, 2, 3));
                //    jTextFieldPre.setText(limitarTamanhoCampo(jTextFieldPre.getText()));
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Insira apenas números");
                jFormattedTextFieldPre.setText("");
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldPreKeyReleased

    private void jFormattedTextFieldCrediarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCrediarioKeyPressed
         atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldCrediarioKeyPressed

    private void jFormattedTextFieldCrediarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCrediarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldCrediarioKeyReleased

    private void jFormattedTextFieldTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTotalKeyPressed

    private void jFormattedTextFieldTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTotalKeyReleased

    private void jFormattedTextFieldFaltaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFaltaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFaltaKeyPressed

    private void jFormattedTextFieldFaltaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFaltaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFaltaKeyReleased

    private void jFormattedTextFieldTrocoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTrocoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldTrocoKeyPressed

    private void jFormattedTextFieldTrocoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTrocoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTrocoKeyReleased

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    public static class StructureFinalizaVenda{
        public static List<FormaPagamento> listaPagamento = new ArrayList();
        
        public StructureFinalizaVenda(List<FormaPagamento> listaPagamento) {
            StructureFinalizaVenda.listaPagamento = listaPagamento;
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
            java.util.logging.Logger.getLogger(JanelaFinalizarVendaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaFinalizarVendaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaFinalizarVendaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaFinalizarVendaNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaFinalizarVendaNova dialog = new JanelaFinalizarVendaNova(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBoxCheque;
    private javax.swing.JComboBox<String> jComboBoxCredito;
    private javax.swing.JFormattedTextField jFormattedTextFieldCartaoCredito;
    private javax.swing.JFormattedTextField jFormattedTextFieldCartaoDebito;
    private javax.swing.JFormattedTextField jFormattedTextFieldCheque;
    private javax.swing.JFormattedTextField jFormattedTextFieldCrediario;
    private javax.swing.JFormattedTextField jFormattedTextFieldDinheiro;
    private javax.swing.JFormattedTextField jFormattedTextFieldFalta;
    private javax.swing.JFormattedTextField jFormattedTextFieldPre;
    private javax.swing.JFormattedTextField jFormattedTextFieldTotal;
    private javax.swing.JFormattedTextField jFormattedTextFieldTroco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelBalloon;
    private javax.swing.JLabel jLabelF12;
    private javax.swing.JLabel jLabelF2;
    private javax.swing.JLabel jLabelF5;
    private javax.swing.JLabel jLabelLogoBalloon;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableResultado;
    private javax.swing.JTextField jTextFieldTituloCheque;
    private javax.swing.JTextField jTextFieldTituloCrediario;
    private javax.swing.JTextField jTextFieldTituloCredito;
    private javax.swing.JTextField jTextFieldTituloDebito;
    private javax.swing.JTextField jTextFieldTituloDinheiro;
    private javax.swing.JTextField jTextFieldTituloPre;
    // End of variables declaration//GEN-END:variables
}
