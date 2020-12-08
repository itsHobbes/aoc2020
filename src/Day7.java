import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Day7 {

    static final Pattern BAG_PATTERN = Pattern.compile("^(.+) bags contain (.+)\\.$");
    static final Pattern BAG_CONTAINER_PATTERN = Pattern.compile("^(\\d+) (.+) bags?$");

    public static void main(String[] args) {
        var list = FileReader.readStrings("day7.txt");
        System.out.println(part1(list));
        System.out.println(part2(list));

    }

    public static long part1(List<String> list) {
        var bagMap = getBagMap(list);
        return bagMap.keySet().stream().filter(colour -> contains("shiny gold", bagMap, colour)).count() - 1;
    }

    private static long part2(List<String> list) {
        var bagMap = getBagMap(list);
        return countBags(bagMap, "shiny gold") - 1;
    }

    private static Map<String, Map<String, Integer>> getBagMap(List<String> list) {
        Map<String, Map<String, Integer>> bagMap = new HashMap<>();
        for (String line : list) {
            var matcher = BAG_PATTERN.matcher(line);
            if (matcher.find()) {
                var innerMap = bagMap.computeIfAbsent(matcher.group(1), s -> new HashMap<>());
                for (var item : matcher.group(2).split(", ")) {
                    matcher = BAG_CONTAINER_PATTERN.matcher(item);
                    if (matcher.find()) {
                        innerMap.put(matcher.group(2), Integer.parseInt(matcher.group(1)));
                    }
                }
            }
        }
        return bagMap;
    }
    
    private static boolean contains(String target, Map<String, Map<String, Integer>> bagMap, String colour) {
        return colour.equals(target) || bagMap.getOrDefault(colour, Map.of()).keySet()
                .stream().anyMatch(key -> contains(target, bagMap, key));
    }
    
    private static int countBags(Map<String, Map<String, Integer>> bagMap, String colour) {
        return bagMap.getOrDefault(colour, Map.of()).entrySet().stream()
                .mapToInt(e -> e.getValue() * countBags(bagMap, e.getKey())).sum() + 1;
    }
}
