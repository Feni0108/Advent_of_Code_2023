package day13;

import java.util.ArrayList;
import java.util.List;

public class Mirror {
    private List<String> horizontal = new ArrayList<>();
    private List<String> vertical = new ArrayList<>();

    public void addLine(String line) {
        this.horizontal.add(line);
    }

    public void flip() {
        StringBuilder builder = new StringBuilder();
        int length = horizontal.get(0).length();
        int rowElements = 0;

        while (rowElements != length) {
            for (int i = 0; i < horizontal.size(); i++) {
                builder.append(horizontal.get(i).charAt(rowElements));
            }
            vertical.add(builder.toString());
            builder.delete(0, builder.length());
            rowElements++;
        }
    }

    public long getSum() {
        int col = findMatch(vertical);
        int row = findMatch(horizontal);
        return row * 100L + col;
    }

    private int findMatch(List<String> strings) {
        List<Integer> potentitals = new ArrayList<>();

        for (int i = 0; i < strings.size() - 1; i++) {
            if (strings.get(i).equals(strings.get(i + 1))) {
                potentitals.add(i);
            }
        }

        for (int pos : potentitals) {
            boolean found = true;
            List<String> square = getSquarePattern(strings, pos);
            int numSteps = (square.size() / 2) - 1;
            for (int i = (square.size() / 2) - 1, j = square.size() / 2; j < square.size(); i--, j++) {
                String first = square.get(i);
                String second = square.get(j);
                if (!first.equals(second)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return pos + 1;
            }
        }
        return 0;
    }

    public List<String> getSquarePattern(List<String> onePattern, int symmetricId) {
        int middleId = onePattern.size() / 2;
        List<String> square = new ArrayList<>();

        if (symmetricId < middleId) {
            for (int i = 0; i < (2 * symmetricId) + 2; i++) {
                square.add(onePattern.get(i));
            }
        } else if (symmetricId >= middleId) {
            int diff = onePattern.size() - 1 - symmetricId;
            for (int i = (symmetricId + 1) - diff; i < symmetricId + diff + 1; i++) {
                square.add(onePattern.get(i));
            }
        }
        return square;
    }
}
