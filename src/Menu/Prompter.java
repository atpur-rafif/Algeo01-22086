package Menu;

import java.util.Scanner;
import java.util.function.Function;

public class Prompter {
    private static String ask = "> ";
    private static Scanner scanner = new Scanner(System.in);

    public static boolean getBoolean(String prompt){
        prompt += " (Y/N)?";
        return get(new String[]{prompt}, (var s) -> {
            if(s.equalsIgnoreCase("y")) return true;
            else if(s.equalsIgnoreCase("n")) return false;
            return null;
        }, (var u) -> {
            return "Input tidak valid";
        });
    }

    public static IOType getIOType(){
        return get(new String[]{
            "Metode input",
            "1. CLI",
            "2. File"
        }, (String v) -> {
            if(v.equals("1")) return IOType.CLI;
            else if(v.equals(v)) return IOType.File;
            else return null;
        }, (var u) -> {
            return "Input tidak valid";
        });
    }

    public static int getBoundedInt(String[] prompt, int min, int max){
        return get(prompt, (var v) -> {
            try {
                var i = Integer.parseInt(v);
                if(min <= i && i <= max) return i;
            } catch (Exception e) {}
            throw new Error("FDSF");
        }, (var u) -> {
            return "Input tidak valid";
        });
    }

    public static <T> T get(String[] prompt, Function<String, T> checkFn, Function<String, String> onFalse){
        T result = null;

        for(int i = 0; i < prompt.length; ++i){
            System.out.print(prompt[i] + "\n");
        }
        System.out.print(ask);

        while(true){
            String input = scanner.next();
            T t = checkFn.apply(input);
            if(t != null){
                result = t;
                System.out.print("\n");
                break;
            } else {
                System.out.println(onFalse.apply(input));
                System.out.print(ask);
            }
        }

        return result;
    }
}
