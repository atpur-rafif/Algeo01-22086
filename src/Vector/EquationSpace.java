package Vector;

public class EquationSpace extends VectorSpace{
    public int independentVariableCount = 0;
    public EquationSpace(int indepentdentVariableCount){
        super(indepentdentVariableCount);
        this.independentVariableCount = indepentdentVariableCount;
    }

	@Override
	protected EquationSpace _createNewZeroVector() {
        return new EquationSpace(this.independentVariableCount);
	}
}