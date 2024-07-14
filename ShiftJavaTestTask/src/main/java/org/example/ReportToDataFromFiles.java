package org.example;

import java.util.Comparator;
import java.util.List;

public class ReportToDataFromFiles {

    public ReportToDataFromFiles () {
    }


    public void GetFullReport (List<List> listData, boolean appendStrings, boolean appendInteger, boolean appendFloat) {
        List <String> listStrings = listData.get(0);
        List<Integer> listIntegers = listData.get(1);
        List <Float> listFloats = listData.get(2);

        if (appendStrings) System.out.println("Count strings: " + listStrings.size());
        if (appendInteger) System.out.println("Count integers: " +  + listIntegers.size());
        if (appendFloat) System.out.println("Count floats: " +  + listFloats.size());

        if (!listIntegers.isEmpty() && appendInteger)  {
            System.out.println("----- Integers -----");
            System.out.println("Max integer: " + listIntegers.stream().max(Integer::compare).get());
            System.out.println("Min integer: " + listIntegers.stream().max(Integer::compare).get());
            System.out.println("Sum integers: " + listIntegers.stream().mapToInt(Integer::intValue).sum());
            System.out.println("Average integers " + listIntegers.stream().mapToInt(Integer::intValue).average().getAsDouble());
        }

        if (!listFloats.isEmpty() && appendFloat)  {
            System.out.println("----- Floats -----");
            System.out.println("Min float: " + listFloats.stream().max(Float::compare).get());
            System.out.println("Min float: " + listFloats.stream().min(Float::compare).get());
            System.out.println("Sum float: " + listFloats.stream().mapToDouble(Float::floatValue).sum());
            System.out.println("Average float: " + listFloats.stream().mapToDouble(Float::floatValue).average().getAsDouble());
        }

        if (!listStrings.isEmpty() && appendStrings)  {
            System.out.println("----- Strings -----");
            System.out.println("Length of long string: " + listStrings.stream().max(Comparator.comparingInt(String::length)).get().length());
            System.out.println("Length of short string: " + listStrings.stream().min(Comparator.comparingInt(String::length)).get().length());
        }

    }

    public void GetShortReport (List<List> listData, boolean appendStrings, boolean appendInteger, boolean appendFloat) {
        List <String> listStrings = listData.get(0);
        List <Integer>  listIntegers = listData.get(1);
        List <Float> listFloats = listData.get(2);

        if (appendStrings) System.out.println("Count strings: " + listStrings.size());
        if (appendInteger) System.out.println("Count integers: " +  + listIntegers.size());
        if (appendFloat) System.out.println("Count floats: " +  + listFloats.size());

    }


}
