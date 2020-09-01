package Interpreter.main;

import Interpreter.main.condition.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GetSql {

    public static String parseAST(String description) {
        Stack<Character> symbol = new Stack<>();
        Stack<String> element = new Stack<>();
        description = description.toLowerCase();

        List<String> res = new LinkedList<>();

        String subExpression = "";
        for (int i = 0;i<description.length();i++) {
            if(description.charAt(i) == '(') {
                symbol.push(description.charAt(i));
                if (!subExpression.equals("")) {
                    element.push(subExpression);
                    subExpression = "";
                }
            } else if(description.charAt(i) == ')') {
                if (!subExpression.equals("")) {
                    element.push(subExpression);
                    subExpression = "";
                }
                symbol.pop();
                String popValue = element.pop();
                check(popValue, res);
            } else {
                subExpression = subExpression + description.charAt(i);
            }
        }

        if (res.size() == 0 && !subExpression.equals(""))
            return addSelect(subExpression);

        if (!symbol.isEmpty())
            throw new IllegalArgumentException();

        while (!element.isEmpty()) {
            check(element.pop(), res);
        }
        return addSelect(res.get(0));
    }

    private static void check(String popValue, List<String> res) {
        if (popValue.replace(" ","").equals("and")){
            Condition expLeft = new ContextCondition(res.get(res.size()-2));
            Condition expRight = new ContextCondition(res.get(res.size()-1));
            Condition and = new AndCondition(expLeft,expRight);
            res.remove(res.size()-2);
            res.remove(res.size()-1);
            res.add(and.getSqlDescription());
        } else if (popValue.replace(" ","").equals("or")){
            Condition expLeft = new ContextCondition(res.get(res.size()-2));
            Condition expRight = new ContextCondition(res.get(res.size()-1));
            Condition or = new OrCondition(expLeft,expRight);
            res.remove(res.size()-2);
            res.remove(res.size()-1);
            res.add(or.getSqlDescription());
        } else if (popValue.replace(" ","").equals("!")){
            Condition condition = new ContextCondition(res.get(res.size()-1));
            Condition not = new NotCondition(condition);
            res.remove(res.size()-1);
            res.add(not.getSqlDescription());
        }else {
            res.add(popValue);
        }

    }

    private static String addSelect(String res) {
        return String.format("select * from table where %s",res);
    }

}
