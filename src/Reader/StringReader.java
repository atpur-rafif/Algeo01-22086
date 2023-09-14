package Reader;

import java.util.Scanner;

class Reader{
    String read(){
        var scanner = new Scanner(System.in);
        var string = scanner.nextLine();
        scanner.close();
        return string;
    }
}