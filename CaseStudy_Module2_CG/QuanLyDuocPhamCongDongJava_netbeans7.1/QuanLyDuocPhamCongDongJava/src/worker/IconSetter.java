
package worker;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class IconSetter {
    public void setIcon(JLabel unit, String iconName){
        unit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
        unit.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
    }
    public void setIcon(JButton unit, String iconName){
        unit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
        unit.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
        unit.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
        unit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
        unit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
    }
    public void setIcon(JToggleButton unit, String iconName){
        unit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
        unit.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
        unit.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
        unit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
        unit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/" + iconName + ".png")));
    }
}
