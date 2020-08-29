package Filter.main;

public abstract class Condition {
    String expression;
    public String getExpression(){
        return this.expression;
    }
    public String and(Condition other){
        return this.expression + " AND " + other.getExpression();
    }

    public String or(Condition other){
        return this.expression + " OR " + other.getExpression();
    }

}
