/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Fornecedor;


/**
 *
 * @author gabri
 */
public class FornecedorDAO {
    
    public void create(Fornecedor f){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("insert into fornecedor(codigo, tipo,razaoSocial, nomeInstituicao, cep," +
                                        "uf, cidade, bairro, rua, numero, complemento, email, fixo, celular, cnpj) " +
                                        "values(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);" );
            
            stmt.setString(1,f.getTipo());
            stmt.setString(2,f.getRazaoSocial());
            stmt.setString(3,f.getNomeInstituicao());
            stmt.setString(4,f.getCep());
            stmt.setString(5,f.getUf());
            stmt.setString(6,f.getCidade());
            stmt.setString(7,f.getBairro());
            stmt.setString(8,f.getRua());
            stmt.setString(9,f.getNumero());
            stmt.setString(10,f.getComplemento());
            stmt.setString(11,f.getEmail());
            stmt.setString(12,f.getFixo());
            stmt.setString(13,f.getCelular());
            stmt.setString(14,f.getCnpj());

            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fornecedor inserido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir Fornecedor");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public void update(Fornecedor f){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE fornecedor " +
                                        "set razaoSocial = ?, " +
                                        "tipo = ?, " +
                                        "nomeInstituicao = ?, " +
                                        "cep = ?, " +
                                        "uf = ?, " +
                                        "cidade = ?, " +
                                        "bairro = ?, " +
                                        "rua = ?, " +
                                        "numero = ?, " +
                                        "complemento = ?, " +
                                        "email = ?, " +
                                        "fixo = ?, " +
                                        "celular = ? ," +
                                        "cnpj = ? " +
                                        "where codigo = ?;" );
            
            stmt.setString(1,f.getRazaoSocial());
            stmt.setString(2,f.getTipo());
            stmt.setString(3,f.getNomeInstituicao());
            stmt.setString(4,f.getCep());
            stmt.setString(5,f.getUf());
            stmt.setString(6,f.getCidade());
            stmt.setString(7,f.getBairro());
            stmt.setString(8,f.getRua());
            stmt.setString(9,f.getNumero());
            stmt.setString(10,f.getComplemento());
            stmt.setString(11,f.getEmail());
            stmt.setString(12,f.getFixo());
            stmt.setString(13,f.getCelular());
            stmt.setString(14,f.getCnpj());
            stmt.setInt(15,f.getCodigo());

            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar Fornecedor");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
 
    public List<Fornecedor> read(){
        Fornecedor f;
        List<Fornecedor>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedor");
            rs = stmt.executeQuery();

            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String tipo = rs.getString("tipo");
                String razaoSocial = rs.getString("razaoSocial");
                String nomeInstituicao = rs.getString("nomeInstituicao");
                String cep = rs.getString("cep");
                String uf = rs.getString("uf");
                String cidade = rs.getString("cidade");
                String bairro = rs.getString("bairro");
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String complemento = rs.getString("complemento");
                String email = rs.getString("email");
                String fixo = rs.getString("fixo");
                String celular = rs.getString("celular");
                String cnpj = rs.getString("cnpj");
           
                f = new Fornecedor(codigo, razaoSocial,  nomeInstituicao,  cep,uf,  cidade, 
                        bairro,  rua,  numero,  complemento, email,  fixo, celular,  cnpj,  tipo);

                lista.add(f);
            }
            
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Fornecedor");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
    

}
