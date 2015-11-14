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

    private static final int WIDTH = 900;
    private static final int HEIGHT = 500;
    private JButton executeButton;
    private ChartPanel chartPanel;
    private ViewGraphic viewGraphic;
    private Solution solution;
    private JPanel paramsPanel;
    private JLabel paramsLabel;
    private JTextField fieldT;

    public void init(){
        executeButton = new JButton("Execute");
        chartPanel = new ChartPanel(null);
        solution = new Solution();
        paramsPanel = new JPanel(new MigLayout("", "[left,100]"));
        paramsLabel = new JLabel("Введите параметр t");
        fieldT = new JTextField();
        viewGraphic = new ViewGraphic(solution);
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeButtonClicked();
            }
        });
        MigLayout mwLayout = new MigLayout("", "", "[top][]");
        getContentPane().setLayout(mwLayout);
        getContentPane().add(chartPanel, "grow,push,span");
        paramsPanel.add(paramsLabel, "gap 10");
        paramsPanel.add(fieldT, "span, growx, wrap");
        paramsPanel.add(executeButton, "gap 10");;
        getContentPane().add(paramsPanel, "dock west");
    }

    private void executeButtonClicked(){
        try {
            double t = Double.parseDouble(fieldT.getText());
            solution.setSmallT(t);
        } catch (NumberFormatException e){

        } finally {
            chartPanel.setChart(viewGraphic.getChart());
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
