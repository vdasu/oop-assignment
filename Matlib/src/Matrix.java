public interface Matrix
{
    default Matrix add(Matrix other) { return null; }
    default Matrix multiply(Matrix other) { return null; }
    default Matrix subtract(Matrix other) { return null; }
    default Size getSize() { return null; }
    default int[][] getMatrix() { return null; }
    //default double[][] getMatrix() { return null; }
}
