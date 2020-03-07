package az.task.demo.Domains.Enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserStatus {
    ACTIVE(0),DELETED(1);
    private int value;

    UserStatus(int value){
        this.value=value;
    }

    public static int getStatus(UserStatus userType){
        List<UserStatus> type= Arrays.stream(UserStatus.values()).filter(a->a==userType).collect(Collectors.toList());
        return type.get(0).value;
    }

    public static boolean checkStatus(int userStatusValue){
        return Arrays.stream(UserStatus.values()).anyMatch(a->a.getValue()==userStatusValue);
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
