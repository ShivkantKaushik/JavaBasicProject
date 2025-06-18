package org.example.streams;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamQuestionsSet4 {

    public static void main(String[] args) {

        findSecondHighestWord();
        findDuplicateStrings();
        findDuplicateStrings( Arrays.asList("sfd sds wwws sfd ssa gess a sds sa".split(" ")));
        convertListOfCharsToString();
        upperCaseOddLengthWords();

    }


    private static void findSecondHighestWord(){

        String sentence = "abc dsd c dsss ddsffs ssdf sersdfsg sdf";

        String word = Arrays.stream(sentence.split(" ")).sorted(Comparator.comparing(String::length, Comparator.reverseOrder())).skip(1).limit(1).findFirst().orElse("");

        System.out.println("Second Highest: " + word);

    }


    private static void findDuplicateStrings() {

        String sentence = "avc dfs xwe avc dsec ess dfs dsfe fdea ess";


        List<String> duplicateWords = Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting())).entrySet().stream().filter(entry -> entry.getValue() > 1).map(Map.Entry::getKey).toList();

        System.out.println("Duplicate words: " + duplicateWords);

    }


    //here in this, grouping by is using only one argument, so what it will do is
    //it will apply classifier(Function.identity()) function to incoming element,
    //then will check result, result will be key, and elements that produces same key
    //will be collected in a list and mapped against that key
    private static List<String> findDuplicateStrings(List<String> input) {
        return input.stream().collect(Collectors.groupingBy(Function.identity())).values().stream()
                .filter(strings -> strings.size() > 1)
                .map(strings -> strings.get(0))
                .collect(Collectors.toList());
    }


    private static void convertListOfCharsToString(){

        List<Character> charList = List.of('A','B','C','D','E');

       String word =  charList.stream().map(Object::toString).reduce("", String::concat);

        System.out.println("Word form chars: " + word);

    }

    private static void upperCaseOddLengthWords(){

        List<String> words = List.of("abc", "defg", "hijkl", "mno", "pq", "rstuvxy","z");

        List<String> upperCaseOddLenWords = words.stream().filter(word -> word.length() % 2 != 0).map(String::toUpperCase).toList();

        System.out.println("Upper case odd len words: " + upperCaseOddLenWords);

    }


}
