
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.CaixaPadrao;



public class CaixaPadraoDAO {
    public void create(CaixaPadrao c){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO caixaPadrao(numero, dataReferencia, historico, vlrCredito, vlrDebito) "
                                      + "VALUES (default, CURDATE(), ?, ?, ?);");
            stmt.setString(1, c.getHistorico());
            stmt.setDouble(2, c.getVlrCredito());
            stmt.setDouble(3, c.getVlrDebito());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar inserir CaixaPadrao");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public List<CaixaPadrao> read(){
        CaixaPadrao cpObjeto;
        
        List<CaixaPadrao>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select * from CaixaPadrao;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int numero = rs.getInt("numero");
                String dataReferencia = rs.getString("dataReferencia");
                String historico = rs.getString("historico");
                double vlrCredito = rs.getDouble("vlrCredito");
                double vlrDebito = rs.getDouble("vlrDebito");
                double vlrSaldo = rs.getDouble("vlrSaldo");

                cpObjeto = new CaixaPadrao(numero, dataReferencia, historico, vlrCredito, vlrDebito, vlrSaldo);
                lista.add(cpObjeto);
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar Pesquisar CaixaPadrao");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return null;
    }
    
}
