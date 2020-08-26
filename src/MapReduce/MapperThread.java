package MapReduce;



import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapperThread {
    private int threadNum;
    private String fileName;
    private MapReduce mapReduce;


    public MapperThread(int threadNum, String fileName) {
        this.threadNum = threadNum;
        this.fileName = fileName;
        this.mapReduce = new MapReduce();

    }

    public List<List> loadFile() throws IOException {
        String input = mapReduce.readFile(fileName);
        List<List> splitInputs = mapReduce.splitting(threadNum, input);
        return splitInputs;
    }

    public List<MapReduce.Tuple> mapThread(List<List> splitInputs) throws ExecutionException, InterruptedException {
        // 线程池
        List<MapReduce.Tuple> tupleList = Collections.synchronizedList(new ArrayList<>());
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);

        for (int i = 0; i < threadNum; i++) {

            int finalI = i;
            pool.execute(() -> {
                try {
                    List<MapReduce.Tuple> mappingTuples = mapReduce.mapping(splitInputs.get(finalI));
                    tupleList.addAll(mappingTuples);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();

        List<MapReduce.Tuple> mappingResult;
        while (true) {
            if (pool.isTerminated()) {
                mappingResult = tupleList;
                break;
            }
        }
        return mappingResult;
    }

}
