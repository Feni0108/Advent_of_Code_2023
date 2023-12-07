package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WaitForIt {

    public static void main(String[] args) {
        System.out.println(getWinnerHolds(30, 200));
        System.out.println(getWinnerRecords(readFile("input_day6.txt")));
    }

    public static List<String> readFile(String input) {
        List<String> readInput = new ArrayList<>();
        try {
            readInput = Files.readAllLines(Paths.get(input));
        } catch (IOException e) {
            System.err.println("Can not read file");
        }
        return readInput;
    }

    public static int getWinnerRecords(List<String> input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input.get(0));
        Matcher matcher1 = pattern.matcher(input.get(1));
        List<Integer> times = new ArrayList<>();
        List<Integer> destinations = new ArrayList<>();
        while (matcher.find()) {
            times.add(Integer.parseInt(matcher.group()));
        }
        while (matcher1.find()) {
            destinations.add(Integer.parseInt(matcher1.group()));
        }

        int multiplication = 1;

        for (int i = 0; i < times.size(); i++) {
            multiplication *= getWinnerHolds(times.get(i), destinations.get(i));
        }

        return multiplication;
    }

    public static int getWinnerHolds(int time, int initialDest) {
        int millisecondsHold;
        int velocity;
        int dest;
        int counter = 0;

        for (int i = 0; i <= time; i++) {
            if (i == time) {
                dest = 0;
            } else {
                millisecondsHold = i + 1;
                velocity = millisecondsHold;
                dest = (time - millisecondsHold) * velocity;
                if (dest > initialDest) {
                    counter++;
                }
            }
        }

        return counter;
    }
}
