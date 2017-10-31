package ops.multithreaded;

class RowCastDouble extends Thread {
    int a[];
    double b[];

    RowCastDouble(int a[], double b[], int i) {
        super("Row " + i);
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < a.length; i++) {
            b[i] = (double) a[i];
        }
    }
}

public class MatCastToDouble {
    public static double[][] operate(int arr[][]) {
        double res[][] = new double[arr.length][arr[0].length];
        RowCastDouble threads[] = new RowCastDouble[res.length];
        for (int i = 0; i < arr.length; i++) {
            threads[i] = new RowCastDouble(arr[i], res[i], i);
        }
        ThreadOps.startAllThreads(threads);
        ThreadOps.waitForThreads(threads);
        return res;
    }
}
