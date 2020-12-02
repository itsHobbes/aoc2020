package utility;

public class PasswordPolicy {

    final int min;
    final int max;
    final char requirement;
    final String password;

    private PasswordPolicy(int min, int max, char requirement, String password) {
        this.min = min;
        this.max = max;
        this.requirement = requirement;
        this.password = password;
    }

    public static PasswordPolicy from(String line) {
        int dashIndex = line.indexOf('-');
        int min = Integer.parseInt(line.substring(0, dashIndex));
        int max = Integer.parseInt(line.substring(dashIndex + 1, line.indexOf(' ')));
        int colonIndex = line.indexOf(':');
        char requirement = line.charAt(colonIndex - 1);
        String password = line.substring(colonIndex + 2, line.length());
        return new PasswordPolicy(min, max, requirement, password);
    }

    public boolean isValid() {
        int counter = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == requirement) {
                counter++;
            }
        }
        return counter >= min && counter <= max;
    }

    public boolean isValidPart2() {
        int counter = 0;
        if (password.charAt(min - 1) == requirement) {
            counter++;
        }
        if (password.charAt(max - 1) == requirement) {
            counter++;
        }
        return counter == 1;
    }
}
