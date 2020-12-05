import java.util.ArrayList;
import java.util.List;
import utility.Passport;

public class Day4 {

    public static void main(String[] args) {
        var list = FileReader.readStrings("day4.txt");
        System.out.println(part1(list));
        System.out.println(part2(list));
    }

    public static long part1(List<String> list) {
        List<Passport> passports = validate1(list);
        return passports.size();
    }

    public static long part2(List<String> list) {
        List<Passport> passports = validate1(list);
        passports.stream().filter(Passport::isValid).forEach(s -> System.out.println(s.toString()));
        return passports.stream().filter(Passport::isValid).count();
    }

    private static List<Passport> validate1(List<String> list) {
        StringBuilder sb = new StringBuilder();
        List<Passport> passports = new ArrayList<>();
        for (String line : list) {
            sb.append(line).append(" ");
            if (line.isEmpty() || line.equals(list.get(list.size() - 1))) {
                if (Passport.DAY1_PREDICATE.test(sb.toString())) {
                    passports.add(Passport.from(sb.toString()));
                }
                sb = new StringBuilder();
                continue;
            }
        }
        return passports;
    }

}
