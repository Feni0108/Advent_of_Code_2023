package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CamelCards {

    public static void main(String[] args) {
        List<Hand> hands = getOrderedHandList("input_day7.txt");
        System.out.println(getTotalWinnings(hands));
    }

    public static List<Hand> getOrderedHandList(String input) {
        List<Hand> hands = new ArrayList<>();
        int totalWinning = 0;
        try {
            File file = new File(input);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] handAndBid = line.trim().split(" ");
                String hand = handAndBid[0];
                int bid = Integer.parseInt(handAndBid[1]);
                hands.add(new Hand(hand, bid));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
            e.printStackTrace();
        }
        Collections.sort(hands);
        return hands;
    }

    public static int getTotalWinnings(List<Hand> hands) {
        int sum = 0;

        for (int i = 0; i < hands.size(); i++) {
            sum += (i + 1) * hands.get(i).getBid();
        }

        return sum;
    }
}
