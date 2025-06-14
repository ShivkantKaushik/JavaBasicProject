package org.example.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamQuestionsSet3 {

    public static void main(String[] args) {

        getMatchesVowels();
        getHighestLengthWord();
        getCityNamesLenStartingWithM();
        countOccurencesOfEachCharacter();
        removeDuplicateCharacters();
        reverseStringWithoutCombiner();

    }



    private static void getMatchesVowels(){
        int length = 2; //words with length number of vowels
        String sentence = "The quick brown fox little";

        List<String> words = Arrays.stream(sentence.split(" ")).filter(word -> word.replaceAll("[aeiouAEIOU]","").length() == word.length() - length ).toList();

        System.out.println("Words with " + length + " vowels: " + words);

    }

    private static void getHighestLengthWord(){

        String sentence = "The quick brown fox littlebittle dob";

        String word = Arrays.stream(sentence.split(" ")).max(Comparator.comparingInt(String::length)).orElse("");

        System.out.println("Word with max length: " + word);

    }

    private static void getCityNamesLenStartingWithM(){
        List<String> cities = List.of("Mumbai", "Delhi", "Bhiwani", "Mahendargarh", "Panchkula");

        //Collectors.toMap function expects two functions for key and value, that it will apply to incoming input
        Map<String,Integer> cityLenMap = cities.stream().filter(city -> "m".equalsIgnoreCase(city.substring(0,1)) ).collect(Collectors.toMap(Function.identity(),String::length));

        System.out.println("City Length Map: "+ cityLenMap);
    }

    private static void countOccurencesOfEachCharacter(){

        String sentence = "Abc ded frw dfe fsdw";

        // in grouping by function, first parameter is function, to get key with we have to group
        //second parameter is map, where we have to put result after grouping
        //third parameter is collector, that will perform operation on elements under key and give one result
        Map<Character,Long> charFrequencyMap = sentence.chars().mapToObj(x -> (char) x).filter(x -> !Character.isSpaceChar(x)).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        System.out.println("Char Frequency Map: " + charFrequencyMap);

        //we also have Collectors.joining(",") that works like String.join(",", list);
    }


    private static void removeDuplicateCharacters(){

        String sentence = "Abc ded frw dfe fsdw";

        List<Character> uniqueChars = sentence.chars().distinct().mapToObj(x -> (char)x).toList();

        System.out.println("Unique chars: " + uniqueChars);

    }



    private static String reverseStringWithoutCombiner() {

        String input = "abcdef";

        return input.chars()
                .mapToObj(c -> String.valueOf((char) c))  // Convert to String
                .reduce("", (a, b) -> b + a);  // Reverse order
    }



}
