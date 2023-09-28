package Menu;

public class StringFormatter {

    public static String createSubscript(int value){
        String r = "";
        if(value < 0) return r;

        String tmp = Integer.toString(value);
        for(int i = 0; i < tmp.length(); ++i){
            int offset = Integer.parseInt(String.valueOf(tmp.charAt(i)));
            r += String.valueOf((char)('\u2080' + offset));
        }
        
        return r;
    }

}
