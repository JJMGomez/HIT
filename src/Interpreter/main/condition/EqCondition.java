package Interpreter.main.condition;

public class EqCondition implements Condition {
    private Condition key;//左表达式
    private Condition value;//右表达式

    public EqCondition(Condition key, Condition value) {
        this.key = key;
        this.value = value;
    }

    public String getSqlDescription() {
        return key.getSqlDescription()+" = "+value.getSqlDescription();
    }
}
