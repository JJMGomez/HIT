package MapReduce;



import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReducerThread {
    private int threadNum;
    private MapReduce mapReduce;


    public ReducerThread(int threadNum) {
        this.threadNum = threadNum;
        this.mapReduce = new MapReduce();

    }


    public void reduceThread(List<MapReduce.Tuple> mappingResult) throws ExecutionException, InterruptedException {

        Map<String, ArrayList<Integer>> shuffleMap = mapReduce.shuffling(mappingResult);
        ExecutorService pool = Executors.newFixedThreadPool(100);
        List<MapReduce.Tuple> finalResult = Collections.synchronizedList(new ArrayList<>());
        for (String skey : shuffleMap.keySet()) {
            final String s = skey;
            pool.execute(() -> {
                try {
                    HashMap<String, List<Integer>> temp;
                    temp = new HashMap<>();
                    temp.put(s,  shuffleMap.get(s));
                    List<MapReduce.Tuple> reduceList = mapReduce.reducing(temp);
                    finalResult.addAll(reduceList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }
        pool.shutdown();
        while (true){
            if(pool.isTerminated()){
                mapReduce.printResult(finalResult);
                break;
            }
        }

    }

}
