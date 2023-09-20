package Image;

import Point.Equation;
import Point.EquationBySampling_Deprecated;
import Point.GradientEquation;
import Point.LocalSplineTransformation;
import Point.Transformation;

public class ResizingMatrix {
    private static int equationCount = EquationBySampling_Deprecated.equationCount;
    private static int coordinateOffset = (-1) * equationCount + (-1);

    protected static int equationLength = EquationBySampling_Deprecated.equationLength;
    protected static int flattenCoordinate(int x, int y){
        return (y * equationCount) + x - coordinateOffset;
    }

    public static Transformation transformation = new LocalSplineTransformation((var p) -> {
        var EQ = new GradientEquation();
        int x = (int) p.x, y = (int) p.y;

        EQ.f = new Equation(equationLength);
        EQ.f.setCoefficient(flattenCoordinate(x, y), 1);

        EQ.f_x = new Equation(equationLength);
        EQ.f_x.setCoefficient(flattenCoordinate(x + 1, y),0.5);
        EQ.f_x.setCoefficient(flattenCoordinate(x - 1, y),-0.5);

        EQ.f_y = new Equation(equationLength);
        EQ.f_y.setCoefficient(flattenCoordinate(x, y + 1), 0.5);
        EQ.f_y.setCoefficient(flattenCoordinate(x, y - 1), -0.5);

        EQ.f_xy = new Equation(equationLength);
        EQ.f_xy.setCoefficient(flattenCoordinate(x + 1, y + 1),  0.25);
        EQ.f_xy.setCoefficient(flattenCoordinate(x - 1, y    ), -0.25);
        EQ.f_xy.setCoefficient(flattenCoordinate(x    , y - 1), -0.25);
        EQ.f_xy.setCoefficient(flattenCoordinate(x    , y    ), -0.25);

        return EQ;
    });


}
