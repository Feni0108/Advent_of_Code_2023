package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Scratchcards {

    public static void main(String[] args) {
        System.out.println(getFullPoints("input_day4.txt"));
    }

    public static int getFullPoints(String filename) {
        int sumOfWinnerValues = 0;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                sumOfWinnerValues += getWinnerValue(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
            e.printStackTrace();
        }

        return sumOfWinnerValues;
    }

    public static int getWinnerValue(String line) {
        int winnerValue = 0;

        String[] cutOffBeginning = line.split(":");
        String onlyNumbers = cutOffBeginning[1];
        String[] separateWinningNumbersAndTheElfsNumber = onlyNumbers.split("\\|");;
        String[] winningNumbersString = separateWinningNumbersAndTheElfsNumber[0].trim().split(" ");
        String[] elfsNumbersString = separateWinningNumbersAndTheElfsNumber[1].trim().split(" ");

        List<Integer> winningNumbers = convertStringArrayToIntegerList(winningNumbersString);
        List<Integer> elfsNumbers = convertStringArrayToIntegerList(elfsNumbersString);

        for (int i = 0; i < winningNumbers.size(); i++) {
            if (elfsNumbers.contains(winningNumbers.get(i))) {
                if (winnerValue == 0) {
                    winnerValue++;
                } else {
                    winnerValue *= 2;
                }
            }
        }

        return winnerValue;
    }

    public static List<Integer> convertStringArrayToIntegerList(String[] array) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals("")) {
                result.add(Integer.parseInt(array[i]));
            }
        }
        return result;
    }
}
