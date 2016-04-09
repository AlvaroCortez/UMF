package course;

public class KrankScheme extends Scheme {

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
        gridValues = new double[(int) (T / ht) + 1][(int) ((L / hx)) + 1];
        int I = gridValues[0].length - 1;
        int K = gridValues.length - 1;
        a = new double[I + 1];
        b = new double[I + 1];
        c = new double[I + 1];
        f = new double[I + 1];
        alpha = new double[I + 1];
        beta = new double[I + 1];
        double G = ALPHA * ht / (C * R);
        double gamma = this.K * ht / (C * hx * hx);

        for (int i = 0; i <= I; i++) {
            gridValues[0][i] = PSI(hx * i) - U0;
        }

        b[0] = 1 + G + gamma;
        c[0] = -gamma / 2;
        // TODO: 09.04.2016 Попробовать потом удалить первое слагаемое.
        f[0] = /*gridValues[0][0] * (1 - G - gamma) +*/ gridValues[0][1] * gamma + ht / C * PHI(0);
        alpha[0] = -c[0] / b[0];
        beta[0] = f[0] / b[0];

        for (int k = 1; k <= K; k++) {
            for (int i = 1; i < I; i++) {
                a[i] = -gamma / 2;
                b[i] = 1 + G + gamma;
                c[i] = -gamma / 2;
                // TODO: 09.04.2016 Поменять обозначение, чтобы потом не путаться. Это f[k][i]. Если будет ошибка, то можно попробовать поменять индексы.
                f[i] = gridValues[k - 1][i - 1] * (-a[i]) + gridValues[k - 1][i] * (1 - G - gamma) + gridValues[k - 1][i + 1] * (-c[i]) + (ht / C) * PHI(hx * i);

                alpha[i] = -c[i] / (b[i] + a[i] * alpha[i - 1]);
                beta[i] = (f[i] - a[i] * beta[i - 1]) / (b[i] + a[i] * alpha[i - 1]);
            }

            a[I] = -gamma / 2;
            b[I] = 1 + G + gamma;

            f[I] = /*gridValues[k - 1][I] * (1 - G - gamma) + */2 * gridValues[k - 1][I - 1] * (-c[I]) + (ht / C) * PHI(hx * I);
            beta[I] = (f[I] - a[I] * beta[I - 1]) / (b[I] + a[I] * alpha[I - 1]);

            gridValues[k][I] = beta[I];

            for (int i = I; i > 0; i--) {
                gridValues[k][i - 1] = gridValues[k][i] * alpha[i - 1] + beta[i - 1];
            }

        }
        return gridValues;
    }
}
