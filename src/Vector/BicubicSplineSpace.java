package Vector;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BicubicSplineSpace extends EquationSpace{
    public static int indepentdentVariableCount = 16;
    public static int maxDegree = 3;

    /* Basis vector: {a_00, a_10, a_20, a_30, a_01, a_11, a_21, a_31, a_02, a_12, a_22, a_32, a_03, a_13, a_23, a_33} */
    BicubicSplineSpace(){
        super(indepentdentVariableCount);
    }

    /* This pow will return 1 when the exponent is less or equal than 0, whatever the base (including 0) */
    private static double pow(double x, int y){
        double r = 1;
        for(; y > 0; --y) r *= x;
        return r;
    }

    /*
     * Independent variable disini adalah a, bukan x dan y
     * f(x, y) = \sum_{j = 0}^{3}{\sum{i = 0}^{3}{a_{ij} * x^{i} * y^{j}}} 
     */
    public static Function<Point, GradientEquation> createGradientEquationLambda = (Point p) -> {
        var EQ = new GradientEquation();
        EQ.f = createF(p);
        EQ.f_x = createF_X(p);
        EQ.f_y = createF_Y(p);
        EQ.f_xy = createF_XY(p);

        return EQ;
    };

    private static BicubicSplineSpace equationFactory(BiFunction<Integer, Integer, Double> fn){
        var r = new BicubicSplineSpace();
        for(int j = 0; j <= maxDegree; ++j){
            for(int i = 0; i <= maxDegree; ++i){
                r.set((maxDegree + 1) * j + i, fn.apply(i, j));
            }
        }
        return r;
    }

    public static BicubicSplineSpace createF(Point p){
        return equationFactory((var i, var j) -> {
            return pow(p.x, i) * pow(p.y, j);
        });
    }

    public static BicubicSplineSpace createF_X(Point p){
        return equationFactory((var i, var j) -> {
            return i * pow(p.x, i - 1) * pow(p.y, j);
        });
    }

    public static BicubicSplineSpace createF_Y(Point p){
        return equationFactory((var i, var j) -> {
            return j * pow(p.x, i) * pow(p.y, j - 1);
        });
    }

    public static BicubicSplineSpace createF_XY(Point p){
        return equationFactory((var i, var j) -> {
            return i * j * pow(p.x, i - 1) * pow(p.y, j - 1);
        });
    }
}
