package Menu;

import java.util.Scanner;
import java.util.function.Function;

import Vector.EuclideanSpace;

public class Prompter {
    private static String inputPrompt = "> ";
    private static Scanner scanner = new Scanner(System.in);

    public static void printListOfString(String[] strings){
        for(int i = 0; i < strings.length; ++i){
            System.out.print(strings[i]);
            if(i != strings.length - 1) System.out.print("\n");
        }
    }

    public static String getInlineString(String prompt){
        System.out.print(prompt);
        var s = scanner.next();
        System.out.print("\n");
        return s;
    }

    public static EuclideanSpace getEuclideanVectorInline(String prompt, int dimension){
        EuclideanSpace r = null;
        System.out.print(prompt);

        while(r == null){
            try {
                var tmp = new EuclideanSpace(dimension);
                for(int i = 0; i < dimension; ++i){
                    var value = scanner.nextDouble();
                    tmp.set(i, value);
                }
                r = tmp;
            } catch (Exception e) {
                System.out.println("Input tidak valid");
            }
        }

        return r;
    }

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
            return null;
        }, (var u) -> {
            return "Input tidak valid";
        });
    }

    public static <T> T get(String[] prompt, Function<String, T> checkFn, Function<String, String> onFalse){
        T result = null;

        printListOfString(prompt);
        System.out.print("\n" + inputPrompt);

        while(true){
            String input = scanner.next();
            T t = checkFn.apply(input);
            if(t != null){
                result = t;
                System.out.print("\n");
                break;
            } else {
                System.out.println(onFalse.apply(input));
                System.out.print(inputPrompt);
            }
        }

        return result;
    }
}
