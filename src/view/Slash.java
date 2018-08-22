/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/**
 *
 * @author gabri
 */
public class Slash {
    private final int  LARGURA_IMG = 385;
    private final int ALTURA_IMG = 281;
    private final int TEMPO_DE_SLASH  = 2000;
    private final String CAMINHO_GIF = "/video/balloonSlow.gif";
    
    public Slash(){
    
        JWindow janelaSlash = new JWindow();
        
        janelaSlash.getContentPane().add(
                new JLabel(
                        "",
                        new ImageIcon(getClass().getResource(CAMINHO_GIF)),
                        SwingConstants.CENTER
        
                )
        
        );
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        
        janelaSlash.setBounds(
                (dimension.width - LARGURA_IMG) / 2, 
                (dimension.height - ALTURA_IMG) / 2,
                LARGURA_IMG,
                ALTURA_IMG
        );
        
        janelaSlash.setVisible(true);
        
        try{
            Thread.sleep(TEMPO_DE_SLASH);
        
        }catch(InterruptedException e){
            
        }
        
        janelaSlash.dispose();
        
    }
}
