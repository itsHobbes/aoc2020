import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 {

    public static void main(String[] args) {
        var list = FileReader.readStrings("day5.txt");
        int maxId = part1(list);
        System.out.println(maxId);
        System.out.println(part2(list, maxId));
    }

    private static int part1(List<String> list) {
        int maxID = 0;
        for (String seatCode : list) {
            int id = getSeatId(seatCode);
            maxID = id > maxID ? id : maxID;
        }
        return maxID;
    }

    private static int getSeatId(String seatCode) {
        int row = Integer.parseInt(seatCode.substring(0, 7).replace('F', '0').replace('B', '1'), 2);
        int col = Integer.parseInt(
                seatCode.substring(7, seatCode.length()).replace('R', '1').replace('L', '0'), 2);
        int id = row * 8 + col;
        return id;
    }

    private static int part2(List<String> list, int maxId) {
        List<Integer> seats = new ArrayList<>();
        for (String seatCode : list) {
            seats.add(getSeatId(seatCode));
        }
        int minId = seats.stream().mapToInt(i -> i).min().orElseThrow(NoSuchElementException::new);
        var nums = IntStream.range(minId, maxId).boxed().collect(Collectors.toList());
        nums.removeAll(seats);
        if (nums.size() == 1) {
            return nums.get(0);
        }
        return 0;
    }

}
