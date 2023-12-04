package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeConundrum {

    public static void main(String[] args) {
        System.out.println(getSumOfPossibleGameIDs("input_day2.txt"));
    }

    public static int getSumOfPossibleGameIDs(String filename) {
        int sumOfIds = 0;
        int sumOfPowers = 0;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                sumOfPowers += powerOfMinimums(scanner.nextLine());
                //sumOfIds += gameNumberOfLineIfPossible(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
            e.printStackTrace();
        }

        return sumOfPowers;
    }

    public static int gameNumberOfLineIfPossible(String line) {
        int redCubes = 12;
        int greenCubes = 13;
        int blueCubes = 14;
        int gameID = 0;
        boolean isPossible = true;

        line = line.replaceAll("\\s", "");
        String[] games = line.split(":");
        String[] sets = games[1].split(";");

        for (int i = 0; i < sets.length; i++) {
            String[] colors = sets[i].split(",");
            for (int j = 0; j < colors.length; j++) {
                int number = getNumbersFromAString(colors[j]);
                if ((colors[j].contains("red") && number > redCubes) ||
                        (colors[j].contains("green") && number > greenCubes) ||
                        (colors[j].contains("blue") && number > blueCubes)) {
                    isPossible = false;
                }
            }
        }

        if (isPossible) {
            gameID = getNumbersFromAString(games[0]);
        }

        return gameID;
    }

    public static int getNumbersFromAString(String gameName) {
        int number = 0;
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(gameName);
        if (matcher.find()) {
            number = Integer.parseInt(matcher.group());
        }
        return number;
    }

    public static int powerOfMinimums(String line) {
        int power = 1;

        line = line.replaceAll("\\s", "");
        String[] games = line.split(":");
        String[] sets = games[1].split(";");

        List<Integer> red = new ArrayList<>();
        List<Integer> green = new ArrayList<>();
        List<Integer> blue = new ArrayList<>();

        for (int i = 0; i < sets.length; i++) {
            String[] colors = sets[i].split(",");
            for (int j = 0; j < colors.length; j++) {
                if (colors[j].contains("red")) {
                    red.add(getNumbersFromAString(colors[j]));
                } else if (colors[j].contains("green")) {
                    green.add(getNumbersFromAString(colors[j]));
                } else if (colors[j].contains("blue")) {
                    blue.add(getNumbersFromAString(colors[j]));
                }
            }
        }

        power = Collections.max(red) * Collections.max(green) * Collections.max(blue);

        return power;
    }
}
