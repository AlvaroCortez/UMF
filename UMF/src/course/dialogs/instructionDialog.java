package course.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Den on 07.12.15.
 */
public class InstructionDialog extends JDialog {

    JLabel jLabel = new JLabel();
    String TEXT = "<html><h1>Инструкции</h1>" +
            "<font face=’verdana’ size = 4>" +
            "<body>В данной программе реализовано построение двух графиков: <br>" +
            "1) функция u(x) изменения температуры, зависящая от расстояния x, <br>" +
            "и при фиксированном времени t; <br>2) функция u(t) изменения температуры, <br>" +
            "зависящая от времени t, при фиксированном расстоянии x. <br>" +
            "Так же реализовано по два варианта построения графиков: <br>" +
            "1) можно указать точное значение фиксированной величины и количество слагаемых суммы ряда; <br>" +
            "Стандартные значения: для первого графика t = 10 сек, N = 100; для второго: x = 0, N = 100. <br>" +
            "2) можно задать точность вычислений, на экран будет выведено количество слагаемых суммы ряда. <br>" +
            "Стандартные значения: 5 элементов для каждого графика.</body></html>";

    public InstructionDialog(JFrame frame) {
        super(frame);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setName("Инструкция");
        setSize(new Dimension(750,260));
        frame.setLocationRelativeTo(frame);
        setModalityType(ModalityType.TOOLKIT_MODAL);
        jLabel.setVerticalAlignment(JLabel.TOP);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setText(TEXT);
        this.setResizable(false);
        getContentPane().add(new JScrollPane(jLabel), BorderLayout.CENTER);
    }

    public void showDialog(){
        setVisible(true);
    }
}
