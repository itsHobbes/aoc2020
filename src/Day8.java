import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utility.Operation;
import utility.OperationType;

public class Day8 {

    public static void main(String[] args) {
        var list = FileReader.readStrings("day8.txt");
        System.out.println(part1(list));
        System.out.println(part2(list));
    }

    private static int part1(List<String> list) {
        var ops = parseOps(list);
        return execute(ops).acc;
    }

    private static long part2(List<String> list) {
        var ops = parseOps(list);
        for (int i = 0; i < list.size(); i++) {
            var newOp = ops.get(i);
            if (!flipOperation(newOp)) {
                continue;
            }
            Result r = execute(ops);
            if (r.lastOp == ops.size() - 1) {
                return r.acc;
            }
            flipOperation(newOp);
        }
        return 0;
    }

    private static boolean flipOperation(Operation newOp) {
        switch (newOp.getOp()) {
            case JMP:
                newOp.setOp(OperationType.NOP);
                return true;
            case NOP:
                newOp.setOp(OperationType.JMP);
                return true;
            default:
                return false;
        }
    }

    private static List<Operation> parseOps(List<String> list) {
        return list.stream().map(x -> Operation.fromString(x)).collect(Collectors.toList());
    }

    private static Result execute(List<Operation> ops) {
        int acc = 0;
        List<Integer> opids = new ArrayList<>();
        for (int i = 0; i < ops.size(); i++) {
            if (opids.contains(i)) {
                break;
            } else {
                opids.add(i);
            }
            Operation op = ops.get(i);
            switch (op.getOp()) {
                case ACC:
                    acc += op.getValue();
                    break;
                case JMP:
                    i += op.getValue() - 1;
                    break;
                case NOP:
                default:
                    break;
            }
        }
        return new Result(acc, opids.get(opids.size() - 1));
    }

    static class Result {
        final int acc;
        final int lastOp;

        public Result(int acc, int lastOp) {
            this.acc = acc;
            this.lastOp = lastOp;
        }
    }


}
