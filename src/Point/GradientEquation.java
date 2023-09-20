package Point;

public class GradientEquation {
    public static int equationCount = 4;
    public Equation f;
    public Equation f_x;
    public Equation f_y;
    public Equation f_xy;

    Equation[] toArray(){
        Equation[] EQ = {f, f_x, f_y, f_xy};
        return EQ;
    }
}
