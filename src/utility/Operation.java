package utility;

public class Operation {

    private OperationType op;
    private int value;

    public Operation(OperationType op, int value) {
        this.op = op;
        this.value = value;
    }

    public static Operation fromOp(Operation operation) {
        return new Operation(operation.getOp(), operation.getValue());
    }

    public static Operation fromString(String operation) {
        var groups = operation.split(" ");
        int val = Integer.parseInt(groups[1]);
        switch (groups[0]) {
            case "acc":
                return new Operation(OperationType.ACC, val);
            case "jmp":
                return new Operation(OperationType.JMP, val);
            case "nop":
                return new Operation(OperationType.NOP, val);
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * @return the op
     */
    public OperationType getOp() {
        return op;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param op the op to set
     */
    public void setOp(OperationType op) {
        this.op = op;
    }

}
