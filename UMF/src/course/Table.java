package course;

public class Table {

    public static void main(String[] args) {
        Table table = new Table();
        table.viewTable(10, 5, new KrankScheme(), 4, 2);
        table.viewTable(10, 5, new ExplicitScheme(), 4, 2);
        table.viewTable(10, 5, new ImplicitScheme(), 4, 2);
    }

    public void viewTable(int K, int I, Scheme scheme, int KMultiplicator, int IMultiplicator) {
        int rowsNumber = 4; //количество строк
        Solution solution = new Solution();
        double hx, ht;
        hx = solution.getL() / I; //считаем шаги
        ht = solution.getT() / K;
        double[][] gridValues = scheme.scheme(hx, ht); //заполняем матрицы для первой строки
        double[][] solutionValues = new double[K + 1][I + 1];
        double[] eps = new double[rowsNumber + 1]; // на единицу больше строк, потому что нужно посчитать значения дельты и эпсилона с меньшим шагом для последней строки
        double[] epsMini = new double[rowsNumber + 1];
        double[] delta = new double[rowsNumber + 1];
        int[] KArr = new int[rowsNumber + 2];
        int[] IArr = new int[rowsNumber + 2];
        KArr[0] = K;
        IArr[0] = I;
        eps[0] = Double.MAX_VALUE;


        // TODO: 19.04.2016 Случай для первой строки
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= I; j++) {
                solutionValues[i][j] = solution.getSumWithLimit(j * hx, i * ht, solution.getN());
            }
        }
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= I; j++) {
                solutionValues[i][j] -= gridValues[i][j];
                eps[0] = Math.abs(solutionValues[i][j]) < eps[0] ? Math.abs(solutionValues[i][j]) : eps[0];
            }
        }


        K *= KMultiplicator;
        I *= IMultiplicator;
        KArr[1] = K;
        IArr[1] = I;
        hx = solution.getL() / I;
        ht = solution.getT() / K;
        gridValues = scheme.scheme(hx, ht);
        solutionValues = new double[K + 1][I + 1];


        // TODO: 19.04.2016 Случай для остальных, кроме последней
        for (int k = 1; k < rowsNumber; k++) {
            eps[k] = Double.MAX_VALUE;
            for (int i = 0; i <= K; i++) {
                for (int j = 0; j <= I; j++) {
                    solutionValues[i][j] = solution.getSumWithLimit(j * hx, i * ht, solution.getN());
                }
            }
            for (int i = 0; i <= K; i++) {
                for (int j = 0; j <= I; j++) {
                    solutionValues[i][j] -= gridValues[i][j];
                    eps[k] = Math.abs(solutionValues[i][j]) < eps[k] ? Math.abs(solutionValues[i][j]) : eps[k];
                }
            }

            K *= KMultiplicator;
            I *= IMultiplicator;
            KArr[k + 1] = K;
            IArr[k + 1] = I;
            hx = solution.getL() / I;
            ht = solution.getT() / K;
            gridValues = scheme.scheme(hx, ht);
            solutionValues = new double[K + 1][I + 1];
        }

        // TODO: 19.04.2016 Случай для последней строки
        eps[rowsNumber] = Double.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= I; j++) {
                solutionValues[i][j] = solution.getSumWithLimit(j * hx, i * ht, solution.getN());
            }
        }
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= I; j++) {
                solutionValues[i][j] -= gridValues[i][j];
                eps[rowsNumber] = Math.abs(solutionValues[i][j]) < eps[rowsNumber] ? Math.abs(solutionValues[i][j]) : eps[rowsNumber];
            }
        }


        // TODO: 19.04.2016 Заполняем епсилон с уменьшенным шагом и считаем дельту
        for (int i = 0; i < rowsNumber; i++) {
            epsMini[i] = eps[i + 1];
            delta[i] = eps[i] / epsMini[i];
            // delta[i] = epsMini[i] / eps[i];
        }


        System.out.println(scheme.toString());
        System.out.println("K \t I \t eps \t epsMini \t delta");
        for (int i = 0; i < rowsNumber; i++) {
            System.out.println(KArr[i] + " \t " + IArr[i] + " \t " + eps[i] + " \t " + epsMini[i] + " \t " + delta[i]);
        }
    }
}
