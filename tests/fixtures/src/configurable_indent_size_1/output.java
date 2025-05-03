package com.typeshare.java;

public class Namespace {

    public record CustomType() {}

    public record Types(
        String s,
        String static_s,
        Byte int8,
        Float _float,
        Double _double,
        java.util.ArrayList<String> array,
        String[] fixed_length_array,
        java.util.HashMap<String, Integer> dictionary,
        java.util.HashMap<String, Integer> optional_dictionary,
        CustomType custom_type
    ) {}

}
