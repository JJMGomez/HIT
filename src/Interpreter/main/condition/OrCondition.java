package Interpreter.main.condition;

public class OrCondition implements Condition {
    private Condition leftExp;//左表达式
    private Condition rightExp;//右表达式

    public OrCondition(Condition leftExp, Condition rightExp) {
        this.leftExp = leftExp;
        this.rightExp = rightExp;
    }

    public String getSqlDescription() {
        return "(" + leftExp.getSqlDescription() + ") or (" + rightExp.getSqlDescription() + ")";
    }
}
