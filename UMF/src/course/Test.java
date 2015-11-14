package course;

public class Test {

	public static double row(double n) {
         return 1/Math.pow(n, 3);
     }

    public static double s(double l, double k) {
//        return 4*l*l/(k*Math.pow(Math.PI, 3));
        return 4*0.04*l*l/Math.PI;
//        return 4/Math.PI;
    }

    public static int findN(double eps) {
        int N = 1;
        double origin = s(1.0, 0.005) * 1.20205690315031; // 1.20205690315031 этому равна сумма ряда от 1 до бесконечности.
        double result = s(1.0, 0.005) * row(N + 1);
        if (Math.abs(origin - result) > eps) {
            return N;
        } else {
            N++;
            N++;
            while (true) {
                result += s(1.0, 0.005) * Math.abs(s(1, 0.005) * row(N));
                if (Math.abs(origin - result) > eps) {
                    return N;
                }
                N++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(findN(0.000001));
    }
}