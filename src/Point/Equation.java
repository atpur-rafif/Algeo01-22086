package Point;

import Matrix.Matrix;

public class Equation {
    private double[] data;
    public int independentVariableCount;

    public Equation(int independentVariableCount){
        this.independentVariableCount = independentVariableCount;
        this.data = new double[independentVariableCount];
        for(int i = 0; i < independentVariableCount; ++i) this.data[i] = 0;
    }

    public double getCoefficient(int i){
        return this.data[i];
    }

    public void setCoefficient(int i, double value){
        this.data[i] = value;
    }

    public double apply(Vector independentVariable){
        double r = 0;
        for(int i = 0; i < this.independentVariableCount; ++i){
            r += this.getCoefficient(i) * independentVariable.getComponent(i);;
        }
        return r;
    }

    public static Matrix toMatrix(Equation eq){
        var m = new Matrix(1, eq.independentVariableCount);
        for(int i = 0; i < eq.independentVariableCount; ++i) m.set(0, i, eq.getCoefficient(i));
        return m;
    }

    public static Equation fromMatrix(Matrix m){
        var eq = new Equation(m.col);
        for(int i = 0; i < m.col; ++i) eq.setCoefficient(i, m.get(0, i));
        return eq;
    }
}
