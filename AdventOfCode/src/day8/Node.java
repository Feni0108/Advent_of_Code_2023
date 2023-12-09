package day8;

public class Node {
    private String nodeName;
    private String leftSide;
    private String rigthSide;

    public Node(String nodeName, String leftSide, String rigthSide) {
        this.nodeName = nodeName;
        this.leftSide = leftSide;
        this.rigthSide = rigthSide;
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRigthSide() {
        return rigthSide;
    }
}
