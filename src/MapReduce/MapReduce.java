package MapReduce;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 单线程版*/
public class MapReduce {
    public class Tuple<String, Integer>{
        public final String key;
        public final Integer value;

        public Tuple(String key, Integer value){
            this.key = key;
            this.value = value;
        }
        public void printTuple(){
            System.out.println(key + ": " + value);
        }
    }

    public void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
//        File filePath = new File("/Users/jocelyn/Exercise/Offer/src/HTSC/hamlet.txt");

        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append(" "); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    /**
     * 1. 大写字母替换
     * 2. 标点替换
     * */
    public String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString().toLowerCase().replaceAll("[\\pP\\p{Punct}]"," ");
    }

    public String[] splitting(String input){
        String[] wordList = input.split(" ");
        return wordList;
    }

    /**
     * List(K,V)
     * 形如：Car:1  Car:1  River:1
     * */
    public List<Tuple> mapping(String[] input){
        List<Tuple> list = new ArrayList<>();
        for (int i=0; i<input.length; i++){
            Tuple tmp = new Tuple(input[i],1);
            list.add(tmp);
        }
        return list;
    }

    /**
     * HashMap <K,List>
     * 形如：Car:{1,1}
     * */
    public HashMap<String, List<Integer>> shuffling(List<Tuple> list){
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
    public List<Tuple> reducing(HashMap<String, List<Integer>> map){
        List<Tuple> list = new ArrayList<>();
        for (String s:map.keySet()){
            int count = map.get(s).size();
            Tuple tmp = new Tuple(s,count);
            list.add(tmp);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        long starttime= System.currentTimeMillis();
        MapReduce mapReduce = new MapReduce();
        String input = mapReduce.readFile("/Users/jocelyn/Exercise/Offer/src/HTSC/hamlet.txt");
        String[] strings = mapReduce.splitting(input);
        List<Tuple> list = mapReduce.mapping(strings);
        HashMap<String, List<Integer>> map = mapReduce.shuffling(list);
        List<Tuple> result = mapReduce.reducing(map);

        for (int i=0; i<result.size(); i++){
            result.get(i).printTuple();
        }
        long endtime = System.currentTimeMillis();
        System.out.println(endtime-starttime);
    }
}
