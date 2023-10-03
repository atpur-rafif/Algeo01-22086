package CLI.IO;

import java.util.Stack;

public class IONavigator {
    private static Stack<String> stack = new Stack<String>();

    public static void clear(){
        System.out.print("\033\143");
    }

    private static String getNavigatorView(){
        String[] strings = (String[]) stack.toArray();
        return IOStringFormatter.combineString(strings, " > ");
    }

    public static void nextPanel(String name){
        stack.push(name);
        clear();
        System.out.println(getNavigatorView());
    }

    public static void backPanel(){
        stack.pop();
        clear();
        System.out.println(getNavigatorView());
    }
}
