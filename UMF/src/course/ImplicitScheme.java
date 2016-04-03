package course;

/**
 * Created by Den on 03.04.16.
 */
public class ImplicitScheme extends Scheme{

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
    private double[] a;
    private double[] b;
    private double[] c;
    private double[] f;
    double[] alfa;
    double[] betta;

    private double[][] gridValues;

    @Override
    public double[][] scheme(double hx, double ht) {
        gridValues = new double[(int) (T / ht ) + 1][(int) ((L / hx) ) + 1];
        int I = gridValues[0].length - 1;
        int K = gridValues.length - 1;
        a = new double[I + 1];
        b = new double[I + 1];
        c = new double[I + 1];
        f = new double[I + 1];
        alfa = new double[I + 1];
        betta = new double[I + 1];

        double gamma = this.K*ht/(C*hx*hx);

        for (int i = 0; i <= I  /*i < I*/; i++) {
            gridValues[0][i] = PSI(hx * i) - U0;
        }

        for (int k = 1; k <= K; k++) {
            alfa[0] = 0.1;
            betta[0] = 0.1;
            for (int i = 1; i <= I; i++) {
                a[i] = -gamma;
                b[i] = 1 + 2* ALPHA*ht/(C*R) + 2*gamma;
                c[i] = -gamma;
                f[i] = gridValues[k - 1][i] + (ht/C)*PHI(hx * i);
                alfa[i] = - a[i] / (b[i] + c[i] * alfa[i - 1]);
                betta[i] = (f[i] - c[i] * betta[i - 1]) / (b[i] + c[i] * alfa[i - 1]);
            }
            
            gridValues[k][I] = (gridValues[k - 1][I] - a[I]*betta[I - 1] + c[I]*betta[I - 1])/(a[I]*alfa[I - 1] + b[I] + c[I]*alfa[I - 1]);
            for (int i = I; i > 0; i--) {
                gridValues[k][i - 1] = gridValues[k][i]*alfa[i - 1] + betta[i - 1];
            }
        }

        return gridValues;
    }
}
