package enums;

import java.util.Arrays;

public enum Operation {
    INSERT(1),
    UPDATE(2),
    DELETE(3);

    private int operationId;
    Operation(int operationId){
        this.operationId = operationId;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public static Operation getOperation(int operationId) {
       return Arrays.stream(values())
               .filter(operation -> operation.operationId == operationId)
               .findFirst()
               .orElse(Operation.INSERT);
    }
}
