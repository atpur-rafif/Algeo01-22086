package Point;

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

    public double apply(Vector independentVariable){
        double r = 0;
        for(int i = 0; i < this.independentVariableCount; ++i){
            r += this.getCoefficient(i) * independentVariable.getComponent(i);;
        }
        return r;
    }
}
