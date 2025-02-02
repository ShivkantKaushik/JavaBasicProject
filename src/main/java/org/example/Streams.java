package org.example;

import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {



    public static void main(String[] args) {

        Stream<Integer> integerStream = Stream.of(1,2,5,3);
        IntStream intStream = Arrays.stream(new int[]{1,2,5,3});

        Stream<Integer> streamFromCollection = List.of(1,2,3).stream();
        Stream<Integer> parallelStreamFromCollection = List.of(1,2,4).parallelStream();
        Stream<Integer> parallelStreamFromStream = streamFromCollection.parallel();

        //Functions

        Function<Integer,Integer> sqrtFunction = x -> (int) Math.pow(x,2);

        System.out.println("This is how function works " + sqrtFunction.apply(5));

        //Map
        List<Integer> sqrtOfNumbers = integerStream.map(sqrtFunction).toList();

        System.out.println("Sqrt Numbers " + sqrtOfNumbers);

        Predicate<Integer> isNumAbove2 = x -> x > 2;

        System.out.println("This is how predicate works " + isNumAbove2.test(3));

        Stream<Integer> integerStream2 = Stream.of(1,2,5,3);


        //Filter
        List<Integer> numsAbove2 = integerStream2.filter(isNumAbove2).toList();

        System.out.println("Nums above 2 "+ numsAbove2);

        Stream<Integer> streamForReduce = Stream.of(1,2,5,3);

        BinaryOperator<Integer> binaryOperator = Integer::sum;
//        BinaryOperator<Integer> binaryOperator = (x,y) -> x+y;
// Binary Operator is nothing, but a specific use case of bi function, where all input and output, all three
        // are of same type

        //Reduce
        Integer sumOfNums = streamForReduce.reduce(0,binaryOperator);

        System.out.println("This is sumOfNums " + sumOfNums);

        Queue<Integer> queue = new LinkedList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt((Integer x) -> x));

        Stack<Integer> stack = new Stack<>();

        LinkedList<Integer> linkedList = new LinkedList<>();

        Set<Integer> set = new HashSet<>();

        List<Map.Entry<Integer,Integer>> list = new ArrayList<>();

        list.add(new AbstractMap.SimpleEntry<>(1,2) );



        String[] x = new String[]{"ba","azc","ab","abc"};

        Arrays.sort(x);

        System.out.println(Arrays.toString(x));


        String json = "{\"name\": \"Bob\", \"age\": \"30\", \"city\": \"New York\"}";

        HashMap<String,String> jsonMap = parseJson(json);

        System.out.println("This is json map " + jsonMap.values());


        String s1 = "abc";
        String s2 = "zbc";

        System.out.println("Compare Strings:- " + s1.compareTo(s2));



        Stream<String> stream1 = Stream.of("abc", "abc", "sdfc", "vcd");
        //foreach --> takes consumer
        //limit, distinct

        Consumer<String> consumer = x1 -> System.out.println(x1);

        //How consumer works
        consumer.accept("This is consumer");


        stream1.distinct().limit(2).forEach(System.out::println);

        Stream<Integer> stream2 = Stream.of(1,3,4,3,5,2,7,6);

        long count = stream2.sorted().count();

        System.out.println("Count: " + count);

        Stream<Integer> stream3 = Stream.of(1,3,4,3,5,2,7,6);


        //How supplier works

        Supplier<String> supplier = () -> "This is supplier";

        System.out.println(supplier.get());

        int minimum = stream3.takeWhile( ax -> ax != 2 ).peek(System.out::print).min(Comparator.comparingInt((j) -> j)).orElse(0);
        //similarly dropWhile is there

        // we can do limit and offset functionality with skip and limt

        System.out.println("Minimum: " + minimum);


        String x1 = "abcdef";

        HashMap<Character, Integer> charFreq = new HashMap<>();

      x1.chars().boxed().map((a) -> {
           System.out.println("vde");
            charFreq.put( (char)(int)a , charFreq.getOrDefault((char)(int)a,0) + 1 );
            return 7;
        }).forEach(System.out::println);

        System.out.println("This is freq "  + charFreq);


        Stream<Integer> findStream = Stream.of(1,2,3);


        //https://medium.com/@mehar.chand.cloud/java-stream-coding-interview-questions-part-1-dc39e3575727
    }


    public static HashMap<String, String> parseJson(String s){

        HashMap<String, String> map = new HashMap<>();

        Stack<String> stack = new Stack<>();

        String key = "";
        String value = "";

        for(int i = 0; i < s.length(); i++){

            if(s.charAt(i) == '{'){
                stack.push("{");
            }else if(s.charAt(i) == '\"'){
                if ( "\"".equals(stack.peek()) ){
                    //means closing quote

                    stack.pop();
                    int indexOfStartingQuote = Integer.parseInt(stack.pop());

                    String isColon = stack.peek();

                    if(":".equals(isColon)){
                        //its value
                        stack.pop();
                        value = s.substring(indexOfStartingQuote+1,i);
                        map.put(key,value);
                    }else{
                        //its key
                        key = s.substring(indexOfStartingQuote+1,i);
                    }
                }

                else{
                    //means starting quote, so add index first, then this quote
                    stack.push(String.valueOf(i));
                    stack.push("\"");
                }
            }else if (s.charAt(i) == ':'){
                stack.push(":");
            }else if (s.charAt(i) == '}'){
                stack.pop();
            }

        }

        if(stack.isEmpty()){
            //json is valid
            return map;
        }
        return new HashMap<String, String>();
    }



}
