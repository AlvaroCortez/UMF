package course;

public class KrankScheme extends Scheme{

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
    double[] alpha;
    double[] beta;

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
        alpha = new double[I + 1];
        beta = new double[I + 1];
        double G = ALPHA*ht/(C*R);

        double gamma = this.K*ht/(C*hx*hx);

        for (int i = 0; i <= I  /*i < I*/; i++) {
            gridValues[0][i] = PSI(hx * i) - U0;
        }

        for (int k = 1; k <= K; k++) {
//            alpha[0] = 0.1;
//            beta[0] = 0.1;
             b[0] = 1 + G + gamma;
             f[0] = gridValues[0][1] * gamma + ht / C * (hx);
             c[0] = -gamma / 2;
             alpha[0] = c[0] / b[0];
             beta[0] = f[0] / b[0];

            for (int i = 1; i < I; i++) {
                a[i] = -gamma/2;
                 b[i] = 1 + G + gamma;
//                b[i] = 1 - G + gamma;
                c[i] = -gamma/2;
                 f[i] = gridValues[k-1][i-1] *(-a[i]) + gridValues[k-1][i] * (1 - G + gamma) + gridValues[k-1][i+1] * (-c[i]) + (ht/C)*PHI(hx * i);
//                f[i] = gridValues[k-1][i-1] *a[i] + gridValues[k-1][i] * b[i] + gridValues[k-1][i+1] * c[i] + (ht/C)*PHI(hx * i);

                 alpha[i] = c[i] / (b[i] - a[i] * alpha[i - 1]);
//                alpha[i] = - a[i] / (b[i] + c[i] * alpha[i - 1]);
                 beta[i] = (f[i] - a[i] * beta[i - 1]) / (b[i] - a[i] * alpha[i - 1]);
//                beta[i] = (f[i] - c[i] * beta[i - 1]) / (b[i] + c[i] * alpha[i - 1]);
            }

            a[I] = -gamma/2;
             b[I] = 1 + G + gamma;
//            b[I] = 1 - G + gamma;
//            c[I] = -gamma/2;
             f[I] = gridValues[k-1][I-1] *(-a[I]) + gridValues[k-1][I] * (1 - G + gamma) + gridValues[k-1][I-1] * (-c[I]) + (ht/C)*PHI(hx * I);
//            f[I] = gridValues[k-1][I-1] *a[I] + gridValues[k-1][I] * b[I] + gridValues[k-1][I-1] * c[I] + (ht/C)*PHI(hx * I);
             alpha[I] = c[I] / (b[I] - a[I] * alpha[I - 1]);
//            alpha[I] = - a[I] / (b[I] + c[I] * alpha[I - 1]);
             beta[I] = (f[I] - a[I] * beta[I - 1]) / (b[I] + a[I] * alpha[I - 1]);
//            beta[I] = (f[I] - c[I] * beta[I - 1]) / (b[I] + c[I] * alpha[I - 1]);

            gridValues[k][I] = beta[I];
//            gridValues[k][I] = (gridValues[k - 1][I] - a[I]* beta[I - 1] + c[I]* beta[I - 1])/(a[I]* alpha[I - 1] + b[I] + c[I]* alpha[I - 1]);
            for (int i = I; i > 0; i--) {
                gridValues[k][i - 1] = -gridValues[k][i]* alpha[i - 1] + beta[i - 1];
//                gridValues[k][i - 1] = gridValues[k][i]* alpha[i - 1] + beta[i - 1];
            }
        }

        return gridValues;

    }
}
