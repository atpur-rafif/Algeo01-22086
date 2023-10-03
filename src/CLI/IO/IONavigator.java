package CLI.IO;

import java.util.Stack;

public class IONavigator {
    Stack<String> stack;

    IONavigator(){
        this.stack = new Stack<String>();
    }

    public void clear(){
        System.out.print("\033\143");
    }

    public void nextPanel(){

    }

    public void backPanel(){

    }
}
