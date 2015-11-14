package course;

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
    private final long N = 1000;

    private double PSI(int n){
        return (n==2) ? 1d : 0d;
    }

    private double PHI(int n){
        return (Math.sin(2*Math.PI*n/3) - Math.sin(Math.PI*n/3))*2/(Math.PI*n);
    }

    private double sub(int n){
        return (2*ALPHA*L*L + K*R*Math.pow(Math.PI*n,2))/(C*R*L*L);
    }

    private double getFirstSummand(double t){
        return R*(1 - Math.exp(-2*ALPHA*t/C*R))/3*ALPHA;
    }

    public double getSumWithLimit(double x, double t, long N){
        R = Math.sqrt(S/Math.PI);
        double summa = getFirstSummand(t);
        for (int n = 1; n < N; n++) {
            double e = Math.exp(-1d*sub(n)*t);
            summa = summa + (PHI(n)*(1d - e)/(C*sub(n)))*Math.cos(Math.PI*n*x/L);
        }
        summa= summa + Math.exp(-1d*sub(2)*t)*Math.cos(2d * Math.PI*x/L);
        return summa;
    }

    public double getSumWithAccuracy(double x, double t, double eps){
        long N = getNumberOfIteration(eps);
        return getSumWithLimit(x, t, N);
    }

    public double row(double n) {
        return 1/Math.pow(n, 3);
    }

    public long getNumberOfIteration(double eps){
        long N = 0;
        R = Math.sqrt(S/Math.PI);
        double original = 1.20205690315031*4*R*L*L/Math.PI;
        double result = 0;
        do{
            N++;
            result+=row(N)*4*R*L*L/Math.PI;
        } while (Math.abs(original - result)>eps);
        return N;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getNumberOfIteration(0.0001));
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

    public double getSmallT() {
        return smallT;
    }

    public void setSmallT(double smallT) {
        this.smallT = smallT;
    }
}
