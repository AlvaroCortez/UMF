package course;

import net.miginfocom.swing.MigLayout;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Den on 08.11.15.
 */
public class JFrameOfApplication extends JFrame{

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 500;
    private static final int ACCURACY = 1;
    private static final int NORMAL = 0;
    private JButton executeButton;
    private ChartPanel chartPanel;
    private ViewGraphic viewGraphic;
    private Solution solution;
    private JPanel paramsPanel;
    private JLabel paramsLabel;
    private JTextField fieldT;
    private JLabel accuracyLabel1;
    private JLabel accuracyLabel2;
    private JLabel epsLabel;
    private JTextField epsField;
    private JButton epsButton;
    private JLabel countIterationLable;

    public void init(){
        executeButton = new JButton("Выполнить");
        chartPanel = new ChartPanel(null);
        solution = new Solution();
        paramsPanel = new JPanel(new MigLayout("", "[left,200]"));
        paramsLabel = new JLabel("Введите параметр t");
        accuracyLabel1 = new JLabel("<html>Вычисление кол-ва итераций</br> с помощью заданной точности<html>");
        epsLabel = new JLabel("Введите точность");
        fieldT = new JTextField();
        fieldT.setPreferredSize(new Dimension(100,2));
        epsField = new JTextField();
        epsField.setPreferredSize(new Dimension(100, 2));
        viewGraphic = new ViewGraphic(solution);
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeButtonClicked(NORMAL);
            }
        });
        epsButton = new JButton("Рассчитать");
        epsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeButtonClicked(ACCURACY);
            }
        });
        countIterationLable = new JLabel();
        MigLayout mwLayout = new MigLayout("", "[][]", "[top][]");
        getContentPane().setLayout(mwLayout);
        getContentPane().add(chartPanel, "grow 50,push,span");
        paramsPanel.add(paramsLabel, "split 2");
        paramsPanel.add(fieldT, "cell 1 0 5 1, wrap");
        paramsPanel.add(executeButton, "wrap");
        paramsPanel.add(accuracyLabel1,"span 5, wrap");
        paramsPanel.add(epsLabel,"split");
        paramsPanel.add(epsField, "cell 1 0 5 1, wrap");
        paramsPanel.add(epsButton,"wrap");
        paramsPanel.add(countIterationLable);
        getContentPane().add(paramsPanel, "dock west");
    }

    private void executeButtonClicked(int type){
        try {
            double t = Double.parseDouble(fieldT.getText());
            solution.setSmallT(t);
        } catch (NumberFormatException e) {

        }
        try {
            double eps = Double.parseDouble(epsField.getText());
            solution.setEps(eps);
        } catch (NumberFormatException e) {

        }
        switch (type) {
            case NORMAL:
                solution.setN(100);
                chartPanel.setChart(viewGraphic.getChart(NORMAL));
                break;
            case ACCURACY:
                chartPanel.setChart(viewGraphic.getChart(ACCURACY));
                countIterationLable.setText("Количество слагаемых: "+String.valueOf(solution.getN()));
                break;
        }
    }

    public static void main(String[] args) {
        JFrameOfApplication frame = new JFrameOfApplication();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.init();
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setVisible(true);
    }
}
