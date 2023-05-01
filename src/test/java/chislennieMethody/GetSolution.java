package chislennieMethody;






public class GetSolution {


    public static double[] highTriangleMatrix( double[][] matrix, double[] b) {
        int n = b.length;
        double[] x = new double[b.length];
        x[n - 1] = b[n - 1] / matrix[n - 1][n - 1]; //находим значение x_n
        for (int i = n - 2; i >= 0; i--) {
            double sum = b[i];
            for (int j = i + 1; j < n; j++) {
                sum -= matrix[i][j] * x[j];
            }
            x[i] = sum / matrix[i][i];
        }
        return x;
    }

    public static double[] lowTriangleMatrix(double[][] matrix, double[] b) {
        int n = b.length;
        double[] alpha = new double[n];
        double[] beta = new double[n];
        double[] x = new double[n];
        beta[0] = b[0] / matrix[0][0];
        x[0] = beta[0];
        for (int i = 1; i < n; i++) {
            double m = matrix[i][i] + matrix[i][i - 1] * alpha[i - 1];
            alpha[i] = -matrix[i][i] / m;
            beta[i] = (b[i] - matrix[i][i - 1] * beta[i - 1]) / m;
        }
        for (int i = 1; i < n; i++) {
            x[i] = alpha[i] * x[i] + beta[i];
        }
        return x;
    }

    public static double[] solveLinearSystem(double[][] matrix, double[] vector) { //метод  исключения Гаусса
        double[] solution = new double[matrix.length];
        for (int k = 0; k < matrix.length; k++) {
            int maxRow = k;
            for (int i = k + 1; i < matrix.length; i++) {
                if (Math.abs(matrix[i][k]) > Math.abs(matrix[maxRow][k])) {
                    maxRow = i;
                }
            }
            double[] tmp = matrix[k];
            matrix[k] = matrix[maxRow];
            matrix[maxRow] = tmp;
            double temp = vector[k];
            vector[k] = vector[maxRow];
            vector[maxRow] = temp;
            for (int i = k + 1; i < matrix.length; i++) {
                double factor = matrix[i][k] / matrix[k][k];
                vector[i] -= factor * vector[k];
                for (int j = k; j < matrix.length; j++) {
                    matrix[i][j] -= factor * matrix[k][j];
                }
            }
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < matrix.length; j++) {
                sum += matrix[i][j] * solution[j];
            }
            solution[i] = (vector[i] - sum) / matrix[i][i];
        }
        return solution;
    }



}
