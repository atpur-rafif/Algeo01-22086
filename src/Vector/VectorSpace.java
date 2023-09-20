package Vector;

public abstract class VectorSpace {
    double[] data;

    double get(int i){
        return this.data[i];
    }

    void set(int i, double newValue){
        this.data[i] = newValue;
    }
}
