package roo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class PrintAlternateNode {

    public List<Object> printAlternateNode(List<Object> inputList) {
        List<Object> flattenedList = new ArrayList<>();
        if (inputList != null || !inputList.isEmpty()) {
            flattenList(inputList, flattenedList);
        }
        var outputList = new ArrayList<>();
        for (int i = 0; i < flattenedList.size(); i += 2) {
            outputList.add(flattenedList.get(i));
        }
        return outputList;
    }

    public void flattenList(Object input, List<Object> output) {
        if (input == null) return; // exit recursion
        switch (input) {
            case List<?> subList -> {
                for (var nestedItem : subList) {
                    flattenList(nestedItem, output);
                }
            }
            default -> output.add(extractLeafNode(input));
        }
    }

    public Object extractLeafNode(Object item) {
        if (item instanceof String curr) {
            if (curr.matches("-?//d")) {
                return Integer.parseInt(curr);
            } else {
                return curr.toUpperCase(Locale.ROOT);
            }
        }
        return item;
    }

    public static void main(String[] args){
        // Sample [1, "hello" ,["4", "world", "abc"]], [[[-1]]], "foo", [["xyz", "bar"]]]
        List<Object> input = List.of(
                1,
                List.of("hello", List.of("4", "world", "abc" )),
                List.of(List.of(-1)),
                "foo",
                List.of(List.of("xyz", "bar")),
                10,
                List.of(-10)
        );
        PrintAlternateNode print = new PrintAlternateNode();
        var output = print.printAlternateNode(input);
        System.out.println(output);
    }
}
