import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        var list = FileReader.readStrings("day3.txt");
        System.out.println(part1(list));
        System.out.println(part2(list));
    }

    public static long part1(List<String> list) {
        return countTrees(new Slope(3, 1), list);
    }

    public static long part2(List<String> list) {
        var slopes = initSlopes();
        long totalCount = 1;
        for (Slope slope : slopes) {
            totalCount *= countTrees(slope, list);
        }
        return totalCount;
    }

    public static long countTrees(Slope slope, List<String> list) {
        int x = 0;
        int width = list.get(0).length();
        int treeCount = 0;
        for (int i = slope.y; i < list.size(); i += slope.y) {
            x += slope.x;
            if (x >= width) {
                x = x % width;
            }
            if (list.get(i).charAt(x) == '#') {
                treeCount++;
            }
        }
        return treeCount;
    }

    private static List<Slope> initSlopes() {
        List<Slope> slopes = new ArrayList<>();
        slopes.add(new Slope(1, 1));
        slopes.add(new Slope(3, 1));
        slopes.add(new Slope(5, 1));
        slopes.add(new Slope(7, 1));
        slopes.add(new Slope(1, 2));
        return slopes;
    }

    private static class Slope {

        public int x;
        public int y;

        public Slope(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
