package Vector;

public class EuclideanSpace extends VectorSpace{
    public int dimension = 0;
    public EuclideanSpace(int dimension){
        super(dimension);
        this.dimension = dimension;
    }

	@Override
	protected EuclideanSpace _createNewZeroVector() {
        return new EuclideanSpace(this.dimension);
	}
}
