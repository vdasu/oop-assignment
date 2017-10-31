import exceptions.ResizeMismatchException;
import exceptions.ShapeMismatchException;
import ops.multithreaded.MatAdd;
import ops.multithreaded.MatCastToInt;
import ops.multithreaded.MatSub;
import ops.multithreaded.MatMul;

public class IntegerMatrix implements Matrix {
    int matrix[][];
    Size size;

    public IntegerMatrix(int matrix[][]) {
        this.matrix = matrix;
        size = new Size(matrix.length, matrix[0].length);
    }

    public IntegerMatrix(int rows, int cols) {
        matrix = new int[rows][cols];
        size = new Size(rows, cols);
    }

    public IntegerMatrix(Size size) {
        matrix = new int[size.rows][size.cols];
    }

    @Override
    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public Matrix add(Matrix other) {
        try {
            if (!this.size.equalsSize(other.getSize()))
                throw new ShapeMismatchException(this.size + " is not equal to " + other.getSize());
        } catch (ShapeMismatchException e) {
            e.printStackTrace();
            System.exit(0);
        }
        double ans[][] = MatAdd.operate(matrix, other.getMatrix());

        return new IntegerMatrix(MatCastToInt.operate(ans));
    }

    @Override
    public Matrix subtract(Matrix other) {
        try {
            if (!this.size.equalsSize(other.getSize()))
                throw new ShapeMismatchException(this.size + " is not equal to " + other.getSize());
        } catch (ShapeMismatchException e) {
            e.printStackTrace();
            System.exit(0);
        }
        double ans[][] = MatSub.operate(matrix, other.getMatrix());

        return new IntegerMatrix(MatCastToInt.operate(ans));
    }

    @Override
    public Matrix multiply(Matrix other) {
        try {
            if(!(this.size.cols != other.getSize().rows))
                throw new ShapeMismatchException(this.size.cols + " is not equal to " + other.getSize().rows);
        } catch (ShapeMismatchException e) {
            e.printStackTrace();
            System.exit(0);
        }
        double ans[][] = MatMul.operate(matrix, other.getMatrix());
        return new IntegerMatrix(MatCastToInt.operate(ans));

    }

    public boolean equalsMatrix(IntegerMatrix other) {
        if (!getSize().equalsSize(other.getSize())) {
            return false;
        } else {
            int arr[][] = other.getMatrix();
            for (int i = 0; i < size.rows; i++) {
                for (int j = 0; j < size.rows; j++) {
                    if (matrix[i][j] != arr[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
/*
    public DoubleMatrix toDouble() {
        double mat[][] = new double[size.rows][size.cols];
        for(int i = 0; i < size.rows; i++)
            for(int j = 0; j < size.cols; j++)
                mat[i][j] = (double)matrix[i][j];
        return new DoubleMatrix(mat);
    }*/

    public Size getSize() {
        return size;
    }

    public void resize(Size newSize) {
        try {
            if (size.totalSize() != newSize.totalSize())
                throw new ResizeMismatchException("Cannot convert from " + size + " to " + newSize);
        } catch (ResizeMismatchException e) {
            e.printStackTrace();
            System.exit(0);
        }
        int resizedMatrix[][] = new int[newSize.rows][newSize.cols];
        for (int i = 0; i < size.totalSize(); i++) {
            resizedMatrix[i / newSize.cols][i % newSize.cols] = matrix[i / size.cols][i % size.cols];
        }
        size = newSize;
        matrix = resizedMatrix;
    }

    public String toString() {
        StringBuffer s = new StringBuffer("[");
        for (int i = 0; i < size.rows; i++) {
            s.append("[");
            for (int j = 0; j < size.cols; j++)
                s.append(matrix[i][j] + ", ");
            s.delete(s.length() - 2, s.length());
            s.append("],\n");
        }
        s.delete(s.length() - 2, s.length());
        s.append("]");
        return s.toString();
    }

    public static void main(String args[]) {
        int a[][] = {{1, 2, 3}, {4, 5, 6}};
        int b[][] = {{1, 2, 3}, {4, 5, 6}};
        IntegerMatrix c = new IntegerMatrix(a);
        IntegerMatrix d = new IntegerMatrix(b);
        System.out.println(c.subtract(d));

    }
}
