

package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Despesas;


public class DespesasDAO {
    public void create(Despesas d){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("insert into despesas(codigo, tipo, observacao, dataDespesa, valor)"
                                      + " values (default, ?, ?, CURDATE(), ?);");
            stmt.setString(1, d.getTipo());
            stmt.setString(2, d.getObservacao());
            stmt.setDouble(3, d.getValor());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Despesas Inserida com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir Despesas");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public List<Despesas> read(String mes){
        Despesas despObjeto;
       
               
        List<Despesas>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select * from despesas "
                                       +" where dataDespesa like ? ORDER BY valor DESC;");
               
            stmt.setString(1, "%-"+mes+"-%");
            
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                String tipo = rs.getString("tipo");
                String observacao = rs.getString("observacao");
                String dataDespesa = rs.getString("dataDespesa");
                double valor = rs.getDouble("valor");


                despObjeto = new Despesas(tipo,observacao,dataDespesa,valor);
                lista.add(despObjeto);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar Despesas");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }

}
