package Image;

import Vector.*;
import Transformation.*;

public class ResizingMatrix {
    private static int equationCount = GradientEquation.equationCount;
    private static int coordinateOffset = (-1) * equationCount + (-1);

    protected static int equationLength = BicubicSplineSpace.indepentdentVariableCount;
    protected static int flattenCoordinate(int x, int y){
        return (y * equationCount) + x - coordinateOffset;
    }

    public static Transformation transformation = new LocalSplineTransformation((var p) -> {
        var EQ = new GradientEquation();
        int x = (int) p.x, y = (int) p.y;

        EQ.f = new EquationSpace(equationLength);
        EQ.f.set(flattenCoordinate(x, y), 1);

        EQ.f_x = new EquationSpace(equationLength);
        EQ.f_x.set(flattenCoordinate(x + 1, y),0.5);
        EQ.f_x.set(flattenCoordinate(x - 1, y),-0.5);

        EQ.f_y = new EquationSpace(equationLength);
        EQ.f_y.set(flattenCoordinate(x, y + 1), 0.5);
        EQ.f_y.set(flattenCoordinate(x, y - 1), -0.5);

        EQ.f_xy = new EquationSpace(equationLength);
        EQ.f_xy.set(flattenCoordinate(x + 1, y + 1),  0.25);
        EQ.f_xy.set(flattenCoordinate(x - 1, y    ), -0.25);
        EQ.f_xy.set(flattenCoordinate(x    , y - 1), -0.25);
        EQ.f_xy.set(flattenCoordinate(x    , y    ), -0.25);

        return EQ;
    });


}
