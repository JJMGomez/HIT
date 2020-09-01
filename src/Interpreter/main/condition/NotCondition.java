package Interpreter.main.condition;

public class NotCondition implements Condition{
    private Condition expression;

    public NotCondition(Condition expression) {
        this.expression = expression;
    }

    @Override
    public String getSqlDescription() {
        return "not "+ expression.getSqlDescription();
    }
}
