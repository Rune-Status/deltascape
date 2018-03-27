import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Design extends JFrame {
    public static final double version = 2.22;

    public Design() {
        initComponents();
    }

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            setProgress(0);
            Download.run();
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            download_button.setVisible(false);
            button_close.setVisible(true);
            label_status.setText("- Finished! The client is located at " + System.getProperty("user.home").replace("\\", "/") + "/Desktop/DeltaScape Client/");
            label_status.setToolTipText("- Finished! The client is located at " + System.getProperty("user.home").replace("\\", "/") + "/Desktop/DeltaScape Client/");
        }
    }

    //download button clicked
    public void download_buttonActionPerformed(ActionEvent e) {
        download_button.setEnabled(false);
        task = new Task();
        task.execute();
    }

    //close button clicked
    private void button_closeActionPerformed(ActionEvent e) {
        dispose();
        System.exit(0);
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        download_button = new JButton();
        progressBar = new JProgressBar();
        label_version = new JLabel();
        label_status = new JLabel();
        button_close = new JButton();

        //======== this ========
        setTitle("DeltaScape");
        setFont(new Font("Calibri", Font.PLAIN, 14));
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            //dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            //======== contentPanel ========
            {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    UIManager.put("TitledBorder.font", new Font("Tahoma", Font.BOLD, 11));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Windows bordering isn't available");
                }
                contentPanel.setBorder(new TitledBorder("Client Downloader"));
                contentPanel.setLayout(null);
                //---- download_button ----
                download_button.setText("Download");
                download_button.setToolTipText("Download the client");
                download_button.setFont(download_button.getFont().deriveFont(download_button.getFont().getStyle() | Font.BOLD));
                download_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        download_buttonActionPerformed(e);
                    }
                });
                contentPanel.add(download_button);
                download_button.setBounds(10, 60, 90, download_button.getPreferredSize().height);
              //  progressBar.setForeground(Color.green);
                contentPanel.add(progressBar);
                progressBar.setStringPainted(true);
                progressBar.setBounds(10, 35, 400, 18);

                //---- label_version ----
                label_version.setText("Version " + version);
                label_version.setToolTipText("Version " + version + " created by Kyle");
                label_version.setBorder(null);
                label_version.setHorizontalAlignment(SwingConstants.CENTER);
                label_version.setFont(new Font("Tahoma", Font.PLAIN, 10));
                label_version.setForeground(Color.gray);
                contentPanel.add(label_version);
                label_version.setBounds(360, 80, 55, 10);

                //---- label_status ----
                label_status.setText("- Idle");
                label_status.setHorizontalAlignment(SwingConstants.LEFT);
                label_status.setFont(new Font("Tahoma", Font.BOLD, 10));
                contentPanel.add(label_status);
                label_status.setBounds(20, 15, 320, 19);

                //---- button_close ----
                button_close.setText("Close");
                button_close.setFont(button_close.getFont().deriveFont(button_close.getFont().getStyle() | Font.BOLD));
                button_close.setToolTipText("Download & unzip complete. It's safe to close now.");
                button_close.setVisible(false);
                button_close.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        button_closeActionPerformed(e);
                    }
                });
                contentPanel.add(button_close);
                button_close.setBounds(10, 60, 90, button_close.getPreferredSize().height);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public JPanel dialogPane;
    public JPanel contentPanel;
    public JButton download_button;
    public static JProgressBar progressBar;
    public JLabel label_version;
    public static JLabel label_status;
    public JButton button_close;
    public Task task;
}
