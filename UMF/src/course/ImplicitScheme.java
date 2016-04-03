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

    private double[][] gridValues;

    @Override
    public double[][] scheme(double hx, double ht) {
        gridValues = new double[(int) (T / ht ) + 1][(int) ((L / hx) ) + 1];
        int I = gridValues[0].length - 1;
        int K = gridValues.length - 1;

        for (int i = 0; i <= I  /*i < I*/; i++) {
            gridValues[0][i] = PSI(hx * i) - U0;
        }

        for (int k = 1; k <= K; k++) {
            gridValues[k - 1][0] = gridValues[k][0] * (1 + 2* ALPHA * ht/(C*R) + 2*this.K*ht/(C*hx*hx)) - (ht/C)*(2*gridValues[k][1]*this.K/(hx*hx) + PHI(hx * 0));
            for (int i = 1; i < I; i++) {
                gridValues[k - 1][i] = gridValues[k][i] * (1 + 2* ALPHA * ht/(C*R) + 2*this.K*ht/(C*hx*hx)) - (ht/C)*((gridValues[k][i + 1] + gridValues[k][i - 1])*this.K/(hx*hx) + PHI(hx * i));
            }
            gridValues[k - 1][I] = gridValues[k][I] * (1 + 2* ALPHA * ht/(C*R) + 2*this.K*ht/(C*hx*hx)) - (ht/C)*(2*gridValues[k][I-1]*this.K/(hx*hx) + PHI(hx * I));
        }

        return gridValues;
    }
}
