package Interpreter.main.condition;

public class ContextCondition implements Condition {
    //定义上下文
    private String context;

    public ContextCondition(String context) {
        this.context = context;
    }

    public String getSqlDescription() {
        return context;
    }
}
