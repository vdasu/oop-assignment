package ops.multithreaded;

class RowCastInt extends Thread {
    double a[];
    int b[];

    RowCastInt(double a[], int b[], int i) {
        super("Row " + i);
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < a.length; i++) {
            b[i] = (int) a[i];
        }
    }
}

public class MatCastToInt {
    public static int[][] operate(double arr[][]) {
        int res[][] = new int[arr.length][arr[0].length];
        RowCastInt threads[] = new RowCastInt[res.length];
        for (int i = 0; i < arr.length; i++) {
            threads[i] = new RowCastInt(arr[i], res[i], i);
        }
        ThreadOps.startAllThreads(threads);
        ThreadOps.waitForThreads(threads);
        return res;
    }
}
