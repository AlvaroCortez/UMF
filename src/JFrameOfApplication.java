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
    private JButton executeXButton;
    private ChartPanel chartPanel;
    private ViewGraphic viewGraphic;
    private Solution solution;
    private JPanel paramsPanel;
    private JLabel paramsLabel;
    private JTextField fieldT;
    private JLabel accuracyLabel;
    private JLabel accuracyXLabel;
    private JLabel epsLabel;
    private JTextField epsField;
    private JLabel epsXLabel;
    private JTextField epsXField;
    private JButton epsButton;
    private JButton epsXButton;
    private JLabel countIterationLabel;
    private JLabel countIterationLabelX;
    private JLabel summandLabel;
    private JTextField summandField;
    private JLabel summandXLabel;
    private JTextField summandXField;
    private JLabel firstGraph;
    private JLabel secondGraph;
    private JLabel paramXLabel;
    private JTextField fieldX;

    public void init(){
        executeButton = new JButton("Выполнить");
        executeXButton = new JButton("Выполнить");
        chartPanel = new ChartPanel(null);
        solution = new Solution();
        paramsPanel = new JPanel(new MigLayout("", "[left,200]"));
        paramsLabel = new JLabel("Введите параметр t");
        paramXLabel = new JLabel("Введите параметр x");
        accuracyLabel = new JLabel("<html>Вычисление кол-ва итераций</br> с помощью заданной точности<html>");
        accuracyXLabel = new JLabel("<html>Вычисление кол-ва итераций</br> с помощью заданной точности<html>");
        epsLabel = new JLabel("Введите точность");
        epsXLabel = new JLabel("Введите точность");
        summandLabel = new JLabel("Кол-во слагаемых");
        summandField = new JTextField();
        summandField.setPreferredSize(new Dimension(100,2));
        summandXLabel = new JLabel("Кол-во слагаемых");
        summandXField = new JTextField();
        summandXField.setPreferredSize(new Dimension(100,2));
        fieldT = new JTextField();
        fieldT.setPreferredSize(new Dimension(100,2));
        fieldX = new JTextField();
        fieldX.setPreferredSize(new Dimension(100,2));
        epsField = new JTextField();
        epsField.setPreferredSize(new Dimension(100, 2));
        epsXField = new JTextField();
        epsXField.setPreferredSize(new Dimension(100, 2));
        viewGraphic = new ViewGraphic(solution);
        firstGraph = new JLabel("График №1 - u(x)");
        secondGraph = new JLabel("График №2 - u(t)");
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeButtonClicked(NORMAL);
            }
        });
        executeXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeButtonXClicked(NORMAL);
            }
        });
        epsButton = new JButton("Рассчитать");
        epsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeButtonClicked(ACCURACY);
            }
        });
        epsXButton = new JButton("Рассчитать");
        epsXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeButtonXClicked(ACCURACY);
            }
        });
        countIterationLabel = new JLabel();
        countIterationLabelX = new JLabel();
        MigLayout mwLayout = new MigLayout("", "[][]", "[top][]");
        getContentPane().setLayout(mwLayout);
        getContentPane().add(chartPanel, "grow 50,push,span");
        paramsPanel.add(firstGraph, "wrap");
        paramsPanel.add(paramsLabel, "split 2");
        paramsPanel.add(fieldT, "cell 1 0 5 1, wrap");
        paramsPanel.add(summandLabel, "split 2");
        paramsPanel.add(summandField, "cell 1 0 5 1, wrap");
        paramsPanel.add(executeButton, "wrap");
        paramsPanel.add(accuracyLabel,"span 5, wrap");
        paramsPanel.add(epsLabel,"split");
        paramsPanel.add(epsField, "cell 1 0 5 1, wrap");
        paramsPanel.add(epsButton,"wrap");
        paramsPanel.add(countIterationLabel, "wrap");
        paramsPanel.add(secondGraph, "wrap");
        paramsPanel.add(paramXLabel, "split 2");
        paramsPanel.add(fieldX, "cell 1 0 5 1, wrap");
        paramsPanel.add(summandXLabel, "split 2");
        paramsPanel.add(summandXField, "cell 1 0 5 1, wrap");
        paramsPanel.add(executeXButton, "wrap");
        paramsPanel.add(accuracyXLabel,"span 5, wrap");
        paramsPanel.add(epsXLabel,"split");
        paramsPanel.add(epsXField, "cell 1 0 5 1, wrap");
        paramsPanel.add(epsXButton,"wrap");
        paramsPanel.add(countIterationLabelX, "wrap");
        getContentPane().add(paramsPanel, "dock west");
        System.out.println("При eps = 0.00001, x = 0.7, t = 300");
        solution.oor(0.00001, 0.7, 300);
        System.out.println("При eps = 0.00001, x = 0.33, t = 300");
        solution.oor(0.00001, 0.33, 300);
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
                try {
                    long N = Long.parseLong(summandField.getText());
                    solution.setN(N);
                } catch (NumberFormatException e){
                    solution.setN(100);
                }
                chartPanel.setChart(viewGraphic.getChartT(NORMAL));
                break;
            case ACCURACY:
                chartPanel.setChart(viewGraphic.getChartT(ACCURACY));
                countIterationLabel.setText("Количество слагаемых: "+String.valueOf(solution.getN()));
                break;
        }
    }

    private void executeButtonXClicked(int type){
        try {
            double x = Double.parseDouble(fieldX.getText());
            solution.setX(x);
        } catch (NumberFormatException e) {

        }
        try {
            double eps = Double.parseDouble(epsXField.getText());
            solution.setEps(eps);
        } catch (NumberFormatException e) {

        }
        switch (type) {
            case NORMAL:
                try {
                    long N = Long.parseLong(summandXField.getText());
                    solution.setN(N);
                } catch (NumberFormatException e){
                    solution.setN(100);
                }
                chartPanel.setChart(viewGraphic.getChartX(NORMAL));
                break;
            case ACCURACY:
                chartPanel.setChart(viewGraphic.getChartX(ACCURACY));
                countIterationLabelX.setText("Количество слагаемых: "+String.valueOf(solution.getN()));
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
