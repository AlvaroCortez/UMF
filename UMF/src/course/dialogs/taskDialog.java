package course.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Den on 07.12.15.
 */
public class TaskDialog extends JDialog {

    JLabel jLabel = new JLabel();
    JLabel jLabel1 = new JLabel();
    String TEXT = "<html><h1>Задание</h1>" +
            "<font face=’verdana’ size = 4>" +
            "</html>";

    public TaskDialog(JFrame frame){
        super(frame);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setName("О задании");
        setSize(new Dimension(570,680));
        frame.setLocationRelativeTo(frame);
        setModalityType(ModalityType.TOOLKIT_MODAL);
        jLabel.setVerticalAlignment(JLabel.TOP);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel1.setVerticalAlignment(JLabel.TOP);
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/task.png")));
        jLabel.setText(TEXT);
        this.setResizable(false);
        getContentPane().add(new JScrollPane(jLabel), BorderLayout.NORTH);
        getContentPane().add(jLabel1, BorderLayout.CENTER);
    }

    public void showDialog(){
        setVisible(true);
    }
}
