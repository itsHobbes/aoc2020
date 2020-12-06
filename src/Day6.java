import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 {

    public static void main(String[] args) {
        var list = FileReader.readStrings("day6.txt");
        System.out.println(part1(list));
        System.out.println(part2(list));
    }

    private static int part1(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            sb.append(s);
            if (s.isEmpty() || i == list.size() - 1) {
                sum += sb.toString().chars().distinct().count();
                sb = new StringBuilder();
            }
        }
        return sum;
    }

    private static int part2(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            sb.append(s).append(" ");
            if (s.isEmpty() || i == list.size() - 1) {
                String[] groups = sb.toString().split(" ");
                Set<Integer> chars = groups[0].chars().boxed().collect(Collectors.toSet());
                for (String group : groups) {
                    chars.retainAll(group.chars().boxed().collect(Collectors.toSet()));
                }
                sum += chars.size();
                sb = new StringBuilder();
            }
        }
        return sum;
    }

}
