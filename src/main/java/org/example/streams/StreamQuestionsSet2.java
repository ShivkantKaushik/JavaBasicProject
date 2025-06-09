package org.example.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamQuestionsSet2 {

    public static void main(String[] args) {


        ascendingNumberValue();
        reverseNumber();
        segregateOddEven();
        meanOfNumbers();
        convert2DArrayToFlatList();
        convert2DArrayToList();
        generateIntStreamOfPrimeNumberStream();



    }


    private static void ascendingNumberValue(){

        /*
         * Given a numeric array , re arrange the elements to form a smallest possible value.
         * Input:  {1, 34, 3, 98, 9, 76, 45, 4};
         * Output: 998764543431
         * Explanation: 9,98,76,45,4,34,3,1
         * */

        int[] arr = new int[]{1, 34, 3, 98, 9, 76, 45, 4};

        String smallestValue = Arrays.stream(arr).mapToObj(String::valueOf).sorted().collect(Collectors.joining());

    //    String highestValue = Arrays.stream(arr).mapToObj(String::valueOf).sorted(Comparator.reverseOrder()).collect(Collectors.joining());


        System.out.println("Smallest Value is " + smallestValue);

    }


    private static void reverseNumber(){

        int num = 1234;

        IntPredicate isNumGreaterThenZero = x -> x > 0;
        IntUnaryOperator divideByTen = x -> x/10;

        int reversedNum = IntStream.iterate(num, isNumGreaterThenZero, divideByTen ).map(y -> y%10).reduce(0, (a,b) -> a*10 + b );

        System.out.println("Reversed num:- "+ reversedNum);
    }


    private static void segregateOddEven(){

        int[] nums = new int[]{2,3,4,5,6,7,8,9};

        //flatmap will first apply provided function to each inner list of list
        //now function inside flatmap must always return stream instance
        //so each inner list will be converted to stream
        //then all inner stream elements will be collected in new stream and returned

        //also Collectors.partitioningBy will return hashmap with two keys
        //true and false, again true, one list will be mapped with filtered partitioned values
        //against false, another list with remaining values

        //so we will use .values function that will give values of hashmap, means collection with
        //two lists

       List<Integer> segregatedValues =  Arrays.stream(nums).boxed().collect(Collectors.partitioningBy(x -> x%2 == 0)).values()
                        .stream().flatMap(Collection::stream).toList();


        System.out.println("Segregated Values:- "+ segregatedValues);
    }


    private static void meanOfNumbers(){

        List<Integer> numbers = List.of(10,20,30,40);

        double mean = numbers.stream().mapToInt(x -> x).average().orElse(0);

        System.out.println("Mean:- "+ mean);
    }


    private static void convert2DArrayToFlatList(){

        int[][] x = new int[][]{{1,2},{3,4},{5,6}};

        List<Integer> flatList =  Arrays.stream(x).flatMapToInt(Arrays::stream).boxed().toList();

        System.out.println("FlatList:- " + flatList);
    }

    public static List<List<Integer>> convert2DArrayToList() {

        int[][] input = new int[][]{{1,2},{3,4},{5,6}};

        return Arrays.stream(input)
                .map(row -> Arrays.stream(row).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }



    public static void generateIntStreamOfPrimeNumberStream() {

        final int limit  = 5;

        IntPredicate isPrimePredicate = x ->
             IntStream.rangeClosed(2, (int)Math.sqrt(x)).noneMatch(y -> x%y == 0);

      List<Integer> primeNumbers = IntStream.rangeClosed(1,Integer.MAX_VALUE).filter(isPrimePredicate).limit(limit).boxed().toList();

        System.out.println("These are prime numbers: " + primeNumbers);

    }

}
