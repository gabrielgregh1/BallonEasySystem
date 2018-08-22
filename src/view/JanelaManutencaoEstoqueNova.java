/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.bean.Fornecedor;
import model.bean.Produto;
import model.dao.FornecedorDAO;
import model.dao.ProdutoDAO;

/**
 *
 * @author gabri
 */
public class JanelaManutencaoEstoqueNova extends javax.swing.JDialog {

    /**
     * Creates new form JanelaMutencaoEstoqueNova
     */
    public JanelaManutencaoEstoqueNova(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        condicaoInicial();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       // setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
        if(id!= -1)
            campoReal = campoReal.substring(0, id)+"."+campoReal.substring(id+1);
        
        double campoDouble = Double.parseDouble(campoReal);
        return campoDouble;
    
    }
    
    private String transformaReais(double campo){
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
    
    private String tamanhoString(double valor){
        String valorString = transformaReais(valor);
        int id = valorString.indexOf(",");
        if(valorString.length()  == id+2)
            valorString += "0";
        return valorString;
    }

    private void condicaoInicial(){
        visibilidadePreInsercao();
        visibilidadePreAlteracao();
        visibilidadePreExclusao();
        configuraComboBox();
   
    }
    
    private void atalhos(KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_F2){
            RealizarInclusao();
        }
        if(evt.getKeyCode() == KeyEvent.VK_F4)
            RealizarAlteracao();
        if(evt.getKeyCode() == KeyEvent.VK_F6){
            try{
                if(!(jTextFieldExclusaoCodigo.getText().equals(""))){
                    int codigo = Integer.parseInt(jTextFieldExclusaoCodigo.getText());
                    excluirItem(codigo);  
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(rootPane, "Informe apenas números");
            }
        }
            
    }
    
    private void configuraComboBox(){
        FornecedorDAO daoForn = new FornecedorDAO();

        for(Fornecedor ponteiroFor : daoForn.read()){
            jComboBoxInsersaoFornecedor.addItem(ponteiroFor.getNomeInstituicao());
            jComboBoxAlteracaoFornecedor.addItem(ponteiroFor.getNomeInstituicao());
            jComboBoxExclusaoFornecedor.addItem(ponteiroFor.getNomeInstituicao());
        }
    }
    
    private String limitarTamanhoCampo(String campoJtextField){
        int idProcura = campoJtextField.indexOf(".");
        while(idProcura+3 < campoJtextField.length())
            campoJtextField = campoJtextField.substring(0, campoJtextField.length()-1);

        return campoJtextField;
    } 
    
    private void visibilidadePreInsercao(){
        jTextFieldInclusaoCodigo.setEnabled(false);
        
        ProdutoDAO dao = new ProdutoDAO();
        int i = 0;
        for(Produto p : dao.read()){
            if(i <= p.getCodigoProduto())
                i = p.getCodigoProduto();
        }

        i++;
        jTextFieldInclusaoCodigo.setText(String.valueOf(i));
        
        jTextFieldInclusaoBarras.setText("");
        jTextFieldInclusaoControle.setText("");
        jTextFieldInclusaoResumida.setText("");
        jTextFieldInclusaoCompleta.setText("");
        jComboBoxInsersaoFornecedor.setSelectedIndex(0);
        jComboBoxInclusaoUnidade.setSelectedIndex(0);
        jTextFieldInclusaoNcm.setText("");
        jTextFieldInclusaoCest.setText("");
        jFormattedTextFieldInclusaoCustoUnid.setText("");
        jFormattedTextFieldInclusaoCustoImp.setText("");
        jFormattedTextFieldInclusaoLucrativa.setText("");
        jFormattedTextFieldInclusaoValorVenda.setText("");
        jFormattedTextFieldInclusaoDesconto.setText("");
        jFormattedTextFieldInclusaoCusto.setText("");
        jFormattedTextFieldInclusaoEstoque.setText("");
        jFormattedTextFieldInclusaoMinima.setText("");
        jFormattedTextFieldInclusaoMaxima.setText("");
    //    jCheckBoxInclusaoInativo.setText("");
          
    }

    private void visibilidadePreAlteracao(){
        jTextFieldAlteracaoBarras.setEnabled(false);
        jTextFieldAlteracaoControle.setEnabled(false);
        jTextFieldAlteracaoResumida.setEnabled(false);
        jTextFieldAlteracaoCompleta.setEnabled(false);
        jComboBoxAlteracaoFornecedor.setEnabled(false);
        jComboBoxAlteracaoUnidade.setEnabled(false);
        jTextFieldAlteracaoNcm.setEnabled(false);
        jTextFieldAlteracaoCest.setEnabled(false);
        jFormattedTextFieldAlteracaoCustoUnid.setEnabled(false);
        jFormattedTextFieldAlteracaoCustoImp.setEnabled(false);
        jFormattedTextFieldAlteracaoLucrativa.setEnabled(false);
        jFormattedTextFieldAlteracaoValorVenda.setEnabled(false);
        jFormattedTextFieldAlteracaoDesconto.setEnabled(false);
        jFormattedTextFieldAlteracaoCusto.setEnabled(false);
        jFormattedTextFieldAlteracaoEstoque.setEnabled(false);
        jFormattedTextFieldAlteracaoMinima.setEnabled(false);
        jFormattedTextFieldAlteracaoMaxima.setEnabled(false);
        
        jButtonAlteracaoSalvar.setEnabled(false);
        jCheckBoxAlteracaoInativo.setEnabled(false);
        jTextFieldAlteracaoCodigo.setEnabled(true);
    }

    private void visibilidadePosAlteracao(){
        jTextFieldAlteracaoBarras.setEnabled(true);
        jTextFieldAlteracaoControle.setEnabled(true);
        jTextFieldAlteracaoResumida.setEnabled(true);
        jTextFieldAlteracaoCompleta.setEnabled(true);
        jComboBoxAlteracaoFornecedor.setEnabled(true);
        jComboBoxAlteracaoUnidade.setEnabled(true);
        jTextFieldAlteracaoNcm.setEnabled(true);
        jTextFieldAlteracaoCest.setEnabled(true);
        jFormattedTextFieldAlteracaoCustoUnid.setEnabled(true);
        jFormattedTextFieldAlteracaoCustoImp.setEnabled(true);
        jFormattedTextFieldAlteracaoLucrativa.setEnabled(true);
        jFormattedTextFieldAlteracaoValorVenda.setEnabled(true);
        jFormattedTextFieldAlteracaoDesconto.setEnabled(true);
        jFormattedTextFieldAlteracaoCusto.setEnabled(true);
        jFormattedTextFieldAlteracaoEstoque.setEnabled(true);
        jFormattedTextFieldAlteracaoMinima.setEnabled(true);
        jFormattedTextFieldAlteracaoMaxima.setEnabled(true);
   
        jButtonAlteracaoSalvar.setEnabled(true);
        jCheckBoxAlteracaoInativo.setEnabled(true);
        
     }

    private void visibilidadePreExclusao(){
        jTextFieldExclusaoBarras.setEnabled(false);
        jTextFieldExclusaoControle.setEnabled(false);
        jTextFieldExclusaoResumida.setEnabled(false);
        jTextFieldExclusaoCompleta.setEnabled(false);
        jComboBoxExclusaoFornecedor.setEnabled(false);
        jComboBoxExclusaoUnidade.setEnabled(false);
        jTextFieldExclusaoNcm.setEnabled(false);
        jTextFieldExclusaoCest.setEnabled(false);
        jFormattedTextFieldExclusaoCustoUnid.setEnabled(false);
        jFormattedTextFieldExclusaoCustoImp.setEnabled(false);
        jFormattedTextFieldExclusaoLucrativa.setEnabled(false);
        jFormattedTextFieldExclusaoValorVenda.setEnabled(false);
        jFormattedTextFieldExclusaoDesconto.setEnabled(false);
        jFormattedTextFieldExclusaoCusto.setEnabled(false);
        jFormattedTextFieldExclusaoEstoque.setEnabled(false);
        jFormattedTextFieldExclusaoMinima.setEnabled(false);
        jFormattedTextFieldExclusaoMaxima.setEnabled(false);
        jButtonExclusaoExcluir.setEnabled(false);
        jCheckBoxExclusaoInativo.setEnabled(false);
        
        jTextFieldExclusaoCodigo.setText(""); 
        jTextFieldExclusaoBarras.setText("");
        jTextFieldExclusaoControle.setText("");
        jTextFieldExclusaoResumida.setText("");
        jTextFieldExclusaoCompleta.setText("");
        jComboBoxExclusaoFornecedor.setSelectedIndex(0);
        jComboBoxExclusaoUnidade.setSelectedIndex(0);
        jTextFieldExclusaoNcm.setText("");
        jTextFieldExclusaoCest.setText("");
        jFormattedTextFieldExclusaoCustoUnid.setText("");
        jFormattedTextFieldExclusaoCustoImp.setText("");
        jFormattedTextFieldExclusaoLucrativa.setText("");
        jFormattedTextFieldExclusaoValorVenda.setText("");
        jFormattedTextFieldExclusaoDesconto.setText("");
        jFormattedTextFieldExclusaoCusto.setText("");
        jFormattedTextFieldExclusaoEstoque.setText("");
        jFormattedTextFieldExclusaoMinima.setText("");
        jFormattedTextFieldExclusaoMaxima.setText("");
        
        
    }
    
    private void ExibirAlteracao(){
        try{
            int codigoProduto = Integer.parseInt(jTextFieldAlteracaoCodigo.getText());
            FornecedorDAO forDAO = new FornecedorDAO();
            ProdutoDAO prodDAO = new ProdutoDAO();
            boolean achou = false;
            
            for(Produto p : prodDAO.read()){
                if(codigoProduto == p.getCodigoProduto()){
                    for(Fornecedor f : forDAO.read()){               
                        if(f.getCodigo() == p.getFornecedor().getCodigo()){
                            jComboBoxAlteracaoFornecedor.setSelectedItem(f.getNomeInstituicao());
                        
                            jComboBoxAlteracaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jTextFieldAlteracaoBarras.setText(String.valueOf(p.getCodigoBarras()));    
                            jTextFieldAlteracaoControle.setText(String.valueOf(p.getControleExtra()));               
                            jTextFieldAlteracaoResumida.setText(p.getResumida());
                            jTextFieldAlteracaoCompleta.setText(p.getCompleta());            
                            jTextFieldAlteracaoNcm.setText(String.valueOf(p.getNcm()));      
                            jTextFieldAlteracaoCest.setText(String.valueOf(p.getCest()));
                            jComboBoxAlteracaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jFormattedTextFieldAlteracaoCustoImp.setText(transformaReais(p.getCustoSemImposto()));
                            jFormattedTextFieldAlteracaoCustoUnid.setText(transformaReais(p.getCustoUnidade())); 
                            jFormattedTextFieldAlteracaoLucrativa.setText(transformaReais(p.getLucrativo()));       
                            jFormattedTextFieldAlteracaoValorVenda.setText(transformaReais(p.getValorVenda()));         
                            jFormattedTextFieldAlteracaoDesconto.setText(transformaReais(p.getDesconto()));
                            jFormattedTextFieldAlteracaoCusto.setText(transformaReais(p.getPreco()));
                            jFormattedTextFieldAlteracaoMinima.setText(transformaReais(p.getQtdMinima()));
                            jFormattedTextFieldAlteracaoMaxima.setText(transformaReais(p.getQtdMaxima()));
                            jFormattedTextFieldAlteracaoEstoque.setText(transformaReais(p.getQtdEstoque()));
                            jLabelAlteracaoDataInclusao.setText(p.getDataInclusao());
                            jTextFieldAlteracaoCodigo.setEnabled(false);
                            achou = true;
                            visibilidadePosAlteracao();
                        }
                    }
                }
            }     
            if(!achou)
                JOptionPane.showMessageDialog(null,"Produto não encontrado"); 
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Informe apenas numeros");
        }
    }
    
    private void ExibirExclusao(){
        try{
            int codigoProduto = Integer.parseInt(jTextFieldExclusaoCodigo.getText());
            FornecedorDAO forDAO = new FornecedorDAO();
            ProdutoDAO prodDAO = new ProdutoDAO();
            boolean achou = false;
            for(Produto p : prodDAO.read()){
                if(codigoProduto == p.getCodigoProduto()){
                    for(Fornecedor f :forDAO.read()){
                        if(f.getCodigo() == p.getFornecedor().getCodigo()){
                            jComboBoxExclusaoFornecedor.setSelectedItem(f.getNomeInstituicao());
                        
                            jComboBoxExclusaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jTextFieldExclusaoBarras.setText(String.valueOf(p.getCodigoBarras()));    
                            jTextFieldExclusaoControle.setText(String.valueOf(p.getControleExtra()));               
                            jTextFieldExclusaoResumida.setText(p.getResumida());
                            jTextFieldExclusaoCompleta.setText(p.getCompleta());            
                            jTextFieldExclusaoNcm.setText(String.valueOf(p.getNcm()));      
                            jTextFieldExclusaoCest.setText(String.valueOf(p.getCest()));
                            jComboBoxExclusaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jFormattedTextFieldExclusaoCustoImp.setText(transformaReais(p.getCustoSemImposto()));
                            jFormattedTextFieldExclusaoCustoUnid.setText(transformaReais(p.getCustoUnidade())); 
                            jFormattedTextFieldExclusaoLucrativa.setText(transformaReais(p.getLucrativo()));       
                            jFormattedTextFieldExclusaoValorVenda.setText(transformaReais(p.getValorVenda()));         
                            jFormattedTextFieldExclusaoDesconto.setText(transformaReais(p.getDesconto()));
                            jFormattedTextFieldExclusaoCusto.setText(transformaReais(p.getPreco()));
                            jFormattedTextFieldExclusaoMinima.setText(transformaReais(p.getQtdMinima()));
                            jFormattedTextFieldExclusaoMaxima.setText(transformaReais(p.getQtdMaxima()));
                            jFormattedTextFieldExclusaoEstoque.setText(transformaReais(p.getQtdEstoque()));
                            jLabelExclusaoDataInclusao.setText(p.getDataInclusao());
                            achou = true; 
                        }                     
                    }    
                }
            }
            for(Produto p : prodDAO.read()){
                if(codigoProduto == p.getCodigoProduto()){
                    for(Fornecedor f :forDAO.read()){
                        if(f.getCodigo() == p.getFornecedor().getCodigo()){
                            jComboBoxExclusaoFornecedor.setSelectedItem(f.getNomeInstituicao());
                        
                            jComboBoxExclusaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jTextFieldExclusaoBarras.setText(String.valueOf(p.getCodigoBarras()));    
                            jTextFieldExclusaoControle.setText(String.valueOf(p.getControleExtra()));               
                            jTextFieldExclusaoResumida.setText(p.getResumida());
                            jTextFieldExclusaoCompleta.setText(p.getCompleta());            
                            jTextFieldExclusaoNcm.setText(String.valueOf(p.getNcm()));      
                            jTextFieldExclusaoCest.setText(String.valueOf(p.getCest()));
                            jComboBoxExclusaoUnidade.setSelectedItem(p.getUnidadeMedida());
                            jFormattedTextFieldExclusaoCustoImp.setText(transformaReais(p.getCustoSemImposto()));
                            jFormattedTextFieldExclusaoCustoUnid.setText(transformaReais(p.getCustoUnidade())); 
                            jFormattedTextFieldExclusaoLucrativa.setText(transformaReais(p.getLucrativo()));       
                            jFormattedTextFieldExclusaoValorVenda.setText(transformaReais(p.getValorVenda()));         
                            jFormattedTextFieldExclusaoDesconto.setText(transformaReais(p.getDesconto()));
                            jFormattedTextFieldExclusaoCusto.setText(transformaReais(p.getPreco()));
                            jFormattedTextFieldExclusaoMinima.setText(transformaReais(p.getQtdMinima()));
                            jFormattedTextFieldExclusaoMaxima.setText(transformaReais(p.getQtdMaxima()));
                            jFormattedTextFieldExclusaoEstoque.setText(transformaReais(p.getQtdEstoque()));
                            jLabelExclusaoDataInclusao.setText(p.getDataInclusao());
                            achou = true; 
                        }                     
                    }    
                }
            }
            jButtonExclusaoExcluir.setEnabled(achou);
            if(!achou){
                JOptionPane.showMessageDialog(null,"Produto não encontrado");
                visibilidadePreExclusao();
            }
        
        }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Informe apenas numeros");
        }
    }
            
    private void RealizarAlteracao(){
        try{
            int codigo = Integer.parseInt(jTextFieldAlteracaoCodigo.getText());
            int controle = Integer.parseInt(jTextFieldAlteracaoControle.getText());
            int codigoBarras = Integer.parseInt(jTextFieldAlteracaoBarras.getText());
        
            String descResumida = jTextFieldAlteracaoResumida.getText();
            String descCompleta = jTextFieldAlteracaoCompleta.getText();
        
            String fornecedorCampo = String.valueOf(jComboBoxAlteracaoFornecedor.getSelectedItem());
    
            Long ncm = Long.parseLong(jTextFieldAlteracaoNcm.getText());
            Long codigoCest = Long.parseLong(jTextFieldAlteracaoCest.getText());
        
            double qtdMinima = transformaDouble(jFormattedTextFieldAlteracaoMinima.getText());
            double qtdMaxima = transformaDouble(jFormattedTextFieldAlteracaoMaxima.getText());
            double estoque = transformaDouble(jFormattedTextFieldAlteracaoEstoque.getText());
        
            String unidadeDeMedida = String.valueOf(jComboBoxAlteracaoUnidade.getSelectedItem());
            double custoSemImposto = transformaDouble(jFormattedTextFieldAlteracaoCustoImp.getText());
            double custoUnidade = transformaDouble(jFormattedTextFieldAlteracaoCustoUnid.getText());
            double valorVenda = transformaDouble(jFormattedTextFieldAlteracaoValorVenda.getText());
            double desconto = transformaDouble(jFormattedTextFieldAlteracaoDesconto.getText());
            double preco = transformaDouble(jFormattedTextFieldAlteracaoCustoUnid.getText());
            double lucrativo = transformaDouble(jFormattedTextFieldAlteracaoLucrativa.getText());

            FornecedorDAO daoForn = new FornecedorDAO();
            ProdutoDAO daoProd = new ProdutoDAO();
            int idFornece = 0; 
            String nomeFornece = "Default"; 
            Fornecedor fornecedor = new Fornecedor(0, " ");
            
            for(Fornecedor ponteiroFor : daoForn.read()){
                if(fornecedorCampo.equals(ponteiroFor.getNomeInstituicao())){
                    idFornece = ponteiroFor.getCodigo();
                    nomeFornece = ponteiroFor.getNomeInstituicao();
                    fornecedor = new Fornecedor(idFornece, nomeFornece);
                }
            }
            for(Fornecedor ponteiroFor : daoForn.read()){
                if(fornecedorCampo.equals(ponteiroFor.getNomeInstituicao())){
                    idFornece = ponteiroFor.getCodigo();
                    nomeFornece = ponteiroFor.getNomeInstituicao();
                    fornecedor = new Fornecedor(idFornece, nomeFornece);
                }
            }
            
        
            Produto p = new Produto( codigo,  controle,  codigoBarras,  descResumida,
                        descCompleta,  unidadeDeMedida,  preco,  valorVenda,  custoSemImposto,
                        custoUnidade,  lucrativo, desconto,  fornecedor,  ncm,  codigoCest,
                        qtdMinima,  qtdMaxima,  estoque);
        
            daoProd.update(p);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Verifique as informações");
        }
        
    }
    
    private void realizaCalculoAlteracao(int campo){
     
            double custoSImp;
            double custoUni;
            double custoFinal = 0;
            double lucrativo;
            double valorVenda;
            double desconto;
            
            if(jFormattedTextFieldAlteracaoCustoImp.getText().equals(""))
                custoSImp = 0;
            else
                custoSImp = transformaDouble(jFormattedTextFieldAlteracaoCustoImp.getText());
            
            if(jFormattedTextFieldAlteracaoCustoUnid.getText().equals(""))
                custoUni = 0;
            else
                custoUni = transformaDouble(jFormattedTextFieldAlteracaoCustoUnid.getText());
            
            if(jFormattedTextFieldAlteracaoCusto.getText().equals(""))
                custoFinal = 0;
            else
               custoFinal =  transformaDouble(jFormattedTextFieldAlteracaoCusto.getText());
            
            if(jFormattedTextFieldAlteracaoLucrativa.getText().equals(""))
                lucrativo = 0;
            else
                lucrativo = transformaDouble(jFormattedTextFieldAlteracaoLucrativa.getText());
            
            if(jFormattedTextFieldAlteracaoValorVenda.getText().equals(""))
                valorVenda = 0;
            else
                valorVenda = transformaDouble(jFormattedTextFieldAlteracaoValorVenda.getText());
            
            if(jFormattedTextFieldAlteracaoDesconto.getText().equals(""))
                desconto = 0;
            
            else
                desconto = transformaDouble(jFormattedTextFieldAlteracaoDesconto.getText());

            /*
                1 - Lucrativo
                2 - Valor Venda
                3 - Desconto
                4 - Custo
            */
            
            switch(campo){
                case 1:
                    if(custoUni != 0)
                        valorVenda = custoUni + custoUni * (lucrativo/100);
                        custoFinal = valorVenda;
                        desconto = 0;
                        
                    break;
                case 2:
                    double diferenca;
                    if(custoUni != 0){
                        diferenca = valorVenda - custoUni;
                        lucrativo = (diferenca * 100)/ custoUni;
                        custoFinal = valorVenda;
                        desconto = 0;        
                    }
                    break;
                case 3:
                    if(desconto < 100)
                        custoFinal = valorVenda - valorVenda * (desconto/100);
                    break;
                case 4:
                    if(custoUni != 0){
                        double diferencaDesc = custoFinal - custoUni;
                        lucrativo = (diferencaDesc * 100)/ custoUni;
                        valorVenda = custoFinal - desconto;
                    }
                    
                    break;
            }
            
            if(campo!= 1)
                if(lucrativo > 0){
                    jFormattedTextFieldAlteracaoLucrativa.setText(tamanhoString(lucrativo));
                    //jTextFieldAlteracaoLucrativa.setText(limitarTamanhoCampo(jTextFieldAlteracaoLucrativa.getText()));
                }
                else
                    jFormattedTextFieldAlteracaoLucrativa.setText(transformaReais(0));
            if(campo!= 2)
                if(valorVenda > 0){
                    jFormattedTextFieldAlteracaoValorVenda.setText(transformaReais(valorVenda));
            //        jFormattedTextFieldAlteracaoValorVenda.setText(limitarTamanhoCampo(jFormattedTextFieldAlteracaoValorVenda.getText()));
                }
                else
                    jFormattedTextFieldAlteracaoValorVenda.setText(transformaReais(0));
            if(campo!= 4)
                if(valorVenda > 0){
                    jFormattedTextFieldAlteracaoCusto.setText(transformaReais(custoFinal));
                //    jFormattedTextFieldAlteracaoCusto.setText(limitarTamanhoCampo(jFormattedTextFieldAlteracaoCusto.getText()));
                }
                else
                    jFormattedTextFieldAlteracaoCusto.setText(transformaReais(0));
            
          
    
    }
    
    private void RealizarInclusao(){  
        try{
            double desconto;
            int codigo = Integer.parseInt(jTextFieldInclusaoCodigo.getText());
            int controle = Integer.parseInt(jTextFieldInclusaoControle.getText());
            int codigoBarras = Integer.parseInt(jTextFieldInclusaoBarras.getText());
        
            String descResumida = jTextFieldInclusaoResumida.getText();
            String descCompleta = jTextFieldInclusaoCompleta.getText();
        
            String fornecedorCampo = String.valueOf(jComboBoxInsersaoFornecedor.getSelectedItem());
    
            Long ncm = Long.parseLong(jTextFieldInclusaoNcm.getText());
            Long codigoCest = Long.parseLong(jTextFieldInclusaoCest.getText());
        
            double qtdMinima = transformaDouble(jFormattedTextFieldInclusaoMinima.getText());
            double qtdMaxima = transformaDouble(jFormattedTextFieldInclusaoMaxima.getText());
            double estoque = transformaDouble(jFormattedTextFieldInclusaoEstoque.getText());
        
            String unidadeDeMedida = String.valueOf(jComboBoxInclusaoUnidade.getSelectedItem());
            double custoSemImposto = transformaDouble(jFormattedTextFieldInclusaoCustoImp.getText());
            double custoUnidade = transformaDouble(jFormattedTextFieldInclusaoCustoUnid.getText());
            double valorVenda = transformaDouble(jFormattedTextFieldInclusaoValorVenda.getText());
            if(!(jFormattedTextFieldInclusaoDesconto.getText().equals("")))
                 desconto = transformaDouble(jFormattedTextFieldInclusaoDesconto.getText());
            else
                desconto = 0;
            double preco = transformaDouble(jFormattedTextFieldInclusaoCusto.getText());
            double lucrativo = transformaDouble(jFormattedTextFieldInclusaoLucrativa.getText());

            FornecedorDAO daoForn = new FornecedorDAO();
            ProdutoDAO daoProd = new ProdutoDAO();
            int idFornece = 0; 
            String nomeFornece = "Default"; 
            Fornecedor fornecedor = new Fornecedor(0, " ");
            
            for(Fornecedor ponteiroFor : daoForn.read()){
                if(fornecedorCampo.equals(ponteiroFor.getNomeInstituicao())){
                    idFornece = ponteiroFor.getCodigo();
                    nomeFornece = ponteiroFor.getNomeInstituicao();
                    fornecedor = new Fornecedor(idFornece, nomeFornece);
                }
            }
            for(Fornecedor ponteiroFor : daoForn.read()){
                if(fornecedorCampo.equals(ponteiroFor.getNomeInstituicao())){
                    idFornece = ponteiroFor.getCodigo();
                    nomeFornece = ponteiroFor.getNomeInstituicao();
                    fornecedor = new Fornecedor(idFornece, nomeFornece);
                }
            }
            
        
            Produto p = new Produto( codigo,  controle,  codigoBarras,  descResumida,
                        descCompleta,  unidadeDeMedida,  preco,  valorVenda,  custoSemImposto,
                        custoUnidade,  lucrativo, desconto,  fornecedor,  ncm,  codigoCest,
                        qtdMinima,  qtdMaxima,  estoque);
        
            daoProd.create(p);
            visibilidadePreInsercao();
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Verifique as informações");
             jFormattedTextFieldAlteracaoCusto.setText("");
             jFormattedTextFieldAlteracaoLucrativa.setText("");
             jFormattedTextFieldAlteracaoValorVenda.setText("");
             jFormattedTextFieldAlteracaoCustoImp.setText("");
             jFormattedTextFieldAlteracaoDesconto.setText("");
             jFormattedTextFieldAlteracaoCustoUnid.setText("");
        }
    }
    
    private void excluirItem(int codigoInformado){
       
        
        String[] opcao = {"Sim", "Não"}; 
        
        int escolha = JOptionPane.showOptionDialog(rootPane, "Tem certeza que quer excluir o produto de codigo "+
                codigoInformado+" ?",
                                     "Escolha um opção", WIDTH, HEIGHT, null, opcao, opcao[0]);
        
        if(escolha == 0){
            ProdutoDAO dao = new ProdutoDAO();
            dao.delete(codigoInformado);
            visibilidadePreExclusao();
        }
        
    
    }
   
    private void realizaCalculoInsercao(int campo){
        try{
            double custoSImp;
            double custoUni;
            double custoFinal = 0;
            double lucrativo;
            double valorVenda;
            double desconto;
            
            if(jFormattedTextFieldInclusaoCustoImp.getText().equals(""))
                custoSImp = 0;
            else
                custoSImp = transformaDouble(jFormattedTextFieldInclusaoCustoImp.getText());
            
            if(jFormattedTextFieldInclusaoCustoUnid.getText().equals(""))
                custoUni = 0;
            else
                custoUni = transformaDouble(jFormattedTextFieldInclusaoCustoUnid.getText());
            
            if(jFormattedTextFieldInclusaoCusto.getText().equals(""))
                custoFinal = 0;
            else
                custoFinal = transformaDouble(jFormattedTextFieldInclusaoCusto.getText());
            
            if(jFormattedTextFieldInclusaoLucrativa.getText().equals(""))
                lucrativo = 0;
            else
                lucrativo = transformaDouble(jFormattedTextFieldInclusaoLucrativa.getText());
            
            if(jFormattedTextFieldInclusaoValorVenda.getText().equals(""))
                valorVenda = 0;
            else
                valorVenda = transformaDouble(jFormattedTextFieldInclusaoValorVenda.getText());
            
            if(jFormattedTextFieldInclusaoDesconto.getText().equals(""))
                desconto = 0;
            
            else
                desconto = transformaDouble(jFormattedTextFieldInclusaoDesconto.getText());
            
           
            /*
                1 - Lucrativo
                2 - Valor Venda
                3 - Desconto
                4 - Custo
            */
            
            switch(campo){
                case 1:
                    if(custoUni != 0)
                        valorVenda = custoUni + custoUni * (lucrativo/100);
                        custoFinal = valorVenda;
                        desconto = 0;
                        
                    break;
                case 2:
                    double diferenca;
                    if(custoUni != 0){
                        diferenca = valorVenda - custoUni;
                        lucrativo = (diferenca * 100)/ custoUni;
                        custoFinal = valorVenda;
                        desconto = 0;        
                    }
                    break;
                case 3:
                    custoFinal = valorVenda - valorVenda * (desconto/100);
                    break;
                case 4:
                    if(custoUni != 0){
                        double diferencaDesc = custoFinal - custoUni;
                        lucrativo = (diferencaDesc * 100)/ custoUni;
                        valorVenda = custoFinal - desconto;
                    }
                    
                    break;
            }
            
            if(campo!= 1)
                if(lucrativo > 0){
                    jFormattedTextFieldInclusaoLucrativa.setText(transformaReais(lucrativo));
                   // jFormattedTextFieldInclusaoLucrativa.setText(limitarTamanhoCampo(jFormattedTextFieldInclusaoLucrativa.getText()));
                }
                    
                else
                    jFormattedTextFieldInclusaoLucrativa.setText(String.valueOf(0));
            if(campo!= 2)
                if(valorVenda > 0){
                    jFormattedTextFieldInclusaoValorVenda.setText(transformaReais(valorVenda));
                   // jFormattedTextFieldInclusaoValorVenda.setText(limitarTamanhoCampo(jFormattedTextFieldInclusaoValorVenda.getText()));
                }
                else
                    jFormattedTextFieldInclusaoValorVenda.setText(String.valueOf(0));
            if(campo!= 4)
                if(valorVenda > 0){
                    jFormattedTextFieldInclusaoCusto.setText(transformaReais(custoFinal));
                    //jTextFieldInclusaoCusto.setText(limitarTamanhoCampo(jFormattedTextFieldInclusaoCusto.getText()));
                }
                else
                    jFormattedTextFieldInclusaoCusto.setText(String.valueOf(0));
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informar apenas números");
             jFormattedTextFieldInclusaoCusto.setText("");
             jFormattedTextFieldInclusaoLucrativa.setText("");
             jFormattedTextFieldInclusaoValorVenda.setText("");
             jFormattedTextFieldInclusaoCustoImp.setText("");
             jFormattedTextFieldInclusaoDesconto.setText("");
             jFormattedTextFieldInclusaoCustoUnid.setText("");
        }    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabelInclusaoTituloCodigo = new javax.swing.JLabel();
        jTextFieldInclusaoCodigo = new javax.swing.JTextField();
        jLabelInclusaoControle = new javax.swing.JLabel();
        jTextFieldInclusaoControle = new javax.swing.JTextField();
        jLabelInclusaoBarras = new javax.swing.JLabel();
        jTextFieldInclusaoBarras = new javax.swing.JTextField();
        jCheckBoxInclusaoInativo = new javax.swing.JCheckBox();
        jLabelInclusaoDescricao = new javax.swing.JLabel();
        jLabelInclusaoResumida = new javax.swing.JLabel();
        jLabelInclusaoCompleta = new javax.swing.JLabel();
        jTextFieldInclusaoCompleta = new javax.swing.JTextField();
        jTextFieldInclusaoResumida = new javax.swing.JTextField();
        jLabelInclusaoData = new javax.swing.JLabel();
        jLabelInclusaoDataInclusao = new javax.swing.JLabel();
        jLabelInclusaoFornecedor = new javax.swing.JLabel();
        jComboBoxInsersaoFornecedor = new javax.swing.JComboBox<>();
        jTextFieldInclusaoNcm = new javax.swing.JTextField();
        jLabelInclusaoNcm = new javax.swing.JLabel();
        jLabelInclusaoCest = new javax.swing.JLabel();
        jTextFieldInclusaoCest = new javax.swing.JTextField();
        jLabelInclusaoPesquisaNcm = new javax.swing.JLabel();
        jLabelInclusaoPesquisaCest = new javax.swing.JLabel();
        jLabelInclusaoQtdEstoque = new javax.swing.JLabel();
        jLabelInclusaoMinima = new javax.swing.JLabel();
        jLabelInclusaoMaxima = new javax.swing.JLabel();
        jLabelInclusaoEmEstoque = new javax.swing.JLabel();
        jLabelInsercaoUn2 = new javax.swing.JLabel();
        jLabelInsercaoUn = new javax.swing.JLabel();
        jLabelInsercaoUn3 = new javax.swing.JLabel();
        jLabelInclusaoVarejo = new javax.swing.JLabel();
        jComboBoxInclusaoUnidade = new javax.swing.JComboBox<>();
        jLabelInclusaoUnidade = new javax.swing.JLabel();
        jLabelInclusaoCustoImp = new javax.swing.JLabel();
        jLabelInclusaoCustoUnid = new javax.swing.JLabel();
        jLabelInclusaoLucrativa = new javax.swing.JLabel();
        jLabelInclusaoValorVenda = new javax.swing.JLabel();
        jLabelInclusaoDesconto = new javax.swing.JLabel();
        jLabelInclusaoCusto = new javax.swing.JLabel();
        jButtonInclusaoSalvar = new javax.swing.JButton();
        jButtonInclusaoSair = new javax.swing.JButton();
        jFormattedTextFieldInclusaoCustoImp = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInclusaoCustoUnid = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInclusaoLucrativa = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInclusaoValorVenda = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInclusaoDesconto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInclusaoCusto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInclusaoMinima = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInclusaoMaxima = new javax.swing.JFormattedTextField();
        jFormattedTextFieldInclusaoEstoque = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabelAlteracaoTituloCodigo = new javax.swing.JLabel();
        jTextFieldAlteracaoCodigo = new javax.swing.JTextField();
        jLabelAlteracaoControle = new javax.swing.JLabel();
        jTextFieldAlteracaoControle = new javax.swing.JTextField();
        jLabelAlteracaoBarras = new javax.swing.JLabel();
        jTextFieldAlteracaoBarras = new javax.swing.JTextField();
        jCheckBoxAlteracaoInativo = new javax.swing.JCheckBox();
        jLabelAlteracaoDescricao = new javax.swing.JLabel();
        jLabelAlteracaoResumida = new javax.swing.JLabel();
        jLabelAlteracaoCompleta = new javax.swing.JLabel();
        jTextFieldAlteracaoCompleta = new javax.swing.JTextField();
        jTextFieldAlteracaoResumida = new javax.swing.JTextField();
        jLabelAlteracaoData = new javax.swing.JLabel();
        jLabelAlteracaoDataInclusao = new javax.swing.JLabel();
        jLabelAlteracaoFornecedor = new javax.swing.JLabel();
        jComboBoxAlteracaoFornecedor = new javax.swing.JComboBox<>();
        jTextFieldAlteracaoNcm = new javax.swing.JTextField();
        jLabelAlteracaoNcm = new javax.swing.JLabel();
        jLabelAlteracaoCest = new javax.swing.JLabel();
        jTextFieldAlteracaoCest = new javax.swing.JTextField();
        jLabeAlteracaoPesquisaNcmm = new javax.swing.JLabel();
        jLabelAlteracaoPesquisaCest = new javax.swing.JLabel();
        jLabelAlteracaoQtdEstoque = new javax.swing.JLabel();
        jLabelAlteracaoMinima = new javax.swing.JLabel();
        jLabelAlteracaoMaxima = new javax.swing.JLabel();
        jLabelAlteracaoEmEstoque = new javax.swing.JLabel();
        jLabelAlteracaoUn2 = new javax.swing.JLabel();
        jLabelInsercaoUn1 = new javax.swing.JLabel();
        jLabelAlteracaoUn = new javax.swing.JLabel();
        jLabelAlteracaoVarejo = new javax.swing.JLabel();
        jComboBoxAlteracaoUnidade = new javax.swing.JComboBox<>();
        jLabelAlteracaoUnidade = new javax.swing.JLabel();
        jLabelAlteracaoCustoImp = new javax.swing.JLabel();
        jLabelAlteracaoCustoUnid = new javax.swing.JLabel();
        jLabelAlteracaoLucrativa = new javax.swing.JLabel();
        jLabelAlteracaoValorVenda = new javax.swing.JLabel();
        jLabelAlteracaoDesconto = new javax.swing.JLabel();
        jLabelAlteracaoCusto = new javax.swing.JLabel();
        jButtonAlteracaoSalvar = new javax.swing.JButton();
        jButtonAlteracaoSair = new javax.swing.JButton();
        jLabelAlteracaoPesquisaNcm = new javax.swing.JLabel();
        jFormattedTextFieldAlteracaoCustoImp = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoCustoUnid = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoLucrativa = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoValorVenda = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoDesconto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoCusto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoMinima = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoMaxima = new javax.swing.JFormattedTextField();
        jFormattedTextFieldAlteracaoEstoque = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabelExclusaoTituloCodigo = new javax.swing.JLabel();
        jTextFieldExclusaoCodigo = new javax.swing.JTextField();
        jLabelExclusaoControle = new javax.swing.JLabel();
        jTextFieldExclusaoControle = new javax.swing.JTextField();
        jLabelExclusaoBarras = new javax.swing.JLabel();
        jTextFieldExclusaoBarras = new javax.swing.JTextField();
        jCheckBoxExclusaoInativo = new javax.swing.JCheckBox();
        jLabelExclusaoDescricao = new javax.swing.JLabel();
        jLabelExclusaoResumida = new javax.swing.JLabel();
        jLabelExclusaoCompleta = new javax.swing.JLabel();
        jTextFieldExclusaoCompleta = new javax.swing.JTextField();
        jTextFieldExclusaoResumida = new javax.swing.JTextField();
        jLabelExclusaoData = new javax.swing.JLabel();
        jLabelExclusaoDataInclusao = new javax.swing.JLabel();
        jLabelExclusaoFornecedor = new javax.swing.JLabel();
        jComboBoxExclusaoFornecedor = new javax.swing.JComboBox<>();
        jTextFieldExclusaoNcm = new javax.swing.JTextField();
        jLabelExclusaoNcm = new javax.swing.JLabel();
        jLabelExclusaoCest = new javax.swing.JLabel();
        jTextFieldExclusaoCest = new javax.swing.JTextField();
        jLabelExclusaoPesquisaNcm = new javax.swing.JLabel();
        jLabelExclusaoPesquisaCest = new javax.swing.JLabel();
        jLabelExclusaoQtdEstoque = new javax.swing.JLabel();
        jLabelExclusaoMinima = new javax.swing.JLabel();
        jLabelExclusaoMaxima = new javax.swing.JLabel();
        jLabelExclusaoEmEstoque = new javax.swing.JLabel();
        jLabelExclusaoUn2 = new javax.swing.JLabel();
        jLabelExclusaoUn3 = new javax.swing.JLabel();
        jLabelExclusaoUn = new javax.swing.JLabel();
        jLabelExclusaoVarejo = new javax.swing.JLabel();
        jComboBoxExclusaoUnidade = new javax.swing.JComboBox<>();
        jLabelExclusaoUnidade = new javax.swing.JLabel();
        jLabelExclusaoCustoImp = new javax.swing.JLabel();
        jLabelExclusaoCustoUnid = new javax.swing.JLabel();
        jLabelExclusaoLucrativa = new javax.swing.JLabel();
        jLabelInclusaoValorVenda2 = new javax.swing.JLabel();
        jLabelInclusaoDesconto2 = new javax.swing.JLabel();
        jLabelExclusaoCusto = new javax.swing.JLabel();
        jButtonExclusaoExcluir = new javax.swing.JButton();
        jButtonExclusaoSair = new javax.swing.JButton();
        jLabelExclusaoPesquisa = new javax.swing.JLabel();
        jFormattedTextFieldExclusaoCustoImp = new javax.swing.JFormattedTextField();
        jFormattedTextFieldExclusaoCustoUnid = new javax.swing.JFormattedTextField();
        jFormattedTextFieldExclusaoLucrativa = new javax.swing.JFormattedTextField();
        jFormattedTextFieldExclusaoValorVenda = new javax.swing.JFormattedTextField();
        jFormattedTextFieldExclusaoDesconto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldExclusaoCusto = new javax.swing.JFormattedTextField();
        jFormattedTextFieldExclusaoMinima = new javax.swing.JFormattedTextField();
        jFormattedTextFieldExclusaoMaxima = new javax.swing.JFormattedTextField();
        jFormattedTextFieldExclusaoEstoque = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabelF5 = new javax.swing.JLabel();
        jLabelF12 = new javax.swing.JLabel();
        jLabelF1 = new javax.swing.JLabel();
        jLabelF2 = new javax.swing.JLabel();
        jLabelLogoBalloon = new javax.swing.JLabel();
        jLabelBalloon = new javax.swing.JLabel();
        jLabelF6 = new javax.swing.JLabel();
        jLabelF7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

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
            .addGap(0, 46, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsProduto_80px.png"))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 255));
        jLabel38.setText("Manutenção de Estoque");

        jTabbedPane1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        jLabelInclusaoTituloCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoTituloCodigo.setText("Código");
        jLabelInclusaoTituloCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoTituloCodigoMouseClicked(evt);
            }
        });

        jTextFieldInclusaoCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCodigoKeyPressed(evt);
            }
        });

        jLabelInclusaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoControle.setText("Controle (Extra)");
        jLabelInclusaoControle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoControleMouseClicked(evt);
            }
        });

        jTextFieldInclusaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoControle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoControleKeyPressed(evt);
            }
        });

        jLabelInclusaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoBarras.setText("Código de Barras");
        jLabelInclusaoBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoBarrasMouseClicked(evt);
            }
        });

        jTextFieldInclusaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInclusaoBarrasActionPerformed(evt);
            }
        });
        jTextFieldInclusaoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoBarrasKeyPressed(evt);
            }
        });

        jCheckBoxInclusaoInativo.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jCheckBoxInclusaoInativo.setForeground(new java.awt.Color(255, 0, 0));
        jCheckBoxInclusaoInativo.setText("Inativo");

        jLabelInclusaoDescricao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoDescricao.setText("Descrição");

        jLabelInclusaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoResumida.setText("Resumida");
        jLabelInclusaoResumida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoResumidaMouseClicked(evt);
            }
        });

        jLabelInclusaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCompleta.setText("Completa");
        jLabelInclusaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCompletaMouseClicked(evt);
            }
        });

        jTextFieldInclusaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldInclusaoCompletaMouseClicked(evt);
            }
        });
        jTextFieldInclusaoCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInclusaoCompletaActionPerformed(evt);
            }
        });
        jTextFieldInclusaoCompleta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCompletaKeyPressed(evt);
            }
        });

        jTextFieldInclusaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoResumida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoResumidaKeyPressed(evt);
            }
        });

        jLabelInclusaoData.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoData.setText("Inclusão");

        jLabelInclusaoDataInclusao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoDataInclusao.setForeground(new java.awt.Color(255, 0, 0));
        jLabelInclusaoDataInclusao.setText("26/06/2018");

        jLabelInclusaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoFornecedor.setText("Fornecedor");
        jLabelInclusaoFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoFornecedorMouseClicked(evt);
            }
        });

        jComboBoxInsersaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxInsersaoFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default" }));
        jComboBoxInsersaoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxInsersaoFornecedorKeyPressed(evt);
            }
        });

        jTextFieldInclusaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoNcm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInclusaoNcmActionPerformed(evt);
            }
        });
        jTextFieldInclusaoNcm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoNcmKeyPressed(evt);
            }
        });

        jLabelInclusaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSoma_16px.png"))); // NOI18N
        jLabelInclusaoNcm.setText("NCM");
        jLabelInclusaoNcm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoNcmMouseClicked(evt);
            }
        });

        jLabelInclusaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCest.setText("Código CEST");
        jLabelInclusaoCest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCestMouseClicked(evt);
            }
        });

        jTextFieldInclusaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldInclusaoCest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInclusaoCestActionPerformed(evt);
            }
        });
        jTextFieldInclusaoCest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInclusaoCestKeyPressed(evt);
            }
        });

        jLabelInclusaoPesquisaNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoPesquisaNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jLabelInclusaoPesquisaCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoPesquisaCest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jLabelInclusaoQtdEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoQtdEstoque.setText("Quantidade em Estoque");

        jLabelInclusaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoMinima.setText("Quantidade Mínima");
        jLabelInclusaoMinima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoMinimaMouseClicked(evt);
            }
        });
        jLabelInclusaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelInclusaoMinimaKeyPressed(evt);
            }
        });

        jLabelInclusaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoMaxima.setText("Quantidade Máxima");
        jLabelInclusaoMaxima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoMaximaMouseClicked(evt);
            }
        });

        jLabelInclusaoEmEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInclusaoEmEstoque.setText("Quantidade Estoque");
        jLabelInclusaoEmEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoEmEstoqueMouseClicked(evt);
            }
        });

        jLabelInsercaoUn2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInsercaoUn2.setText("UN");

        jLabelInsercaoUn.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInsercaoUn.setText("UN");

        jLabelInsercaoUn3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInsercaoUn3.setText("UN");

        jLabelInclusaoVarejo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoVarejo.setText("Varejo");

        jComboBoxInclusaoUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UN", "KILO", "KILOGRAMAS" }));
        jComboBoxInclusaoUnidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxInclusaoUnidadeKeyPressed(evt);
            }
        });

        jLabelInclusaoUnidade.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoUnidade.setText("Unidade");
        jLabelInclusaoUnidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoUnidadeMouseClicked(evt);
            }
        });

        jLabelInclusaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCustoImp.setText("Custo S/ Imp.");
        jLabelInclusaoCustoImp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCustoImpMouseClicked(evt);
            }
        });

        jLabelInclusaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCustoUnid.setText("Custo Unid. $");
        jLabelInclusaoCustoUnid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCustoUnidMouseClicked(evt);
            }
        });

        jLabelInclusaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoLucrativa.setText("Lucrativ. %");
        jLabelInclusaoLucrativa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoLucrativaMouseClicked(evt);
            }
        });

        jLabelInclusaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoValorVenda.setText("Valor Venda $");
        jLabelInclusaoValorVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoValorVendaMouseClicked(evt);
            }
        });

        jLabelInclusaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoDesconto.setText("Desconto %");
        jLabelInclusaoDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoDescontoMouseClicked(evt);
            }
        });

        jLabelInclusaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoCusto.setText("Custo");
        jLabelInclusaoCusto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoCustoMouseClicked(evt);
            }
        });

        jButtonInclusaoSalvar.setBackground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoSalvar.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonInclusaoSalvar.setForeground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSalvar_40px.png"))); // NOI18N
        jButtonInclusaoSalvar.setText("Gravar");
        jButtonInclusaoSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonInclusaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInclusaoSalvarActionPerformed(evt);
            }
        });

        jButtonInclusaoSair.setBackground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoSair.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonInclusaoSair.setForeground(new java.awt.Color(0, 153, 255));
        jButtonInclusaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonInclusaoSair.setText("Sair");
        jButtonInclusaoSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jFormattedTextFieldInclusaoCustoImp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldInclusaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldInclusaoCustoImp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoCustoImpKeyPressed(evt);
            }
        });

        jFormattedTextFieldInclusaoCustoUnid.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldInclusaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldInclusaoCustoUnid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoCustoUnidKeyPressed(evt);
            }
        });

        jFormattedTextFieldInclusaoLucrativa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldInclusaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldInclusaoLucrativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoLucrativaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoLucrativaKeyReleased(evt);
            }
        });

        jFormattedTextFieldInclusaoValorVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldInclusaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldInclusaoValorVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoValorVendaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoValorVendaKeyReleased(evt);
            }
        });

        jFormattedTextFieldInclusaoDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldInclusaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldInclusaoDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoDescontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoDescontoKeyReleased(evt);
            }
        });

        jFormattedTextFieldInclusaoCusto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldInclusaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldInclusaoCusto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoCustoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoCustoKeyReleased(evt);
            }
        });

        jFormattedTextFieldInclusaoMinima.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldInclusaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldInclusaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoMinimaKeyPressed(evt);
            }
        });

        jFormattedTextFieldInclusaoMaxima.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldInclusaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldInclusaoMaxima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoMaximaKeyPressed(evt);
            }
        });

        jFormattedTextFieldInclusaoEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldInclusaoEstoque.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jFormattedTextFieldInclusaoEstoque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldInclusaoEstoqueKeyPressed(evt);
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoTituloCodigo)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldInclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(jCheckBoxInclusaoInativo)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelInclusaoDescricao))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoControle)
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldInclusaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelInclusaoResumida)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldInclusaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoBarras)
                                .addGap(5, 5, 5)
                                .addComponent(jTextFieldInclusaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelInclusaoCompleta)
                                .addGap(21, 21, 21)
                                .addComponent(jTextFieldInclusaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelInclusaoData)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelInclusaoDataInclusao)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoMinima)
                                .addGap(13, 13, 13)
                                .addComponent(jFormattedTextFieldInclusaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelInsercaoUn3)
                                .addGap(139, 139, 139)
                                .addComponent(jLabelInclusaoUnidade)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelInclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelInclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelInclusaoLucrativa)
                                .addGap(48, 48, 48)
                                .addComponent(jLabelInclusaoValorVenda)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelInclusaoDesconto)
                                .addGap(44, 44, 44)
                                .addComponent(jLabelInclusaoCusto))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoEmEstoque)
                                .addGap(6, 6, 6)
                                .addComponent(jFormattedTextFieldInclusaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelInsercaoUn))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelInclusaoMaxima)
                                .addGap(11, 11, 11)
                                .addComponent(jFormattedTextFieldInclusaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelInsercaoUn2)
                                .addGap(79, 79, 79)
                                .addComponent(jLabelInclusaoVarejo)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxInclusaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextFieldInclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldInclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldInclusaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldInclusaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldInclusaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldInclusaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonInclusaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonInclusaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelInclusaoQtdEstoque, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelInclusaoFornecedor)
                                            .addComponent(jComboBoxInsersaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(82, 82, 82)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelInclusaoNcm)
                                            .addComponent(jTextFieldInclusaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabelInclusaoPesquisaNcm)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelInclusaoCest)
                                            .addComponent(jTextFieldInclusaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabelInclusaoPesquisaCest)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInclusaoData)
                    .addComponent(jLabelInclusaoDataInclusao)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInclusaoTituloCodigo)
                            .addComponent(jTextFieldInclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxInclusaoInativo)
                            .addComponent(jLabelInclusaoDescricao))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInclusaoControle)
                            .addComponent(jTextFieldInclusaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelInclusaoResumida)
                            .addComponent(jTextFieldInclusaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInclusaoBarras)
                            .addComponent(jTextFieldInclusaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelInclusaoCompleta)
                            .addComponent(jTextFieldInclusaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelInclusaoFornecedor)
                        .addGap(12, 12, 12)
                        .addComponent(jComboBoxInsersaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabelInclusaoNcm)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldInclusaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelInclusaoPesquisaNcm))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabelInclusaoCest)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldInclusaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelInclusaoPesquisaCest)))
                .addGap(25, 25, 25)
                .addComponent(jLabelInclusaoQtdEstoque)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInclusaoMinima)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelInsercaoUn3)
                        .addComponent(jFormattedTextFieldInclusaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelInclusaoUnidade))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelInclusaoCustoImp))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelInclusaoCustoUnid))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoLucrativa))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoValorVenda))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoDesconto))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoCusto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInclusaoMaxima)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelInsercaoUn2)
                        .addComponent(jFormattedTextFieldInclusaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelInclusaoVarejo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxInclusaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldInclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldInclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jFormattedTextFieldInclusaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jFormattedTextFieldInclusaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldInclusaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldInclusaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInclusaoEmEstoque)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelInsercaoUn)
                        .addComponent(jFormattedTextFieldInclusaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInclusaoSalvar)
                    .addComponent(jButtonInclusaoSair))
                .addContainerGap())
        );

        jTabbedPane1.addTab("INCLUSÃO", jPanel3);

        jLabelAlteracaoTituloCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoTituloCodigo.setText("Código");
        jLabelAlteracaoTituloCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoTituloCodigoMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCodigoKeyPressed(evt);
            }
        });

        jLabelAlteracaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoControle.setText("Controle (Extra)");
        jLabelAlteracaoControle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoControleMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoControle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoControleKeyPressed(evt);
            }
        });

        jLabelAlteracaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoBarras.setText("Código de Barras");
        jLabelAlteracaoBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoBarrasMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlteracaoBarrasActionPerformed(evt);
            }
        });
        jTextFieldAlteracaoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoBarrasKeyPressed(evt);
            }
        });

        jCheckBoxAlteracaoInativo.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jCheckBoxAlteracaoInativo.setForeground(new java.awt.Color(255, 0, 0));
        jCheckBoxAlteracaoInativo.setText("Inativo");

        jLabelAlteracaoDescricao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoDescricao.setText("Descrição");

        jLabelAlteracaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoResumida.setText("Resumida");
        jLabelAlteracaoResumida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoResumidaMouseClicked(evt);
            }
        });

        jLabelAlteracaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCompleta.setText("Completa");
        jLabelAlteracaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCompletaMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldAlteracaoCompletaMouseClicked(evt);
            }
        });
        jTextFieldAlteracaoCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlteracaoCompletaActionPerformed(evt);
            }
        });
        jTextFieldAlteracaoCompleta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCompletaKeyPressed(evt);
            }
        });

        jTextFieldAlteracaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoResumida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoResumidaKeyPressed(evt);
            }
        });

        jLabelAlteracaoData.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoData.setText("Inclusão");

        jLabelAlteracaoDataInclusao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoDataInclusao.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAlteracaoDataInclusao.setText("26/06/2018");

        jLabelAlteracaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoFornecedor.setText("Fornecedor");
        jLabelAlteracaoFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoFornecedorMouseClicked(evt);
            }
        });

        jComboBoxAlteracaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxAlteracaoFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default" }));
        jComboBoxAlteracaoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxAlteracaoFornecedorKeyPressed(evt);
            }
        });

        jTextFieldAlteracaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoNcm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlteracaoNcmActionPerformed(evt);
            }
        });
        jTextFieldAlteracaoNcm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoNcmKeyPressed(evt);
            }
        });

        jLabelAlteracaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsAlteracao_16px.png"))); // NOI18N
        jLabelAlteracaoNcm.setText("NCM");
        jLabelAlteracaoNcm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoNcmMouseClicked(evt);
            }
        });

        jLabelAlteracaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCest.setText("Código CEST");
        jLabelAlteracaoCest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCestMouseClicked(evt);
            }
        });

        jTextFieldAlteracaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldAlteracaoCest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlteracaoCestActionPerformed(evt);
            }
        });
        jTextFieldAlteracaoCest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAlteracaoCestKeyPressed(evt);
            }
        });

        jLabeAlteracaoPesquisaNcmm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabeAlteracaoPesquisaNcmm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jLabelAlteracaoPesquisaCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoPesquisaCest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jLabelAlteracaoQtdEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoQtdEstoque.setText("Quantidade em Estoque");

        jLabelAlteracaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoMinima.setText("Quantidade Mínima");
        jLabelAlteracaoMinima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoMinimaMouseClicked(evt);
            }
        });
        jLabelAlteracaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelAlteracaoMinimaKeyPressed(evt);
            }
        });

        jLabelAlteracaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoMaxima.setText("Quantidade Máxima");
        jLabelAlteracaoMaxima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoMaximaMouseClicked(evt);
            }
        });

        jLabelAlteracaoEmEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelAlteracaoEmEstoque.setText("Quantidade Estoque");
        jLabelAlteracaoEmEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoEmEstoqueMouseClicked(evt);
            }
        });

        jLabelAlteracaoUn2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoUn2.setText("UN");

        jLabelInsercaoUn1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelInsercaoUn1.setText("UN");

        jLabelAlteracaoUn.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoUn.setText("UN");

        jLabelAlteracaoVarejo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoVarejo.setText("Varejo");

        jComboBoxAlteracaoUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UN", "KILO", "KILOGRAMAS" }));

        jLabelAlteracaoUnidade.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoUnidade.setText("Unidade");
        jLabelAlteracaoUnidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoUnidadeMouseClicked(evt);
            }
        });

        jLabelAlteracaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCustoImp.setText("Custo S/ Imp.");
        jLabelAlteracaoCustoImp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCustoImpMouseClicked(evt);
            }
        });

        jLabelAlteracaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCustoUnid.setText("Custo Unid. $");
        jLabelAlteracaoCustoUnid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCustoUnidMouseClicked(evt);
            }
        });

        jLabelAlteracaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoLucrativa.setText("Lucrativ. %");
        jLabelAlteracaoLucrativa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoLucrativaMouseClicked(evt);
            }
        });

        jLabelAlteracaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoValorVenda.setText("Valor Venda $");
        jLabelAlteracaoValorVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoValorVendaMouseClicked(evt);
            }
        });

        jLabelAlteracaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoDesconto.setText("Desconto %");
        jLabelAlteracaoDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoDescontoMouseClicked(evt);
            }
        });

        jLabelAlteracaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoCusto.setText("Custo");
        jLabelAlteracaoCusto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoCustoMouseClicked(evt);
            }
        });

        jButtonAlteracaoSalvar.setBackground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoSalvar.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonAlteracaoSalvar.setForeground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSalvar_40px.png"))); // NOI18N
        jButtonAlteracaoSalvar.setText("Gravar");
        jButtonAlteracaoSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAlteracaoSalvar.setMaximumSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSalvar.setMinimumSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSalvar.setPreferredSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlteracaoSalvarActionPerformed(evt);
            }
        });

        jButtonAlteracaoSair.setBackground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoSair.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonAlteracaoSair.setForeground(new java.awt.Color(0, 153, 255));
        jButtonAlteracaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonAlteracaoSair.setText("Sair");
        jButtonAlteracaoSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAlteracaoSair.setMaximumSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSair.setMinimumSize(new java.awt.Dimension(179, 49));
        jButtonAlteracaoSair.setPreferredSize(new java.awt.Dimension(179, 49));

        jLabelAlteracaoPesquisaNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelAlteracaoPesquisaNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N
        jLabelAlteracaoPesquisaNcm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAlteracaoPesquisaNcm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAlteracaoPesquisaNcmMouseClicked(evt);
            }
        });

        jFormattedTextFieldAlteracaoCustoImp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldAlteracaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldAlteracaoCustoImp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoCustoImpKeyPressed(evt);
            }
        });

        jFormattedTextFieldAlteracaoCustoUnid.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldAlteracaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldAlteracaoCustoUnid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoCustoUnidKeyPressed(evt);
            }
        });

        jFormattedTextFieldAlteracaoLucrativa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldAlteracaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldAlteracaoLucrativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoLucrativaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoLucrativaKeyReleased(evt);
            }
        });

        jFormattedTextFieldAlteracaoValorVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldAlteracaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldAlteracaoValorVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoValorVendaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoValorVendaKeyReleased(evt);
            }
        });

        jFormattedTextFieldAlteracaoDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldAlteracaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldAlteracaoDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoDescontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoDescontoKeyReleased(evt);
            }
        });

        jFormattedTextFieldAlteracaoCusto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldAlteracaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldAlteracaoCusto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoCustoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoCustoKeyReleased(evt);
            }
        });

        jFormattedTextFieldAlteracaoMinima.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldAlteracaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldAlteracaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoMinimaKeyPressed(evt);
            }
        });

        jFormattedTextFieldAlteracaoMaxima.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldAlteracaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldAlteracaoMaxima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoMaximaKeyPressed(evt);
            }
        });

        jFormattedTextFieldAlteracaoEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldAlteracaoEstoque.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jFormattedTextFieldAlteracaoEstoque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAlteracaoEstoqueKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoTituloCodigo)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldAlteracaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelAlteracaoPesquisaNcm)
                                .addGap(37, 37, 37)
                                .addComponent(jCheckBoxAlteracaoInativo)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelAlteracaoDescricao))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoControle)
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldAlteracaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelAlteracaoResumida)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldAlteracaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoBarras)
                                .addGap(5, 5, 5)
                                .addComponent(jTextFieldAlteracaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelAlteracaoCompleta)
                                .addGap(21, 21, 21)
                                .addComponent(jTextFieldAlteracaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelAlteracaoData)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelAlteracaoDataInclusao)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoMinima)
                                .addGap(13, 13, 13)
                                .addComponent(jFormattedTextFieldAlteracaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAlteracaoUn)
                                .addGap(139, 139, 139)
                                .addComponent(jLabelAlteracaoUnidade)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelAlteracaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelAlteracaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelAlteracaoLucrativa)
                                .addGap(48, 48, 48)
                                .addComponent(jLabelAlteracaoValorVenda)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelAlteracaoDesconto)
                                .addGap(44, 44, 44)
                                .addComponent(jLabelAlteracaoCusto))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoEmEstoque)
                                .addGap(6, 6, 6)
                                .addComponent(jFormattedTextFieldAlteracaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelInsercaoUn1))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelAlteracaoMaxima)
                                .addGap(11, 11, 11)
                                .addComponent(jFormattedTextFieldAlteracaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAlteracaoUn2)
                                .addGap(79, 79, 79)
                                .addComponent(jLabelAlteracaoVarejo)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxAlteracaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextFieldAlteracaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldAlteracaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldAlteracaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldAlteracaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldAlteracaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldAlteracaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonAlteracaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAlteracaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAlteracaoQtdEstoque)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAlteracaoFornecedor)
                                            .addComponent(jComboBoxAlteracaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(82, 82, 82)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAlteracaoNcm)
                                            .addComponent(jTextFieldAlteracaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabeAlteracaoPesquisaNcmm)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelAlteracaoCest)
                                            .addComponent(jTextFieldAlteracaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabelAlteracaoPesquisaCest)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlteracaoData)
                    .addComponent(jLabelAlteracaoDataInclusao)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlteracaoTituloCodigo)
                            .addComponent(jTextFieldAlteracaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxAlteracaoInativo)
                            .addComponent(jLabelAlteracaoDescricao)
                            .addComponent(jLabelAlteracaoPesquisaNcm))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlteracaoControle)
                            .addComponent(jTextFieldAlteracaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAlteracaoResumida)
                            .addComponent(jTextFieldAlteracaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlteracaoBarras)
                            .addComponent(jTextFieldAlteracaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAlteracaoCompleta)
                            .addComponent(jTextFieldAlteracaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabelAlteracaoFornecedor)
                        .addGap(12, 12, 12)
                        .addComponent(jComboBoxAlteracaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabelAlteracaoNcm)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldAlteracaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabeAlteracaoPesquisaNcmm))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabelAlteracaoCest)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldAlteracaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelAlteracaoPesquisaCest)))
                .addGap(25, 25, 25)
                .addComponent(jLabelAlteracaoQtdEstoque)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlteracaoMinima)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAlteracaoUn)
                        .addComponent(jFormattedTextFieldAlteracaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelAlteracaoUnidade))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelAlteracaoCustoImp))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelAlteracaoCustoUnid))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelAlteracaoLucrativa))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelAlteracaoValorVenda))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelAlteracaoDesconto))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelAlteracaoCusto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlteracaoMaxima)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAlteracaoUn2)
                        .addComponent(jFormattedTextFieldAlteracaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelAlteracaoVarejo))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxAlteracaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jFormattedTextFieldAlteracaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldAlteracaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldAlteracaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jFormattedTextFieldAlteracaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldAlteracaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldAlteracaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAlteracaoEmEstoque)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelInsercaoUn1)
                        .addComponent(jFormattedTextFieldAlteracaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlteracaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlteracaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ALTERAÇÃO", jPanel6);

        jLabelExclusaoTituloCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoTituloCodigo.setText("Código");
        jLabelExclusaoTituloCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoTituloCodigoMouseClicked(evt);
            }
        });

        jTextFieldExclusaoCodigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoCodigoKeyPressed(evt);
            }
        });

        jLabelExclusaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoControle.setText("Controle (Extra)");
        jLabelExclusaoControle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoControleMouseClicked(evt);
            }
        });

        jTextFieldExclusaoControle.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoControle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoControleKeyPressed(evt);
            }
        });

        jLabelExclusaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoBarras.setText("Código de Barras");
        jLabelExclusaoBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoBarrasMouseClicked(evt);
            }
        });

        jTextFieldExclusaoBarras.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExclusaoBarrasActionPerformed(evt);
            }
        });
        jTextFieldExclusaoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoBarrasKeyPressed(evt);
            }
        });

        jCheckBoxExclusaoInativo.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jCheckBoxExclusaoInativo.setForeground(new java.awt.Color(255, 0, 0));
        jCheckBoxExclusaoInativo.setText("Inativo");

        jLabelExclusaoDescricao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoDescricao.setText("Descrição");

        jLabelExclusaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoResumida.setText("Resumida");
        jLabelExclusaoResumida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoResumidaMouseClicked(evt);
            }
        });

        jLabelExclusaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCompleta.setText("Completa");
        jLabelExclusaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCompletaMouseClicked(evt);
            }
        });

        jTextFieldExclusaoCompleta.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldExclusaoCompletaMouseClicked(evt);
            }
        });
        jTextFieldExclusaoCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExclusaoCompletaActionPerformed(evt);
            }
        });

        jTextFieldExclusaoResumida.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoResumida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoResumidaKeyPressed(evt);
            }
        });

        jLabelExclusaoData.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoData.setText("Inclusão");

        jLabelExclusaoDataInclusao.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoDataInclusao.setForeground(new java.awt.Color(255, 0, 0));
        jLabelExclusaoDataInclusao.setText("26/06/2018");

        jLabelExclusaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoFornecedor.setText("Fornecedor");
        jLabelExclusaoFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoFornecedorMouseClicked(evt);
            }
        });

        jComboBoxExclusaoFornecedor.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jComboBoxExclusaoFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default" }));
        jComboBoxExclusaoFornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxExclusaoFornecedorKeyPressed(evt);
            }
        });

        jTextFieldExclusaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoNcm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExclusaoNcmActionPerformed(evt);
            }
        });
        jTextFieldExclusaoNcm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoNcmKeyPressed(evt);
            }
        });

        jLabelExclusaoNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSubtracao_16px.png"))); // NOI18N
        jLabelExclusaoNcm.setText("NCM");
        jLabelExclusaoNcm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoNcmMouseClicked(evt);
            }
        });

        jLabelExclusaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCest.setText("Código CEST");
        jLabelExclusaoCest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCestMouseClicked(evt);
            }
        });

        jTextFieldExclusaoCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextFieldExclusaoCest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExclusaoCestActionPerformed(evt);
            }
        });
        jTextFieldExclusaoCest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldExclusaoCestKeyPressed(evt);
            }
        });

        jLabelExclusaoPesquisaNcm.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoPesquisaNcm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jLabelExclusaoPesquisaCest.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoPesquisaCest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N

        jLabelExclusaoQtdEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoQtdEstoque.setText("Quantidade em Estoque");

        jLabelExclusaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoMinima.setText("Quantidade Mínima");
        jLabelExclusaoMinima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoMinimaMouseClicked(evt);
            }
        });
        jLabelExclusaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelExclusaoMinimaKeyPressed(evt);
            }
        });

        jLabelExclusaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoMaxima.setText("Quantidade Máxima");
        jLabelExclusaoMaxima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoMaximaMouseClicked(evt);
            }
        });

        jLabelExclusaoEmEstoque.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoEmEstoque.setText("Quantidade Estoque");
        jLabelExclusaoEmEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoEmEstoqueMouseClicked(evt);
            }
        });

        jLabelExclusaoUn2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoUn2.setText("UN");

        jLabelExclusaoUn3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabelExclusaoUn3.setText("UN");

        jLabelExclusaoUn.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoUn.setText("UN");

        jLabelExclusaoVarejo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoVarejo.setText("Varejo");

        jComboBoxExclusaoUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UN", "KILO", "KILOGRAMAS" }));
        jComboBoxExclusaoUnidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxExclusaoUnidadeKeyPressed(evt);
            }
        });

        jLabelExclusaoUnidade.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoUnidade.setText("Unidade");
        jLabelExclusaoUnidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoUnidadeMouseClicked(evt);
            }
        });

        jLabelExclusaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCustoImp.setText("Custo S/ Imp.");
        jLabelExclusaoCustoImp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCustoImpMouseClicked(evt);
            }
        });

        jLabelExclusaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCustoUnid.setText("Custo Unid. $");
        jLabelExclusaoCustoUnid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCustoUnidMouseClicked(evt);
            }
        });

        jLabelExclusaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoLucrativa.setText("Lucrativ. %");
        jLabelExclusaoLucrativa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoLucrativaMouseClicked(evt);
            }
        });

        jLabelInclusaoValorVenda2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoValorVenda2.setText("Valor Venda $");
        jLabelInclusaoValorVenda2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoValorVenda2MouseClicked(evt);
            }
        });

        jLabelInclusaoDesconto2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelInclusaoDesconto2.setText("Desconto %");
        jLabelInclusaoDesconto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInclusaoDesconto2MouseClicked(evt);
            }
        });

        jLabelExclusaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoCusto.setText("Custo");
        jLabelExclusaoCusto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoCustoMouseClicked(evt);
            }
        });

        jButtonExclusaoExcluir.setBackground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoExcluir.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonExclusaoExcluir.setForeground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsLixeira_40px.png"))); // NOI18N
        jButtonExclusaoExcluir.setText("Excluir");
        jButtonExclusaoExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonExclusaoExcluir.setMaximumSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoExcluir.setMinimumSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoExcluir.setPreferredSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonExclusaoExcluirMouseEntered(evt);
            }
        });
        jButtonExclusaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExclusaoExcluirActionPerformed(evt);
            }
        });

        jButtonExclusaoSair.setBackground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoSair.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonExclusaoSair.setForeground(new java.awt.Color(0, 153, 255));
        jButtonExclusaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img_azul/iconsSair_40px.png"))); // NOI18N
        jButtonExclusaoSair.setText("Sair");
        jButtonExclusaoSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonExclusaoSair.setMaximumSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoSair.setMinimumSize(new java.awt.Dimension(179, 49));
        jButtonExclusaoSair.setPreferredSize(new java.awt.Dimension(179, 49));

        jLabelExclusaoPesquisa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabelExclusaoPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconSeach.png"))); // NOI18N
        jLabelExclusaoPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelExclusaoPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExclusaoPesquisaMouseClicked(evt);
            }
        });

        jFormattedTextFieldExclusaoCustoImp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldExclusaoCustoImp.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldExclusaoCustoImp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldExclusaoCustoImpKeyPressed(evt);
            }
        });

        jFormattedTextFieldExclusaoCustoUnid.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldExclusaoCustoUnid.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldExclusaoCustoUnid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldExclusaoCustoUnidKeyPressed(evt);
            }
        });

        jFormattedTextFieldExclusaoLucrativa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldExclusaoLucrativa.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldExclusaoLucrativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldExclusaoLucrativaKeyPressed(evt);
            }
        });

        jFormattedTextFieldExclusaoValorVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldExclusaoValorVenda.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldExclusaoValorVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldExclusaoValorVendaKeyPressed(evt);
            }
        });

        jFormattedTextFieldExclusaoDesconto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldExclusaoDesconto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldExclusaoDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldExclusaoDescontoKeyPressed(evt);
            }
        });

        jFormattedTextFieldExclusaoCusto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldExclusaoCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldExclusaoCusto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldExclusaoCustoKeyPressed(evt);
            }
        });

        jFormattedTextFieldExclusaoMinima.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldExclusaoMinima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldExclusaoMinima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldExclusaoMinimaKeyPressed(evt);
            }
        });

        jFormattedTextFieldExclusaoMaxima.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldExclusaoMaxima.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jFormattedTextFieldExclusaoMaxima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldExclusaoMaximaKeyPressed(evt);
            }
        });

        jFormattedTextFieldExclusaoEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextFieldExclusaoEstoque.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jFormattedTextFieldExclusaoEstoque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldExclusaoEstoqueKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoTituloCodigo)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldExclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelExclusaoPesquisa)
                                .addGap(37, 37, 37)
                                .addComponent(jCheckBoxExclusaoInativo)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelExclusaoDescricao))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoControle)
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldExclusaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelExclusaoResumida)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldExclusaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoBarras)
                                .addGap(5, 5, 5)
                                .addComponent(jTextFieldExclusaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabelExclusaoCompleta)
                                .addGap(21, 21, 21)
                                .addComponent(jTextFieldExclusaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabelExclusaoData)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelExclusaoDataInclusao)
                        .addContainerGap(227, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExclusaoQtdEstoque)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoMinima)
                                .addGap(13, 13, 13)
                                .addComponent(jFormattedTextFieldExclusaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelExclusaoUn)
                                .addGap(139, 139, 139)
                                .addComponent(jLabelExclusaoUnidade)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelExclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelExclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabelExclusaoLucrativa)
                                .addGap(48, 48, 48)
                                .addComponent(jLabelInclusaoValorVenda2)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelInclusaoDesconto2)
                                .addGap(44, 44, 44)
                                .addComponent(jLabelExclusaoCusto))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoEmEstoque)
                                .addGap(6, 6, 6)
                                .addComponent(jFormattedTextFieldExclusaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelExclusaoUn3))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelExclusaoMaxima)
                                .addGap(11, 11, 11)
                                .addComponent(jFormattedTextFieldExclusaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelExclusaoUn2)
                                .addGap(79, 79, 79)
                                .addComponent(jLabelExclusaoVarejo)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxExclusaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextFieldExclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldExclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldExclusaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldExclusaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldExclusaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jFormattedTextFieldExclusaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(143, 143, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonExclusaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonExclusaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelExclusaoFornecedor)
                                    .addComponent(jComboBoxExclusaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(82, 82, 82)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelExclusaoNcm)
                                    .addComponent(jTextFieldExclusaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addComponent(jLabelExclusaoPesquisaNcm)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelExclusaoCest)
                                    .addComponent(jTextFieldExclusaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addComponent(jLabelExclusaoPesquisaCest)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(381, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExclusaoData)
                    .addComponent(jLabelExclusaoDataInclusao)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExclusaoTituloCodigo)
                            .addComponent(jTextFieldExclusaoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxExclusaoInativo)
                            .addComponent(jLabelExclusaoDescricao)
                            .addComponent(jLabelExclusaoPesquisa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExclusaoControle)
                            .addComponent(jTextFieldExclusaoControle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelExclusaoResumida)
                            .addComponent(jTextFieldExclusaoResumida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelExclusaoBarras)
                            .addComponent(jTextFieldExclusaoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelExclusaoCompleta)
                            .addComponent(jTextFieldExclusaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabelExclusaoFornecedor)
                        .addGap(12, 12, 12)
                        .addComponent(jComboBoxExclusaoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabelExclusaoNcm)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldExclusaoNcm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelExclusaoPesquisaNcm))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabelExclusaoCest)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldExclusaoCest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabelExclusaoPesquisaCest)))
                .addGap(25, 25, 25)
                .addComponent(jLabelExclusaoQtdEstoque)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExclusaoMinima)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelExclusaoUn)
                        .addComponent(jFormattedTextFieldExclusaoMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelExclusaoUnidade))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelExclusaoCustoImp))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelExclusaoCustoUnid))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelExclusaoLucrativa))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoValorVenda2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelInclusaoDesconto2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelExclusaoCusto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExclusaoMaxima)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelExclusaoUn2)
                        .addComponent(jFormattedTextFieldExclusaoMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelExclusaoVarejo))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jComboBoxExclusaoUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldExclusaoCustoImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldExclusaoCustoUnid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldExclusaoLucrativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldExclusaoValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldExclusaoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldExclusaoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelExclusaoEmEstoque)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelExclusaoUn3)
                        .addComponent(jFormattedTextFieldExclusaoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExclusaoSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExclusaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("EXCLUSÃO", jPanel8);

        jPanel5.setBackground(new java.awt.Color(0, 153, 255));

        jLabelF5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF5.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF5.setText("F5 - Limpar dados");

        jLabelF12.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF12.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF12.setText("F12 - Fechar janela");

        jLabelF1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF1.setText("F1 - Historico de Manutenção");

        jLabelF2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF2.setText("F2 - Gravar Inserção");

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

        jLabelF6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF6.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF6.setText("F4 - Gravar Alteração");

        jLabelF7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabelF7.setForeground(new java.awt.Color(255, 255, 255));
        jLabelF7.setText("F6 - Gravar Exclusão");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelF1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelF2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelF6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelF5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelF7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelF12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelBalloon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLogoBalloon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogoBalloon, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelF2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelF7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelAlteracaoTituloCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoTituloCodigoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoTituloCodigoMouseClicked

    private void jTextFieldAlteracaoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCodigoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        ExibirAlteracao();
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCodigoKeyPressed

    private void jLabelAlteracaoControleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoControleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoControleMouseClicked

    private void jTextFieldAlteracaoControleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoControleKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoControleKeyPressed

    private void jLabelAlteracaoBarrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoBarrasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoBarrasMouseClicked

    private void jTextFieldAlteracaoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoBarrasActionPerformed

    private void jTextFieldAlteracaoBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoBarrasKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoBarrasKeyPressed

    private void jLabelAlteracaoResumidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoResumidaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoResumidaMouseClicked

    private void jLabelAlteracaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCompletaMouseClicked

    private void jTextFieldAlteracaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoCompletaMouseClicked

    private void jTextFieldAlteracaoCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCompletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoCompletaActionPerformed

    private void jTextFieldAlteracaoCompletaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCompletaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCompletaKeyPressed

    private void jTextFieldAlteracaoResumidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoResumidaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoResumidaKeyPressed

    private void jLabelAlteracaoFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoFornecedorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoFornecedorMouseClicked

    private void jComboBoxAlteracaoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxAlteracaoFornecedorKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxAlteracaoFornecedorKeyPressed

    private void jTextFieldAlteracaoNcmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoNcmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoNcmActionPerformed

    private void jTextFieldAlteracaoNcmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoNcmKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoNcmKeyPressed

    private void jLabelAlteracaoNcmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoNcmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoNcmMouseClicked

    private void jLabelAlteracaoCestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCestMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCestMouseClicked

    private void jTextFieldAlteracaoCestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlteracaoCestActionPerformed

    private void jTextFieldAlteracaoCestKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAlteracaoCestKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldAlteracaoCestKeyPressed

    private void jLabelAlteracaoMinimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoMinimaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoMinimaMouseClicked

    private void jLabelAlteracaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelAlteracaoMinimaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoMinimaKeyPressed

    private void jLabelAlteracaoMaximaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoMaximaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoMaximaMouseClicked

    private void jLabelAlteracaoEmEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoEmEstoqueMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoEmEstoqueMouseClicked

    private void jLabelAlteracaoUnidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoUnidadeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoUnidadeMouseClicked

    private void jLabelAlteracaoCustoImpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCustoImpMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCustoImpMouseClicked

    private void jLabelAlteracaoCustoUnidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCustoUnidMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCustoUnidMouseClicked

    private void jLabelAlteracaoLucrativaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoLucrativaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoLucrativaMouseClicked

    private void jLabelAlteracaoValorVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoValorVendaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoValorVendaMouseClicked

    private void jLabelAlteracaoDescontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoDescontoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoDescontoMouseClicked

    private void jLabelAlteracaoCustoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoCustoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelAlteracaoCustoMouseClicked

    private void jButtonAlteracaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlteracaoSalvarActionPerformed
        RealizarAlteracao();
    }//GEN-LAST:event_jButtonAlteracaoSalvarActionPerformed

    private void jLabelAlteracaoPesquisaNcmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlteracaoPesquisaNcmMouseClicked
        ExibirAlteracao();
        visibilidadePreAlteracao();
    }//GEN-LAST:event_jLabelAlteracaoPesquisaNcmMouseClicked

    private void jLabelExclusaoTituloCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoTituloCodigoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoTituloCodigoMouseClicked

    private void jTextFieldExclusaoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCodigoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        ExibirExclusao();
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoCodigoKeyPressed

    private void jLabelExclusaoControleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoControleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoControleMouseClicked

    private void jTextFieldExclusaoControleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoControleKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoControleKeyPressed

    private void jLabelExclusaoBarrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoBarrasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoBarrasMouseClicked

    private void jTextFieldExclusaoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoBarrasActionPerformed

    private void jTextFieldExclusaoBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoBarrasKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoBarrasKeyPressed

    private void jLabelExclusaoResumidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoResumidaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoResumidaMouseClicked

    private void jLabelExclusaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCompletaMouseClicked

    private void jTextFieldExclusaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoCompletaMouseClicked

    private void jTextFieldExclusaoCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCompletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoCompletaActionPerformed

    private void jTextFieldExclusaoResumidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoResumidaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoResumidaKeyPressed

    private void jLabelExclusaoFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoFornecedorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoFornecedorMouseClicked

    private void jComboBoxExclusaoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxExclusaoFornecedorKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxExclusaoFornecedorKeyPressed

    private void jTextFieldExclusaoNcmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoNcmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoNcmActionPerformed

    private void jTextFieldExclusaoNcmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoNcmKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoNcmKeyPressed

    private void jLabelExclusaoNcmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoNcmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoNcmMouseClicked

    private void jLabelExclusaoCestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCestMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCestMouseClicked

    private void jTextFieldExclusaoCestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExclusaoCestActionPerformed

    private void jTextFieldExclusaoCestKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldExclusaoCestKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldExclusaoCestKeyPressed

    private void jLabelExclusaoMinimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoMinimaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoMinimaMouseClicked

    private void jLabelExclusaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelExclusaoMinimaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoMinimaKeyPressed

    private void jLabelExclusaoMaximaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoMaximaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoMaximaMouseClicked

    private void jLabelExclusaoEmEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoEmEstoqueMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoEmEstoqueMouseClicked

    private void jComboBoxExclusaoUnidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxExclusaoUnidadeKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxExclusaoUnidadeKeyPressed

    private void jLabelExclusaoUnidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoUnidadeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoUnidadeMouseClicked

    private void jLabelExclusaoCustoImpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCustoImpMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCustoImpMouseClicked

    private void jLabelExclusaoCustoUnidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCustoUnidMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCustoUnidMouseClicked

    private void jLabelExclusaoLucrativaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoLucrativaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoLucrativaMouseClicked

    private void jLabelInclusaoValorVenda2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoValorVenda2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelInclusaoValorVenda2MouseClicked

    private void jLabelInclusaoDesconto2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoDesconto2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelInclusaoDesconto2MouseClicked

    private void jLabelExclusaoCustoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoCustoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExclusaoCustoMouseClicked

    private void jButtonExclusaoExcluirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonExclusaoExcluirMouseEntered
        jButtonExclusaoExcluir.setBackground(Color.red);
    }//GEN-LAST:event_jButtonExclusaoExcluirMouseEntered

    private void jButtonExclusaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExclusaoExcluirActionPerformed
        try{
            if(!(jTextFieldExclusaoCodigo.getText().equals(""))){
                int codigo = Integer.parseInt(jTextFieldExclusaoCodigo.getText());
                excluirItem(codigo);
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(rootPane, "Informe apenas números");
        }
    }//GEN-LAST:event_jButtonExclusaoExcluirActionPerformed

    private void jLabelExclusaoPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExclusaoPesquisaMouseClicked
        ExibirExclusao();
    }//GEN-LAST:event_jLabelExclusaoPesquisaMouseClicked

    private void jLabelLogoBalloonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoBalloonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLogoBalloonMouseClicked

    private void jFormattedTextFieldInclusaoLucrativaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoLucrativaKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
        realizaCalculoInsercao(1);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoLucrativaKeyReleased

    private void jFormattedTextFieldInclusaoLucrativaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoLucrativaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoLucrativaKeyPressed

    private void jFormattedTextFieldInclusaoCustoUnidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoCustoUnidKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoCustoUnidKeyPressed

    private void jFormattedTextFieldInclusaoCustoImpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoCustoImpKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoCustoImpKeyPressed

    private void jButtonInclusaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInclusaoSalvarActionPerformed
        RealizarInclusao();
    }//GEN-LAST:event_jButtonInclusaoSalvarActionPerformed

    private void jLabelInclusaoCustoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCustoMouseClicked
        jFormattedTextFieldInclusaoCusto.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCustoMouseClicked

    private void jLabelInclusaoDescontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoDescontoMouseClicked
        jFormattedTextFieldInclusaoDesconto.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoDescontoMouseClicked

    private void jLabelInclusaoValorVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoValorVendaMouseClicked
        jFormattedTextFieldInclusaoValorVenda.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoValorVendaMouseClicked

    private void jLabelInclusaoLucrativaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoLucrativaMouseClicked
        jFormattedTextFieldInclusaoLucrativa.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoLucrativaMouseClicked

    private void jLabelInclusaoCustoUnidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCustoUnidMouseClicked
        jFormattedTextFieldInclusaoCustoUnid.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCustoUnidMouseClicked

    private void jLabelInclusaoCustoImpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCustoImpMouseClicked
        jFormattedTextFieldInclusaoCustoImp.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCustoImpMouseClicked

    private void jLabelInclusaoUnidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoUnidadeMouseClicked
        jComboBoxInclusaoUnidade.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoUnidadeMouseClicked

    private void jComboBoxInclusaoUnidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxInclusaoUnidadeKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxInclusaoUnidadeKeyPressed

    private void jLabelInclusaoEmEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoEmEstoqueMouseClicked
        jFormattedTextFieldInclusaoEstoque.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoEmEstoqueMouseClicked

    private void jLabelInclusaoMaximaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoMaximaMouseClicked
        jFormattedTextFieldInclusaoMaxima.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoMaximaMouseClicked

    private void jLabelInclusaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelInclusaoMinimaKeyPressed
        jFormattedTextFieldInclusaoMinima.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoMinimaKeyPressed

    private void jLabelInclusaoMinimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoMinimaMouseClicked
        jFormattedTextFieldInclusaoMinima.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoMinimaMouseClicked

    private void jTextFieldInclusaoCestKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCestKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoCestKeyPressed

    private void jTextFieldInclusaoCestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoCestActionPerformed

    private void jLabelInclusaoCestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCestMouseClicked
        jTextFieldInclusaoCest.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCestMouseClicked

    private void jLabelInclusaoNcmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoNcmMouseClicked
        jTextFieldInclusaoNcm.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoNcmMouseClicked

    private void jTextFieldInclusaoNcmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoNcmKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoNcmKeyPressed

    private void jTextFieldInclusaoNcmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoNcmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoNcmActionPerformed

    private void jComboBoxInsersaoFornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxInsersaoFornecedorKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jComboBoxInsersaoFornecedorKeyPressed

    private void jLabelInclusaoFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoFornecedorMouseClicked
        jComboBoxInsersaoFornecedor.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoFornecedorMouseClicked

    private void jTextFieldInclusaoResumidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoResumidaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoResumidaKeyPressed

    private void jTextFieldInclusaoCompletaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCompletaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoCompletaKeyPressed

    private void jTextFieldInclusaoCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCompletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoCompletaActionPerformed

    private void jTextFieldInclusaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCompletaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoCompletaMouseClicked

    private void jLabelInclusaoCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoCompletaMouseClicked
        jTextFieldInclusaoCompleta.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoCompletaMouseClicked

    private void jLabelInclusaoResumidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoResumidaMouseClicked
        jTextFieldInclusaoResumida.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoResumidaMouseClicked

    private void jTextFieldInclusaoBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoBarrasKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoBarrasKeyPressed

    private void jTextFieldInclusaoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInclusaoBarrasActionPerformed

    private void jLabelInclusaoBarrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoBarrasMouseClicked
        jTextFieldInclusaoBarras.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoBarrasMouseClicked

    private void jTextFieldInclusaoControleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoControleKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoControleKeyPressed

    private void jLabelInclusaoControleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoControleMouseClicked
        jTextFieldInclusaoControle.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoControleMouseClicked

    private void jTextFieldInclusaoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInclusaoCodigoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jTextFieldInclusaoCodigoKeyPressed

    private void jLabelInclusaoTituloCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInclusaoTituloCodigoMouseClicked
        jTextFieldInclusaoCodigo.requestFocus();
    }//GEN-LAST:event_jLabelInclusaoTituloCodigoMouseClicked

    private void jFormattedTextFieldInclusaoValorVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoValorVendaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoValorVendaKeyPressed

    private void jFormattedTextFieldInclusaoValorVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoValorVendaKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
            realizaCalculoInsercao(2);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoValorVendaKeyReleased

    private void jFormattedTextFieldInclusaoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoDescontoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoDescontoKeyPressed

    private void jFormattedTextFieldInclusaoDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoDescontoKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
            realizaCalculoInsercao(3);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoDescontoKeyReleased

    private void jFormattedTextFieldInclusaoCustoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoCustoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoCustoKeyPressed

    private void jFormattedTextFieldInclusaoCustoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoCustoKeyReleased
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
            realizaCalculoInsercao(4);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoCustoKeyReleased

    private void jFormattedTextFieldAlteracaoCustoImpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoCustoImpKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoCustoImpKeyPressed

    private void jFormattedTextFieldAlteracaoCustoUnidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoCustoUnidKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoCustoUnidKeyPressed

    private void jFormattedTextFieldAlteracaoLucrativaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoLucrativaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoLucrativaKeyPressed

    private void jFormattedTextFieldAlteracaoLucrativaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoLucrativaKeyReleased
        realizaCalculoAlteracao(1);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoLucrativaKeyReleased

    private void jFormattedTextFieldAlteracaoValorVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoValorVendaKeyPressed
       atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoValorVendaKeyPressed

    private void jFormattedTextFieldAlteracaoValorVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoValorVendaKeyReleased
       realizaCalculoAlteracao(2);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoValorVendaKeyReleased

    private void jFormattedTextFieldAlteracaoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoDescontoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoDescontoKeyPressed

    private void jFormattedTextFieldAlteracaoDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoDescontoKeyReleased
        realizaCalculoAlteracao(3);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoDescontoKeyReleased

    private void jFormattedTextFieldAlteracaoCustoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoCustoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoCustoKeyPressed

    private void jFormattedTextFieldAlteracaoCustoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoCustoKeyReleased
        realizaCalculoAlteracao(4);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoCustoKeyReleased

    private void jFormattedTextFieldExclusaoCustoImpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldExclusaoCustoImpKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldExclusaoCustoImpKeyPressed

    private void jFormattedTextFieldExclusaoCustoUnidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldExclusaoCustoUnidKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldExclusaoCustoUnidKeyPressed

    private void jFormattedTextFieldExclusaoLucrativaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldExclusaoLucrativaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldExclusaoLucrativaKeyPressed

    private void jFormattedTextFieldExclusaoValorVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldExclusaoValorVendaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldExclusaoValorVendaKeyPressed

    private void jFormattedTextFieldExclusaoDescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldExclusaoDescontoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldExclusaoDescontoKeyPressed

    private void jFormattedTextFieldExclusaoCustoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldExclusaoCustoKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldExclusaoCustoKeyPressed

    private void jFormattedTextFieldInclusaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoMinimaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoMinimaKeyPressed

    private void jFormattedTextFieldInclusaoMaximaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoMaximaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoMaximaKeyPressed

    private void jFormattedTextFieldInclusaoEstoqueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldInclusaoEstoqueKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldInclusaoEstoqueKeyPressed

    private void jFormattedTextFieldAlteracaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoMinimaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoMinimaKeyPressed

    private void jFormattedTextFieldAlteracaoMaximaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoMaximaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoMaximaKeyPressed

    private void jFormattedTextFieldAlteracaoEstoqueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAlteracaoEstoqueKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldAlteracaoEstoqueKeyPressed

    private void jFormattedTextFieldExclusaoMinimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldExclusaoMinimaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldExclusaoMinimaKeyPressed

    private void jFormattedTextFieldExclusaoMaximaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldExclusaoMaximaKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldExclusaoMaximaKeyPressed

    private void jFormattedTextFieldExclusaoEstoqueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldExclusaoEstoqueKeyPressed
        atalhos(evt);
    }//GEN-LAST:event_jFormattedTextFieldExclusaoEstoqueKeyPressed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        this.dispose();
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
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoqueNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoqueNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoqueNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoqueNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JanelaManutencaoEstoqueNova dialog = new JanelaManutencaoEstoqueNova(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonExclusaoExcluir;
    private javax.swing.JButton jButtonExclusaoSair;
    private javax.swing.JButton jButtonInclusaoSair;
    private javax.swing.JButton jButtonInclusaoSalvar;
    private javax.swing.JCheckBox jCheckBoxAlteracaoInativo;
    private javax.swing.JCheckBox jCheckBoxExclusaoInativo;
    private javax.swing.JCheckBox jCheckBoxInclusaoInativo;
    private javax.swing.JComboBox<String> jComboBoxAlteracaoFornecedor;
    private javax.swing.JComboBox<String> jComboBoxAlteracaoUnidade;
    private javax.swing.JComboBox<String> jComboBoxExclusaoFornecedor;
    private javax.swing.JComboBox<String> jComboBoxExclusaoUnidade;
    private javax.swing.JComboBox<String> jComboBoxInclusaoUnidade;
    private javax.swing.JComboBox<String> jComboBoxInsersaoFornecedor;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoCusto;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoCustoImp;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoCustoUnid;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoEstoque;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoLucrativa;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoMaxima;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoMinima;
    private javax.swing.JFormattedTextField jFormattedTextFieldAlteracaoValorVenda;
    private javax.swing.JFormattedTextField jFormattedTextFieldExclusaoCusto;
    private javax.swing.JFormattedTextField jFormattedTextFieldExclusaoCustoImp;
    private javax.swing.JFormattedTextField jFormattedTextFieldExclusaoCustoUnid;
    private javax.swing.JFormattedTextField jFormattedTextFieldExclusaoDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldExclusaoEstoque;
    private javax.swing.JFormattedTextField jFormattedTextFieldExclusaoLucrativa;
    private javax.swing.JFormattedTextField jFormattedTextFieldExclusaoMaxima;
    private javax.swing.JFormattedTextField jFormattedTextFieldExclusaoMinima;
    private javax.swing.JFormattedTextField jFormattedTextFieldExclusaoValorVenda;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoCusto;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoCustoImp;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoCustoUnid;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoDesconto;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoEstoque;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoLucrativa;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoMaxima;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoMinima;
    private javax.swing.JFormattedTextField jFormattedTextFieldInclusaoValorVenda;
    private javax.swing.JLabel jLabeAlteracaoPesquisaNcmm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAlteracaoBarras;
    private javax.swing.JLabel jLabelAlteracaoCest;
    private javax.swing.JLabel jLabelAlteracaoCompleta;
    private javax.swing.JLabel jLabelAlteracaoControle;
    private javax.swing.JLabel jLabelAlteracaoCusto;
    private javax.swing.JLabel jLabelAlteracaoCustoImp;
    private javax.swing.JLabel jLabelAlteracaoCustoUnid;
    private javax.swing.JLabel jLabelAlteracaoData;
    private javax.swing.JLabel jLabelAlteracaoDataInclusao;
    private javax.swing.JLabel jLabelAlteracaoDesconto;
    private javax.swing.JLabel jLabelAlteracaoDescricao;
    private javax.swing.JLabel jLabelAlteracaoEmEstoque;
    private javax.swing.JLabel jLabelAlteracaoFornecedor;
    private javax.swing.JLabel jLabelAlteracaoLucrativa;
    private javax.swing.JLabel jLabelAlteracaoMaxima;
    private javax.swing.JLabel jLabelAlteracaoMinima;
    private javax.swing.JLabel jLabelAlteracaoNcm;
    private javax.swing.JLabel jLabelAlteracaoPesquisaCest;
    private javax.swing.JLabel jLabelAlteracaoPesquisaNcm;
    private javax.swing.JLabel jLabelAlteracaoQtdEstoque;
    private javax.swing.JLabel jLabelAlteracaoResumida;
    private javax.swing.JLabel jLabelAlteracaoTituloCodigo;
    private javax.swing.JLabel jLabelAlteracaoUn;
    private javax.swing.JLabel jLabelAlteracaoUn2;
    private javax.swing.JLabel jLabelAlteracaoUnidade;
    private javax.swing.JLabel jLabelAlteracaoValorVenda;
    private javax.swing.JLabel jLabelAlteracaoVarejo;
    private javax.swing.JLabel jLabelBalloon;
    private javax.swing.JLabel jLabelExclusaoBarras;
    private javax.swing.JLabel jLabelExclusaoCest;
    private javax.swing.JLabel jLabelExclusaoCompleta;
    private javax.swing.JLabel jLabelExclusaoControle;
    private javax.swing.JLabel jLabelExclusaoCusto;
    private javax.swing.JLabel jLabelExclusaoCustoImp;
    private javax.swing.JLabel jLabelExclusaoCustoUnid;
    private javax.swing.JLabel jLabelExclusaoData;
    private javax.swing.JLabel jLabelExclusaoDataInclusao;
    private javax.swing.JLabel jLabelExclusaoDescricao;
    private javax.swing.JLabel jLabelExclusaoEmEstoque;
    private javax.swing.JLabel jLabelExclusaoFornecedor;
    private javax.swing.JLabel jLabelExclusaoLucrativa;
    private javax.swing.JLabel jLabelExclusaoMaxima;
    private javax.swing.JLabel jLabelExclusaoMinima;
    private javax.swing.JLabel jLabelExclusaoNcm;
    private javax.swing.JLabel jLabelExclusaoPesquisa;
    private javax.swing.JLabel jLabelExclusaoPesquisaCest;
    private javax.swing.JLabel jLabelExclusaoPesquisaNcm;
    private javax.swing.JLabel jLabelExclusaoQtdEstoque;
    private javax.swing.JLabel jLabelExclusaoResumida;
    private javax.swing.JLabel jLabelExclusaoTituloCodigo;
    private javax.swing.JLabel jLabelExclusaoUn;
    private javax.swing.JLabel jLabelExclusaoUn2;
    private javax.swing.JLabel jLabelExclusaoUn3;
    private javax.swing.JLabel jLabelExclusaoUnidade;
    private javax.swing.JLabel jLabelExclusaoVarejo;
    private javax.swing.JLabel jLabelF1;
    private javax.swing.JLabel jLabelF12;
    private javax.swing.JLabel jLabelF2;
    private javax.swing.JLabel jLabelF5;
    private javax.swing.JLabel jLabelF6;
    private javax.swing.JLabel jLabelF7;
    private javax.swing.JLabel jLabelInclusaoBarras;
    private javax.swing.JLabel jLabelInclusaoCest;
    private javax.swing.JLabel jLabelInclusaoCompleta;
    private javax.swing.JLabel jLabelInclusaoControle;
    private javax.swing.JLabel jLabelInclusaoCusto;
    private javax.swing.JLabel jLabelInclusaoCustoImp;
    private javax.swing.JLabel jLabelInclusaoCustoUnid;
    private javax.swing.JLabel jLabelInclusaoData;
    private javax.swing.JLabel jLabelInclusaoDataInclusao;
    private javax.swing.JLabel jLabelInclusaoDesconto;
    private javax.swing.JLabel jLabelInclusaoDesconto2;
    private javax.swing.JLabel jLabelInclusaoDescricao;
    private javax.swing.JLabel jLabelInclusaoEmEstoque;
    private javax.swing.JLabel jLabelInclusaoFornecedor;
    private javax.swing.JLabel jLabelInclusaoLucrativa;
    private javax.swing.JLabel jLabelInclusaoMaxima;
    private javax.swing.JLabel jLabelInclusaoMinima;
    private javax.swing.JLabel jLabelInclusaoNcm;
    private javax.swing.JLabel jLabelInclusaoPesquisaCest;
    private javax.swing.JLabel jLabelInclusaoPesquisaNcm;
    private javax.swing.JLabel jLabelInclusaoQtdEstoque;
    private javax.swing.JLabel jLabelInclusaoResumida;
    private javax.swing.JLabel jLabelInclusaoTituloCodigo;
    private javax.swing.JLabel jLabelInclusaoUnidade;
    private javax.swing.JLabel jLabelInclusaoValorVenda;
    private javax.swing.JLabel jLabelInclusaoValorVenda2;
    private javax.swing.JLabel jLabelInclusaoVarejo;
    private javax.swing.JLabel jLabelInsercaoUn;
    private javax.swing.JLabel jLabelInsercaoUn1;
    private javax.swing.JLabel jLabelInsercaoUn2;
    private javax.swing.JLabel jLabelInsercaoUn3;
    private javax.swing.JLabel jLabelLogoBalloon;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldAlteracaoBarras;
    private javax.swing.JTextField jTextFieldAlteracaoCest;
    private javax.swing.JTextField jTextFieldAlteracaoCodigo;
    private javax.swing.JTextField jTextFieldAlteracaoCompleta;
    private javax.swing.JTextField jTextFieldAlteracaoControle;
    private javax.swing.JTextField jTextFieldAlteracaoNcm;
    private javax.swing.JTextField jTextFieldAlteracaoResumida;
    private javax.swing.JTextField jTextFieldExclusaoBarras;
    private javax.swing.JTextField jTextFieldExclusaoCest;
    private javax.swing.JTextField jTextFieldExclusaoCodigo;
    private javax.swing.JTextField jTextFieldExclusaoCompleta;
    private javax.swing.JTextField jTextFieldExclusaoControle;
    private javax.swing.JTextField jTextFieldExclusaoNcm;
    private javax.swing.JTextField jTextFieldExclusaoResumida;
    private javax.swing.JTextField jTextFieldInclusaoBarras;
    private javax.swing.JTextField jTextFieldInclusaoCest;
    private javax.swing.JTextField jTextFieldInclusaoCodigo;
    private javax.swing.JTextField jTextFieldInclusaoCompleta;
    private javax.swing.JTextField jTextFieldInclusaoControle;
    private javax.swing.JTextField jTextFieldInclusaoNcm;
    private javax.swing.JTextField jTextFieldInclusaoResumida;
    // End of variables declaration//GEN-END:variables
}
