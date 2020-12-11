import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {

    public static void main(String[] args) {
        var list = FileReader.readInts("day10.txt");
        System.out.println(part1(list));
        System.out.println(part2(list));
    }

    private static int part1(List<Integer> list) {
        Collections.sort(list);
        list.add(0, 0);
        list.add(list.get(list.size() - 1) + 3);
        int[] input = list.stream().mapToInt(i -> i).toArray();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < input.length; i++) {
            int key = input[i] - input[i - 1];
            int count = 1;
            if (map.containsKey(key)) {
                count += map.get(key);
            }
            map.put(key, count);
        }
        return map.get(1) * map.get(3);
    }

    private static long part2(List<Integer> list) {
        Collections.sort(list);
        Map<Long, Long> map = new HashMap<>();
        long[] input = list.stream().mapToLong(i -> i).toArray();
        map.put(input[input.length - 1], 1L);
        for (int i = input.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < input.length && j <= i + 3; j++) {
                if (input[j] - input[i] <= 3) {
                    long key = input[i];
                    long count = map.get(input[j]);
                    if (map.containsKey(key)) {
                        count += map.get(key);
                    }
                    map.put(key, count);
                }
            }
        }
        return map.get(0L);
    }

}
