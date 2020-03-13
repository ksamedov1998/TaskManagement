package az.task.demo.Domains.Enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TaskState {
    NOT_ASSIGNED(0),ASSIGNED(1),DONE(2),ARCHIVED(-1),EXPIRED(2);
    private int value;

    TaskState(int value){
        this.value=value;
    }

    public static int getState(TaskState taskState){
        List<TaskState> type= Arrays.stream(TaskState.values()).filter(a->a==taskState).collect(Collectors.toList());
        return type.get(0).value;
    }

    public static boolean checkState(int taskStateValue){
        return Arrays.stream(TaskState.values()).anyMatch(a->a.getValue()==taskStateValue);
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
