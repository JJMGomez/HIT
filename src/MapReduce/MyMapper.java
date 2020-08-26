package MapReduce;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyMapper implements Runnable {

    BlockingQueue<String> inputs;
    List<myTuple<String, Long>> collector;
    CountDownLatch latch;

    public MyMapper(BlockingQueue<String> strings, List<myTuple<String, Long>> collector, CountDownLatch latch) {
        this.inputs = strings;
        this.collector = collector;
        this.latch = latch;
    }


    @Override
    public void run() {
        try {
            String input = inputs.take();
            map(input, collector);
        } catch (InterruptedException ignored) {

        } finally {
            latch.countDown();
        }

    }

    public void map(String text, List<myTuple<String, Long>> collector) {
        collector.addAll(Stream.of(text.split(" "))
                .filter(s -> s != null && s.length() > 0)
                .map(i -> new myTuple<>(i, 1L))
                .collect(Collectors.toList()));
    }
}
