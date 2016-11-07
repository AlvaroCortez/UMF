package course.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Den on 07.12.15.
 */
public class AboutUsDialog extends JDialog{

    JLabel jLabel = new JLabel();
    String TEXT = "<html><h1>Авторы</h1><br>" +
            "<font face=’verdana’ size = 4>" +
            "</html>";
    JLabel jLabel1 = new JLabel();
    String TEXT1 = "<html>" +
            "<font face=’verdana’ size = 20>" +
            "<body><h2>Анна Шиповских, Денис Маслаков, Павел Фадеев (слева направо)<br>" +
            "<p>группа 6408, 2015</p>" +
            "</h5></body></html>";
    JLabel jLabel2 = new JLabel();

    public AboutUsDialog(JFrame frame) {
        super(frame);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setName("О нас");
        setSize(new Dimension(850,540));
        frame.setLocationRelativeTo(frame);
        setModalityType(ModalityType.TOOLKIT_MODAL);
        jLabel.setVerticalAlignment(JLabel.TOP);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel1.setVerticalAlignment(JLabel.TOP);
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        jLabel2.setVerticalAlignment(JLabel.TOP);
        jLabel2.setHorizontalAlignment(JLabel.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/us.jpg")));
        jLabel2.setText(TEXT1);
        jLabel.setText(TEXT);

        this.setResizable(false);
        getContentPane().add(new JScrollPane(jLabel), BorderLayout.NORTH);
        getContentPane().add(jLabel1, BorderLayout.CENTER);
        getContentPane().add(jLabel2, BorderLayout.SOUTH);
    }

    public void showDialog(){
        setVisible(true);
    }
}
