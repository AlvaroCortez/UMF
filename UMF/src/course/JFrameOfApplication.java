package course;

import course.dialogs.AboutUsDialog;
import course.dialogs.InstructionDialog;
import course.dialogs.TaskDialog;
import course.image.KrankScheme;
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
    private static final int HEIGHT = 700;
    private static final int ACCURACY = 1;
    private static final int NORMAL = 0;
    private static final int EXPLICIT = 100;
    private static final int IMPLICIT = 200;
    private static final int KRANK = 300;
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
    private JMenuBar menuBar;
    private JMenuItem taskItem;
    private JMenuItem instructionsItem;
    private JMenuItem aboutUsItem;
    private JMenuItem exitItem;
    private TaskDialog taskDialog;
    private InstructionDialog instructionDialog;
    private AboutUsDialog aboutUsDialog;
    private JButton explicitSchemeButton;
    private JButton implicitSchemeButton;
    private JLabel schemeLabel;
    private JLabel schemeLabelParamT;
    private JLabel schemeLabelParamHX;
    private JLabel schemeLabelParamHT;
    private JTextField schemeFieldParamT;
    private JTextField schemeFieldParamHX;
    private JTextField schemeFieldParamHT;
    private ExplicitScheme explicitScheme;
    private ImplicitScheme implicitScheme;
    private KrankScheme krankScheme;
    private JButton krankSchemeButton;

    public void init(){
        taskDialog = new TaskDialog(this);
        instructionDialog = new InstructionDialog(this);
        aboutUsDialog = new AboutUsDialog(this);
        menuBar = new JMenuBar();
        taskItem = new JMenuItem("О задании");
        taskItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskDialog.showDialog();
            }
        });
        instructionsItem = new JMenuItem("Инструкция");
        instructionsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructionDialog.showDialog();
            }
        });
        aboutUsItem = new JMenuItem("Об авторах", new ImageIcon("/aboutUs.png"));
        aboutUsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutUsDialog.showDialog();
            }
        });
        exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuBar.add(taskItem);
        menuBar.add(instructionsItem);
        menuBar.add(aboutUsItem);
        menuBar.add(exitItem);
        setJMenuBar(menuBar);
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

        explicitScheme = new ExplicitScheme();
        explicitSchemeButton = new JButton("Явная схема");
        explicitSchemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schemeButtonClicked(EXPLICIT);
            }
        });
        schemeLabel = new JLabel("Построение схем");
        schemeLabelParamT = new JLabel("Введите параметр t");
        schemeLabelParamHT = new JLabel("Введите параметр hx");
        schemeLabelParamHX = new JLabel("Введите параметр ht");
        schemeFieldParamHT = new JTextField();
        schemeFieldParamT = new JTextField();
        schemeFieldParamHX = new JTextField();

        implicitScheme = new ImplicitScheme();
        implicitSchemeButton = new JButton("Неявная схема");
        implicitSchemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schemeButtonClicked(IMPLICIT);
            }
        });

        krankScheme = new KrankScheme();
        krankSchemeButton = new JButton("Схема Кранка-Николсона");
        krankSchemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schemeButtonClicked(KRANK);
            }
        });

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
        paramsPanel.add(schemeLabel, "wrap");
        paramsPanel.add(schemeLabelParamT, "split 2");
        paramsPanel.add(schemeFieldParamT, "cell 1 0 5 1, wrap");
        paramsPanel.add(schemeLabelParamHX, "split 2");
        paramsPanel.add(schemeFieldParamHX, "cell 1 0 5 1, wrap");
        paramsPanel.add(schemeLabelParamHT, "split 2");
        paramsPanel.add(schemeFieldParamHT, "cell 1 0 5 1, wrap");
        paramsPanel.add(explicitSchemeButton, "wrap");
        paramsPanel.add(implicitSchemeButton, "wrap");
        paramsPanel.add(krankSchemeButton, "wrap");
        getContentPane().add(paramsPanel, "dock west");
        System.out.println("При eps = 0.00001, x = 0.66, t = 1");
        solution.oor(0.00001, 0.66, 1);
        System.out.println("При eps = 0.00001, x = 0.33, t = 1");
        solution.oor(0.00001, 0.33, 1);
        System.out.println("При eps = 0.00001, x = 0.1, t = 5");
        solution.oor(0.00001, 0.1, 5);
        setName("Курсовая работа");
    }

    private void schemeButtonClicked(int type){
        double t, ht, hx;
        try {
            t = Double.parseDouble(schemeFieldParamT.getText());
        } catch (NumberFormatException e) {
            t = 5;
        }
        try {
            hx = Double.parseDouble(schemeFieldParamHX.getText());
        } catch (NumberFormatException e) {
            hx = 0.01;
        }
        try {
            ht = Double.parseDouble(schemeFieldParamHT.getText());
        } catch (NumberFormatException e) {
            ht = 0.0005;
        }
        switch (type){
            case EXPLICIT:
                explicitScheme.explicitScheme(ht, hx, t);
                break;
            case IMPLICIT:
                implicitScheme.explicitScheme(ht, hx, t);
                break;
            case KRANK:
                krankScheme.explicitScheme(ht, hx, t);
        }
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
