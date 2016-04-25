package course;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Created by Den on 08.11.15.
 */
public class ViewGraphic {

    private static final int POINTS_X_ON_PLOT = 101;
    private static final int POINTS_T_ON_PLOT = 300;
    private static final String NAME = "Graphic of U";
    private Solution solution;
    XYSeriesCollection xy = new XYSeriesCollection();
    XYSeries series = new XYSeries(NAME);
    XYSeries implicitSchemeSeries = new XYSeries("Неявная схема");
    XYSeries explicitSchemeSeries = new XYSeries("Явная схема");
    XYSeries krankSchemeSeries = new XYSeries("Схема Кранка-Николсона");

    public ViewGraphic(Solution solution) {
        this.solution = solution;
        xy.addSeries(series);
        xy.addSeries(implicitSchemeSeries);
        xy.addSeries(explicitSchemeSeries);
        xy.addSeries(krankSchemeSeries);
    }

    public JFreeChart getChartT(int type) {
        series.clear();
        int I = POINTS_X_ON_PLOT;
        double spatialStep = solution.getL() / I;
        switch (type) {
            case 0:
                for (int i = 0; i <= I; i++) {
                    double x = i * spatialStep;
                    series.add(x, solution.getSumWithLimit(x, solution.getSmallT(), solution.getN()));
                }
                break;
            case 1:
                for (int i = 0; i <= I; i++) {
                    double x = i * spatialStep;
                    series.add(x, solution.getSumWithAccuracy(x, solution.getSmallT(), solution.getEps()));
                }
                break;
        }
        JFreeChart pl = ChartFactory.createXYLineChart("", "x, кол-во слагаемых N = " + solution.getN(), "u(x), при t = " + solution.getSmallT(),
                xy, PlotOrientation.VERTICAL, true, true, false);
        double dy = (series.getMaxY() - series.getMinY()) * 0.03;
        ((XYPlot) pl.getPlot()).getRangeAxis()
                .setRange(series.getMinY() - dy, series.getMaxY() + dy);
        return pl;
    }

    public JFreeChart getChartX(int type) {
        XYSeriesCollection xy = new XYSeriesCollection();
        XYSeries series = new XYSeries(NAME);
        int I = POINTS_T_ON_PLOT;
        double spatialStep = solution.getT() / I;
        switch (type) {
            case 0:
                for (int i = 0; i <= I; i++) {
                    double t = i * spatialStep;
                    series.add(t, solution.getSumWithLimit(solution.getX(), t, solution.getN()));
                }
                break;
            case 1:
                for (int i = 0; i <= I; i++) {
                    double t = i * spatialStep;
                    series.add(t, solution.getSumWithAccuracy(solution.getX(), t, solution.getEps()));
                }
                break;
        }
        xy.addSeries(series);
        JFreeChart pl = ChartFactory.createXYLineChart("", "t, кол-во слагаемых N = " + solution.getN(), "u(t), при x = " + solution.getX(),
                xy, PlotOrientation.VERTICAL, true, true, false);
        double dy = (series.getMaxY() - series.getMinY()) * 0.03;
        ((XYPlot) pl.getPlot()).getRangeAxis()
                .setRange(series.getMinY() - dy, series.getMaxY() + dy);
        return pl;
    }


    public XYSeriesCollection getXy() {
        return xy;
    }

    public void setXy(XYSeriesCollection xy) {
        this.xy = xy;
    }

    public XYSeries getImplicitSchemeSeries() {
        return implicitSchemeSeries;
    }

    public void setImplicitSchemeSeries(XYSeries implicitSchemeSeries) {
        this.implicitSchemeSeries = implicitSchemeSeries;
    }

    public XYSeries getKrankSchemeSeries() {
        return krankSchemeSeries;
    }

    public void setKrankSchemeSeries(XYSeries krankSchemeSeries) {
        this.krankSchemeSeries = krankSchemeSeries;
    }

    public XYSeries getExplicitSchemeSeries() {
        return explicitSchemeSeries;
    }

    public void setExplicitSchemeSeries(XYSeries explicitSchemeSeries) {
        this.explicitSchemeSeries = explicitSchemeSeries;
    }
    public void clear(){
        //implicitSchemeSeries = new XYSeries("Неявная схема");
        implicitSchemeSeries.clear();
        //explicitSchemeSeries = new XYSeries("Явная схема");
        explicitSchemeSeries.clear();
        //krankSchemeSeries = new XYSeries("Схема Кранка-Николсона");
        krankSchemeSeries.clear();

    }
}
