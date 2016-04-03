package course;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class ExplicitScheme {

    private final double L = 1;
    private final double U0 = 0;
    private final double K = 0.005;
    private final double C = 1;
    private final double ALPHA = 0.005;
    private final double T = 300;
    private double smallT = 10;
    private final double S = 0.005;
    private double R = Math.sqrt(S / Math.PI);
    private long N = 100;
    private double eps = 0.001;
    private double x = 0;

    private double[][] gridValues;

    public double[][] scheme(double hx, double ht) {
        gridValues = new double[(int) (T / ht ) + 1][(int) ((L / hx) ) + 1];
        int I = gridValues[0].length - 1;
        int K = gridValues.length - 1;

        for (int i = 0; i <= I  /*i < I*/; i++) {
            gridValues[0][i] = PSI(hx * i) - U0;
        }

        for (int k = 0; k < K; k++) {
            gridValues[k + 1][0] = gridValues[k][0] * (1 - 2* ALPHA * ht/(C*R) - 2*this.K*ht/(C*hx*hx)) + (ht/C)*(2*gridValues[k][0]*this.K/(hx*hx) + PHI(hx * 0));
            for (int i = 1; i < I; i++) {
                gridValues[k + 1][i] = gridValues[k][i] * (1 - 2* ALPHA * ht/(C*R) - 2*this.K*ht/(C*hx*hx)) + (ht/C)*((gridValues[k][i + 1] + gridValues[k][i - 1])*this.K/(hx*hx) + PHI(hx * i));
            }
            gridValues[k + 1][I] = gridValues[k][I] * (1 - 2* ALPHA * ht/(C*R) - 2*this.K*ht/(C*hx*hx)) + (ht/C)*(2*gridValues[k][I-1]*this.K/(hx*hx) + PHI(hx * I));
        }

        return gridValues;
    }

    private double PHI(double xi) {
        if (xi >= L / 3 && xi <= 2 * L / 3) {
            return 1;
        } else {
            return 0;
        }
    }

    private double PSI(double x){
        return Math.cos(2 * Math.PI * x / L);
    }

    public void explicitScheme(double ht, double hx, double t) {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xySeries = new XYSeries("Явная схема");
        //ExplicitScheme explicitScheme = new ExplicitScheme();
        //double ht = 0.0005;
        //double hx = 0.01;
        double[][] scheme = scheme(hx, ht);
        double x = 0;
        // int level = 1630;
        // double temperature = level * ht;
        //double t = 5;
        int level = (int) (t / ht);
        for (int i = 0; i < scheme[0].length; i++) {
            xySeries.add(x, scheme[level][i]);
            x += hx;
        }
        xySeriesCollection.addSeries(xySeries);
        JFreeChart pl = ChartFactory.createXYLineChart("", "x, при t = " + t + ", ht = " + ht + ", hx = " + hx, "u(x)",
                xySeriesCollection, PlotOrientation.VERTICAL, true, true, false);
        JFrame jFrame = new JFrame();
        ChartPanel chartPanel = new ChartPanel(pl);
        jFrame.add(chartPanel);
        chartPanel.setVisible(true);
        jFrame.setVisible(true);
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
