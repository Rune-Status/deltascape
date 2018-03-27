import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Kyle
 * Date: 1/14/11
 * Time: 9:26 AM
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Windows isn't available");
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Design GUI = new Design();
                GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GUI.setVisible(true);
            }
        });
    }
}
