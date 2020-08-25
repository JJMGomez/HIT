package MapReduce;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MapReduce_multiThread {

    public static final int MAPPER_NUM = 6;
    int num =0;
    public static List<myTuple> result;

    public void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        num = 1;
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
            num++;
//            System.out.println("打印一行"+line);
        }
        reader.close();
        is.close();
    }

    /**
     * 读文件
     * */
    public String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString().toLowerCase().replaceAll("[\\pP\\p{Punct}]"," ");
    }

    /**
     * 1. 句子划分
     * */
    public String[] splitString(String filepath, int left, int right) throws IOException {
        String[] split_s = readFile(filepath).split("\n");
        String[] split_ss = new String[right-left];
        System.arraycopy(split_s,left,split_ss,0,right-left);
        return split_ss;
    }

    /**
     * 分词，大小写处理，标点处理
     * */
    public List<String> splitting(String[] input){
        ArrayList<String> wordList = new ArrayList<>();
        for (String s: input) {
            String[] tmpList = s.toLowerCase().replaceAll("[\\pP\\p{Punct}]", " ").split(" ");
            wordList.addAll(Arrays.asList(tmpList));
        }
        return wordList;
    }

    /**
     * List(K,V)
     * 形如：Car:1  Car:1  River:1
     * */
    public List<myTuple> mapping(List<String> input){
        List<myTuple> list = new ArrayList<>();
        for (int i=0; i<input.size(); i++){
            myTuple tmp = new myTuple(input.get(i),1);
            list.add(tmp);
        }
        return list;
    }

    /**
     * HashMap <K,List>
     * 形如：Car:{1,1}
     * */
    public HashMap<String, List<Integer>> shuffling(List<myTuple> list){
        HashMap<String, List<Integer>> map = new HashMap<>();
        for (int i=0; i<list.size(); i++){
            add(map,(String)list.get(i).key);
        }
        return map;
    }

    public void add(HashMap<String, List<Integer>> map, String s){
        if (map.containsKey(s)){
            map.get(s).add(1);
        }
        else{
            List<Integer> tmp = new ArrayList<>();
            tmp.add(1);
            map.put(s,tmp);
        }
    }

    /**
     * List <K,V>
     * 形如：Car:2
     * */
    public List<myTuple> reducing(HashMap<String, List<Integer>> map){
        List<myTuple> list = new ArrayList<>();
        for (String s:map.keySet()){
            int count = map.get(s).size();
            myTuple tmp = new myTuple(s,count);
            list.add(tmp);
        }

        return list;
    }

    public List<myTuple> mapReduce(String filepath, int left, int right) throws IOException {
        return reducing(shuffling(mapping(splitting(splitString(filepath, left, right)))));
    }

//    public void initJob(String filename) {
//        int split = num/MAPPER_NUM;
//        for (int i = 0; i < MAPPER_NUM; i++) {
//            Thread t = new Thread(new MyThread("thread"+i, filename, split*i, split*(i+1),result));
//            t.start();
//        }
//    }


    public static void main(String[] args) throws IOException {

        String filename = "/Users/jocelyn/Exercise/Offer/src/HTSC/hamlet.txt";
        long startTime = System.currentTimeMillis();
//        MapReduce_multiThread mapReduce = new MapReduce_multiThread();
        // 线程初始化并等待阻塞队列输入
//        mapReduce.initJob(filename);

        long endTime = System.currentTimeMillis();
        System.out.println("use time: " + (endTime - startTime) +  "ms");

//        long starttime= System.currentTimeMillis();
        MapReduce_multiThread mapReduce = new MapReduce_multiThread();
        List<myTuple> result = mapReduce.mapReduce("/Users/jocelyn/Exercise/Offer/src/HTSC/hamlet.txt",0,2);


//        String[] strings = mapReduce.splitting(input);
//        List<Tuple> list = mapReduce.mapping(strings);
//        HashMap<String, List<Integer>> map = mapReduce.shuffling(list);
//        List<Tuple> result = mapReduce.reducing(map);

        for (int i=0; i<result.size(); i++){
            result.get(i).printTuple();
        }
        long endtime = System.currentTimeMillis();
//        System.out.println(endtime-starttime);
    }
}
