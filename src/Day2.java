import java.util.List;
import java.util.stream.Collectors;
import utility.PasswordPolicy;

public class Day2 {

    public static void main(String[] args) {
        var list = FileReader.readStrings("day2.txt").stream().map(PasswordPolicy::from)
                .collect(Collectors.toList());
        System.out.println(part1(list));
        System.out.println(part2(list));
    }

    public static int part1(List<PasswordPolicy> policies) {
        int counter = 0;
        for (PasswordPolicy passwordPolicy : policies) {
            if (passwordPolicy.isValid()) {
                counter++;
            }
        }
        return counter;
    }

    public static int part2(List<PasswordPolicy> policies) {
        int counter = 0;
        for (PasswordPolicy passwordPolicy : policies) {
            if (passwordPolicy.isValidPart2()) {
                counter++;
            }
        }
        return counter;
    }

}
