/**
 * Created by Den on 05.11.15.
 */
public class Solution {
    private final double L = 1;
    private final double U0 = 0;
    private final double K = 0.005;
    private final double C = 1;
    private final double ALPHA = 0.005;
    private final double T = 300;
    private double smallT = 10;
    private final double S = 0.005;
    private double R = 0;
    private long N = 100;
    private double eps = 0.001;
    private double x = 0;

    private double PSI(int n){
        return (n==2) ? 1d : 0d;
    }

    private double PHI(int n){
        return (Math.sin(2*Math.PI*n/3) - Math.sin(Math.PI*n/3))*4/(2*Math.PI*n + Math.sin(2*Math.PI*n));
    }

    private double sub(int n){
        return (2*ALPHA*L*L+K*R*Math.pow(Math.PI*n,2))/(C*R*L*L);
    }

    private double getFirstSummand(double t){
        return R*(1 - Math.exp(-2*ALPHA*t/(C*R)))/(6*ALPHA);
    }

    public double getSumWithLimit(double x, double t, long N){
        R = Math.sqrt(S/Math.PI);
        double summa = getFirstSummand(t);
        for (int n = 1; n < N; n++) {
            double e = Math.exp(-1d*sub(n)*t);
            summa = summa + (PHI(n)*(1d - e)/(C*sub(n)))*Math.cos(Math.PI*n*x/L);
//            System.out.println("summa = " + summa);
        }
        summa= summa + Math.exp(-1d*sub(2)*t)*Math.cos(2d * Math.PI*x/L)*8*Math.PI/(L*Math.sin(4*Math.PI)+4*L*Math.PI);
        return summa;
    }

    public double getSumWithAccuracy(double x, double t, double eps){
        N = getNumberOfIteration(eps);
        return getSumWithLimit(x, t, N);
    }

    public double row(double n) {
        return 1/Math.pow(n, 3);
//        return 1/(0.01*n + 0.002*n*n*n);
//        return 1/(2*Math.PI*n*n*n + n*n + n);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.oor(0.0001, 0.33, 1);

        /*for (int i = 0; i < 15; i++) {
            System.out.println(solution.getSumWithLimit(0.7, 300, i));
        }*/
    }

    public void oor(double eps, double x, double t) {
        /*double eps = 0.000001;
        double x = 0.7;
        double t = 300;*/

        for (int i = 0; i < 4; i++) {
            long n = this.getNumberOfIteration(eps);
            long n_t = n;
            double uOtN = this.getSumWithLimit(x, t, n--);
            double uOtN_1 = this.getSumWithLimit(x, t, n);
            while (uOtN - uOtN_1 < eps) {
//                uOtN = uOtN_1;
                uOtN_1 = this.getSumWithLimit(x, t, --n);
                if(n == -1) {
                    n = 0;
                    break;
                }
            }
            System.out.println("eps = " + eps);
            System.out.println("N[т] = " + n_t);
            System.out.println("N[п] = " + n);
            eps *= 10;
        }
    }

    public long getNumberOfIteration(double eps){
        long N = 0;
//        long N = 45;
        R = Math.sqrt(S/Math.PI);
//        double original = 1.20205690315031*4*R*L*L/Math.PI;
        double original = 1.20205690315031*4*R*L*L/Math.PI;
//        double original = 1.20205690315031*4*R*L*L/Math.PI*K*R*R*Math.PI/(ALPHA*ALPHA);
//        double original = 1.20205690315031*4*L*L/(Math.PI*Math.PI*Math.PI*R + (2*ALPHA*L*L) + 2);
//        double original = 21.7509*R*8*L*L;
//        double original = 0.150153990445*8*L*L/(Math.PI*Math.PI);
//        double original = 96.9401*R*8*L*L;
        double result = 0;
        double p = original - result;
        do{
            N++;
//            result+=row(N)*4*R*L*L/Math.PI*K*R*R*Math.PI/(ALPHA*ALPHA);
            result+=row(N)*4*R*L*L/Math.PI;
//            result+=row(N)*4*R*L*L/Math.PI;
//            result+=row(N)*8*L*L/(Math.PI*Math.PI);
//            result+=row(N);
//            result += row(N)*4*L*L/(K*Math.PI*Math.PI*Math.PI);
//            result += row(N)*4/Math.PI;
            p = original - result;
           /* System.out.println("result = " + result);
            System.out.println("p = " + Math.abs(p));*/
        } while (Math.abs(p)>=eps);
        return N;
    }

    public double getL() {
        return L;
    }

    public double getU0() {
        return U0;
    }

    public double getK() {
        return K;
    }

    public double getC() {
        return C;
    }

    public double getALPHA() {
        return ALPHA;
    }

    public double getT() {
        return T;
    }

    public double getS() {
        return S;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public long getN() {
        return N;
    }

    public void setN(long n) {
        N = n;
    }

    public double getSmallT() {
        return smallT;
    }

    public void setSmallT(double smallT) {
        this.smallT = smallT;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
