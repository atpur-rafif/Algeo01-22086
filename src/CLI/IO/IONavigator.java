package CLI.IO;

import java.util.Stack;

public class IONavigator {
    private static Stack<String> stack = new Stack<String>();

    public static void clear(){
        System.out.print("\033\143");
    }

    private static String getNavigatorView(){
        String[] strings = new String[stack.size()];
        stack.toArray(strings);
        return IOStringFormatter.combineString(strings, " > ");
    }

    public static void next(String name){
        stack.add(name);
        clear();
        System.out.println(getNavigatorView());
    }

    public static void back(){
        stack.pop();
        clear();
        System.out.println(getNavigatorView());
    }
}
