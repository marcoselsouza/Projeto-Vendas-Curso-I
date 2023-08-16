
package br.com.projeto.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author marco
 */
public class Utilitarios {
    
    //metodo limpar campos
    public void LimpaTela(JPanel container){
        Component components[] = container.getComponents();
        for(Component component: components){
            if(component instanceof JTextField){
                ((JTextField) component).setText(null);
            }
        }
        
    }
    
}
