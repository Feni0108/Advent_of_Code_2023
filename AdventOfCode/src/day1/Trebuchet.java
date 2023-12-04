package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Trebuchet {

    public static void main(String[] args) {
        String input = "input.txt";
        List<String> content = readFile(input);
        System.out.println(content);
        System.out.println(getSum(getRealNumbers(getNumbers(content))));
        changeWrittenNumbersToDigits(content);
        System.out.println(content);
        System.out.println(getSum(getRealNumbers(getNumbers(content))));
    }

    public static List<String> readFile(String input) {
        List<String> calibrationValues = new ArrayList<>();
        try {
            calibrationValues = Files.readAllLines(Paths.get(input));
            /*for (String line : calibrationValues) {
                System.out.println(line);
            }*/
        } catch (IOException e) {
            System.err.println("Can not read file");
        }
        return calibrationValues;
    }

    public static void changeWrittenNumbersToDigits(List<String> content) {
        for (int i = 0; i < content.size(); i++) {
            content.set(i, content.get(i).replace("one", "o1e"));
            content.set(i, content.get(i).replace("two", "t2o"));
            content.set(i, content.get(i).replace("three", "th3ee"));
            content.set(i, content.get(i).replace("four", "fo4r"));
            content.set(i, content.get(i).replace("five", "fi5e"));
            content.set(i, content.get(i).replace("six", "s6x"));
            content.set(i, content.get(i).replace("seven", "se7en"));
            content.set(i, content.get(i).replace("eight", "ei8ht"));
            content.set(i, content.get(i).replace("nine", "ni9e"));
        }
    }

    public static List<String> getNumbers(List<String> content) {
        List<String> allNumber = new ArrayList<>();
        for (int i = 0; i < content.size(); i++) {
            String theNumber = "";
            for (int j = 0; j < content.get(i).length(); j++) {
                if (Character.isDigit(content.get(i).charAt(j))) {
                    theNumber += content.get(i).charAt(j);
                }
            }
            allNumber.add(theNumber);
        }
        return allNumber;
    }

    public static List<Integer> getRealNumbers(List<String> stringNumbers) {
        List<Integer> realNumbers = new ArrayList<>();
        for (int i = 0; i < stringNumbers.size(); i++) {
            if (stringNumbers.get(i).length() == 1) {
                stringNumbers.set(i, stringNumbers.get(i) + stringNumbers.get(i));
            } else if (stringNumbers.get(i).length() > 2) {
                int lengthOfRow = stringNumbers.get(i).length();
                String newNumber = "";
                newNumber += stringNumbers.get(i).charAt(0);
                newNumber += stringNumbers.get(i).charAt(lengthOfRow - 1);
                stringNumbers.set(i, newNumber);
            }
        }
        for (int i = 0; i < stringNumbers.size(); i++) {
            realNumbers.add(Integer.valueOf(stringNumbers.get(i)));
        }
        return realNumbers;
    }

    public static int getSum(List<Integer> realNumbers) {
        int sum = 0;
        for (int i = 0; i < realNumbers.size(); i++) {
            sum += realNumbers.get(i);
        }
        return sum;
    }
}
