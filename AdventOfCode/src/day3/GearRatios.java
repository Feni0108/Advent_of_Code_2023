package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios {

    public static void main(String[] args) {

        System.out.println(getEngineSchematic(readFile("input_day3.txt")));
    }

    public static List<String> readFile(String input) {
        List<String> calibrationValues = new ArrayList<>();
        try {
            calibrationValues = Files.readAllLines(Paths.get(input));
        } catch (IOException e) {
            System.err.println("Can not read file");
        }
        return calibrationValues;
    }

    public static List<Integer> getSymbolIDs(String lines) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < lines.length(); i++) {
            if (!Character.isDigit(lines.charAt(i))
                    && !Character.isLetter(lines.charAt(i))
                    && !Character.isWhitespace(lines.charAt(i))
                    && lines.charAt(i) != '.') {
                ids.add(i);
            }
        }
        return ids;
    }

    public static List<List<Integer>> getNumbersData(String line) {
        List<List<Integer>> allNumbers = new ArrayList<>();

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            List<Integer> data = new ArrayList<>();
            data.add(matcher.start());
            data.add(matcher.end());
            data.add(Integer.parseInt(matcher.group()));
            allNumbers.add(data);
        }

        return allNumbers;
    }

    public static List<Integer> getGoodNumbersFromNextLine(String first, String middle, String next) {
        List<Integer> goodNumbers = new ArrayList<>();

        List<List<Integer>> numberDataFromFirstLine = getNumbersData(middle);
        List<Integer> idsOfSymbolsFromFirstLine = getSymbolIDs(first);
        List<Integer> idsOfSymbolsFromSecondLine = getSymbolIDs(middle);
        List<Integer> idsOfSymbolsFromThirdLine = getSymbolIDs(next);
        List<Integer> allIDs = new ArrayList<>();
        allIDs.addAll(idsOfSymbolsFromFirstLine);
        allIDs.addAll(idsOfSymbolsFromSecondLine);
        allIDs.addAll(idsOfSymbolsFromThirdLine);

        for (int i = 0; i < allIDs.size(); i++) {
            for (int j = 0; j < numberDataFromFirstLine.size(); j++) {
                if (allIDs.get(i) >= (numberDataFromFirstLine.get(j).get(0) - 1)  && allIDs.get(i) <= (numberDataFromFirstLine.get(j).get(1))) {
                    goodNumbers.add(numberDataFromFirstLine.get(j).get(2));
                }
            }
        }

        return goodNumbers;
    }

    public static int getEngineSchematic(List<String> input) {
        int sum = 0;

        List<Integer> firstLineNumbers = getGoodNumbersFromNextLine(input.get(0), input.get(0), input.get(1));
        List<Integer> lastLineNumbers = getGoodNumbersFromNextLine(input.get(input.size() - 2), input.get(input.size() - 1), input.get(input.size() - 1));

        List<Integer> firstAndLast = new ArrayList<>();
        firstAndLast.addAll(firstLineNumbers);
        firstAndLast.addAll(lastLineNumbers);


        sum += firstAndLast.stream()
                .mapToInt(Integer::intValue)
                .sum();

        for (int i = 1; i < input.size() - 1; i++) {
            List<Integer> numbersInARow = getGoodNumbersFromNextLine(input.get(i - 1), input.get(i), input.get(i + 1));
            sum += numbersInARow.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        return sum;
    }
}
