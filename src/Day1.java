import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day1 {
    private static final int TARGET = 2020;

    public static void main(String[] args) throws Exception {
        var list = FileReader.readInts("day1.txt");
        System.out.println(part1(list));
        System.out.println(part2(list));
    }

    public static long part1(List<Integer> list) {
        var compliments = new HashMap<Integer, Integer>();
        for (Integer num : list) {
            int compliment = TARGET - num;
            if (compliments.containsKey(num)) {
                return num * compliment;
            } else {
                compliments.put(compliment, num);
            }
        }
        throw new IllegalArgumentException("Unsolvable");
    }

    public static long part2(List<Integer> list) {
        Collections.sort(list);
        for (int i = 0; i < list.size() - 2; i++) {
            int x = list.get(i);
            int j = i + 1;
            int k = list.size() - 1;
            while (j < k) {
                int y = list.get(j);
                int z = list.get(k);
                if (x + y + z == TARGET) {
                    return x * y * z;
                } else if (x + y + z > TARGET) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        throw new IllegalArgumentException("Unsolvable");
    }
}
