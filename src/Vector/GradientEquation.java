package Vector;

public class GradientEquation {
    public static int equationCount = 4;
    public EquationSpace f;
    public EquationSpace f_x;
    public EquationSpace f_y;
    public EquationSpace f_xy;

    EquationSpace[] toArray(){
        EquationSpace[] EQ = {f, f_x, f_y, f_xy};
        return EQ;
    }
}
