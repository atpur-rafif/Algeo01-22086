package Menu;

import java.util.Scanner;
import java.util.function.Function;

public class Prompter {
    private static Scanner scanner = new Scanner(System.in);

    public static int getBoundedInt(String[] prompt, int min, int max){
        return Integer.parseInt(get(prompt, (var v) -> {
            try {
                var i = Integer.parseInt(v);
                return min <= i && i <= max;
            } catch (Exception e) {
                return false;
            }
        }, (var k) -> {
            return "Input tidak valid";
        }));
    }

    public static String get(String[] prompt, Function<String, Boolean> checkFn, Function<String, String> onFalse){
        boolean isFinished = false;
        String result = "";

        for(int i = 0; i < prompt.length; ++i){
            System.out.print(prompt[i] + "\n");
        }
        System.out.print("input> ");

        while(!isFinished){
            String input = scanner.next();
            if(checkFn.apply(input)){
                result = input;
                isFinished = true;
            } else {
                System.out.println(onFalse.apply(input));
                System.out.print("input> ");
            }
        }

        return result;
    }
}
