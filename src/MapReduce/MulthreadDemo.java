package MapReduce;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MulthreadDemo {
    public static final int THREAD_NUM = 6;

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        long startTime = System.currentTimeMillis();
        MapperThread mapThread = new MapperThread(THREAD_NUM, "/Users/jocelyn/IdeaProjects/HTSC/src/MapReduce/hamlet.txt");
        ReducerThread reduceThread = new ReducerThread(THREAD_NUM);

        List<List> splitInputs = mapThread.loadFile();

        List<MapReduce.Tuple> mappingReduce = mapThread.mapThread(splitInputs);
        reduceThread.reduceThread(mappingReduce);
        long endTime = System.currentTimeMillis();
        System.out.println("多线程耗时: " + (endTime - startTime) +  "ms");
    }
}
