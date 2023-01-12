package ca.jrvs.practice.codingChallenge;

public class RotateString {

    public boolean rotateString(String s, String goal) {

        for (char c : s.toCharArray()){
            String firstLetter = s.substring(0,1);
            String restLetter = s.substring(1,s.length());
            s = restLetter + firstLetter;
            if (s.equals(goal)){
                return true;
            }
        }
        return false;
    }
    public boolean rotateStringSimple(String a, String b){
        return a.length() == b.length() && (a+a).contains(b);
    }
}
