package Menu;

public class PrintListMenu {
    
    public static void Print(String[] strings) {
        for (String str : strings) {
            System.out.println(str);
        }
    }

    public static void Repetitive(int Menu) {
        String[] printerMenu = {
            "==================================Cara Input=================================", 
            "1. CLI", 
            "2. File", 
            "3. Back",
            "*Note: Pilih menggunakan angka yang sesuai",
        };

        if(Menu == 1){
            printerMenu[0] = "==============================Interpolasi Polinomial Menu================================";
        }
        else if(Menu == 2){
            printerMenu[0] = "==============================Regresi Linear Berganda================================";
        }
        else if(Menu == 5){
            printerMenu[0] = "=============================Sistem Persamaan Linear Menu=================================";
        }
        else if(Menu == 6){
            printerMenu[0] = "================================Determinant Menu======================================";
        }
        else if(Menu == 7){
            printerMenu[0] = "================================Inverse Menu==========================================";
        }
        Print(printerMenu);
    }

    public static void clear(){
        System.out.print("\033\143");
    }

}
