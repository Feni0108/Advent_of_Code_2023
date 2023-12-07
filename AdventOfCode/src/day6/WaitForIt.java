package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WaitForIt {

    public static void main(String[] args) {
        System.out.println(getWinnerRecords(readFile("input_day6.txt")));
        System.out.println(getWinnerRecordSecondChallenge(readFile("input_day6.txt")));
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

    public static long getWinnerRecordSecondChallenge(List<String> input) {
        input.set(0, input.get(0).replaceAll(" ", ""));
        input.set(1, input.get(1).replaceAll(" ", ""));
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input.get(0));
        Matcher matcher1 = pattern.matcher(input.get(1));
        long time = 0;
        long dest = 0;
        if (matcher.find() && matcher1.find()) {
            time = Long.parseLong(matcher.group());
            dest = Long.parseLong(matcher1.group());
        }
        System.out.println(time + " " + dest);

        return getWinnerHolds(time, dest);
    }

    public static int getWinnerHolds(long time, long initialDest) {
        int millisecondsHold;
        int velocity;
        long dest;
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
