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

    private static final int POINTS_ON_PLOT = 101;
    private static final String NAME = "Graphic of U";
    private Solution solution;

    public ViewGraphic(Solution solution){
        this.solution = solution;
    }

    public JFreeChart getChart(){
        XYSeriesCollection xy = new XYSeriesCollection();
        XYSeries series = new XYSeries(NAME);
        int I = POINTS_ON_PLOT;
        double spatialStep =  solution.getL() / I;
        for (int i = 0; i <= I; i++) {
            double x = i * spatialStep;
            series.add(x, solution.getSumWithLimit(x, solution.getSmallT(), solution.getN()));
        }
        xy.addSeries(series);
        JFreeChart pl = ChartFactory.createXYLineChart("", "x", "u(x), при t = " + solution.getSmallT(),
                xy, PlotOrientation.VERTICAL, true, true, false);
        double dy = (series.getMaxY() - series.getMinY()) * 0.03;
        ((XYPlot) pl.getPlot()).getRangeAxis()
                .setRange(series.getMinY() - dy, series.getMaxY() + dy);
        return pl;
    }
}
