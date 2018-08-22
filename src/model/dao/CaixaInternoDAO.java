
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.CaixaInterno;


public class CaixaInternoDAO {
    public void create(CaixaInterno c){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO caixaInterno(numero, dataReferencia, historico, vlrCredito, vlrDebito, vlrSaldo) "
                                      + "VALUES (default, ?, ?, ?, ?, ?);");
            stmt.setString(1, c.getDataReferencia());
            stmt.setString(2, c.getHistorico());
            stmt.setDouble(3, c.getVlrCredito());
            stmt.setDouble(4, c.getVlrDebito());
            stmt.setDouble(5, c.getVlrSaldo());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir CaixaPadrao");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public List<CaixaInterno> read(){
        CaixaInterno cpObjeto;
       
               
        List<CaixaInterno>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select * from CaixaInterno;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int numero = rs.getInt("numero");
                String dataReferencia = rs.getString("dataReferencia");
                String historico = rs.getString("historico");
                double vlrCredito = rs.getDouble("vlrCredito");
                double vlrDebito = rs.getDouble("vlrDebito");
                double vlrSaldo = rs.getDouble("vlrSaldo");

                cpObjeto = new CaixaInterno(numero, dataReferencia, historico, vlrCredito, vlrDebito, vlrSaldo);
                lista.add(cpObjeto);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar CaixaInterno");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
    
    
    
}
