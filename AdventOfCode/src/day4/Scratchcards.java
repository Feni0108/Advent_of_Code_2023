package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Scratchcards {

    public static void main(String[] args) {
        //System.out.println(getFullPoints("input_day4.txt"));

        System.out.println(getFullPoints("input_day4.txt"));

        String line = "Card  74: 21  3 32 71 98 69 44 78 34 11 | 83 56 29 18 34  9 63 92 53 22 61 45 41 38 82 27 49  5 32 99 69 17 55 66 96";
        int winning = getNumberOfWinnings(line);
        System.out.println(winning);
    }

    public static int getFullPoints(String filename) {
        //int sumOfWinnerValues = 0;
        int lineNumber = 0;
        int winningNumbersPerRow;
        int numbersOfCopies = 1;
        int sum = 0;

        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i = 1; i <= 190; i++) {
            count.put(i, 1);
        }

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                //sumOfWinnerValues += getWinnerValue(scanner.nextLine());
                winningNumbersPerRow = getNumberOfWinnings(scanner.nextLine());
                lineNumber++;

                numbersOfCopies = count.get(lineNumber);

                for (int i = 0; i < numbersOfCopies; i++) {
                    for (int j = lineNumber + 1; j <= lineNumber + winningNumbersPerRow; j++) {
                        count.put(j, count.get(j) + 1);
                    }
                }
            }
            scanner.close();

            for(Map.Entry entry : count.entrySet()){
                System.out.println("Occurrences of " + entry.getKey() + " = " + entry.getValue());
            }


            for (int value : count.values()) {
                sum += value;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
            e.printStackTrace();
        }

        return sum;
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

    public static int getNumberOfWinnings(String line) {
        int sum = 0;

        String[] cutOffBeginning = line.split(":");
        String onlyNumbers = cutOffBeginning[1];
        String[] separateWinningNumbersAndTheElfsNumber = onlyNumbers.split("\\|");;
        String[] winningNumbersString = separateWinningNumbersAndTheElfsNumber[0].trim().split(" ");
        String[] elfsNumbersString = separateWinningNumbersAndTheElfsNumber[1].trim().split(" ");

        List<Integer> winningNumbers = convertStringArrayToIntegerList(winningNumbersString);
        List<Integer> elfsNumbers = convertStringArrayToIntegerList(elfsNumbersString);

        for (int i = 0; i < winningNumbers.size(); i++) {
            if (elfsNumbers.contains(winningNumbers.get(i))) {
                sum++;
            }
        }

        return sum;
    }

    public static Map<Integer, Integer> getCounts(int lineNumber, int winningNumbers) {
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();

        for (int i = lineNumber + 1; i <= lineNumber + winningNumbers; i++) {
                if(count.containsKey(i)){
                    //if character already traversed, increment it
                    count.put(i, count.get(i) + 1);
                }else{
                    //if character not traversed, add it to hashmap
                    count.put(i, 1);
                }
        }
        for(Map.Entry entry : count.entrySet()){
            System.out.println("Occurrences of " + entry.getKey() + " = " + entry.getValue());
        }

        int sum = 0;
        for (int f : count.values()) {
            sum += f;
        }
        return count;
    }
}
