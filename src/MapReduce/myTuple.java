package MapReduce;

public class myTuple {
    public  String key;
    public  Integer value;
    public myTuple(String key, int value){
        this.key = key;
        this.value = value;
    }

    public void printTuple(){
        System.out.println(this.key + ": " + this.value);
    }
}
