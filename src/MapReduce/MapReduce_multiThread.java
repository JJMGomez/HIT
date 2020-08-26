package MapReduce;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class MapReduce_multiThread {

    public static final int MAPPER_NUM = 6;
    public static final int REDUCER_NUM = 6;

    CountDownLatch mapJobLatch = new CountDownLatch(MAPPER_NUM);
    CountDownLatch reduceJobLatch = new CountDownLatch(REDUCER_NUM);

    BlockingQueue<String> fileSplits = new LinkedBlockingQueue<>();
    List<myTuple<String, Long>> spills = Collections.synchronizedList(new ArrayList<>());
    Map<String, myTuple<String, List<Long>>> merge = new HashMap<>();
    BlockingQueue<List<myTuple<String, List<Long>>>> shuffles = new LinkedBlockingQueue<>();
    List<myTuple<String, Long>> results = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        MapReduce_multiThread mapReduce = new MapReduce_multiThread();
        // 线程初始化并等待阻塞队列输入
        mapReduce.initMapJob();
        mapReduce.initReduceJob();
        mapReduce.start();
        mapReduce.print();
        long endTime = System.currentTimeMillis();
        System.out.println("多线程总耗时: " + (endTime - startTime) +  "ms");
    }

    public void splitter() {
        try {
            String path = "/Users/jocelyn/Exercise/Offer/src/HTSC/MapReduce/hamlet.txt";
            File file = new File(path);
            FileReader fr = new FileReader(file);
            LineNumberReader reader = new LineNumberReader(fr);
            reader.skip(Long.MAX_VALUE);
            int lines = reader.getLineNumber();
            reader.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            StringBuilder sBuilder = new StringBuilder();
            String line;
            int count = 0;
            while((line = br.readLine()) != null) {
                line = line.toLowerCase().replaceAll("[\\pP\\p{Punct}]"," ");
                sBuilder.append(line).append(" ");
                count++;
                if (count == lines / MAPPER_NUM + 1) {
                    fileSplits.put(sBuilder.toString());
                    count = 0;
                    sBuilder = new StringBuilder();
                }
            }
            if (count != 0) {
                fileSplits.put(sBuilder.toString());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initMapJob() {
        for (int i = 0; i < MAPPER_NUM; i++) {
            Thread t = new Thread(new MyMapper(fileSplits, spills, mapJobLatch));
            t.start();
        }
    }

    public void initReduceJob() {
        for (int i = 0; i < REDUCER_NUM; i++) {
            Thread t = new Thread(new MyReducer(shuffles, results, reduceJobLatch));
            t.start();
        }
    }

    public void start() throws InterruptedException {
        splitter();
        mapJobLatch.await();
        shuffle();
        reduceJobLatch.await();
    }

    public void shuffle() {
        spills.forEach(s -> {
            if (merge.containsKey(s.getA())) {
                myTuple<String, List<Long>> m = merge.get(s.getA());
                shuffle(s, m);
            } else {
                myTuple<String, List<Long>> m = new myTuple<>(s.getA(), new ArrayList<>());
                shuffle(s, m);
                merge.put(s.getA(), m);
            }
        });
        Map<Integer, List<myTuple<String, List<Long>>>> groups = merge.values()
                .stream()
                .collect(Collectors.groupingBy(t -> Math.abs(t.getA().hashCode()) % REDUCER_NUM));
        groups.values().forEach(e -> {
            try {
                shuffles.put(e);
            } catch (InterruptedException ignored) {

            }
        });
    }

    public void print() {
        results.forEach(r -> System.out.println(r.getA() + " : " + r.getB()));
//        System.out.println("result size: " + results.size());
    }

    public static void shuffle(myTuple<String, Long> input, myTuple<String, List<Long>> collector) {
        if (input.getA().equals(collector.getA())) {
            collector.getB().add(input.getB());
        }
    }

}
