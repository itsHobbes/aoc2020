import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        var list = FileReader.readStrings("day3.txt");
        System.out.println(part1(list));
        System.out.println(part2(list));
    }

    public static int part1(List<String> list) {
        int treeCount = 0;
        int width = list.get(0).length();
        int xCord = 0;
        for (String string : list) {
            if (string.equals(list.get(0))) {
                continue;
            }
            xCord += 3;
            if (xCord >= width) {
                xCord = xCord % width;
            }
            if (string.charAt(xCord) == '#') {
                treeCount++;
            }
        }
        return treeCount;
    }

    public static long part2(List<String> list) {
        var pairs = initPairs();
        int width = list.get(0).length();
        long totalCount = 0;

        for (Pair pair : pairs) {
            int xCord = 0;
            int treeCount = 0;
            for (int i = pair.y; i < list.size(); i += pair.y) {
                xCord += pair.x;
                if (xCord >= width) {
                    xCord = xCord % width;
                }
                if (list.get(i).charAt(xCord) == '#') {
                    treeCount++;
                }
            }
            totalCount = totalCount == 0 ? treeCount : totalCount * treeCount;
        }

        return totalCount;
    }

    private static List<Pair> initPairs() {
        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(1, 1));
        pairs.add(new Pair(3, 1));
        pairs.add(new Pair(5, 1));
        pairs.add(new Pair(7, 1));
        pairs.add(new Pair(1, 2));
        return pairs;
    }

    private static class Pair {

        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
