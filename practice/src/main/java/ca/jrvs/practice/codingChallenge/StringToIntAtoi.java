package ca.jrvs.practice.codingChallenge;

/**
 * Coding Challenge : String to Integer (atoi)
 * https://www.notion.so/jarvisdev/String-to-Integer-atoi-95b3bdec084144058e75d56dcff4fefc
 */
public class StringToIntAtoi {

    /**
     * This method does not check, Int Max/Mini value
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        String text = s.replaceAll(" ","");
        char [] charArray = text.toCharArray();
        char sign =' ';
        String result= "";

        for (char c : charArray){
            if (c == '+' || c == '-'){
                sign = c;
            }else if (Character.isDigit(c)){
                result = result + c;
            }
        }
        int resultInt = Integer.parseInt(result);
        if (sign == '-'){
            resultInt = resultInt *(-1);
        }
        return resultInt;
    }
}
