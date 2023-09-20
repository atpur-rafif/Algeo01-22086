package Point;

import java.util.function.BiFunction;

public class BicubicSplineEquation{
    public static int indepentdentVariableCount = 16;
    public static int maxDegree = 3;

    /*
     * Independent variable disini adalah a, bukan x dan y
     * f(x, y) = \sum_{j = 0}^{3}{\sum{i = 0}^{3}{a_{ij} * x^{i} * y^{j}}} 
     */
    public static GradientEquation createEquation(Point p){
        var EQ = new GradientEquation();
        EQ.f = createF(p);
        EQ.f_x = createF_X(p);
        EQ.f_y = createF_Y(p);
        EQ.f_xy = createF_XY(p);
        return EQ;
    }

    public static int degreeToPos(int i, int j){
        return i + 3 * j;
    }

    private static Equation equationFactory(BiFunction<Integer, Integer, Double> fn){
        var r = new Equation(indepentdentVariableCount);
        for(int j = 0; j < maxDegree; ++j){
            for(int i = 0; i < maxDegree; ++i){
                r.setCoefficient(i, fn.apply(i, j));
            }
        }
        return r;
    }

    public static Equation createF(Point p){
        return equationFactory((var i, var j) -> {
            return Math.pow(p.x, i) * Math.pow(p.y, j);
        });
    }

    public static Equation createF_X(Point p){
        return equationFactory((var i, var j) -> {
            return i * Math.pow(p.x, i - 1) * Math.pow(p.y, j);
        });
    }

    public static Equation createF_Y(Point p){
        return equationFactory((var i, var j) -> {
            return j * Math.pow(p.x, i) * Math.pow(p.y, j - 1);
        });
    }

    public static Equation createF_XY(Point p){
        return equationFactory((var i, var j) -> {
            return i * j * Math.pow(p.x, i - 1) * Math.pow(p.y, j - 1);
        });
    }
}
