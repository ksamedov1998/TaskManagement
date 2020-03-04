package az.task.demo.Domains.Enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TaskState {
    NOT_ASSIGNED(0),ASSIGNED(1),DONE(2),ARCHIVED(-1);
    private int value;

    TaskState(int value){
        this.value=value;
    }

    public static int getStatus(TaskState userType){
        List<TaskState> type= Arrays.stream(TaskState.values()).filter(a->a==userType).collect(Collectors.toList());
        return type.get(0).value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
