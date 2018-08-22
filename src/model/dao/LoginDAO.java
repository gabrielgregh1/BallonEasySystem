
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
import model.bean.Login;


public class LoginDAO {
   public void create(Login l){
        Connection con =  ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("insert into Login(usarname, pass) VALUES(md5(?), md5(?));");
            stmt.setString(1, l.getUsername());
            stmt.setString(2, l.getPass());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar Logar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    public int read(Login l){
        String cargo = "";
        List<CaixaPadrao>lista = new ArrayList();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select cargo from Login " +
                                        "where  usarname = md5(?) and pass = md5(?);");
            stmt.setString(1, l.getUsername());
            stmt.setString(2, l.getPass());
            
            rs = stmt.executeQuery();
            
            while(rs.next()){  
                cargo = rs.getString("cargo");          
            }
            
            if(cargo.equals("Administrador"))
                return 1;
            
            if(cargo.equals("Funcionario"))
                return 2;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao pesquisar os dados");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    JOptionPane.showMessageDialog(null,"A Senha ou Login estão errados");    
    return 0;
    }
    
    
}
