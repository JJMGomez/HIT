package MapReduce;

import java.io.IOException;
import java.util.List;

public class MyThread implements Runnable {
    private String name;
    private int left;
    private int right;
    private String filename;
    private List<myTuple> result;


    public MyThread(String name,String filename, int left, int right, List<myTuple> result){
        this.name = name;
        this.left = left;
        this.right = right;
        this.filename = filename;
        this.result = result;
    }

    public List<myTuple> mapReduceThread(String filename, int left, int right) throws IOException {
        MapReduce_multiThread  mapReduce_multiThread = new MapReduce_multiThread();
        return mapReduce_multiThread.mapReduce(filename,left,right);
    }

    public void print(){
        for (myTuple tuple: result){
            tuple.printTuple();
        }
    }

    @Override
    public void run() {
        try {
            List<myTuple> tmp_result = mapReduceThread(filename, left, right);
            for (myTuple tuple : tmp_result) {
                if (result.contains(tuple.key)){
                    result.get(result.indexOf(tuple.key)).value += tuple.value;
                }
                else{
                    result.add(tuple);
                }

            }
            for (int i=0; i<result.size(); i++){
                result.get(i).printTuple();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
