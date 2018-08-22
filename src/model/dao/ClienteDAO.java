

package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.bean.ClienteFisico;
import model.bean.ClienteJuridico;



public class ClienteDAO {
    
    public void createFisico(ClienteFisico c){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO clienteFisico(codigo, cpf, nome, sexo, dataNascimento, rg,"
                    + " orgaoExpeditor, permitecredito, cepCliente, ufCliente, ruaCliente, numeroCasa, complementoCliente, "
                    + "bairroCliente, cidadeCliente, emailCliente, renda,limiteCompra, mae, pai, telefoneCliente)"
                    +"VALUES(default,?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            stmt.setString(1, c.getCpf());
            stmt.setString(2,c.getNome());
            stmt.setString(3, c.getSexo());
            stmt.setString(4,c.getDataNascFund());
            stmt.setString(5, c.getRg());
            stmt.setString(6,c.getOrgaoExpeditor());
            stmt.setBoolean(7, c.isPermitecredito());
            stmt.setString(8,c.getCep());
            stmt.setString(9, c.getUf());
            stmt.setString(10,c.getRua());
            stmt.setString(11, c.getNumeroCasa());
            stmt.setString(12,c.getComplemento());
            stmt.setString(13, c.getBairro());
            stmt.setString(14, c.getCidade());
            stmt.setString(15, c.getEmail());
            stmt.setDouble(16, c.getRenda());
            stmt.setDouble(17, c.getLimiteCompra());
            stmt.setString(18, c.getMae());
            stmt.setString(19, c.getPai());
            stmt.setString(20, c.getTelefone());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente Fisico inserido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir Cliente Fisico");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public void createJuridico(ClienteJuridico c){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("insert into clienteJuridica(codigo, cnpj,nomeFantasia,dataFundacao,permitecredito"
                    + ",cepJuridica,ruaJuridica,numeroJuridica,complementoJuridica,bairroJuridica,ufJuridica,cidadeJuridica"
                    + ",emailJuridica,rendaJuridica,limiteCompraJuridica,telefoneJuridica )"
                    + "values(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            
            stmt.setString(1, c.getCnpj());
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getDataNascFund());
            stmt.setBoolean(4, c.isPermitecredito());
            stmt.setString(5, c.getCep());
            stmt.setString(6, c.getRua());
            stmt.setString(7, c.getNumeroCasa());
            stmt.setString(8, c.getComplemento());
            stmt.setString(9, c.getBairro());
            stmt.setString(10, c.getUf());
            stmt.setString(11, c.getCidade());
            stmt.setString(12, c.getEmail());
            stmt.setDouble(13, c.getRenda());
            stmt.setDouble(14, c.getLimiteCompra());
            stmt.setString(15, c.getTelefone());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente Juridico inserido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir Cliente Juridico");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public void updateJuridico(ClienteJuridico c){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("update clienteJuridica "
                                      + "set nomeFantasia = ?" 
                                      + ",dataFundacao = ? "
                                      + ",permitecredito = ?" +
                                        ",cepJuridica = ?" +
                                        ",ruaJuridica = ?" +
                                        ",numeroJuridica = ?" +
                                        ",complementoJuridica = ?" +
                                        ",bairroJuridica = ?" +
                                        ",ufJuridica = ?" +
                                        ",cidadeJuridica = ?" +
                                        ",emailJuridica = ?" +
                                        ",rendaJuridica = ?" +
                                        ",limiteCompraJuridica = ?" +
                                        ",telefoneJuridica = ?" +
                                        "where cnpj = ?;");
            
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDataNascFund());
            stmt.setBoolean(3, c.isPermitecredito());
            stmt.setString(4, c.getCep());
            stmt.setString(5, c.getRua());
            stmt.setString(6, c.getNumeroCasa());
            stmt.setString(7, c.getComplemento());
            stmt.setString(8, c.getBairro());
            stmt.setString(9, c.getUf());
            stmt.setString(10, c.getCidade());
            stmt.setString(11, c.getEmail());
            stmt.setDouble(12, c.getRenda());
            stmt.setDouble(13, c.getLimiteCompra());
            stmt.setString(14, c.getTelefone());
            stmt.setString(15, c.getCnpj());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente Juridico atualizar com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar Cliente Juridico");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public void updateFisico(ClienteFisico c){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement("update clientefisico "
                                      + "set nome = ?" 
                                      + ",sexo = ? "
                                      + ",dataNascimento = ?" +
                                        ",rg = ?" +
                                        ",orgaoExpeditor = ?" +
                                        ",permitecredito = ?" +
                                        ",cepCliente = ?" +
                                        ",ufCliente = ?" +
                                        ",ruaCliente = ?" +
                                        ",numeroCasa = ?" +
                                        ",complementoCliente = ?" +
                                        ",bairroCliente = ?" +
                                        ",cidadeCliente = ?" +
                                        ",emailCliente = ?" +
                                        ",renda = ?" +
                                        ",limiteCompra = ?" +
                                        ",mae = ?" +
                                        ",pai = ?" +
                                        ",telefoneCliente = ?" +
                                        "where cpf = ?;");
            

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getSexo());
            stmt.setString(3, c.getDataNascFund());
            stmt.setString(4, c.getRg());
            stmt.setString(5, c.getOrgaoExpeditor());
            stmt.setBoolean(6, c.isPermitecredito());
            stmt.setString(7, c.getCep());
            stmt.setString(8, c.getUf());
            stmt.setString(9, c.getRua());
            stmt.setString(10, c.getNumeroCasa());
            stmt.setString(11, c.getComplemento());
            stmt.setString(12, c.getBairro());
            stmt.setString(13, c.getCidade());
            stmt.setString(14, c.getEmail());
            stmt.setDouble(15, c.getRenda());
            stmt.setDouble(16, c.getLimiteCompra());
            stmt.setString(17, c.getMae());
            stmt.setString(18, c.getPai());
            stmt.setString(19, c.getTelefone());
            stmt.setString(20, c.getCpf());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente Fisico atualizar com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar Cliente Fisico");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
        

        
    
    public List<ClienteFisico> readFisico(){
        ClienteFisico c ;
        List<ClienteFisico>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM clientefisico");
            rs = stmt.executeQuery();

            while(rs.next()){
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                String dataNascimento = rs.getString("dataNascimento");
                String rg = rs.getString("rg");
                String orgaoExpeditor = rs.getString("orgaoExpeditor");
                boolean permitecredito = rs.getBoolean("permitecredito");
                String cepCliente = rs.getString("cepCliente");
                String ufCliente = rs.getString("ufCliente");
                String ruaCliente = rs.getString("ruaCliente");
                String numeroCasa = rs.getString("numeroCasa");
                String complementoCliente = rs.getString("complementoCliente");
                String bairroCliente = rs.getString("bairroCliente");
                String cidadeCliente = rs.getString("cidadeCliente");
                String emailCliente = rs.getString("emailCliente");
                double renda = rs.getDouble("renda");
                double limiteCompra = rs.getDouble("limiteCompra");
                String mae = rs.getString("mae");
                String pai = rs.getString("pai");
                String telefoneCliente = rs.getString("telefoneCliente");
                
                c = new ClienteFisico( cpf,  sexo,  rg,  orgaoExpeditor,  mae,  pai, nome,  dataNascimento,  permitecredito, 
                    cepCliente,  ufCliente,  ruaCliente,  numeroCasa,
                    complementoCliente,  bairroCliente,  cidadeCliente,  emailCliente,
                    renda,  limiteCompra,  telefoneCliente);
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Cliente Fisico");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
    
    public List<ClienteJuridico> readJuridico(){
        ClienteJuridico c ;
        List<ClienteJuridico>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM clienteJuridica");
            rs = stmt.executeQuery();

            
            while(rs.next()){
                String cnpj = rs.getString("cnpj");
                String nomeFantasia = rs.getString("nomeFantasia");
                String dataFundacao = rs.getString("dataFundacao");
                boolean permitecredito = rs.getBoolean("permitecredito");
                String cepJuridica = rs.getString("cepJuridica");
                String ruaJuridica = rs.getString("ruaJuridica");
                String numeroJuridica = rs.getString("numeroJuridica");
                String complementoJuridica = rs.getString("complementoJuridica");
                String bairroJuridica = rs.getString("bairroJuridica");
                String ufJuridica = rs.getString("ufJuridica");
                String cidadeJuridica = rs.getString("cidadeJuridica");
                String emailJuridica = rs.getString("emailJuridica");
                double rendaJuridica = rs.getDouble("rendaJuridica");
                double limiteCompraJuridica = rs.getDouble("limiteCompraJuridica");
                String telefoneJuridica = rs.getString("telefoneJuridica");
                
                c = new ClienteJuridico( cnpj, nomeFantasia,  dataFundacao,  permitecredito, 
                    cepJuridica,  ufJuridica,  ruaJuridica,  numeroJuridica,
                    complementoJuridica,  bairroJuridica,  cidadeJuridica,  emailJuridica,
                    rendaJuridica,  limiteCompraJuridica,  telefoneJuridica);
                lista.add(c);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Cliente Jurídico");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
    
}
