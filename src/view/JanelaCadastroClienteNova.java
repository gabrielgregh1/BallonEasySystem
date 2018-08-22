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
import model.bean.ClienteFisico;
import model.bean.ClienteJuridico;
import model.dao.ClienteDAO;
import utilitarios.WebServiceCep;

/**
 *
 * @author gabri
 */
public class JanelaCadastroClienteNova extends javax.swing.JDialog {

    /**
     * Creates new form JanelaCadastroClienteNova
     */
    public JanelaCadastroClienteNova(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        condicaoInicial();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      //  setExtendedState(JFrame.MAXIMIZED_BOTH); 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
    }

    private void limparCamposAltercao(){
        jTextFieldAlteracaoCPF.setText("");
        jTextFieldAlteracaoNome.setText("");

        jFormattedTextFieldAlteracaoDataNasc.setText("");
        jTextFieldAlteracaoRG.setText("");
        jComboBoxAlteracaoOrgao.setSelectedIndex(0);
        jCheckBoxAlteracaoPermite.setSelected(false);
            
        jFormattedTextFieldAlteracaoCEP.setText("");
        jComboBoxAlteracaoUF.setSelectedIndex(0);
        jTextFieldAlteracaoCidade.setText("");
        jTextFieldAlteracaoBairro.setText("");
           
        jTextFieldAlteracaoRua.setText("");
        jTextFieldAlteracaoNumero.setText("");
        jTextFieldAlteracaoComplemento.setText("");
            
        jTextFieldAlteracaoEmail.setText("");
        jFormattedTextFieldAlteracaoRenda.setText("");
        jFormattedTextFieldAlteracaoLimite.setText("");
        jTextFieldAlteracaoTelefone.setText("");
            
        jTextFieldAlteracaoMae.setText("");
        jTextFieldAlteracaoPai.setText("");
     
    }
    
    private void limparCamposInclusao(){
        jTextFieldInclusaoCPF.setText("");
        jTextFieldInclusaoNome.setText("");

        jFormattedTextFieldInsercaoDataNasc.setText("");
        jTextFieldInclusaoRG.setText("");
        jComboBoxInclusaoOrgao.setSelectedIndex(0);
        jCheckBoxInclusaoPermite.setSelected(false);
            
        jFormattedTextFieldInclusaoCEP.setText("");
        jComboBoxInclusaoUF.setSelectedIndex(0);
        jTextFieldInclusaoCidade.setText("");
        jTextFieldInclusaoBairro.setText("");
           
        jTextFieldInclusaoRua.setText("");
        jTextFieldInclusaoNumero.setText("");
        jTextFieldInclusaoComplemento.setText("");
            
        jTextFieldInclusaoEmail.setText("");
        jFormattedTextFieldInclusaoRenda.setText("");
        jFormattedTextFieldInclusaoLimite.setText("");
        jTextFieldInclusaoTelefone.setText("");
            
        jTextFieldInclusaoMae.setText("");
        jTextFieldInclusaoPai.setText("");
    }
    
    private void condicaoInicial(){
        jTextFieldAlteracaoCPF.setEnabled(true);
        jTextFieldAlteracaoNome.setEnabled(false);
     
        jRadioButtonAlteracaoMasculino.setEnabled(false);

        jRadioButtonAlteracaoFeminino.setEnabled(false);

        jFormattedTextFieldAlteracaoDataNasc.setEnabled(false);
        jTextFieldAlteracaoRG.setEnabled(false);
        jComboBoxAlteracaoOrgao.setEnabled(false);
        jCheckBoxAlteracaoPermite.setEnabled(false);
            
        jFormattedTextFieldAlteracaoCEP.setEnabled(false);
        jComboBoxAlteracaoUF.setEnabled(false);
        jTextFieldAlteracaoCidade.setEnabled(false);
        jTextFieldAlteracaoBairro.setEnabled(false);
           
        jTextFieldAlteracaoRua.setEnabled(false);
        jTextFieldAlteracaoNumero.setEnabled(false);
        jTextFieldAlteracaoComplemento.setEnabled(false);
            
        jTextFieldAlteracaoEmail.setEnabled(false);
        jFormattedTextFieldAlteracaoRenda.setEnabled(false);
        jFormattedTextFieldAlteracaoLimite.setEnabled(false);
        jTextFieldAlteracaoTelefone.setEnabled(false);
            
        jTextFieldAlteracaoMae.setEnabled(false);
        jTextFieldAlteracaoPai.setEnabled(false);
    
    }
    
    private void condicaoFinal(){
        jTextFieldAlteracaoCPF.setEnabled(false);
        jTextFieldAlteracaoNome.setEnabled(true);
     
        jRadioButtonAlteracaoMasculino.setEnabled(true);

        jRadioButtonAlteracaoFeminino.setEnabled(true);

        jFormattedTextFieldAlteracaoDataNasc.setEnabled(true);
        jTextFieldAlteracaoRG.setEnabled(true);
        jComboBoxAlteracaoOrgao.setEnabled(true);
        jCheckBoxAlteracaoPermite.setEnabled(true);
            
        jFormattedTextFieldAlteracaoCEP.setEnabled(true);
        jComboBoxAlteracaoUF.setEnabled(true);
        jTextFieldAlteracaoCidade.setEnabled(true);
        jTextFieldAlteracaoBairro.setEnabled(true);
           
        jTextFieldAlteracaoRua.setEnabled(true);
        jTextFieldAlteracaoNumero.setEnabled(true);
        jTextFieldAlteracaoComplemento.setEnabled(true);
            
        jTextFieldAlteracaoEmail.setEnabled(true);
        jFormattedTextFieldAlteracaoRenda.setEnabled(true);
        jFormattedTextFieldAlteracaoLimite.setEnabled(true);
        jTextFieldAlteracaoTelefone.setEnabled(true);
            
        jTextFieldAlteracaoMae.setEnabled(true);
        jTextFieldAlteracaoPai.setEnabled(true);
    
    }
    
    private void realizaUpdate(){
        try{
            if(jRadioButtonAlteracaoFisica.isSelected()){
                String cpf = jTextFieldAlteracaoCPF.getText();
                String nome = jTextFieldAlteracaoNome.getText();
            
                String sexo = " ";
                if(jRadioButtonAlteracaoMasculino.isSelected())
                    sexo = jRadioButtonAlteracaoMasculino.getText();
                if(jRadioButtonAlteracaoFeminino.isSelected())
                    sexo = jRadioButtonAlteracaoFeminino.getText();
            
                String dataNasc = jFormattedTextFieldAlteracaoDataNasc.getText();
                String rg = jTextFieldAlteracaoRG.getText();
                String orgao = String.valueOf(jComboBoxAlteracaoOrgao.getSelectedItem());
                boolean crediario = jCheckBoxAlteracaoPermite.isSelected();
            
                String cep = jFormattedTextFieldAlteracaoCEP.getText();  
                String uf = String.valueOf(jComboBoxAlteracaoUF.getSelectedItem());
                String cidade = jTextFieldAlteracaoCidade.getText();
                String bairro = jTextFieldAlteracaoBairro.getText();
           
                String rua = jTextFieldAlteracaoRua.getText();
                String numero = jTextFieldAlteracaoNumero.getText();
                String complemento = jTextFieldAlteracaoComplemento.getText();
            
                String email = jTextFieldAlteracaoEmail.getText();
                double renda = transformaDouble(jFormattedTextFieldAlteracaoRenda.getText());
                double limite = transformaDouble(jFormattedTextFieldAlteracaoLimite.getText());
                String telefone = jTextFieldAlteracaoTelefone.getText();
            
                String mae = jTextFieldAlteracaoMae.getText();
                String pai = jTextFieldAlteracaoPai.getText();
            
                ClienteFisico cliente = new ClienteFisico(cpf, sexo, rg, orgao, mae, pai, nome, dataNasc, crediario, 
                            cep, uf, rua,  numero, complemento, bairro, cidade,  email,renda,  limite,  telefone);
                ClienteDAO dao = new ClienteDAO();
                dao.updateFisico(cliente);

            }
            if(jRadioButtonAlteracaoJuridica.isSelected()){
                String cnpj = jTextFieldAlteracaoCPF.getText();
                String nome = jTextFieldAlteracaoNome.getText();
            
                String dataFund = jFormattedTextFieldAlteracaoDataNasc.getText();
                boolean crediario = jCheckBoxAlteracaoPermite.isSelected();
            
                String cep = jFormattedTextFieldAlteracaoCEP.getText();  
                String uf = String.valueOf(jComboBoxAlteracaoUF.getSelectedItem());
                String cidade = jTextFieldAlteracaoCidade.getText();
                String bairro = jTextFieldAlteracaoBairro.getText();
           
                String rua = jTextFieldAlteracaoRua.getText();
                String numero = jTextFieldAlteracaoNumero.getText();
                String complemento = jTextFieldAlteracaoComplemento.getText();
            
                String email = jTextFieldAlteracaoEmail.getText();
                double renda = transformaDouble(jFormattedTextFieldAlteracaoRenda.getText());
                double limite = transformaDouble(jFormattedTextFieldAlteracaoLimite.getText());
                String telefone = jTextFieldAlteracaoTelefone.getText();
      
                ClienteJuridico cliente = new ClienteJuridico( cnpj, nome, dataFund, crediario, 
                              cep, uf, rua, numero, complemento, bairro, cidade, email, renda, limite, telefone);
      
                ClienteDAO dao = new ClienteDAO();
                dao.updateJuridico(cliente);
            
                }   
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Verifique as informações");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Verifique as informações");
        }
    }
 
    private void pesquisa(){
       //NumFormException
        ClienteDAO dao  = new ClienteDAO();
        boolean achou = false;
        String pesquisado = jTextFieldAlteracaoCPF.getText();
        if(jRadioButtonAlteracaoFisica.isSelected()){
            for(ClienteFisico c : dao.readFisico()){
                if(c.getCpf().equals(pesquisado)){
                    condicaoFinal();
                    jTextFieldAlteracaoNome.setText(c.getNome());
                   
                    if(c.getSexo().equals("Masculino")){
                        jRadioButtonAlteracaoMasculino.setSelected(true);
                        jRadioButtonAlteracaoFeminino.setSelected(false);
                    }else{
                        jRadioButtonAlteracaoFeminino.setSelected(true);
                        jRadioButtonAlteracaoMasculino.setSelected(false);
                    }
                    jFormattedTextFieldAlteracaoDataNasc.setText(c.getDataNascFund());
                    jTextFieldAlteracaoRG.setText(c.getRg());
                    jComboBoxAlteracaoOrgao.setSelectedItem(c.getOrgaoExpeditor());
                    jCheckBoxAlteracaoPermite.setSelected(c.isPermitecredito());
            
                    jFormattedTextFieldAlteracaoCEP.setText(c.getCep());
                    jComboBoxAlteracaoUF.setSelectedItem(c.getUf());
                    jTextFieldAlteracaoCidade.setText(c.getCidade());
                    jTextFieldAlteracaoBairro.setText(c.getBairro());
           
                    jTextFieldAlteracaoRua.setText(c.getRua());
                    jTextFieldAlteracaoNumero.setText(c.getNumeroCasa());
                    jTextFieldAlteracaoComplemento.setText(c.getComplemento());
            
                    jTextFieldAlteracaoEmail.setText(c.getEmail());
                    jFormattedTextFieldAlteracaoRenda.setText(transformaReais(c.getRenda()));
                    jFormattedTextFieldAlteracaoLimite.setText(transformaReais(c.getLimiteCompra()));
                    jTextFieldAlteracaoTelefone.setText(c.getTelefone());
            
                    jTextFieldAlteracaoMae.setText(c.getMae());
                    jTextFieldAlteracaoPai.setText(c.getPai());
                    achou = true;
                }
            }
        }
        if(jRadioButtonAlteracaoJuridica.isSelected()){
            for(ClienteJuridico c : dao.readJuridico()){
                if(c.getCnpj().equals(pesquisado)){
                    condicaoFinal();
                    jTextFieldAlteracaoNome.setText(c.getNome());
   
                    jFormattedTextFieldAlteracaoDataNasc.setText(c.getDataNascFund());
                    jCheckBoxAlteracaoPermite.setSelected(c.isPermitecredito());
            
                    jFormattedTextFieldAlteracaoCEP.setText(c.getCep());
                    jComboBoxAlteracaoUF.setSelectedItem(c.getUf());
                    jTextFieldAlteracaoCidade.setText(c.getCidade());
                    jTextFieldAlteracaoBairro.setText(c.getBairro());
           
                    jTextFieldAlteracaoRua.setText(c.getRua());
                    jTextFieldAlteracaoNumero.setText(c.getNumeroCasa());
                    jTextFieldAlteracaoComplemento.setText(c.getComplemento());
            
                    jTextFieldAlteracaoEmail.setText(c.getEmail());
                    jFormattedTextFieldAlteracaoRenda.setText(String.valueOf(c.getRenda()));
                    jFormattedTextFieldAlteracaoLimite.setText(transformaReais(c.getLimiteCompra()));
                    jTextFieldAlteracaoTelefone.setText(c.getTelefone());
                    achou = true;

                }
            }
        }
        if(!achou){
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            limparCamposAltercao();
        }
   
    }
    
    private void inserirDados(){
        try{
            if(jRadioButtonInclusaoFisica.isSelected()){
                String cpf = jTextFieldInclusaoCPF.getText();
                String nome = jTextFieldInclusaoNome.getText();
            
                String sexo = " ";
                if(jRadioButtonInclusaoMasculino.isSelected())
                    sexo = jRadioButtonInclusaoMasculino.getText();
                if(jRadioButtonInclusaoFeminino.isSelected())
                    sexo = jRadioButtonInclusaoFeminino.getText();
            
                String dataNasc = jFormattedTextFieldInsercaoDataNasc.getText();
                String rg = jTextFieldInclusaoRG.getText();
                String orgao = String.valueOf(jComboBoxInclusaoOrgao.getSelectedItem());
                boolean crediario = jCheckBoxInclusaoPermite.isSelected();
            
                String cep = jFormattedTextFieldInclusaoCEP.getText();  
                String uf = String.valueOf(jComboBoxInclusaoUF.getSelectedItem());
                String cidade = jTextFieldInclusaoCidade.getText();
                String bairro = jTextFieldInclusaoBairro.getText();
           
                String rua = jTextFieldInclusaoRua.getText();
                String numero = jTextFieldInclusaoNumero.getText();
                String complemento = jTextFieldInclusaoComplemento.getText();
            
                String email = jTextFieldInclusaoEmail.getText();
                double renda = transformaDouble(jFormattedTextFieldInclusaoRenda.getText());
                double limite = transformaDouble(jFormattedTextFieldInclusaoLimite.getText());
                String telefone = jTextFieldInclusaoTelefone.getText();
            
            
                String mae = jTextFieldInclusaoMae.getText();
                String pai = jTextFieldInclusaoPai.getText();

            
                ClienteFisico cliente = new ClienteFisico(cpf, sexo, rg, orgao, mae, pai, nome, dataNasc, crediario, 
                   cep, uf, rua,  numero, complemento, bairro, cidade,  email,renda,  limite,  telefone);
            
            
                ClienteDAO dao = new ClienteDAO();
                dao.createFisico(cliente);
            }
            if(jRadioButtonInclusaoJuridica.isSelected()){
                String cnpj = jTextFieldInclusaoCPF.getText();
                String nome = jTextFieldInclusaoNome.getText();
            
                String dataFund = jFormattedTextFieldInsercaoDataNasc.getText();
                boolean crediario = jCheckBoxInclusaoPermite.isSelected();
            
                String cep = jFormattedTextFieldInclusaoCEP.getText();  
                String uf = String.valueOf(jComboBoxInclusaoUF.getSelectedItem());
                String cidade = jTextFieldInclusaoCidade.getText();
                String bairro = jTextFieldInclusaoBairro.getText();
           
                String rua = jTextFieldInclusaoRua.getText();
                String numero = jTextFieldInclusaoNumero.getText();
                String complemento = jTextFieldInclusaoComplemento.getText();
            
                String email = jTextFieldInclusaoEmail.getText();
                double renda = transformaDouble(jFormattedTextFieldInclusaoRenda.getText());
                double limite = transformaDouble(jFormattedTextFieldInclusaoLimite.getText());
                String telefone = jTextFieldInclusaoTelefone.getText();
            
                ClienteJuridico cliente = new ClienteJuridico( cnpj, nome, dataFund, crediario, 
                    cep, uf, rua, numero, complemento, bairro, cidade, email, renda, limite, telefone);
      
                ClienteDAO dao = new ClienteDAO();
                dao.createJuridico(cliente);
                
                limparCamposInclusao();
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Verifique as informações");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Verifique as informações");
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
                System.out.println(campoReal);
            }else
                break;
           
            colocaPonto -= 3;  
        }
        
        return campoReal;
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelIcon = new javax.swing.JLabel();
        jTabbedPaneInclusao = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldInclusaoPai = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jRadioButtonInclusaoFisica = new javax.swing.JRadioButton();
        jRadioButtonInclusaoJuridica = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldInclusaoNome = new javax.swing.JTextField();
        jLabelInclusaoNasc = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldInclusaoRua = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldInclusaoNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldInclusaoComplemento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldInclusaoBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxInclusaoUF = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabelInclusaoMae = new javax.swing.JLabel();
        jTextFieldInclusaoMae = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldInclusaoEmail = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButtonInclusaoSair = new javax.swing.JButton();
        jButtonInclusaoSalvar = new javax.swing.JButton();
        jRadioButtonInclusaoMasculino = new javax.swing.JRadioButton();
        jLabelInclusaoSexo = new javax.swing.JLabel();
        jRadioButtonInclusaoFeminino = new javax.swing.JRadioButton();
        jTextFieldInclusaoRG = new javax.swing.JTextField();
        jLabelInclusaoRg = new javax.swing.JLabel();
        jLabelInclusaoEmissorOrgao = new javax.swing.JLabel();
        jComboBoxInclusaoOrgao = new javax.swing.JComboBox<>();
        jCheckBoxInclusaoPermite = new javax.swing.JCheckBox();
        jTextFieldInclusaoCPF = new javax.swing.JTextField();
        jLabelInclusaoPai = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldInclusaoCidade = new javax.swing.JTextField();
        jFormattedTextFieldInclusaoCEP = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInsercaoDataNasc = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldInclusaoTelefone = new javax.swing.JTextField();
        jFormattedTextFieldInclusaoRenda = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInclusaoLimite = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAlteracaoPai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButtonAlteracaoFisica = new javax.swing.JRadioButton();
        jRadioButtonAlteracaoJuridica = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldAlteracaoNome = new javax.swing.JTextField();
        jLabelAlteracaoNasc = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldAlteracaoRua = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldAlteracaoNumero = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldAlteracaoComplemento = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldAlteracaoBairro = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jComboBoxAlteracaoUF = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabelAlteracaoMae = new javax.swing.JLabel();
        jTextFieldAlteracaoMae = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldAlteracaoEmail = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButtonAlteracaoSair = new javax.swing.JButton();
        jButtonAlteracaoSalvar = new javax.swing.JButton();
        jRadioButtonAlteracaoMasculino = new javax.swing.JRadioButton();
        jLabelAlteracaoSexo = new javax.swing.JLabel();
        jRadioButtonAlteracaoFeminino = new javax.swing.JRadioButton();
        jTextFieldAlteracaoRG = new javax.swing.JTextField();
        jLabelAlteracaoRg = new javax.swing.JLabel();
        jLabelAlteracaoOrgao = new javax.swing.JLabel();
        jComboBoxAlteracaoOrgao = new javax.swing.JComboBox<>();
        jCheckBoxAlteracaoPermite = new javax.swing.JCheckBox();
        jTextFieldAlteracaoCPF = new javax.swing.JTextField();
        jLabelAlteracaoPai = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jTextFieldAlteracaoCidade = new javax.swing.JTextField();
        jFormattedTextFieldAlteracaoDataNasc = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoCEP = new javax.swing.JFormattedTextField();
        jTextFieldAlteracaoTelefone = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextFieldAlteracaoRenda = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoLimite = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabelBallon = new javax.swing.JLabel();
        jLabelBalloonIcon = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabelTitulo.setFont(new java.awt.Font("Trebuchet MS", 1, 28)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(0, 153, 255));
        jLabelTitulo.setText("Cadastro Cliente");

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconCadastroCliente_80px.png"))); // NOI18N

        jTabbedPaneInclusao.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("CPF/CNPJ");

        jTextFieldInclusaoPai.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("PESSOA");

        jRadioButtonInclusaoFisica.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonInclusaoFisica.setSelected(true);
        jRadioButtonInclusaoFisica.setText("Física");
        jRadioButtonInclusaoFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInclusaoFisicaActionPerformed(evt);
            }
        });

        jRadioButtonInclusaoJuridica.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonInclusaoJuridica.setText("Jurídica");
        jRadioButtonInclusaoJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInclusaoJuridicaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("NOME/RAZÃO SOCIAL");

        jTextFieldInclusaoNome.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabelInclusaoNasc.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelInclusaoNasc.setText("DATA NASC/FUND");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("CEP");

        jTextFieldInclusaoRua.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("NÚMERO");

        jTextFieldInclusaoNumero.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel9.setText("COMPLEMENTO");

        jTextFieldInclusaoComplemento.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel10.setText("BAIRRO");

        jTextFieldInclusaoBairro.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setText("UF");

        jComboBoxInclusaoUF.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jComboBoxInclusaoUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("CIDADE");

        jLabelInclusaoMae.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelInclusaoMae.setText("NOME DA MÃE");

        jTextFieldInclusaoMae.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel14.setText("E-MAIL");

        jTextFieldInclusaoEmail.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel15.setText("RENDA");

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel16.setText("LIMITE COMPRA");

        jButtonInclusaoSair.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButtonInclusaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonInclusaoSair.setText("SAIR");

        jButtonInclusaoSalvar.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButtonInclusaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSalvar_40px.png"))); // NOI18N
        jButtonInclusaoSalvar.setText("SALVAR");
        jButtonInclusaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInclusaoSalvarActionPerformed(evt);
            }
        });

        jRadioButtonInclusaoMasculino.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonInclusaoMasculino.setSelected(true);
        jRadioButtonInclusaoMasculino.setText("Masculino");
        jRadioButtonInclusaoMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInclusaoMasculinoActionPerformed(evt);
            }
        });

        jLabelInclusaoSexo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelInclusaoSexo.setText("SEXO");

        jRadioButtonInclusaoFeminino.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonInclusaoFeminino.setText("Feminino");
        jRadioButtonInclusaoFeminino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInclusaoFemininoActionPerformed(evt);
            }
        });

        jTextFieldInclusaoRG.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabelInclusaoRg.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelInclusaoRg.setText("RG");

        jLabelInclusaoEmissorOrgao.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelInclusaoEmissorOrgao.setText("ORGÃO EXPEDITOR");

        jComboBoxInclusaoOrgao.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jComboBoxInclusaoOrgao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "ABNC", "CGPI/DUREX/DPF", "CGPI", "CGPMAF", "CNIG", "CNT", "COREN", "CORECON", "CRA", "CRAS", "CRB", "CRC", "CRE", "CREA", "CRECI", "CREFIT", "CRESS", "CRF", "CRM", "CRN", "CRO", "CRP", "CRPRE", "CRQ", "CRRC", "CRMV", "CSC", "CTPS", "DIC", "DIREX", "DPMAF", "DPT", "DST", "FGTS", "FIPE", "FLS", "GOVGO", "I CLA", "IFP", "IGP", "IICCECF/RO", "IIMG", "IML", "IPC", "IPF", "MAE", "MEX", "MMA", "OAB", "OMB", "PCMG", "PMMG", "POF ou DPF", "POM", "SDS", "SNJ", "SECC", "SEJUSP", "SES ou EST", "SESP", "SJS", "SJTC", "SJTS", "SPTC", "SSP" }));

        jCheckBoxInclusaoPermite.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jCheckBoxInclusaoPermite.setText("Permitir Crediário");

        jTextFieldInclusaoCPF.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabelInclusaoPai.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelInclusaoPai.setText("NOME DO PAI");

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel19.setText("RUA");

        jTextFieldInclusaoCidade.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        try {
            jFormattedTextFieldInclusaoCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldInclusaoCEP.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jFormattedTextFieldInclusaoCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoCEPKeyReleased(evt);
            }
        });

        try {
            jFormattedTextFieldInsercaoDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldInsercaoDataNasc.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel17.setText("TELEFONE");

        jTextFieldInclusaoTelefone.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jFormattedTextFieldInclusaoRenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jFormattedTextFieldInclusaoLimite.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jFormattedTextFieldInclusaoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxInclusaoUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jTextFieldInclusaoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldInclusaoBairro)
                                .addGap(260, 260, 260))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInclusaoMae)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldInclusaoPai, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldInclusaoEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel15)
                                        .addGap(131, 131, 131))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldInclusaoRenda, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jFormattedTextFieldInclusaoLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jTextFieldInclusaoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldInclusaoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(150, 150, 150)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonInclusaoFisica)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonInclusaoJuridica))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel2))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jTextFieldInclusaoRua, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldInclusaoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextFieldInclusaoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextFieldInclusaoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabelInclusaoNasc)
                                                .addGap(46, 46, 46))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jFormattedTextFieldInsercaoDataNasc)
                                                .addGap(30, 30, 30)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelInclusaoRg)
                                            .addComponent(jTextFieldInclusaoRG, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelInclusaoEmissorOrgao)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jComboBoxInclusaoOrgao, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jCheckBoxInclusaoPermite)))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonInclusaoMasculino)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonInclusaoFeminino))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelInclusaoSexo)
                                        .addGap(69, 69, 69))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelInclusaoPai)
                                    .addComponent(jTextFieldInclusaoMae, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonInclusaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonInclusaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)))
                        .addContainerGap(72, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldInclusaoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonInclusaoFisica)
                            .addComponent(jRadioButtonInclusaoJuridica))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldInclusaoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoSexo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButtonInclusaoMasculino)
                                    .addComponent(jRadioButtonInclusaoFeminino))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxInclusaoOrgao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBoxInclusaoPermite)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelInclusaoNasc)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelInclusaoRg)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldInclusaoRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextFieldInsercaoDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelInclusaoEmissorOrgao)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxInclusaoUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldInclusaoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldInclusaoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldInclusaoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldInclusaoRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldInclusaoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldInclusaoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldInclusaoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelInclusaoMae)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldInclusaoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldInclusaoMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelInclusaoPai))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonInclusaoSair)
                                .addComponent(jButtonInclusaoSalvar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldInclusaoPai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldInclusaoRenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldInclusaoLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0))
        );

        jTabbedPaneInclusao.addTab("INCLUSÃO", jPanel1);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel4.setText("CPF/CNPJ");

        jTextFieldAlteracaoPai.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("PESSOA");

        jRadioButtonAlteracaoFisica.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonAlteracaoFisica.setSelected(true);
        jRadioButtonAlteracaoFisica.setText("Física");
        jRadioButtonAlteracaoFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAlteracaoFisicaActionPerformed(evt);
            }
        });

        jRadioButtonAlteracaoJuridica.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonAlteracaoJuridica.setText("Jurídica");
        jRadioButtonAlteracaoJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAlteracaoJuridicaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel20.setText("NOME/RAZÃO SOCIAL");

        jTextFieldAlteracaoNome.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabelAlteracaoNasc.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelAlteracaoNasc.setText("DATA NASC/FUND");

        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel22.setText("CEP");

        jTextFieldAlteracaoRua.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel23.setText("NÚMERO");

        jTextFieldAlteracaoNumero.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel24.setText("COMPLEMENTO");

        jTextFieldAlteracaoComplemento.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel25.setText("BAIRRO");

        jTextFieldAlteracaoBairro.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel26.setText("UF");

        jComboBoxAlteracaoUF.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jComboBoxAlteracaoUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel27.setText("CIDADE");

        jLabelAlteracaoMae.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelAlteracaoMae.setText("NOME DA MÃE");

        jTextFieldAlteracaoMae.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel29.setText("E-MAIL");

        jTextFieldAlteracaoEmail.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel30.setText("RENDA");

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel31.setText("LIMITE COMPRA");

        jButtonAlteracaoSair.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButtonAlteracaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonAlteracaoSair.setText("SAIR");

        jButtonAlteracaoSalvar.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButtonAlteracaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSalvar_40px.png"))); // NOI18N
        jButtonAlteracaoSalvar.setText("SALVAR");
        jButtonAlteracaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlteracaoSalvarActionPerformed(evt);
            }
        });

        jRadioButtonAlteracaoMasculino.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonAlteracaoMasculino.setSelected(true);
        jRadioButtonAlteracaoMasculino.setText("Masculino");
        jRadioButtonAlteracaoMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAlteracaoMasculinoActionPerformed(evt);
            }
        });

        jLabelAlteracaoSexo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelAlteracaoSexo.setText("SEXO");

        jRadioButtonAlteracaoFeminino.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonAlteracaoFeminino.setText("Feminino");
        jRadioButtonAlteracaoFeminino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAlteracaoFemininoActionPerformed(evt);
            }
        });

        jTextFieldAlteracaoRG.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabelAlteracaoRg.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelAlteracaoRg.setText("RG");

        jLabelAlteracaoOrgao.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelAlteracaoOrgao.setText("ORGÃO EXPEDITOR");

        jComboBoxAlteracaoOrgao.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jComboBoxAlteracaoOrgao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "ABNC", "CGPI/DUREX/DPF", "CGPI", "CGPMAF", "CNIG", "CNT", "COREN", "CORECON", "CRA", "CRAS", "CRB", "CRC", "CRE", "CREA", "CRECI", "CREFIT", "CRESS", "CRF", "CRM", "CRN", "CRO", "CRP", "CRPRE", "CRQ", "CRRC", "CRMV", "CSC", "CTPS", "DIC", "DIREX", "DPMAF", "DPT", "DST", "FGTS", "FIPE", "FLS", "GOVGO", "I CLA", "IFP", "IGP", "IICCECF/RO", "IIMG", "IML", "IPC", "IPF", "MAE", "MEX", "MMA", "OAB", "OMB", "PCMG", "PMMG", "POF ou DPF", "POM", "SDS", "SNJ", "SECC", "SEJUSP", "SES ou EST", "SESP", "SJS", "SJTC", "SJTS", "SPTC", "SSP" }));

        jCheckBoxAlteracaoPermite.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jCheckBoxAlteracaoPermite.setText("Permitir Crediário");

        jTextFieldAlteracaoCPF.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jTextFieldAlteracaoCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCPFKeyPressed(evt);
            }
        });

        jLabelAlteracaoPai.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelAlteracaoPai.setText("NOME DO PAI");

        jLabel63.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel63.setText("RUA");

        jTextFieldAlteracaoCidade.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        try {
            jFormattedTextFieldAlteracaoDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldAlteracaoDataNasc.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        try {
            jFormattedTextFieldAlteracaoCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldAlteracaoCEP.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jFormattedTextFieldAlteracaoCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoCEPKeyReleased(evt);
            }
        });

        jTextFieldAlteracaoTelefone.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel32.setText("TELEFONE");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jFormattedTextFieldAlteracaoRenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jFormattedTextFieldAlteracaoLimite.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlteracaoPai)
                            .addComponent(jTextFieldAlteracaoMae, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAlteracaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAlteracaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jFormattedTextFieldAlteracaoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxAlteracaoUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jTextFieldAlteracaoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jTextFieldAlteracaoBairro)
                                .addGap(260, 260, 260))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlteracaoMae)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldAlteracaoPai, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldAlteracaoEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel30)
                                        .addGap(131, 131, 131))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldAlteracaoRenda, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(jFormattedTextFieldAlteracaoLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jTextFieldAlteracaoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jTextFieldAlteracaoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)))
                                .addGap(122, 122, 122)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonAlteracaoFisica)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonAlteracaoJuridica))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel7))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel63)
                                    .addComponent(jTextFieldAlteracaoRua, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldAlteracaoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jTextFieldAlteracaoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jTextFieldAlteracaoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabelAlteracaoNasc)
                                                .addGap(46, 46, 46))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jFormattedTextFieldAlteracaoDataNasc)
                                                .addGap(30, 30, 30)))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAlteracaoRg)
                                            .addComponent(jTextFieldAlteracaoRG, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAlteracaoOrgao)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jComboBoxAlteracaoOrgao, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jCheckBoxAlteracaoPermite)))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonAlteracaoMasculino)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonAlteracaoFeminino))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabelAlteracaoSexo)
                                        .addGap(69, 69, 69)))))
                        .addContainerGap(72, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAlteracaoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonAlteracaoFisica)
                            .addComponent(jRadioButtonAlteracaoJuridica))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAlteracaoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoSexo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButtonAlteracaoMasculino)
                                    .addComponent(jRadioButtonAlteracaoFeminino))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxAlteracaoOrgao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBoxAlteracaoPermite)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAlteracaoNasc)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabelAlteracaoRg)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldAlteracaoRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextFieldAlteracaoDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelAlteracaoOrgao)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAlteracaoUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAlteracaoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAlteracaoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldAlteracaoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAlteracaoRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAlteracaoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAlteracaoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAlteracaoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelAlteracaoMae)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAlteracaoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jTextFieldAlteracaoMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelAlteracaoPai))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonAlteracaoSair)
                                .addComponent(jButtonAlteracaoSalvar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAlteracaoPai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldAlteracaoRenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldAlteracaoLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPaneInclusao.addTab("ALTERAÇÃO", jPanel5);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jLabelBallon.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabelBallon.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBallon.setText("BALLOON");

        jLabelBalloonIcon.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelBalloonIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelBallon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBalloonIcon)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBalloonIcon)
                    .addComponent(jLabelBallon))
                .addContainerGap())
        );

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/closeWindow_30 (2).png"))); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jTabbedPaneInclusao, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitulo)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabelIcon))
                .addGap(0, 0, 0)
                .addComponent(jTabbedPaneInclusao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonInclusaoFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInclusaoFisicaActionPerformed
        if(jRadioButtonInclusaoJuridica.isSelected()){
            jRadioButtonInclusaoJuridica.setSelected(false);
        }

        jRadioButtonInclusaoFisica.setSelected(true);

        jTextFieldInclusaoRG.setEnabled(true);
        jLabelInclusaoRg.setEnabled(true);

        jComboBoxInclusaoOrgao.setEnabled(true);
        jLabelInclusaoEmissorOrgao.setEnabled(true);

        jLabelInclusaoSexo.setEnabled(true);
        jRadioButtonInclusaoMasculino.setEnabled(true);
        jRadioButtonInclusaoFeminino.setEnabled(true);

        jLabelInclusaoMae.setEnabled(true);
        jTextFieldInclusaoMae.setEnabled(true);

        jLabelInclusaoPai.setEnabled(true);
        jTextFieldInclusaoPai.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonInclusaoFisicaActionPerformed

    private void jRadioButtonInclusaoJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInclusaoJuridicaActionPerformed
        if(jRadioButtonInclusaoFisica.isSelected()){
            jRadioButtonInclusaoFisica.setSelected(false);

        }

        jRadioButtonInclusaoJuridica.setSelected(true);

        jTextFieldInclusaoRG.setEnabled(false);
        jLabelInclusaoRg.setEnabled(false);

        jComboBoxInclusaoOrgao.setEnabled(false);
        jLabelInclusaoEmissorOrgao.setEnabled(false);

        jLabelInclusaoSexo.setEnabled(false);
        jRadioButtonInclusaoMasculino.setEnabled(false);
        jRadioButtonInclusaoFeminino.setEnabled(false);

        jLabelInclusaoMae.setEnabled(false);
        jTextFieldInclusaoMae.setEnabled(false);

        jLabelInclusaoPai.setEnabled(false);
        jTextFieldInclusaoPai.setEnabled(false);

    }//GEN-LAST:event_jRadioButtonInclusaoJuridicaActionPerformed

    private void jButtonInclusaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInclusaoSalvarActionPerformed
        inserirDados();
        

    }//GEN-LAST:event_jButtonInclusaoSalvarActionPerformed

    private void jRadioButtonInclusaoMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInclusaoMasculinoActionPerformed
        if(jRadioButtonInclusaoFeminino.isSelected()){
            jRadioButtonInclusaoFeminino.setSelected(false);
        }

        jRadioButtonInclusaoMasculino.setSelected(true);
    }//GEN-LAST:event_jRadioButtonInclusaoMasculinoActionPerformed

    private void jRadioButtonInclusaoFemininoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInclusaoFemininoActionPerformed
        if(jRadioButtonInclusaoMasculino.isSelected()){
            jRadioButtonInclusaoMasculino.setSelected(false);
        }

        jRadioButtonInclusaoFeminino.setSelected(true);
    }//GEN-LAST:event_jRadioButtonInclusaoFemininoActionPerformed

    private void jFormattedTextFieldInclusaoCEPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoCEPKeyReleased
        // Verifica CEP e completa os Demais campos
        String cp = jFormattedTextFieldInclusaoCEP.getText();
        cp = cp.replaceAll("\\D*", ""); //ignora qualquer coisa que não seja numero.
        int cont = cp.length();

        if(cont == 8){
            try{
                correio(evt);
            }
            catch(Error e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldInclusaoCEPKeyReleased

    private void jRadioButtonAlteracaoFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAlteracaoFisicaActionPerformed
        if(jRadioButtonAlteracaoJuridica.isSelected()){
            jRadioButtonAlteracaoJuridica.setSelected(false);
            limparCamposAltercao();
            condicaoInicial();
        }

        jRadioButtonAlteracaoFisica.setSelected(true);

        jTextFieldAlteracaoRG.setEnabled(true);
        jLabelInclusaoRg.setEnabled(true);

        jComboBoxAlteracaoOrgao.setEnabled(true);
        jLabelAlteracaoOrgao.setEnabled(true);

        jLabelAlteracaoSexo.setEnabled(true);
        jRadioButtonAlteracaoMasculino.setEnabled(true);
        jRadioButtonAlteracaoFeminino.setEnabled(true);

        jLabelAlteracaoMae.setEnabled(true);
        jTextFieldAlteracaoMae.setEnabled(true);

        jLabelAlteracaoPai.setEnabled(true);
        jTextFieldAlteracaoPai.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonAlteracaoFisicaActionPerformed

    private void jRadioButtonAlteracaoJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAlteracaoJuridicaActionPerformed
        if(jRadioButtonAlteracaoFisica.isSelected()){
            jRadioButtonAlteracaoFisica.setSelected(false);
            limparCamposAltercao();
            condicaoInicial();
        }

        jRadioButtonAlteracaoJuridica.setSelected(true);

        jTextFieldAlteracaoRG.setEnabled(false);
        jLabelInclusaoRg.setEnabled(false);

        jComboBoxAlteracaoOrgao.setEnabled(false);
        jLabelAlteracaoOrgao.setEnabled(false);

        jLabelAlteracaoSexo.setEnabled(false);
        jRadioButtonAlteracaoMasculino.setEnabled(false);
        jRadioButtonAlteracaoFeminino.setEnabled(false);

        jLabelAlteracaoMae.setEnabled(false);
        jTextFieldAlteracaoMae.setEnabled(false);

        jLabelAlteracaoPai.setEnabled(false);
        jTextFieldAlteracaoPai.setEnabled(false);
    }//GEN-LAST:event_jRadioButtonAlteracaoJuridicaActionPerformed

    private void jButtonAlteracaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlteracaoSalvarActionPerformed
        realizaUpdate();
        condicaoInicial();
    }//GEN-LAST:event_jButtonAlteracaoSalvarActionPerformed

    private void jRadioButtonAlteracaoMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAlteracaoMasculinoActionPerformed
        if(jRadioButtonAlteracaoFeminino.isSelected()){
            jRadioButtonAlteracaoFeminino.setSelected(false);
        }

        jRadioButtonAlteracaoMasculino.setSelected(true);
    }//GEN-LAST:event_jRadioButtonAlteracaoMasculinoActionPerformed

    private void jRadioButtonAlteracaoFemininoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAlteracaoFemininoActionPerformed
        if(jRadioButtonAlteracaoMasculino.isSelected()){
            jRadioButtonAlteracaoMasculino.setSelected(false);
        }

        jRadioButtonAlteracaoFeminino.setSelected(true);
    }//GEN-LAST:event_jRadioButtonAlteracaoFemininoActionPerformed

    private void jTextFieldAlteracaoCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCPFKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            pesquisa();
        }
    }//GEN-LAST:event_jTextFieldAlteracaoCPFKeyPressed

    private void jFormattedTextFieldAlteracaoCEPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoCEPKeyReleased
        // Verifica CEP e completa os Demais campos
        String cp = jFormattedTextFieldInclusaoCEP.getText();
        cp = cp.replaceAll("\\D*", ""); //ignora qualquer coisa que não seja numero.
        int cont = cp.length();

        if(cont == 8){
            try{
                correio(evt);
            }
            catch(Error e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoCEPKeyReleased

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        pesquisa();
        condicaoInicial();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel28MouseClicked

     public  void correio(KeyEvent evt) {
        if(evt.getSource() == jFormattedTextFieldInclusaoCEP){
            String cep =  jFormattedTextFieldInclusaoCEP.getText();
		
            WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

            if (webServiceCep.wasSuccessful()) {
                       
                jTextFieldInclusaoRua.setText(webServiceCep.getLogradouroFull()); 
                jTextFieldInclusaoBairro.setText(webServiceCep.getBairro());                        
                jTextFieldInclusaoCidade.setText(webServiceCep.getCidade());
                jComboBoxInclusaoUF.setSelectedItem( webServiceCep.getUf());
            }		
        }
        if(evt.getSource() == jFormattedTextFieldAlteracaoCEP){
            String cep =  jFormattedTextFieldAlteracaoCEP.getText();
		
            WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

            if (webServiceCep.wasSuccessful()) {
                       
                jTextFieldAlteracaoRua.setText(webServiceCep.getLogradouroFull()); 
                jTextFieldAlteracaoBairro.setText(webServiceCep.getBairro());                        
                jTextFieldAlteracaoCidade.setText(webServiceCep.getCidade());
                jComboBoxAlteracaoUF.setSelectedItem( webServiceCep.getUf());
            }	
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
            java.util.logging.Logger.getLogger(JanelaCadastroClienteNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroClienteNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroClienteNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroClienteNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaCadastroClienteNova dialog = new JanelaCadastroClienteNova(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonAlteracaoSair;
    private javax.swing.JButton jButtonAlteracaoSalvar;
    private javax.swing.JButton jButtonInclusaoSair;
    private javax.swing.JButton jButtonInclusaoSalvar;
    private javax.swing.JCheckBox jCheckBoxAlteracaoPermite;
    private javax.swing.JCheckBox jCheckBoxInclusaoPermite;
    private javax.swing.JComboBox<String> jComboBoxAlteracaoOrgao;
    private javax.swing.JComboBox<String> jComboBoxAlteracaoUF;
    private javax.swing.JComboBox<String> jComboBoxInclusaoOrgao;
    private javax.swing.JComboBox<String> jComboBoxInclusaoUF;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoCEP;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoDataNasc;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoLimite;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoRenda;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoCEP;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoLimite;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoRenda;
    private javax.swing.JFormattedTextField jFormattedTextFieldInsercaoDataNasc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAlteracaoMae;
    private javax.swing.JLabel jLabelAlteracaoNasc;
    private javax.swing.JLabel jLabelAlteracaoOrgao;
    private javax.swing.JLabel jLabelAlteracaoPai;
    private javax.swing.JLabel jLabelAlteracaoRg;
    private javax.swing.JLabel jLabelAlteracaoSexo;
    private javax.swing.JLabel jLabelBallon;
    private javax.swing.JLabel jLabelBalloonIcon;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelInclusaoEmissorOrgao;
    private javax.swing.JLabel jLabelInclusaoMae;
    private javax.swing.JLabel jLabelInclusaoNasc;
    private javax.swing.JLabel jLabelInclusaoPai;
    private javax.swing.JLabel jLabelInclusaoRg;
    private javax.swing.JLabel jLabelInclusaoSexo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButtonAlteracaoFeminino;
    private javax.swing.JRadioButton jRadioButtonAlteracaoFisica;
    private javax.swing.JRadioButton jRadioButtonAlteracaoJuridica;
    private javax.swing.JRadioButton jRadioButtonAlteracaoMasculino;
    private javax.swing.JRadioButton jRadioButtonInclusaoFeminino;
    private javax.swing.JRadioButton jRadioButtonInclusaoFisica;
    private javax.swing.JRadioButton jRadioButtonInclusaoJuridica;
    private javax.swing.JRadioButton jRadioButtonInclusaoMasculino;
    private javax.swing.JTabbedPane jTabbedPaneInclusao;
    private javax.swing.JTextField jTextFieldAlteracaoBairro;
    private javax.swing.JTextField jTextFieldAlteracaoCPF;
    private javax.swing.JTextField jTextFieldAlteracaoCidade;
    private javax.swing.JTextField jTextFieldAlteracaoComplemento;
    private javax.swing.JTextField jTextFieldAlteracaoEmail;
    private javax.swing.JTextField jTextFieldAlteracaoMae;
    private javax.swing.JTextField jTextFieldAlteracaoNome;
    private javax.swing.JTextField jTextFieldAlteracaoNumero;
    private javax.swing.JTextField jTextFieldAlteracaoPai;
    private javax.swing.JTextField jTextFieldAlteracaoRG;
    private javax.swing.JTextField jTextFieldAlteracaoRua;
    private javax.swing.JTextField jTextFieldAlteracaoTelefone;
    private javax.swing.JTextField jTextFieldInclusaoBairro;
    private javax.swing.JTextField jTextFieldInclusaoCPF;
    private javax.swing.JTextField jTextFieldInclusaoCidade;
    private javax.swing.JTextField jTextFieldInclusaoComplemento;
    private javax.swing.JTextField jTextFieldInclusaoEmail;
    private javax.swing.JTextField jTextFieldInclusaoMae;
    private javax.swing.JTextField jTextFieldInclusaoNome;
    private javax.swing.JTextField jTextFieldInclusaoNumero;
    private javax.swing.JTextField jTextFieldInclusaoPai;
    private javax.swing.JTextField jTextFieldInclusaoRG;
    private javax.swing.JTextField jTextFieldInclusaoRua;
    private javax.swing.JTextField jTextFieldInclusaoTelefone;
    // End of variables declaration//GEN-END:variables
}
