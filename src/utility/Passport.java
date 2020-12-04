package utility;

import java.util.function.Predicate;

public class Passport {

    public static final Predicate<String> DAY1_PREDICATE = s -> s.contains("ecl:")
            && s.contains("pid:") && s.contains("eyr:") && s.contains("hcl:") && s.contains("byr:")
            && s.contains("iyr:") && s.contains("hgt:");

    private int byr;
    private int iyr;
    private int eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;

    public static Passport from(String line) {
        String[] contents = line.split(" ");
        Passport p = new Passport();
        for (String content : contents) {
            String[] c = content.split(":");
            switch (c[0]) {
                case "byr":
                    p.setByr(Integer.parseInt(c[1]));
                    break;
                case "iyr":
                    p.setIyr(Integer.parseInt(c[1]));
                    break;
                case "eyr":
                    p.setEyr(Integer.parseInt(c[1]));
                    break;
                case "hgt":
                    p.setHgt(c[1]);
                    break;
                case "hcl":
                    p.setHcl(c[1]);
                    break;
                case "ecl":
                    p.setEcl(c[1]);
                    break;
                case "pid":
                    p.setPid(c[1]);
                    break;
                default:
                    break;
            }
        }
        return p;
    }

    public boolean isValid() {
        if (byr < 1920 || byr > 2002) {
            return false;
        }
        if (iyr < 2010 || iyr > 2020) {
            return false;
        }
        if (eyr < 2020 || eyr > 2030) {
            return false;
        }
        if (!hgt.contains("cm") && !hgt.contains("in")) {
            return false;
        }
        if (hgt.contains("cm")) {
            int height = Integer.parseInt(hgt.substring(0, hgt.indexOf("cm")));
            if (height < 150 || height > 193) {
                return false;
            }
        }
        if (hgt.contains("in")) {
            int height = Integer.parseInt(hgt.substring(0, hgt.indexOf("in")));
            if (height < 59 || height > 76) {
                return false;
            }
        }
        if (!hcl.matches("#([a-f0-9]{6})")) {
            return false;
        }
        if (!ecl.matches("(amb|blu|brn|gry|grn|hzl|oth)")) {
            return false;
        }
        if (pid.length() != 9) {
            return false;
        }
        return true;
    }

    /**
     * @param byr the byr to set
     */
    public void setByr(int byr) {
        this.byr = byr;
    }

    /**
     * @param ecl the ecl to set
     */
    public void setEcl(String ecl) {
        this.ecl = ecl;
    }

    /**
     * @param eyr the eyr to set
     */
    public void setEyr(int eyr) {
        this.eyr = eyr;
    }

    /**
     * @param hcl the hcl to set
     */
    public void setHcl(String hcl) {
        this.hcl = hcl;
    }

    /**
     * @param hgt the hgt to set
     */
    public void setHgt(String hgt) {
        this.hgt = hgt;
    }

    /**
     * @param iyr the iyr to set
     */
    public void setIyr(int iyr) {
        this.iyr = iyr;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }
}
