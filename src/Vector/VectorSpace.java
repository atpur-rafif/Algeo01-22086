package Vector;

public class VectorSpace {
    public int basisCount;
    double[] data;

    VectorSpace(int basisCount){
        this.basisCount = basisCount;
        this.data = new double[basisCount];
    }

    double get(int i){
        return this.data[i];
    }

    void set(int i, double newValue){
        this.data[i] = newValue;
    }

    public static VectorSpace add(VectorSpace v1, VectorSpace v2){
        if(v1.basisCount != v2.basisCount) throw new Error("Basis count should be equal");
        var r = new VectorSpace(v1.basisCount);
        for(int i = 0; i < v1.basisCount; ++i){
            r.set(i, v1.get(i) + v2.get(i));
        }
        return r;
    }

    public static double innerProduct(VectorSpace v1, VectorSpace v2){
        if(v1.basisCount != v2.basisCount) throw new Error("Basis count should be equal");
        double r = 0;
        for(int i = 0; i < v1.basisCount; ++i) r += v1.get(i) * v2.get(i);
        return r;
    }
}
