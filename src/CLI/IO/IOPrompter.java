package CLI.IO;

import java.util.Scanner;
import java.util.function.Function;

import Vector.EuclideanSpace;

public class IOPrompter {
    private static Scanner scanner = new Scanner(System.in);

    public static void printMultiLine(String[] strings){
        for(int i = 0; i < strings.length; ++i){
            System.out.print(strings[i]);
            System.out.print("\n");
        }
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
        return get(prompt, (var s) -> {
            if(s.equalsIgnoreCase("y")) return true;
            else if(s.equalsIgnoreCase("n")) return false;
            return null;
        });
    }

    public static IOType getIOType(){
        printMultiLine(new String[]{
            "Metode input",
            "1. CLI",
            "2. File"
        });
        return get("Input Type>", (String v) -> {
            if(v.equals("1")) return IOType.CLI;
            else if(v.equals(v)) return IOType.File;
            else return null;
        });
    }

    public static int getInteger(String prompt){
        return get(prompt, (var v) -> {
            try {
                var i = Integer.parseInt(v);
                return i;
            } catch (Exception e) {}
            return null;
        });
    }

    public static double getDouble(String prompt){
        return get(prompt, (var v) -> {
            try {
                var i = Double.parseDouble(v);
                return i;
            } catch (Exception e) {}
            return null;
        });
    }

    public static int getBoundedInt(String prompt, int min, int max){
        return get(prompt, (var v) -> {
            try {
                var i = Integer.parseInt(v);
                if(min <= i && i <= max) return i;
            } catch (Exception e) {}
            return null;
        });
    }

    public static String getString(String prompt){
        return get(prompt, (var v) -> { return (String) v; });
    }

    public static <T> T get(String prompt, Function<String, T> checkFn){
        return get(prompt, checkFn, (var n) -> { return "\n(Input tidak valid)"; });
    }

    public static <T> T get(String prompt, Function<String, T> checkFn, Function<String, String> onFalse){
        T result = null;

        while(true){
            System.out.print(prompt);
            String input = scanner.next();
            T t = checkFn.apply(input);
            if(t != null){
                result = t;
                break;
            } else {
                System.out.print(onFalse.apply(input));
            }
        }

        return result;
    }
}
