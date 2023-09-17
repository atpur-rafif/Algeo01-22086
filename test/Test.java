import Matrix.*;
import Application.*;
import Temp.*;

public class Test {
    public static void main(String[] args){
        var I = MultipleLinearInput.getInput();

        var M = MultipleLinear.solve(I);
        
        for(int i = 0; i < M.length; ++i){
            System.out.print(M[i] + " ");
        }
    }   
}