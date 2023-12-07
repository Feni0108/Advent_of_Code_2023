package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class IfYouGiveASeedAFertilizer {
    public static void main(String[] args) {
        int seedNumber = 79;
        List<Integer> lineDetails = new ArrayList<>(Arrays.asList(52, 50, 48));

        System.out.println(getLocationNumber(readFile("input_day5.txt")));


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

    public static long getNextNumberFromLine(String line, long code) {
        String[] lineNumbers = line.split("\\s");

        if (code >= Long.parseLong(lineNumbers[1]) && code <= Long.parseLong(lineNumbers[1]) + Long.parseLong(lineNumbers[2])) {
            long decoder = code - Long.parseLong(lineNumbers[1]);
            code = Long.parseLong(lineNumbers[0]) + decoder;
        }

        return code;
    }

    public static List<Integer> getRoundsStartID(List<String> input) {
        List<Integer> roundIDs = new ArrayList<>();

        for (int i = 1; i < input.size(); i++) {
            if (isLetter(input.get(i))) {
                roundIDs.add(i);
            }
        }

        return roundIDs;
    }

    public static boolean isLetter(String strNum) {
        Pattern pattern = Pattern.compile ("[A-Za-z]");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).find();
    }

    public static long getLocationNumber(List<String> input) {
        String[] seeds = input.get(0).split(":");
        String[] seedNumbers = seeds[1].trim().split("\\s");


        List<Integer> roundStartIds = getRoundsStartID(input);
        roundStartIds.add(248);

        List<Long> allLocationNumbers = new ArrayList<>();

        for (int i = 0; i < seedNumbers.length; i++) {
            long seedNumber = Long.parseLong(seedNumbers[i]);
            for (int j = 0; j < roundStartIds.size() - 1; j++) {
                int index = roundStartIds.get(j) + 1;
                while (index < roundStartIds.get(j + 1) - 1) {
                    long newSeedCode = getNextNumberFromLine(input.get(index), seedNumber);
                    if (newSeedCode != seedNumber) {
                        seedNumber = newSeedCode;
                        break;
                    }
                    index++;
                }
            }

          allLocationNumbers.add(seedNumber);
        }

        return Collections.min(allLocationNumbers);
    }
}
