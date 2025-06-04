package org.example.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class StreamQuestionsSet1 {

    public static void main(String[] args) {

        //https://github.com/ZahidFKhan/Streams-API-Practices/blob/main/src/test/java/com/github/streams/practice/numbers/problems/A_SumOfNumbers.java


        sumOfNumbers();
        sumOfUniqueNumbers();
        sumOfDigits();
        validNumbersOnly();


    }


    private static void sumOfNumbers(){

        int[] arr = new int[]{2,3,4,5,6};

        int sum =  Arrays.stream(arr).sum();

        System.out.println("Sum is :- " + sum);

    }


    private static void sumOfUniqueNumbers(){

        int[] arr = new int[]{3,3,4,5,5};

        int sum =  Arrays.stream(arr).distinct().sum();

        System.out.println("Distinct Sum is :- " + sum);

    }

    private static void sumOfDigits(){

        int num = 1234;

        IntPredicate intPredicate = x -> x > 0;
        IntUnaryOperator intUnaryOperator = x -> x/10;

        //num is seed
        //predicate is to terminate stream
        //unary operator is function to be applied to current element to generate next element of stream
        int sum = IntStream.iterate(num, intPredicate, intUnaryOperator).peek(a -> System.out.println(a)).map(b -> b%10).sum();

        System.out.println("Sum of Digits is:- "+ sum);

    }



    private static void validNumbersOnly(){

        String[] nums = new String[]{"123","42s","32","de12","987","21d"};


        //in mapMultiInt, mapMulti, mapMultiLong, we have one original stream element and one consumer
        //means mapMulti can be used as as filter
       List<Integer> validNums =  Arrays.stream(nums).mapMultiToInt( (num, streamConsumer) -> {
            try {
                int intValue = Integer.parseInt(num);
                streamConsumer.accept(intValue);
            }catch (Exception e){
                //ignore
            }
        } ).boxed().toList();

        System.out.println("Valid Numbers are:- "+ validNums);

    }


   




}
