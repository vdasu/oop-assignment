package ops.multithreaded;

class RowAdd extends Thread {
    private double[] ans, a, b;
    RowAdd(double ans[], double a[], double b[], int i) {
        super("RowAdd " + i);
        this.ans = ans;
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        for(int i = 0; i < a.length; i++) {
            ans[i] = a[i]+b[i];
        }
    }
}

public class MatAdd {
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
        RowAdd threads[] = new RowAdd[in1.length];
        for (int i = 0; i < in1.length; i++) {
            threads[i] = new RowAdd(res[i], in1[i], in2[i], i);
        }
        ThreadOps.startAllThreads(threads);
        ThreadOps.waitForThreads(threads);
        return res;
    }
}
