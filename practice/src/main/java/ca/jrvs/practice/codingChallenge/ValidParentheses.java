package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/***
 * Valid Parentheses
 * https://www.notion.so/jarvisdev/Valid-Parentheses-95e973baefd44124bdf0351ccd3525f0
 */
public class ValidParentheses {
    /**
     * There is a pattern, if {,(,[  exist than },),] must exist consecutively.
     *  f not that its false;
     * @param s
     * @return
     */
    public boolean isValid(String s){
        Map<Character,Character> pattern = new HashMap<>();
        pattern.put(')','(');
        pattern.put(']','[');
        pattern.put('}','{');

        List<Character> text = s.chars()
                .mapToObj(e->(char) e)
                .collect(Collectors.toList());

        Stack stack = new Stack();
        for (Character c : text){
            if (pattern.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() !=pattern.get(c)){
                    return false;
                }
            }else if (pattern.containsValue(c)) stack.push(c);
        }
        return stack.isEmpty();

    }

    public boolean isValidSimple (String s){
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
