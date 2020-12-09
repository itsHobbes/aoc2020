import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Queue;

public class Day9 {

    public static void main(String[] args) {
        var list = FileReader.readLongs("day9.txt");
        final int preamble = 25;
        long part1Result = part1(list, preamble);
        System.out.println(part1Result);
        System.out.println(part2(list, part1Result));
    }

    private static long part1(List<Long> list, int preamble) {
        Queue<Long> queue = new LinkedList<>();
        for (long num : list) {
            if (queue.size() == preamble) {
                if (!canPreambleSumToTarget(queue, num)) {
                    return num;
                }
                queue.poll();
            }
            queue.add(num);
        }
        return 0;
    }

    private static long part2(List<Long> list, long target) {
        Queue<Long> queue = new LinkedList<>();
        for (long num : list) {
            queue.add(num);
            while (!queue.isEmpty()) {
                long queueSum = queue.stream().mapToLong(x -> x).sum();
                if (queueSum < target) {
                    break;
                }
                if (queueSum > target) {
                    queue.poll();
                    continue;
                }
                if (queueSum == target) {
                    LongSummaryStatistics s = queue.stream().mapToLong(x -> x).summaryStatistics();
                    return s.getMin() + s.getMax();
                }
            }
        }
        return 0;
    }



    private static boolean canPreambleSumToTarget(Queue<Long> preamble, long target) {
        List<Long> nums = new ArrayList<>(preamble);
        var compliments = new HashMap<Long, Long>();
        for (long num : nums) {
            long compliment = target - num;
            if (compliments.containsKey(num) && compliment != num) {
                return true;
            } else {
                compliments.put(compliment, num);
            }
        }
        return false;
    }

}
