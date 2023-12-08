package day7;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Hand implements Comparable {
    private int bid;
    private int strength;
    private int[] cards = new int[5];
    private HashMap<Integer, Integer> cardsAndFreqs = new HashMap<Integer, Integer>();
    //private String[] ranks= new String[]{"A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"};
    private String[] ranks= new String[]{"A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J"};

    public Hand(String line, int bid) {
        this.bid = bid;
        int numberOfJokers = 0;
        String[] cardChars = line.split("");
        for (int i = 0; i < cardChars.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                if (cardChars[i].equals(ranks[j])) {
                    if (cardChars[i].equals("J")) {
                        numberOfJokers++;
                    } else {
                        if (cardsAndFreqs.containsKey(j)){
                            cardsAndFreqs.put(j, cardsAndFreqs.get(j) + 1);
                        } else {
                            cardsAndFreqs.put(j, 1);
                        }
                    }
                    cards[i] = j;
                }
            }
        }

        if (cardsAndFreqs.isEmpty()) {
            cardsAndFreqs.put(12, 5);
        } else {
            int maxValueKey = 0;
            int maxValue = (Collections.max(cardsAndFreqs.values()));  // This will return max value in the HashMap
            for (Map.Entry<Integer, Integer> entry : cardsAndFreqs.entrySet()) {  // Iterate through HashMap
                if (entry.getValue()==maxValue) {
                    maxValueKey = entry.getKey();// Print the key with max value
                }
            }
            cardsAndFreqs.put(maxValueKey, cardsAndFreqs.get(maxValueKey) + numberOfJokers);
        }

        strength = 7 - cardsAndFreqs.size();

        if (cardsAndFreqs.containsValue(3)) {
            strength += 1;
        } else if (cardsAndFreqs.size() == 1 || cardsAndFreqs.containsValue(4)){
            strength += 2;
        }

    }

    /*public Hand(String line, int bid) {
        this.bid = bid;
        String[] cardChars = line.split("");
        for (int i = 0; i < cardChars.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                if (cardChars[i].equals(ranks[j])) {
                    if (cardsAndFreqs.containsKey(j)){
                            cardsAndFreqs.put(j, cardsAndFreqs.get(j) + 1);
                    } else {
                            cardsAndFreqs.put(j, 1);
                    }
                    cards[i] = j;
                }
            }
        }

        strength = 7 - cardsAndFreqs.size();

        if (cardsAndFreqs.containsValue(3)) {
             strength += 1;
        } else if (cardsAndFreqs.size() == 1 || cardsAndFreqs.containsValue(4)){
            strength += 2;
        }
    }*/

    @Override
    public int compareTo(Object o) {
        Hand otherHand = (Hand) o;
        if (strength != otherHand.getStrength()) {
            return strength - otherHand.getStrength();
        } else {
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] != otherHand.getCards()[i]) {
                    return otherHand.getCards()[i] - cards[i];
                }
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(this.strength);
    }

    public int getBid() {
        return bid;
    }

    public int getStrength() {
        return strength;
    }

    public HashMap<Integer, Integer> getCardsAndFreqs() {
        return cardsAndFreqs;
    }

    public int[] getCards() {
        return cards;
    }
}
