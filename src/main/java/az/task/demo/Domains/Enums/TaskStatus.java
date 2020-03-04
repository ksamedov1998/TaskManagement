package az.task.demo.Domains.Enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TaskStatus {
    ACTIVE(0),DELETED(1);
    private int value;

    TaskStatus(int value){
        this.value=value;
    }

    public static int getStatus(TaskStatus userType){
        List<TaskStatus> type= Arrays.stream(TaskStatus.values()).filter(a->a==userType).collect(Collectors.toList());
        return type.get(0).value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
