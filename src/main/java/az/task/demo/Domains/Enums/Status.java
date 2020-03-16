package az.task.demo.Domains.Enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Status {
    ACTIVE(0),DELETED(1);
    private int value;

    Status(int value){
        this.value=value;
    }

    public static int getStatus(Status userType){
        List<Status> type= Arrays.stream(Status.values()).filter(a->a==userType).collect(Collectors.toList());
        return type.get(0).value;
    }

    public static boolean checkState(int taskStatusValue){
        return Arrays.stream(Status.values()).anyMatch(a->a.getValue()==taskStatusValue);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
