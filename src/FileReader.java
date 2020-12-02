import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public static List<? extends Object> readLines(String file,
            Function<String, ? extends Object> mapper) {
        try (Stream<String> stream = Files.lines(Paths.get("res/" + file))) {
            return stream.map(mapper).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static List<String> readStrings(String file) {
        return (List<String>) readLines(file, String::toString);
    }

    public static List<Integer> readInts(String file) {
        return (List<Integer>) readLines(file, Integer::parseInt);
    }

}
