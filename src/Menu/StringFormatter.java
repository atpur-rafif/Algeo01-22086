package Menu;

import java.util.function.Function;

import Matrix.Matrix;
import Vector.BicubicSplineSpace;
import Vector.EquationSpace;

public class StringFormatter {

    private static String offsetIntString(int value, Function<Character, Character> fn){
        String r = "";
        if(value < 0) return r;

        String tmp = Integer.toString(value);
        for(int i = 0; i < tmp.length(); ++i){
            r += fn.apply(tmp.charAt(i));
        }
        
        return r;
    }

    public static String createSubscript(int value){ 
        char offset = '\u2080';
        return offsetIntString(value, (var c) -> {
            char r = (char) (offset + Integer.parseInt(String.valueOf(c)));
            return r;
        });
    }

    public static String createSuperscript(int value){ 
        return offsetIntString(value, (var c) -> {
            int v = Integer.parseInt(String.valueOf(c));

            char r = ' ';
            switch(v){
                case 1:
                    r = '\u00B9';
                    break;
                case 2:
                    r = '\u00B2';
                    break;
                case 3:
                    r = '\u00B3'; 
                    break;  
                default:
                    r = (char) ('\u2070' + v);
                    break;
            }

            return r;
        });
    }

    public static String matrix(Matrix m){
        String r = "";
        for(int i = 0; i < m.row; ++i){
            for(int j = 0; j < m.col; ++j){
                r += m.get(i, j);
                if(j != m.col - 1) r += " ";
            }
            if(i != m.row - 1) r += "\n";
        }

        return r;
    }

    public static String bicubicSpline(EquationSpace eq){
        String r = "";

        var maxDegree = BicubicSplineSpace.maxDegree;
        for(int j = 0; j <= maxDegree; ++j){
            for(int i = 0; i <= maxDegree; ++i){
                var t = eq.get(j * (maxDegree + 1) + i);
                if(t != 0){
                    r += eq.get(j * (maxDegree + 1) + i);
                    if(i != 0) r += "x" + createSuperscript(i);
                    if(j != 0) r += "y" + createSuperscript(j);
                    if(j != maxDegree || i != maxDegree) r += " + ";
                }
            }
        }

        return r;
    }

    public static String multipleRegression(EquationSpace eq){
        String r = "";
        for(int i = 0; i < eq.basisCount; ++i){
            var t = eq.get(i);
            if(t != 0){
                if(i == 0) r += t;
                else{
                    r += t + "x" + createSubscript(i);
                }
                if(i != eq.basisCount - 1) r += " + ";
            }
        }
        return r;
    }

    public static String polynomialEquation(EquationSpace eq){
        String r = "";
        for(int i = 0; i < eq.basisCount; ++i){
            var t = eq.get(i);
            r += t;
            if(i != 0) r += "x" + createSuperscript(i);
            if(i != eq.basisCount - 1) r += " + ";
        }
        return r;
    }
}
