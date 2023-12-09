package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HauntedWasteland {

    public static void main(String[] args) {
        System.out.println(getSteps(getNodes("input_day8.txt")));
    }

    public static List<Node> getNodes(String input) {


        List<Node> nodes = new ArrayList<>();

        try {
            File file = new File(input);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern pattern = Pattern.compile("[a-zA-Z]+");
                Matcher matcher = pattern.matcher(line);
                List<String> codes = new ArrayList<>();
                while (matcher.find()) {
                    codes.add(matcher.group());
                }
                Node node = new Node(codes.get(0), codes.get(1), codes.get(2));
                nodes.add(node);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
            e.printStackTrace();
        }

        return nodes;
    }

    public static int getSteps(List<Node> nodes) {
        String leftRight = "LRRRLRLLLLLLLRLRLRRLRRRLRRLRRRLRRLRRRLLRRRLRRLRLRRRLRRLRRRLLRLLRRRLRRRLRLLRLRLRRRLRRLRRLRRLRLRRRLRRLRRRLLRLLRLLRRLRLLRLRRLRLRLRRLRRRLLLRRLRRRLLRRLRLRLRRRLRLRRRLLRLLLRRRLLLRRLLRLLRRLLRLRRRLRLRRLRRLLRRLRLLRLRRRLRRRLRLRRRLRLRLRRLRLRRRLRRRLRRRLRRLRRLRRRLLRLRLLRLLRRRR";
        List<Node> nodeWay = new ArrayList<>();
        int id = getNextNodeId(nodes, "AAA");

        while (!getHasLastCode(nodeWay)) {
            for (int i = 0; i < leftRight.length(); i++) {
                Node node = nodes.get(id);
                if (leftRight.charAt(i) == 'L') {
                    id = getNextNodeId(nodes, node.getLeftSide());
                    Node nextNode = nodes.get(id);
                    nodeWay.add(nextNode);
                    if (nextNode.getNodeName().equals("ZZZ")) {
                        break;
                    }
                } else if (leftRight.charAt(i) == 'R') {
                    id = getNextNodeId(nodes, node.getRigthSide());
                    Node nextNode = nodes.get(id);
                    nodeWay.add(nextNode);
                    if (nextNode.getNodeName().equals("ZZZ")) {
                        break;
                    }
                }
            }
        }

        return nodeWay.size();
    }

    public static int getNextNodeId(List<Node> nodes, String nodeName) {
        int id = 0;

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getNodeName().equals(nodeName)) {
                id = i;
            }
        }

        return id;
    }

    public static boolean getHasLastCode(List<Node> nodes) {
        boolean hasLastcode = false;
        for (Node node : nodes) {
            if (node.getNodeName().equals("ZZZ")) {
                hasLastcode = true;
            }
        }
        return hasLastcode;
    }
}
