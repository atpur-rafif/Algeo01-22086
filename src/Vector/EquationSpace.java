package Vector;

public class EquationSpace extends VectorSpace{
    public int independentVariableCount = 0;
    EquationSpace(int indepentdentVariableCount){
        super(indepentdentVariableCount);
        this.independentVariableCount = indepentdentVariableCount;
    }
}