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
import model.bean.Fornecedor;
import model.dao.FornecedorDAO;
import utilitarios.WebServiceCep;

/**
 *
 * @author gabri
 */
public class JanelaCadastroFornecedorNova extends javax.swing.JDialog {

    /**
     * Creates new form JanelaCadastroFornecedorNova
     */
    public JanelaCadastroFornecedorNova(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jTextFieldCodigo.setEnabled(false);
        condicaoInicial();
        pesquisa();
     //   setExtendedState(JFrame.MAXIMIZED_BOTH); 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    
    private void condicaoInicial(){
        jTextFieldCodigo1.setEnabled(true);
        jTextFieldCNPJ1.setEnabled(false);
        jTextFieldNome1.setEnabled(false);
        jTextFieldRazaoSocial1.setEnabled(false);
            
        jFormattedTextFieldInclusaoCEP1.setEnabled(false);
        jComboBoxUF1.setEnabled(false);
        jTextFieldCidade1.setEnabled(false);
            
        jTextFieldBairro1.setEnabled(false);
        jTextFieldRua1.setEnabled(false);
        jTextFieldCodigoNumero1.setEnabled(false);
        jTextFieldCodigoComplemento1.setEnabled(false);
            
        jTextFieldEmail1.setEnabled(false);
        jTextFieldFixo1.setEnabled(false);
        jTextFieldCelular1.setEnabled(false);

    }
    
    private void condicaoFinal(){
        jTextFieldCodigo1.setEnabled(false);
        jTextFieldCNPJ1.setEnabled(true);
        jTextFieldNome1.setEnabled(true);
        jTextFieldRazaoSocial1.setEnabled(true);
            
        jFormattedTextFieldInclusaoCEP1.setEnabled(true);
        jComboBoxUF1.setEnabled(true);
        jTextFieldCidade1.setEnabled(true);
            
        jTextFieldBairro1.setEnabled(true);
        jTextFieldRua1.setEnabled(true);
        jTextFieldCodigoNumero1.setEnabled(true);
        jTextFieldCodigoComplemento1.setEnabled(true);
            
        jTextFieldEmail1.setEnabled(true);
        jTextFieldFixo1.setEnabled(true);
        jTextFieldCelular1.setEnabled(true);

    }
    
    private void pesquisa(){
        if(jRadioButtonFisica.isSelected()){
            FornecedorDAO dao = new FornecedorDAO();
            int i = 0;
            for(Fornecedor f : dao.read()){
                if(f.getCodigo() > i)
                    i = f.getCodigo();
            }
            i++;
            jTextFieldCodigo.setText(String.valueOf(i));
        }
    }
    
    private void limparCampos(){
        jTextFieldCNPJ1.setText("");
        jTextFieldNome1.setText("");
        jTextFieldRazaoSocial1.setText("");
            
        jFormattedTextFieldInclusaoCEP1.setText("");
        jComboBoxUF1.setSelectedIndex(0);
        jTextFieldCidade1.setText("");
            
        jTextFieldBairro1.setText("");
        jTextFieldRua1.setText("");
        jTextFieldCodigoNumero1.setText("");
        jTextFieldCodigoComplemento1.setText("");
            
        jTextFieldEmail1.setText("");
        jTextFieldFixo1.setText("");
        jTextFieldCelular1.setText("");
    }
    
    private void limparCamposInsercao(){
        jTextFieldCNPJ.setText("");
        jTextFieldNome.setText("");
        jTextFieldRazaoSocial.setText("");
            
        jFormattedTextFieldInclusaoCEP.setText("");
        jComboBoxUF.setSelectedIndex(0);
        jTextFieldCidade.setText("");
            
        jTextFieldBairro.setText("");
        jTextFieldRua.setText("");
        jTextFieldCodigoNumero.setText("");
        jTextFieldCodigoComplemento.setText("");
            
        jTextFieldEmail.setText("");
        jTextFieldFixo.setText("");
        jTextFieldCelular.setText("");
    }
    
    private void resultadoPesquisa(){
        boolean achou = false; 
            FornecedorDAO dao = new FornecedorDAO();
            int i = Integer.parseInt(jTextFieldCodigo1.getText());
            
            for(Fornecedor f : dao.read()){
                if(f.getCodigo() == i){
                    jTextFieldCNPJ1.setText(f.getCnpj());
                    if(!(f.getCnpj().equals("")) && !(f.getCnpj().equals("INATIVO")))
                        jCheckBoxInclusaoInativo1.setSelected(false);
                    else
                        jCheckBoxInclusaoInativo1.setSelected(true);
                    
                    jTextFieldNome1.setText(f.getNomeInstituicao());
                    jTextFieldRazaoSocial1.setText(f.getRazaoSocial());
                    
                    if(f.getTipo().equals("Física")){
                        jRadioButtonJuridica1.setSelected(false);
                        jRadioButtonFisica1.setSelected(true);
                    }else{
                        jRadioButtonFisica1.setSelected(false);
                        jRadioButtonJuridica1.setSelected(true);
                    }
                    jFormattedTextFieldInclusaoCEP1.setText(f.getCep());
                    jComboBoxUF1.setSelectedItem(f.getUf());
                    jTextFieldCidade1.setText(f.getCidade());
            
                    jTextFieldBairro1.setText(f.getBairro());
                    jTextFieldRua1.setText(f.getRua());
                    jTextFieldCodigoNumero1.setText(f.getNumero());
                    jTextFieldCodigoComplemento1.setText(f.getComplemento());
            
                    jTextFieldEmail1.setText(f.getEmail());
                    jTextFieldFixo1.setText(f.getFixo());
                    jTextFieldCelular1.setText(f.getCelular());
                    achou = true;
                }
            }    
            if(!achou){
                JOptionPane.showMessageDialog(null,"Fornecedor não Encontrado");
                limparCampos();
                condicaoInicial();
            }
        }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTabbedPaneInclusao = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jRadioButtonFisica = new javax.swing.JRadioButton();
        jRadioButtonJuridica = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldRazaoSocial = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldRua = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCodigoNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCodigoComplemento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxUF = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabelInclusaoCNPJ = new javax.swing.JLabel();
        jTextFieldCNPJ = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jCheckBoxInclusaoInativo = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldFixo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldCelular = new javax.swing.JTextField();
        jButtonSair = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jTextFieldCidade = new javax.swing.JTextField();
        jFormattedTextFieldInclusaoCEP = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldCodigo1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jRadioButtonFisica1 = new javax.swing.JRadioButton();
        jRadioButtonJuridica1 = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        jTextFieldRazaoSocial1 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldNome1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextFieldRua1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextFieldCodigoNumero1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextFieldCodigoComplemento1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextFieldBairro1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jComboBoxUF1 = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabelInclusaoCNPJ1 = new javax.swing.JLabel();
        jTextFieldCNPJ1 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextFieldEmail1 = new javax.swing.JTextField();
        jCheckBoxInclusaoInativo1 = new javax.swing.JCheckBox();
        jLabel45 = new javax.swing.JLabel();
        jTextFieldFixo1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jTextFieldCelular1 = new javax.swing.JTextField();
        jButtonSair1 = new javax.swing.JButton();
        jButtonSalvar1 = new javax.swing.JButton();
        jTextFieldCidade1 = new javax.swing.JTextField();
        jFormattedTextFieldInclusaoCEP1 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jLabelTitulo.setFont(new java.awt.Font("Trebuchet MS", 1, 28)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(0, 153, 255));
        jLabelTitulo.setText("Cadastro de Fornecedor");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsFornecedor_80px_1.png"))); // NOI18N

        jTabbedPaneInclusao.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("CÓDIGO");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("PESSOA");

        jRadioButtonFisica.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonFisica.setSelected(true);
        jRadioButtonFisica.setText("Física");
        jRadioButtonFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFisicaActionPerformed(evt);
            }
        });

        jRadioButtonJuridica.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonJuridica.setText("Jurídica");
        jRadioButtonJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonJuridicaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("RAZÃO SOCIAL");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("NOME INSTITUIÇÃO");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("CEP");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("CIDADE");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("NÚMERO");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel9.setText("COMPLEMENTO");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel10.setText("BAIRRO");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setText("UF");

        jComboBoxUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("RUA");

        jLabelInclusaoCNPJ.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelInclusaoCNPJ.setText("CNPJ");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel14.setText("E-MAIL");

        jCheckBoxInclusaoInativo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jCheckBoxInclusaoInativo.setForeground(new java.awt.Color(255, 0, 51));
        jCheckBoxInclusaoInativo.setText("INATIVO");
        jCheckBoxInclusaoInativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxInclusaoInativoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel15.setText("TELEFONE (FIXO)");

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel16.setText("TELEFONE (CELULAR)");

        jButtonSair.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonSair.setText("SAIR");

        jButtonSalvar.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSalvar_40px.png"))); // NOI18N
        jButtonSalvar.setText("SALVAR");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jRadioButtonFisica)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonJuridica))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBoxInclusaoInativo))
                                    .addComponent(jLabelInclusaoCNPJ))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
                            .addComponent(jTextFieldNome))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldCidade)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jFormattedTextFieldInclusaoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jComboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel9))
                            .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldCodigoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCodigoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonFisica)
                    .addComponent(jRadioButtonJuridica))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldInclusaoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigoComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelInclusaoCNPJ)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxInclusaoInativo)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSair)
                    .addComponent(jButtonSalvar))
                .addContainerGap())
        );

        jTabbedPaneInclusao.addTab("INCLUSÃO", jPanel1);

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel13.setText("CÓDIGO");

        jTextFieldCodigo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCodigo1KeyPressed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel34.setText("PESSOA");

        jRadioButtonFisica1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonFisica1.setSelected(true);
        jRadioButtonFisica1.setText("Física");
        jRadioButtonFisica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFisica1ActionPerformed(evt);
            }
        });

        jRadioButtonJuridica1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jRadioButtonJuridica1.setText("Jurídica");
        jRadioButtonJuridica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonJuridica1ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel35.setText("RAZÃO SOCIAL");

        jLabel36.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel36.setText("NOME INSTITUIÇÃO");

        jLabel37.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel37.setText("CEP");

        jLabel38.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel38.setText("CIDADE");

        jLabel39.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel39.setText("NÚMERO");

        jLabel40.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel40.setText("COMPLEMENTO");

        jLabel41.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel41.setText("BAIRRO");

        jLabel42.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel42.setText("UF");

        jComboBoxUF1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));

        jLabel43.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel43.setText("RUA");

        jLabelInclusaoCNPJ1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabelInclusaoCNPJ1.setText("CNPJ");

        jLabel44.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel44.setText("E-MAIL");

        jCheckBoxInclusaoInativo1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jCheckBoxInclusaoInativo1.setForeground(new java.awt.Color(255, 0, 51));
        jCheckBoxInclusaoInativo1.setText("INATIVO");
        jCheckBoxInclusaoInativo1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxInclusaoInativo1ItemStateChanged(evt);
            }
        });
        jCheckBoxInclusaoInativo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxInclusaoInativo1MouseClicked(evt);
            }
        });
        jCheckBoxInclusaoInativo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxInclusaoInativo1ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel45.setText("TELEFONE (FIXO)");

        jLabel46.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel46.setText("TELEFONE (CELULAR)");

        jButtonSair1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButtonSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonSair1.setText("SAIR");

        jButtonSalvar1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButtonSalvar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSalvar_40px.png"))); // NOI18N
        jButtonSalvar1.setText("SALVAR");
        jButtonSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvar1ActionPerformed(evt);
            }
        });

        try {
            jFormattedTextFieldInclusaoCEP1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldInclusaoCEP1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jFormattedTextFieldInclusaoCEP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoCEP1KeyReleased(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(27, 27, 27)
                        .addComponent(jRadioButtonFisica1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonJuridica1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel34))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel35))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel36))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButtonSair1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextFieldCNPJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBoxInclusaoInativo1))
                                    .addComponent(jLabelInclusaoCNPJ1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(jTextFieldEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldFixo1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCelular1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldRazaoSocial1, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
                            .addComponent(jTextFieldNome1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldCidade1)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel37)
                                .addComponent(jFormattedTextFieldInclusaoCEP1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel42)
                                .addComponent(jComboBoxUF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldRua1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel38))))
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel40))
                            .addComponent(jTextFieldBairro1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldCodigoNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCodigoComplemento1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel41))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel34)
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonFisica1)
                    .addComponent(jRadioButtonJuridica1)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRazaoSocial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxUF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBairro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldInclusaoCEP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigoComplemento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigoNumero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelInclusaoCNPJ1)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCNPJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxInclusaoInativo1)
                            .addComponent(jTextFieldEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFixo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCelular1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSair1)
                    .addComponent(jButtonSalvar1))
                .addContainerGap())
        );

        jTabbedPaneInclusao.addTab("ALTERAÇÃO", jPanel3);

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconBalloon_80px.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("BALLOON");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jTabbedPaneInclusao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1123, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitulo)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20))
                .addGap(0, 0, 0)
                .addComponent(jTabbedPaneInclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFisicaActionPerformed
        if(jRadioButtonJuridica.isSelected()){
            jRadioButtonJuridica.setSelected(false);
        }

        jRadioButtonFisica.setSelected(true);
        jCheckBoxInclusaoInativo.setEnabled(false);
        jLabelInclusaoCNPJ.setText("CPF");
        pesquisa();
    }//GEN-LAST:event_jRadioButtonFisicaActionPerformed

    private void jRadioButtonJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonJuridicaActionPerformed
        if(jRadioButtonFisica.isSelected()){
            jRadioButtonFisica.setSelected(false);
        }

        jRadioButtonJuridica.setSelected(true);
        jCheckBoxInclusaoInativo.setEnabled(true);
        jLabelInclusaoCNPJ.setText("CNPJ");
        pesquisa();
    }//GEN-LAST:event_jRadioButtonJuridicaActionPerformed

    private void jCheckBoxInclusaoInativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxInclusaoInativoActionPerformed
        if(jCheckBoxInclusaoInativo.isSelected())
        jTextFieldCNPJ.setEnabled(false);
        else
        jTextFieldCNPJ.setEnabled(true);
    }//GEN-LAST:event_jCheckBoxInclusaoInativoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if(!(jTextFieldCodigo.getText().equals("")) &&
            !(jTextFieldNome.getText().equals("")) &&
            !(jTextFieldRazaoSocial.getText().equals("")) &&
            !(jFormattedTextFieldInclusaoCEP.getText().equals("")) &&
            !(jTextFieldCidade.getText().equals("")) &&
            !(jTextFieldBairro.getText().equals("")) &&
            !(jTextFieldRua.getText().equals("")) &&
            !(jTextFieldCodigoNumero.getText().equals("")) &&
            !(jTextFieldCodigoComplemento.getText().equals("")) &&
            !(jTextFieldEmail.getText().equals("")) &&
            !(jTextFieldFixo.getText().equals("")) &&
            !(jTextFieldCelular.getText().equals(""))){

            int codigo = Integer.parseInt(jTextFieldCodigo.getText());
            String nome = jTextFieldNome.getText();
            String razao = jTextFieldRazaoSocial.getText();
            String cnpj = jTextFieldCNPJ.getText();
            if(jCheckBoxInclusaoInativo.isSelected())
            cnpj = "INATIVO";

            String cep = jFormattedTextFieldInclusaoCEP.getText();
            String uf = String.valueOf(jComboBoxUF.getSelectedItem());
            String cidade = jTextFieldCidade.getText();

            String bairro = jTextFieldBairro.getText();
            String rua = jTextFieldRua.getText();
            String numero = jTextFieldCodigoNumero.getText();
            String complemento = jTextFieldCodigoComplemento.getText();

            String email = jTextFieldEmail.getText();
            String fixo = jTextFieldFixo.getText();
            String celular = jTextFieldCelular.getText();
            String tipo = "";
            if(jRadioButtonFisica.isSelected())
            tipo = jRadioButtonFisica.getText();
            if(jRadioButtonJuridica.isSelected())
            tipo = jRadioButtonJuridica.getText();

            Fornecedor fornecedor = new Fornecedor( codigo, razao,  nome,  cep,
                uf,  cidade,  bairro,  rua,  numero,  complemento,
                email,  fixo,  celular,  cnpj,  tipo);

            FornecedorDAO dao = new FornecedorDAO();
            dao.create(fornecedor);

            pesquisa();
            limparCamposInsercao();

        }else{
            JOptionPane.showMessageDialog(null, "Informe valores a todos os campos");
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

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

    private void jTextFieldCodigo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigo1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            condicaoFinal();
            resultadoPesquisa();
        }
    }//GEN-LAST:event_jTextFieldCodigo1KeyPressed

    private void jRadioButtonFisica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFisica1ActionPerformed
        if(jRadioButtonJuridica1.isSelected()){
            jRadioButtonJuridica1.setSelected(false);
        }

        jRadioButtonFisica1.setSelected(true);
        jCheckBoxInclusaoInativo1.setEnabled(false);
        jLabelInclusaoCNPJ1.setText("CPF");
    }//GEN-LAST:event_jRadioButtonFisica1ActionPerformed

    private void jRadioButtonJuridica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonJuridica1ActionPerformed
        if(jRadioButtonFisica1.isSelected()){
            jRadioButtonFisica1.setSelected(false);
        }

        jRadioButtonJuridica1.setSelected(true);
        jCheckBoxInclusaoInativo.setEnabled(true);
        jLabelInclusaoCNPJ1.setText("CNPJ");
    }//GEN-LAST:event_jRadioButtonJuridica1ActionPerformed

    private void jCheckBoxInclusaoInativo1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxInclusaoInativo1ItemStateChanged

    }//GEN-LAST:event_jCheckBoxInclusaoInativo1ItemStateChanged

    private void jCheckBoxInclusaoInativo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxInclusaoInativo1MouseClicked

    }//GEN-LAST:event_jCheckBoxInclusaoInativo1MouseClicked

    private void jCheckBoxInclusaoInativo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxInclusaoInativo1ActionPerformed
        if(jCheckBoxInclusaoInativo1.isSelected())
        jTextFieldCNPJ1.setEnabled(false);
        else
        jTextFieldCNPJ1.setEnabled(true);
    }//GEN-LAST:event_jCheckBoxInclusaoInativo1ActionPerformed

    private void jButtonSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvar1ActionPerformed
        if(!(jTextFieldCodigo1.getText().equals("")) &&
            !(jTextFieldNome1.getText().equals("")) &&
            !(jTextFieldRazaoSocial1.getText().equals("")) &&
            !(jFormattedTextFieldInclusaoCEP1.getText().equals("")) &&
            !(jTextFieldCidade1.getText().equals("")) &&
            !(jTextFieldBairro1.getText().equals("")) &&
            !(jTextFieldRua1.getText().equals("")) &&
            !(jTextFieldCodigoNumero1.getText().equals("")) &&
            !(jTextFieldCodigoComplemento1.getText().equals("")) &&
            !(jTextFieldEmail1.getText().equals("")) &&
            !(jTextFieldFixo1.getText().equals("")) &&
            !(jTextFieldCelular1.getText().equals(""))){

            int codigo = Integer.parseInt(jTextFieldCodigo1.getText());
            String nome = jTextFieldNome1.getText();
            String razao = jTextFieldRazaoSocial1.getText();
            String cnpj = jTextFieldCNPJ1.getText();
            if(jCheckBoxInclusaoInativo.isSelected())
            cnpj = "INATIVO";

            String cep = jFormattedTextFieldInclusaoCEP1.getText();
            String uf = String.valueOf(jComboBoxUF1.getSelectedItem());
            String cidade = jTextFieldCidade1.getText();

            String bairro = jTextFieldBairro1.getText();
            String rua = jTextFieldRua1.getText();
            String numero = jTextFieldCodigoNumero1.getText();
            String complemento = jTextFieldCodigoComplemento1.getText();

            String email = jTextFieldEmail1.getText();
            String fixo = jTextFieldFixo1.getText();
            String celular = jTextFieldCelular1.getText();
            String tipo = "";
            if(jRadioButtonFisica1.isSelected())
            tipo = jRadioButtonFisica1.getText();
            if(jRadioButtonJuridica1.isSelected())
            tipo = jRadioButtonJuridica1.getText();

            Fornecedor fornecedor = new Fornecedor( codigo, razao,  nome,  cep,
                uf,  cidade,  bairro,  rua,  numero,  complemento,
                email,  fixo,  celular,  cnpj,  tipo);

            FornecedorDAO dao = new FornecedorDAO();
            dao.update(fornecedor);

        }else
        JOptionPane.showMessageDialog(null, "Informe valores para todos os campos");
    }//GEN-LAST:event_jButtonSalvar1ActionPerformed

    private void jFormattedTextFieldInclusaoCEP1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoCEP1KeyReleased
        // Verifica CEP e completa os Demais campos
        String cp = jFormattedTextFieldInclusaoCEP1.getText();
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
    }//GEN-LAST:event_jFormattedTextFieldInclusaoCEP1KeyReleased

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        resultadoPesquisa();
        condicaoInicial();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    public  void correio(KeyEvent evt) {
        if(evt.getSource() == jFormattedTextFieldInclusaoCEP){
            String cep =  jFormattedTextFieldInclusaoCEP.getText();
		
            WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

            if (webServiceCep.wasSuccessful()) {
                       
                jTextFieldRua.setText(webServiceCep.getLogradouroFull()); 
                jTextFieldBairro.setText(webServiceCep.getBairro());                        
                jTextFieldCidade.setText(webServiceCep.getCidade());
                jComboBoxUF.setSelectedItem( webServiceCep.getUf());
            }		
        }
        if(evt.getSource() == jFormattedTextFieldInclusaoCEP1){
            String cep =  jFormattedTextFieldInclusaoCEP1.getText();
		
            WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

            if (webServiceCep.wasSuccessful()) {
                       
                jTextFieldRua1.setText(webServiceCep.getLogradouroFull()); 
                jTextFieldBairro1.setText(webServiceCep.getBairro());                        
                jTextFieldCidade1.setText(webServiceCep.getCidade());
                jComboBoxUF1.setSelectedItem( webServiceCep.getUf());
            }	
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroFornecedorNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroFornecedorNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroFornecedorNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCadastroFornecedorNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaCadastroFornecedorNova dialog = new JanelaCadastroFornecedorNova(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonSair1;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSalvar1;
    private javax.swing.JCheckBox jCheckBoxInclusaoInativo;
    private javax.swing.JCheckBox jCheckBoxInclusaoInativo1;
    private javax.swing.JComboBox<String> jComboBoxUF;
    private javax.swing.JComboBox<String> jComboBoxUF1;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoCEP;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoCEP1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelInclusaoCNPJ;
    private javax.swing.JLabel jLabelInclusaoCNPJ1;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButtonFisica;
    private javax.swing.JRadioButton jRadioButtonFisica1;
    private javax.swing.JRadioButton jRadioButtonJuridica;
    private javax.swing.JRadioButton jRadioButtonJuridica1;
    private javax.swing.JTabbedPane jTabbedPaneInclusao;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldBairro1;
    private javax.swing.JTextField jTextFieldCNPJ;
    private javax.swing.JTextField jTextFieldCNPJ1;
    private javax.swing.JTextField jTextFieldCelular;
    private javax.swing.JTextField jTextFieldCelular1;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldCidade1;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldCodigo1;
    private javax.swing.JTextField jTextFieldCodigoComplemento;
    private javax.swing.JTextField jTextFieldCodigoComplemento1;
    private javax.swing.JTextField jTextFieldCodigoNumero;
    private javax.swing.JTextField jTextFieldCodigoNumero1;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldEmail1;
    private javax.swing.JTextField jTextFieldFixo;
    private javax.swing.JTextField jTextFieldFixo1;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNome1;
    private javax.swing.JTextField jTextFieldRazaoSocial;
    private javax.swing.JTextField jTextFieldRazaoSocial1;
    private javax.swing.JTextField jTextFieldRua;
    private javax.swing.JTextField jTextFieldRua1;
    // End of variables declaration//GEN-END:variables
}
