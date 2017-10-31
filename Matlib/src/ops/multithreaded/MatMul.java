package ops.multithreaded;

class RowMul extends Thread {
    double arr[], arr2[][], ans[][];

    RowMul(double arr[], double arr2[][], double ans[][], int i) {
        super("RowMul " + i);
        this.arr = arr;
        this.arr2 = arr2;
        this.ans = ans;
    }

    @Override
    public void run() {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr2[0].length; j++) {
                continue;
            }
        }
    }
}

public class MatMul {

    public static double[][] operate(int[][] in1, int[][] in2) {
        double in1d[][] = MatCastToDouble.operate(in1);
        double in2d[][] = MatCastToDouble.operate(in2);
        return operate(in1d, in2d);
    }

    public static double[][] operate(int[][] in1, double[][] in2) {
        return operate(MatCastToDouble.operate(in1), in2);
    }

    public static double[][] operate(double[][] in1, int[][] in2) {
        return operate(in1, MatCastToDouble.operate(in2));
    }

    public static double[][] operate(double[][] in1, double[][] in2) {
        double res[][] = new double[in1.length][in1[0].length];
        RowMul threads[] = new RowMul[in1.length];
        for (int i = 0; i < in1.length; i++) {
            threads[i] = new RowMul(in1[i], in2, res, i);
        }
        ThreadOps.startAllThreads(threads);
        ThreadOps.waitForThreads(threads);
        return res;
    }
}
