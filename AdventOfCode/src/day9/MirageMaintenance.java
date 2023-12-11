package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MirageMaintenance {

    public static void main(String[] args) {
        System.out.println(solve("input_day9.txt"));
    }

    public static long solve(String input) {
        long sum = 0L;

        try {
            File file = new File(input);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //sum += getTheLastNumber(line);
                sum += getTheFirstNumber(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
            e.printStackTrace();
        }

        return sum;
    }

    public static int getTheLastNumber(String line) {
        String[] numbers = line.split("\\s");
        List<Integer> realNumbers = convertStringArrayToIntegerList(numbers);

        List<Integer> lastNumbers = new ArrayList<>();
        lastNumbers.add(realNumbers.get(realNumbers.size() - 1));

        while (!areNUmbersAllZeros(realNumbers)) {
            List<Integer> newNumbers = getDifferences(realNumbers);
            lastNumbers.add(newNumbers.get(newNumbers.size() - 1));
            realNumbers.clear();
            realNumbers.addAll(newNumbers);
        }

        return getSumOfLastNumbers(lastNumbers);
    }

    public static int getTheFirstNumber(String line) {
        String[] numbers = line.split("\\s");
        List<Integer> realNumbers = convertStringArrayToIntegerList(numbers);
        List<Integer> copy = new ArrayList<>();
        copy.addAll(realNumbers);

        List<Integer> firstNumbers = new ArrayList<>();
        List<List<Integer>> allLines = new ArrayList<>();
        allLines.add(copy);

        while (!areNUmbersAllZeros(realNumbers)) {
            List<Integer> newNumbers = getDifferences(realNumbers);
            allLines.add(newNumbers);
            realNumbers.clear();
            realNumbers.addAll(newNumbers);
        }

        return calculateBeforeFirstNumber(allLines);
    }

    public static int calculateBeforeFirstNumber(List<List<Integer>> allLines) {
        List<Integer> justFirstNumbersFromEveryLine = new ArrayList<>();

        for (List<Integer> list : allLines) {
            justFirstNumbersFromEveryLine.add(list.get(0));
        }

        justFirstNumbersFromEveryLine.add(0);
        int toReduceWith = justFirstNumbersFromEveryLine.get(justFirstNumbersFromEveryLine.size() - 2) - justFirstNumbersFromEveryLine.get(justFirstNumbersFromEveryLine.size() - 1);

        for (int i = justFirstNumbersFromEveryLine.size() - 3; i > -1; i--) {
            toReduceWith = justFirstNumbersFromEveryLine.get(i) - toReduceWith;
        }

        return toReduceWith;
    }

    public static List<Integer> convertStringArrayToIntegerList(String[] numbers) {
        List<Integer> intNumbers = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            intNumbers.add(Integer.parseInt(numbers[i]));
        }

        return intNumbers;
    }

    public static List<Integer> getDifferences(List<Integer> originalNumbers) {
        List<Integer> differences = new ArrayList<>();

        for (int i = 1; i < originalNumbers.size(); i++) {
            differences.add(originalNumbers.get(i) - originalNumbers.get(i - 1));
        }

        return differences;
    }

    public static boolean areNUmbersAllZeros(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count() <= 1;
    }

    public static int getSumOfLastNumbers(List<Integer> lastNumbers) {
        return lastNumbers.stream()
                .reduce(0, Integer::sum);
    }
}
