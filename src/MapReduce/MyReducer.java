package MapReduce;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class MyReducer implements Runnable{

    BlockingQueue<List<myTuple<String, List<Long>>>> inputs;
    List<myTuple<String, Long>> collector;
    CountDownLatch latch;

    public MyReducer(BlockingQueue<List<myTuple<String, List<Long>>>> tuples, List<myTuple<String, Long>> collector, CountDownLatch latch) {
        this.inputs = tuples;
        this.collector = collector;
        this.latch = latch;
    }


    @Override
    public void run() {
        try {
            List<myTuple<String, List<Long>>> inputList = inputs.take();
            inputList.forEach(i -> reduce(i, collector));
        } catch (InterruptedException ignored) {

        } finally {
            latch.countDown();

        }
    }

    public void reduce(myTuple<String, List<Long>> value, List<myTuple<String, Long>> output) {
        long sum = value.getB().stream()
                .reduce(Long::sum)
                .get();
        myTuple<String, Long> r = new myTuple<>(value.getA(), sum);
        output.add(r);
    }
}
