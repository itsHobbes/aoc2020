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

    public static long part1(List<PasswordPolicy> policies) {
        return policies.stream().filter(PasswordPolicy::isValid).count();
    }

    public static long part2(List<PasswordPolicy> policies) {
        return policies.stream().filter(PasswordPolicy::isValidPart2).count();
    }

}
