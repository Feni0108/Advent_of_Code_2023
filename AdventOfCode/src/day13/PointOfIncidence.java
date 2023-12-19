package day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PointOfIncidence {
    public static void main(String[] args) {
        System.out.println(solve(readFile("input_day13.txt")));
    }

    public static long solve(List<String> patterns) {
        long sum = 0L;

        Mirror currentMirror = new Mirror();

        for (String line : patterns) {
            if (line.isBlank()) {
                currentMirror.flip();
                sum += currentMirror.getSum();
                currentMirror = new Mirror();
                continue;
            }
            currentMirror.addLine(line);
        }
        currentMirror.flip();
        sum += currentMirror.getSum();

        return sum;
    }

    public static List<String> readFile(String input) {
        List<String> patterns = new ArrayList<>();
        try {
            patterns = Files.readAllLines(Paths.get(input));
        } catch (IOException e) {
            System.err.println("Can not read file");
        }
        return patterns;
    }
}
