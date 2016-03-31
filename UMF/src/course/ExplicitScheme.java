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
        gridValues = new double[(int) (T / ht + 1)][(int) ((L / hx) + 1)];
        int I = gridValues[0].length;
        int K = gridValues.length;

        for (int i = 0; i < I  /*i < I*/; i++) {
            gridValues[0][i] = PSI(hx * i) - U0;
        }
        //gridValues[0][(int) (I-1)] = 0;

        // Сделал проверку, чтобы убедиться, что на границе возможно должен быть ноль.
         System.out.println(gridValues[0][gridValues[0].length - 1]);
//        for (int k = 1; k < K; k++) {
////            gridValues[k][0] = (-2 * ALPHA * ht / (C * R)) * gridValues[k - 1][0] + (this.K * ht / C) * ( 2 * (gridValues[k - 1][1] - gridValues[k - 1][0])) / (hx*hx)
////                    + (ht * PHI(hx * 0) / C) + gridValues[k -1][0];
//            gridValues[k][0] = gridValues[k - 1][0] * (1 - 2* ALPHA * ht/(C*R) - 2*this.K*ht/(C*hx*hx)) + (ht/C)*(2*gridValues[k-1][0]*this.K/(hx*hx) + PHI(hx * 0));
//        }
        for (int k = 1; k < K; k++) {
            gridValues[k][0] = gridValues[k - 1][0] * (1 - 2* ALPHA * ht/(C*R) - 2*this.K*ht/(C*hx*hx)) + (ht/C)*(2*gridValues[k-1][0]*this.K/(hx*hx) + PHI(hx * 0));
            for (int i = 1; i < I - 1; i++) {
                //gridValues[k][i] = uikplus1(gridValues[k - 1][i + 1], gridValues[k - 1][i], gridValues[k - 1][i - 1], hx, ht, PHI(hx * i));
                gridValues[k][i] = gridValues[k - 1][i] * (1 - 2* ALPHA * ht/(C*R) - 2*this.K*ht/(C*hx*hx)) + (ht/C)*((gridValues[k-1][i + 1] + gridValues[k - 1][i - 1])*this.K/(hx*hx) + PHI(hx * i));
            }
            gridValues[k][I - 1] = gridValues[k - 1][I - 1] * (1 - 2* ALPHA * ht/(C*R) - 2*this.K*ht/(C*hx*hx)) + (ht/C)*(2*gridValues[k-1][I-2]*this.K/(hx*hx) + PHI(hx * 0));
        }
//        for (int k = 1; k < K; k++) {
//            gridValues[k][I - 1] = (-2 * ALPHA * ht / (C * R)) * gridValues[k - 1][I - 2] - (K * ht / C) * ( - 2 *  + gridValues[k - 1][I - 2]) / (hx)
//                    + (ht * PHI(hx*(I-2)) / C) + gridValues[k - 1][I - 2];
//        }
//        gridValues[K - 1][I - 1] = (-2 * ALPHA * ht / (C * R)) * gridValues[K - 1][I - 2] - (K * ht / C) * ( - 2 *  + gridValues[K - 1][I - 2]) / (hx)
//                + (ht * PHI(hx*(I-2)) / C) + gridValues[K - 1][I - 2];
//        for (int k = 1; k < K; k++) {
////            gridValues[k][I - 1] = (-2 * ALPHA * ht / (C * R)) * gridValues[k - 1][I - 1] + (this.K * ht / C) * ( 2 * (gridValues[k - 1][I - 2] - gridValues[k - 1][I - 1])) / (hx*hx)
////                    + (ht * PHI(hx * (I - 1)) / C) + gridValues[k -1][I - 1];
//            gridValues[k][I - 1] = gridValues[k - 1][I - 1] * (1 - 2* ALPHA * ht/(C*R) - 2*this.K*ht/(C*hx*hx)) + (ht/C)*(2*gridValues[k-1][I-2]*this.K/(hx*hx) + PHI(hx * 0));
//
//        }

        return gridValues;
    }

    private double PHI(double xi) {
        if (xi >= L / 3 && xi <= 2 * L / 3) {
            return 1;
        } else {
            return 0;
        }


        // return (Math.sin(2 * Math.PI * xi / 3) - Math.sin(Math.PI * xi / 3)) * 4 / (2 * Math.PI * xi + Math.sin(2 * Math.PI * xi));
    }

    private double PSI(double x){
        return Math.cos(2 * Math.PI * x / L);
    }


    public double uikplus1(double uiplus1k, double uik, double uiminus1k, double hx, double ht, double phi) {
        double result = (-2 * ALPHA * ht / (C * R)) * uik + (K * ht / C) * ((uiplus1k - 2 * uik + uiminus1k) / (hx*hx))
                + (ht * phi / C) + uik;
        return result;
    }

    public static void main(String[] args) {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries xySeries = new XYSeries("Явная схема");
        ExplicitScheme explicitScheme = new ExplicitScheme();
        double ht = 0.0005;
        double hx = 0.01;
        double[][] scheme = explicitScheme.scheme(hx, ht);
        double x = 0;
        // int level = 1630;
        // double temperature = level * ht;
        double t = 5;
        int level = (int) (t / ht);
        for (int i = 0; i < scheme[0].length; i++) {
            xySeries.add(x, scheme[level][i]);
            x += hx;
        }
        xySeriesCollection.addSeries(xySeries);
        JFreeChart pl = ChartFactory.createXYLineChart("", "x, кол-во слагаемых N = ", "u(x), при t = " + t,
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
