package Vector;

public class VectorSpace{
    public int basisCount;
    double[] data;

    public VectorSpace(int basisCount){
        this.basisCount = basisCount;
        this.data = new double[basisCount];
        for(int i = 0; i < basisCount; ++i) this.set(i, 0);
    }

    public double get(int i){
        return this.data[i];
    }

    public void set(int i, double newValue){
        this.data[i] = newValue;
    }

    @SuppressWarnings("unchecked")
    private static <T extends VectorSpace> T createBase(T t){
        try {
            var clazz = t.getClass();
            t = (T) clazz.getDeclaredConstructor(int.class).newInstance(t.basisCount);
        } catch (Exception e) {
        }
        return t;
    }

    public static <T extends VectorSpace> T add(T v1, T v2){
        if(v1.basisCount != v2.basisCount) throw new Error("Basis count should be equal");
        var r = createBase(v1);
        for (int i = 0; i < v1.basisCount; ++i) {
            r.set(i, v1.get(i) + v2.get(i));
        }
        return v1;
    }

    public static <T extends VectorSpace> T scale(T v, double s){
        var r = createBase(v);
        for(int i = 0; i < v.basisCount; ++i) r.set(i, v.get(i) * s);
        return r;
    }

    public static <T extends VectorSpace> double innerProduct(T v1, T v2){
        if(v1.basisCount != v2.basisCount) throw new Error("Basis count should be equal");
        double r = 0;
        for(int i = 0; i < v1.basisCount; ++i) r += v1.get(i) * v2.get(i);
        return r;
    }
}
