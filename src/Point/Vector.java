package Point;

import Matrix.Matrix;

public class Vector {
    private double[] data;
    public int dimension;

    public Vector(int dimension){
        this.dimension = dimension;
        this.data = new double[dimension];
        for(int i = 0; i < dimension; ++i) this.data[i] = 0;
    }

    public double getComponent(int i){
        return this.data[i];
    }

    public void setComponent(int i, double value){
        this.data[i] = value;
    }

    public static Matrix toMatrix(Vector v){
        var m = new Matrix(v.dimension, 1);
        for(int i = 0; i < v.dimension; ++i) m.set(i, 0, v.getComponent(i));
        return m;
    }

    public static Vector fromMatrix(Matrix m){
        var v = new Vector(m.row);
        for(int i = 0; i < m.row; ++i) v.setComponent(i, m.get(i, 0));
        return v;
    }
}