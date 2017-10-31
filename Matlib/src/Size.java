public class Size {
    int rows, cols;
    Size(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public String toString() {
        return "("+rows+", "+cols+")";
    }

    public int totalSize() {
        return rows * cols;
    }

    public boolean equalsSize(Size other) {
        System.out.println("test");
        return rows == other.rows && cols == other.cols;
    }
}
