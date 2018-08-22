/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author gabri
 */
public class main {
     public static void main(String[] args) {
         try {
      
            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
           // new Slash();
            JanelaRealizaLoginNova login = new JanelaRealizaLoginNova(); 
            login.setVisible(true);
            //JanelaPrincipal janela = new JanelaPrincipal();
          //  janela.setVisible(true);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoqueNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoqueNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoqueNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaManutencaoEstoqueNova.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
     
     }

    private static JanelaRealizaLoginNova JanelaRealizaLoginNova(main aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
