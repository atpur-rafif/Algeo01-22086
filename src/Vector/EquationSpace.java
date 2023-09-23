package Vector;

public class EquationSpace extends VectorSpace{
    public int independentVariableCount = 0;
    public EquationSpace(int indepentdentVariableCount){
        super(indepentdentVariableCount);
        this.independentVariableCount = indepentdentVariableCount;
    }
}